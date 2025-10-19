/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.CreditsPlan;
import asignaturas.Subject;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Credits Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.CreditsPlanImpl#getMinCredits <em>Min Credits</em>}</li>
 *   <li>{@link asignaturas.impl.CreditsPlanImpl#getGroupOfSubjects <em>Group Of Subjects</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CreditsPlanImpl extends PlanImpl implements CreditsPlan {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CreditsPlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.CREDITS_PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getMinCredits() {
		return minCredits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinCredits(int newMinCredits) {
		int oldMinCredits = minCredits;
		minCredits = newMinCredits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CREDITS_PLAN__MIN_CREDITS, oldMinCredits, minCredits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Subject> getGroupOfSubjects() {
		if (groupOfSubjects == null) {
			groupOfSubjects = new EObjectContainmentEList<Subject>(Subject.class, this, AsignaturasPackage.CREDITS_PLAN__GROUP_OF_SUBJECTS);
		}
		return groupOfSubjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsignaturasPackage.CREDITS_PLAN__GROUP_OF_SUBJECTS:
				return ((InternalEList<?>)getGroupOfSubjects()).basicRemove(otherEnd, msgs);
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
			case AsignaturasPackage.CREDITS_PLAN__MIN_CREDITS:
				return getMinCredits();
			case AsignaturasPackage.CREDITS_PLAN__GROUP_OF_SUBJECTS:
				return getGroupOfSubjects();
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
			case AsignaturasPackage.CREDITS_PLAN__MIN_CREDITS:
				setMinCredits((Integer)newValue);
				return;
			case AsignaturasPackage.CREDITS_PLAN__GROUP_OF_SUBJECTS:
				getGroupOfSubjects().clear();
				getGroupOfSubjects().addAll((Collection<? extends Subject>)newValue);
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
			case AsignaturasPackage.CREDITS_PLAN__MIN_CREDITS:
				setMinCredits(MIN_CREDITS_EDEFAULT);
				return;
			case AsignaturasPackage.CREDITS_PLAN__GROUP_OF_SUBJECTS:
				getGroupOfSubjects().clear();
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
			case AsignaturasPackage.CREDITS_PLAN__MIN_CREDITS:
				return minCredits != MIN_CREDITS_EDEFAULT;
			case AsignaturasPackage.CREDITS_PLAN__GROUP_OF_SUBJECTS:
				return groupOfSubjects != null && !groupOfSubjects.isEmpty();
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
		result.append(" (MinCredits: ");
		result.append(minCredits);
		result.append(')');
		return result.toString();
	}

} //CreditsPlanImpl
