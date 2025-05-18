/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
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
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.CourseInscription;
import Estudiantes.PlanInscription;
import Estudiantes.Root;
import Estudiantes.Student;
import asignaturas.Course;
import asignaturas.CurricularUnit;

/**
 * Servlet implementation class Estudiante
 */
@MultipartConfig
public class Estudiante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Estudiante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Carga modelo
		asignaturas.Root asignaturasRoot;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		asignaturasRoot = (asignaturas.Root) resource.getContents().get(0);
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		if(name == null || name.isBlank() || id == null || id.isBlank()) {
			response.getWriter().append("Error: name or id is empty");
		}
		Root studentModel = Estudiantes.EstudiantesFactory.eINSTANCE.createRoot();
		Student student = Estudiantes.EstudiantesFactory.eINSTANCE.createStudent();
		student.setName(name);
		student.setId(id);
		studentModel.getStudent().add(student);
		Resource outputResource = resourceSet.createResource(URI.createURI("model.xmi"));
		outputResource.getContents().add(studentModel);
		Map<String, Object> saveOptions = new HashMap<>();
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
		
		response.setContentType("application/xml");
		response.setHeader("Content-Disposition", "attachment;filename=model.xmi");
		
		try {
			outputResource.save(response.getOutputStream(), saveOptions);
		} catch (Exception e) {
			response.getWriter().write(e.toString());
			return;
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		asignaturas.Root asignaturasRoot;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		asignaturasRoot = (asignaturas.Root) resource.getContents().get(0);
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		if(name == null || name.isBlank() || id == null || id.isBlank()) {
			response.getWriter().write("Error: name or id is empty");
			return;
		}
		
		Estudiantes.Root rootStudent;
		Part filePart;
		InputStream inputStream;
		Resource resourceStudent = null;
		try {
			filePart = request.getPart("file");
			inputStream = filePart.getInputStream();
			resourceStudent = resourceSet.createResource(URI.createURI(Paths.get(filePart.getSubmittedFileName()).getFileName().toString()));
		
			resourceStudent.load(inputStream, null);
			
			rootStudent = (Estudiantes.Root) resourceStudent.getContents().get(0);
		} catch (IOException e) {
			response.getWriter().write(e.toString());
			return;
		}
		
		Student student = Estudiantes.EstudiantesFactory.eINSTANCE.createStudent();
		student.setName(name);
		student.setId(id);
		rootStudent.getStudent().add(student);
		
		Map<String, Object> saveOptions = new HashMap<>();
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
		response.setContentType("application/xml");
		response.setHeader("Content-Disposition", "attachment;filename=model.xmi");
		
		try {
			resourceStudent.save(response.getOutputStream(), saveOptions);
		} catch (Exception e) {
			response.getWriter().write(e.toString());
		}
		
		return;
	}
	
}
