/**
 */
package asignaturas;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Credits On Subject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.CreditsOnSubject#getCred <em>Cred</em>}</li>
 *   <li>{@link asignaturas.CreditsOnSubject#getGroupOfSubjects <em>Group Of Subjects</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCreditsOnSubject()
 * @model
 * @generated
 */
public interface CreditsOnSubject extends Requirement {
	/**
	 * Returns the value of the '<em><b>Cred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cred</em>' attribute.
	 * @see #setCred(int)
	 * @see asignaturas.AsignaturasPackage#getCreditsOnSubject_Cred()
	 * @model required="true"
	 * @generated
	 */
	int getCred();

	/**
	 * Sets the value of the '{@link asignaturas.CreditsOnSubject#getCred <em>Cred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cred</em>' attribute.
	 * @see #getCred()
	 * @generated
	 */
	void setCred(int value);

	/**
	 * Returns the value of the '<em><b>Group Of Subjects</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Of Subjects</em>' reference.
	 * @see #setGroupOfSubjects(Subject)
	 * @see asignaturas.AsignaturasPackage#getCreditsOnSubject_GroupOfSubjects()
	 * @model required="true"
	 * @generated
	 */
	Subject getGroupOfSubjects();

	/**
	 * Sets the value of the '{@link asignaturas.CreditsOnSubject#getGroupOfSubjects <em>Group Of Subjects</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Of Subjects</em>' reference.
	 * @see #getGroupOfSubjects()
	 * @generated
	 */
	void setGroupOfSubjects(Subject value);

} // CreditsOnSubject
