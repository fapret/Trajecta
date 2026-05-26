/**
 */
package Workspace.impl;

import Workspace.WorkspaceElem;
import Workspace.WorkspacePackage;

import asignaturas.Root;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Elem</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Workspace.impl.WorkspaceElemImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link Workspace.impl.WorkspaceElemImpl#getAsignaturas <em>Asignaturas</em>}</li>
 *   <li>{@link Workspace.impl.WorkspaceElemImpl#getEstudiante <em>Estudiante</em>}</li>
 *   <li>{@link Workspace.impl.WorkspaceElemImpl#getID <em>ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkspaceElemImpl extends MinimalEObjectImpl.Container implements WorkspaceElem {
	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAsignaturas() <em>Asignaturas</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsignaturas()
	 * @generated
	 * @ordered
	 */
	protected Root asignaturas;

	/**
	 * The cached value of the '{@link #getEstudiante() <em>Estudiante</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEstudiante()
	 * @generated
	 * @ordered
	 */
	protected Estudiantes.Root estudiante;

	/**
	 * The default value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceElemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WorkspacePackage.Literals.WORKSPACE_ELEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_ELEM__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Root getAsignaturas() {
		if (asignaturas != null && asignaturas.eIsProxy()) {
			InternalEObject oldAsignaturas = (InternalEObject)asignaturas;
			asignaturas = (Root)eResolveProxy(oldAsignaturas);
			if (asignaturas != oldAsignaturas) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkspacePackage.WORKSPACE_ELEM__ASIGNATURAS, oldAsignaturas, asignaturas));
			}
		}
		return asignaturas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root basicGetAsignaturas() {
		return asignaturas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsignaturas(Root newAsignaturas) {
		Root oldAsignaturas = asignaturas;
		asignaturas = newAsignaturas;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_ELEM__ASIGNATURAS, oldAsignaturas, asignaturas));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Estudiantes.Root getEstudiante() {
		if (estudiante != null && estudiante.eIsProxy()) {
			InternalEObject oldEstudiante = (InternalEObject)estudiante;
			estudiante = (Estudiantes.Root)eResolveProxy(oldEstudiante);
			if (estudiante != oldEstudiante) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkspacePackage.WORKSPACE_ELEM__ESTUDIANTE, oldEstudiante, estudiante));
			}
		}
		return estudiante;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Estudiantes.Root basicGetEstudiante() {
		return estudiante;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEstudiante(Estudiantes.Root newEstudiante) {
		Estudiantes.Root oldEstudiante = estudiante;
		estudiante = newEstudiante;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_ELEM__ESTUDIANTE, oldEstudiante, estudiante));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getID() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setID(String newID) {
		String oldID = id;
		id = newID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WorkspacePackage.WORKSPACE_ELEM__ID, oldID, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WorkspacePackage.WORKSPACE_ELEM__PASSWORD:
				return getPassword();
			case WorkspacePackage.WORKSPACE_ELEM__ASIGNATURAS:
				if (resolve) return getAsignaturas();
				return basicGetAsignaturas();
			case WorkspacePackage.WORKSPACE_ELEM__ESTUDIANTE:
				if (resolve) return getEstudiante();
				return basicGetEstudiante();
			case WorkspacePackage.WORKSPACE_ELEM__ID:
				return getID();
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
			case WorkspacePackage.WORKSPACE_ELEM__PASSWORD:
				setPassword((String)newValue);
				return;
			case WorkspacePackage.WORKSPACE_ELEM__ASIGNATURAS:
				setAsignaturas((Root)newValue);
				return;
			case WorkspacePackage.WORKSPACE_ELEM__ESTUDIANTE:
				setEstudiante((Estudiantes.Root)newValue);
				return;
			case WorkspacePackage.WORKSPACE_ELEM__ID:
				setID((String)newValue);
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
			case WorkspacePackage.WORKSPACE_ELEM__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case WorkspacePackage.WORKSPACE_ELEM__ASIGNATURAS:
				setAsignaturas((Root)null);
				return;
			case WorkspacePackage.WORKSPACE_ELEM__ESTUDIANTE:
				setEstudiante((Estudiantes.Root)null);
				return;
			case WorkspacePackage.WORKSPACE_ELEM__ID:
				setID(ID_EDEFAULT);
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
			case WorkspacePackage.WORKSPACE_ELEM__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case WorkspacePackage.WORKSPACE_ELEM__ASIGNATURAS:
				return asignaturas != null;
			case WorkspacePackage.WORKSPACE_ELEM__ESTUDIANTE:
				return estudiante != null;
			case WorkspacePackage.WORKSPACE_ELEM__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (Password: ");
		result.append(password);
		result.append(", ID: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //WorkspaceElemImpl
