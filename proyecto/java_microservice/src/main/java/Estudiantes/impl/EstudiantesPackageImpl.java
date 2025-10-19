/**
 */
package Estudiantes.impl;

import Estudiantes.CourseInscription;
import Estudiantes.Degree;
import Estudiantes.EstudiantesFactory;
import Estudiantes.EstudiantesPackage;
import Estudiantes.PlanInscription;
import Estudiantes.Root;
import Estudiantes.Student;
import Estudiantes.StudentEvaluation;

import Workspace.WorkspacePackage;

import Workspace.impl.WorkspacePackageImpl;

import asignaturas.AsignaturasPackage;

import asignaturas.impl.AsignaturasPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EstudiantesPackageImpl extends EPackageImpl implements EstudiantesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass studentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass courseInscriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass studentEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass planInscriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass degreeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see Estudiantes.EstudiantesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EstudiantesPackageImpl() {
		super(eNS_URI, EstudiantesFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link EstudiantesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EstudiantesPackage init() {
		if (isInited) return (EstudiantesPackage)EPackage.Registry.INSTANCE.getEPackage(EstudiantesPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredEstudiantesPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		EstudiantesPackageImpl theEstudiantesPackage = registeredEstudiantesPackage instanceof EstudiantesPackageImpl ? (EstudiantesPackageImpl)registeredEstudiantesPackage : new EstudiantesPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(AsignaturasPackage.eNS_URI);
		AsignaturasPackageImpl theAsignaturasPackage = (AsignaturasPackageImpl)(registeredPackage instanceof AsignaturasPackageImpl ? registeredPackage : AsignaturasPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(WorkspacePackage.eNS_URI);
		WorkspacePackageImpl theWorkspacePackage = (WorkspacePackageImpl)(registeredPackage instanceof WorkspacePackageImpl ? registeredPackage : WorkspacePackage.eINSTANCE);

		// Create package meta-data objects
		theEstudiantesPackage.createPackageContents();
		theAsignaturasPackage.createPackageContents();
		theWorkspacePackage.createPackageContents();

		// Initialize created meta-data
		theEstudiantesPackage.initializePackageContents();
		theAsignaturasPackage.initializePackageContents();
		theWorkspacePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEstudiantesPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EstudiantesPackage.eNS_URI, theEstudiantesPackage);
		return theEstudiantesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRoot() {
		return rootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRoot_Student() {
		return (EReference)rootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStudent() {
		return studentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudent_Id() {
		return (EAttribute)studentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudent_Name() {
		return (EAttribute)studentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudent_StudentPlanInscription() {
		return (EReference)studentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCourseInscription() {
		return courseInscriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCourseInscription_CUCourse() {
		return (EReference)courseInscriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCourseInscription_Date() {
		return (EAttribute)courseInscriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStudentEvaluation() {
		return studentEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStudentEvaluation_Grade() {
		return (EAttribute)studentEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStudentEvaluation_Evaluation() {
		return (EReference)studentEvaluationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlanInscription() {
		return planInscriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlanInscription_Date() {
		return (EAttribute)planInscriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlanInscription_Plan() {
		return (EReference)planInscriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlanInscription_PlanCourseInscription() {
		return (EReference)planInscriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlanInscription_PlanStudentEvaluation() {
		return (EReference)planInscriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlanInscription_Degree() {
		return (EReference)planInscriptionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDegree() {
		return degreeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDegree_Date() {
		return (EAttribute)degreeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EstudiantesFactory getEstudiantesFactory() {
		return (EstudiantesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		rootEClass = createEClass(ROOT);
		createEReference(rootEClass, ROOT__STUDENT);

		studentEClass = createEClass(STUDENT);
		createEAttribute(studentEClass, STUDENT__ID);
		createEAttribute(studentEClass, STUDENT__NAME);
		createEReference(studentEClass, STUDENT__STUDENT_PLAN_INSCRIPTION);

		courseInscriptionEClass = createEClass(COURSE_INSCRIPTION);
		createEReference(courseInscriptionEClass, COURSE_INSCRIPTION__CU_COURSE);
		createEAttribute(courseInscriptionEClass, COURSE_INSCRIPTION__DATE);

		studentEvaluationEClass = createEClass(STUDENT_EVALUATION);
		createEAttribute(studentEvaluationEClass, STUDENT_EVALUATION__GRADE);
		createEReference(studentEvaluationEClass, STUDENT_EVALUATION__EVALUATION);

		planInscriptionEClass = createEClass(PLAN_INSCRIPTION);
		createEAttribute(planInscriptionEClass, PLAN_INSCRIPTION__DATE);
		createEReference(planInscriptionEClass, PLAN_INSCRIPTION__PLAN);
		createEReference(planInscriptionEClass, PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION);
		createEReference(planInscriptionEClass, PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION);
		createEReference(planInscriptionEClass, PLAN_INSCRIPTION__DEGREE);

		degreeEClass = createEClass(DEGREE);
		createEAttribute(degreeEClass, DEGREE__DATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		AsignaturasPackage theAsignaturasPackage = (AsignaturasPackage)EPackage.Registry.INSTANCE.getEPackage(AsignaturasPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(rootEClass, Root.class, "Root", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRoot_Student(), this.getStudent(), null, "Student", null, 1, -1, Root.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(studentEClass, Student.class, "Student", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStudent_Id(), ecorePackage.getEString(), "Id", null, 1, 1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStudent_Name(), ecorePackage.getEString(), "Name", null, 1, 1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudent_StudentPlanInscription(), this.getPlanInscription(), null, "StudentPlanInscription", null, 0, -1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(courseInscriptionEClass, CourseInscription.class, "CourseInscription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCourseInscription_CUCourse(), theAsignaturasPackage.getCourse(), null, "CUCourse", null, 1, 1, CourseInscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCourseInscription_Date(), ecorePackage.getEDate(), "date", null, 1, 1, CourseInscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(studentEvaluationEClass, StudentEvaluation.class, "StudentEvaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStudentEvaluation_Grade(), ecorePackage.getEInt(), "grade", null, 0, 1, StudentEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStudentEvaluation_Evaluation(), theAsignaturasPackage.getEvaluation(), null, "evaluation", null, 1, 1, StudentEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(planInscriptionEClass, PlanInscription.class, "PlanInscription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlanInscription_Date(), ecorePackage.getEDate(), "date", null, 0, 1, PlanInscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlanInscription_Plan(), theAsignaturasPackage.getPlan(), null, "Plan", null, 1, 1, PlanInscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlanInscription_PlanCourseInscription(), this.getCourseInscription(), null, "PlanCourseInscription", null, 0, -1, PlanInscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlanInscription_PlanStudentEvaluation(), this.getStudentEvaluation(), null, "PlanStudentEvaluation", null, 0, -1, PlanInscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlanInscription_Degree(), this.getDegree(), null, "degree", null, 0, 1, PlanInscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(degreeEClass, Degree.class, "Degree", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDegree_Date(), ecorePackage.getEDate(), "date", null, 1, 1, Degree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EstudiantesPackageImpl
