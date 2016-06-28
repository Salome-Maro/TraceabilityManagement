package org.eclipse.app4mc.capra.handlers;

import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.emf.facet.custom.metamodel.v0_2_0.internal.treeproxy.TreeElement;

public class PapyrusHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {		
		return selection instanceof TreeElement;
	}

	@Override
	public EObject getEObjectForSelection(Object selection, EObject artifactModel) {	
		// Returns the EObject corresponding to the input object if the input is an EObject, or if it is Adaptable to an EObject
		return EObject.class.cast((TreeElement)selection);
	}

}
