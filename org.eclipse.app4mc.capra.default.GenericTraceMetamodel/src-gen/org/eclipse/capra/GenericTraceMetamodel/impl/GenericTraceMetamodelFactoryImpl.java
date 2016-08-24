/**
 */
package org.eclipse.capra.GenericTraceMetamodel.impl;

import org.eclipse.capra.GenericTraceMetamodel.*;

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
public class GenericTraceMetamodelFactoryImpl extends EFactoryImpl implements GenericTraceMetamodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenericTraceMetamodelFactory init() {
		try {
			GenericTraceMetamodelFactory theGenericTraceMetamodelFactory = (GenericTraceMetamodelFactory)EPackage.Registry.INSTANCE.getEFactory(GenericTraceMetamodelPackage.eNS_URI);
			if (theGenericTraceMetamodelFactory != null) {
				return theGenericTraceMetamodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GenericTraceMetamodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericTraceMetamodelFactoryImpl() {
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
			case GenericTraceMetamodelPackage.GENERIC_TRACE_MODEL: return createGenericTraceModel();
			case GenericTraceMetamodelPackage.RELATED_TO: return createRelatedTo();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericTraceModel createGenericTraceModel() {
		GenericTraceModelImpl genericTraceModel = new GenericTraceModelImpl();
		return genericTraceModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelatedTo createRelatedTo() {
		RelatedToImpl relatedTo = new RelatedToImpl();
		return relatedTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericTraceMetamodelPackage getGenericTraceMetamodelPackage() {
		return (GenericTraceMetamodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GenericTraceMetamodelPackage getPackage() {
		return GenericTraceMetamodelPackage.eINSTANCE;
	}

} //GenericTraceMetamodelFactoryImpl
