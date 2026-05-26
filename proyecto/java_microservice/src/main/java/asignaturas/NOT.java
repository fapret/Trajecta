/**
 */
package asignaturas;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NOT</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.NOT#getRequirement <em>Requirement</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getNOT()
 * @model
 * @generated
 */
public interface NOT extends Requirement {
	/**
	 * Returns the value of the '<em><b>Requirement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirement</em>' containment reference.
	 * @see #setRequirement(Requirement)
	 * @see asignaturas.AsignaturasPackage#getNOT_Requirement()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Requirement getRequirement();

	/**
	 * Sets the value of the '{@link asignaturas.NOT#getRequirement <em>Requirement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requirement</em>' containment reference.
	 * @see #getRequirement()
	 * @generated
	 */
	void setRequirement(Requirement value);

} // NOT
