/*******************************************************************************
 * Copyright (c) 2016 Chalmers | University of Gothenburg, rt-labs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Chalmers | University of Gothenburg and rt-labs - initial API and implementation and/or initial documentation
 *******************************************************************************/
/**
 */
package org.eclipse.capra.GenericArtifactMetamodel;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see org.eclipse.capra.GenericArtifactMetamodel.GenericArtifactMetamodelFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel basePackage='org.eclipse.capra'"
 * @generated
 */
public interface GenericArtifactMetamodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "GenericArtifactMetamodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.eclipse.capra.GenericArtifactMetamodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "GenericArtifactMetamodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GenericArtifactMetamodelPackage eINSTANCE = org.eclipse.capra.GenericArtifactMetamodel.impl.GenericArtifactMetamodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperContainerImpl <em>Artifact Wrapper Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperContainerImpl
	 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.GenericArtifactMetamodelPackageImpl#getArtifactWrapperContainer()
	 * @generated
	 */
	int ARTIFACT_WRAPPER_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER_CONTAINER__ARTIFACTS = 0;

	/**
	 * The number of structural features of the '<em>Artifact Wrapper Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Artifact Wrapper Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperImpl <em>Artifact Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperImpl
	 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.GenericArtifactMetamodelPackageImpl#getArtifactWrapper()
	 * @generated
	 */
	int ARTIFACT_WRAPPER = 1;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER__URI = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Artifact Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER__ARTIFACT_HANDLER = 2;

	/**
	 * The number of structural features of the '<em>Artifact Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Artifact Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapperContainer <em>Artifact Wrapper Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Wrapper Container</em>'.
	 * @see org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapperContainer
	 * @generated
	 */
	EClass getArtifactWrapperContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapperContainer#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifacts</em>'.
	 * @see org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapperContainer#getArtifacts()
	 * @see #getArtifactWrapperContainer()
	 * @generated
	 */
	EReference getArtifactWrapperContainer_Artifacts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper <em>Artifact Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Wrapper</em>'.
	 * @see org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper
	 * @generated
	 */
	EClass getArtifactWrapper();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getUri()
	 * @see #getArtifactWrapper()
	 * @generated
	 */
	EAttribute getArtifactWrapper_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getName()
	 * @see #getArtifactWrapper()
	 * @generated
	 */
	EAttribute getArtifactWrapper_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getArtifactHandler <em>Artifact Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Handler</em>'.
	 * @see org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getArtifactHandler()
	 * @see #getArtifactWrapper()
	 * @generated
	 */
	EAttribute getArtifactWrapper_ArtifactHandler();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GenericArtifactMetamodelFactory getGenericArtifactMetamodelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperContainerImpl <em>Artifact Wrapper Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperContainerImpl
		 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.GenericArtifactMetamodelPackageImpl#getArtifactWrapperContainer()
		 * @generated
		 */
		EClass ARTIFACT_WRAPPER_CONTAINER = eINSTANCE.getArtifactWrapperContainer();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT_WRAPPER_CONTAINER__ARTIFACTS = eINSTANCE.getArtifactWrapperContainer_Artifacts();

		/**
		 * The meta object literal for the '{@link org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperImpl <em>Artifact Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.ArtifactWrapperImpl
		 * @see org.eclipse.capra.GenericArtifactMetamodel.impl.GenericArtifactMetamodelPackageImpl#getArtifactWrapper()
		 * @generated
		 */
		EClass ARTIFACT_WRAPPER = eINSTANCE.getArtifactWrapper();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_WRAPPER__URI = eINSTANCE.getArtifactWrapper_Uri();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_WRAPPER__NAME = eINSTANCE.getArtifactWrapper_Name();

		/**
		 * The meta object literal for the '<em><b>Artifact Handler</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_WRAPPER__ARTIFACT_HANDLER = eINSTANCE.getArtifactWrapper_ArtifactHandler();

	}

} //GenericArtifactMetamodelPackage
