package org.eclipse.app4mc.capra.handlers;

import org.eclipse.app4mc.capra.core.adapters.ArtifactMetaModelAdapter;
import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.app4mc.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.builds.internal.core.BuildElement;
import org.eclipse.mylyn.builds.internal.core.TestElement;

public class HudsonHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {
		return (selection instanceof TestElement || selection instanceof BuildElement);
	}

	@Override
	public EObject getEObjectForSelection(Object selection, EObject artifactModel) {
		ArtifactMetaModelAdapter adapter = ExtensionPointHelper.getArtifactWrapperMetaModelAdapter().get();
		if (selection instanceof TestElement) {
			TestElement test = (TestElement) selection;

			EObject testWrapper = adapter.createArtifact(
					artifactModel,
					this.getClass().getName(), 
					test.getLabel(), //TODO Need to get the URI for where the test is
					test.getLabel());
			return testWrapper;
		} 
		else if (selection instanceof BuildElement) {
			BuildElement build = (BuildElement) selection;

			EObject buildWrapper = adapter.createArtifact(
					artifactModel,	
					this.getClass().getName(), 
					build.getUrl(),
					build.getLabel());
			return buildWrapper;
		}

		return null;
	}

}
