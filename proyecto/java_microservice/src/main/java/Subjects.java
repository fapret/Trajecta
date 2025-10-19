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
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.EstudiantesPackage;
import asignaturas.AsignaturasPackage;
import asignaturas.Career;
import asignaturas.CreditsPlan;
import asignaturas.CurricularUnit;
import asignaturas.Faculty;
import asignaturas.Root;
import asignaturas.Subject;
import asignaturas.SubjectPlan;

/**
 * Servlet implementation class Subjects
 */
@MultipartConfig
public class Subjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subjects() {
        super();
    }

    private String getSubject(asignaturas.Subject g) {
    	String out = "\"" + g.getId() + "\",";
    	for(asignaturas.Subject gc : g.getGroupOfSubjects()) {
    		out += getSubject(gc);
    	}
    	return out;
    }
    
    private String getSubject(asignaturas.Subject g, String id) {
		if(g.getId() == Integer.parseInt(id)) {
			String responseText = "";
			responseText = "{ \"Id\": \"" + g.getId() + "\",";
			responseText += "\"Name\": \"" + g.getName() + "\",";
			responseText += "\"MinCredits\": " + g.getMinCredits() + ",";
			responseText += "\"Subjects\": [";
			boolean f = false;
			for(asignaturas.Subject cg : g.getGroupOfSubjects()) {
				responseText += "\"" + cg.getId() + "\",";
				f = true;
			}
			if(f) {
				responseText = responseText.substring(0, responseText.lastIndexOf(','));
			}
			responseText += "], \"CurricularUnits\": [";
			f = false;
			for(CurricularUnit cu : g.getSubjectCurricularUnit()) {
				responseText += "\"" + cu.getId() + "\",";
				f = true;
			}
			if(f) {
				responseText = responseText.substring(0, responseText.lastIndexOf(','));
			}
			responseText += "]}";
			return responseText;
		}
		for(asignaturas.Subject gc : g.getGroupOfSubjects()) {
			String out = getSubject(gc, id);
			if(out != null) {
				return out;
			}
		}
		return null;
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
		
		try (JsonReader jsonReader = Json.createReader(request.getInputStream())) {
            JsonObject json = jsonReader.readObject();

            String faculty = json.getString("faculty");
            String career = json.getString("career");
            int plan = json.getInt("plan");
            int subjectID = json.getInt("subjectID");
            String subjectName = json.getString("subjectName");
            int subjectCredits = json.getInt("subjectCredits");
            int parentSubject = json.getInt("Parent", -1);

            // Arrays
            var curricularUnits = json.getJsonArray("curricularUnits");

            Subject subject = asignaturas.AsignaturasFactory.eINSTANCE.createSubject();
            subject.setId(subjectID);
            subject.setMinCredits(subjectCredits);
            subject.setName(subjectName);
            
            for (Faculty facultad : rootElement.getFaculty()) {
            	if(facultad.getName().equals(faculty)) {
            		for(Career carrera : facultad.getCareers()) {
            			if(carrera.getName().equals(career)) {
            				for(asignaturas.Plan p : carrera.getPlan()) {
            					if(p instanceof CreditsPlan) {
            						if(p.getYear() == plan) {
            							CreditsPlan planCreditos = (CreditsPlan) p;
            				            if(parentSubject != -1) {
            				            	for(Subject planSubject : planCreditos.getGroupOfSubjects()) {
            				            		if(planSubject.getId() == parentSubject) {
            				            			planSubject.getGroupOfSubjects().add(subject);
            				            			break;
            				            		}
            				            	}
            				            } else {
            				            	planCreditos.getGroupOfSubjects().add(subject);
            				            }
            							break;
            						}
            					}
            				}
            				break;
            			}
            		}
            		int Total = curricularUnits.size(); //Se puede optimizar, quitando el elemento encontrado, pero como en gral son pocos CU, no lo hago
            		for(CurricularUnit cu : facultad.getFacultyCU()) {
                		for(int i = 0; i < curricularUnits.size(); i++) {
                			String currUnit = curricularUnits.getString(i);
                			if(cu.getId().equals(currUnit)) {
                				subject.getSubjectCurricularUnit().add(cu);
                				Total--;
                				break;
                			}
                		}
                		if(Total == 0)
                			break;
            		}           		
            		break;
            	}
            }
            
            response.getWriter().append("Subject created succesfully");
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
		int plan = Integer.parseInt(request.getParameter("plan"));
		String subject = request.getParameter("subject");
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						for(asignaturas.Plan p : carrera.getPlan()) {
							if(p instanceof CreditsPlan) {
								if(p.getYear() == plan) {
									CreditsPlan planCreditos = (CreditsPlan) p;
									if(subject == null || subject.isBlank()) {
										String out = "[";
										for (asignaturas.Subject g : planCreditos.getGroupOfSubjects()) {
											out += getSubject(g);
										}
										if(!out.equals("[")) {
											out = out.substring(0, out.lastIndexOf(','));
										}
										out += "]";
										response.getWriter().append(out);
										return;
									} else {
										for (asignaturas.Subject g : planCreditos.getGroupOfSubjects()) {
											String out = getSubject(g, subject);
											if (out != null) {
												response.getWriter().append(out);
												return;
											}
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
