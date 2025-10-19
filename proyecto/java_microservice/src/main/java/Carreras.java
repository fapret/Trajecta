/*
    Trajecta is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be. And helps academic managers do different researches.
    Copyright (C) 2023  Santiago Nicolás Díaz Conde, Santiago Freire López
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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.EstudiantesPackage;
import asignaturas.AsignaturasPackage;
import asignaturas.Career;
import asignaturas.CreditsPlan;
import asignaturas.Faculty;
import asignaturas.Root;
import asignaturas.SubjectPlan;

/**
 * Servlet implementation class Carreras
 */
public class Carreras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carreras() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Path baseDir = Utils.getBasePath();
		String uuid = request.getParameter("uuid");
	    if (uuid == null || uuid.isBlank()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing uuid parameter");
	        return;
	    }
		Path workspaceDir = baseDir.resolve(uuid);
	    if (!Files.exists(workspaceDir)) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Workspace not found");
	        return;
	    }
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        
        URI modelURI = URI.createFileURI(workspaceDir.resolve("model.xmi").toString());
        Resource resource = resourceSet.getResource(modelURI, true);
        asignaturas.Root rootElement = (asignaturas.Root) resource.getContents().get(0);
        
        String name = request.getParameter("name");
		if(name == null || name.isBlank()) {
			response.getWriter().append("Error: name is empty");
			return;
		}
		String faculty = request.getParameter("faculty");
		if(faculty == null || faculty.isBlank()) {
			response.getWriter().append("Error: faculty is empty");
			return;
		}
		
		for (Faculty facultad : rootElement.getFaculty()) {
        	if(facultad.getName().equals(faculty)) {
        		Career careerElem = asignaturas.AsignaturasFactory.eINSTANCE.createCareer();
        		careerElem.setName(name);
        		
        		facultad.getCareers().add(careerElem);
        		
        		Map<String, Object> saveOptions = new HashMap<>();
        		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
        		
        	    try {
        	        resource.save(saveOptions);
        	        response.setContentType("text/plain");
        	        response.getWriter().write("Career saved successfully to workspace: " + uuid);
        	    } catch (Exception e) {
        	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving model: " + e.getMessage());
        	    }
        		return;
        	}
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Path baseDir = Utils.getBasePath();
		String uuid = request.getParameter("uuid");
	    if (uuid == null || uuid.isBlank()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing uuid parameter");
	        return;
	    }
		Path workspaceDir = baseDir.resolve(uuid);
	    if (!Files.exists(workspaceDir)) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Workspace not found");
	        return;
	    }
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        
        URI modelURI = URI.createFileURI(workspaceDir.resolve("model.xmi").toString());
        Resource resource = resourceSet.getResource(modelURI, true);
        asignaturas.Root rootElement = (asignaturas.Root) resource.getContents().get(0);
        
		String faculty = request.getParameter("faculty");
		String career = request.getParameter("career");
		String responseText = "";
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						responseText += "{ \"Name\": \"" + carrera.getName() + "\", \"Plans\": [";
						Boolean auxBool = false;
						for(asignaturas.Plan plan : carrera.getPlan()) {
							auxBool = true;
							responseText += plan.getYear() + ",";
						}
						if(auxBool) {
							responseText = responseText.substring(0, responseText.lastIndexOf(','));
						}
						responseText += "]}";
						response.getWriter().append(responseText);
						return;
					}
				}
			}
		}
	}

}
