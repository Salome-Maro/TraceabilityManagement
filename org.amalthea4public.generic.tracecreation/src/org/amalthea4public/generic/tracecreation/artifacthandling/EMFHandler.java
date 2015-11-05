package org.amalthea4public.generic.tracecreation.artifacthandling;

import org.eclipse.emf.ecore.EObject;

public class EMFHandler implements ArtifactHandler {


	@Override
	public EObject getEObjectForSelection(Object selection) {
		return EObject.class.cast(selection);
	}
	
	public boolean canHandleSelection(Object selection) {
		return selection instanceof EObject;
	}
}
