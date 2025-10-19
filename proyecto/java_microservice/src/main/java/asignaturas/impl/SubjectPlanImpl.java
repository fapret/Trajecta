/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.CurricularUnit;
import asignaturas.SubjectPlan;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subject Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.SubjectPlanImpl#getCurricularUnit <em>Curricular Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubjectPlanImpl extends PlanImpl implements SubjectPlan {
	/**
	 * The cached value of the '{@link #getCurricularUnit() <em>Curricular Unit</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurricularUnit()
	 * @generated
	 * @ordered
	 */
	protected EList<CurricularUnit> curricularUnit;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubjectPlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.SUBJECT_PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CurricularUnit> getCurricularUnit() {
		if (curricularUnit == null) {
			curricularUnit = new EObjectResolvingEList<CurricularUnit>(CurricularUnit.class, this, AsignaturasPackage.SUBJECT_PLAN__CURRICULAR_UNIT);
		}
		return curricularUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsignaturasPackage.SUBJECT_PLAN__CURRICULAR_UNIT:
				return getCurricularUnit();
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
			case AsignaturasPackage.SUBJECT_PLAN__CURRICULAR_UNIT:
				getCurricularUnit().clear();
				getCurricularUnit().addAll((Collection<? extends CurricularUnit>)newValue);
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
			case AsignaturasPackage.SUBJECT_PLAN__CURRICULAR_UNIT:
				getCurricularUnit().clear();
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
			case AsignaturasPackage.SUBJECT_PLAN__CURRICULAR_UNIT:
				return curricularUnit != null && !curricularUnit.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SubjectPlanImpl
