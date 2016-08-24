/**
 */
package org.eclipse.capra.GenericTraceMetamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.capra.GenericTraceMetamodel.GenericTraceMetamodelFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel basePackage='org.eclipse.capra'"
 * @generated
 */
public interface GenericTraceMetamodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "GenericTraceMetamodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.eclipse.capra.GenericTraceMetamodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "GenericTraceMetamodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GenericTraceMetamodelPackage eINSTANCE = org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceMetamodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceModelImpl <em>Generic Trace Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceModelImpl
	 * @see org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceMetamodelPackageImpl#getGenericTraceModel()
	 * @generated
	 */
	int GENERIC_TRACE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE_MODEL__TRACES = 0;

	/**
	 * The number of structural features of the '<em>Generic Trace Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Generic Trace Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.capra.GenericTraceMetamodel.impl.RelatedToImpl <em>Related To</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.capra.GenericTraceMetamodel.impl.RelatedToImpl
	 * @see org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceMetamodelPackageImpl#getRelatedTo()
	 * @generated
	 */
	int RELATED_TO = 1;

	/**
	 * The feature id for the '<em><b>Item</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_TO__ITEM = 0;

	/**
	 * The number of structural features of the '<em>Related To</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_TO_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Related To</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATED_TO_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.capra.GenericTraceMetamodel.GenericTraceModel <em>Generic Trace Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Trace Model</em>'.
	 * @see org.eclipse.capra.GenericTraceMetamodel.GenericTraceModel
	 * @generated
	 */
	EClass getGenericTraceModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.capra.GenericTraceMetamodel.GenericTraceModel#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traces</em>'.
	 * @see org.eclipse.capra.GenericTraceMetamodel.GenericTraceModel#getTraces()
	 * @see #getGenericTraceModel()
	 * @generated
	 */
	EReference getGenericTraceModel_Traces();

	/**
	 * Returns the meta object for class '{@link org.eclipse.capra.GenericTraceMetamodel.RelatedTo <em>Related To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Related To</em>'.
	 * @see org.eclipse.capra.GenericTraceMetamodel.RelatedTo
	 * @generated
	 */
	EClass getRelatedTo();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.capra.GenericTraceMetamodel.RelatedTo#getItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Item</em>'.
	 * @see org.eclipse.capra.GenericTraceMetamodel.RelatedTo#getItem()
	 * @see #getRelatedTo()
	 * @generated
	 */
	EReference getRelatedTo_Item();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GenericTraceMetamodelFactory getGenericTraceMetamodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceModelImpl <em>Generic Trace Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceModelImpl
		 * @see org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceMetamodelPackageImpl#getGenericTraceModel()
		 * @generated
		 */
		EClass GENERIC_TRACE_MODEL = eINSTANCE.getGenericTraceModel();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_TRACE_MODEL__TRACES = eINSTANCE.getGenericTraceModel_Traces();

		/**
		 * The meta object literal for the '{@link org.eclipse.capra.GenericTraceMetamodel.impl.RelatedToImpl <em>Related To</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.capra.GenericTraceMetamodel.impl.RelatedToImpl
		 * @see org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceMetamodelPackageImpl#getRelatedTo()
		 * @generated
		 */
		EClass RELATED_TO = eINSTANCE.getRelatedTo();

		/**
		 * The meta object literal for the '<em><b>Item</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATED_TO__ITEM = eINSTANCE.getRelatedTo_Item();

	}

} //GenericTraceMetamodelPackage
