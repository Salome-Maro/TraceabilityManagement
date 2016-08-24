/**
 */
package org.eclipse.capra.GenericTraceMetamodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.capra.GenericTraceMetamodel.GenericTraceMetamodelPackage
 * @generated
 */
public interface GenericTraceMetamodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GenericTraceMetamodelFactory eINSTANCE = org.eclipse.capra.GenericTraceMetamodel.impl.GenericTraceMetamodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Generic Trace Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Trace Model</em>'.
	 * @generated
	 */
	GenericTraceModel createGenericTraceModel();

	/**
	 * Returns a new object of class '<em>Related To</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Related To</em>'.
	 * @generated
	 */
	RelatedTo createRelatedTo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	GenericTraceMetamodelPackage getGenericTraceMetamodelPackage();

} //GenericTraceMetamodelFactory
