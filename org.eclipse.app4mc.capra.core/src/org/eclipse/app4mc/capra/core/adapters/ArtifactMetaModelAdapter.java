package org.eclipse.app4mc.capra.core.adapters;

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
