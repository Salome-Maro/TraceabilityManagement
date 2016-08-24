/*******************************************************************************
 *  Copyright (c) {2016} Chalmers|Gothenburg University, rt-labs and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Chalmers|Gothenburg University and rt-labs - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.capra.core.adapters;

import org.eclipse.emf.ecore.EObject;

public interface ArtifactMetaModelAdapter {

	EObject createModel();

	/**
	 * Create a new artifact. The list of artifacts is searched for an existing
	 * artifact with the same handler and uri. If found, the existing artifact
	 * is returned, otherwise a new artifact is created.
	 * 
	 * @param artifactHandler	Handler of artifact
	 * @param artifactUri 		Uri of artifact
	 * @param artifactName		Name of artifact
	 * @return new or existing artifact
	 */
	EObject createArtifact(EObject artifactModel, String artifactHandler, String artifactUri, String artifactName);
	
	/**
	 * Get artifact with given handler and uri.
	 * 
	 * @param artifactHandler	Handler of artifact
	 * @param artifactUri 		Uri of artifact
	 * @return artifact if found, null otherwise
	 */
	EObject getArtifact(EObject artifactModel, String artifactHandler, String artifactUri);
	
	/**
	 * Get artifact handler
	 * 
	 * @param artifact
	 * @return artifact handler
	 */
	String getArtifactHandler(EObject artifact);
	
	/**
	 * Get artifact name
	 * 
	 * @param artifact
	 * @return artifact name
	 */
	String getArtifactName(EObject artifact);
	
	/**
	 * Get artifact Uri
	 * 
	 * @param artifact
	 * @return artifact uri
	 */
	String getArtifactUri(EObject artifact);
	

}
