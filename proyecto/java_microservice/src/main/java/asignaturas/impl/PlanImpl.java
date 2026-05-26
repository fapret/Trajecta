/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.Career;
import asignaturas.GradeScale;
import asignaturas.Plan;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.PlanImpl#getYear <em>Year</em>}</li>
 *   <li>{@link asignaturas.impl.PlanImpl#isValid <em>Valid</em>}</li>
 *   <li>{@link asignaturas.impl.PlanImpl#getCareer_parent <em>Career parent</em>}</li>
 *   <li>{@link asignaturas.impl.PlanImpl#getGradescale <em>Gradescale</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PlanImpl extends MinimalEObjectImpl.Container implements Plan {
	/**
	 * The default value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected static final int YEAR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected int year = YEAR_EDEFAULT;

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
	 * The cached value of the '{@link #getGradescale() <em>Gradescale</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGradescale()
	 * @generated
	 * @ordered
	 */
	protected GradeScale gradescale;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getYear() {
		return year;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYear(int newYear) {
		int oldYear = year;
		year = newYear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.PLAN__YEAR, oldYear, year));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValid(boolean newValid) {
		boolean oldValid = valid;
		valid = newValid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.PLAN__VALID, oldValid, valid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Career getCareer_parent() {
		if (eContainerFeatureID() != AsignaturasPackage.PLAN__CAREER_PARENT)
			return null;
		return (Career) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCareer_parent(Career newCareer_parent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newCareer_parent, AsignaturasPackage.PLAN__CAREER_PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCareer_parent(Career newCareer_parent) {
		if (newCareer_parent != eInternalContainer()
				|| (eContainerFeatureID() != AsignaturasPackage.PLAN__CAREER_PARENT && newCareer_parent != null)) {
			if (EcoreUtil.isAncestor(this, newCareer_parent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCareer_parent != null)
				msgs = ((InternalEObject) newCareer_parent).eInverseAdd(this, AsignaturasPackage.CAREER__PLAN,
						Career.class, msgs);
			msgs = basicSetCareer_parent(newCareer_parent, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.PLAN__CAREER_PARENT,
					newCareer_parent, newCareer_parent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GradeScale getGradescale() {
		if (gradescale != null && gradescale.eIsProxy()) {
			InternalEObject oldGradescale = (InternalEObject) gradescale;
			gradescale = (GradeScale) eResolveProxy(oldGradescale);
			if (gradescale != oldGradescale) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsignaturasPackage.PLAN__GRADESCALE,
							oldGradescale, gradescale));
			}
		}
		return gradescale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GradeScale basicGetGradescale() {
		return gradescale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGradescale(GradeScale newGradescale) {
		GradeScale oldGradescale = gradescale;
		gradescale = newGradescale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.PLAN__GRADESCALE, oldGradescale,
					gradescale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AsignaturasPackage.PLAN__CAREER_PARENT:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetCareer_parent((Career) otherEnd, msgs);
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
		case AsignaturasPackage.PLAN__CAREER_PARENT:
			return basicSetCareer_parent(null, msgs);
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
		case AsignaturasPackage.PLAN__CAREER_PARENT:
			return eInternalContainer().eInverseRemove(this, AsignaturasPackage.CAREER__PLAN, Career.class, msgs);
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
		case AsignaturasPackage.PLAN__YEAR:
			return getYear();
		case AsignaturasPackage.PLAN__VALID:
			return isValid();
		case AsignaturasPackage.PLAN__CAREER_PARENT:
			return getCareer_parent();
		case AsignaturasPackage.PLAN__GRADESCALE:
			if (resolve)
				return getGradescale();
			return basicGetGradescale();
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
		case AsignaturasPackage.PLAN__YEAR:
			setYear((Integer) newValue);
			return;
		case AsignaturasPackage.PLAN__VALID:
			setValid((Boolean) newValue);
			return;
		case AsignaturasPackage.PLAN__CAREER_PARENT:
			setCareer_parent((Career) newValue);
			return;
		case AsignaturasPackage.PLAN__GRADESCALE:
			setGradescale((GradeScale) newValue);
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
		case AsignaturasPackage.PLAN__YEAR:
			setYear(YEAR_EDEFAULT);
			return;
		case AsignaturasPackage.PLAN__VALID:
			setValid(VALID_EDEFAULT);
			return;
		case AsignaturasPackage.PLAN__CAREER_PARENT:
			setCareer_parent((Career) null);
			return;
		case AsignaturasPackage.PLAN__GRADESCALE:
			setGradescale((GradeScale) null);
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
		case AsignaturasPackage.PLAN__YEAR:
			return year != YEAR_EDEFAULT;
		case AsignaturasPackage.PLAN__VALID:
			return valid != VALID_EDEFAULT;
		case AsignaturasPackage.PLAN__CAREER_PARENT:
			return getCareer_parent() != null;
		case AsignaturasPackage.PLAN__GRADESCALE:
			return gradescale != null;
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
		result.append(" (Year: ");
		result.append(year);
		result.append(", Valid: ");
		result.append(valid);
		result.append(')');
		return result.toString();
	}

} //PlanImpl
