package org.eclipse.app4mc.capra.handlers;

import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.emf.ecore.EObject;

public class EMFHandler implements ArtifactHandler {

	public boolean canHandleSelection(Object selection) {
		return selection instanceof EObject;

	}

	@Override
	public EObject getEObjectForSelection(Object selection, EObject  artifactModel) {
		return EObject.class.cast(selection);
	}
}
