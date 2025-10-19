/**
 */
package Workspace;

import asignaturas.Root;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Elem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Workspace.WorkspaceElem#getPassword <em>Password</em>}</li>
 *   <li>{@link Workspace.WorkspaceElem#getAsignaturas <em>Asignaturas</em>}</li>
 *   <li>{@link Workspace.WorkspaceElem#getEstudiante <em>Estudiante</em>}</li>
 *   <li>{@link Workspace.WorkspaceElem#getID <em>ID</em>}</li>
 * </ul>
 *
 * @see Workspace.WorkspacePackage#getWorkspaceElem()
 * @model
 * @generated
 */
public interface WorkspaceElem extends EObject {
	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see Workspace.WorkspacePackage#getWorkspaceElem_Password()
	 * @model
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link Workspace.WorkspaceElem#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Asignaturas</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asignaturas</em>' reference.
	 * @see #setAsignaturas(Root)
	 * @see Workspace.WorkspacePackage#getWorkspaceElem_Asignaturas()
	 * @model
	 * @generated
	 */
	Root getAsignaturas();

	/**
	 * Sets the value of the '{@link Workspace.WorkspaceElem#getAsignaturas <em>Asignaturas</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asignaturas</em>' reference.
	 * @see #getAsignaturas()
	 * @generated
	 */
	void setAsignaturas(Root value);

	/**
	 * Returns the value of the '<em><b>Estudiante</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estudiante</em>' reference.
	 * @see #setEstudiante(Estudiantes.Root)
	 * @see Workspace.WorkspacePackage#getWorkspaceElem_Estudiante()
	 * @model
	 * @generated
	 */
	Estudiantes.Root getEstudiante();

	/**
	 * Sets the value of the '{@link Workspace.WorkspaceElem#getEstudiante <em>Estudiante</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Estudiante</em>' reference.
	 * @see #getEstudiante()
	 * @generated
	 */
	void setEstudiante(Estudiantes.Root value);

	/**
	 * Returns the value of the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ID</em>' attribute.
	 * @see #setID(String)
	 * @see Workspace.WorkspacePackage#getWorkspaceElem_ID()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getID();

	/**
	 * Sets the value of the '{@link Workspace.WorkspaceElem#getID <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ID</em>' attribute.
	 * @see #getID()
	 * @generated
	 */
	void setID(String value);

} // WorkspaceElem
