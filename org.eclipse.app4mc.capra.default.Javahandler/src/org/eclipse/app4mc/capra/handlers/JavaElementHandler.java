package org.eclipse.app4mc.capra.handlers;

import java.util.Optional;

import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapper;
import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapperContainer;
import org.eclipse.app4mc.capra.generic.artifacts.ArtifactsFactory;
import org.eclipse.app4mc.capra.generic.handlers.ArtifactHandler;
import org.eclipse.app4mc.capra.generic.helpers.ArtifactHelper;
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
	public EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers) {
		IJavaElement cu = (IJavaElement) selection;
		ArtifactWrapper wrapper = ArtifactsFactory.eINSTANCE.createArtifactWrapper();
		wrapper.setName(cu.getElementName());
		wrapper.setUri(cu.getHandleIdentifier());
		wrapper.setArtifactHandler(this.getClass().getName());
		
		return ArtifactHelper.existingWrapperWithURIorNew(wrapper, existingWrappers);
	}

}
