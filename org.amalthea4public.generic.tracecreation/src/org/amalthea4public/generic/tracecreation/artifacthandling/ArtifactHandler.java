package org.amalthea4public.generic.tracecreation.artifacthandling;

import org.eclipse.emf.ecore.EObject;

public interface ArtifactHandler {
	
	boolean canHandleSelection(Object selection);
	
	EObject getEObjectForSelection(Object selection);

}
