/**
 */
package Workspace.tests;

import Workspace.WorkspaceElem;
import Workspace.WorkspaceFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Elem</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class WorkspaceElemTest extends TestCase {

	/**
	 * The fixture for this Elem test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceElem fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(WorkspaceElemTest.class);
	}

	/**
	 * Constructs a new Elem test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkspaceElemTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Elem test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(WorkspaceElem fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Elem test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceElem getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(WorkspaceFactory.eINSTANCE.createWorkspaceElem());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //WorkspaceElemTest
