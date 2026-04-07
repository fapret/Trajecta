import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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

@WebFilter("/*")
public class WorkspaceAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String servletPath = httpRequest.getServletPath();
        if ("/Workspaces".equals(servletPath) || "/Workspaces/Unlock".equals(servletPath)) {
            chain.doFilter(request, response);
            return;
        }

        String uuid = httpRequest.getParameter("uuid");
        if (uuid == null || uuid.isBlank()) {
            chain.doFilter(request, response);
            return;
        }

        WorkspaceElem workspace = getWorkspace(uuid);
        if (workspace == null) {
            chain.doFilter(request, response);
            return;
        }

        String storedPasswordHash = workspace.getPassword();
        if (storedPasswordHash == null || storedPasswordHash.isBlank()) {
            chain.doFilter(request, response);
            return;
        }

        String authorization = httpRequest.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Bearer token");
            return;
        }

        String token = authorization.substring("Bearer ".length()).trim();
        if (!WorkspaceTokenUtil.isTokenValidForWorkspace(token, uuid)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }

        chain.doFilter(request, response);
    }

    private WorkspaceElem getWorkspace(String uuid) {
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

        Resource workspaceResource = resourceSet.getResource(workspaceURI, true);
        RootWorkspaces root = (RootWorkspaces) workspaceResource.getContents().get(0);
        for (WorkspaceElem workspaceElem : root.getWorkspace()) {
            if (uuid.equals(workspaceElem.getID())) {
                return workspaceElem;
            }
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
