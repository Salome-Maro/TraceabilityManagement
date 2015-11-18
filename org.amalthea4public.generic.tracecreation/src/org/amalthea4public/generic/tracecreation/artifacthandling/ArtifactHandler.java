package org.amalthea4public.generic.tracecreation.artifacthandling;

import java.util.Optional;

import org.amalthea4public.generic.tracecreation.ArtifactWrapperContainer;
import org.eclipse.emf.ecore.EObject;

public interface ArtifactHandler {
	
	boolean canHandleSelection(Object selection);
	
	EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers);

}
