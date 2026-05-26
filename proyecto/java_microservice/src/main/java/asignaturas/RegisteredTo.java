/**
 */
package asignaturas;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Registered To</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.RegisteredTo#getCurricularUnit <em>Curricular Unit</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getRegisteredTo()
 * @model
 * @generated
 */
public interface RegisteredTo extends Requirement {
	/**
	 * Returns the value of the '<em><b>Curricular Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Curricular Unit</em>' reference.
	 * @see #setCurricularUnit(CurricularUnit)
	 * @see asignaturas.AsignaturasPackage#getRegisteredTo_CurricularUnit()
	 * @model required="true"
	 * @generated
	 */
	CurricularUnit getCurricularUnit();

	/**
	 * Sets the value of the '{@link asignaturas.RegisteredTo#getCurricularUnit <em>Curricular Unit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Curricular Unit</em>' reference.
	 * @see #getCurricularUnit()
	 * @generated
	 */
	void setCurricularUnit(CurricularUnit value);

} // RegisteredTo
