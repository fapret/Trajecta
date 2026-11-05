import java.io.*;
import java.lang.reflect.Proxy;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import jakarta.json.*;
import jakarta.servlet.http.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import asignaturas.*;
import Estudiantes.*;

/** Dependency-free integration tests against servlet boundaries and persisted XMI. */
public class AuthoringIntegrationTest {
    static Path base;
    static int assertions;
    static final Map<String, HttpServlet> SERVLETS = Map.ofEntries(
        Map.entry("/Faculty", new Facultad()), Map.entry("/Faculty/Carrera", new Carreras()),
        Map.entry("/Faculty/Carrera/Plan", new Plan()), Map.entry("/Faculty/Carrera/Plan/Subjects", new Subjects()),
        Map.entry("/Faculty/ucs", new UnidadCurricular()), Map.entry("/Faculty/ucs/course", new Course()),
        Map.entry("/Faculty/ucs/evaluation", new CreateEvaluation()), Map.entry("/Faculty/grade-scales", new GradeScales()),
        Map.entry("/Faculty/Carrera/Plan/evaluations", new Evaluations()), Map.entry("/log", new GetStudentsLog()),
        Map.entry("/Workspaces", new Workspaces()));
    record Reply(int status, String body) {
        JsonObject object() { try (JsonReader r = Json.createReader(new StringReader(body))) { return r.readObject(); } }
    }
    static void check(boolean ok, String message) { assertions++; if (!ok) throw new AssertionError(message); }
    static Reply call(String method, String path, Map<String,String> params, String body, String contentType) throws Exception {
        StringWriter output = new StringWriter(); int[] code = {200};
        HttpServletRequest q = (HttpServletRequest)Proxy.newProxyInstance(AuthoringIntegrationTest.class.getClassLoader(), new Class[]{HttpServletRequest.class}, (proxy, m, args) -> {
            switch (m.getName()) {
                case "getParameter": return params.get((String)args[0]);
                case "getMethod": return method;
                case "getProtocol": return "HTTP/1.1";
                case "getContentType": return contentType;
                case "getReader": return new BufferedReader(new StringReader(body == null ? "" : body));
                case "getInputStream": {
                    var bytes = new ByteArrayInputStream((body == null ? "" : body).getBytes(StandardCharsets.UTF_8));
                    return new jakarta.servlet.ServletInputStream() {
                        public int read() { return bytes.read(); }
                        public boolean isFinished() { return bytes.available() == 0; }
                        public boolean isReady() { return true; }
                        public void setReadListener(jakarta.servlet.ReadListener l) {}
                    };
                }
                default: return primitive(m.getReturnType());
            }
        });
        HttpServletResponse r = (HttpServletResponse)Proxy.newProxyInstance(AuthoringIntegrationTest.class.getClassLoader(), new Class[]{HttpServletResponse.class}, (proxy, m, args) -> {
            if (m.getName().equals("getWriter")) return new PrintWriter(output);
            if (m.getName().equals("setStatus") || m.getName().equals("sendError")) { code[0] = (int)args[0]; if (args.length > 1) output.write(String.valueOf(args[1])); }
            return primitive(m.getReturnType());
        });
        SERVLETS.get(path).service(q, r);
        return new Reply(code[0], output.toString());
    }
    static Object primitive(Class<?> type) {
        if (type == boolean.class) return false;
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        return null;
    }
    static Map<String,String> context(String... values) {
        Map<String,String> map = new HashMap<>(); map.put("uuid", "test"); map.put("faculty", "Engineering");
        for (int i = 0; i < values.length; i += 2) map.put(values[i], values[i+1]); return map;
    }
    static Reply request(int expected, String method, String path, Map<String,String> params, String body) throws Exception {
        Reply r = call(method, path, params, body, "application/json");
        check(r.status == expected, method + " " + path + " expected " + expected + " got " + r.status + ": " + r.body); return r;
    }
    static Reply post(String path, String json) throws Exception { return request(201, "POST", path, context(), json); }
    static void fixture(String id) throws Exception {
        Path directory = Files.createDirectories(base.resolve(id));
        ResourceSet rs = new ResourceSetImpl(); rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource model = rs.createResource(URI.createFileURI(directory.resolve("model.xmi").toString()));
        model.getContents().add(ModelStore.FACTORY.createRoot()); model.save(null);
        Resource students = rs.createResource(URI.createFileURI(directory.resolve("students.xmi").toString()));
        students.getContents().add(EstudiantesFactory.eINSTANCE.createRoot()); students.save(null);
    }
    static String memberships = "[{\"career\":\"Computing\",\"plan\":2026,\"subject\":101},{\"career\":\"Computing\",\"plan\":2027}]";
    static void tests() throws Exception {
        fixture("test"); fixture("other");
        post("/Faculty", "{\"name\":\"Engineering\"}");
        post("/Faculty", "{\"name\":\"Arts\"}");
        post("/Faculty/Carrera", "{\"name\":\"Computing\"}");
        post("/Faculty/grade-scales", "{\"name\":\"Standard\"}");
        post("/Faculty/grade-scales", "{\"name\":\"Strict\",\"examApprovalGrade\":5.5}");
        post("/Faculty/grade-scales", "{\"name\":\"Browser scale\",\"examApprovalGrade\":4.5}");
        request(201, "POST", "/Faculty/grade-scales", context("faculty", "Arts"), "{\"name\":\"Arts scale\"}");
        post("/Faculty/Carrera/Plan", "{\"career\":\"Computing\",\"plan\":2026,\"planType\":0,\"minCred\":90,\"gradeScale\":\"Standard\"}");
        post("/Faculty/Carrera/Plan", "{\"career\":\"Computing\",\"plan\":2027,\"planType\":1,\"gradeScale\":\"Standard\"}");
        post("/Faculty/Carrera/Plan/Subjects", "{\"career\":\"Computing\",\"plan\":2026,\"subjectID\":100,\"subjectName\":\"Area\"}");
        post("/Faculty/Carrera/Plan/Subjects", "{\"career\":\"Computing\",\"plan\":2026,\"subjectID\":101,\"subjectName\":\"Nested\",\"Parent\":100}");
        post("/Faculty/Carrera/Plan/Subjects", "{\"career\":\"Computing\",\"plan\":2026,\"subjectID\":102,\"subjectName\":\"Deep\",\"Parent\":101}");
        post("/Faculty/ucs", "{\"id\":\"A\",\"name\":\"Algebra \\\"á\\\"\",\"cred\":10,\"memberships\":" + memberships + "}");
        post("/Faculty/ucs", "{\"id\":\"B\",\"name\":\"Advanced\",\"cred\":20}");
        String requirement = "{\"SomeOf\":[2,{\"Coursed\":\"A\"},{\"NOT\":{\"RegisteredTo\":\"B\"}},{\"Exam\":\"A\"},{\"CreditsOnPlan\":\"2026,Computing,10\"},{\"CreditsOnSubject\":\"102,0\"}]}";
        request(200, "PUT", "/Faculty/ucs", context(), "{\"id\":\"B\",\"requirements\":" + requirement + ",\"memberships\":" + memberships + "}");
        JsonObject b = request(200, "GET", "/Faculty/ucs", context("curricularUnit", "B"), null).object();
        check(b.getJsonArray("Requirement").getJsonObject(0).toString().equals(requirement), "Nested requirement round trip");
        check(b.getJsonArray("Memberships").size() == 2, "Shared unit memberships");
        request(200,"PUT","/Faculty/ucs",context(),"{\"id\":\"B\",\"gradeScale\":\"Browser scale\"}");
        check(request(200,"GET","/Faculty/ucs",context("curricularUnit","B"),null).object().getString("GradeScale").equals("Browser scale"),"Scale names with spaces round trip");
        request(200,"PUT","/Faculty/ucs",context(),"{\"id\":\"B\",\"name\":\"Advanced\"}");
        check(request(200,"GET","/Faculty/ucs",context("curricularUnit","B"),null).object().getJsonArray("Requirement").size()==1,"Omitted requirements preserve existing expression");
        post("/Faculty/ucs/course", "{\"id\":\"A\",\"year\":2026}");
        post("/Faculty/ucs/course", "{\"id\":\"A\",\"year\":2026,\"edition\":2}");
        for (String type : List.of("ExamEvaluation", "CourseEvaluation", "PartialEvaluation", "TutoringEvaluation")) {
            post("/Faculty/ucs/evaluation", "{\"id\":\"A\",\"type\":\"" + type + "\",\"date\":\"2026-11-05\",\"year\":2026,\"edition\":1}");
        }
        var store = new ModelStore("test"); var f = store.faculty("Engineering"); var a = ModelStore.unit(f, "A");
        var p = ModelStore.plan(f, "Computing", 2026);
        check(a.isValid() && p.isValid(), "Valid defaults");
        check(a.getCourse().size() == 2 && a.getCourse().get(0).getTutoringevaluation().size() == 1, "All evaluations persisted");
        check(a.getExamEvaluation().get(0).getDate().toInstant().equals(LocalDate.of(2026,11,5).atStartOfDay(ZoneId.of("America/Montevideo")).toInstant()), "Evaluation date timezone");
        var students = store.resource.getResourceSet().getResource(URI.createFileURI(base.resolve("test/students.xmi").toString()), true);
        var root = (Estudiantes.Root)students.getContents().get(0); Student student = EstudiantesFactory.eINSTANCE.createStudent(); student.setId("student"); student.setName("Test"); root.getStudent().add(student);
        PlanInscription pi = EstudiantesFactory.eINSTANCE.createPlanInscription(); pi.setPlan(p); pi.setDate(new Date()); student.getStudentPlanInscription().add(pi);
        StudentEvaluation exam = EstudiantesFactory.eINSTANCE.createStudentEvaluation(); exam.setEvaluation(a.getExamEvaluation().get(0)); exam.setGrade(3); pi.getPlanStudentEvaluation().add(exam);
        StudentEvaluation course = EstudiantesFactory.eINSTANCE.createStudentEvaluation(); course.setEvaluation(a.getCourse().get(0).getCourseEvaluation().get(0)); course.setGrade(3); pi.getPlanStudentEvaluation().add(course);
        check(Grading.passed(exam, p, true), "Exact exam threshold");
        check(Grading.passed(course, p, false) && !Grading.passed(course, p, true), "Course eligibility differs from full approval");
        course.setGrade(6); check(Grading.completesUnit(course, p), "Exact full course threshold");
        students.save(null);
        request(200, "PUT", "/Faculty/grade-scales", context(), "{\"name\":\"Standard\",\"examApprovalGrade\":3.5,\"coursePartialApprovalGrade\":4.5,\"courseApprovalGrade\":6.5}");
        request(200, "PUT", "/Faculty/ucs", context(), "{\"id\":\"A\",\"name\":\"Updated\",\"cred\":11}");
        store = new ModelStore("test"); f = store.faculty("Engineering"); a = ModelStore.unit(f,"A"); p = ModelStore.plan(f,"Computing",2026);
        check(a.getCourse().size() == 2 && a.getExamEvaluation().size() == 1, "Editing preserves contained objects");
        var reloadedStudents = store.resource.getResourceSet().getResource(URI.createFileURI(base.resolve("test/students.xmi").toString()), true);
        student = ((Estudiantes.Root)reloadedStudents.getContents().get(0)).getStudent().get(0); pi = student.getStudentPlanInscription().get(0);
        exam = pi.getPlanStudentEvaluation().get(0); course = pi.getPlanStudentEvaluation().get(1);
        check(exam.getEvaluation() == a.getExamEvaluation().get(0) && exam.getGrade() == 3, "Student references and grades survive edits");
        check(!Grading.passed(exam,p,true) && !Grading.passed(course,p,true), "Scale edits affect recorded grade checks");
        a.setGradescale(ModelStore.scale(f,"Strict")); exam.setGrade(5); check(!Grading.passed(exam,p,true), "Unit override takes precedence");
        exam.setGrade(6); check(Grading.passed(exam,p,true), "Decimal threshold boundary");
        a.setGradescale(null); check(Grading.passed(exam,p,true), "Clearing override inherits plan");
        p.setGradescale(null); exam.setGrade(3); check(Grading.passed(exam,p,true), "Legacy fallback");
        // Partial and tutoring thresholds are independent, without granting unit credit.
        p.setGradescale(ModelStore.scale(f,"Standard"));
        var partial = EstudiantesFactory.eINSTANCE.createStudentEvaluation(); partial.setEvaluation(a.getCourse().get(0).getPartialevaluation().get(0)); partial.setGrade(59);
        check(!Grading.passed(partial,p,false), "Partial below threshold"); partial.setGrade(60); check(Grading.passed(partial,p,false) && !Grading.completesUnit(partial,p), "Partial passes without full credit");
        var tutoring = EstudiantesFactory.eINSTANCE.createStudentEvaluation(); tutoring.setEvaluation(a.getCourse().get(0).getTutoringevaluation().get(0)); tutoring.setGrade(0);
        check(Grading.passed(tutoring,p,false) && !Grading.completesUnit(tutoring,p), "Tutoring threshold");
        pi.getPlanStudentEvaluation().add(partial); pi.getPlanStudentEvaluation().add(tutoring); reloadedStudents.save(null);
        // Consultations/logs read the edited scale from disk rather than cached thresholds.
        Reply approved = request(200,"GET","/Faculty/Carrera/Plan/evaluations",context("career","Computing","plan","2026","id","student"),null);
        check(approved.body.contains("CourseEvaluation") && !approved.body.contains("ExamEvaluation"), "Approved query uses changed thresholds and course eligibility");
        check(approved.body.contains("PartialEvaluation") && approved.body.contains("TutoringEvaluation"), "Approved query supports partial and tutoring thresholds");
        Reply log = request(200,"POST","/log",context(),null);
        check(log.body.contains("Evaluation - Exam") && !log.body.contains(",3,11\n"), "Log does not award credit below edited threshold");
        check(log.body.contains("Evaluation - Tutoring"), "Log includes tutoring evaluations");
        request(200,"PUT","/Faculty/grade-scales",context(),"{\"name\":\"Standard\",\"partialApprovalGrade\":60.5,\"tutoringApprovalGrade\":0.5}");
        approved=request(200,"GET","/Faculty/Carrera/Plan/evaluations",context("career","Computing","plan","2026","id","student"),null);
        check(!approved.body.contains("PartialEvaluation") && !approved.body.contains("TutoringEvaluation"), "Scale edits affect partial and tutoring queries");
        request(200,"PUT","/Faculty/ucs",context(),"{\"id\":\"A\",\"gradeScale\":\"Strict\"}");
        request(200,"PUT","/Faculty/ucs",context(),"{\"id\":\"A\",\"gradeScale\":null}");
        request(200,"PUT","/Faculty/Carrera/Plan",context(),"{\"career\":\"Computing\",\"plan\":2027,\"gradeScale\":\"Strict\"}");
        // Every rejected request must leave the model byte-for-byte unchanged.
        byte[] before = Files.readAllBytes(base.resolve("test/model.xmi"));
        request(409,"POST","/Faculty/ucs",context(),"{\"id\":\"A\",\"name\":\"Duplicate\"}");
        request(409,"POST","/Faculty/grade-scales",context("faculty","Arts"),"{\"name\":\"Standard\"}");
        request(409,"POST","/Faculty/grade-scales",context(),"{\"name\":\"Browser scale\"}");
        request(409,"POST","/Faculty/Carrera/Plan",context(),"{\"career\":\"Computing\",\"plan\":2026,\"planType\":0,\"gradeScale\":\"Standard\"}");
        request(409,"POST","/Faculty/ucs/course",context(),"{\"id\":\"A\",\"year\":2026}");
        request(409,"POST","/Faculty/ucs/evaluation",context(),"{\"id\":\"A\",\"type\":\"ExamEvaluation\",\"date\":\"2026-11-05\"}");
        request(404,"PUT","/Faculty/ucs",context(),"{\"id\":\"A\",\"name\":\"Should not save\",\"requirements\":{\"Exam\":\"missing\"}}");
        request(404,"PUT","/Faculty/ucs",context(),"{\"id\":\"A\",\"gradeScale\":\"Arts scale\"}");
        request(400,"PUT","/Faculty/ucs",context(),"{\"id\":\"A\",\"requirements\":{\"SomeOf\":[4,{\"Exam\":\"B\"}]}}");
        request(400,"POST","/Faculty/ucs/course",context(),"{\"id\":\"A\",\"year\":\"bad\"}");
        request(400,"POST","/Faculty/ucs/evaluation",context(),"{\"id\":\"A\",\"type\":\"ExamEvaluation\",\"date\":\"2026-02-30\"}");
        request(400,"PUT","/Faculty/grade-scales",context(),"{\"name\":\"Standard\",\"examApprovalGrade\":\"NaN\"}");
        request(400,"PUT","/Faculty/ucs",context(),"{\"id\":\"A\",\"memberships\":[{}]}");
        request(404,"POST","/Faculty/ucs",context("faculty","missing"),"{\"id\":\"X\",\"name\":\"X\"}");
        request(404,"GET","/Faculty",context("uuid","missing"),null);
        request(400,"GET","/Faculty",context("uuid","../test"),null);
        request(409,"POST","/Workspaces",context(),null);
        request(400,"POST","/Workspaces",context("uuid","../escape"),null);
        check(Arrays.equals(before,Files.readAllBytes(base.resolve("test/model.xmi"))),"Rejected requests leave persistence unchanged");
        check(request(200,"GET","/Faculty",Map.of("uuid","other"),null).body.equals("[]"),"Workspace isolation");
        // Legacy parameter and raw requirement request formats remain supported.
        request(201,"POST","/Faculty/ucs",context("id","Legacy","name","Legacy","cred","0"),"{\"Exam\":\"A\"}");
        request(200,"PUT","/Faculty/ucs",context(),"{\"id\":\"B\",\"requirements\":null,\"memberships\":[]}");
        b=request(200,"GET","/Faculty/ucs",context("curricularUnit","B"),null).object();
        check(b.getJsonArray("Requirement").isEmpty() && b.getJsonArray("Memberships").isEmpty(),"Explicit clearing");
        System.out.println("PASS: " + assertions + " authoring / XMI / grading assertions");
    }
    public static void main(String[] args) throws Exception {
        base = Files.createTempDirectory(Paths.get("build").toAbsolutePath(),"authoring-test-");
        System.setProperty("trajecta.workspaces",base.toString()); tests();
        if (args.length > 0 && args[0].equals("--serve")) serve();
    }
    // Optional local-only HTTP adapter for browser smoke tests; not part of the WAR.
    static void serve() throws Exception {
        var server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress("127.0.0.1",8080),0);
        server.createContext("/curricula_microservice", exchange -> {
            try {
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin","*");
                exchange.getResponseHeaders().set("Access-Control-Allow-Methods","GET,POST,PUT,OPTIONS");
                exchange.getResponseHeaders().set("Access-Control-Allow-Headers","Content-Type");
                if (exchange.getRequestMethod().equals("OPTIONS")) { exchange.sendResponseHeaders(204,-1); exchange.close(); return; }
                String path=exchange.getRequestURI().getPath().substring("/curricula_microservice".length());
                Map<String,String> params=new HashMap<>(); String query=exchange.getRequestURI().getRawQuery();
                if(query!=null) for(String pair:query.split("&")) { String[] v=pair.split("=",2); params.put(URLDecoder.decode(v[0],StandardCharsets.UTF_8),URLDecoder.decode(v.length>1?v[1]:"",StandardCharsets.UTF_8)); }
                Reply reply;
                if(path.equals("/Workspaces") && exchange.getRequestMethod().equals("GET")) reply=new Reply(200,"[\"test\",\"other\"]");
                else reply=call(exchange.getRequestMethod(),path,params,new String(exchange.getRequestBody().readAllBytes(),StandardCharsets.UTF_8),exchange.getRequestHeaders().getFirst("Content-Type"));
                byte[] bytes=reply.body.getBytes(StandardCharsets.UTF_8); exchange.getResponseHeaders().set("Content-Type","application/json; charset=UTF-8");
                exchange.sendResponseHeaders(reply.status,bytes.length); exchange.getResponseBody().write(bytes); exchange.close();
            } catch(Exception e) { e.printStackTrace(); exchange.sendResponseHeaders(500,-1); exchange.close(); }
        });
        server.start(); System.out.println("Browser fixture API listening on 127.0.0.1:8080; workspace=test ("+base+")");
    }
}
