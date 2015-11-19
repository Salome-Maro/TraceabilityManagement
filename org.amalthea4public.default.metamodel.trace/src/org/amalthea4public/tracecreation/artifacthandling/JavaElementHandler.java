package org.amalthea4public.tracecreation.artifacthandling;

import java.util.Optional;

import org.amalthea4public.generic.tracecreation.ArtifactWrapper;
import org.amalthea4public.generic.tracecreation.ArtifactWrapperContainer;
import org.amalthea4public.generic.tracecreation.TracecreationFactory;
import org.amalthea4public.generic.tracecreation.artifacthandling.ArtifactHandler;
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
		ArtifactWrapper wrapper = TracecreationFactory.eINSTANCE.createArtifactWrapper();
		wrapper.setName(cu.getElementName());
		wrapper.setUri(cu.getHandleIdentifier());
		wrapper.setArtifactHandler(this.getClass().getName());
		
		return existingWrapperWithURIorNew(wrapper, existingWrappers);
	}

}
