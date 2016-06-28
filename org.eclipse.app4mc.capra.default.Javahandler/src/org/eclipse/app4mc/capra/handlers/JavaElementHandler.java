package org.eclipse.app4mc.capra.handlers;

import org.eclipse.app4mc.capra.core.adapters.ArtifactMetaModelAdapter;
import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.app4mc.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaElement;

public class JavaElementHandler implements ArtifactHandler {
	@Override
	public boolean canHandleSelection(Object selection) {
		if(selection instanceof IJavaElement){
			return true;
		}
		
		return false;
	}

	@Override  
	public EObject getEObjectForSelection(Object selection, EObject artifactModel) {
		IJavaElement cu = (IJavaElement) selection;
		ArtifactMetaModelAdapter adapter = ExtensionPointHelper.getArtifactWrapperMetaModelAdapter().get();
		EObject wrapper = adapter.createArtifact(
				artifactModel,
				this.getClass().getName(), 
				cu.getHandleIdentifier(), 
				cu.getElementName());
		return wrapper;
	} 

}
