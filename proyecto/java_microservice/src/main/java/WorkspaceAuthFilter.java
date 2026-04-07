import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Workspace.RootWorkspaces;
import Workspace.WorkspaceElem;
import Workspace.WorkspacePackage;
import asignaturas.AsignaturasPackage;
import Estudiantes.EstudiantesPackage;

public class WorkspaceAuthFilter implements Filter {

    private static final String JWT_SECRET_ENV = "WORKSPACE_JWT_SECRET";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isExemptEndpoint(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }

        String workspaceId = readWorkspaceId(httpRequest);
        if (workspaceId == null || workspaceId.isBlank()) {
            chain.doFilter(request, response);
            return;
        }

        WorkspaceElem workspace = loadWorkspace(workspaceId);
        if (workspace == null || workspace.getPassword() == null || workspace.getPassword().isBlank()) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            writeJsonError(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "Missing bearer token");
            return;
        }

        String token = authHeader.substring("Bearer ".length()).trim();
        JwtClaims claims;
        try {
            claims = validateToken(token);
        } catch (SecurityException e) {
            writeJsonError(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        if (!workspaceId.equals(claims.workspace)) {
            writeJsonError(httpResponse, HttpServletResponse.SC_FORBIDDEN, "Token workspace does not match requested uuid");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean isExemptEndpoint(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return "POST".equalsIgnoreCase(request.getMethod())
                && ("/Workspaces".equals(path) || "/Workspaces/Unlock".equals(path));
    }

    private String readWorkspaceId(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        if (uuid != null && !uuid.isBlank()) {
            return uuid;
        }
        return null;
    }

    private WorkspaceElem loadWorkspace(String workspaceId) {
        Path baseDir = Utils.getBasePath();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(WorkspacePackage.eNS_URI, WorkspacePackage.eINSTANCE);

        URI workspaceURI = URI.createFileURI(baseDir.resolve("workspaces.xmi").toString());
        File workspaceFile = new File(workspaceURI.toFileString());
        if (!workspaceFile.exists()) {
            return null;
        }

        Resource resource = resourceSet.getResource(workspaceURI, true);
        RootWorkspaces root = (RootWorkspaces) resource.getContents().get(0);
        for (WorkspaceElem workspace : root.getWorkspace()) {
            if (workspaceId.equals(workspace.getID())) {
                return workspace;
            }
        }
        return null;
    }

    private JwtClaims validateToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new SecurityException("Invalid token format");
        }

        String signingInput = parts[0] + "." + parts[1];
        String expectedSig = hmacSha256Base64Url(signingInput, getJwtSecret());
        if (!constantTimeEquals(expectedSig, parts[2])) {
            throw new SecurityException("Invalid token signature");
        }

        String payloadJson;
        try {
            payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            throw new SecurityException("Invalid token payload encoding");
        }

        JsonObject payload;
        try (JsonReader reader = Json.createReader(new StringReader(payloadJson))) {
            payload = reader.readObject();
        } catch (RuntimeException e) {
            throw new SecurityException("Invalid token payload");
        }

        if (payload.containsKey("exp") && !payload.isNull("exp")) {
            long now = Instant.now().getEpochSecond();
            long exp = payload.getJsonNumber("exp").longValue();
            if (exp < now) {
                throw new SecurityException("Token expired");
            }
        }

        String workspace = payload.getString("workspace", null);
        if (workspace == null || workspace.isBlank()) {
            workspace = payload.getString("uuid", null);
        }
        if (workspace == null || workspace.isBlank()) {
            throw new SecurityException("Token missing workspace claim");
        }

        return new JwtClaims(workspace);
    }

    private String getJwtSecret() {
        String secret = System.getenv(JWT_SECRET_ENV);
        if (secret == null || secret.isBlank()) {
            throw new SecurityException("Server token secret is not configured");
        }
        return secret;
    }

    private String hmacSha256Base64Url(String value, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signature = mac.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
        } catch (Exception e) {
            throw new SecurityException("Unable to validate token signature");
        }
    }

    private boolean constantTimeEquals(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < left.length(); i++) {
            result |= left.charAt(i) ^ right.charAt(i);
        }
        return result == 0;
    }

    private void writeJsonError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        String safeMessage = message.replace("\"", "\\\"");
        response.getWriter().write("{\"error\":\"" + safeMessage + "\"}");
    }

    private static final class JwtClaims {
        private final String workspace;

        private JwtClaims(String workspace) {
            this.workspace = workspace;
        }
    }
}
