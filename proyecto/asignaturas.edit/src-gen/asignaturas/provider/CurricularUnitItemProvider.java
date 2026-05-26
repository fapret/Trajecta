/**
 */
package asignaturas.provider;

import asignaturas.AsignaturasFactory;
import asignaturas.AsignaturasPackage;
import asignaturas.CurricularUnit;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

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
 * This is the item provider adapter for a {@link asignaturas.CurricularUnit} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CurricularUnitItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CurricularUnitItemProvider(AdapterFactory adapterFactory) {
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

			addIdPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addCredPropertyDescriptor(object);
			addValidPropertyDescriptor(object);
			addRequirementPropertyDescriptor(object);
			addGradescalePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CurricularUnit_Id_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CurricularUnit_Id_feature",
								"_UI_CurricularUnit_type"),
						AsignaturasPackage.Literals.CURRICULAR_UNIT__ID, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
						getResourceLocator(), getString("_UI_CurricularUnit_Name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CurricularUnit_Name_feature",
								"_UI_CurricularUnit_type"),
						AsignaturasPackage.Literals.CURRICULAR_UNIT__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Cred feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCredPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CurricularUnit_Cred_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CurricularUnit_Cred_feature",
								"_UI_CurricularUnit_type"),
						AsignaturasPackage.Literals.CURRICULAR_UNIT__CRED, true, false, false,
						ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Valid feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValidPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CurricularUnit_Valid_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CurricularUnit_Valid_feature",
								"_UI_CurricularUnit_type"),
						AsignaturasPackage.Literals.CURRICULAR_UNIT__VALID, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Requirement feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRequirementPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CurricularUnit_Requirement_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CurricularUnit_Requirement_feature",
								"_UI_CurricularUnit_type"),
						AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Gradescale feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGradescalePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CurricularUnit_gradescale_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CurricularUnit_gradescale_feature",
								"_UI_CurricularUnit_type"),
						AsignaturasPackage.Literals.CURRICULAR_UNIT__GRADESCALE, true, false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT);
			childrenFeatures.add(AsignaturasPackage.Literals.CURRICULAR_UNIT__COURSE);
			childrenFeatures.add(AsignaturasPackage.Literals.CURRICULAR_UNIT__EXAM_EVALUATION);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns CurricularUnit.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CurricularUnit"));
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
		String label = ((CurricularUnit) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_CurricularUnit_type")
				: getString("_UI_CurricularUnit_type") + " " + label;
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

		switch (notification.getFeatureID(CurricularUnit.class)) {
		case AsignaturasPackage.CURRICULAR_UNIT__ID:
		case AsignaturasPackage.CURRICULAR_UNIT__NAME:
		case AsignaturasPackage.CURRICULAR_UNIT__CRED:
		case AsignaturasPackage.CURRICULAR_UNIT__VALID:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AsignaturasPackage.CURRICULAR_UNIT__REQUIREMENT:
		case AsignaturasPackage.CURRICULAR_UNIT__COURSE:
		case AsignaturasPackage.CURRICULAR_UNIT__EXAM_EVALUATION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT,
				AsignaturasFactory.eINSTANCE.createSomeOf()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT,
				AsignaturasFactory.eINSTANCE.createNOT()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT,
				AsignaturasFactory.eINSTANCE.createCoursed()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT,
				AsignaturasFactory.eINSTANCE.createExam()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT,
				AsignaturasFactory.eINSTANCE.createCreditsOnPlan()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT,
				AsignaturasFactory.eINSTANCE.createCreditsOnSubject()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__REQUIREMENT,
				AsignaturasFactory.eINSTANCE.createRegisteredTo()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__COURSE,
				AsignaturasFactory.eINSTANCE.createCourse()));

		newChildDescriptors.add(createChildParameter(AsignaturasPackage.Literals.CURRICULAR_UNIT__EXAM_EVALUATION,
				AsignaturasFactory.eINSTANCE.createExamEvaluation()));
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
