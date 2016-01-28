package org.eclipse.app4mc.capra.handlers;

import java.util.Optional;

import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapperContainer;
import org.eclipse.app4mc.capra.generic.handlers.ArtifactHandler;
import org.eclipse.emf.ecore.EObject;

public class EMFHandler implements ArtifactHandler {

	public boolean canHandleSelection(Object selection) {
		return selection instanceof EObject;

	}

	@Override
	public EObject getEObjectForSelection(Object selection,
			Optional<ArtifactWrapperContainer> existingWrappers) {
		return EObject.class.cast(selection);
	}
}
