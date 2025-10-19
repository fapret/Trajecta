/**
 */
package asignaturas.impl;

import asignaturas.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AsignaturasFactoryImpl extends EFactoryImpl implements AsignaturasFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AsignaturasFactory init() {
		try {
			AsignaturasFactory theAsignaturasFactory = (AsignaturasFactory)EPackage.Registry.INSTANCE.getEFactory(AsignaturasPackage.eNS_URI);
			if (theAsignaturasFactory != null) {
				return theAsignaturasFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AsignaturasFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsignaturasFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AsignaturasPackage.ROOT: return createRoot();
			case AsignaturasPackage.CAREER: return createCareer();
			case AsignaturasPackage.CREDITS_PLAN: return createCreditsPlan();
			case AsignaturasPackage.FACULTY: return createFaculty();
			case AsignaturasPackage.SUBJECT: return createSubject();
			case AsignaturasPackage.CURRICULAR_UNIT: return createCurricularUnit();
			case AsignaturasPackage.SOME_OF: return createSomeOf();
			case AsignaturasPackage.NOT: return createNOT();
			case AsignaturasPackage.COURSED: return createCoursed();
			case AsignaturasPackage.EXAM: return createExam();
			case AsignaturasPackage.CREDITS_ON_PLAN: return createCreditsOnPlan();
			case AsignaturasPackage.CREDITS_ON_SUBJECT: return createCreditsOnSubject();
			case AsignaturasPackage.SUBJECT_PLAN: return createSubjectPlan();
			case AsignaturasPackage.REGISTERED_TO: return createRegisteredTo();
			case AsignaturasPackage.COURSE: return createCourse();
			case AsignaturasPackage.EXAM_EVALUATION: return createExamEvaluation();
			case AsignaturasPackage.EVALUACION_EXAMEN: return createEvaluacionExamen();
			case AsignaturasPackage.COURSE_EVALUATION: return createCourseEvaluation();
			case AsignaturasPackage.PARTIAL_EVALUATION: return createPartialEvaluation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Root createRoot() {
		RootImpl root = new RootImpl();
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Career createCareer() {
		CareerImpl career = new CareerImpl();
		return career;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CreditsPlan createCreditsPlan() {
		CreditsPlanImpl creditsPlan = new CreditsPlanImpl();
		return creditsPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Faculty createFaculty() {
		FacultyImpl faculty = new FacultyImpl();
		return faculty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Subject createSubject() {
		SubjectImpl subject = new SubjectImpl();
		return subject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurricularUnit createCurricularUnit() {
		CurricularUnitImpl curricularUnit = new CurricularUnitImpl();
		return curricularUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SomeOf createSomeOf() {
		SomeOfImpl someOf = new SomeOfImpl();
		return someOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NOT createNOT() {
		NOTImpl not = new NOTImpl();
		return not;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Coursed createCoursed() {
		CoursedImpl coursed = new CoursedImpl();
		return coursed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Exam createExam() {
		ExamImpl exam = new ExamImpl();
		return exam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CreditsOnPlan createCreditsOnPlan() {
		CreditsOnPlanImpl creditsOnPlan = new CreditsOnPlanImpl();
		return creditsOnPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CreditsOnSubject createCreditsOnSubject() {
		CreditsOnSubjectImpl creditsOnSubject = new CreditsOnSubjectImpl();
		return creditsOnSubject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SubjectPlan createSubjectPlan() {
		SubjectPlanImpl subjectPlan = new SubjectPlanImpl();
		return subjectPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RegisteredTo createRegisteredTo() {
		RegisteredToImpl registeredTo = new RegisteredToImpl();
		return registeredTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Course createCourse() {
		CourseImpl course = new CourseImpl();
		return course;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExamEvaluation createExamEvaluation() {
		ExamEvaluationImpl examEvaluation = new ExamEvaluationImpl();
		return examEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EvaluacionExamen createEvaluacionExamen() {
		EvaluacionExamenImpl evaluacionExamen = new EvaluacionExamenImpl();
		return evaluacionExamen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CourseEvaluation createCourseEvaluation() {
		CourseEvaluationImpl courseEvaluation = new CourseEvaluationImpl();
		return courseEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PartialEvaluation createPartialEvaluation() {
		PartialEvaluationImpl partialEvaluation = new PartialEvaluationImpl();
		return partialEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AsignaturasPackage getAsignaturasPackage() {
		return (AsignaturasPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AsignaturasPackage getPackage() {
		return AsignaturasPackage.eINSTANCE;
	}

} //AsignaturasFactoryImpl
