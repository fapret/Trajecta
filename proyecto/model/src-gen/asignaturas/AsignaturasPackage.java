/**
 */
package asignaturas;

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
 * @see asignaturas.AsignaturasFactory
 * @model kind="package"
 * @generated
 */
public interface AsignaturasPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "asignaturas";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "asignaturasURI";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "asignaturas";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AsignaturasPackage eINSTANCE = asignaturas.impl.AsignaturasPackageImpl.init();

	/**
	 * The meta object id for the '{@link asignaturas.impl.RootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.RootImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getRoot()
	 * @generated
	 */
	int ROOT = 0;

	/**
	 * The feature id for the '<em><b>Faculty</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__FACULTY = 0;

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
	 * The meta object id for the '{@link asignaturas.impl.CareerImpl <em>Career</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CareerImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCareer()
	 * @generated
	 */
	int CAREER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAREER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAREER__PLAN = 1;

	/**
	 * The number of structural features of the '<em>Career</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAREER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Career</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAREER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.PlanImpl <em>Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.PlanImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getPlan()
	 * @generated
	 */
	int PLAN = 4;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__YEAR = 0;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__VALID = 1;

	/**
	 * The feature id for the '<em><b>Career parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__CAREER_PARENT = 2;

	/**
	 * The feature id for the '<em><b>Gradescale</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__GRADESCALE = 3;

	/**
	 * The number of structural features of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.CreditsPlanImpl <em>Credits Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CreditsPlanImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCreditsPlan()
	 * @generated
	 */
	int CREDITS_PLAN = 2;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN__YEAR = PLAN__YEAR;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN__VALID = PLAN__VALID;

	/**
	 * The feature id for the '<em><b>Career parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN__CAREER_PARENT = PLAN__CAREER_PARENT;

	/**
	 * The feature id for the '<em><b>Gradescale</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN__GRADESCALE = PLAN__GRADESCALE;

	/**
	 * The feature id for the '<em><b>Min Credits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN__MIN_CREDITS = PLAN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Group Of Subjects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN__GROUP_OF_SUBJECTS = PLAN_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Credits Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN_FEATURE_COUNT = PLAN_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Credits Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_PLAN_OPERATION_COUNT = PLAN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.FacultyImpl <em>Faculty</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.FacultyImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getFaculty()
	 * @generated
	 */
	int FACULTY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACULTY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Careers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACULTY__CAREERS = 1;

	/**
	 * The feature id for the '<em><b>Faculty CU</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACULTY__FACULTY_CU = 2;

	/**
	 * The feature id for the '<em><b>Gradescale</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACULTY__GRADESCALE = 3;

	/**
	 * The number of structural features of the '<em>Faculty</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACULTY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Faculty</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACULTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.SubjectImpl <em>Subject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.SubjectImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getSubject()
	 * @generated
	 */
	int SUBJECT = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Min Credits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__MIN_CREDITS = 2;

	/**
	 * The feature id for the '<em><b>Group Of Subjects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__GROUP_OF_SUBJECTS = 3;

	/**
	 * The feature id for the '<em><b>Group Of Subjects Opposite</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE = 4;

	/**
	 * The feature id for the '<em><b>Subject Curricular Unit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__SUBJECT_CURRICULAR_UNIT = 5;

	/**
	 * The number of structural features of the '<em>Subject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Subject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.CurricularUnitImpl <em>Curricular Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CurricularUnitImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCurricularUnit()
	 * @generated
	 */
	int CURRICULAR_UNIT = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Cred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__CRED = 2;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__VALID = 3;

	/**
	 * The feature id for the '<em><b>Requirement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__REQUIREMENT = 4;

	/**
	 * The feature id for the '<em><b>Course</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__COURSE = 5;

	/**
	 * The feature id for the '<em><b>Exam Evaluation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__EXAM_EVALUATION = 6;

	/**
	 * The feature id for the '<em><b>Gradescale</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT__GRADESCALE = 7;

	/**
	 * The number of structural features of the '<em>Curricular Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Curricular Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRICULAR_UNIT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.RequirementImpl <em>Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.RequirementImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getRequirement()
	 * @generated
	 */
	int REQUIREMENT = 8;

	/**
	 * The number of structural features of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.SomeOfImpl <em>Some Of</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.SomeOfImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getSomeOf()
	 * @generated
	 */
	int SOME_OF = 7;

	/**
	 * The feature id for the '<em><b>N</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_OF__N = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Requirement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_OF__REQUIREMENT = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Some Of</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_OF_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Some Of</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_OF_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.NOTImpl <em>NOT</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.NOTImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getNOT()
	 * @generated
	 */
	int NOT = 9;

	/**
	 * The feature id for the '<em><b>Requirement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT__REQUIREMENT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>NOT</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>NOT</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.CoursedImpl <em>Coursed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CoursedImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCoursed()
	 * @generated
	 */
	int COURSED = 10;

	/**
	 * The feature id for the '<em><b>Curricular Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSED__CURRICULAR_UNIT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Coursed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSED_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Coursed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSED_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.ExamImpl <em>Exam</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.ExamImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getExam()
	 * @generated
	 */
	int EXAM = 11;

	/**
	 * The feature id for the '<em><b>Curricular Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAM__CURRICULAR_UNIT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exam</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAM_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Exam</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAM_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.CreditsOnPlanImpl <em>Credits On Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CreditsOnPlanImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCreditsOnPlan()
	 * @generated
	 */
	int CREDITS_ON_PLAN = 12;

	/**
	 * The feature id for the '<em><b>Cred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_PLAN__CRED = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Credits Plan</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_PLAN__CREDITS_PLAN = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Credits On Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_PLAN_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Credits On Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_PLAN_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.CreditsOnSubjectImpl <em>Credits On Subject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CreditsOnSubjectImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCreditsOnSubject()
	 * @generated
	 */
	int CREDITS_ON_SUBJECT = 13;

	/**
	 * The feature id for the '<em><b>Cred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_SUBJECT__CRED = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Group Of Subjects</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Credits On Subject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_SUBJECT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Credits On Subject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREDITS_ON_SUBJECT_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.SubjectPlanImpl <em>Subject Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.SubjectPlanImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getSubjectPlan()
	 * @generated
	 */
	int SUBJECT_PLAN = 14;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_PLAN__YEAR = PLAN__YEAR;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_PLAN__VALID = PLAN__VALID;

	/**
	 * The feature id for the '<em><b>Career parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_PLAN__CAREER_PARENT = PLAN__CAREER_PARENT;

	/**
	 * The feature id for the '<em><b>Gradescale</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_PLAN__GRADESCALE = PLAN__GRADESCALE;

	/**
	 * The feature id for the '<em><b>Curricular Unit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_PLAN__CURRICULAR_UNIT = PLAN_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Subject Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_PLAN_FEATURE_COUNT = PLAN_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Subject Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_PLAN_OPERATION_COUNT = PLAN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.RegisteredToImpl <em>Registered To</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.RegisteredToImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getRegisteredTo()
	 * @generated
	 */
	int REGISTERED_TO = 15;

	/**
	 * The feature id for the '<em><b>Curricular Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_TO__CURRICULAR_UNIT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Registered To</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_TO_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Registered To</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTERED_TO_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.CourseImpl <em>Course</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CourseImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCourse()
	 * @generated
	 */
	int COURSE = 16;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE__YEAR = 0;

	/**
	 * The feature id for the '<em><b>Edition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE__EDITION = 1;

	/**
	 * The feature id for the '<em><b>Course Evaluation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE__COURSE_EVALUATION = 2;

	/**
	 * The feature id for the '<em><b>Curricularunit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE__CURRICULARUNIT = 3;

	/**
	 * The feature id for the '<em><b>Partialevaluation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE__PARTIALEVALUATION = 4;

	/**
	 * The feature id for the '<em><b>Tutoringevaluation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE__TUTORINGEVALUATION = 5;

	/**
	 * The number of structural features of the '<em>Course</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Course</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.EvaluationImpl <em>Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.EvaluationImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getEvaluation()
	 * @generated
	 */
	int EVALUATION = 17;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__DATE = 0;

	/**
	 * The number of structural features of the '<em>Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.ExamEvaluationImpl <em>Exam Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.ExamEvaluationImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getExamEvaluation()
	 * @generated
	 */
	int EXAM_EVALUATION = 18;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAM_EVALUATION__DATE = EVALUATION__DATE;

	/**
	 * The feature id for the '<em><b>Curricularunit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAM_EVALUATION__CURRICULARUNIT = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exam Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAM_EVALUATION_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Exam Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXAM_EVALUATION_OPERATION_COUNT = EVALUATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.EvaluacionExamenImpl <em>Evaluacion Examen</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.EvaluacionExamenImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getEvaluacionExamen()
	 * @generated
	 */
	int EVALUACION_EXAMEN = 19;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUACION_EXAMEN__DATE = EVALUATION__DATE;

	/**
	 * The number of structural features of the '<em>Evaluacion Examen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUACION_EXAMEN_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Evaluacion Examen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUACION_EXAMEN_OPERATION_COUNT = EVALUATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.CourseEvaluationImpl <em>Course Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.CourseEvaluationImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getCourseEvaluation()
	 * @generated
	 */
	int COURSE_EVALUATION = 20;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_EVALUATION__DATE = EVALUATION__DATE;

	/**
	 * The feature id for the '<em><b>Course</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_EVALUATION__COURSE = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Course Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_EVALUATION_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Course Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COURSE_EVALUATION_OPERATION_COUNT = EVALUATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.PartialEvaluationImpl <em>Partial Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.PartialEvaluationImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getPartialEvaluation()
	 * @generated
	 */
	int PARTIAL_EVALUATION = 21;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTIAL_EVALUATION__DATE = EVALUATION__DATE;

	/**
	 * The feature id for the '<em><b>Course</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTIAL_EVALUATION__COURSE = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Partial Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTIAL_EVALUATION_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Partial Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTIAL_EVALUATION_OPERATION_COUNT = EVALUATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.TutoringEvaluationImpl <em>Tutoring Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.TutoringEvaluationImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getTutoringEvaluation()
	 * @generated
	 */
	int TUTORING_EVALUATION = 22;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTORING_EVALUATION__DATE = EVALUATION__DATE;

	/**
	 * The feature id for the '<em><b>Course</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTORING_EVALUATION__COURSE = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tutoring Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTORING_EVALUATION_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tutoring Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUTORING_EVALUATION_OPERATION_COUNT = EVALUATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link asignaturas.impl.GradeScaleImpl <em>Grade Scale</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asignaturas.impl.GradeScaleImpl
	 * @see asignaturas.impl.AsignaturasPackageImpl#getGradeScale()
	 * @generated
	 */
	int GRADE_SCALE = 23;

	/**
	 * The feature id for the '<em><b>Exam Approval Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE__EXAM_APPROVAL_GRADE = 0;

	/**
	 * The feature id for the '<em><b>Tutoring Approval Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE__TUTORING_APPROVAL_GRADE = 1;

	/**
	 * The feature id for the '<em><b>Course Approval Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE__COURSE_APPROVAL_GRADE = 2;

	/**
	 * The feature id for the '<em><b>Course Partial Approval Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE = 3;

	/**
	 * The feature id for the '<em><b>Partial Approval Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE__PARTIAL_APPROVAL_GRADE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE__NAME = 5;

	/**
	 * The number of structural features of the '<em>Grade Scale</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Grade Scale</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRADE_SCALE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link asignaturas.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see asignaturas.Root
	 * @generated
	 */
	EClass getRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Root#getFaculty <em>Faculty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Faculty</em>'.
	 * @see asignaturas.Root#getFaculty()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_Faculty();

	/**
	 * Returns the meta object for class '{@link asignaturas.Career <em>Career</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Career</em>'.
	 * @see asignaturas.Career
	 * @generated
	 */
	EClass getCareer();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Career#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see asignaturas.Career#getName()
	 * @see #getCareer()
	 * @generated
	 */
	EAttribute getCareer_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Career#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Plan</em>'.
	 * @see asignaturas.Career#getPlan()
	 * @see #getCareer()
	 * @generated
	 */
	EReference getCareer_Plan();

	/**
	 * Returns the meta object for class '{@link asignaturas.CreditsPlan <em>Credits Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Credits Plan</em>'.
	 * @see asignaturas.CreditsPlan
	 * @generated
	 */
	EClass getCreditsPlan();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.CreditsPlan#getMinCredits <em>Min Credits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Credits</em>'.
	 * @see asignaturas.CreditsPlan#getMinCredits()
	 * @see #getCreditsPlan()
	 * @generated
	 */
	EAttribute getCreditsPlan_MinCredits();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.CreditsPlan#getGroupOfSubjects <em>Group Of Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Group Of Subjects</em>'.
	 * @see asignaturas.CreditsPlan#getGroupOfSubjects()
	 * @see #getCreditsPlan()
	 * @generated
	 */
	EReference getCreditsPlan_GroupOfSubjects();

	/**
	 * Returns the meta object for class '{@link asignaturas.Faculty <em>Faculty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Faculty</em>'.
	 * @see asignaturas.Faculty
	 * @generated
	 */
	EClass getFaculty();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Faculty#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see asignaturas.Faculty#getName()
	 * @see #getFaculty()
	 * @generated
	 */
	EAttribute getFaculty_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Faculty#getCareers <em>Careers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Careers</em>'.
	 * @see asignaturas.Faculty#getCareers()
	 * @see #getFaculty()
	 * @generated
	 */
	EReference getFaculty_Careers();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Faculty#getFacultyCU <em>Faculty CU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Faculty CU</em>'.
	 * @see asignaturas.Faculty#getFacultyCU()
	 * @see #getFaculty()
	 * @generated
	 */
	EReference getFaculty_FacultyCU();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Faculty#getGradescale <em>Gradescale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gradescale</em>'.
	 * @see asignaturas.Faculty#getGradescale()
	 * @see #getFaculty()
	 * @generated
	 */
	EReference getFaculty_Gradescale();

	/**
	 * Returns the meta object for class '{@link asignaturas.Plan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plan</em>'.
	 * @see asignaturas.Plan
	 * @generated
	 */
	EClass getPlan();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Plan#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Year</em>'.
	 * @see asignaturas.Plan#getYear()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_Year();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Plan#isValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid</em>'.
	 * @see asignaturas.Plan#isValid()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_Valid();

	/**
	 * Returns the meta object for the container reference '{@link asignaturas.Plan#getCareer_parent <em>Career parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Career parent</em>'.
	 * @see asignaturas.Plan#getCareer_parent()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Career_parent();

	/**
	 * Returns the meta object for the reference '{@link asignaturas.Plan#getGradescale <em>Gradescale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gradescale</em>'.
	 * @see asignaturas.Plan#getGradescale()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Gradescale();

	/**
	 * Returns the meta object for class '{@link asignaturas.Subject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subject</em>'.
	 * @see asignaturas.Subject
	 * @generated
	 */
	EClass getSubject();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Subject#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see asignaturas.Subject#getId()
	 * @see #getSubject()
	 * @generated
	 */
	EAttribute getSubject_Id();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Subject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see asignaturas.Subject#getName()
	 * @see #getSubject()
	 * @generated
	 */
	EAttribute getSubject_Name();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Subject#getMinCredits <em>Min Credits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Credits</em>'.
	 * @see asignaturas.Subject#getMinCredits()
	 * @see #getSubject()
	 * @generated
	 */
	EAttribute getSubject_MinCredits();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Subject#getGroupOfSubjects <em>Group Of Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Group Of Subjects</em>'.
	 * @see asignaturas.Subject#getGroupOfSubjects()
	 * @see #getSubject()
	 * @generated
	 */
	EReference getSubject_GroupOfSubjects();

	/**
	 * Returns the meta object for the container reference '{@link asignaturas.Subject#getGroupOfSubjectsOpposite <em>Group Of Subjects Opposite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Group Of Subjects Opposite</em>'.
	 * @see asignaturas.Subject#getGroupOfSubjectsOpposite()
	 * @see #getSubject()
	 * @generated
	 */
	EReference getSubject_GroupOfSubjectsOpposite();

	/**
	 * Returns the meta object for the reference list '{@link asignaturas.Subject#getSubjectCurricularUnit <em>Subject Curricular Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Subject Curricular Unit</em>'.
	 * @see asignaturas.Subject#getSubjectCurricularUnit()
	 * @see #getSubject()
	 * @generated
	 */
	EReference getSubject_SubjectCurricularUnit();

	/**
	 * Returns the meta object for class '{@link asignaturas.CurricularUnit <em>Curricular Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Curricular Unit</em>'.
	 * @see asignaturas.CurricularUnit
	 * @generated
	 */
	EClass getCurricularUnit();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.CurricularUnit#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see asignaturas.CurricularUnit#getId()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EAttribute getCurricularUnit_Id();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.CurricularUnit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see asignaturas.CurricularUnit#getName()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EAttribute getCurricularUnit_Name();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.CurricularUnit#getCred <em>Cred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cred</em>'.
	 * @see asignaturas.CurricularUnit#getCred()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EAttribute getCurricularUnit_Cred();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.CurricularUnit#isValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid</em>'.
	 * @see asignaturas.CurricularUnit#isValid()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EAttribute getCurricularUnit_Valid();

	/**
	 * Returns the meta object for the containment reference '{@link asignaturas.CurricularUnit#getRequirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Requirement</em>'.
	 * @see asignaturas.CurricularUnit#getRequirement()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EReference getCurricularUnit_Requirement();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.CurricularUnit#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Course</em>'.
	 * @see asignaturas.CurricularUnit#getCourse()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EReference getCurricularUnit_Course();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.CurricularUnit#getExamEvaluation <em>Exam Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exam Evaluation</em>'.
	 * @see asignaturas.CurricularUnit#getExamEvaluation()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EReference getCurricularUnit_ExamEvaluation();

	/**
	 * Returns the meta object for the reference '{@link asignaturas.CurricularUnit#getGradescale <em>Gradescale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gradescale</em>'.
	 * @see asignaturas.CurricularUnit#getGradescale()
	 * @see #getCurricularUnit()
	 * @generated
	 */
	EReference getCurricularUnit_Gradescale();

	/**
	 * Returns the meta object for class '{@link asignaturas.SomeOf <em>Some Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Some Of</em>'.
	 * @see asignaturas.SomeOf
	 * @generated
	 */
	EClass getSomeOf();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.SomeOf#getN <em>N</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>N</em>'.
	 * @see asignaturas.SomeOf#getN()
	 * @see #getSomeOf()
	 * @generated
	 */
	EAttribute getSomeOf_N();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.SomeOf#getRequirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirement</em>'.
	 * @see asignaturas.SomeOf#getRequirement()
	 * @see #getSomeOf()
	 * @generated
	 */
	EReference getSomeOf_Requirement();

	/**
	 * Returns the meta object for class '{@link asignaturas.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement</em>'.
	 * @see asignaturas.Requirement
	 * @generated
	 */
	EClass getRequirement();

	/**
	 * Returns the meta object for class '{@link asignaturas.NOT <em>NOT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>NOT</em>'.
	 * @see asignaturas.NOT
	 * @generated
	 */
	EClass getNOT();

	/**
	 * Returns the meta object for the containment reference '{@link asignaturas.NOT#getRequirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Requirement</em>'.
	 * @see asignaturas.NOT#getRequirement()
	 * @see #getNOT()
	 * @generated
	 */
	EReference getNOT_Requirement();

	/**
	 * Returns the meta object for class '{@link asignaturas.Coursed <em>Coursed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Coursed</em>'.
	 * @see asignaturas.Coursed
	 * @generated
	 */
	EClass getCoursed();

	/**
	 * Returns the meta object for the reference '{@link asignaturas.Coursed#getCurricularUnit <em>Curricular Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Curricular Unit</em>'.
	 * @see asignaturas.Coursed#getCurricularUnit()
	 * @see #getCoursed()
	 * @generated
	 */
	EReference getCoursed_CurricularUnit();

	/**
	 * Returns the meta object for class '{@link asignaturas.Exam <em>Exam</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exam</em>'.
	 * @see asignaturas.Exam
	 * @generated
	 */
	EClass getExam();

	/**
	 * Returns the meta object for the reference '{@link asignaturas.Exam#getCurricularUnit <em>Curricular Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Curricular Unit</em>'.
	 * @see asignaturas.Exam#getCurricularUnit()
	 * @see #getExam()
	 * @generated
	 */
	EReference getExam_CurricularUnit();

	/**
	 * Returns the meta object for class '{@link asignaturas.CreditsOnPlan <em>Credits On Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Credits On Plan</em>'.
	 * @see asignaturas.CreditsOnPlan
	 * @generated
	 */
	EClass getCreditsOnPlan();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.CreditsOnPlan#getCred <em>Cred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cred</em>'.
	 * @see asignaturas.CreditsOnPlan#getCred()
	 * @see #getCreditsOnPlan()
	 * @generated
	 */
	EAttribute getCreditsOnPlan_Cred();

	/**
	 * Returns the meta object for the reference '{@link asignaturas.CreditsOnPlan#getCreditsPlan <em>Credits Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Credits Plan</em>'.
	 * @see asignaturas.CreditsOnPlan#getCreditsPlan()
	 * @see #getCreditsOnPlan()
	 * @generated
	 */
	EReference getCreditsOnPlan_CreditsPlan();

	/**
	 * Returns the meta object for class '{@link asignaturas.CreditsOnSubject <em>Credits On Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Credits On Subject</em>'.
	 * @see asignaturas.CreditsOnSubject
	 * @generated
	 */
	EClass getCreditsOnSubject();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.CreditsOnSubject#getCred <em>Cred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cred</em>'.
	 * @see asignaturas.CreditsOnSubject#getCred()
	 * @see #getCreditsOnSubject()
	 * @generated
	 */
	EAttribute getCreditsOnSubject_Cred();

	/**
	 * Returns the meta object for the reference '{@link asignaturas.CreditsOnSubject#getGroupOfSubjects <em>Group Of Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Group Of Subjects</em>'.
	 * @see asignaturas.CreditsOnSubject#getGroupOfSubjects()
	 * @see #getCreditsOnSubject()
	 * @generated
	 */
	EReference getCreditsOnSubject_GroupOfSubjects();

	/**
	 * Returns the meta object for class '{@link asignaturas.SubjectPlan <em>Subject Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subject Plan</em>'.
	 * @see asignaturas.SubjectPlan
	 * @generated
	 */
	EClass getSubjectPlan();

	/**
	 * Returns the meta object for the reference list '{@link asignaturas.SubjectPlan#getCurricularUnit <em>Curricular Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Curricular Unit</em>'.
	 * @see asignaturas.SubjectPlan#getCurricularUnit()
	 * @see #getSubjectPlan()
	 * @generated
	 */
	EReference getSubjectPlan_CurricularUnit();

	/**
	 * Returns the meta object for class '{@link asignaturas.RegisteredTo <em>Registered To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Registered To</em>'.
	 * @see asignaturas.RegisteredTo
	 * @generated
	 */
	EClass getRegisteredTo();

	/**
	 * Returns the meta object for the reference '{@link asignaturas.RegisteredTo#getCurricularUnit <em>Curricular Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Curricular Unit</em>'.
	 * @see asignaturas.RegisteredTo#getCurricularUnit()
	 * @see #getRegisteredTo()
	 * @generated
	 */
	EReference getRegisteredTo_CurricularUnit();

	/**
	 * Returns the meta object for class '{@link asignaturas.Course <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Course</em>'.
	 * @see asignaturas.Course
	 * @generated
	 */
	EClass getCourse();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Course#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Year</em>'.
	 * @see asignaturas.Course#getYear()
	 * @see #getCourse()
	 * @generated
	 */
	EAttribute getCourse_Year();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Course#getEdition <em>Edition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Edition</em>'.
	 * @see asignaturas.Course#getEdition()
	 * @see #getCourse()
	 * @generated
	 */
	EAttribute getCourse_Edition();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Course#getCourseEvaluation <em>Course Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Course Evaluation</em>'.
	 * @see asignaturas.Course#getCourseEvaluation()
	 * @see #getCourse()
	 * @generated
	 */
	EReference getCourse_CourseEvaluation();

	/**
	 * Returns the meta object for the container reference '{@link asignaturas.Course#getCurricularunit <em>Curricularunit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Curricularunit</em>'.
	 * @see asignaturas.Course#getCurricularunit()
	 * @see #getCourse()
	 * @generated
	 */
	EReference getCourse_Curricularunit();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Course#getPartialevaluation <em>Partialevaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Partialevaluation</em>'.
	 * @see asignaturas.Course#getPartialevaluation()
	 * @see #getCourse()
	 * @generated
	 */
	EReference getCourse_Partialevaluation();

	/**
	 * Returns the meta object for the containment reference list '{@link asignaturas.Course#getTutoringevaluation <em>Tutoringevaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tutoringevaluation</em>'.
	 * @see asignaturas.Course#getTutoringevaluation()
	 * @see #getCourse()
	 * @generated
	 */
	EReference getCourse_Tutoringevaluation();

	/**
	 * Returns the meta object for class '{@link asignaturas.Evaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation</em>'.
	 * @see asignaturas.Evaluation
	 * @generated
	 */
	EClass getEvaluation();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.Evaluation#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see asignaturas.Evaluation#getDate()
	 * @see #getEvaluation()
	 * @generated
	 */
	EAttribute getEvaluation_Date();

	/**
	 * Returns the meta object for class '{@link asignaturas.ExamEvaluation <em>Exam Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exam Evaluation</em>'.
	 * @see asignaturas.ExamEvaluation
	 * @generated
	 */
	EClass getExamEvaluation();

	/**
	 * Returns the meta object for the container reference '{@link asignaturas.ExamEvaluation#getCurricularunit <em>Curricularunit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Curricularunit</em>'.
	 * @see asignaturas.ExamEvaluation#getCurricularunit()
	 * @see #getExamEvaluation()
	 * @generated
	 */
	EReference getExamEvaluation_Curricularunit();

	/**
	 * Returns the meta object for class '{@link asignaturas.EvaluacionExamen <em>Evaluacion Examen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluacion Examen</em>'.
	 * @see asignaturas.EvaluacionExamen
	 * @generated
	 */
	EClass getEvaluacionExamen();

	/**
	 * Returns the meta object for class '{@link asignaturas.CourseEvaluation <em>Course Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Course Evaluation</em>'.
	 * @see asignaturas.CourseEvaluation
	 * @generated
	 */
	EClass getCourseEvaluation();

	/**
	 * Returns the meta object for the container reference '{@link asignaturas.CourseEvaluation#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Course</em>'.
	 * @see asignaturas.CourseEvaluation#getCourse()
	 * @see #getCourseEvaluation()
	 * @generated
	 */
	EReference getCourseEvaluation_Course();

	/**
	 * Returns the meta object for class '{@link asignaturas.PartialEvaluation <em>Partial Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Partial Evaluation</em>'.
	 * @see asignaturas.PartialEvaluation
	 * @generated
	 */
	EClass getPartialEvaluation();

	/**
	 * Returns the meta object for the container reference '{@link asignaturas.PartialEvaluation#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Course</em>'.
	 * @see asignaturas.PartialEvaluation#getCourse()
	 * @see #getPartialEvaluation()
	 * @generated
	 */
	EReference getPartialEvaluation_Course();

	/**
	 * Returns the meta object for class '{@link asignaturas.TutoringEvaluation <em>Tutoring Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tutoring Evaluation</em>'.
	 * @see asignaturas.TutoringEvaluation
	 * @generated
	 */
	EClass getTutoringEvaluation();

	/**
	 * Returns the meta object for the container reference '{@link asignaturas.TutoringEvaluation#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Course</em>'.
	 * @see asignaturas.TutoringEvaluation#getCourse()
	 * @see #getTutoringEvaluation()
	 * @generated
	 */
	EReference getTutoringEvaluation_Course();

	/**
	 * Returns the meta object for class '{@link asignaturas.GradeScale <em>Grade Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grade Scale</em>'.
	 * @see asignaturas.GradeScale
	 * @generated
	 */
	EClass getGradeScale();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.GradeScale#getExamApprovalGrade <em>Exam Approval Grade</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exam Approval Grade</em>'.
	 * @see asignaturas.GradeScale#getExamApprovalGrade()
	 * @see #getGradeScale()
	 * @generated
	 */
	EAttribute getGradeScale_ExamApprovalGrade();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.GradeScale#getTutoringApprovalGrade <em>Tutoring Approval Grade</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tutoring Approval Grade</em>'.
	 * @see asignaturas.GradeScale#getTutoringApprovalGrade()
	 * @see #getGradeScale()
	 * @generated
	 */
	EAttribute getGradeScale_TutoringApprovalGrade();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.GradeScale#getCourseApprovalGrade <em>Course Approval Grade</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Course Approval Grade</em>'.
	 * @see asignaturas.GradeScale#getCourseApprovalGrade()
	 * @see #getGradeScale()
	 * @generated
	 */
	EAttribute getGradeScale_CourseApprovalGrade();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.GradeScale#getCoursePartialApprovalGrade <em>Course Partial Approval Grade</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Course Partial Approval Grade</em>'.
	 * @see asignaturas.GradeScale#getCoursePartialApprovalGrade()
	 * @see #getGradeScale()
	 * @generated
	 */
	EAttribute getGradeScale_CoursePartialApprovalGrade();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.GradeScale#getPartialApprovalGrade <em>Partial Approval Grade</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partial Approval Grade</em>'.
	 * @see asignaturas.GradeScale#getPartialApprovalGrade()
	 * @see #getGradeScale()
	 * @generated
	 */
	EAttribute getGradeScale_PartialApprovalGrade();

	/**
	 * Returns the meta object for the attribute '{@link asignaturas.GradeScale#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see asignaturas.GradeScale#getName()
	 * @see #getGradeScale()
	 * @generated
	 */
	EAttribute getGradeScale_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AsignaturasFactory getAsignaturasFactory();

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
		 * The meta object literal for the '{@link asignaturas.impl.RootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.RootImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getRoot()
		 * @generated
		 */
		EClass ROOT = eINSTANCE.getRoot();

		/**
		 * The meta object literal for the '<em><b>Faculty</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__FACULTY = eINSTANCE.getRoot_Faculty();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CareerImpl <em>Career</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CareerImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCareer()
		 * @generated
		 */
		EClass CAREER = eINSTANCE.getCareer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAREER__NAME = eINSTANCE.getCareer_Name();

		/**
		 * The meta object literal for the '<em><b>Plan</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAREER__PLAN = eINSTANCE.getCareer_Plan();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CreditsPlanImpl <em>Credits Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CreditsPlanImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCreditsPlan()
		 * @generated
		 */
		EClass CREDITS_PLAN = eINSTANCE.getCreditsPlan();

		/**
		 * The meta object literal for the '<em><b>Min Credits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREDITS_PLAN__MIN_CREDITS = eINSTANCE.getCreditsPlan_MinCredits();

		/**
		 * The meta object literal for the '<em><b>Group Of Subjects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CREDITS_PLAN__GROUP_OF_SUBJECTS = eINSTANCE.getCreditsPlan_GroupOfSubjects();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.FacultyImpl <em>Faculty</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.FacultyImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getFaculty()
		 * @generated
		 */
		EClass FACULTY = eINSTANCE.getFaculty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FACULTY__NAME = eINSTANCE.getFaculty_Name();

		/**
		 * The meta object literal for the '<em><b>Careers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACULTY__CAREERS = eINSTANCE.getFaculty_Careers();

		/**
		 * The meta object literal for the '<em><b>Faculty CU</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACULTY__FACULTY_CU = eINSTANCE.getFaculty_FacultyCU();

		/**
		 * The meta object literal for the '<em><b>Gradescale</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACULTY__GRADESCALE = eINSTANCE.getFaculty_Gradescale();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.PlanImpl <em>Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.PlanImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getPlan()
		 * @generated
		 */
		EClass PLAN = eINSTANCE.getPlan();

		/**
		 * The meta object literal for the '<em><b>Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__YEAR = eINSTANCE.getPlan_Year();

		/**
		 * The meta object literal for the '<em><b>Valid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__VALID = eINSTANCE.getPlan_Valid();

		/**
		 * The meta object literal for the '<em><b>Career parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__CAREER_PARENT = eINSTANCE.getPlan_Career_parent();

		/**
		 * The meta object literal for the '<em><b>Gradescale</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__GRADESCALE = eINSTANCE.getPlan_Gradescale();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.SubjectImpl <em>Subject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.SubjectImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getSubject()
		 * @generated
		 */
		EClass SUBJECT = eINSTANCE.getSubject();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBJECT__ID = eINSTANCE.getSubject_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBJECT__NAME = eINSTANCE.getSubject_Name();

		/**
		 * The meta object literal for the '<em><b>Min Credits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBJECT__MIN_CREDITS = eINSTANCE.getSubject_MinCredits();

		/**
		 * The meta object literal for the '<em><b>Group Of Subjects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBJECT__GROUP_OF_SUBJECTS = eINSTANCE.getSubject_GroupOfSubjects();

		/**
		 * The meta object literal for the '<em><b>Group Of Subjects Opposite</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE = eINSTANCE.getSubject_GroupOfSubjectsOpposite();

		/**
		 * The meta object literal for the '<em><b>Subject Curricular Unit</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBJECT__SUBJECT_CURRICULAR_UNIT = eINSTANCE.getSubject_SubjectCurricularUnit();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CurricularUnitImpl <em>Curricular Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CurricularUnitImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCurricularUnit()
		 * @generated
		 */
		EClass CURRICULAR_UNIT = eINSTANCE.getCurricularUnit();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRICULAR_UNIT__ID = eINSTANCE.getCurricularUnit_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRICULAR_UNIT__NAME = eINSTANCE.getCurricularUnit_Name();

		/**
		 * The meta object literal for the '<em><b>Cred</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRICULAR_UNIT__CRED = eINSTANCE.getCurricularUnit_Cred();

		/**
		 * The meta object literal for the '<em><b>Valid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRICULAR_UNIT__VALID = eINSTANCE.getCurricularUnit_Valid();

		/**
		 * The meta object literal for the '<em><b>Requirement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CURRICULAR_UNIT__REQUIREMENT = eINSTANCE.getCurricularUnit_Requirement();

		/**
		 * The meta object literal for the '<em><b>Course</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CURRICULAR_UNIT__COURSE = eINSTANCE.getCurricularUnit_Course();

		/**
		 * The meta object literal for the '<em><b>Exam Evaluation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CURRICULAR_UNIT__EXAM_EVALUATION = eINSTANCE.getCurricularUnit_ExamEvaluation();

		/**
		 * The meta object literal for the '<em><b>Gradescale</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CURRICULAR_UNIT__GRADESCALE = eINSTANCE.getCurricularUnit_Gradescale();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.SomeOfImpl <em>Some Of</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.SomeOfImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getSomeOf()
		 * @generated
		 */
		EClass SOME_OF = eINSTANCE.getSomeOf();

		/**
		 * The meta object literal for the '<em><b>N</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOME_OF__N = eINSTANCE.getSomeOf_N();

		/**
		 * The meta object literal for the '<em><b>Requirement</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOME_OF__REQUIREMENT = eINSTANCE.getSomeOf_Requirement();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.RequirementImpl <em>Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.RequirementImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getRequirement()
		 * @generated
		 */
		EClass REQUIREMENT = eINSTANCE.getRequirement();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.NOTImpl <em>NOT</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.NOTImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getNOT()
		 * @generated
		 */
		EClass NOT = eINSTANCE.getNOT();

		/**
		 * The meta object literal for the '<em><b>Requirement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOT__REQUIREMENT = eINSTANCE.getNOT_Requirement();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CoursedImpl <em>Coursed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CoursedImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCoursed()
		 * @generated
		 */
		EClass COURSED = eINSTANCE.getCoursed();

		/**
		 * The meta object literal for the '<em><b>Curricular Unit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COURSED__CURRICULAR_UNIT = eINSTANCE.getCoursed_CurricularUnit();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.ExamImpl <em>Exam</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.ExamImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getExam()
		 * @generated
		 */
		EClass EXAM = eINSTANCE.getExam();

		/**
		 * The meta object literal for the '<em><b>Curricular Unit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXAM__CURRICULAR_UNIT = eINSTANCE.getExam_CurricularUnit();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CreditsOnPlanImpl <em>Credits On Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CreditsOnPlanImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCreditsOnPlan()
		 * @generated
		 */
		EClass CREDITS_ON_PLAN = eINSTANCE.getCreditsOnPlan();

		/**
		 * The meta object literal for the '<em><b>Cred</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREDITS_ON_PLAN__CRED = eINSTANCE.getCreditsOnPlan_Cred();

		/**
		 * The meta object literal for the '<em><b>Credits Plan</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CREDITS_ON_PLAN__CREDITS_PLAN = eINSTANCE.getCreditsOnPlan_CreditsPlan();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CreditsOnSubjectImpl <em>Credits On Subject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CreditsOnSubjectImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCreditsOnSubject()
		 * @generated
		 */
		EClass CREDITS_ON_SUBJECT = eINSTANCE.getCreditsOnSubject();

		/**
		 * The meta object literal for the '<em><b>Cred</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREDITS_ON_SUBJECT__CRED = eINSTANCE.getCreditsOnSubject_Cred();

		/**
		 * The meta object literal for the '<em><b>Group Of Subjects</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS = eINSTANCE.getCreditsOnSubject_GroupOfSubjects();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.SubjectPlanImpl <em>Subject Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.SubjectPlanImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getSubjectPlan()
		 * @generated
		 */
		EClass SUBJECT_PLAN = eINSTANCE.getSubjectPlan();

		/**
		 * The meta object literal for the '<em><b>Curricular Unit</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBJECT_PLAN__CURRICULAR_UNIT = eINSTANCE.getSubjectPlan_CurricularUnit();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.RegisteredToImpl <em>Registered To</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.RegisteredToImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getRegisteredTo()
		 * @generated
		 */
		EClass REGISTERED_TO = eINSTANCE.getRegisteredTo();

		/**
		 * The meta object literal for the '<em><b>Curricular Unit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGISTERED_TO__CURRICULAR_UNIT = eINSTANCE.getRegisteredTo_CurricularUnit();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CourseImpl <em>Course</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CourseImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCourse()
		 * @generated
		 */
		EClass COURSE = eINSTANCE.getCourse();

		/**
		 * The meta object literal for the '<em><b>Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COURSE__YEAR = eINSTANCE.getCourse_Year();

		/**
		 * The meta object literal for the '<em><b>Edition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COURSE__EDITION = eINSTANCE.getCourse_Edition();

		/**
		 * The meta object literal for the '<em><b>Course Evaluation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COURSE__COURSE_EVALUATION = eINSTANCE.getCourse_CourseEvaluation();

		/**
		 * The meta object literal for the '<em><b>Curricularunit</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COURSE__CURRICULARUNIT = eINSTANCE.getCourse_Curricularunit();

		/**
		 * The meta object literal for the '<em><b>Partialevaluation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COURSE__PARTIALEVALUATION = eINSTANCE.getCourse_Partialevaluation();

		/**
		 * The meta object literal for the '<em><b>Tutoringevaluation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COURSE__TUTORINGEVALUATION = eINSTANCE.getCourse_Tutoringevaluation();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.EvaluationImpl <em>Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.EvaluationImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getEvaluation()
		 * @generated
		 */
		EClass EVALUATION = eINSTANCE.getEvaluation();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION__DATE = eINSTANCE.getEvaluation_Date();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.ExamEvaluationImpl <em>Exam Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.ExamEvaluationImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getExamEvaluation()
		 * @generated
		 */
		EClass EXAM_EVALUATION = eINSTANCE.getExamEvaluation();

		/**
		 * The meta object literal for the '<em><b>Curricularunit</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXAM_EVALUATION__CURRICULARUNIT = eINSTANCE.getExamEvaluation_Curricularunit();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.EvaluacionExamenImpl <em>Evaluacion Examen</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.EvaluacionExamenImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getEvaluacionExamen()
		 * @generated
		 */
		EClass EVALUACION_EXAMEN = eINSTANCE.getEvaluacionExamen();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.CourseEvaluationImpl <em>Course Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.CourseEvaluationImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getCourseEvaluation()
		 * @generated
		 */
		EClass COURSE_EVALUATION = eINSTANCE.getCourseEvaluation();

		/**
		 * The meta object literal for the '<em><b>Course</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COURSE_EVALUATION__COURSE = eINSTANCE.getCourseEvaluation_Course();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.PartialEvaluationImpl <em>Partial Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.PartialEvaluationImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getPartialEvaluation()
		 * @generated
		 */
		EClass PARTIAL_EVALUATION = eINSTANCE.getPartialEvaluation();

		/**
		 * The meta object literal for the '<em><b>Course</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTIAL_EVALUATION__COURSE = eINSTANCE.getPartialEvaluation_Course();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.TutoringEvaluationImpl <em>Tutoring Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.TutoringEvaluationImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getTutoringEvaluation()
		 * @generated
		 */
		EClass TUTORING_EVALUATION = eINSTANCE.getTutoringEvaluation();

		/**
		 * The meta object literal for the '<em><b>Course</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUTORING_EVALUATION__COURSE = eINSTANCE.getTutoringEvaluation_Course();

		/**
		 * The meta object literal for the '{@link asignaturas.impl.GradeScaleImpl <em>Grade Scale</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asignaturas.impl.GradeScaleImpl
		 * @see asignaturas.impl.AsignaturasPackageImpl#getGradeScale()
		 * @generated
		 */
		EClass GRADE_SCALE = eINSTANCE.getGradeScale();

		/**
		 * The meta object literal for the '<em><b>Exam Approval Grade</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADE_SCALE__EXAM_APPROVAL_GRADE = eINSTANCE.getGradeScale_ExamApprovalGrade();

		/**
		 * The meta object literal for the '<em><b>Tutoring Approval Grade</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADE_SCALE__TUTORING_APPROVAL_GRADE = eINSTANCE.getGradeScale_TutoringApprovalGrade();

		/**
		 * The meta object literal for the '<em><b>Course Approval Grade</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADE_SCALE__COURSE_APPROVAL_GRADE = eINSTANCE.getGradeScale_CourseApprovalGrade();

		/**
		 * The meta object literal for the '<em><b>Course Partial Approval Grade</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE = eINSTANCE.getGradeScale_CoursePartialApprovalGrade();

		/**
		 * The meta object literal for the '<em><b>Partial Approval Grade</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADE_SCALE__PARTIAL_APPROVAL_GRADE = eINSTANCE.getGradeScale_PartialApprovalGrade();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRADE_SCALE__NAME = eINSTANCE.getGradeScale_Name();

	}

} //AsignaturasPackage
