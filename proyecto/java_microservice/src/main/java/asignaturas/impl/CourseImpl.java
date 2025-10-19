/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.Course;
import asignaturas.CourseEvaluation;
import asignaturas.CurricularUnit;
import asignaturas.PartialEvaluation;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.CourseImpl#getYear <em>Year</em>}</li>
 *   <li>{@link asignaturas.impl.CourseImpl#getEdition <em>Edition</em>}</li>
 *   <li>{@link asignaturas.impl.CourseImpl#getCourseEvaluation <em>Course Evaluation</em>}</li>
 *   <li>{@link asignaturas.impl.CourseImpl#getCurricularunit <em>Curricularunit</em>}</li>
 *   <li>{@link asignaturas.impl.CourseImpl#getPartialevaluation <em>Partialevaluation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CourseImpl extends MinimalEObjectImpl.Container implements Course {
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
	 * The default value of the '{@link #getEdition() <em>Edition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdition()
	 * @generated
	 * @ordered
	 */
	protected static final int EDITION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEdition() <em>Edition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdition()
	 * @generated
	 * @ordered
	 */
	protected int edition = EDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCourseEvaluation() <em>Course Evaluation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCourseEvaluation()
	 * @generated
	 * @ordered
	 */
	protected EList<CourseEvaluation> courseEvaluation;

	/**
	 * The cached value of the '{@link #getPartialevaluation() <em>Partialevaluation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartialevaluation()
	 * @generated
	 * @ordered
	 */
	protected EList<PartialEvaluation> partialevaluation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CourseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.COURSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getYear() {
		return year;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setYear(int newYear) {
		int oldYear = year;
		year = newYear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.COURSE__YEAR, oldYear, year));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getEdition() {
		return edition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEdition(int newEdition) {
		int oldEdition = edition;
		edition = newEdition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.COURSE__EDITION, oldEdition, edition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CourseEvaluation> getCourseEvaluation() {
		if (courseEvaluation == null) {
			courseEvaluation = new EObjectContainmentWithInverseEList<CourseEvaluation>(CourseEvaluation.class, this, AsignaturasPackage.COURSE__COURSE_EVALUATION, AsignaturasPackage.COURSE_EVALUATION__COURSE);
		}
		return courseEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurricularUnit getCurricularunit() {
		if (eContainerFeatureID() != AsignaturasPackage.COURSE__CURRICULARUNIT) return null;
		return (CurricularUnit)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCurricularunit(CurricularUnit newCurricularunit, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCurricularunit, AsignaturasPackage.COURSE__CURRICULARUNIT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCurricularunit(CurricularUnit newCurricularunit) {
		if (newCurricularunit != eInternalContainer() || (eContainerFeatureID() != AsignaturasPackage.COURSE__CURRICULARUNIT && newCurricularunit != null)) {
			if (EcoreUtil.isAncestor(this, newCurricularunit))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCurricularunit != null)
				msgs = ((InternalEObject)newCurricularunit).eInverseAdd(this, AsignaturasPackage.CURRICULAR_UNIT__COURSE, CurricularUnit.class, msgs);
			msgs = basicSetCurricularunit(newCurricularunit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.COURSE__CURRICULARUNIT, newCurricularunit, newCurricularunit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PartialEvaluation> getPartialevaluation() {
		if (partialevaluation == null) {
			partialevaluation = new EObjectContainmentWithInverseEList<PartialEvaluation>(PartialEvaluation.class, this, AsignaturasPackage.COURSE__PARTIALEVALUATION, AsignaturasPackage.PARTIAL_EVALUATION__COURSE);
		}
		return partialevaluation;
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
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCourseEvaluation()).basicAdd(otherEnd, msgs);
			case AsignaturasPackage.COURSE__CURRICULARUNIT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCurricularunit((CurricularUnit)otherEnd, msgs);
			case AsignaturasPackage.COURSE__PARTIALEVALUATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPartialevaluation()).basicAdd(otherEnd, msgs);
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
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				return ((InternalEList<?>)getCourseEvaluation()).basicRemove(otherEnd, msgs);
			case AsignaturasPackage.COURSE__CURRICULARUNIT:
				return basicSetCurricularunit(null, msgs);
			case AsignaturasPackage.COURSE__PARTIALEVALUATION:
				return ((InternalEList<?>)getPartialevaluation()).basicRemove(otherEnd, msgs);
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
			case AsignaturasPackage.COURSE__CURRICULARUNIT:
				return eInternalContainer().eInverseRemove(this, AsignaturasPackage.CURRICULAR_UNIT__COURSE, CurricularUnit.class, msgs);
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
			case AsignaturasPackage.COURSE__YEAR:
				return getYear();
			case AsignaturasPackage.COURSE__EDITION:
				return getEdition();
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				return getCourseEvaluation();
			case AsignaturasPackage.COURSE__CURRICULARUNIT:
				return getCurricularunit();
			case AsignaturasPackage.COURSE__PARTIALEVALUATION:
				return getPartialevaluation();
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
			case AsignaturasPackage.COURSE__YEAR:
				setYear((Integer)newValue);
				return;
			case AsignaturasPackage.COURSE__EDITION:
				setEdition((Integer)newValue);
				return;
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				getCourseEvaluation().clear();
				getCourseEvaluation().addAll((Collection<? extends CourseEvaluation>)newValue);
				return;
			case AsignaturasPackage.COURSE__CURRICULARUNIT:
				setCurricularunit((CurricularUnit)newValue);
				return;
			case AsignaturasPackage.COURSE__PARTIALEVALUATION:
				getPartialevaluation().clear();
				getPartialevaluation().addAll((Collection<? extends PartialEvaluation>)newValue);
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
			case AsignaturasPackage.COURSE__YEAR:
				setYear(YEAR_EDEFAULT);
				return;
			case AsignaturasPackage.COURSE__EDITION:
				setEdition(EDITION_EDEFAULT);
				return;
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				getCourseEvaluation().clear();
				return;
			case AsignaturasPackage.COURSE__CURRICULARUNIT:
				setCurricularunit((CurricularUnit)null);
				return;
			case AsignaturasPackage.COURSE__PARTIALEVALUATION:
				getPartialevaluation().clear();
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
			case AsignaturasPackage.COURSE__YEAR:
				return year != YEAR_EDEFAULT;
			case AsignaturasPackage.COURSE__EDITION:
				return edition != EDITION_EDEFAULT;
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				return courseEvaluation != null && !courseEvaluation.isEmpty();
			case AsignaturasPackage.COURSE__CURRICULARUNIT:
				return getCurricularunit() != null;
			case AsignaturasPackage.COURSE__PARTIALEVALUATION:
				return partialevaluation != null && !partialevaluation.isEmpty();
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
		result.append(" (Year: ");
		result.append(year);
		result.append(", Edition: ");
		result.append(edition);
		result.append(')');
		return result.toString();
	}

} //CourseImpl
