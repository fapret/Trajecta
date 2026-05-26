/**
 */
package asignaturas;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exam Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.ExamEvaluation#getCurricularunit <em>Curricularunit</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getExamEvaluation()
 * @model
 * @generated
 */
public interface ExamEvaluation extends Evaluation {
	/**
	 * Returns the value of the '<em><b>Curricularunit</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asignaturas.CurricularUnit#getExamEvaluation <em>Exam Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Curricularunit</em>' container reference.
	 * @see #setCurricularunit(CurricularUnit)
	 * @see asignaturas.AsignaturasPackage#getExamEvaluation_Curricularunit()
	 * @see asignaturas.CurricularUnit#getExamEvaluation
	 * @model opposite="ExamEvaluation" required="true" transient="false"
	 * @generated
	 */
	CurricularUnit getCurricularunit();

	/**
	 * Sets the value of the '{@link asignaturas.ExamEvaluation#getCurricularunit <em>Curricularunit</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Curricularunit</em>' container reference.
	 * @see #getCurricularunit()
	 * @generated
	 */
	void setCurricularunit(CurricularUnit value);

} // ExamEvaluation
