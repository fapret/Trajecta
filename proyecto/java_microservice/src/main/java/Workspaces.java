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
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.EstudiantesPackage;
import Workspace.RootWorkspaces;
import Workspace.WorkspaceElem;
import Workspace.WorkspacePackage;
import asignaturas.AsignaturasPackage;

/**
 * Servlet implementation class Workspaces
 */
@WebServlet("/Workspaces")
@MultipartConfig
public class Workspaces extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final int PBKDF2_ITERATIONS = 120000;
	private static final int PBKDF2_KEY_LENGTH = 256;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Workspaces() {
        super();
    }

    static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	byte[] salt = new byte[16];
    	SecureRandom.getInstanceStrong().nextBytes(salt);
    	KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, PBKDF2_KEY_LENGTH);
    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
    	byte[] hash = keyFactory.generateSecret(spec).getEncoded();
    	String encodedSalt = Base64.getEncoder().encodeToString(salt);
    	String encodedHash = Base64.getEncoder().encodeToString(hash);
    	return "pbkdf2$" + PBKDF2_ITERATIONS + "$" + encodedSalt + "$" + encodedHash;
    }

    static boolean verifyPassword(String candidate, String storedValue) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	if(storedValue == null || storedValue.isEmpty()) {
    		return false;
    	}
    	String[] parts = storedValue.split("\\$");
    	if(parts.length != 4 || !"pbkdf2".equals(parts[0])) {
    		return false;
    	}
    	int iterations = Integer.parseInt(parts[1]);
    	byte[] salt = Base64.getDecoder().decode(parts[2]);
    	byte[] expectedHash = Base64.getDecoder().decode(parts[3]);

    	KeySpec spec = new PBEKeySpec(candidate.toCharArray(), salt, iterations, expectedHash.length * 8);
    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
    	byte[] calculated = keyFactory.generateSecret(spec).getEncoded();
    	return java.security.MessageDigest.isEqual(expectedHash, calculated);
    }

    static String extractJsonField(String jsonBody, String fieldName) {
    	if(jsonBody == null || jsonBody.isEmpty()) {
    		return null;
    	}
    	String pattern = "\"" + fieldName + "\"";
    	int keyIdx = jsonBody.indexOf(pattern);
    	if(keyIdx < 0) {
    		return null;
    	}
    	int colonIdx = jsonBody.indexOf(':', keyIdx + pattern.length());
    	if(colonIdx < 0) {
    		return null;
    	}
    	int firstQuote = jsonBody.indexOf('"', colonIdx + 1);
    	if(firstQuote < 0) {
    		return null;
    	}
    	int secondQuote = jsonBody.indexOf('"', firstQuote + 1);
    	if(secondQuote < 0) {
    		return null;
    	}
    	return jsonBody.substring(firstQuote + 1, secondQuote);
    }

    static WorkspaceElem findWorkspaceByUuid(RootWorkspaces root, String uuid) {
    	if(root == null || uuid == null) {
    		return null;
    	}
    	for (WorkspaceElem workspaceElem : root.getWorkspace()) {
    		if(uuid.equals(workspaceElem.getID())) {
    			return workspaceElem;
    		}
    	}
    	return null;
    }

    static RootWorkspaces loadWorkspaceRoot(Path baseDir, ResourceSet resourceSet) throws IOException {
    	URI workspaceURI = URI.createFileURI(baseDir.resolve("workspaces.xmi").toString());
        File workspaceFile = new File(workspaceURI.toFileString());
        Resource workspaceResource;
        RootWorkspaces workspaceRoot;
        if(workspaceFile.exists()) {
        	workspaceResource = resourceSet.getResource(workspaceURI, true);
        	workspaceRoot = (RootWorkspaces) workspaceResource.getContents().get(0);
        } else {
        	workspaceResource = resourceSet.createResource(workspaceURI);
        	workspaceRoot = Workspace.WorkspaceFactory.eINSTANCE.createRootWorkspaces();
        	workspaceResource.getContents().add(workspaceRoot);
        }
        return workspaceRoot;
    }

    static Resource loadWorkspaceResource(Path baseDir, ResourceSet resourceSet) {
    	URI workspaceURI = URI.createFileURI(baseDir.resolve("workspaces.xmi").toString());
    	File workspaceFile = new File(workspaceURI.toFileString());
    	if(workspaceFile.exists()) {
    		return resourceSet.getResource(workspaceURI, true);
    	}
    	return resourceSet.createResource(workspaceURI);
    }

    static ResourceSet createResourceSet() {
    	ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xmi", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, AsignaturasPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, EstudiantesPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(WorkspacePackage.eNS_URI, WorkspacePackage.eINSTANCE);
        return resourceSet;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Path baseDir = Utils.getBasePath();
		ResourceSet resourceSet = createResourceSet();
        URI workspaceURI = URI.createFileURI(baseDir.resolve("workspaces.xmi").toString());
        File workspaceFile = new File(workspaceURI.toFileString());
        Resource resource;
        Workspace.RootWorkspaces rootElement;
        if(workspaceFile.exists()) {
        	resource = resourceSet.getResource(workspaceURI, true);
        	rootElement = (Workspace.RootWorkspaces) resource.getContents().get(0);
        } else {
        	resource = resourceSet.createResource(workspaceURI);
        	rootElement = Workspace.WorkspaceFactory.eINSTANCE.createRootWorkspaces();
        	resource.getContents().add(rootElement);
        	
            Map<String, Object> saveOptions = new HashMap<>();
            saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
            resource.save(saveOptions);
        }

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
		String password = request.getParameter("password");
		if (password == null || password.isBlank()) {
			String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			password = extractJsonField(body, "password");
		}
		Path workspaceDir = baseDir.resolve(uuid);
		Files.createDirectories(workspaceDir);
		
		ResourceSet resourceSet = createResourceSet();
		
        URI modelURI = URI.createFileURI(workspaceDir.resolve("model.xmi").toString());
        Resource modelResource = resourceSet.createResource(modelURI);
        asignaturas.Root asignaturasRoot = asignaturas.AsignaturasFactory.eINSTANCE.createRoot();
        modelResource.getContents().add(asignaturasRoot);
        modelResource.save(null);
        
        URI studentsURI = URI.createFileURI(workspaceDir.resolve("students.xmi").toString());
        Resource studentsResource = resourceSet.createResource(studentsURI);
        Estudiantes.Root studentRoot = Estudiantes.EstudiantesFactory.eINSTANCE.createRoot();
        studentsResource.getContents().add(studentRoot);
        studentsResource.save(null);
        
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
    	if (password != null && !password.isBlank()) {
    		try {
    			workspaceElement.setPassword(hashPassword(password));
    		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
    			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error hashing password");
    			return;
    		}
    	} else {
    		workspaceElement.setPassword(null);
    	}
    	workspaceRoot.getWorkspace().add(workspaceElement);
		Map<String, Object> saveOptions = new HashMap<>();
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
	    try {
	    	WorkspaceResource.save(saveOptions);
	    } catch (Exception e) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving model: " + e.getMessage());
	        return;
	    }
        
        response.setContentType("application/json");
        response.getWriter().write("{\"workspace\":\"" + uuid + "\"}");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
