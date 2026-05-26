/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Curricular Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.CurricularUnit#getId <em>Id</em>}</li>
 *   <li>{@link asignaturas.CurricularUnit#getName <em>Name</em>}</li>
 *   <li>{@link asignaturas.CurricularUnit#getCred <em>Cred</em>}</li>
 *   <li>{@link asignaturas.CurricularUnit#isValid <em>Valid</em>}</li>
 *   <li>{@link asignaturas.CurricularUnit#getRequirement <em>Requirement</em>}</li>
 *   <li>{@link asignaturas.CurricularUnit#getCourse <em>Course</em>}</li>
 *   <li>{@link asignaturas.CurricularUnit#getExamEvaluation <em>Exam Evaluation</em>}</li>
 *   <li>{@link asignaturas.CurricularUnit#getGradescale <em>Gradescale</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCurricularUnit()
 * @model
 * @generated
 */
public interface CurricularUnit extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link asignaturas.CurricularUnit#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link asignaturas.CurricularUnit#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Cred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cred</em>' attribute.
	 * @see #setCred(int)
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_Cred()
	 * @model
	 * @generated
	 */
	int getCred();

	/**
	 * Sets the value of the '{@link asignaturas.CurricularUnit#getCred <em>Cred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cred</em>' attribute.
	 * @see #getCred()
	 * @generated
	 */
	void setCred(int value);

	/**
	 * Returns the value of the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid</em>' attribute.
	 * @see #setValid(boolean)
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_Valid()
	 * @model
	 * @generated
	 */
	boolean isValid();

	/**
	 * Sets the value of the '{@link asignaturas.CurricularUnit#isValid <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid</em>' attribute.
	 * @see #isValid()
	 * @generated
	 */
	void setValid(boolean value);

	/**
	 * Returns the value of the '<em><b>Requirement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirement</em>' containment reference.
	 * @see #setRequirement(Requirement)
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_Requirement()
	 * @model containment="true"
	 * @generated
	 */
	Requirement getRequirement();

	/**
	 * Sets the value of the '{@link asignaturas.CurricularUnit#getRequirement <em>Requirement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requirement</em>' containment reference.
	 * @see #getRequirement()
	 * @generated
	 */
	void setRequirement(Requirement value);

	/**
	 * Returns the value of the '<em><b>Course</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.Course}.
	 * It is bidirectional and its opposite is '{@link asignaturas.Course#getCurricularunit <em>Curricularunit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_Course()
	 * @see asignaturas.Course#getCurricularunit
	 * @model opposite="curricularunit" containment="true"
	 * @generated
	 */
	EList<Course> getCourse();

	/**
	 * Returns the value of the '<em><b>Exam Evaluation</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.ExamEvaluation}.
	 * It is bidirectional and its opposite is '{@link asignaturas.ExamEvaluation#getCurricularunit <em>Curricularunit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exam Evaluation</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_ExamEvaluation()
	 * @see asignaturas.ExamEvaluation#getCurricularunit
	 * @model opposite="curricularunit" containment="true"
	 * @generated
	 */
	EList<ExamEvaluation> getExamEvaluation();

	/**
	 * Returns the value of the '<em><b>Gradescale</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gradescale</em>' reference.
	 * @see #setGradescale(GradeScale)
	 * @see asignaturas.AsignaturasPackage#getCurricularUnit_Gradescale()
	 * @model
	 * @generated
	 */
	GradeScale getGradescale();

	/**
	 * Sets the value of the '{@link asignaturas.CurricularUnit#getGradescale <em>Gradescale</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gradescale</em>' reference.
	 * @see #getGradescale()
	 * @generated
	 */
	void setGradescale(GradeScale value);

} // CurricularUnit
