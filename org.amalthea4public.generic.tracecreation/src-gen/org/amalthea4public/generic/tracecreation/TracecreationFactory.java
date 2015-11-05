/**
 */
package org.amalthea4public.generic.tracecreation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.amalthea4public.generic.tracecreation.TracecreationPackage
 * @generated
 */
public interface TracecreationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracecreationFactory eINSTANCE = org.amalthea4public.generic.tracecreation.impl.TracecreationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Artifact Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Artifact Wrapper</em>'.
	 * @generated
	 */
	ArtifactWrapper createArtifactWrapper();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TracecreationPackage getTracecreationPackage();

} //TracecreationFactory
