package handlers;


import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.amalthea4public.tracemanagement.generic.handlers.ArtifactHandler;
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
