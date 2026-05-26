/**
 */
package asignaturas.provider;

import asignaturas.AsignaturasPackage;
import asignaturas.GradeScale;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link asignaturas.GradeScale} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GradeScaleItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GradeScaleItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addExamApprovalGradePropertyDescriptor(object);
			addTutoringApprovalGradePropertyDescriptor(object);
			addCourseApprovalGradePropertyDescriptor(object);
			addCoursePartialApprovalGradePropertyDescriptor(object);
			addPartialApprovalGradePropertyDescriptor(object);
			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Exam Approval Grade feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addExamApprovalGradePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GradeScale_ExamApprovalGrade_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GradeScale_ExamApprovalGrade_feature",
								"_UI_GradeScale_type"),
						AsignaturasPackage.Literals.GRADE_SCALE__EXAM_APPROVAL_GRADE, true, false, false,
						ItemPropertyDescriptor.REAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Tutoring Approval Grade feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTutoringApprovalGradePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_GradeScale_TutoringApprovalGrade_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_GradeScale_TutoringApprovalGrade_feature",
						"_UI_GradeScale_type"),
				AsignaturasPackage.Literals.GRADE_SCALE__TUTORING_APPROVAL_GRADE, true, false, false,
				ItemPropertyDescriptor.REAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Course Approval Grade feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCourseApprovalGradePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GradeScale_CourseApprovalGrade_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GradeScale_CourseApprovalGrade_feature",
								"_UI_GradeScale_type"),
						AsignaturasPackage.Literals.GRADE_SCALE__COURSE_APPROVAL_GRADE, true, false, false,
						ItemPropertyDescriptor.REAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Course Partial Approval Grade feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCoursePartialApprovalGradePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_GradeScale_CoursePartialApprovalGrade_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_GradeScale_CoursePartialApprovalGrade_feature",
						"_UI_GradeScale_type"),
				AsignaturasPackage.Literals.GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE, true, false, false,
				ItemPropertyDescriptor.REAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Partial Approval Grade feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPartialApprovalGradePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GradeScale_PartialApprovalGrade_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GradeScale_PartialApprovalGrade_feature",
								"_UI_GradeScale_type"),
						AsignaturasPackage.Literals.GRADE_SCALE__PARTIAL_APPROVAL_GRADE, true, false, false,
						ItemPropertyDescriptor.REAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GradeScale_Name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GradeScale_Name_feature",
								"_UI_GradeScale_type"),
						AsignaturasPackage.Literals.GRADE_SCALE__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns GradeScale.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GradeScale"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((GradeScale) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_GradeScale_type")
				: getString("_UI_GradeScale_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(GradeScale.class)) {
		case AsignaturasPackage.GRADE_SCALE__EXAM_APPROVAL_GRADE:
		case AsignaturasPackage.GRADE_SCALE__TUTORING_APPROVAL_GRADE:
		case AsignaturasPackage.GRADE_SCALE__COURSE_APPROVAL_GRADE:
		case AsignaturasPackage.GRADE_SCALE__COURSE_PARTIAL_APPROVAL_GRADE:
		case AsignaturasPackage.GRADE_SCALE__PARTIAL_APPROVAL_GRADE:
		case AsignaturasPackage.GRADE_SCALE__NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AsignaturasEditPlugin.INSTANCE;
	}

}
