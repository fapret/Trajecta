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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.EstudiantesPackage;
import asignaturas.*;

import java.util.*;

/**
 * Servlet implementation class UnidadCurricular
 */
public class UnidadCurricular extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnidadCurricular() {
        super();
    }
    
    private CurricularUnit findCU(String cu, String faculty, Root rootElement) {
        for (Faculty fac : rootElement.getFaculty()) {
            if (fac.getName().equalsIgnoreCase(faculty)) {
                for (CurricularUnit cur : fac.getFacultyCU()) {
                    if (cur.getId().equalsIgnoreCase(cu)) {
                        return cur;
                    }
                }
            }
        }
        return null;
    }
    
    private asignaturas.Plan findPlan(String year, String faculty, String careerName, Root rootElement) {
        for (Faculty fac : rootElement.getFaculty()) {
            if (fac.getName().equalsIgnoreCase(faculty)) {
                for (Career career : fac.getCareers()) {
                    if (career.getName().equalsIgnoreCase(careerName)) {
                        for (asignaturas.Plan plan : career.getPlan()) {
                            if (plan.getYear() == Integer.parseInt(year)) {
                                return plan;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private Subject findSubject(String id, String facultyName, Root rootElement) {
        for (Faculty fac : rootElement.getFaculty()) {
            if (fac.getName().equalsIgnoreCase(facultyName)) {
                // Subjects appear in CreditsPlan (via GroupOfSubjects)
                for (Career career : fac.getCareers()) {
                    for (asignaturas.Plan plan : career.getPlan()) {
                        if (plan instanceof CreditsPlan) {
                            CreditsPlan cp = (CreditsPlan) plan;
                            for (Subject subj : cp.getGroupOfSubjects()) {
                                if (subj.getId() == Integer.parseInt(id)) {
                                    return subj;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
	}
    
    private String stripQuotes(String s) {
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\"")) s = s.substring(1, s.length() - 1);
        return s;
    }
    
    private List<String> splitTopLevel(String value) {
        List<String> parts = new ArrayList<>();
        int brace = 0;
        int bracket = 0;
        int start = 0;

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '{') brace++;
            else if (c == '}') brace--;
            else if (c == '[') bracket++;
            else if (c == ']') bracket--;
            else if (c == ',' && brace == 0 && bracket == 0) {
                parts.add(value.substring(start, i).trim());
                start = i + 1;
            }
        }
        parts.add(value.substring(start).trim());
        return parts;
    }
    
    private Requirement buildRequirement(String requerimiento, String faculty, Root rootElement) {
		if(requerimiento == null || requerimiento.isBlank()) {
			return null;
		}
		
		requerimiento = requerimiento.trim();
		
		if (requerimiento.startsWith("{") && requerimiento.endsWith("}")) {
			requerimiento = requerimiento.substring(1, requerimiento.length() - 1).trim(); // quitar {}
			int colonIndex = requerimiento.indexOf(":");
			String key = requerimiento.substring(0, colonIndex).trim().replace("\"", "");
			String value = requerimiento.substring(colonIndex + 1).trim();
			
			switch (key) {
			case "NOT":
				NOT reqNOT = asignaturas.AsignaturasFactory.eINSTANCE.createNOT();
				Requirement result = buildRequirement(value, faculty, rootElement);
				reqNOT.setRequirement(result);
				return reqNOT;
			case "Coursed":
				Coursed reqCoursed = asignaturas.AsignaturasFactory.eINSTANCE.createCoursed();
				reqCoursed.setCurricularUnit(findCU(value, faculty, rootElement));
				return reqCoursed;
			case "Exam":
				Exam reqExam = asignaturas.AsignaturasFactory.eINSTANCE.createExam();
				reqExam.setCurricularUnit(findCU(value, faculty, rootElement));
				return reqExam;
			case "RegisteredTo":
				RegisteredTo reqRegisteredTo = asignaturas.AsignaturasFactory.eINSTANCE.createRegisteredTo();
				reqRegisteredTo.setCurricularUnit(findCU(value, faculty, rootElement));
				return reqRegisteredTo;
			case "CreditsOnPlan":
				CreditsOnPlan reqCreditsOnPlan = asignaturas.AsignaturasFactory.eINSTANCE.createCreditsOnPlan();
				String[] planParts = stripQuotes(value).split(",");
				reqCreditsOnPlan.setCred(Integer.parseInt(planParts[2]));
				asignaturas.Plan plan = findPlan(planParts[0], faculty, planParts[1], rootElement);
				reqCreditsOnPlan.setCreditsPlan((CreditsPlan) plan);
				return reqCreditsOnPlan;
			case "CreditsOnSubject":
				CreditsOnSubject reqCreditsOnSubject = asignaturas.AsignaturasFactory.eINSTANCE.createCreditsOnSubject();
				String[] subjParts = stripQuotes(value).split(",");
				reqCreditsOnSubject.setCred(Integer.parseInt(subjParts[1]));
				Subject subj = findSubject(subjParts[0], faculty, rootElement);
				reqCreditsOnSubject.setGroupOfSubjects(subj);
				return reqCreditsOnSubject;
			case "SomeOf":
				SomeOf smof = asignaturas.AsignaturasFactory.eINSTANCE.createSomeOf();
				value = value.trim();
				if (value.startsWith("[") && value.endsWith("]")) {
					value = value.substring(1, value.length() - 1).trim();
					List<String> parts = splitTopLevel(value);
					int n = Integer.parseInt(parts.get(0).trim());
					smof.setN(n);
					for (int i = 1; i < parts.size(); i++) {
						smof.getRequirement().add(buildRequirement(parts.get(i), faculty, rootElement));
					}
					return smof;
				}
			}
		}
		
		return null;
    }

	private String getRequeriment(Requirement requerimiento) {
    	if(requerimiento instanceof NOT) {
    		NOT req = (NOT) requerimiento;
    		return "{ \"NOT\": " + getRequeriment(req.getRequirement()) + "}";
    	}
    	if(requerimiento instanceof Coursed) {
    		Coursed req = (Coursed) requerimiento;
    		return "{ \"Coursed\": \"" + req.getCurricularUnit().getId() + "\"}";
    	}
    	if(requerimiento instanceof Exam) {
    		Exam req = (Exam) requerimiento;
    		return "{ \"Exam\": \"" + req.getCurricularUnit().getId() + "\"}";
    	}
    	if(requerimiento instanceof RegisteredTo) {
    		RegisteredTo req = (RegisteredTo) requerimiento;
    		return "{ \"RegisteredTo\": \"" + req.getCurricularUnit().getId() + "\"}";
    	}
    	if(requerimiento instanceof SomeOf) {
    		SomeOf req = (SomeOf) requerimiento;
    		String reqs = "";
    		for(Requirement requer : req.getRequirement()) {
    			reqs += getRequeriment(requer) + ",";
    		}
    		reqs = reqs.substring(0, reqs.lastIndexOf(','));
    		return "{ \"SomeOf\": ["+String.valueOf(req.getN())+", "+ reqs + "]}";
    	}
    	if(requerimiento instanceof CreditsOnPlan) {
    		CreditsOnPlan req = (CreditsOnPlan) requerimiento;
    		return "{ \"CreditsOnPlan\": \"" + req.getCreditsPlan().getYear() + "," + req.getCreditsPlan().getCareer_parent().getName() + "," + req.getCred() + "\"}";
    	}
    	if(requerimiento instanceof CreditsOnSubject) {
    		CreditsOnSubject req = (CreditsOnSubject) requerimiento;
    		return "{ \"CreditsOnSubject\": \"" + req.getGroupOfSubjects().getId() + "," + req.getCred() + "\"}";
    	}
    	return "";
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		asignaturas.Root rootElement;
		Path baseDir = Utils.getBasePath();
		String uuid = request.getParameter("uuid");
		if(uuid == null || uuid.isBlank()) {
			response.getWriter().append("Error: uuid is empty");
			return;
		}
		Path workspaceDir = baseDir.resolve(uuid);
		
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        
        URI modelURI = URI.createFileURI(workspaceDir.resolve("model.xmi").toString());
        Resource resource = resourceSet.getResource(modelURI, true);
        rootElement = (asignaturas.Root) resource.getContents().get(0);
        
        String faculty = request.getParameter("faculty");
		if(faculty == null || faculty.isBlank()) {
			response.getWriter().append("Error: faculty is empty");
			return;
		}
        String id = request.getParameter("id");
		if(id == null || id.isBlank()) {
			response.getWriter().append("Error: id is empty");
			return;
		}
        String name = request.getParameter("name");
		if(name == null || name.isBlank()) {
			response.getWriter().append("Error: name is empty");
			return;
		}
		int cred = Integer.parseInt(request.getParameter("name"));

        // Read JSON body
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        String requirements = jsonBuffer.toString();
        
        
        for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
		        CurricularUnit cu = asignaturas.AsignaturasFactory.eINSTANCE.createCurricularUnit();
		        Requirement req = buildRequirement(requirements, faculty, rootElement);
		        if(req != null)
		        	cu.setRequirement(req);
		        cu.setId(id);
		        cu.setName(name);
		        cu.setCred(cred);
				facultad.getFacultyCU().add(cu);
				Map<String, Object> saveOptions = new HashMap<>();
				saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
				
			    try {
			        resource.save(saveOptions);
			        response.setContentType("text/plain");
			        response.getWriter().write("Curricular Unit saved successfully to workspace: " + uuid);
			    } catch (Exception e) {
			        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving model: " + e.getMessage());
			    }
			    return;
			}
        }
        response.getWriter().write("Faculty not found on workspace: " + uuid);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		asignaturas.Root rootElement;
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
        rootElement = (asignaturas.Root) resource.getContents().get(0);
		
		String faculty = request.getParameter("faculty");
		String curricularUnit = request.getParameter("curricularUnit");
		Boolean withName = Boolean.parseBoolean(request.getParameter("withName"));
		String responseText = "";
		
		if(curricularUnit == null || curricularUnit.isBlank()) {
			for (Faculty facultad : rootElement.getFaculty()) {
				if(facultad.getName().equals(faculty)) {
					response.getWriter().append("[");
					Boolean auxBool = false;
					for(CurricularUnit cu : facultad.getFacultyCU()) {
						if (withName)
							responseText += "{\"id\":\""+cu.getId()+"\", \"name\":\"" + cu.getName() + "\"},";
						else
							responseText += "\""+cu.getId()+"\",";
						auxBool = true;
					}
					if(auxBool) {
						responseText = responseText.substring(0, responseText.lastIndexOf(','));
					}
					response.getWriter().append(responseText).append("]");
					return;
				}
			}
		}
		
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(CurricularUnit cu : facultad.getFacultyCU()) {
					if(cu.getId().equals(curricularUnit)) {
						responseText = "{ \"Id\": \"" + cu.getId() + "\", \"Name\": \"" + cu.getName() + "\", " + "\"Cred\": " + cu.getCred() + ", ";
						responseText += "\"Requirement\": [" + getRequeriment(cu.getRequirement()) + "], ";
						responseText += "\"ExamEvaluation\": [";
						Boolean auxBool = false;
						for(ExamEvaluation exam : cu.getExamEvaluation()) {
							auxBool = true;
							responseText += "\"" + exam.getDate().toString() + "\",";
						}
						if(auxBool) {
							responseText = responseText.substring(0, responseText.lastIndexOf(','));
						}
						responseText += "], \"Course\": [";
						auxBool = false;
						for(Course curso : cu.getCourse()) {
							auxBool = true;
							responseText += "{ \"Year\": " + curso.getYear() + ", \"Edition\": " + curso.getEdition() + ", \"CourseEvaluation\": [";
							Boolean auxBool2 = false;
							for(CourseEvaluation ce : curso.getCourseEvaluation()) {
								responseText += "\"" + ce.getDate().toString() + "\",";
								auxBool2 = true;
							}
							if(auxBool2) {
								responseText = responseText.substring(0, responseText.lastIndexOf(','));
							}
							responseText += "], \"PartialEvaluation\": [";
							auxBool2 = false;
							for(PartialEvaluation ce : curso.getPartialevaluation()) {
								responseText += "\"" + ce.getDate().toString() + "\",";
								auxBool2 = true;
							}
							if(auxBool2) {
								responseText = responseText.substring(0, responseText.lastIndexOf(','));
							}
							responseText += "]},";
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
