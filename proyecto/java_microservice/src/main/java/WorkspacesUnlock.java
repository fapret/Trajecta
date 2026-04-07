import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.EstudiantesPackage;
import Workspace.RootWorkspaces;
import Workspace.WorkspaceElem;
import Workspace.WorkspacePackage;
import asignaturas.AsignaturasPackage;

@WebServlet("/Workspaces/Unlock")
public class WorkspacesUnlock extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static ResourceSet createResourceSet() {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(WorkspacePackage.eNS_URI, WorkspacePackage.eINSTANCE);
        return resourceSet;
    }

    private static WorkspaceElem findWorkspaceByUuid(RootWorkspaces root, String uuid) {
        if (root == null || uuid == null) {
            return null;
        }
        for (WorkspaceElem workspaceElem : root.getWorkspace()) {
            if (uuid.equals(workspaceElem.getID())) {
                return workspaceElem;
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String password = request.getParameter("password");
        if ((uuid == null || uuid.isBlank()) || (password == null || password.isBlank())) {
            String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            if (uuid == null || uuid.isBlank()) {
                uuid = Workspaces.extractJsonField(body, "uuid");
            }
            if (password == null || password.isBlank()) {
                password = Workspaces.extractJsonField(body, "password");
            }
        }

        if (uuid == null || uuid.isBlank() || password == null || password.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "uuid and password are required");
            return;
        }

        Path baseDir = Utils.getBasePath();
        ResourceSet resourceSet = createResourceSet();
        URI workspaceURI = URI.createFileURI(baseDir.resolve("workspaces.xmi").toString());
        File workspaceFile = new File(workspaceURI.toFileString());
        if (!workspaceFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Workspace not found");
            return;
        }

        Resource workspaceResource = resourceSet.getResource(workspaceURI, true);
        RootWorkspaces root = (RootWorkspaces) workspaceResource.getContents().get(0);
        WorkspaceElem workspace = findWorkspaceByUuid(root, uuid);

        if (workspace == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Workspace not found");
            return;
        }

        String storedPassword = workspace.getPassword();
        if (storedPassword == null || storedPassword.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Workspace is public and does not need unlock token");
            return;
        }

        try {
            if (!Workspaces.verifyPassword(password, storedPassword)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid password");
                return;
            }

            long issuedAt = java.time.Instant.now().getEpochSecond();
            long expiresAt = issuedAt + 3600;
            String token = WorkspaceTokenUtil.issueToken(uuid);
            response.setContentType("application/json");

            Map<String, String> payload = new HashMap<>();
            payload.put("workspace", uuid);
            payload.put("token", token);
            payload.put("iat", String.valueOf(issuedAt));
            payload.put("exp", String.valueOf(expiresAt));

            response.getWriter().write("{\"workspace\":\"" + payload.get("workspace") + "\","
                    + "\"token\":\"" + payload.get("token") + "\","
                    + "\"iat\":" + payload.get("iat") + ","
                    + "\"exp\":" + payload.get("exp") + "}");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to verify password");
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to issue token");
        }
    }
}
