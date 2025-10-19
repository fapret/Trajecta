/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.CurricularUnit;
import asignaturas.ExamEvaluation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exam Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.ExamEvaluationImpl#getCurricularunit <em>Curricularunit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExamEvaluationImpl extends EvaluationImpl implements ExamEvaluation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExamEvaluationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.EXAM_EVALUATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurricularUnit getCurricularunit() {
		if (eContainerFeatureID() != AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT) return null;
		return (CurricularUnit)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCurricularunit(CurricularUnit newCurricularunit, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCurricularunit, AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCurricularunit(CurricularUnit newCurricularunit) {
		if (newCurricularunit != eInternalContainer() || (eContainerFeatureID() != AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT && newCurricularunit != null)) {
			if (EcoreUtil.isAncestor(this, newCurricularunit))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCurricularunit != null)
				msgs = ((InternalEObject)newCurricularunit).eInverseAdd(this, AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION, CurricularUnit.class, msgs);
			msgs = basicSetCurricularunit(newCurricularunit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT, newCurricularunit, newCurricularunit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCurricularunit((CurricularUnit)otherEnd, msgs);
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
			case AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT:
				return basicSetCurricularunit(null, msgs);
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
			case AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT:
				return eInternalContainer().eInverseRemove(this, AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION, CurricularUnit.class, msgs);
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
			case AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT:
				return getCurricularunit();
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
			case AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT:
				setCurricularunit((CurricularUnit)newValue);
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
			case AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT:
				setCurricularunit((CurricularUnit)null);
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
			case AsignaturasPackage.EXAM_EVALUATION__CURRICULARUNIT:
				return getCurricularunit() != null;
		}
		return super.eIsSet(featureID);
	}

} //ExamEvaluationImpl
