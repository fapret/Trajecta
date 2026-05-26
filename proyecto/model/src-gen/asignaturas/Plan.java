/**
 */
package asignaturas;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.Plan#getYear <em>Year</em>}</li>
 *   <li>{@link asignaturas.Plan#isValid <em>Valid</em>}</li>
 *   <li>{@link asignaturas.Plan#getCareer_parent <em>Career parent</em>}</li>
 *   <li>{@link asignaturas.Plan#getGradescale <em>Gradescale</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getPlan()
 * @model abstract="true"
 * @generated
 */
public interface Plan extends EObject {
	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Year</em>' attribute.
	 * @see #setYear(int)
	 * @see asignaturas.AsignaturasPackage#getPlan_Year()
	 * @model
	 * @generated
	 */
	int getYear();

	/**
	 * Sets the value of the '{@link asignaturas.Plan#getYear <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Year</em>' attribute.
	 * @see #getYear()
	 * @generated
	 */
	void setYear(int value);

	/**
	 * Returns the value of the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid</em>' attribute.
	 * @see #setValid(boolean)
	 * @see asignaturas.AsignaturasPackage#getPlan_Valid()
	 * @model
	 * @generated
	 */
	boolean isValid();

	/**
	 * Sets the value of the '{@link asignaturas.Plan#isValid <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid</em>' attribute.
	 * @see #isValid()
	 * @generated
	 */
	void setValid(boolean value);

	/**
	 * Returns the value of the '<em><b>Career parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asignaturas.Career#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Career parent</em>' container reference.
	 * @see #setCareer_parent(Career)
	 * @see asignaturas.AsignaturasPackage#getPlan_Career_parent()
	 * @see asignaturas.Career#getPlan
	 * @model opposite="plan" required="true" transient="false"
	 * @generated
	 */
	Career getCareer_parent();

	/**
	 * Sets the value of the '{@link asignaturas.Plan#getCareer_parent <em>Career parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Career parent</em>' container reference.
	 * @see #getCareer_parent()
	 * @generated
	 */
	void setCareer_parent(Career value);

	/**
	 * Returns the value of the '<em><b>Gradescale</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gradescale</em>' reference.
	 * @see #setGradescale(GradeScale)
	 * @see asignaturas.AsignaturasPackage#getPlan_Gradescale()
	 * @model required="true"
	 * @generated
	 */
	GradeScale getGradescale();

	/**
	 * Sets the value of the '{@link asignaturas.Plan#getGradescale <em>Gradescale</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gradescale</em>' reference.
	 * @see #getGradescale()
	 * @generated
	 */
	void setGradescale(GradeScale value);

} // Plan
