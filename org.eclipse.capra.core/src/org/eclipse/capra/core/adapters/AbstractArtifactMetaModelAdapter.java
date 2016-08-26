package org.eclipse.capra.core.adapters;

import org.eclipse.capra.core.handlers.ArtifactHandler;
import org.eclipse.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.emf.ecore.EObject;

public class AbstractArtifactMetaModelAdapter implements ArtifactMetaModelAdapter {

	@Override
	public EObject createModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EObject createArtifact(EObject artifactModel, String artifactHandler, String artifactUri,
			String artifactName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EObject getArtifact(EObject artifactModel, String artifactHandler, String artifactUri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getArtifactHandler(EObject artifact) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getArtifactName(EObject artifact) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getArtifactUri(EObject artifact) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ArtifactHandler getArtifactHandlerInstance(EObject artifact) {
		String handler = getArtifactHandler(artifact);
		return ExtensionPointHelper.getArtifactHandler(handler).orElse(null);
	}

}
