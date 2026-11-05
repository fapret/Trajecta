import jakarta.servlet.annotation.MultipartConfig;

/** Workspace-scoped scale endpoint. */
@MultipartConfig
public class GradeScales extends ModelAuthoring {
    private static final long serialVersionUID = 1L;
    public GradeScales() { super("scale"); }
}
