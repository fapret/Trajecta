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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.CourseInscription;
import Estudiantes.EstudiantesPackage;
import Estudiantes.PlanInscription;
import Estudiantes.Student;
import asignaturas.AsignaturasPackage;
import asignaturas.Career;
import asignaturas.Course;
import asignaturas.CurricularUnit;
import asignaturas.Faculty;

/**
 * Servlet implementation class EstudianteAddCourse
 */
@MultipartConfig
public class EstudianteAddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstudianteAddCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int plan = Integer.parseInt(request.getParameter("plan"));
		String unidadcurricular = request.getParameter("curricularunit");
		String course = request.getParameter("course");
		String[] courseparts = course.split("-", 2);
		String date = request.getParameter("date");
		String ID = request.getParameter("id");
		
		
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
		
		for(PlanInscription planInsc : estudiante.getStudentPlanInscription()) {
			for(CourseInscription courseInsc : planInsc.getPlanCourseInscription()) {
				if(courseInsc.getCUCourse().getYear() == Integer.parseInt(courseparts[0]) && courseInsc.getCUCourse().getEdition() == Integer.parseInt(courseparts[1]) && courseInsc.getCUCourse().getCurricularunit().getId().equals(unidadcurricular)) {
					response.getWriter().append("El estudiante ya esta inscripto en el curso");
					return;
				}
			}
		}
		
		for(Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(CurricularUnit cu : facultad.getFacultyCU()) {
					if(cu.getId().equals(unidadcurricular)) {
						for(Course cor : cu.getCourse()) {
							if(cor.getYear() == Integer.parseInt(courseparts[0]) && cor.getEdition() == Integer.parseInt(courseparts[1])) {
								for(PlanInscription pi : estudiante.getStudentPlanInscription()) {
									if(pi.getPlan().getYear() == plan && pi.getPlan().getCareer_parent().getName().equals(career)) {
										CourseInscription CorI = Estudiantes.EstudiantesFactory.eINSTANCE.createCourseInscription();
										SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
										Date selectedDate = null;
										try {
											selectedDate = dateFormat.parse(date);
										} catch (ParseException e) {
											response.getWriter().append(e.toString());
											return;
										}
										CorI.setDate(selectedDate);
										CorI.setCUCourse(cor);
										pi.getPlanCourseInscription().add(CorI);
										
										Map<String, Object> saveOptions = new HashMap<>();
										saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
										
									    try {
									    	resource2.save(saveOptions);
									        response.setContentType("text/plain");
									        response.getWriter().write("Student updated successfully to workspace: " + uuid);
									    } catch (Exception e) {
									        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving model: " + e.getMessage());
									    }
										return;
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
