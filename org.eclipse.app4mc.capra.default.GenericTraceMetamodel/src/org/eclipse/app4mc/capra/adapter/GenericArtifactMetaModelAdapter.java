package org.eclipse.app4mc.capra.adapter;

import org.eclipse.app4mc.capra.GenericArtifactMetamodel.ArtifactWrapper;
import org.eclipse.app4mc.capra.GenericArtifactMetamodel.ArtifactWrapperContainer;
import org.eclipse.app4mc.capra.GenericArtifactMetamodel.GenericArtifactMetamodelFactory;
import org.eclipse.app4mc.capra.core.adapters.ArtifactMetaModelAdapter;
import org.eclipse.emf.ecore.EObject;

public class GenericArtifactMetaModelAdapter implements
		ArtifactMetaModelAdapter {

	private ArtifactWrapperContainer getContainer(EObject artifactModel) {
		return (ArtifactWrapperContainer) artifactModel;
	}
	
	@Override
	public EObject createModel() {
		return GenericArtifactMetamodelFactory.eINSTANCE.createArtifactWrapperContainer();
	}

	public EObject getArtifact(EObject artifactModel, String artifactHandler, String artifactUri) {
		ArtifactWrapperContainer container = getContainer(artifactModel);
		for (ArtifactWrapper artifact : container.getArtifacts()) {
			if (getArtifactHandler(artifact).equals(artifactHandler) && getArtifactUri(artifact).equals(artifactUri))								
				return artifact;			
		}
		return null;
	}

	@Override
	public EObject createArtifact(EObject artifactModel, String artifactHandler, String artifactUri, String artifactName) {		
		ArtifactWrapperContainer container = getContainer(artifactModel);
		EObject existingWrapper = getArtifact(artifactModel, artifactHandler, artifactUri);
		if (existingWrapper != null)
			return existingWrapper;
		
		ArtifactWrapper wrapper = GenericArtifactMetamodelFactory.eINSTANCE.createArtifactWrapper();
		wrapper.setArtifactHandler(artifactHandler);
		wrapper.setUri(artifactUri);
		wrapper.setName(artifactName);
		container.getArtifacts().add(wrapper);
		
		return wrapper;
	}

	@Override
	public String getArtifactHandler(EObject artifact) {
		if (artifact instanceof ArtifactWrapper) {
			ArtifactWrapper wrapper = (ArtifactWrapper) artifact;
			return wrapper.getArtifactHandler();
		}
		return null;
	}

	@Override
	public String getArtifactName(EObject artifact) {
		if (artifact instanceof ArtifactWrapper) {
			ArtifactWrapper wrapper = (ArtifactWrapper) artifact;
			return wrapper.getName();
		}
		return null;
	}

	@Override
	public String getArtifactUri(EObject artifact) {
		if (artifact instanceof ArtifactWrapper) {
			ArtifactWrapper wrapper = (ArtifactWrapper) artifact;
			return wrapper.getUri();
		}
		return null;
	}



}
