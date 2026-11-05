import jakarta.servlet.annotation.MultipartConfig;

/** Workspace-scoped evaluation endpoint. */
@MultipartConfig
public class CreateEvaluation extends ModelAuthoring {
    private static final long serialVersionUID = 1L;
    public CreateEvaluation() { super("evaluation"); }
}
