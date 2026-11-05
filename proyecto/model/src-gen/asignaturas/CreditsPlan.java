/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Credits Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.CreditsPlan#getMinCredits <em>Min Credits</em>}</li>
 *   <li>{@link asignaturas.CreditsPlan#getGroupOfSubjects <em>Group Of Subjects</em>}</li>
 *   <li>{@link asignaturas.CreditsPlan#getCurricularunit <em>Curricularunit</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCreditsPlan()
 * @model
 * @generated
 */
public interface CreditsPlan extends Plan {
	/**
	 * Returns the value of the '<em><b>Min Credits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Credits</em>' attribute.
	 * @see #setMinCredits(int)
	 * @see asignaturas.AsignaturasPackage#getCreditsPlan_MinCredits()
	 * @model
	 * @generated
	 */
	int getMinCredits();

	/**
	 * Sets the value of the '{@link asignaturas.CreditsPlan#getMinCredits <em>Min Credits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Credits</em>' attribute.
	 * @see #getMinCredits()
	 * @generated
	 */
	void setMinCredits(int value);

	/**
	 * Returns the value of the '<em><b>Group Of Subjects</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.Subject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Of Subjects</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCreditsPlan_GroupOfSubjects()
	 * @model containment="true"
	 * @generated
	 */
	EList<Subject> getGroupOfSubjects();

	/**
	 * Returns the value of the '<em><b>Curricularunit</b></em>' reference list.
	 * The list contents are of type {@link asignaturas.CurricularUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Curricularunit</em>' reference list.
	 * @see asignaturas.AsignaturasPackage#getCreditsPlan_Curricularunit()
	 * @model
	 * @generated
	 */
	EList<CurricularUnit> getCurricularunit();

} // CreditsPlan
