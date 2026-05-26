/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.CurricularUnit;
import asignaturas.Subject;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.SubjectImpl#getId <em>Id</em>}</li>
 *   <li>{@link asignaturas.impl.SubjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link asignaturas.impl.SubjectImpl#getMinCredits <em>Min Credits</em>}</li>
 *   <li>{@link asignaturas.impl.SubjectImpl#getGroupOfSubjects <em>Group Of Subjects</em>}</li>
 *   <li>{@link asignaturas.impl.SubjectImpl#getGroupOfSubjectsOpposite <em>Group Of Subjects Opposite</em>}</li>
 *   <li>{@link asignaturas.impl.SubjectImpl#getSubjectCurricularUnit <em>Subject Curricular Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubjectImpl extends MinimalEObjectImpl.Container implements Subject {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

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
	 * The default value of the '{@link #getMinCredits() <em>Min Credits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinCredits()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_CREDITS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinCredits() <em>Min Credits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinCredits()
	 * @generated
	 * @ordered
	 */
	protected int minCredits = MIN_CREDITS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroupOfSubjects() <em>Group Of Subjects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupOfSubjects()
	 * @generated
	 * @ordered
	 */
	protected EList<Subject> groupOfSubjects;

	/**
	 * The cached value of the '{@link #getSubjectCurricularUnit() <em>Subject Curricular Unit</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectCurricularUnit()
	 * @generated
	 * @ordered
	 */
	protected EList<CurricularUnit> subjectCurricularUnit;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.SUBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.SUBJECT__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.SUBJECT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinCredits() {
		return minCredits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinCredits(int newMinCredits) {
		int oldMinCredits = minCredits;
		minCredits = newMinCredits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.SUBJECT__MIN_CREDITS,
					oldMinCredits, minCredits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Subject> getGroupOfSubjects() {
		if (groupOfSubjects == null) {
			groupOfSubjects = new EObjectContainmentWithInverseEList<Subject>(Subject.class, this,
					AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS,
					AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE);
		}
		return groupOfSubjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Subject getGroupOfSubjectsOpposite() {
		if (eContainerFeatureID() != AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE)
			return null;
		return (Subject) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroupOfSubjectsOpposite(Subject newGroupOfSubjectsOpposite,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newGroupOfSubjectsOpposite,
				AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupOfSubjectsOpposite(Subject newGroupOfSubjectsOpposite) {
		if (newGroupOfSubjectsOpposite != eInternalContainer()
				|| (eContainerFeatureID() != AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE
						&& newGroupOfSubjectsOpposite != null)) {
			if (EcoreUtil.isAncestor(this, newGroupOfSubjectsOpposite))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroupOfSubjectsOpposite != null)
				msgs = ((InternalEObject) newGroupOfSubjectsOpposite).eInverseAdd(this,
						AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS, Subject.class, msgs);
			msgs = basicSetGroupOfSubjectsOpposite(newGroupOfSubjectsOpposite, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE, newGroupOfSubjectsOpposite,
					newGroupOfSubjectsOpposite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CurricularUnit> getSubjectCurricularUnit() {
		if (subjectCurricularUnit == null) {
			subjectCurricularUnit = new EObjectResolvingEList<CurricularUnit>(CurricularUnit.class, this,
					AsignaturasPackage.SUBJECT__SUBJECT_CURRICULAR_UNIT);
		}
		return subjectCurricularUnit;
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
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getGroupOfSubjects()).basicAdd(otherEnd, msgs);
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetGroupOfSubjectsOpposite((Subject) otherEnd, msgs);
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
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS:
			return ((InternalEList<?>) getGroupOfSubjects()).basicRemove(otherEnd, msgs);
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE:
			return basicSetGroupOfSubjectsOpposite(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE:
			return eInternalContainer().eInverseRemove(this, AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS,
					Subject.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AsignaturasPackage.SUBJECT__ID:
			return getId();
		case AsignaturasPackage.SUBJECT__NAME:
			return getName();
		case AsignaturasPackage.SUBJECT__MIN_CREDITS:
			return getMinCredits();
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS:
			return getGroupOfSubjects();
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE:
			return getGroupOfSubjectsOpposite();
		case AsignaturasPackage.SUBJECT__SUBJECT_CURRICULAR_UNIT:
			return getSubjectCurricularUnit();
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
		case AsignaturasPackage.SUBJECT__ID:
			setId((Integer) newValue);
			return;
		case AsignaturasPackage.SUBJECT__NAME:
			setName((String) newValue);
			return;
		case AsignaturasPackage.SUBJECT__MIN_CREDITS:
			setMinCredits((Integer) newValue);
			return;
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS:
			getGroupOfSubjects().clear();
			getGroupOfSubjects().addAll((Collection<? extends Subject>) newValue);
			return;
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE:
			setGroupOfSubjectsOpposite((Subject) newValue);
			return;
		case AsignaturasPackage.SUBJECT__SUBJECT_CURRICULAR_UNIT:
			getSubjectCurricularUnit().clear();
			getSubjectCurricularUnit().addAll((Collection<? extends CurricularUnit>) newValue);
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
		case AsignaturasPackage.SUBJECT__ID:
			setId(ID_EDEFAULT);
			return;
		case AsignaturasPackage.SUBJECT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case AsignaturasPackage.SUBJECT__MIN_CREDITS:
			setMinCredits(MIN_CREDITS_EDEFAULT);
			return;
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS:
			getGroupOfSubjects().clear();
			return;
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE:
			setGroupOfSubjectsOpposite((Subject) null);
			return;
		case AsignaturasPackage.SUBJECT__SUBJECT_CURRICULAR_UNIT:
			getSubjectCurricularUnit().clear();
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
		case AsignaturasPackage.SUBJECT__ID:
			return id != ID_EDEFAULT;
		case AsignaturasPackage.SUBJECT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case AsignaturasPackage.SUBJECT__MIN_CREDITS:
			return minCredits != MIN_CREDITS_EDEFAULT;
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS:
			return groupOfSubjects != null && !groupOfSubjects.isEmpty();
		case AsignaturasPackage.SUBJECT__GROUP_OF_SUBJECTS_OPPOSITE:
			return getGroupOfSubjectsOpposite() != null;
		case AsignaturasPackage.SUBJECT__SUBJECT_CURRICULAR_UNIT:
			return subjectCurricularUnit != null && !subjectCurricularUnit.isEmpty();
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
		result.append(" (Id: ");
		result.append(id);
		result.append(", Name: ");
		result.append(name);
		result.append(", MinCredits: ");
		result.append(minCredits);
		result.append(')');
		return result.toString();
	}

} //SubjectImpl
