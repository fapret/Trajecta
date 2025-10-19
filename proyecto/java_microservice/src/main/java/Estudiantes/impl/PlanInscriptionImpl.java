/**
 */
package Estudiantes.impl;

import Estudiantes.CourseInscription;
import Estudiantes.Degree;
import Estudiantes.EstudiantesPackage;
import Estudiantes.PlanInscription;
import Estudiantes.StudentEvaluation;

import asignaturas.Plan;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plan Inscription</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Estudiantes.impl.PlanInscriptionImpl#getDate <em>Date</em>}</li>
 *   <li>{@link Estudiantes.impl.PlanInscriptionImpl#getPlan <em>Plan</em>}</li>
 *   <li>{@link Estudiantes.impl.PlanInscriptionImpl#getPlanCourseInscription <em>Plan Course Inscription</em>}</li>
 *   <li>{@link Estudiantes.impl.PlanInscriptionImpl#getPlanStudentEvaluation <em>Plan Student Evaluation</em>}</li>
 *   <li>{@link Estudiantes.impl.PlanInscriptionImpl#getDegree <em>Degree</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlanInscriptionImpl extends MinimalEObjectImpl.Container implements PlanInscription {
	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected Date date = DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPlan() <em>Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlan()
	 * @generated
	 * @ordered
	 */
	protected Plan plan;

	/**
	 * The cached value of the '{@link #getPlanCourseInscription() <em>Plan Course Inscription</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlanCourseInscription()
	 * @generated
	 * @ordered
	 */
	protected EList<CourseInscription> planCourseInscription;

	/**
	 * The cached value of the '{@link #getPlanStudentEvaluation() <em>Plan Student Evaluation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlanStudentEvaluation()
	 * @generated
	 * @ordered
	 */
	protected EList<StudentEvaluation> planStudentEvaluation;

	/**
	 * The cached value of the '{@link #getDegree() <em>Degree</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDegree()
	 * @generated
	 * @ordered
	 */
	protected Degree degree;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanInscriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EstudiantesPackage.Literals.PLAN_INSCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDate(Date newDate) {
		Date oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EstudiantesPackage.PLAN_INSCRIPTION__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Plan getPlan() {
		if (plan != null && plan.eIsProxy()) {
			InternalEObject oldPlan = (InternalEObject)plan;
			plan = (Plan)eResolveProxy(oldPlan);
			if (plan != oldPlan) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EstudiantesPackage.PLAN_INSCRIPTION__PLAN, oldPlan, plan));
			}
		}
		return plan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Plan basicGetPlan() {
		return plan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlan(Plan newPlan) {
		Plan oldPlan = plan;
		plan = newPlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EstudiantesPackage.PLAN_INSCRIPTION__PLAN, oldPlan, plan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CourseInscription> getPlanCourseInscription() {
		if (planCourseInscription == null) {
			planCourseInscription = new EObjectContainmentEList<CourseInscription>(CourseInscription.class, this, EstudiantesPackage.PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION);
		}
		return planCourseInscription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<StudentEvaluation> getPlanStudentEvaluation() {
		if (planStudentEvaluation == null) {
			planStudentEvaluation = new EObjectContainmentEList<StudentEvaluation>(StudentEvaluation.class, this, EstudiantesPackage.PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION);
		}
		return planStudentEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Degree getDegree() {
		return degree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDegree(Degree newDegree, NotificationChain msgs) {
		Degree oldDegree = degree;
		degree = newDegree;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EstudiantesPackage.PLAN_INSCRIPTION__DEGREE, oldDegree, newDegree);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDegree(Degree newDegree) {
		if (newDegree != degree) {
			NotificationChain msgs = null;
			if (degree != null)
				msgs = ((InternalEObject)degree).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EstudiantesPackage.PLAN_INSCRIPTION__DEGREE, null, msgs);
			if (newDegree != null)
				msgs = ((InternalEObject)newDegree).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EstudiantesPackage.PLAN_INSCRIPTION__DEGREE, null, msgs);
			msgs = basicSetDegree(newDegree, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EstudiantesPackage.PLAN_INSCRIPTION__DEGREE, newDegree, newDegree));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION:
				return ((InternalEList<?>)getPlanCourseInscription()).basicRemove(otherEnd, msgs);
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION:
				return ((InternalEList<?>)getPlanStudentEvaluation()).basicRemove(otherEnd, msgs);
			case EstudiantesPackage.PLAN_INSCRIPTION__DEGREE:
				return basicSetDegree(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EstudiantesPackage.PLAN_INSCRIPTION__DATE:
				return getDate();
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN:
				if (resolve) return getPlan();
				return basicGetPlan();
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION:
				return getPlanCourseInscription();
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION:
				return getPlanStudentEvaluation();
			case EstudiantesPackage.PLAN_INSCRIPTION__DEGREE:
				return getDegree();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EstudiantesPackage.PLAN_INSCRIPTION__DATE:
				setDate((Date)newValue);
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN:
				setPlan((Plan)newValue);
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION:
				getPlanCourseInscription().clear();
				getPlanCourseInscription().addAll((Collection<? extends CourseInscription>)newValue);
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION:
				getPlanStudentEvaluation().clear();
				getPlanStudentEvaluation().addAll((Collection<? extends StudentEvaluation>)newValue);
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__DEGREE:
				setDegree((Degree)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EstudiantesPackage.PLAN_INSCRIPTION__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN:
				setPlan((Plan)null);
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION:
				getPlanCourseInscription().clear();
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION:
				getPlanStudentEvaluation().clear();
				return;
			case EstudiantesPackage.PLAN_INSCRIPTION__DEGREE:
				setDegree((Degree)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EstudiantesPackage.PLAN_INSCRIPTION__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN:
				return plan != null;
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_COURSE_INSCRIPTION:
				return planCourseInscription != null && !planCourseInscription.isEmpty();
			case EstudiantesPackage.PLAN_INSCRIPTION__PLAN_STUDENT_EVALUATION:
				return planStudentEvaluation != null && !planStudentEvaluation.isEmpty();
			case EstudiantesPackage.PLAN_INSCRIPTION__DEGREE:
				return degree != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (date: ");
		result.append(date);
		result.append(')');
		return result.toString();
	}

} //PlanInscriptionImpl
