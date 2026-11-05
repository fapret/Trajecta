import jakarta.json.*;
import asignaturas.*;

/** Codec for the requirement format already exposed by /Faculty/ucs. */
public final class RequirementJson {
    public static Requirement parse(JsonValue value, Faculty faculty) { return parse(value, faculty, 0); }
    private static Requirement parse(JsonValue value, Faculty f, int depth) {
        if (value == null || value == JsonValue.NULL) return null;
        ModelStore.require(depth < 64, "Requirements are too deeply nested");
        ModelStore.require(value instanceof JsonObject && ((JsonObject)value).size() == 1,
            "A requirement must contain exactly one operator");
        JsonObject object = (JsonObject)value;
        String key = object.keySet().iterator().next();
        JsonValue v = object.get(key);
        AsignaturasFactory factory = ModelStore.FACTORY;
        switch (key) {
            case "NOT": {
                NOT r = factory.createNOT(); Requirement child = parse(v, f, depth + 1);
                ModelStore.require(child != null, "NOT requires a requirement"); r.setRequirement(child); return r;
            }
            case "Coursed": { Coursed r = factory.createCoursed(); r.setCurricularUnit(ModelStore.unit(f, string(v))); return r; }
            case "Exam": { Exam r = factory.createExam(); r.setCurricularUnit(ModelStore.unit(f, string(v))); return r; }
            case "RegisteredTo": { RegisteredTo r = factory.createRegisteredTo(); r.setCurricularUnit(ModelStore.unit(f, string(v))); return r; }
            case "CreditsOnPlan": {
                String[] parts = string(v).split(",", -1);
                ModelStore.require(parts.length == 3, "CreditsOnPlan expects year,career,credits");
                asignaturas.Plan p = ModelStore.plan(f, parts[1].trim(), ModelStore.integer(parts[0].trim(), "plan year", 1));
                ModelStore.require(p instanceof CreditsPlan, "CreditsOnPlan requires a credit plan");
                CreditsOnPlan r = factory.createCreditsOnPlan(); r.setCreditsPlan((CreditsPlan)p);
                r.setCred(ModelStore.integer(parts[2].trim(), "credits", 0)); return r;
            }
            case "CreditsOnSubject": {
                String[] parts = string(v).split(",", -1);
                ModelStore.require(parts.length == 2, "CreditsOnSubject expects subjectID,credits");
                CreditsOnSubject r = factory.createCreditsOnSubject();
                r.setGroupOfSubjects(ModelStore.subject(f, ModelStore.integer(parts[0].trim(), "subjectID", 0)));
                r.setCred(ModelStore.integer(parts[1].trim(), "credits", 0)); return r;
            }
            case "SomeOf": {
                ModelStore.require(v instanceof JsonArray, "SomeOf expects an array"); JsonArray a = (JsonArray)v;
                ModelStore.require(a.size() >= 2 && a.get(0) instanceof JsonNumber, "SomeOf needs N and requirements");
                int n = ((JsonNumber)a.get(0)).intValueExact();
                ModelStore.require(n >= 1 && n < a.size(), "SomeOf N must be between 1 and the number of requirements");
                SomeOf r = factory.createSomeOf(); r.setN(n);
                for (int i = 1; i < a.size(); i++) {
                    Requirement child = parse(a.get(i), f, depth + 1);
                    ModelStore.require(child != null, "SomeOf cannot contain null"); r.getRequirement().add(child);
                }
                return r;
            }
            default: throw new ModelStore.Problem(400, "Unknown requirement operator: " + key);
        }
    }
    private static String string(JsonValue v) {
        ModelStore.require(v instanceof JsonString, "Requirement reference must be a string"); return ((JsonString)v).getString();
    }
    public static JsonValue write(Requirement r) {
        if (r == null) return JsonValue.NULL;
        JsonObjectBuilder o = Json.createObjectBuilder();
        if (r instanceof NOT) return o.add("NOT", write(((NOT)r).getRequirement())).build();
        if (r instanceof Coursed) return o.add("Coursed", ((Coursed)r).getCurricularUnit().getId()).build();
        if (r instanceof Exam) return o.add("Exam", ((Exam)r).getCurricularUnit().getId()).build();
        if (r instanceof RegisteredTo) return o.add("RegisteredTo", ((RegisteredTo)r).getCurricularUnit().getId()).build();
        if (r instanceof SomeOf) {
            SomeOf s = (SomeOf)r; JsonArrayBuilder a = Json.createArrayBuilder().add(s.getN());
            s.getRequirement().forEach(child -> a.add(write(child))); return o.add("SomeOf", a).build();
        }
        if (r instanceof CreditsOnPlan) {
            CreditsOnPlan c = (CreditsOnPlan)r;
            return o.add("CreditsOnPlan", c.getCreditsPlan().getYear() + "," + c.getCreditsPlan().getCareer_parent().getName() + "," + c.getCred()).build();
        }
        CreditsOnSubject c = (CreditsOnSubject)r;
        return o.add("CreditsOnSubject", c.getGroupOfSubjects().getId() + "," + c.getCred()).build();
    }
}
