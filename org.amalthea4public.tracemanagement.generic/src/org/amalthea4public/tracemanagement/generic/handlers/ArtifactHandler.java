package org.amalthea4public.tracemanagement.generic.handlers;

import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.eclipse.emf.ecore.EObject;

public interface ArtifactHandler {
	
	boolean canHandleSelection(Object selection);
	
	EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers);
	
	default EObject existingWrapperWithURIorNew(ArtifactWrapper wrapper,
			Optional<ArtifactWrapperContainer> existingWrappers) {
		if (existingWrappers.isPresent()) {
			for (ArtifactWrapper existing : existingWrappers.get().getArtifacts()) {
				if (wrapper.getArtifactHandler().equals(existing.getArtifactHandler())
						&& existing.getUri().equals(wrapper.getUri()))
					return existing;
			}
		}
		
		return wrapper;
	}
}
