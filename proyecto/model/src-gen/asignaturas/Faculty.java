/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Faculty</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.Faculty#getName <em>Name</em>}</li>
 *   <li>{@link asignaturas.Faculty#getCareers <em>Careers</em>}</li>
 *   <li>{@link asignaturas.Faculty#getFacultyCU <em>Faculty CU</em>}</li>
 *   <li>{@link asignaturas.Faculty#getGradescale <em>Gradescale</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getFaculty()
 * @model
 * @generated
 */
public interface Faculty extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see asignaturas.AsignaturasPackage#getFaculty_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link asignaturas.Faculty#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Careers</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.Career}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Careers</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getFaculty_Careers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Career> getCareers();

	/**
	 * Returns the value of the '<em><b>Faculty CU</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.CurricularUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Faculty CU</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getFaculty_FacultyCU()
	 * @model containment="true"
	 * @generated
	 */
	EList<CurricularUnit> getFacultyCU();

	/**
	 * Returns the value of the '<em><b>Gradescale</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.GradeScale}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gradescale</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getFaculty_Gradescale()
	 * @model containment="true"
	 * @generated
	 */
	EList<GradeScale> getGradescale();

} // Faculty
