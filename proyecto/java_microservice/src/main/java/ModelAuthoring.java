import java.io.*;
import java.time.*;
import java.util.*;
import jakarta.json.*;
import jakarta.servlet.http.*;
import asignaturas.*;

/** Shared HTTP boundary for faculty model authoring and consultation. */
public abstract class ModelAuthoring extends HttpServlet {
    private final String kind;
    protected ModelAuthoring(String kind) { this.kind = kind; }
    @Override protected void doGet(HttpServletRequest q, HttpServletResponse r) throws IOException { handle(q, r, "GET"); }
    @Override protected void doPost(HttpServletRequest q, HttpServletResponse r) throws IOException { handle(q, r, "POST"); }
    @Override protected void doPut(HttpServletRequest q, HttpServletResponse r) throws IOException { handle(q, r, "PUT"); }

    private void handle(HttpServletRequest q, HttpServletResponse r, String method) throws IOException {
        q.setCharacterEncoding("UTF-8"); r.setCharacterEncoding("UTF-8"); r.setContentType("application/json");
        synchronized (ModelStore.LOCK) {
            try {
                Input in = new Input(q, kind);
                ModelStore store = new ModelStore(in.required("uuid"));
                JsonValue result;
                if (method.equals("GET")) result = read(store, in);
                else {
                    ModelStore.require(!method.equals("PUT") || Set.of("unit", "plan", "scale").contains(kind), "Editing is not supported here");
                    result = change(store, in, method.equals("PUT"));
                    store.save();
                    r.setStatus(method.equals("PUT") ? 200 : 201);
                }
                r.getWriter().write(result.toString());
            } catch (ModelStore.Problem e) {
                r.setStatus(e.status); r.getWriter().write(Json.createObjectBuilder().add("error", e.getMessage()).build().toString());
            } catch (JsonException | ClassCastException | ArithmeticException | IllegalArgumentException | java.time.DateTimeException | NullPointerException e) {
                r.setStatus(400); r.getWriter().write("{\"error\":\"Invalid JSON, field type, number, or date\"}");
            } catch (Exception e) {
                java.util.logging.Logger.getLogger(ModelAuthoring.class.getName()).log(java.util.logging.Level.SEVERE, "Model authoring failed", e);
                r.setStatus(500); r.getWriter().write("{\"error\":\"Unable to load or save workspace model\"}");
            }
        }
    }

    static final class Input {
        final HttpServletRequest request;
        final JsonObject json;
        final JsonValue rawRequirement;
        Input(HttpServletRequest q, String kind) throws IOException {
            request = q;
            JsonObject data = Json.createObjectBuilder().build(); JsonValue raw = null;
            if (q.getContentType() != null && q.getContentType().toLowerCase(Locale.ROOT).startsWith("application/json")) {
                String body = q.getReader().lines().collect(java.util.stream.Collectors.joining("\n"));
                if (!body.isBlank()) {
                    JsonValue v;
                    try (JsonReader reader = Json.createReader(new StringReader(body))) { v = reader.readValue(); }
                    if (kind.equals("unit") && (v == JsonValue.NULL || v instanceof JsonObject &&
                            !((JsonObject)v).containsKey("id") && !((JsonObject)v).containsKey("name") &&
                            !((JsonObject)v).containsKey("requirements") && !((JsonObject)v).containsKey("memberships") &&
                            !((JsonObject)v).containsKey("gradeScale") && !((JsonObject)v).containsKey("cred") &&
                            !((JsonObject)v).containsKey("valid"))) raw = v;
                    else { ModelStore.require(v instanceof JsonObject, "Expected JSON object"); data = (JsonObject)v; }
                }
            } else if (kind.equals("unit") && q.getContentType() != null && q.getContentType().startsWith("text/plain")) {
                String body = q.getReader().lines().collect(java.util.stream.Collectors.joining("\n"));
                if (!body.isBlank()) try (JsonReader reader = Json.createReader(new StringReader(body))) { raw = reader.readValue(); }
            }
            json = data; rawRequirement = raw;
        }
        boolean has(String key) { return json.containsKey(key) || request.getParameter(key) != null; }
        String get(String key) {
            if (json.containsKey(key)) {
                JsonValue v = json.get(key);
                if (v == JsonValue.NULL) return null;
                if (v instanceof JsonString) return ((JsonString)v).getString();
                if (v instanceof JsonNumber || v == JsonValue.TRUE || v == JsonValue.FALSE) return v.toString();
                throw new ModelStore.Problem(400, "Expected scalar: " + key);
            }
            return request.getParameter(key);
        }
        String required(String key) { return ModelStore.text(get(key), key); }
        int number(String key, int min) { return ModelStore.integer(get(key), key, min); }
        int number(String key, int min, int fallback) { return has(key) ? number(key, min) : fallback; }
        boolean bool(String key, boolean fallback) {
            if (!has(key)) return fallback;
            String value = get(key); ModelStore.require("true".equals(value) || "false".equals(value), "Invalid boolean: " + key);
            return Boolean.parseBoolean(value);
        }
    }

    private JsonValue change(ModelStore store, Input in, boolean edit) {
        var factory = ModelStore.FACTORY;
        if (kind.equals("faculty")) {
            String name = in.required("name"); store.uniqueId(name);
            Faculty f = factory.createFaculty(); f.setName(name); store.referenceId(f, name); store.root.getFaculty().add(f); return message("Faculty created");
        }
        Faculty f = store.faculty(in.required("faculty"));
        switch (kind) {
            case "career": {
                String name = in.required("name");
                if (f.getCareers().stream().anyMatch(c -> c.getName().equals(name))) throw new ModelStore.Problem(409, "Career already exists");
                Career c = factory.createCareer(); c.setName(name); f.getCareers().add(c); return message("Career created");
            }
            case "scale": return changeScale(store, f, in, edit);
            case "plan": {
                Career c = ModelStore.career(f, in.required("career")); int year = in.number("plan", 1);
                GradeScale scale = ModelStore.scale(f, in.required("gradeScale"));
                if (edit) { var p = ModelStore.plan(f, c.getName(), year); p.setGradescale(scale); return planJson(p); }
                if (c.getPlan().stream().anyMatch(p -> p.getYear() == year)) throw new ModelStore.Problem(409, "Plan already exists");
                int type = in.number("planType", 0); ModelStore.require(type <= 1, "planType must be 0 (credits) or 1 (subjects)");
                asignaturas.Plan p;
                if (type == 0) { CreditsPlan cp = factory.createCreditsPlan(); cp.setMinCredits(in.number("minCred", 0, 0)); p = cp; }
                else p = factory.createSubjectPlan();
                p.setYear(year); p.setValid(in.bool("valid", true)); p.setGradescale(scale); c.getPlan().add(p); return planJson(p);
            }
            case "subject": {
                var p = ModelStore.plan(f, in.required("career"), in.number("plan", 1));
                ModelStore.require(p instanceof CreditsPlan, "Subjects require a credit plan");
                int id = in.number("subjectID", 0); store.uniqueId(Integer.toString(id));
                Subject s = factory.createSubject(); s.setId(id); s.setName(in.required("subjectName"));
                s.setMinCredits(in.number("subjectCredits", 0, 0));
                int parent = in.has("Parent") ? ModelStore.integer(in.get("Parent"), "Parent", -1) : -1;
                Subject parentSubject = parent == -1 ? null : ModelStore.subject(f, parent);
                ModelStore.require(parentSubject == null || ModelStore.subjects(p).contains(parentSubject), "Parent must belong to the selected plan");
                JsonArray units = in.json.getJsonArray("curricularUnits");
                if (units != null) for (JsonValue v : units) {
                    ModelStore.require(v instanceof JsonString, "curricularUnits must contain IDs");
                    s.getSubjectCurricularUnit().add(ModelStore.unit(f, ((JsonString)v).getString()));
                }
                if (parentSubject == null) ((CreditsPlan)p).getGroupOfSubjects().add(s); else parentSubject.getGroupOfSubjects().add(s);
                return subjectJson(s);
            }
            case "unit": return changeUnit(store, f, in, edit);
            case "course": {
                CurricularUnit cu = ModelStore.unit(f, in.required("id")); int year = in.number("year", 1), edition = in.number("edition", 1, 1);
                if (cu.getCourse().stream().anyMatch(c -> c.getYear() == year && c.getEdition() == edition)) throw new ModelStore.Problem(409, "Course already exists");
                var c = factory.createCourse(); c.setYear(year); c.setEdition(edition); cu.getCourse().add(c); return courseJson(c);
            }
            case "evaluation": {
                CurricularUnit cu = ModelStore.unit(f, in.required("id"));
                String type = in.required("type"); Date date = Date.from(LocalDate.parse(in.required("date")).atStartOfDay(ZoneId.of("America/Montevideo")).toInstant());
                Evaluation evaluation; List<? extends Evaluation> existing;
                if (type.equals("ExamEvaluation")) { ExamEvaluation e = factory.createExamEvaluation(); evaluation = e; existing = cu.getExamEvaluation(); }
                else {
                    var c = ModelStore.course(cu, in.number("year", 1), in.number("edition", 1));
                    switch (type) {
                        case "CourseEvaluation": { CourseEvaluation e = factory.createCourseEvaluation(); evaluation = e; existing = c.getCourseEvaluation(); break; }
                        case "PartialEvaluation": { PartialEvaluation e = factory.createPartialEvaluation(); evaluation = e; existing = c.getPartialevaluation(); break; }
                        case "TutoringEvaluation": { TutoringEvaluation e = factory.createTutoringEvaluation(); evaluation = e; existing = c.getTutoringevaluation(); break; }
                        default: throw new ModelStore.Problem(400, "Unknown evaluation type");
                    }
                }
                if (existing.stream().anyMatch(e -> e.getDate().equals(date))) throw new ModelStore.Problem(409, "Evaluation already exists on this date");
                evaluation.setDate(date);
                if (evaluation instanceof ExamEvaluation) cu.getExamEvaluation().add((ExamEvaluation)evaluation);
                else {
                    var c = ModelStore.course(cu, in.number("year", 1), in.number("edition", 1));
                    if (evaluation instanceof CourseEvaluation) c.getCourseEvaluation().add((CourseEvaluation)evaluation);
                    else if (evaluation instanceof PartialEvaluation) c.getPartialevaluation().add((PartialEvaluation)evaluation);
                    else c.getTutoringevaluation().add((TutoringEvaluation)evaluation);
                }
                return message("Evaluation created");
            }
            default: throw new ModelStore.Problem(400, "Unknown operation");
        }
    }

    private JsonValue changeScale(ModelStore store, Faculty f, Input in, boolean edit) {
        String name = in.required("name"); GradeScale s;
        if (edit) s = ModelStore.scale(f, name);
        else { store.uniqueId(name); s = ModelStore.FACTORY.createGradeScale(); s.setName(name); store.referenceId(s, name); }
        String[] fields = {"examApprovalGrade", "courseApprovalGrade", "coursePartialApprovalGrade", "partialApprovalGrade", "tutoringApprovalGrade"};
        float[] values = {s.getExamApprovalGrade(), s.getCourseApprovalGrade(), s.getCoursePartialApprovalGrade(), s.getPartialApprovalGrade(), s.getTutoringApprovalGrade()};
        for (int i = 0; i < fields.length; i++) if (in.has(fields[i])) {
            try { values[i] = Float.parseFloat(in.required(fields[i])); }
            catch (NumberFormatException e) { throw new ModelStore.Problem(400, "Invalid threshold: " + fields[i]); }
            ModelStore.require(Float.isFinite(values[i]), "Thresholds must be finite");
        }
        s.setExamApprovalGrade(values[0]); s.setCourseApprovalGrade(values[1]); s.setCoursePartialApprovalGrade(values[2]);
        s.setPartialApprovalGrade(values[3]); s.setTutoringApprovalGrade(values[4]);
        if (!edit) f.getGradescale().add(s);
        return scaleJson(f, s);
    }

    private JsonValue changeUnit(ModelStore store, Faculty f, Input in, boolean edit) {
        String id = in.required("id"); CurricularUnit cu;
        if (edit) cu = ModelStore.unit(f, id);
        else { store.uniqueId(id); cu = ModelStore.FACTORY.createCurricularUnit(); cu.setId(id); store.referenceId(cu, id); }
        // Resolve and validate every input before touching an existing unit or its memberships.
        String name = !edit || in.has("name") ? in.required("name") : cu.getName();
        int credits = in.number("cred", 0, edit ? cu.getCred() : 0);
        boolean valid = in.bool("valid", edit ? cu.isValid() : true);
        GradeScale scale = in.has("gradeScale") ? (in.get("gradeScale") == null ? null : ModelStore.scale(f, in.required("gradeScale"))) : cu.getGradescale();
        boolean replaceRequirement = in.json.containsKey("requirements") || in.rawRequirement != null;
        Requirement requirement = replaceRequirement ? RequirementJson.parse(in.json.containsKey("requirements") ? in.json.get("requirements") : in.rawRequirement, f) : cu.getRequirement();
        List<Subject> subjects = new ArrayList<>(); List<SubjectPlan> plans = new ArrayList<>();
        boolean replaceMemberships = in.json.containsKey("memberships");
        if (replaceMemberships) {
            JsonArray a = in.json.getJsonArray("memberships"); ModelStore.require(a != null, "memberships must be an array");
            for (JsonValue value : a) {
                JsonObject m = (JsonObject)value;
                var p = ModelStore.plan(f, m.getString("career"), m.getInt("plan"));
                if (p instanceof SubjectPlan) { ModelStore.require(!m.containsKey("subject"), "Subject plans do not have subjects"); plans.add((SubjectPlan)p); }
                else {
                    Subject s = ModelStore.subject(f, m.getInt("subject"));
                    ModelStore.require(ModelStore.subjects(p).contains(s), "Subject is not in selected plan"); subjects.add(s);
                }
            }
        }
        cu.setName(name); cu.setCred(credits); cu.setValid(valid); cu.setGradescale(scale);
        if (replaceRequirement) cu.setRequirement(requirement);
        if (!edit) f.getFacultyCU().add(cu);
        if (replaceMemberships) {
            for (Career c : f.getCareers()) for (asignaturas.Plan p : c.getPlan()) {
                if (p instanceof SubjectPlan) ((SubjectPlan)p).getCurricularUnit().remove(cu);
                for (Subject s : ModelStore.subjects(p)) s.getSubjectCurricularUnit().remove(cu);
            }
            for (Subject s : subjects) s.getSubjectCurricularUnit().add(cu);
            for (SubjectPlan p : plans) p.getCurricularUnit().add(cu);
        }
        return unitJson(f, cu);
    }

    private JsonValue read(ModelStore store, Input in) {
        if (kind.equals("faculty") && !in.has("faculty")) return ModelStore.strings(store.root.getFaculty().stream().map(Faculty::getName).toList());
        Faculty f = store.faculty(in.required("faculty"));
        switch (kind) {
            case "faculty": return Json.createObjectBuilder().add("Name", f.getName())
                .add("Careers", ModelStore.strings(f.getCareers().stream().map(Career::getName).toList()))
                .add("FacultyCU", ModelStore.strings(f.getFacultyCU().stream().map(CurricularUnit::getId).toList())).build();
            case "career": {
                Career c = ModelStore.career(f, in.required("career")); JsonArrayBuilder a = Json.createArrayBuilder(); c.getPlan().forEach(p -> a.add(p.getYear()));
                return Json.createObjectBuilder().add("Name", c.getName()).add("Plans", a).build();
            }
            case "plan": return planJson(ModelStore.plan(f, in.required("career"), in.number("plan", 1)));
            case "subject": {
                var p = ModelStore.plan(f, in.required("career"), in.number("plan", 1));
                List<Subject> subjects = ModelStore.subjects(p);
                if (in.has("subject") && !in.get("subject").isBlank()) {
                    Subject s = ModelStore.subject(f, in.number("subject", 0)); ModelStore.require(subjects.contains(s), "Subject is not in selected plan"); return subjectJson(s);
                }
                return ModelStore.strings(subjects.stream().map(s -> Integer.toString(s.getId())).toList());
            }
            case "unit": {
                if (in.has("curricularUnit") && !in.get("curricularUnit").isBlank()) return unitJson(f, ModelStore.unit(f, in.required("curricularUnit")));
                JsonArrayBuilder a = Json.createArrayBuilder();
                for (CurricularUnit cu : f.getFacultyCU()) {
                    if (Boolean.parseBoolean(in.get("withName"))) a.add(Json.createObjectBuilder().add("id", cu.getId()).add("name", cu.getName()));
                    else a.add(cu.getId());
                }
                return a.build();
            }
            case "course": { JsonArrayBuilder a = Json.createArrayBuilder(); ModelStore.unit(f, in.required("id")).getCourse().forEach(c -> a.add(courseJson(c))); return a.build(); }
            case "approved": {
                var plan = ModelStore.plan(f, in.required("career"), in.number("plan", 1));
                org.eclipse.emf.common.util.URI uri = store.resource.getURI().trimSegments(1).appendSegment("students.xmi");
                var resource = store.resource.getResourceSet().getResource(uri, true);
                var students = ((Estudiantes.Root)resource.getContents().get(0)).getStudent();
                Estudiantes.Student student = in.has("id") && !in.get("id").isBlank()
                    ? students.stream().filter(s -> s.getId().equals(in.get("id"))).findFirst().orElse(null)
                    : students.isEmpty() ? null : students.get(0);
                if (student == null) throw new ModelStore.Problem(404, "Student not found");
                JsonArrayBuilder a = Json.createArrayBuilder();
                for (Estudiantes.PlanInscription inscription : student.getStudentPlanInscription()) if (inscription.getPlan() == plan)
                    for (Estudiantes.StudentEvaluation e : inscription.getPlanStudentEvaluation()) if (Grading.passed(e, plan, false)) {
                        CurricularUnit cu = Grading.unit(e.getEvaluation());
                        if (cu != null && f.getFacultyCU().contains(cu)) a.add(Json.createObjectBuilder().add("id", cu.getId())
                            .add("type", e.getEvaluation().eClass().getName()).add("grade", e.getGrade()));
                    }
                return a.build();
            }
            case "scale": {
                if (in.has("name")) return scaleJson(f, ModelStore.scale(f, in.required("name")));
                JsonArrayBuilder a = Json.createArrayBuilder(); f.getGradescale().forEach(s -> a.add(scaleJson(f, s))); return a.build();
            }
            default: throw new ModelStore.Problem(400, "Unsupported query");
        }
    }

    static JsonObject message(String text) { return Json.createObjectBuilder().add("message", text).build(); }
    static JsonObject planJson(asignaturas.Plan p) {
        JsonObjectBuilder o = Json.createObjectBuilder().add("Year", p.getYear()).add("Career", p.getCareer_parent().getName())
            .add("Valid", p.isValid()).add("GradeScale", ModelStore.nullable(p.getGradescale() == null ? null : p.getGradescale().getName()));
        if (p instanceof CreditsPlan) return o.add("Type", "CreditsPlan").add("MinCredits", ((CreditsPlan)p).getMinCredits())
            .add("Subjects", ModelStore.strings(ModelStore.subjects(p).stream().map(s -> Integer.toString(s.getId())).toList())).build();
        return o.add("Type", "SubjectPlan").add("Curricular Units", ModelStore.strings(((SubjectPlan)p).getCurricularUnit().stream().map(CurricularUnit::getId).toList())).build();
    }
    static JsonObject subjectJson(Subject s) {
        return Json.createObjectBuilder().add("Id", Integer.toString(s.getId())).add("Name", s.getName()).add("MinCredits", s.getMinCredits())
            .add("Subjects", ModelStore.strings(s.getGroupOfSubjects().stream().map(c -> Integer.toString(c.getId())).toList()))
            .add("CurricularUnits", ModelStore.strings(s.getSubjectCurricularUnit().stream().map(CurricularUnit::getId).toList())).build();
    }
    static JsonArray dates(List<? extends Evaluation> evaluations) {
        return ModelStore.strings(evaluations.stream().map(e -> e.getDate().toString()).toList());
    }
    static JsonObject courseJson(asignaturas.Course c) {
        return Json.createObjectBuilder().add("Year", c.getYear()).add("Edition", c.getEdition()).add("CourseEvaluation", dates(c.getCourseEvaluation()))
            .add("PartialEvaluation", dates(c.getPartialevaluation())).add("TutoringEvaluation", dates(c.getTutoringevaluation())).build();
    }
    static JsonArray memberships(Faculty f, CurricularUnit cu) {
        JsonArrayBuilder a = Json.createArrayBuilder();
        for (Career c : f.getCareers()) for (asignaturas.Plan p : c.getPlan()) {
            if (p instanceof SubjectPlan && ((SubjectPlan)p).getCurricularUnit().contains(cu)) a.add(Json.createObjectBuilder().add("career", c.getName()).add("plan", p.getYear()));
            for (Subject s : ModelStore.subjects(p)) if (s.getSubjectCurricularUnit().contains(cu))
                a.add(Json.createObjectBuilder().add("career", c.getName()).add("plan", p.getYear()).add("subject", s.getId()));
        }
        return a.build();
    }
    static JsonObject unitJson(Faculty f, CurricularUnit cu) {
        JsonArrayBuilder courses = Json.createArrayBuilder(); cu.getCourse().forEach(c -> courses.add(courseJson(c)));
        JsonArrayBuilder requirements = Json.createArrayBuilder(); if (cu.getRequirement() != null) requirements.add(RequirementJson.write(cu.getRequirement()));
        return Json.createObjectBuilder().add("Id", cu.getId()).add("Name", cu.getName()).add("Cred", cu.getCred()).add("Valid", cu.isValid())
            .add("Requirement", requirements).add("ExamEvaluation", dates(cu.getExamEvaluation())).add("Course", courses)
            .add("GradeScale", ModelStore.nullable(cu.getGradescale() == null ? null : cu.getGradescale().getName())).add("Memberships", memberships(f, cu)).build();
    }
    static JsonObject scaleJson(Faculty f, GradeScale s) {
        JsonArrayBuilder plans = Json.createArrayBuilder(), units = Json.createArrayBuilder();
        for (Career c : f.getCareers()) for (asignaturas.Plan p : c.getPlan()) if (p.getGradescale() == s)
            plans.add(Json.createObjectBuilder().add("career", c.getName()).add("plan", p.getYear()));
        for (CurricularUnit cu : f.getFacultyCU()) if (cu.getGradescale() == s) units.add(cu.getId());
        return Json.createObjectBuilder().add("name", s.getName()).add("examApprovalGrade", s.getExamApprovalGrade())
            .add("courseApprovalGrade", s.getCourseApprovalGrade()).add("coursePartialApprovalGrade", s.getCoursePartialApprovalGrade())
            .add("partialApprovalGrade", s.getPartialApprovalGrade()).add("tutoringApprovalGrade", s.getTutoringApprovalGrade())
            .add("plans", plans).add("curricularUnits", units).build();
    }
}
