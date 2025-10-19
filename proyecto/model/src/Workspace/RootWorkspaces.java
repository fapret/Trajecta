/**
 */
package Workspace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root Workspaces</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Workspace.RootWorkspaces#getWorkspace <em>Workspace</em>}</li>
 * </ul>
 *
 * @see Workspace.WorkspacePackage#getRootWorkspaces()
 * @model
 * @generated
 */
public interface RootWorkspaces extends EObject {
	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' containment reference list.
	 * The list contents are of type {@link Workspace.Workspace}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace</em>' containment reference list.
	 * @see Workspace.WorkspacePackage#getRootWorkspaces_Workspace()
	 * @model containment="true"
	 * @generated
	 */
	EList<Workspace> getWorkspace();

} // RootWorkspaces
