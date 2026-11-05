import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import jakarta.json.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import asignaturas.*;

/** Workspace-scoped model access. Callers serialize read/modify/save operations. */
public final class ModelStore {
    public static final Object LOCK = new Object();
    public static final AsignaturasFactory FACTORY = AsignaturasFactory.eINSTANCE;
    public final Resource resource;
    public final asignaturas.Root root;

    public static class Problem extends RuntimeException {
        public final int status;
        public Problem(int status, String message) { super(message); this.status = status; }
    }
    public static void require(boolean ok, String message) {
        if (!ok) throw new Problem(400, message);
    }
    public static String text(String value, String field) {
        require(value != null && !value.isBlank(), "Missing " + field);
        return value.trim();
    }
    public static int integer(String value, String field, int minimum) {
        try {
            int n = Integer.parseInt(text(value, field));
            require(n >= minimum, field + " must be >= " + minimum);
            return n;
        } catch (NumberFormatException e) { throw new Problem(400, "Invalid integer: " + field); }
    }
    public static Path workspacePath(String uuid) throws IOException {
        uuid = text(uuid, "uuid");
        require(!uuid.contains("/") && !uuid.contains("\\") && !uuid.contains(":"), "Invalid workspace ID");
        Path base = Utils.getBasePath().toRealPath();
        Path dir = base.resolve(uuid).normalize();
        require(!dir.equals(base) && dir.startsWith(base), "Invalid workspace path");
        if (Files.exists(dir)) require(dir.toRealPath().startsWith(base), "Invalid workspace path");
        return dir;
    }
    public static Path workspace(String uuid) throws IOException {
        Path dir = workspacePath(uuid);
        if (!Files.isDirectory(dir)) throw new Problem(404, "Workspace not found");
        return dir;
    }
    public ModelStore(String uuid) throws IOException {
        Path file = workspace(uuid).resolve("model.xmi");
        if (!Files.isRegularFile(file)) throw new Problem(404, "Workspace model not found");
        require(file.toRealPath().startsWith(workspace(uuid).toRealPath()), "Invalid model path");
        ResourceSet rs = new ResourceSetImpl();
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        rs.getPackageRegistry().put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        rs.getPackageRegistry().put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
        resource = rs.getResource(URI.createFileURI(file.toString()), true);
        root = (asignaturas.Root) resource.getContents().get(0);
    }
    public void save() throws IOException {
        // Save to a sibling then replace, so a failed serialization cannot truncate model.xmi.
        Path file = Paths.get(resource.getURI().toFileString());
        Path tmp = Files.createTempFile(file.getParent(), ".model-", ".xmi");
        try {
            try (var out = Files.newOutputStream(tmp)) {
                resource.save(out, Map.of(XMLResource.OPTION_SCHEMA_LOCATION, true));
            }
            try { Files.move(tmp, file, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING); }
            catch (AtomicMoveNotSupportedException e) { Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING); }
        } finally { Files.deleteIfExists(tmp); }
    }
    public Faculty faculty(String name) {
        text(name, "faculty");
        return root.getFaculty().stream().filter(f -> f.getName().equals(name)).findFirst()
            .orElseThrow(() -> new Problem(404, "Faculty not found"));
    }
    public static Career career(Faculty f, String name) {
        text(name, "career");
        return f.getCareers().stream().filter(c -> c.getName().equals(name)).findFirst()
            .orElseThrow(() -> new Problem(404, "Career not found"));
    }
    public static asignaturas.Plan plan(Faculty f, String career, int year) {
        return career(f, career).getPlan().stream().filter(p -> p.getYear() == year).findFirst()
            .orElseThrow(() -> new Problem(404, "Plan not found"));
    }
    public static List<Subject> subjects(asignaturas.Plan plan) {
        List<Subject> out = new ArrayList<>();
        if (plan instanceof CreditsPlan) for (Subject s : ((CreditsPlan) plan).getGroupOfSubjects()) collect(s, out);
        return out;
    }
    private static void collect(Subject s, List<Subject> out) {
        out.add(s);
        for (Subject child : s.getGroupOfSubjects()) collect(child, out);
    }
    public static List<Subject> subjects(Faculty f) {
        List<Subject> out = new ArrayList<>();
        for (Career c : f.getCareers()) for (asignaturas.Plan p : c.getPlan()) out.addAll(subjects(p));
        return out;
    }
    public static Subject subject(Faculty f, int id) {
        return subjects(f).stream().filter(s -> s.getId() == id).findFirst()
            .orElseThrow(() -> new Problem(404, "Subject not found: " + id));
    }
    public static CurricularUnit unit(Faculty f, String id) {
        text(id, "id");
        return f.getFacultyCU().stream().filter(cu -> cu.getId().equals(id)).findFirst()
            .orElseThrow(() -> new Problem(404, "Curricular unit not found: " + id));
    }
    public static asignaturas.Course course(CurricularUnit cu, int year, int edition) {
        return cu.getCourse().stream().filter(c -> c.getYear() == year && c.getEdition() == edition).findFirst()
            .orElseThrow(() -> new Problem(404, "Course not found"));
    }
    public static GradeScale scale(Faculty f, String name) {
        text(name, "gradeScale");
        return f.getGradescale().stream().filter(s -> s.getName().equals(name)).findFirst()
            .orElseThrow(() -> new Problem(404, "Grade scale not found in faculty"));
    }
    public void uniqueId(String id) {
        if (resource.getEObject(id) != null) throw new Problem(409, "Model ID already exists: " + id);
    }
    public void referenceId(org.eclipse.emf.ecore.EObject object, String businessId) {
        // XMI IDREF attributes are whitespace-separated. Preserve human-facing IDs,
        // but give new objects with URI delimiters or spaces a safe serialization ID.
        if (businessId.chars().anyMatch(Character::isWhitespace) || businessId.matches(".*[/#?%].*"))
            ((XMLResource)resource).setID(object, "_" + UUID.randomUUID().toString());
    }
    public static JsonArray strings(Collection<String> values) {
        JsonArrayBuilder a = Json.createArrayBuilder(); values.forEach(a::add); return a.build();
    }
    public static JsonValue nullable(String value) { return value == null ? JsonValue.NULL : Json.createValue(value); }
}
