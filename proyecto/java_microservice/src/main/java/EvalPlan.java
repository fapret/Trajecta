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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.*;
import asignaturas.*;

/**
 * Servlet implementation class EvalPlan
 */
@MultipartConfig
public class EvalPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalPlan() {
        super();
    }

    private List<String> getUC(Student e, Subject s){
    	List<String> uc = new ArrayList<>();
    	for(CurricularUnit cu : s.getSubjectCurricularUnit()) {
    		if(evaluateRequirements(e, cu.getRequirement())) {
    			uc.add(cu.getId());
    		}
    	}
    	for(Subject s2 : s.getGroupOfSubjects()) {
    		uc.addAll(getUC(e, s2));
    	}
    	return uc;
    }
    
    private List<String> get_valid_UC(Student e, Subject s){
    	List<String> uc = new ArrayList<>();
    	for(CurricularUnit cu : s.getSubjectCurricularUnit()) {
    		if (cu.isValid()) {
    			if(evaluateRequirements(e, cu.getRequirement())) {
    				uc.add(cu.getId());
    			}
    		}
    	}
    	for(Subject s2 : s.getGroupOfSubjects()) {
    		uc.addAll(get_valid_UC(e, s2));
    	}
    	return uc;
    }

    private Boolean evaluateRequirements(Student student, Requirement requirement) {
        return Grading.requirements(student, requirement);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	asignaturas.Root asignaturasRoot;
    	
    	Path baseDir = Utils.getBasePath();
		
		String uuid = request.getParameter("uuid");
		Path workspaceDir;
        try { workspaceDir = ModelStore.workspace(uuid); }
        catch (ModelStore.Problem e) { response.sendError(e.status, e.getMessage()); return; }
		
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        
        URI modelURI = URI.createFileURI(workspaceDir.resolve("model.xmi").toString());
        Resource resource = resourceSet.getResource(modelURI, true);
        asignaturasRoot = (asignaturas.Root) resource.getContents().get(0);
        
        URI model2URI = URI.createFileURI(workspaceDir.resolve("students.xmi").toString());
        Resource resource2 = resourceSet.getResource(model2URI, true);
        Estudiantes.Root rootStudent = (Estudiantes.Root) resource2.getContents().get(0);
		
		String faculty = request.getParameter("faculty");
		String career = request.getParameter("career");
		String plan = request.getParameter("plan");
		String ID = request.getParameter("id");
		boolean valid_flag = false;
		if (request.getParameter("valid_flag") != null)
			valid_flag = true;
		
		
		Student estudiante = null;
		if(ID == null || ID.isBlank()) {
			estudiante = rootStudent.getStudent().get(0);
		} else {
			for(Student studentSearch : rootStudent.getStudent()) {
				if(studentSearch.getId().equals(ID)) {
					estudiante = studentSearch;
					break;
				}
			}
		}
		if(estudiante == null) {
			response.getWriter().append("Error: Estudiante no encontrado.");
			return;
		}
		
		List<String> uc = new ArrayList<>();
		for (Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for (Career carrera : facultad.getCareers()) {
					if (carrera.getName().equals(career)) {
						for (asignaturas.Plan p : carrera.getPlan()) {
							if(Integer.toString(p.getYear()).equals(plan)) {
								if(p instanceof CreditsPlan) {
									CreditsPlan cp = (CreditsPlan) p;
									for(Subject sub : cp.getGroupOfSubjects()) {
										if (valid_flag)
											uc.addAll(get_valid_UC(estudiante, sub));
										else
											uc.addAll(getUC(estudiante, sub));
									}
								} else { //Es plan de materias
									SubjectPlan sp = (SubjectPlan) p;
									for(CurricularUnit cu : sp.getCurricularUnit()) {
										if (valid_flag) {
											if(cu.isValid() && evaluateRequirements(estudiante, cu.getRequirement())) {
												uc.add(cu.getId());
											}
										}
										else {
											if(evaluateRequirements(estudiante, cu.getRequirement())) {
												uc.add(cu.getId());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < uc.size(); i++) 
			uc.set(i, "\""+uc.get(i)+"\"");
		
		response.getWriter().append(uc.toString());
	}

}
