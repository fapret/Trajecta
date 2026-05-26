/**
 */
package asignaturas;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grade Scale</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.GradeScale#getExamApprovalGrade <em>Exam Approval Grade</em>}</li>
 *   <li>{@link asignaturas.GradeScale#getTutoringApprovalGrade <em>Tutoring Approval Grade</em>}</li>
 *   <li>{@link asignaturas.GradeScale#getCourseApprovalGrade <em>Course Approval Grade</em>}</li>
 *   <li>{@link asignaturas.GradeScale#getCoursePartialApprovalGrade <em>Course Partial Approval Grade</em>}</li>
 *   <li>{@link asignaturas.GradeScale#getPartialApprovalGrade <em>Partial Approval Grade</em>}</li>
 *   <li>{@link asignaturas.GradeScale#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getGradeScale()
 * @model
 * @generated
 */
public interface GradeScale extends EObject {
	/**
	 * Returns the value of the '<em><b>Exam Approval Grade</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exam Approval Grade</em>' attribute.
	 * @see #setExamApprovalGrade(float)
	 * @see asignaturas.AsignaturasPackage#getGradeScale_ExamApprovalGrade()
	 * @model default="3"
	 * @generated
	 */
	float getExamApprovalGrade();

	/**
	 * Sets the value of the '{@link asignaturas.GradeScale#getExamApprovalGrade <em>Exam Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exam Approval Grade</em>' attribute.
	 * @see #getExamApprovalGrade()
	 * @generated
	 */
	void setExamApprovalGrade(float value);

	/**
	 * Returns the value of the '<em><b>Tutoring Approval Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tutoring Approval Grade</em>' attribute.
	 * @see #setTutoringApprovalGrade(float)
	 * @see asignaturas.AsignaturasPackage#getGradeScale_TutoringApprovalGrade()
	 * @model
	 * @generated
	 */
	float getTutoringApprovalGrade();

	/**
	 * Sets the value of the '{@link asignaturas.GradeScale#getTutoringApprovalGrade <em>Tutoring Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tutoring Approval Grade</em>' attribute.
	 * @see #getTutoringApprovalGrade()
	 * @generated
	 */
	void setTutoringApprovalGrade(float value);

	/**
	 * Returns the value of the '<em><b>Course Approval Grade</b></em>' attribute.
	 * The default value is <code>"6"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course Approval Grade</em>' attribute.
	 * @see #setCourseApprovalGrade(float)
	 * @see asignaturas.AsignaturasPackage#getGradeScale_CourseApprovalGrade()
	 * @model default="6"
	 * @generated
	 */
	float getCourseApprovalGrade();

	/**
	 * Sets the value of the '{@link asignaturas.GradeScale#getCourseApprovalGrade <em>Course Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Course Approval Grade</em>' attribute.
	 * @see #getCourseApprovalGrade()
	 * @generated
	 */
	void setCourseApprovalGrade(float value);

	/**
	 * Returns the value of the '<em><b>Course Partial Approval Grade</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course Partial Approval Grade</em>' attribute.
	 * @see #setCoursePartialApprovalGrade(float)
	 * @see asignaturas.AsignaturasPackage#getGradeScale_CoursePartialApprovalGrade()
	 * @model default="3"
	 * @generated
	 */
	float getCoursePartialApprovalGrade();

	/**
	 * Sets the value of the '{@link asignaturas.GradeScale#getCoursePartialApprovalGrade <em>Course Partial Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Course Partial Approval Grade</em>' attribute.
	 * @see #getCoursePartialApprovalGrade()
	 * @generated
	 */
	void setCoursePartialApprovalGrade(float value);

	/**
	 * Returns the value of the '<em><b>Partial Approval Grade</b></em>' attribute.
	 * The default value is <code>"60"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partial Approval Grade</em>' attribute.
	 * @see #setPartialApprovalGrade(float)
	 * @see asignaturas.AsignaturasPackage#getGradeScale_PartialApprovalGrade()
	 * @model default="60"
	 * @generated
	 */
	float getPartialApprovalGrade();

	/**
	 * Sets the value of the '{@link asignaturas.GradeScale#getPartialApprovalGrade <em>Partial Approval Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partial Approval Grade</em>' attribute.
	 * @see #getPartialApprovalGrade()
	 * @generated
	 */
	void setPartialApprovalGrade(float value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see asignaturas.AsignaturasPackage#getGradeScale_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link asignaturas.GradeScale#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // GradeScale
