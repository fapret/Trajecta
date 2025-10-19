/**
 */
package asignaturas;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Credits On Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.CreditsOnPlan#getCred <em>Cred</em>}</li>
 *   <li>{@link asignaturas.CreditsOnPlan#getCreditsPlan <em>Credits Plan</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCreditsOnPlan()
 * @model
 * @generated
 */
public interface CreditsOnPlan extends Requirement {
	/**
	 * Returns the value of the '<em><b>Cred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cred</em>' attribute.
	 * @see #setCred(int)
	 * @see asignaturas.AsignaturasPackage#getCreditsOnPlan_Cred()
	 * @model required="true"
	 * @generated
	 */
	int getCred();

	/**
	 * Sets the value of the '{@link asignaturas.CreditsOnPlan#getCred <em>Cred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cred</em>' attribute.
	 * @see #getCred()
	 * @generated
	 */
	void setCred(int value);

	/**
	 * Returns the value of the '<em><b>Credits Plan</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Credits Plan</em>' reference.
	 * @see #setCreditsPlan(CreditsPlan)
	 * @see asignaturas.AsignaturasPackage#getCreditsOnPlan_CreditsPlan()
	 * @model required="true"
	 * @generated
	 */
	CreditsPlan getCreditsPlan();

	/**
	 * Sets the value of the '{@link asignaturas.CreditsOnPlan#getCreditsPlan <em>Credits Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Credits Plan</em>' reference.
	 * @see #getCreditsPlan()
	 * @generated
	 */
	void setCreditsPlan(CreditsPlan value);

} // CreditsOnPlan
