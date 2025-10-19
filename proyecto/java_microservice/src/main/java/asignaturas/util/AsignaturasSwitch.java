/**
 */
package asignaturas.util;

import asignaturas.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see asignaturas.AsignaturasPackage
 * @generated
 */
public class AsignaturasSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AsignaturasPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsignaturasSwitch() {
		if (modelPackage == null) {
			modelPackage = AsignaturasPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AsignaturasPackage.ROOT: {
				Root root = (Root)theEObject;
				T result = caseRoot(root);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.CAREER: {
				Career career = (Career)theEObject;
				T result = caseCareer(career);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.CREDITS_PLAN: {
				CreditsPlan creditsPlan = (CreditsPlan)theEObject;
				T result = caseCreditsPlan(creditsPlan);
				if (result == null) result = casePlan(creditsPlan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.FACULTY: {
				Faculty faculty = (Faculty)theEObject;
				T result = caseFaculty(faculty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.PLAN: {
				Plan plan = (Plan)theEObject;
				T result = casePlan(plan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.SUBJECT: {
				Subject subject = (Subject)theEObject;
				T result = caseSubject(subject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.CURRICULAR_UNIT: {
				CurricularUnit curricularUnit = (CurricularUnit)theEObject;
				T result = caseCurricularUnit(curricularUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.SOME_OF: {
				SomeOf someOf = (SomeOf)theEObject;
				T result = caseSomeOf(someOf);
				if (result == null) result = caseRequirement(someOf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.REQUIREMENT: {
				Requirement requirement = (Requirement)theEObject;
				T result = caseRequirement(requirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.NOT: {
				NOT not = (NOT)theEObject;
				T result = caseNOT(not);
				if (result == null) result = caseRequirement(not);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.COURSED: {
				Coursed coursed = (Coursed)theEObject;
				T result = caseCoursed(coursed);
				if (result == null) result = caseRequirement(coursed);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.EXAM: {
				Exam exam = (Exam)theEObject;
				T result = caseExam(exam);
				if (result == null) result = caseRequirement(exam);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.CREDITS_ON_PLAN: {
				CreditsOnPlan creditsOnPlan = (CreditsOnPlan)theEObject;
				T result = caseCreditsOnPlan(creditsOnPlan);
				if (result == null) result = caseRequirement(creditsOnPlan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.CREDITS_ON_SUBJECT: {
				CreditsOnSubject creditsOnSubject = (CreditsOnSubject)theEObject;
				T result = caseCreditsOnSubject(creditsOnSubject);
				if (result == null) result = caseRequirement(creditsOnSubject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.SUBJECT_PLAN: {
				SubjectPlan subjectPlan = (SubjectPlan)theEObject;
				T result = caseSubjectPlan(subjectPlan);
				if (result == null) result = casePlan(subjectPlan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.REGISTERED_TO: {
				RegisteredTo registeredTo = (RegisteredTo)theEObject;
				T result = caseRegisteredTo(registeredTo);
				if (result == null) result = caseRequirement(registeredTo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.COURSE: {
				Course course = (Course)theEObject;
				T result = caseCourse(course);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.EVALUATION: {
				Evaluation evaluation = (Evaluation)theEObject;
				T result = caseEvaluation(evaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.EXAM_EVALUATION: {
				ExamEvaluation examEvaluation = (ExamEvaluation)theEObject;
				T result = caseExamEvaluation(examEvaluation);
				if (result == null) result = caseEvaluation(examEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.EVALUACION_EXAMEN: {
				EvaluacionExamen evaluacionExamen = (EvaluacionExamen)theEObject;
				T result = caseEvaluacionExamen(evaluacionExamen);
				if (result == null) result = caseEvaluation(evaluacionExamen);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.COURSE_EVALUATION: {
				CourseEvaluation courseEvaluation = (CourseEvaluation)theEObject;
				T result = caseCourseEvaluation(courseEvaluation);
				if (result == null) result = caseEvaluation(courseEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsignaturasPackage.PARTIAL_EVALUATION: {
				PartialEvaluation partialEvaluation = (PartialEvaluation)theEObject;
				T result = casePartialEvaluation(partialEvaluation);
				if (result == null) result = caseEvaluation(partialEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoot(Root object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Career</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Career</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCareer(Career object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Credits Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Credits Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreditsPlan(CreditsPlan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Faculty</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Faculty</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFaculty(Faculty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlan(Plan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Subject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Subject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubject(Subject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Curricular Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Curricular Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCurricularUnit(CurricularUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Some Of</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Some Of</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSomeOf(SomeOf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirement(Requirement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>NOT</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>NOT</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNOT(NOT object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Coursed</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Coursed</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCoursed(Coursed object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exam</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exam</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExam(Exam object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Credits On Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Credits On Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreditsOnPlan(CreditsOnPlan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Credits On Subject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Credits On Subject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreditsOnSubject(CreditsOnSubject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Subject Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Subject Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubjectPlan(SubjectPlan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Registered To</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Registered To</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRegisteredTo(RegisteredTo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Course</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Course</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCourse(Course object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvaluation(Evaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exam Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exam Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExamEvaluation(ExamEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Evaluacion Examen</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Evaluacion Examen</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvaluacionExamen(EvaluacionExamen object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Course Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Course Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCourseEvaluation(CourseEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Partial Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partial Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePartialEvaluation(PartialEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //AsignaturasSwitch
