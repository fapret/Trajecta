/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.Course#getYear <em>Year</em>}</li>
 *   <li>{@link asignaturas.Course#getEdition <em>Edition</em>}</li>
 *   <li>{@link asignaturas.Course#getCourseEvaluation <em>Course Evaluation</em>}</li>
 *   <li>{@link asignaturas.Course#getCurricularunit <em>Curricularunit</em>}</li>
 *   <li>{@link asignaturas.Course#getPartialevaluation <em>Partialevaluation</em>}</li>
 *   <li>{@link asignaturas.Course#getTutoringevaluation <em>Tutoringevaluation</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCourse()
 * @model
 * @generated
 */
public interface Course extends EObject {
	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Year</em>' attribute.
	 * @see #setYear(int)
	 * @see asignaturas.AsignaturasPackage#getCourse_Year()
	 * @model
	 * @generated
	 */
	int getYear();

	/**
	 * Sets the value of the '{@link asignaturas.Course#getYear <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Year</em>' attribute.
	 * @see #getYear()
	 * @generated
	 */
	void setYear(int value);

	/**
	 * Returns the value of the '<em><b>Edition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edition</em>' attribute.
	 * @see #setEdition(int)
	 * @see asignaturas.AsignaturasPackage#getCourse_Edition()
	 * @model
	 * @generated
	 */
	int getEdition();

	/**
	 * Sets the value of the '{@link asignaturas.Course#getEdition <em>Edition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edition</em>' attribute.
	 * @see #getEdition()
	 * @generated
	 */
	void setEdition(int value);

	/**
	 * Returns the value of the '<em><b>Course Evaluation</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.CourseEvaluation}.
	 * It is bidirectional and its opposite is '{@link asignaturas.CourseEvaluation#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course Evaluation</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCourse_CourseEvaluation()
	 * @see asignaturas.CourseEvaluation#getCourse
	 * @model opposite="course" containment="true"
	 * @generated
	 */
	EList<CourseEvaluation> getCourseEvaluation();

	/**
	 * Returns the value of the '<em><b>Curricularunit</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asignaturas.CurricularUnit#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Curricularunit</em>' container reference.
	 * @see #setCurricularunit(CurricularUnit)
	 * @see asignaturas.AsignaturasPackage#getCourse_Curricularunit()
	 * @see asignaturas.CurricularUnit#getCourse
	 * @model opposite="Course" required="true" transient="false"
	 * @generated
	 */
	CurricularUnit getCurricularunit();

	/**
	 * Sets the value of the '{@link asignaturas.Course#getCurricularunit <em>Curricularunit</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Curricularunit</em>' container reference.
	 * @see #getCurricularunit()
	 * @generated
	 */
	void setCurricularunit(CurricularUnit value);

	/**
	 * Returns the value of the '<em><b>Partialevaluation</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.PartialEvaluation}.
	 * It is bidirectional and its opposite is '{@link asignaturas.PartialEvaluation#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partialevaluation</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCourse_Partialevaluation()
	 * @see asignaturas.PartialEvaluation#getCourse
	 * @model opposite="course" containment="true"
	 * @generated
	 */
	EList<PartialEvaluation> getPartialevaluation();

	/**
	 * Returns the value of the '<em><b>Tutoringevaluation</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.TutoringEvaluation}.
	 * It is bidirectional and its opposite is '{@link asignaturas.TutoringEvaluation#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tutoringevaluation</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCourse_Tutoringevaluation()
	 * @see asignaturas.TutoringEvaluation#getCourse
	 * @model opposite="course" containment="true"
	 * @generated
	 */
	EList<TutoringEvaluation> getTutoringevaluation();

} // Course
