/**
 */
package asignaturas.impl;

import Estudiantes.EstudiantesPackage;

import Estudiantes.impl.EstudiantesPackageImpl;

import asignaturas.AsignaturasFactory;
import asignaturas.AsignaturasPackage;
import asignaturas.Career;
import asignaturas.Course;
import asignaturas.CourseEvaluation;
import asignaturas.Coursed;
import asignaturas.CreditsOnPlan;
import asignaturas.CreditsOnSubject;
import asignaturas.CreditsPlan;
import asignaturas.CurricularUnit;
import asignaturas.EvaluacionExamen;
import asignaturas.Evaluation;
import asignaturas.Exam;
import asignaturas.ExamEvaluation;
import asignaturas.Faculty;
import asignaturas.GradeScale;
import asignaturas.PartialEvaluation;
import asignaturas.Plan;
import asignaturas.RegisteredTo;
import asignaturas.Requirement;
import asignaturas.Root;
import asignaturas.SomeOf;
import asignaturas.Subject;
import asignaturas.SubjectPlan;

import asignaturas.TutoringEvaluation;
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
public class AsignaturasPackageImpl extends EPackageImpl implements AsignaturasPackage {
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
	private EClass careerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass creditsPlanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass facultyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass planEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass curricularUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass someOfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coursedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass examEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass creditsOnPlanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass creditsOnSubjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subjectPlanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass registeredToEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass courseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass examEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evaluacionExamenEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass courseEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass partialEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tutoringEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gradeScaleEClass = null;

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
	 * @see asignaturas.AsignaturasPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AsignaturasPackageImpl() {
		super(eNS_URI, AsignaturasFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AsignaturasPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AsignaturasPackage init() {
		if (isInited)
			return (AsignaturasPackage) EPackage.Registry.INSTANCE.getEPackage(AsignaturasPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredAsignaturasPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		AsignaturasPackageImpl theAsignaturasPackage = registeredAsignaturasPackage instanceof AsignaturasPackageImpl
				? (AsignaturasPackageImpl) registeredAsignaturasPackage
				: new AsignaturasPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(EstudiantesPackage.eNS_URI);
		EstudiantesPackageImpl theEstudiantesPackage = (EstudiantesPackageImpl) (registeredPackage instanceof EstudiantesPackageImpl
				? registeredPackage
				: EstudiantesPackage.eINSTANCE);

		// Create package meta-data objects
		theAsignaturasPackage.createPackageContents();
		theEstudiantesPackage.createPackageContents();

		// Initialize created meta-data
		theAsignaturasPackage.initializePackageContents();
		theEstudiantesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAsignaturasPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AsignaturasPackage.eNS_URI, theAsignaturasPackage);
		return theAsignaturasPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoot() {
		return rootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoot_Faculty() {
		return (EReference) rootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCareer() {
		return careerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCareer_Name() {
		return (EAttribute) careerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCareer_Plan() {
		return (EReference) careerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreditsPlan() {
		return creditsPlanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreditsPlan_MinCredits() {
		return (EAttribute) creditsPlanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCreditsPlan_GroupOfSubjects() {
		return (EReference) creditsPlanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFaculty() {
		return facultyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaculty_Name() {
		return (EAttribute) facultyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFaculty_Careers() {
		return (EReference) facultyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFaculty_FacultyCU() {
		return (EReference) facultyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFaculty_Gradescale() {
		return (EReference) facultyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlan() {
		return planEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlan_Year() {
		return (EAttribute) planEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlan_Valid() {
		return (EAttribute) planEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlan_Career_parent() {
		return (EReference) planEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlan_Gradescale() {
		return (EReference) planEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubject() {
		return subjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubject_Id() {
		return (EAttribute) subjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubject_Name() {
		return (EAttribute) subjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubject_MinCredits() {
		return (EAttribute) subjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_GroupOfSubjects() {
		return (EReference) subjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_GroupOfSubjectsOpposite() {
		return (EReference) subjectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_SubjectCurricularUnit() {
		return (EReference) subjectEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCurricularUnit() {
		return curricularUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCurricularUnit_Id() {
		return (EAttribute) curricularUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCurricularUnit_Name() {
		return (EAttribute) curricularUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCurricularUnit_Cred() {
		return (EAttribute) curricularUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCurricularUnit_Valid() {
		return (EAttribute) curricularUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCurricularUnit_Requirement() {
		return (EReference) curricularUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCurricularUnit_Course() {
		return (EReference) curricularUnitEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCurricularUnit_ExamEvaluation() {
		return (EReference) curricularUnitEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCurricularUnit_Gradescale() {
		return (EReference) curricularUnitEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSomeOf() {
		return someOfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSomeOf_N() {
		return (EAttribute) someOfEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSomeOf_Requirement() {
		return (EReference) someOfEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirement() {
		return requirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNOT() {
		return notEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNOT_Requirement() {
		return (EReference) notEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoursed() {
		return coursedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCoursed_CurricularUnit() {
		return (EReference) coursedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExam() {
		return examEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExam_CurricularUnit() {
		return (EReference) examEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreditsOnPlan() {
		return creditsOnPlanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreditsOnPlan_Cred() {
		return (EAttribute) creditsOnPlanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCreditsOnPlan_CreditsPlan() {
		return (EReference) creditsOnPlanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreditsOnSubject() {
		return creditsOnSubjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreditsOnSubject_Cred() {
		return (EAttribute) creditsOnSubjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCreditsOnSubject_GroupOfSubjects() {
		return (EReference) creditsOnSubjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubjectPlan() {
		return subjectPlanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubjectPlan_CurricularUnit() {
		return (EReference) subjectPlanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRegisteredTo() {
		return registeredToEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRegisteredTo_CurricularUnit() {
		return (EReference) registeredToEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCourse() {
		return courseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCourse_Year() {
		return (EAttribute) courseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCourse_Edition() {
		return (EAttribute) courseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCourse_CourseEvaluation() {
		return (EReference) courseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCourse_Curricularunit() {
		return (EReference) courseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCourse_Partialevaluation() {
		return (EReference) courseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCourse_Tutoringevaluation() {
		return (EReference) courseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvaluation() {
		return evaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluation_Date() {
		return (EAttribute) evaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExamEvaluation() {
		return examEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExamEvaluation_Curricularunit() {
		return (EReference) examEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvaluacionExamen() {
		return evaluacionExamenEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCourseEvaluation() {
		return courseEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCourseEvaluation_Course() {
		return (EReference) courseEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPartialEvaluation() {
		return partialEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPartialEvaluation_Course() {
		return (EReference) partialEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTutoringEvaluation() {
		return tutoringEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTutoringEvaluation_Course() {
		return (EReference) tutoringEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGradeScale() {
		return gradeScaleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradeScale_ExamApprovalGrade() {
		return (EAttribute) gradeScaleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradeScale_TutoringApprovalGrade() {
		return (EAttribute) gradeScaleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradeScale_CourseApprovalGrade() {
		return (EAttribute) gradeScaleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradeScale_CoursePartialApprovalGrade() {
		return (EAttribute) gradeScaleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradeScale_PartialApprovalGrade() {
		return (EAttribute) gradeScaleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGradeScale_Name() {
		return (EAttribute) gradeScaleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsignaturasFactory getAsignaturasFactory() {
		return (AsignaturasFactory) getEFactoryInstance();
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
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		rootEClass = createEClass(ROOT);
		createEReference(rootEClass, ROOT__FACULTY);

		careerEClass = createEClass(CAREER);
		createEAttribute(careerEClass, CAREER__NAME);
		createEReference(careerEClass, CAREER__PLAN);

		creditsPlanEClass = createEClass(CREDITS_PLAN);
		createEAttribute(creditsPlanEClass, CREDITS_PLAN__MIN_CREDITS);
		createEReference(creditsPlanEClass, CREDITS_PLAN__GROUP_OF_SUBJECTS);

		facultyEClass = createEClass(FACULTY);
		createEAttribute(facultyEClass, FACULTY__NAME);
		createEReference(facultyEClass, FACULTY__CAREERS);
		createEReference(facultyEClass, FACULTY__FACULTY_CU);
		createEReference(facultyEClass, FACULTY__GRADESCALE);

		planEClass = createEClass(PLAN);
		createEAttribute(planEClass, PLAN__YEAR);
		createEAttribute(planEClass, PLAN__VALID);
		createEReference(planEClass, PLAN__CAREER_PARENT);
		createEReference(planEClass, PLAN__GRADESCALE);

		subjectEClass = createEClass(SUBJECT);
		createEAttribute(subjectEClass, SUBJECT__ID);
		createEAttribute(subjectEClass, SUBJECT__NAME);
		createEAttribute(subjectEClass, SUBJECT__MIN_CREDITS);
		createEReference(subjectEClass, SUBJECT__GROUP_OF_SUBJECTS);
		createEReference(subjectEClass, SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE);
		createEReference(subjectEClass, SUBJECT__SUBJECT_CURRICULAR_UNIT);

		curricularUnitEClass = createEClass(CURRICULAR_UNIT);
		createEAttribute(curricularUnitEClass, CURRICULAR_UNIT__ID);
		createEAttribute(curricularUnitEClass, CURRICULAR_UNIT__NAME);
		createEAttribute(curricularUnitEClass, CURRICULAR_UNIT__CRED);
		createEAttribute(curricularUnitEClass, CURRICULAR_UNIT__VALID);
		createEReference(curricularUnitEClass, CURRICULAR_UNIT__REQUIREMENT);
		createEReference(curricularUnitEClass, CURRICULAR_UNIT__COURSE);
		createEReference(curricularUnitEClass, CURRICULAR_UNIT__EXAM_EVALUATION);
		createEReference(curricularUnitEClass, CURRICULAR_UNIT__GRADESCALE);

		someOfEClass = createEClass(SOME_OF);
		createEAttribute(someOfEClass, SOME_OF__N);
		createEReference(someOfEClass, SOME_OF__REQUIREMENT);

		requirementEClass = createEClass(REQUIREMENT);

		notEClass = createEClass(NOT);
		createEReference(notEClass, NOT__REQUIREMENT);

		coursedEClass = createEClass(COURSED);
		createEReference(coursedEClass, COURSED__CURRICULAR_UNIT);

		examEClass = createEClass(EXAM);
		createEReference(examEClass, EXAM__CURRICULAR_UNIT);

		creditsOnPlanEClass = createEClass(CREDITS_ON_PLAN);
		createEAttribute(creditsOnPlanEClass, CREDITS_ON_PLAN__CRED);
		createEReference(creditsOnPlanEClass, CREDITS_ON_PLAN__CREDITS_PLAN);

		creditsOnSubjectEClass = createEClass(CREDITS_ON_SUBJECT);
		createEAttribute(creditsOnSubjectEClass, CREDITS_ON_SUBJECT__CRED);
		createEReference(creditsOnSubjectEClass, CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS);

		subjectPlanEClass = createEClass(SUBJECT_PLAN);
		createEReference(subjectPlanEClass, SUBJECT_PLAN__CURRICULAR_UNIT);

		registeredToEClass = createEClass(REGISTERED_TO);
		createEReference(registeredToEClass, REGISTERED_TO__CURRICULAR_UNIT);

		courseEClass = createEClass(COURSE);
		createEAttribute(courseEClass, COURSE__YEAR);
		createEAttribute(courseEClass, COURSE__EDITION);
		createEReference(courseEClass, COURSE__COURSE_EVALUATION);
		createEReference(courseEClass, COURSE__CURRICULARUNIT);
		createEReference(courseEClass, COURSE__PARTIALEVALUATION);
		createEReference(courseEClass, COURSE__TUTORINGEVALUATION);

		evaluationEClass = createEClass(EVALUATION);
		createEAttribute(evaluationEClass, EVALUATION__DATE);

		examEvaluationEClass = createEClass(EXAM_EVALUATION);
		createEReference(examEvaluationEClass, EXAM_EVALUATION__CURRICULARUNIT);

		evaluacionExamenEClass = createEClass(EVALUACION_EXAMEN);

		courseEvaluationEClass = createEClass(COURSE_EVALUATION);
		createEReference(courseEvaluationEClass, COURSE_EVALUATION__COURSE);

		partialEvaluationEClass = createEClass(PARTIAL_EVALUATION);
		createEReference(partialEvaluationEClass, PARTIAL_EVALUATION__COURSE);

		tutoringEvaluationEClass = createEClass(TUTORING_EVALUATION);
		createEReference(tutoringEvaluationEClass, TUTORING_EVALUATION__COURSE);

		gradeScaleEClass = createEClass(GRADE_SCALE);
		createEAttribute(gradeScaleEClass, GRADE_SCALE__EXAM_APPROVAL_GRADE);
		createEAttribute(gradeScaleEClass, GRADE_SCALE__TUTORING_APPROVAL_GRADE);
		createEAttribute(gradeScaleEClass, GRADE_SCALE__COURSE_APPROVAL_GRADE);
		createEAttribute(gradeScaleEClass, GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE);
		createEAttribute(gradeScaleEClass, GRADE_SCALE__PARTIAL_APPROVAL_GRADE);
		createEAttribute(gradeScaleEClass, GRADE_SCALE__NAME);
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
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		creditsPlanEClass.getESuperTypes().add(this.getPlan());
		someOfEClass.getESuperTypes().add(this.getRequirement());
		notEClass.getESuperTypes().add(this.getRequirement());
		coursedEClass.getESuperTypes().add(this.getRequirement());
		examEClass.getESuperTypes().add(this.getRequirement());
		creditsOnPlanEClass.getESuperTypes().add(this.getRequirement());
		creditsOnSubjectEClass.getESuperTypes().add(this.getRequirement());
		subjectPlanEClass.getESuperTypes().add(this.getPlan());
		registeredToEClass.getESuperTypes().add(this.getRequirement());
		examEvaluationEClass.getESuperTypes().add(this.getEvaluation());
		evaluacionExamenEClass.getESuperTypes().add(this.getEvaluation());
		courseEvaluationEClass.getESuperTypes().add(this.getEvaluation());
		partialEvaluationEClass.getESuperTypes().add(this.getEvaluation());
		tutoringEvaluationEClass.getESuperTypes().add(this.getEvaluation());

		// Initialize classes, features, and operations; add parameters
		initEClass(rootEClass, Root.class, "Root", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRoot_Faculty(), this.getFaculty(), null, "Faculty", null, 0, -1, Root.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(careerEClass, Career.class, "Career", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCareer_Name(), ecorePackage.getEString(), "Name", null, 1, 1, Career.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCareer_Plan(), this.getPlan(), this.getPlan_Career_parent(), "plan", null, 0, -1,
				Career.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(creditsPlanEClass, CreditsPlan.class, "CreditsPlan", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCreditsPlan_MinCredits(), ecorePackage.getEInt(), "MinCredits", null, 0, 1, CreditsPlan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCreditsPlan_GroupOfSubjects(), this.getSubject(), null, "GroupOfSubjects", null, 0, -1,
				CreditsPlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(facultyEClass, Faculty.class, "Faculty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFaculty_Name(), ecorePackage.getEString(), "Name", null, 1, 1, Faculty.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFaculty_Careers(), this.getCareer(), null, "Careers", null, 0, -1, Faculty.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFaculty_FacultyCU(), this.getCurricularUnit(), null, "FacultyCU", null, 0, -1, Faculty.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFaculty_Gradescale(), this.getGradeScale(), null, "gradescale", null, 0, -1, Faculty.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(planEClass, Plan.class, "Plan", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlan_Year(), ecorePackage.getEInt(), "Year", null, 0, 1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlan_Valid(), ecorePackage.getEBoolean(), "Valid", null, 0, 1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Career_parent(), this.getCareer(), this.getCareer_Plan(), "career_parent", null, 1, 1,
				Plan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Gradescale(), this.getGradeScale(), null, "gradescale", null, 1, 1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subjectEClass, Subject.class, "Subject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSubject_Id(), ecorePackage.getEInt(), "Id", null, 1, 1, Subject.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubject_Name(), ecorePackage.getEString(), "Name", null, 1, 1, Subject.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubject_MinCredits(), ecorePackage.getEInt(), "MinCredits", null, 0, 1, Subject.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubject_GroupOfSubjects(), this.getSubject(), this.getSubject_GroupOfSubjectsOpposite(),
				"GroupOfSubjects", null, 0, -1, Subject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubject_GroupOfSubjectsOpposite(), this.getSubject(), this.getSubject_GroupOfSubjects(),
				"GroupOfSubjectsOpposite", null, 0, 1, Subject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubject_SubjectCurricularUnit(), this.getCurricularUnit(), null, "SubjectCurricularUnit",
				null, 0, -1, Subject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(curricularUnitEClass, CurricularUnit.class, "CurricularUnit", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCurricularUnit_Id(), ecorePackage.getEString(), "Id", null, 1, 1, CurricularUnit.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCurricularUnit_Name(), ecorePackage.getEString(), "Name", null, 1, 1, CurricularUnit.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCurricularUnit_Cred(), ecorePackage.getEInt(), "Cred", null, 0, 1, CurricularUnit.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCurricularUnit_Valid(), ecorePackage.getEBoolean(), "Valid", null, 0, 1, CurricularUnit.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCurricularUnit_Requirement(), this.getRequirement(), null, "Requirement", null, 0, 1,
				CurricularUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCurricularUnit_Course(), this.getCourse(), this.getCourse_Curricularunit(), "Course", null, 0,
				-1, CurricularUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCurricularUnit_ExamEvaluation(), this.getExamEvaluation(),
				this.getExamEvaluation_Curricularunit(), "ExamEvaluation", null, 0, -1, CurricularUnit.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCurricularUnit_Gradescale(), this.getGradeScale(), null, "gradescale", null, 0, 1,
				CurricularUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(someOfEClass, SomeOf.class, "SomeOf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSomeOf_N(), ecorePackage.getEInt(), "N", null, 1, 1, SomeOf.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSomeOf_Requirement(), this.getRequirement(), null, "Requirement", null, 1, -1, SomeOf.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementEClass, Requirement.class, "Requirement", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(notEClass, asignaturas.NOT.class, "NOT", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNOT_Requirement(), this.getRequirement(), null, "Requirement", null, 1, 1,
				asignaturas.NOT.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(coursedEClass, Coursed.class, "Coursed", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCoursed_CurricularUnit(), this.getCurricularUnit(), null, "CurricularUnit", null, 1, 1,
				Coursed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(examEClass, Exam.class, "Exam", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExam_CurricularUnit(), this.getCurricularUnit(), null, "CurricularUnit", null, 1, 1,
				Exam.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(creditsOnPlanEClass, CreditsOnPlan.class, "CreditsOnPlan", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCreditsOnPlan_Cred(), ecorePackage.getEInt(), "cred", null, 1, 1, CreditsOnPlan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCreditsOnPlan_CreditsPlan(), this.getCreditsPlan(), null, "CreditsPlan", null, 1, 1,
				CreditsOnPlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(creditsOnSubjectEClass, CreditsOnSubject.class, "CreditsOnSubject", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCreditsOnSubject_Cred(), ecorePackage.getEInt(), "cred", null, 1, 1, CreditsOnSubject.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCreditsOnSubject_GroupOfSubjects(), this.getSubject(), null, "GroupOfSubjects", null, 1, 1,
				CreditsOnSubject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subjectPlanEClass, SubjectPlan.class, "SubjectPlan", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSubjectPlan_CurricularUnit(), this.getCurricularUnit(), null, "CurricularUnit", null, 0, -1,
				SubjectPlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(registeredToEClass, RegisteredTo.class, "RegisteredTo", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRegisteredTo_CurricularUnit(), this.getCurricularUnit(), null, "CurricularUnit", null, 1, 1,
				RegisteredTo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(courseEClass, Course.class, "Course", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCourse_Year(), ecorePackage.getEInt(), "Year", null, 0, 1, Course.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCourse_Edition(), ecorePackage.getEInt(), "Edition", null, 0, 1, Course.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCourse_CourseEvaluation(), this.getCourseEvaluation(), this.getCourseEvaluation_Course(),
				"CourseEvaluation", null, 0, -1, Course.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCourse_Curricularunit(), this.getCurricularUnit(), this.getCurricularUnit_Course(),
				"curricularunit", null, 1, 1, Course.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCourse_Partialevaluation(), this.getPartialEvaluation(), this.getPartialEvaluation_Course(),
				"partialevaluation", null, 0, -1, Course.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCourse_Tutoringevaluation(), this.getTutoringEvaluation(),
				this.getTutoringEvaluation_Course(), "tutoringevaluation", null, 0, -1, Course.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(evaluationEClass, Evaluation.class, "Evaluation", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvaluation_Date(), ecorePackage.getEDate(), "date", null, 0, 1, Evaluation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(examEvaluationEClass, ExamEvaluation.class, "ExamEvaluation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExamEvaluation_Curricularunit(), this.getCurricularUnit(),
				this.getCurricularUnit_ExamEvaluation(), "curricularunit", null, 1, 1, ExamEvaluation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(evaluacionExamenEClass, EvaluacionExamen.class, "EvaluacionExamen", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(courseEvaluationEClass, CourseEvaluation.class, "CourseEvaluation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCourseEvaluation_Course(), this.getCourse(), this.getCourse_CourseEvaluation(), "course",
				null, 1, 1, CourseEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(partialEvaluationEClass, PartialEvaluation.class, "PartialEvaluation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPartialEvaluation_Course(), this.getCourse(), this.getCourse_Partialevaluation(), "course",
				null, 1, 1, PartialEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tutoringEvaluationEClass, TutoringEvaluation.class, "TutoringEvaluation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTutoringEvaluation_Course(), this.getCourse(), this.getCourse_Tutoringevaluation(), "course",
				null, 1, 1, TutoringEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gradeScaleEClass, GradeScale.class, "GradeScale", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGradeScale_ExamApprovalGrade(), ecorePackage.getEFloat(), "ExamApprovalGrade", "3", 0, 1,
				GradeScale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGradeScale_TutoringApprovalGrade(), ecorePackage.getEFloat(), "TutoringApprovalGrade", null,
				0, 1, GradeScale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGradeScale_CourseApprovalGrade(), ecorePackage.getEFloat(), "CourseApprovalGrade", "6", 0, 1,
				GradeScale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGradeScale_CoursePartialApprovalGrade(), ecorePackage.getEFloat(),
				"CoursePartialApprovalGrade", "3", 0, 1, GradeScale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGradeScale_PartialApprovalGrade(), ecorePackage.getEFloat(), "PartialApprovalGrade", "60", 0,
				1, GradeScale.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGradeScale_Name(), ecorePackage.getEString(), "Name", null, 1, 1, GradeScale.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //AsignaturasPackageImpl
