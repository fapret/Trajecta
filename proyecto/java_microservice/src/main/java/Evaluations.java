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
public class Evaluations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Evaluations() {
        super();
    }

        
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	asignaturas.Root asignaturasRoot;
    	
    	Path baseDir = Utils.getBasePath();
		
		String uuid = request.getParameter("uuid");
		Path workspaceDir = baseDir.resolve(uuid);
		
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
		
		//de un estudiante, devuelve las evaluaciones que tiene aprobadas, distinguiendo si es CourseEvaluation o ExamEvaluation y su nota
		
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
		

		//O(nˆ8), creo que es un récord personal
		List<String> evals = new ArrayList<>();
		for (Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for (Career carrera : facultad.getCareers()) {
					if (carrera.getName().equals(career)) {
						for (asignaturas.Plan p : carrera.getPlan()) {
							if(Integer.toString(p.getYear()).equals(plan)) {
								for (PlanInscription i : estudiante.getStudentPlanInscription()) {
									if (i.getPlan().equals(p)) {
										for (StudentEvaluation se : i.getPlanStudentEvaluation()) {
											
											if (se.getEvaluation() instanceof CourseEvaluation && se.getGrade() >= 3) {
												//encuentro a qué CU corresponde la evaluación
												for (CurricularUnit cu : facultad.getFacultyCU())
													for (asignaturas.Course cur : cu.getCourse())
														for (CourseEvaluation coe : cur.getCourseEvaluation())
															if (coe == se.getEvaluation())
																evals.add("{\""+"id\": \""+cu.getId()+"\", \"type\": \"CourseEvaluation\", \"grade\": "+String.valueOf(se.getGrade())+"}");
											}
											else if (se.getEvaluation() instanceof ExamEvaluation && se.getGrade() >= 3) {
												for (CurricularUnit cu : facultad.getFacultyCU())
													for (ExamEvaluation ee : cu.getExamEvaluation())
														if (ee == se.getEvaluation())
															evals.add("{\""+"id\": \""+cu.getId()+"\", \"type\": \"ExamEvaluation\", \"grade\": "+String.valueOf(se.getGrade())+"}");
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
		
		String out = "[";
		if (evals.size() > 0) {
			for (int i = 0; i < evals.size()-1; i++)
				out += evals.get(i)+", ";
			
			out += evals.get(evals.size()-1);
		}
		out += "]";
		
		response.getWriter().append(out);
	}
}
