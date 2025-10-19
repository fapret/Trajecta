/**
 */
package asignaturas;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Course Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.CourseEvaluation#getCourse <em>Course</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCourseEvaluation()
 * @model
 * @generated
 */
public interface CourseEvaluation extends Evaluation {
	/**
	 * Returns the value of the '<em><b>Course</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asignaturas.Course#getCourseEvaluation <em>Course Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course</em>' container reference.
	 * @see #setCourse(Course)
	 * @see asignaturas.AsignaturasPackage#getCourseEvaluation_Course()
	 * @see asignaturas.Course#getCourseEvaluation
	 * @model opposite="CourseEvaluation" required="true" transient="false"
	 * @generated
	 */
	Course getCourse();

	/**
	 * Sets the value of the '{@link asignaturas.CourseEvaluation#getCourse <em>Course</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Course</em>' container reference.
	 * @see #getCourse()
	 * @generated
	 */
	void setCourse(Course value);

} // CourseEvaluation
