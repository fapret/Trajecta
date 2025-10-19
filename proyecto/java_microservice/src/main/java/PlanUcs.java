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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.EstudiantesPackage;
import asignaturas.AsignaturasPackage;
import asignaturas.Career;
import asignaturas.Faculty;
import asignaturas.Root;


/**
 * Servlet implementation class PlanUcs
 */
public class PlanUcs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanUcs() {
        super();
    }
    
    private String getSubjectUCs(asignaturas.Subject g, Boolean withName) {
    	String res = "";
		for (asignaturas.CurricularUnit cu : g.getSubjectCurricularUnit())
			if (withName)
				res += "{\"id\":\""+cu.getId()+"\", \"name\":\"" + cu.getName() + "\"},";
			else
				res += "\""+cu.getId()+"\",";

    	for (asignaturas.Subject g2 : g.getGroupOfSubjects())
    		res += getSubjectUCs(g2, withName);
    	
    	return res;
    }
    
    private String get_valid_SubjectUCs(asignaturas.Subject g, Boolean withName) {
    	String res = "";
		for (asignaturas.CurricularUnit cu : g.getSubjectCurricularUnit())
			if (cu.isValid()) {
				if (withName)
					res += "{\"id\":\""+cu.getId()+"\", \"name\":\"" + cu.getName() + "\"},";
				else
					res += "\""+cu.getId()+"\",";
			}

    	for (asignaturas.Subject g2 : g.getGroupOfSubjects())
    		res += get_valid_SubjectUCs(g2, withName);
    	
    	return res;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// devuelve todas las unidades curriculares de un plan de carrera
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
		String plan = request.getParameter("plan");
		Boolean withName = Boolean.parseBoolean(request.getParameter("withName"));
		boolean valid_flag = false;
		if (request.getParameter("valid_flag") != null)
			valid_flag = true;
		
		String res = "[";
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						for (asignaturas.Plan p : carrera.getPlan()) {
							if (String.valueOf(p.getYear()).equals(plan) && p instanceof asignaturas.CreditsPlan) {
								asignaturas.CreditsPlan pc = (asignaturas.CreditsPlan)(p);
								for (asignaturas.Subject g : pc.getGroupOfSubjects())
									if (valid_flag)
										res += get_valid_SubjectUCs(g, withName);
									else
										res += getSubjectUCs(g, withName);
							}
							else {
								asignaturas.SubjectPlan ps = (asignaturas.SubjectPlan)(p);
								if (valid_flag) {
									for (asignaturas.CurricularUnit cu : ps.getCurricularUnit()) {
										if (cu.isValid())
											if (withName)
												res += "{\"id\":\""+cu.getId()+"\", \"name\":\"" + cu.getName() + "\"},";
											else
												res += "\""+cu.getId()+"\",";
									}
								}
								else {
									for (asignaturas.CurricularUnit cu : ps.getCurricularUnit()) {
										if (withName)
											res += "{\"id\":\""+cu.getId()+"\", \"name\":\"" + cu.getName() + "\"},";
										else
											res += "\""+cu.getId()+"\",";
									}
								}
							}
						}
					}
				}
			}
		}
		
		//le saco el último ", "
		res = res.substring(0, res.length()-1);
		response.getWriter().append(res+"]");
					
	}

}
