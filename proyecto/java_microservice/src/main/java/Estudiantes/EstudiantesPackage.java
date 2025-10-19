/**
 */
package Estudiantes;

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
 * @see Estudiantes.EstudiantesFactory
 * @model kind="package"
 * @generated
 */
public interface EstudiantesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Estudiantes";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "EstudiantesURI";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "EstudiantesURI";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EstudiantesPackage eINSTANCE = Estudiantes.impl.EstudiantesPackageImpl.init();

	/**
	 * The meta object id for the '{@link Estudiantes.impl.RootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Estudiantes.impl.RootImpl
	 * @see Estudiantes.impl.EstudiantesPackageImpl#getRoot()
	 * @generated
	 */
	int ROOT = 0;

	/**
	 * The feature id for the '<em><b>Student</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__STUDENT = 0;

	/**
	 * The number of structural features of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Estudiantes.impl.StudentImpl <em>Student</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Estudiantes.impl.StudentImpl
	 * @see Estudiantes.impl.EstudiantesPackageImpl#getStudent()
	 * @generated
	 */
	int STUDENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Student Plan Inscription</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__STUDENT_PLAN_INSCRIPTION = 2;

	/**
	 * The number of structural features of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Estudiantes.impl.CourseInscriptionImpl <em>Course Inscription</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Estudiantes.impl.CourseInscriptionImpl
	 * @see Estudiantes.impl.EstudiantesPackageImpl#getCourseInscription()
	 * @generated
	 */
	int COURSE_INSCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>CU Course</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_INSCRIPTION__CU_COURSE = 0;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_INSCRIPTION__DATE = 1;

	/**
	 * The number of structural features of the '<em>Course Inscription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_INSCRIPTION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Course Inscription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_INSCRIPTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Estudiantes.impl.StudentEvaluationImpl <em>Student Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Estudiantes.impl.StudentEvaluationImpl
	 * @see Estudiantes.impl.EstudiantesPackageImpl#getStudentEvaluation()
	 * @generated
	 */
	int STUDENT_EVALUATION = 3;

	/**
	 * The feature id for the '<em><b>Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_EVALUATION__GRADE = 0;

	/**
	 * The feature id for the '<em><b>Evaluation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_EVALUATION__EVALUATION = 1;

	/**
	 * The number of structural features of the '<em>Student Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_EVALUATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Student Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_EVALUATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Estudiantes.impl.PlanInscriptionImpl <em>Plan Inscription</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Estudiantes.impl.PlanInscriptionImpl
	 * @see Estudiantes.impl.EstudiantesPackageImpl#getPlanInscription()
	 * @generated
	 */
	int PLAN_INSCRIPTION = 4;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_INSCRIPTION__DATE = 0;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_INSCRIPTION__PLAN = 1;

	/**
	 * The feature id for the '<em><b>Plan Course Inscription</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Plan Student Evaluation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION = 3;

	/**
	 * The feature id for the '<em><b>Degree</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_INSCRIPTION__DEGREE = 4;

	/**
	 * The number of structural features of the '<em>Plan Inscription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_INSCRIPTION_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Plan Inscription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_INSCRIPTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Estudiantes.impl.DegreeImpl <em>Degree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Estudiantes.impl.DegreeImpl
	 * @see Estudiantes.impl.EstudiantesPackageImpl#getDegree()
	 * @generated
	 */
	int DEGREE = 5;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEGREE__DATE = 0;

	/**
	 * The number of structural features of the '<em>Degree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEGREE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Degree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEGREE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link Estudiantes.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see Estudiantes.Root
	 * @generated
	 */
	EClass getRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link Estudiantes.Root#getStudent <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Student</em>'.
	 * @see Estudiantes.Root#getStudent()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_Student();

	/**
	 * Returns the meta object for class '{@link Estudiantes.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student</em>'.
	 * @see Estudiantes.Student
	 * @generated
	 */
	EClass getStudent();

	/**
	 * Returns the meta object for the attribute '{@link Estudiantes.Student#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see Estudiantes.Student#getId()
	 * @see #getStudent()
	 * @generated
	 */
	EAttribute getStudent_Id();

	/**
	 * Returns the meta object for the attribute '{@link Estudiantes.Student#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Estudiantes.Student#getName()
	 * @see #getStudent()
	 * @generated
	 */
	EAttribute getStudent_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link Estudiantes.Student#getStudentPlanInscription <em>Student Plan Inscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Student Plan Inscription</em>'.
	 * @see Estudiantes.Student#getStudentPlanInscription()
	 * @see #getStudent()
	 * @generated
	 */
	EReference getStudent_StudentPlanInscription();

	/**
	 * Returns the meta object for class '{@link Estudiantes.CourseInscription <em>Course Inscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Course Inscription</em>'.
	 * @see Estudiantes.CourseInscription
	 * @generated
	 */
	EClass getCourseInscription();

	/**
	 * Returns the meta object for the reference '{@link Estudiantes.CourseInscription#getCUCourse <em>CU Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>CU Course</em>'.
	 * @see Estudiantes.CourseInscription#getCUCourse()
	 * @see #getCourseInscription()
	 * @generated
	 */
	EReference getCourseInscription_CUCourse();

	/**
	 * Returns the meta object for the attribute '{@link Estudiantes.CourseInscription#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see Estudiantes.CourseInscription#getDate()
	 * @see #getCourseInscription()
	 * @generated
	 */
	EAttribute getCourseInscription_Date();

	/**
	 * Returns the meta object for class '{@link Estudiantes.StudentEvaluation <em>Student Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student Evaluation</em>'.
	 * @see Estudiantes.StudentEvaluation
	 * @generated
	 */
	EClass getStudentEvaluation();

	/**
	 * Returns the meta object for the attribute '{@link Estudiantes.StudentEvaluation#getGrade <em>Grade</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grade</em>'.
	 * @see Estudiantes.StudentEvaluation#getGrade()
	 * @see #getStudentEvaluation()
	 * @generated
	 */
	EAttribute getStudentEvaluation_Grade();

	/**
	 * Returns the meta object for the reference '{@link Estudiantes.StudentEvaluation#getEvaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Evaluation</em>'.
	 * @see Estudiantes.StudentEvaluation#getEvaluation()
	 * @see #getStudentEvaluation()
	 * @generated
	 */
	EReference getStudentEvaluation_Evaluation();

	/**
	 * Returns the meta object for class '{@link Estudiantes.PlanInscription <em>Plan Inscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plan Inscription</em>'.
	 * @see Estudiantes.PlanInscription
	 * @generated
	 */
	EClass getPlanInscription();

	/**
	 * Returns the meta object for the attribute '{@link Estudiantes.PlanInscription#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see Estudiantes.PlanInscription#getDate()
	 * @see #getPlanInscription()
	 * @generated
	 */
	EAttribute getPlanInscription_Date();

	/**
	 * Returns the meta object for the reference '{@link Estudiantes.PlanInscription#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Plan</em>'.
	 * @see Estudiantes.PlanInscription#getPlan()
	 * @see #getPlanInscription()
	 * @generated
	 */
	EReference getPlanInscription_Plan();

	/**
	 * Returns the meta object for the containment reference list '{@link Estudiantes.PlanInscription#getPlanCourseInscription <em>Plan Course Inscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Plan Course Inscription</em>'.
	 * @see Estudiantes.PlanInscription#getPlanCourseInscription()
	 * @see #getPlanInscription()
	 * @generated
	 */
	EReference getPlanInscription_PlanCourseInscription();

	/**
	 * Returns the meta object for the containment reference list '{@link Estudiantes.PlanInscription#getPlanStudentEvaluation <em>Plan Student Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Plan Student Evaluation</em>'.
	 * @see Estudiantes.PlanInscription#getPlanStudentEvaluation()
	 * @see #getPlanInscription()
	 * @generated
	 */
	EReference getPlanInscription_PlanStudentEvaluation();

	/**
	 * Returns the meta object for the containment reference '{@link Estudiantes.PlanInscription#getDegree <em>Degree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Degree</em>'.
	 * @see Estudiantes.PlanInscription#getDegree()
	 * @see #getPlanInscription()
	 * @generated
	 */
	EReference getPlanInscription_Degree();

	/**
	 * Returns the meta object for class '{@link Estudiantes.Degree <em>Degree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Degree</em>'.
	 * @see Estudiantes.Degree
	 * @generated
	 */
	EClass getDegree();

	/**
	 * Returns the meta object for the attribute '{@link Estudiantes.Degree#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see Estudiantes.Degree#getDate()
	 * @see #getDegree()
	 * @generated
	 */
	EAttribute getDegree_Date();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EstudiantesFactory getEstudiantesFactory();

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
		 * The meta object literal for the '{@link Estudiantes.impl.RootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Estudiantes.impl.RootImpl
		 * @see Estudiantes.impl.EstudiantesPackageImpl#getRoot()
		 * @generated
		 */
		EClass ROOT = eINSTANCE.getRoot();

		/**
		 * The meta object literal for the '<em><b>Student</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__STUDENT = eINSTANCE.getRoot_Student();

		/**
		 * The meta object literal for the '{@link Estudiantes.impl.StudentImpl <em>Student</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Estudiantes.impl.StudentImpl
		 * @see Estudiantes.impl.EstudiantesPackageImpl#getStudent()
		 * @generated
		 */
		EClass STUDENT = eINSTANCE.getStudent();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT__ID = eINSTANCE.getStudent_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT__NAME = eINSTANCE.getStudent_Name();

		/**
		 * The meta object literal for the '<em><b>Student Plan Inscription</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT__STUDENT_PLAN_INSCRIPTION = eINSTANCE.getStudent_StudentPlanInscription();

		/**
		 * The meta object literal for the '{@link Estudiantes.impl.CourseInscriptionImpl <em>Course Inscription</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Estudiantes.impl.CourseInscriptionImpl
		 * @see Estudiantes.impl.EstudiantesPackageImpl#getCourseInscription()
		 * @generated
		 */
		EClass COURSE_INSCRIPTION = eINSTANCE.getCourseInscription();

		/**
		 * The meta object literal for the '<em><b>CU Course</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COURSE_INSCRIPTION__CU_COURSE = eINSTANCE.getCourseInscription_CUCourse();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COURSE_INSCRIPTION__DATE = eINSTANCE.getCourseInscription_Date();

		/**
		 * The meta object literal for the '{@link Estudiantes.impl.StudentEvaluationImpl <em>Student Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Estudiantes.impl.StudentEvaluationImpl
		 * @see Estudiantes.impl.EstudiantesPackageImpl#getStudentEvaluation()
		 * @generated
		 */
		EClass STUDENT_EVALUATION = eINSTANCE.getStudentEvaluation();

		/**
		 * The meta object literal for the '<em><b>Grade</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_EVALUATION__GRADE = eINSTANCE.getStudentEvaluation_Grade();

		/**
		 * The meta object literal for the '<em><b>Evaluation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_EVALUATION__EVALUATION = eINSTANCE.getStudentEvaluation_Evaluation();

		/**
		 * The meta object literal for the '{@link Estudiantes.impl.PlanInscriptionImpl <em>Plan Inscription</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Estudiantes.impl.PlanInscriptionImpl
		 * @see Estudiantes.impl.EstudiantesPackageImpl#getPlanInscription()
		 * @generated
		 */
		EClass PLAN_INSCRIPTION = eINSTANCE.getPlanInscription();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN_INSCRIPTION__DATE = eINSTANCE.getPlanInscription_Date();

		/**
		 * The meta object literal for the '<em><b>Plan</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN_INSCRIPTION__PLAN = eINSTANCE.getPlanInscription_Plan();

		/**
		 * The meta object literal for the '<em><b>Plan Course Inscription</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION = eINSTANCE.getPlanInscription_PlanCourseInscription();

		/**
		 * The meta object literal for the '<em><b>Plan Student Evaluation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION = eINSTANCE.getPlanInscription_PlanStudentEvaluation();

		/**
		 * The meta object literal for the '<em><b>Degree</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN_INSCRIPTION__DEGREE = eINSTANCE.getPlanInscription_Degree();

		/**
		 * The meta object literal for the '{@link Estudiantes.impl.DegreeImpl <em>Degree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Estudiantes.impl.DegreeImpl
		 * @see Estudiantes.impl.EstudiantesPackageImpl#getDegree()
		 * @generated
		 */
		EClass DEGREE = eINSTANCE.getDegree();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEGREE__DATE = eINSTANCE.getDegree_Date();

	}

} //EstudiantesPackage
