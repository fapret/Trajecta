/**
 */
package asignaturas;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see asignaturas.AsignaturasPackage
 * @generated
 */
public interface AsignaturasFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AsignaturasFactory eINSTANCE = asignaturas.impl.AsignaturasFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Root</em>'.
	 * @generated
	 */
	Root createRoot();

	/**
	 * Returns a new object of class '<em>Career</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Career</em>'.
	 * @generated
	 */
	Career createCareer();

	/**
	 * Returns a new object of class '<em>Credits Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Credits Plan</em>'.
	 * @generated
	 */
	CreditsPlan createCreditsPlan();

	/**
	 * Returns a new object of class '<em>Faculty</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Faculty</em>'.
	 * @generated
	 */
	Faculty createFaculty();

	/**
	 * Returns a new object of class '<em>Subject</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subject</em>'.
	 * @generated
	 */
	Subject createSubject();

	/**
	 * Returns a new object of class '<em>Curricular Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Curricular Unit</em>'.
	 * @generated
	 */
	CurricularUnit createCurricularUnit();

	/**
	 * Returns a new object of class '<em>Some Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Some Of</em>'.
	 * @generated
	 */
	SomeOf createSomeOf();

	/**
	 * Returns a new object of class '<em>NOT</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>NOT</em>'.
	 * @generated
	 */
	NOT createNOT();

	/**
	 * Returns a new object of class '<em>Coursed</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Coursed</em>'.
	 * @generated
	 */
	Coursed createCoursed();

	/**
	 * Returns a new object of class '<em>Exam</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exam</em>'.
	 * @generated
	 */
	Exam createExam();

	/**
	 * Returns a new object of class '<em>Credits On Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Credits On Plan</em>'.
	 * @generated
	 */
	CreditsOnPlan createCreditsOnPlan();

	/**
	 * Returns a new object of class '<em>Credits On Subject</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Credits On Subject</em>'.
	 * @generated
	 */
	CreditsOnSubject createCreditsOnSubject();

	/**
	 * Returns a new object of class '<em>Subject Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subject Plan</em>'.
	 * @generated
	 */
	SubjectPlan createSubjectPlan();

	/**
	 * Returns a new object of class '<em>Registered To</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Registered To</em>'.
	 * @generated
	 */
	RegisteredTo createRegisteredTo();

	/**
	 * Returns a new object of class '<em>Course</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Course</em>'.
	 * @generated
	 */
	Course createCourse();

	/**
	 * Returns a new object of class '<em>Exam Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exam Evaluation</em>'.
	 * @generated
	 */
	ExamEvaluation createExamEvaluation();

	/**
	 * Returns a new object of class '<em>Evaluacion Examen</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Evaluacion Examen</em>'.
	 * @generated
	 */
	EvaluacionExamen createEvaluacionExamen();

	/**
	 * Returns a new object of class '<em>Course Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Course Evaluation</em>'.
	 * @generated
	 */
	CourseEvaluation createCourseEvaluation();

	/**
	 * Returns a new object of class '<em>Partial Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Partial Evaluation</em>'.
	 * @generated
	 */
	PartialEvaluation createPartialEvaluation();

	/**
	 * Returns a new object of class '<em>Tutoring Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tutoring Evaluation</em>'.
	 * @generated
	 */
	TutoringEvaluation createTutoringEvaluation();

	/**
	 * Returns a new object of class '<em>Grade Scale</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grade Scale</em>'.
	 * @generated
	 */
	GradeScale createGradeScale();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AsignaturasPackage getAsignaturasPackage();

} //AsignaturasFactory
