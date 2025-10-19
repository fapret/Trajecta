/**
 */
package Estudiantes.impl;

import Estudiantes.CourseInscription;
import Estudiantes.EstudiantesPackage;

import asignaturas.Course;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Course Inscription</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Estudiantes.impl.CourseInscriptionImpl#getCUCourse <em>CU Course</em>}</li>
 *   <li>{@link Estudiantes.impl.CourseInscriptionImpl#getDate <em>Date</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CourseInscriptionImpl extends MinimalEObjectImpl.Container implements CourseInscription {
	/**
	 * The cached value of the '{@link #getCUCourse() <em>CU Course</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCUCourse()
	 * @generated
	 * @ordered
	 */
	protected Course cuCourse;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CourseInscriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EstudiantesPackage.Literals.COURSE_INSCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Course getCUCourse() {
		if (cuCourse != null && cuCourse.eIsProxy()) {
			InternalEObject oldCUCourse = (InternalEObject)cuCourse;
			cuCourse = (Course)eResolveProxy(oldCUCourse);
			if (cuCourse != oldCUCourse) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EstudiantesPackage.COURSE_INSCRIPTION__CU_COURSE, oldCUCourse, cuCourse));
			}
		}
		return cuCourse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Course basicGetCUCourse() {
		return cuCourse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCUCourse(Course newCUCourse) {
		Course oldCUCourse = cuCourse;
		cuCourse = newCUCourse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EstudiantesPackage.COURSE_INSCRIPTION__CU_COURSE, oldCUCourse, cuCourse));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EstudiantesPackage.COURSE_INSCRIPTION__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EstudiantesPackage.COURSE_INSCRIPTION__CU_COURSE:
				if (resolve) return getCUCourse();
				return basicGetCUCourse();
			case EstudiantesPackage.COURSE_INSCRIPTION__DATE:
				return getDate();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EstudiantesPackage.COURSE_INSCRIPTION__CU_COURSE:
				setCUCourse((Course)newValue);
				return;
			case EstudiantesPackage.COURSE_INSCRIPTION__DATE:
				setDate((Date)newValue);
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
			case EstudiantesPackage.COURSE_INSCRIPTION__CU_COURSE:
				setCUCourse((Course)null);
				return;
			case EstudiantesPackage.COURSE_INSCRIPTION__DATE:
				setDate(DATE_EDEFAULT);
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
			case EstudiantesPackage.COURSE_INSCRIPTION__CU_COURSE:
				return cuCourse != null;
			case EstudiantesPackage.COURSE_INSCRIPTION__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
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

} //CourseInscriptionImpl
