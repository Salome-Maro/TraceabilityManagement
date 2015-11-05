/**
 */
package org.amalthea4public.generic.tracecreation.impl;

import org.amalthea4public.generic.tracecreation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TracecreationFactoryImpl extends EFactoryImpl implements TracecreationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TracecreationFactory init() {
		try {
			TracecreationFactory theTracecreationFactory = (TracecreationFactory)EPackage.Registry.INSTANCE.getEFactory(TracecreationPackage.eNS_URI);
			if (theTracecreationFactory != null) {
				return theTracecreationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TracecreationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracecreationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TracecreationPackage.ARTIFACT_WRAPPER: return createArtifactWrapper();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactWrapper createArtifactWrapper() {
		ArtifactWrapperImpl artifactWrapper = new ArtifactWrapperImpl();
		return artifactWrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracecreationPackage getTracecreationPackage() {
		return (TracecreationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TracecreationPackage getPackage() {
		return TracecreationPackage.eINSTANCE;
	}

} //TracecreationFactoryImpl
