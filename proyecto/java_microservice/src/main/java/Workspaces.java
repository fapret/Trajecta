/*
    Trajecta is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be. And helps academic managers do different researches.
    Copyright (C) 2025  Santiago Nicolás Díaz Conde

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

    Santiago Nicolás Díaz Conde Email: sndc.33@gmail.com and contact@fapret.com
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.EstudiantesPackage;
import Workspace.RootWorkspaces;
import Workspace.WorkspacePackage;
import asignaturas.AsignaturasPackage;

/**
 * Servlet implementation class Workspaces
 */
@WebServlet("/Workspaces")
@MultipartConfig
public class Workspaces extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Workspaces() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Path baseDir = Utils.getBasePath();
		
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(WorkspacePackage.eNS_URI, WorkspacePackage.eINSTANCE);
        
        URI workspaceURI = URI.createFileURI(baseDir.resolve("workspaces.xmi").toString());
        Resource resource = resourceSet.getResource(workspaceURI, true);
        Workspace.RootWorkspaces rootElement = (Workspace.RootWorkspaces) resource.getContents().get(0);
        String responseText = "[";
        Boolean auxBool = false;
        for(Workspace.WorkspaceElem w : rootElement.getWorkspace()) {
        	auxBool = true;
        	responseText += "\"" + w.getID() + "\",";
        }
		if(auxBool) {
			responseText = responseText.substring(0, responseText.lastIndexOf(','));
		}
        responseText += "]";
        response.getWriter().append(responseText);
        return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Path baseDir = Utils.getBasePath();
		
		String uuid = request.getParameter("uuid");
		Path workspaceDir = baseDir.resolve(uuid);
		Files.createDirectories(workspaceDir);
		
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(WorkspacePackage.eNS_URI, WorkspacePackage.eINSTANCE);
        
        // === Create empty model.xmi ===
        URI modelURI = URI.createFileURI(workspaceDir.resolve("model.xmi").toString());
        Resource modelResource = resourceSet.createResource(modelURI);
        asignaturas.Root asignaturasRoot = asignaturas.AsignaturasFactory.eINSTANCE.createRoot();
        modelResource.getContents().add(asignaturasRoot);
        modelResource.save(null);
        
        // === Create empty students.xmi ===
        URI studentsURI = URI.createFileURI(workspaceDir.resolve("students.xmi").toString());
        Resource studentsResource = resourceSet.createResource(studentsURI);
        Estudiantes.Root studentRoot = Estudiantes.EstudiantesFactory.eINSTANCE.createRoot();
        studentsResource.getContents().add(studentRoot);
        studentsResource.save(null);
        
        // === Update Workspace xmi ===
        URI workspaceURI = URI.createFileURI(baseDir.resolve("workspaces.xmi").toString());
        File workspaceFile = new File(workspaceURI.toFileString());
        Resource WorkspaceResource;
        Workspace.RootWorkspaces workspaceRoot;
        if(workspaceFile.exists()) {
        	WorkspaceResource = resourceSet.getResource(workspaceURI, true);
        	workspaceRoot = (RootWorkspaces) WorkspaceResource.getContents().get(0);
        } else {
        	WorkspaceResource = resourceSet.createResource(workspaceURI);
        	workspaceRoot = Workspace.WorkspaceFactory.eINSTANCE.createRootWorkspaces();
        	WorkspaceResource.getContents().add(workspaceRoot);
        }
    	Workspace.WorkspaceElem workspaceElement = Workspace.WorkspaceFactory.eINSTANCE.createWorkspaceElem();
    	workspaceElement.setAsignaturas(asignaturasRoot);
    	workspaceElement.setEstudiante(studentRoot);
    	workspaceElement.setID(uuid);
    	workspaceRoot.getWorkspace().add(workspaceElement);
		Map<String, Object> saveOptions = new HashMap<>();
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
	    try {
	    	WorkspaceResource.save(saveOptions);
	        response.setContentType("text/plain");
	        response.getWriter().write("Workspace created successfully");
	    } catch (Exception e) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving model: " + e.getMessage());
	    }
        
        // === Response ===
        response.setContentType("application/json");
        response.getWriter().write("{\"workspace\":\"" + uuid + "\"}");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
