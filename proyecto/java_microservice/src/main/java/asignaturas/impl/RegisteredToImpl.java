/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.CurricularUnit;
import asignaturas.RegisteredTo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Registered To</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.RegisteredToImpl#getCurricularUnit <em>Curricular Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RegisteredToImpl extends RequirementImpl implements RegisteredTo {
	/**
	 * The cached value of the '{@link #getCurricularUnit() <em>Curricular Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurricularUnit()
	 * @generated
	 * @ordered
	 */
	protected CurricularUnit curricularUnit;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RegisteredToImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.REGISTERED_TO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CurricularUnit getCurricularUnit() {
		if (curricularUnit != null && curricularUnit.eIsProxy()) {
			InternalEObject oldCurricularUnit = (InternalEObject) curricularUnit;
			curricularUnit = (CurricularUnit) eResolveProxy(oldCurricularUnit);
			if (curricularUnit != oldCurricularUnit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							AsignaturasPackage.REGISTERED_TO__CURRICULAR_UNIT, oldCurricularUnit, curricularUnit));
			}
		}
		return curricularUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CurricularUnit basicGetCurricularUnit() {
		return curricularUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurricularUnit(CurricularUnit newCurricularUnit) {
		CurricularUnit oldCurricularUnit = curricularUnit;
		curricularUnit = newCurricularUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.REGISTERED_TO__CURRICULAR_UNIT,
					oldCurricularUnit, curricularUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AsignaturasPackage.REGISTERED_TO__CURRICULAR_UNIT:
			if (resolve)
				return getCurricularUnit();
			return basicGetCurricularUnit();
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
		case AsignaturasPackage.REGISTERED_TO__CURRICULAR_UNIT:
			setCurricularUnit((CurricularUnit) newValue);
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
		case AsignaturasPackage.REGISTERED_TO__CURRICULAR_UNIT:
			setCurricularUnit((CurricularUnit) null);
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
		case AsignaturasPackage.REGISTERED_TO__CURRICULAR_UNIT:
			return curricularUnit != null;
		}
		return super.eIsSet(featureID);
	}

} //RegisteredToImpl
