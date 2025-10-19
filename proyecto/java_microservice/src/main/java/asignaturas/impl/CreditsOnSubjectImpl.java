/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.CreditsOnSubject;
import asignaturas.Subject;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Credits On Subject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.CreditsOnSubjectImpl#getCred <em>Cred</em>}</li>
 *   <li>{@link asignaturas.impl.CreditsOnSubjectImpl#getGroupOfSubjects <em>Group Of Subjects</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CreditsOnSubjectImpl extends RequirementImpl implements CreditsOnSubject {
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
	 * The cached value of the '{@link #getGroupOfSubjects() <em>Group Of Subjects</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupOfSubjects()
	 * @generated
	 * @ordered
	 */
	protected Subject groupOfSubjects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CreditsOnSubjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.CREDITS_ON_SUBJECT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CREDITS_ON_SUBJECT__CRED, oldCred, cred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Subject getGroupOfSubjects() {
		if (groupOfSubjects != null && groupOfSubjects.eIsProxy()) {
			InternalEObject oldGroupOfSubjects = (InternalEObject)groupOfSubjects;
			groupOfSubjects = (Subject)eResolveProxy(oldGroupOfSubjects);
			if (groupOfSubjects != oldGroupOfSubjects) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsignaturasPackage.CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS, oldGroupOfSubjects, groupOfSubjects));
			}
		}
		return groupOfSubjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Subject basicGetGroupOfSubjects() {
		return groupOfSubjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGroupOfSubjects(Subject newGroupOfSubjects) {
		Subject oldGroupOfSubjects = groupOfSubjects;
		groupOfSubjects = newGroupOfSubjects;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS, oldGroupOfSubjects, groupOfSubjects));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsignaturasPackage.CREDITS_ON_SUBJECT__CRED:
				return getCred();
			case AsignaturasPackage.CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS:
				if (resolve) return getGroupOfSubjects();
				return basicGetGroupOfSubjects();
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
			case AsignaturasPackage.CREDITS_ON_SUBJECT__CRED:
				setCred((Integer)newValue);
				return;
			case AsignaturasPackage.CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS:
				setGroupOfSubjects((Subject)newValue);
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
			case AsignaturasPackage.CREDITS_ON_SUBJECT__CRED:
				setCred(CRED_EDEFAULT);
				return;
			case AsignaturasPackage.CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS:
				setGroupOfSubjects((Subject)null);
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
			case AsignaturasPackage.CREDITS_ON_SUBJECT__CRED:
				return cred != CRED_EDEFAULT;
			case AsignaturasPackage.CREDITS_ON_SUBJECT__GROUP_OF_SUBJECTS:
				return groupOfSubjects != null;
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
		result.append(" (cred: ");
		result.append(cred);
		result.append(')');
		return result.toString();
	}

} //CreditsOnSubjectImpl
