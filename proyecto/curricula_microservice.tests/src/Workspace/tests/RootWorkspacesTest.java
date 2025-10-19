/**
 */
package Workspace.tests;

import Workspace.RootWorkspaces;
import Workspace.WorkspaceFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Root Workspaces</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RootWorkspacesTest extends TestCase {

	/**
	 * The fixture for this Root Workspaces test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootWorkspaces fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RootWorkspacesTest.class);
	}

	/**
	 * Constructs a new Root Workspaces test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootWorkspacesTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Root Workspaces test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(RootWorkspaces fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Root Workspaces test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootWorkspaces getFixture() {
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
		setFixture(WorkspaceFactory.eINSTANCE.createRootWorkspaces());
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

} //RootWorkspacesTest
