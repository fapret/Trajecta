/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.Career;
import asignaturas.CurricularUnit;
import asignaturas.Faculty;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Faculty</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.FacultyImpl#getName <em>Name</em>}</li>
 *   <li>{@link asignaturas.impl.FacultyImpl#getCareers <em>Careers</em>}</li>
 *   <li>{@link asignaturas.impl.FacultyImpl#getFacultyCU <em>Faculty CU</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FacultyImpl extends MinimalEObjectImpl.Container implements Faculty {
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
	 * The cached value of the '{@link #getCareers() <em>Careers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCareers()
	 * @generated
	 * @ordered
	 */
	protected EList<Career> careers;

	/**
	 * The cached value of the '{@link #getFacultyCU() <em>Faculty CU</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacultyCU()
	 * @generated
	 * @ordered
	 */
	protected EList<CurricularUnit> facultyCU;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FacultyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.FACULTY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.FACULTY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Career> getCareers() {
		if (careers == null) {
			careers = new EObjectContainmentEList<Career>(Career.class, this, AsignaturasPackage.FACULTY__CAREERS);
		}
		return careers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CurricularUnit> getFacultyCU() {
		if (facultyCU == null) {
			facultyCU = new EObjectContainmentEList<CurricularUnit>(CurricularUnit.class, this, AsignaturasPackage.FACULTY__FACULTY_CU);
		}
		return facultyCU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsignaturasPackage.FACULTY__CAREERS:
				return ((InternalEList<?>)getCareers()).basicRemove(otherEnd, msgs);
			case AsignaturasPackage.FACULTY__FACULTY_CU:
				return ((InternalEList<?>)getFacultyCU()).basicRemove(otherEnd, msgs);
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
			case AsignaturasPackage.FACULTY__NAME:
				return getName();
			case AsignaturasPackage.FACULTY__CAREERS:
				return getCareers();
			case AsignaturasPackage.FACULTY__FACULTY_CU:
				return getFacultyCU();
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
			case AsignaturasPackage.FACULTY__NAME:
				setName((String)newValue);
				return;
			case AsignaturasPackage.FACULTY__CAREERS:
				getCareers().clear();
				getCareers().addAll((Collection<? extends Career>)newValue);
				return;
			case AsignaturasPackage.FACULTY__FACULTY_CU:
				getFacultyCU().clear();
				getFacultyCU().addAll((Collection<? extends CurricularUnit>)newValue);
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
			case AsignaturasPackage.FACULTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AsignaturasPackage.FACULTY__CAREERS:
				getCareers().clear();
				return;
			case AsignaturasPackage.FACULTY__FACULTY_CU:
				getFacultyCU().clear();
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
			case AsignaturasPackage.FACULTY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AsignaturasPackage.FACULTY__CAREERS:
				return careers != null && !careers.isEmpty();
			case AsignaturasPackage.FACULTY__FACULTY_CU:
				return facultyCU != null && !facultyCU.isEmpty();
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
		result.append(" (Name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //FacultyImpl
