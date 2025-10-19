/**
 */
package Workspace;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see Workspace.WorkspaceFactory
 * @model kind="package"
 * @generated
 */
public interface WorkspacePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Workspace";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "WorkspaceURI";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "WorkspaceURI";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WorkspacePackage eINSTANCE = Workspace.impl.WorkspacePackageImpl.init();

	/**
	 * The meta object id for the '{@link Workspace.impl.RootWorkspacesImpl <em>Root Workspaces</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Workspace.impl.RootWorkspacesImpl
	 * @see Workspace.impl.WorkspacePackageImpl#getRootWorkspaces()
	 * @generated
	 */
	int ROOT_WORKSPACES = 0;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_WORKSPACES__WORKSPACE = 0;

	/**
	 * The number of structural features of the '<em>Root Workspaces</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_WORKSPACES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Root Workspaces</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_WORKSPACES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Workspace.impl.WorkspaceElemImpl <em>Elem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Workspace.impl.WorkspaceElemImpl
	 * @see Workspace.impl.WorkspacePackageImpl#getWorkspaceElem()
	 * @generated
	 */
	int WORKSPACE_ELEM = 1;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ELEM__PASSWORD = 0;

	/**
	 * The feature id for the '<em><b>Asignaturas</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ELEM__ASIGNATURAS = 1;

	/**
	 * The feature id for the '<em><b>Estudiante</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ELEM__ESTUDIANTE = 2;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ELEM__ID = 3;

	/**
	 * The number of structural features of the '<em>Elem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ELEM_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Elem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ELEM_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link Workspace.RootWorkspaces <em>Root Workspaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root Workspaces</em>'.
	 * @see Workspace.RootWorkspaces
	 * @generated
	 */
	EClass getRootWorkspaces();

	/**
	 * Returns the meta object for the containment reference list '{@link Workspace.RootWorkspaces#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Workspace</em>'.
	 * @see Workspace.RootWorkspaces#getWorkspace()
	 * @see #getRootWorkspaces()
	 * @generated
	 */
	EReference getRootWorkspaces_Workspace();

	/**
	 * Returns the meta object for class '{@link Workspace.WorkspaceElem <em>Elem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Elem</em>'.
	 * @see Workspace.WorkspaceElem
	 * @generated
	 */
	EClass getWorkspaceElem();

	/**
	 * Returns the meta object for the attribute '{@link Workspace.WorkspaceElem#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see Workspace.WorkspaceElem#getPassword()
	 * @see #getWorkspaceElem()
	 * @generated
	 */
	EAttribute getWorkspaceElem_Password();

	/**
	 * Returns the meta object for the reference '{@link Workspace.WorkspaceElem#getAsignaturas <em>Asignaturas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Asignaturas</em>'.
	 * @see Workspace.WorkspaceElem#getAsignaturas()
	 * @see #getWorkspaceElem()
	 * @generated
	 */
	EReference getWorkspaceElem_Asignaturas();

	/**
	 * Returns the meta object for the reference '{@link Workspace.WorkspaceElem#getEstudiante <em>Estudiante</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Estudiante</em>'.
	 * @see Workspace.WorkspaceElem#getEstudiante()
	 * @see #getWorkspaceElem()
	 * @generated
	 */
	EReference getWorkspaceElem_Estudiante();

	/**
	 * Returns the meta object for the attribute '{@link Workspace.WorkspaceElem#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see Workspace.WorkspaceElem#getID()
	 * @see #getWorkspaceElem()
	 * @generated
	 */
	EAttribute getWorkspaceElem_ID();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WorkspaceFactory getWorkspaceFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link Workspace.impl.RootWorkspacesImpl <em>Root Workspaces</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Workspace.impl.RootWorkspacesImpl
		 * @see Workspace.impl.WorkspacePackageImpl#getRootWorkspaces()
		 * @generated
		 */
		EClass ROOT_WORKSPACES = eINSTANCE.getRootWorkspaces();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT_WORKSPACES__WORKSPACE = eINSTANCE.getRootWorkspaces_Workspace();

		/**
		 * The meta object literal for the '{@link Workspace.impl.WorkspaceElemImpl <em>Elem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Workspace.impl.WorkspaceElemImpl
		 * @see Workspace.impl.WorkspacePackageImpl#getWorkspaceElem()
		 * @generated
		 */
		EClass WORKSPACE_ELEM = eINSTANCE.getWorkspaceElem();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_ELEM__PASSWORD = eINSTANCE.getWorkspaceElem_Password();

		/**
		 * The meta object literal for the '<em><b>Asignaturas</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE_ELEM__ASIGNATURAS = eINSTANCE.getWorkspaceElem_Asignaturas();

		/**
		 * The meta object literal for the '<em><b>Estudiante</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE_ELEM__ESTUDIANTE = eINSTANCE.getWorkspaceElem_Estudiante();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_ELEM__ID = eINSTANCE.getWorkspaceElem_ID();

	}

} //WorkspacePackage
