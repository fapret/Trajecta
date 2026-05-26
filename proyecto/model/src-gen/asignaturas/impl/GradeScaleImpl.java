/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.GradeScale;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grade Scale</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.GradeScaleImpl#getExamApprovalGrade <em>Exam Approval Grade</em>}</li>
 *   <li>{@link asignaturas.impl.GradeScaleImpl#getTutoringApprovalGrade <em>Tutoring Approval Grade</em>}</li>
 *   <li>{@link asignaturas.impl.GradeScaleImpl#getCourseApprovalGrade <em>Course Approval Grade</em>}</li>
 *   <li>{@link asignaturas.impl.GradeScaleImpl#getCoursePartialApprovalGrade <em>Course Partial Approval Grade</em>}</li>
 *   <li>{@link asignaturas.impl.GradeScaleImpl#getPartialApprovalGrade <em>Partial Approval Grade</em>}</li>
 *   <li>{@link asignaturas.impl.GradeScaleImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GradeScaleImpl extends MinimalEObjectImpl.Container implements GradeScale {
	/**
	 * The default value of the '{@link #getExamApprovalGrade() <em>Exam Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExamApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected static final float EXAM_APPROVAL_GRADE_EDEFAULT = 3.0F;

	/**
	 * The cached value of the '{@link #getExamApprovalGrade() <em>Exam Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExamApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected float examApprovalGrade = EXAM_APPROVAL_GRADE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTutoringApprovalGrade() <em>Tutoring Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTutoringApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected static final float TUTORING_APPROVAL_GRADE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getTutoringApprovalGrade() <em>Tutoring Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTutoringApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected float tutoringApprovalGrade = TUTORING_APPROVAL_GRADE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCourseApprovalGrade() <em>Course Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCourseApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected static final float COURSE_APPROVAL_GRADE_EDEFAULT = 6.0F;

	/**
	 * The cached value of the '{@link #getCourseApprovalGrade() <em>Course Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCourseApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected float courseApprovalGrade = COURSE_APPROVAL_GRADE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCoursePartialApprovalGrade() <em>Course Partial Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoursePartialApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected static final float COURSE_PARTIAL_APPROVAL_GRADE_EDEFAULT = 3.0F;

	/**
	 * The cached value of the '{@link #getCoursePartialApprovalGrade() <em>Course Partial Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoursePartialApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected float coursePartialApprovalGrade = COURSE_PARTIAL_APPROVAL_GRADE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPartialApprovalGrade() <em>Partial Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartialApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected static final float PARTIAL_APPROVAL_GRADE_EDEFAULT = 60.0F;

	/**
	 * The cached value of the '{@link #getPartialApprovalGrade() <em>Partial Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartialApprovalGrade()
	 * @generated
	 * @ordered
	 */
	protected float partialApprovalGrade = PARTIAL_APPROVAL_GRADE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GradeScaleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.GRADE_SCALE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getExamApprovalGrade() {
		return examApprovalGrade;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExamApprovalGrade(float newExamApprovalGrade) {
		float oldExamApprovalGrade = examApprovalGrade;
		examApprovalGrade = newExamApprovalGrade;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.GRADE_SCALE__EXAM_APPROVAL_GRADE,
					oldExamApprovalGrade, examApprovalGrade));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getTutoringApprovalGrade() {
		return tutoringApprovalGrade;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTutoringApprovalGrade(float newTutoringApprovalGrade) {
		float oldTutoringApprovalGrade = tutoringApprovalGrade;
		tutoringApprovalGrade = newTutoringApprovalGrade;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AsignaturasPackage.GRADE_SCALE__TUTORING_APPROVAL_GRADE, oldTutoringApprovalGrade,
					tutoringApprovalGrade));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getCourseApprovalGrade() {
		return courseApprovalGrade;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCourseApprovalGrade(float newCourseApprovalGrade) {
		float oldCourseApprovalGrade = courseApprovalGrade;
		courseApprovalGrade = newCourseApprovalGrade;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.GRADE_SCALE__COURSE_APPROVAL_GRADE,
					oldCourseApprovalGrade, courseApprovalGrade));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getCoursePartialApprovalGrade() {
		return coursePartialApprovalGrade;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoursePartialApprovalGrade(float newCoursePartialApprovalGrade) {
		float oldCoursePartialApprovalGrade = coursePartialApprovalGrade;
		coursePartialApprovalGrade = newCoursePartialApprovalGrade;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AsignaturasPackage.GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE, oldCoursePartialApprovalGrade,
					coursePartialApprovalGrade));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPartialApprovalGrade() {
		return partialApprovalGrade;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartialApprovalGrade(float newPartialApprovalGrade) {
		float oldPartialApprovalGrade = partialApprovalGrade;
		partialApprovalGrade = newPartialApprovalGrade;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AsignaturasPackage.GRADE_SCALE__PARTIAL_APPROVAL_GRADE, oldPartialApprovalGrade,
					partialApprovalGrade));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.GRADE_SCALE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AsignaturasPackage.GRADE_SCALE__EXAM_APPROVAL_GRADE:
			return getExamApprovalGrade();
		case AsignaturasPackage.GRADE_SCALE__TUTORING_APPROVAL_GRADE:
			return getTutoringApprovalGrade();
		case AsignaturasPackage.GRADE_SCALE__COURSE_APPROVAL_GRADE:
			return getCourseApprovalGrade();
		case AsignaturasPackage.GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE:
			return getCoursePartialApprovalGrade();
		case AsignaturasPackage.GRADE_SCALE__PARTIAL_APPROVAL_GRADE:
			return getPartialApprovalGrade();
		case AsignaturasPackage.GRADE_SCALE__NAME:
			return getName();
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
		case AsignaturasPackage.GRADE_SCALE__EXAM_APPROVAL_GRADE:
			setExamApprovalGrade((Float) newValue);
			return;
		case AsignaturasPackage.GRADE_SCALE__TUTORING_APPROVAL_GRADE:
			setTutoringApprovalGrade((Float) newValue);
			return;
		case AsignaturasPackage.GRADE_SCALE__COURSE_APPROVAL_GRADE:
			setCourseApprovalGrade((Float) newValue);
			return;
		case AsignaturasPackage.GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE:
			setCoursePartialApprovalGrade((Float) newValue);
			return;
		case AsignaturasPackage.GRADE_SCALE__PARTIAL_APPROVAL_GRADE:
			setPartialApprovalGrade((Float) newValue);
			return;
		case AsignaturasPackage.GRADE_SCALE__NAME:
			setName((String) newValue);
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
		case AsignaturasPackage.GRADE_SCALE__EXAM_APPROVAL_GRADE:
			setExamApprovalGrade(EXAM_APPROVAL_GRADE_EDEFAULT);
			return;
		case AsignaturasPackage.GRADE_SCALE__TUTORING_APPROVAL_GRADE:
			setTutoringApprovalGrade(TUTORING_APPROVAL_GRADE_EDEFAULT);
			return;
		case AsignaturasPackage.GRADE_SCALE__COURSE_APPROVAL_GRADE:
			setCourseApprovalGrade(COURSE_APPROVAL_GRADE_EDEFAULT);
			return;
		case AsignaturasPackage.GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE:
			setCoursePartialApprovalGrade(COURSE_PARTIAL_APPROVAL_GRADE_EDEFAULT);
			return;
		case AsignaturasPackage.GRADE_SCALE__PARTIAL_APPROVAL_GRADE:
			setPartialApprovalGrade(PARTIAL_APPROVAL_GRADE_EDEFAULT);
			return;
		case AsignaturasPackage.GRADE_SCALE__NAME:
			setName(NAME_EDEFAULT);
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
		case AsignaturasPackage.GRADE_SCALE__EXAM_APPROVAL_GRADE:
			return examApprovalGrade != EXAM_APPROVAL_GRADE_EDEFAULT;
		case AsignaturasPackage.GRADE_SCALE__TUTORING_APPROVAL_GRADE:
			return tutoringApprovalGrade != TUTORING_APPROVAL_GRADE_EDEFAULT;
		case AsignaturasPackage.GRADE_SCALE__COURSE_APPROVAL_GRADE:
			return courseApprovalGrade != COURSE_APPROVAL_GRADE_EDEFAULT;
		case AsignaturasPackage.GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE:
			return coursePartialApprovalGrade != COURSE_PARTIAL_APPROVAL_GRADE_EDEFAULT;
		case AsignaturasPackage.GRADE_SCALE__PARTIAL_APPROVAL_GRADE:
			return partialApprovalGrade != PARTIAL_APPROVAL_GRADE_EDEFAULT;
		case AsignaturasPackage.GRADE_SCALE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (ExamApprovalGrade: ");
		result.append(examApprovalGrade);
		result.append(", TutoringApprovalGrade: ");
		result.append(tutoringApprovalGrade);
		result.append(", CourseApprovalGrade: ");
		result.append(courseApprovalGrade);
		result.append(", CoursePartialApprovalGrade: ");
		result.append(coursePartialApprovalGrade);
		result.append(", PartialApprovalGrade: ");
		result.append(partialApprovalGrade);
		result.append(", Name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //GradeScaleImpl
