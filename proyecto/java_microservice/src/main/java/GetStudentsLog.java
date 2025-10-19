/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.*;
import asignaturas.*;
import asignaturas.Root;

/**
 * Servlet implementation class GetStudentsLog
 */
@MultipartConfig
public class GetStudentsLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentsLog() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
        
        URI modelURI = URI.createFileURI(workspaceDir.resolve("students.xmi").toString());
        Resource resource = resourceSet.getResource(modelURI, true);
        Estudiantes.Root rootStudent = (Estudiantes.Root) resource.getContents().get(0);
        
		
		//Append header of CSV (ID, Activity, Timestamp, Career, Plan, Curricular Unit, Course Edition, Course Year, Grade)
		StringBuilder csvBuilder = new StringBuilder();
		csvBuilder.append("ID,Activity,Timestamp,Career,Plan,Curricular Unit,Course Edition,Course Year,Grade,Credits\n");
		
		//Itero por cada estudiante
		for (Student estudiante : rootStudent.getStudent()) {
			String ID = estudiante.getId();
			for(PlanInscription inscripcionPlan : estudiante.getStudentPlanInscription()) {
				Date TimeStamp = inscripcionPlan.getDate();
				String Career = inscripcionPlan.getPlan().getCareer_parent().getName();
				int Plan = inscripcionPlan.getPlan().getYear();
				String Activity = "Inscription to Plan";
				//Append to file each inscription
				csvBuilder.append(String.format("%s,%s,%s,%s,%d,,,,,\n",
					    ID, Activity, TimeStamp, Career, Plan));
				
				if(inscripcionPlan.getDegree() != null) {
					TimeStamp = inscripcionPlan.getDegree().getDate();
					Activity = "Degree obtained";
					csvBuilder.append(String.format("%s,%s,%s,%s,%d,,,,,\n",
						    ID, Activity, TimeStamp, Career, Plan));
				}
				
				for(CourseInscription inscripcionCurso : inscripcionPlan.getPlanCourseInscription()) {
					TimeStamp = inscripcionCurso.getDate();
					Activity = "Inscription to Course";
					int CourseEdition = inscripcionCurso.getCUCourse().getEdition();
					int CourseYear = inscripcionCurso.getCUCourse().getYear();
					String CurricularUnit = inscripcionCurso.getCUCourse().getCurricularunit().getId();
					//Append to file each inscription
					csvBuilder.append(String.format("%s,%s,%s,%s,%d,%s,%d,%d,,\n",
						    ID, Activity, TimeStamp, Career, Plan, CurricularUnit, CourseEdition, CourseYear));
				}
				
				for(StudentEvaluation evaluacion : inscripcionPlan.getPlanStudentEvaluation()) {
					int Grade = evaluacion.getGrade();
					int Credits = 0;
					Activity = "Evaluation - ";
					Evaluation eval = evaluacion.getEvaluation();
					TimeStamp = eval.getDate();
					
					if (eval instanceof ExamEvaluation) {
						Activity += "Exam";
						ExamEvaluation evalExam = (ExamEvaluation) eval;
						String CurricularUnit = evalExam.getCurricularunit().getId();
						if(evaluacion.getGrade() >= 3)
							Credits = evalExam.getCurricularunit().getCred();
						csvBuilder.append(String.format("%s,%s,%s,%s,%d,%s,,,%d,%d\n",
							    ID, Activity, TimeStamp, Career, Plan, CurricularUnit, Grade, Credits));
						
					} else if (eval instanceof CourseEvaluation) {
						Activity += "Course";
						CourseEvaluation evalCourse = (CourseEvaluation) eval;
						String CurricularUnit = evalCourse.getCourse().getCurricularunit().getId();
						int CourseEdition = evalCourse.getCourse().getEdition();
						int CourseYear = evalCourse.getCourse().getYear();
						if(evaluacion.getGrade() >= 6)
							Credits = evalCourse.getCourse().getCurricularunit().getCred();
						csvBuilder.append(String.format("%s,%s,%s,%s,%d,%s,%d,%d,%d,%d\n",
							    ID, Activity, TimeStamp, Career, Plan, CurricularUnit, CourseEdition, CourseYear, Grade, Credits));
					} else if (eval instanceof PartialEvaluation) {
						Activity += "Partial";
						PartialEvaluation partialCourse = (PartialEvaluation) eval;
						String CurricularUnit = partialCourse.getCourse().getCurricularunit().getId();
						int CourseEdition = partialCourse.getCourse().getEdition();
						int CourseYear = partialCourse.getCourse().getYear();
						csvBuilder.append(String.format("%s,%s,%s,%s,%d,%s,%d,%d,%d,\n",
							    ID, Activity, TimeStamp, Career, Plan, CurricularUnit, CourseEdition, CourseYear, Grade));
					}
				}
			}
		}
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"student_log.csv\"");
		response.getWriter().write(csvBuilder.toString());
	}

}
