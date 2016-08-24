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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact Wrapper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getArtifactHandler <em>Artifact Handler</em>}</li>
 * </ul>
 *
 * @see org.eclipse.capra.GenericArtifactMetamodel.GenericArtifactMetamodelPackage#getArtifactWrapper()
 * @model
 * @generated
 */
public interface ArtifactWrapper extends EObject {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see org.eclipse.capra.GenericArtifactMetamodel.GenericArtifactMetamodelPackage#getArtifactWrapper_Uri()
	 * @model unique="false"
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.capra.GenericArtifactMetamodel.GenericArtifactMetamodelPackage#getArtifactWrapper_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Artifact Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact Handler</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Handler</em>' attribute.
	 * @see #setArtifactHandler(String)
	 * @see org.eclipse.capra.GenericArtifactMetamodel.GenericArtifactMetamodelPackage#getArtifactWrapper_ArtifactHandler()
	 * @model unique="false"
	 * @generated
	 */
	String getArtifactHandler();

	/**
	 * Sets the value of the '{@link org.eclipse.capra.GenericArtifactMetamodel.ArtifactWrapper#getArtifactHandler <em>Artifact Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact Handler</em>' attribute.
	 * @see #getArtifactHandler()
	 * @generated
	 */
	void setArtifactHandler(String value);

} // ArtifactWrapper
