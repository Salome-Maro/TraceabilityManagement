/**
 */
package org.eclipse.capra.GenericArtifactMetamodel.impl;

import org.eclipse.capra.GenericArtifactMetamodel.*;

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
public class GenericArtifactMetamodelFactoryImpl extends EFactoryImpl implements GenericArtifactMetamodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenericArtifactMetamodelFactory init() {
		try {
			GenericArtifactMetamodelFactory theGenericArtifactMetamodelFactory = (GenericArtifactMetamodelFactory)EPackage.Registry.INSTANCE.getEFactory(GenericArtifactMetamodelPackage.eNS_URI);
			if (theGenericArtifactMetamodelFactory != null) {
				return theGenericArtifactMetamodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GenericArtifactMetamodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericArtifactMetamodelFactoryImpl() {
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
			case GenericArtifactMetamodelPackage.ARTIFACT_WRAPPER_CONTAINER: return createArtifactWrapperContainer();
			case GenericArtifactMetamodelPackage.ARTIFACT_WRAPPER: return createArtifactWrapper();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactWrapperContainer createArtifactWrapperContainer() {
		ArtifactWrapperContainerImpl artifactWrapperContainer = new ArtifactWrapperContainerImpl();
		return artifactWrapperContainer;
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
	public GenericArtifactMetamodelPackage getGenericArtifactMetamodelPackage() {
		return (GenericArtifactMetamodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GenericArtifactMetamodelPackage getPackage() {
		return GenericArtifactMetamodelPackage.eINSTANCE;
	}

} //GenericArtifactMetamodelFactoryImpl
