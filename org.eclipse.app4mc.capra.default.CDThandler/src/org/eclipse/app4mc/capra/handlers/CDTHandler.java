package org.eclipse.app4mc.capra.handlers;

import org.eclipse.app4mc.capra.core.adapters.ArtifactMetaModelAdapter;
import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.app4mc.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.emf.ecore.EObject;


public class CDTHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {
		return selection instanceof ICElement; 
	}

	@Override
	public EObject getEObjectForSelection(Object selection, EObject  artifactModel) {
		ICElement cu = (ICElement)selection;
		ArtifactMetaModelAdapter adapter = ExtensionPointHelper.getArtifactWrapperMetaModelAdapter().get();
		EObject wrapper = adapter.createArtifact(
				artifactModel,
				this.getClass().getName(), 
				cu.getHandleIdentifier(), 
				cu.getElementName());
		return wrapper;
	}

}
