/**
 */
package asignaturas.util;

import asignaturas.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see asignaturas.AsignaturasPackage
 * @generated
 */
public class AsignaturasAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AsignaturasPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsignaturasAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AsignaturasPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsignaturasSwitch<Adapter> modelSwitch = new AsignaturasSwitch<Adapter>() {
		@Override
		public Adapter caseRoot(Root object) {
			return createRootAdapter();
		}

		@Override
		public Adapter caseCareer(Career object) {
			return createCareerAdapter();
		}

		@Override
		public Adapter caseCreditsPlan(CreditsPlan object) {
			return createCreditsPlanAdapter();
		}

		@Override
		public Adapter caseFaculty(Faculty object) {
			return createFacultyAdapter();
		}

		@Override
		public Adapter casePlan(Plan object) {
			return createPlanAdapter();
		}

		@Override
		public Adapter caseSubject(Subject object) {
			return createSubjectAdapter();
		}

		@Override
		public Adapter caseCurricularUnit(CurricularUnit object) {
			return createCurricularUnitAdapter();
		}

		@Override
		public Adapter caseSomeOf(SomeOf object) {
			return createSomeOfAdapter();
		}

		@Override
		public Adapter caseRequirement(Requirement object) {
			return createRequirementAdapter();
		}

		@Override
		public Adapter caseNOT(NOT object) {
			return createNOTAdapter();
		}

		@Override
		public Adapter caseCoursed(Coursed object) {
			return createCoursedAdapter();
		}

		@Override
		public Adapter caseExam(Exam object) {
			return createExamAdapter();
		}

		@Override
		public Adapter caseCreditsOnPlan(CreditsOnPlan object) {
			return createCreditsOnPlanAdapter();
		}

		@Override
		public Adapter caseCreditsOnSubject(CreditsOnSubject object) {
			return createCreditsOnSubjectAdapter();
		}

		@Override
		public Adapter caseSubjectPlan(SubjectPlan object) {
			return createSubjectPlanAdapter();
		}

		@Override
		public Adapter caseRegisteredTo(RegisteredTo object) {
			return createRegisteredToAdapter();
		}

		@Override
		public Adapter caseCourse(Course object) {
			return createCourseAdapter();
		}

		@Override
		public Adapter caseEvaluation(Evaluation object) {
			return createEvaluationAdapter();
		}

		@Override
		public Adapter caseExamEvaluation(ExamEvaluation object) {
			return createExamEvaluationAdapter();
		}

		@Override
		public Adapter caseEvaluacionExamen(EvaluacionExamen object) {
			return createEvaluacionExamenAdapter();
		}

		@Override
		public Adapter caseCourseEvaluation(CourseEvaluation object) {
			return createCourseEvaluationAdapter();
		}

		@Override
		public Adapter casePartialEvaluation(PartialEvaluation object) {
			return createPartialEvaluationAdapter();
		}

		@Override
		public Adapter caseTutoringEvaluation(TutoringEvaluation object) {
			return createTutoringEvaluationAdapter();
		}

		@Override
		public Adapter caseGradeScale(GradeScale object) {
			return createGradeScaleAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Root
	 * @generated
	 */
	public Adapter createRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Career <em>Career</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Career
	 * @generated
	 */
	public Adapter createCareerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.CreditsPlan <em>Credits Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.CreditsPlan
	 * @generated
	 */
	public Adapter createCreditsPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Faculty <em>Faculty</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Faculty
	 * @generated
	 */
	public Adapter createFacultyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Plan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Plan
	 * @generated
	 */
	public Adapter createPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Subject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Subject
	 * @generated
	 */
	public Adapter createSubjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.CurricularUnit <em>Curricular Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.CurricularUnit
	 * @generated
	 */
	public Adapter createCurricularUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.SomeOf <em>Some Of</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.SomeOf
	 * @generated
	 */
	public Adapter createSomeOfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Requirement
	 * @generated
	 */
	public Adapter createRequirementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.NOT <em>NOT</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.NOT
	 * @generated
	 */
	public Adapter createNOTAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Coursed <em>Coursed</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Coursed
	 * @generated
	 */
	public Adapter createCoursedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Exam <em>Exam</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Exam
	 * @generated
	 */
	public Adapter createExamAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.CreditsOnPlan <em>Credits On Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.CreditsOnPlan
	 * @generated
	 */
	public Adapter createCreditsOnPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.CreditsOnSubject <em>Credits On Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.CreditsOnSubject
	 * @generated
	 */
	public Adapter createCreditsOnSubjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.SubjectPlan <em>Subject Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.SubjectPlan
	 * @generated
	 */
	public Adapter createSubjectPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.RegisteredTo <em>Registered To</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.RegisteredTo
	 * @generated
	 */
	public Adapter createRegisteredToAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Course <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Course
	 * @generated
	 */
	public Adapter createCourseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.Evaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.Evaluation
	 * @generated
	 */
	public Adapter createEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.ExamEvaluation <em>Exam Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.ExamEvaluation
	 * @generated
	 */
	public Adapter createExamEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.EvaluacionExamen <em>Evaluacion Examen</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.EvaluacionExamen
	 * @generated
	 */
	public Adapter createEvaluacionExamenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.CourseEvaluation <em>Course Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.CourseEvaluation
	 * @generated
	 */
	public Adapter createCourseEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.PartialEvaluation <em>Partial Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.PartialEvaluation
	 * @generated
	 */
	public Adapter createPartialEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.TutoringEvaluation <em>Tutoring Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.TutoringEvaluation
	 * @generated
	 */
	public Adapter createTutoringEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link asignaturas.GradeScale <em>Grade Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see asignaturas.GradeScale
	 * @generated
	 */
	public Adapter createGradeScaleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AsignaturasAdapterFactory
