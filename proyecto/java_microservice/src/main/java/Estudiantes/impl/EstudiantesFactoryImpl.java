/**
 */
package Estudiantes.impl;

import Estudiantes.*;

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
public class EstudiantesFactoryImpl extends EFactoryImpl implements EstudiantesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EstudiantesFactory init() {
		try {
			EstudiantesFactory theEstudiantesFactory = (EstudiantesFactory)EPackage.Registry.INSTANCE.getEFactory(EstudiantesPackage.eNS_URI);
			if (theEstudiantesFactory != null) {
				return theEstudiantesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EstudiantesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EstudiantesFactoryImpl() {
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
			case EstudiantesPackage.ROOT: return createRoot();
			case EstudiantesPackage.STUDENT: return createStudent();
			case EstudiantesPackage.COURSE_INSCRIPTION: return createCourseInscription();
			case EstudiantesPackage.STUDENT_EVALUATION: return createStudentEvaluation();
			case EstudiantesPackage.PLAN_INSCRIPTION: return createPlanInscription();
			case EstudiantesPackage.DEGREE: return createDegree();
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
	public Student createStudent() {
		StudentImpl student = new StudentImpl();
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CourseInscription createCourseInscription() {
		CourseInscriptionImpl courseInscription = new CourseInscriptionImpl();
		return courseInscription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StudentEvaluation createStudentEvaluation() {
		StudentEvaluationImpl studentEvaluation = new StudentEvaluationImpl();
		return studentEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PlanInscription createPlanInscription() {
		PlanInscriptionImpl planInscription = new PlanInscriptionImpl();
		return planInscription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Degree createDegree() {
		DegreeImpl degree = new DegreeImpl();
		return degree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EstudiantesPackage getEstudiantesPackage() {
		return (EstudiantesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EstudiantesPackage getPackage() {
		return EstudiantesPackage.eINSTANCE;
	}

} //EstudiantesFactoryImpl
