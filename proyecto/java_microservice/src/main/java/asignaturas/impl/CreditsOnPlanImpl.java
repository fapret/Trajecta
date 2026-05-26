/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.CreditsOnPlan;
import asignaturas.CreditsPlan;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Credits On Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.CreditsOnPlanImpl#getCred <em>Cred</em>}</li>
 *   <li>{@link asignaturas.impl.CreditsOnPlanImpl#getCreditsPlan <em>Credits Plan</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CreditsOnPlanImpl extends RequirementImpl implements CreditsOnPlan {
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
	 * The cached value of the '{@link #getCreditsPlan() <em>Credits Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreditsPlan()
	 * @generated
	 * @ordered
	 */
	protected CreditsPlan creditsPlan;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CreditsOnPlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.CREDITS_ON_PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCred() {
		return cred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCred(int newCred) {
		int oldCred = cred;
		cred = newCred;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CREDITS_ON_PLAN__CRED, oldCred,
					cred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreditsPlan getCreditsPlan() {
		if (creditsPlan != null && creditsPlan.eIsProxy()) {
			InternalEObject oldCreditsPlan = (InternalEObject) creditsPlan;
			creditsPlan = (CreditsPlan) eResolveProxy(oldCreditsPlan);
			if (creditsPlan != oldCreditsPlan) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							AsignaturasPackage.CREDITS_ON_PLAN__CREDITS_PLAN, oldCreditsPlan, creditsPlan));
			}
		}
		return creditsPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreditsPlan basicGetCreditsPlan() {
		return creditsPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreditsPlan(CreditsPlan newCreditsPlan) {
		CreditsPlan oldCreditsPlan = creditsPlan;
		creditsPlan = newCreditsPlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.CREDITS_ON_PLAN__CREDITS_PLAN,
					oldCreditsPlan, creditsPlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AsignaturasPackage.CREDITS_ON_PLAN__CRED:
			return getCred();
		case AsignaturasPackage.CREDITS_ON_PLAN__CREDITS_PLAN:
			if (resolve)
				return getCreditsPlan();
			return basicGetCreditsPlan();
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
		case AsignaturasPackage.CREDITS_ON_PLAN__CRED:
			setCred((Integer) newValue);
			return;
		case AsignaturasPackage.CREDITS_ON_PLAN__CREDITS_PLAN:
			setCreditsPlan((CreditsPlan) newValue);
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
		case AsignaturasPackage.CREDITS_ON_PLAN__CRED:
			setCred(CRED_EDEFAULT);
			return;
		case AsignaturasPackage.CREDITS_ON_PLAN__CREDITS_PLAN:
			setCreditsPlan((CreditsPlan) null);
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
		case AsignaturasPackage.CREDITS_ON_PLAN__CRED:
			return cred != CRED_EDEFAULT;
		case AsignaturasPackage.CREDITS_ON_PLAN__CREDITS_PLAN:
			return creditsPlan != null;
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
		result.append(" (cred: ");
		result.append(cred);
		result.append(')');
		return result.toString();
	}

} //CreditsOnPlanImpl
