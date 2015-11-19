package org.amalthea4public.tracecreation.artifacthandling;

import java.util.Optional;

import org.amalthea4public.generic.tracecreation.ArtifactWrapperContainer;
import org.amalthea4public.generic.tracecreation.artifacthandling.ArtifactHandler;
import org.eclipse.emf.ecore.EObject;

public class EMFHandler implements ArtifactHandler {
	
	public boolean canHandleSelection(Object selection) {
		return selection instanceof EObject;
	}

	@Override
	public EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers) {
		return EObject.class.cast(selection);
	}
}
