/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.Course;
import asignaturas.CurricularUnit;
import asignaturas.ExamEvaluation;
import asignaturas.Requirement;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Curricular Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.CurricularUnitImpl#getId <em>Id</em>}</li>
 *   <li>{@link asignaturas.impl.CurricularUnitImpl#getName <em>Name</em>}</li>
 *   <li>{@link asignaturas.impl.CurricularUnitImpl#getCred <em>Cred</em>}</li>
 *   <li>{@link asignaturas.impl.CurricularUnitImpl#isValid <em>Valid</em>}</li>
 *   <li>{@link asignaturas.impl.CurricularUnitImpl#getRequirement <em>Requirement</em>}</li>
 *   <li>{@link asignaturas.impl.CurricularUnitImpl#getCourse <em>Course</em>}</li>
 *   <li>{@link asignaturas.impl.CurricularUnitImpl#getExamEvaluation <em>Exam Evaluation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CurricularUnitImpl extends MinimalEObjectImpl.Container implements CurricularUnit {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	 * The default value of the '{@link #getCred() <em>Cred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCred()
	 * @generated
	 * @ordered
	 */
	protected static final int CRED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCred() <em>Cred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCred()
	 * @generated
	 * @ordered
	 */
	protected int cred = CRED_EDEFAULT;

	/**
	 * The default value of the '{@link #isValid() <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValid()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isValid() <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValid()
	 * @generated
	 * @ordered
	 */
	protected boolean valid = VALID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequirement() <em>Requirement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirement()
	 * @generated
	 * @ordered
	 */
	protected Requirement requirement;

	/**
	 * The cached value of the '{@link #getCourse() <em>Course</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCourse()
	 * @generated
	 * @ordered
	 */
	protected EList<Course> course;

	/**
	 * The cached value of the '{@link #getExamEvaluation() <em>Exam Evaluation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExamEvaluation()
	 * @generated
	 * @ordered
	 */
	protected EList<ExamEvaluation> examEvaluation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CurricularUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.CURRICULAR_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CURRICULAR_UNIT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CURRICULAR_UNIT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getCred() {
		return cred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCred(int newCred) {
		int oldCred = cred;
		cred = newCred;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CURRICULAR_UNIT__CRED, oldCred, cred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isValid() {
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValid(boolean newValid) {
		boolean oldValid = valid;
		valid = newValid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CURRICULAR_UNIT__VALID, oldValid, valid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Requirement getRequirement() {
		return requirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRequirement(Requirement newRequirement, NotificationChain msgs) {
		Requirement oldRequirement = requirement;
		requirement = newRequirement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT, oldRequirement, newRequirement);
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
	public void setRequirement(Requirement newRequirement) {
		if (newRequirement != requirement) {
			NotificationChain msgs = null;
			if (requirement != null)
				msgs = ((InternalEObject)requirement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT, null, msgs);
			if (newRequirement != null)
				msgs = ((InternalEObject)newRequirement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT, null, msgs);
			msgs = basicSetRequirement(newRequirement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT, newRequirement, newRequirement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Course> getCourse() {
		if (course == null) {
			course = new EObjectContainmentWithInverseEList<Course>(Course.class, this, AsignaturasPackage.CURRICULAR_UNIT__COURSE, AsignaturasPackage.COURSE__CURRICULARUNIT);
		}
		return course;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExamEvaluation> getExamEvaluation() {
		if (examEvaluation == null) {
			examEvaluation = new EObjectContainmentWithInverseEList<ExamEvaluation>(ExamEvaluation.class, this, AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION, AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT);
		}
		return examEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsignaturasPackage.CURRICULAR_UNIT__COURSE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCourse()).basicAdd(otherEnd, msgs);
			case AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExamEvaluation()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT:
				return basicSetRequirement(null, msgs);
			case AsignaturasPackage.CURRICULAR_UNIT__COURSE:
				return ((InternalEList<?>)getCourse()).basicRemove(otherEnd, msgs);
			case AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION:
				return ((InternalEList<?>)getExamEvaluation()).basicRemove(otherEnd, msgs);
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
			case AsignaturasPackage.CURRICULAR_UNIT__ID:
				return getId();
			case AsignaturasPackage.CURRICULAR_UNIT__NAME:
				return getName();
			case AsignaturasPackage.CURRICULAR_UNIT__CRED:
				return getCred();
			case AsignaturasPackage.CURRICULAR_UNIT__VALID:
				return isValid();
			case AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT:
				return getRequirement();
			case AsignaturasPackage.CURRICULAR_UNIT__COURSE:
				return getCourse();
			case AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION:
				return getExamEvaluation();
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
			case AsignaturasPackage.CURRICULAR_UNIT__ID:
				setId((String)newValue);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__NAME:
				setName((String)newValue);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__CRED:
				setCred((Integer)newValue);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__VALID:
				setValid((Boolean)newValue);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT:
				setRequirement((Requirement)newValue);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__COURSE:
				getCourse().clear();
				getCourse().addAll((Collection<? extends Course>)newValue);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION:
				getExamEvaluation().clear();
				getExamEvaluation().addAll((Collection<? extends ExamEvaluation>)newValue);
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
			case AsignaturasPackage.CURRICULAR_UNIT__ID:
				setId(ID_EDEFAULT);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__CRED:
				setCred(CRED_EDEFAULT);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__VALID:
				setValid(VALID_EDEFAULT);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT:
				setRequirement((Requirement)null);
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__COURSE:
				getCourse().clear();
				return;
			case AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION:
				getExamEvaluation().clear();
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
			case AsignaturasPackage.CURRICULAR_UNIT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case AsignaturasPackage.CURRICULAR_UNIT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AsignaturasPackage.CURRICULAR_UNIT__CRED:
				return cred != CRED_EDEFAULT;
			case AsignaturasPackage.CURRICULAR_UNIT__VALID:
				return valid != VALID_EDEFAULT;
			case AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT:
				return requirement != null;
			case AsignaturasPackage.CURRICULAR_UNIT__COURSE:
				return course != null && !course.isEmpty();
			case AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION:
				return examEvaluation != null && !examEvaluation.isEmpty();
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
		result.append(" (Id: ");
		result.append(id);
		result.append(", Name: ");
		result.append(name);
		result.append(", Cred: ");
		result.append(cred);
		result.append(", Valid: ");
		result.append(valid);
		result.append(')');
		return result.toString();
	}

} //CurricularUnitImpl
