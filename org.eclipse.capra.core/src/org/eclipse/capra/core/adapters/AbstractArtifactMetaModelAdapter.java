package org.eclipse.capra.core.adapters;

import org.eclipse.capra.core.handlers.ArtifactHandler;
import org.eclipse.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.emf.ecore.EObject;

public abstract class AbstractArtifactMetaModelAdapter implements ArtifactMetaModelAdapter {

	@Override
	public ArtifactHandler getArtifactHandlerInstance(EObject artifact) {
		String handler = getArtifactHandler(artifact);
		return ExtensionPointHelper.getArtifactHandler(handler).orElse(null);
	}

}
