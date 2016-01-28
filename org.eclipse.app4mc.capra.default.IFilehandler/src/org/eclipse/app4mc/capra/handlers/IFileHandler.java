package org.eclipse.app4mc.capra.handlers;


import java.util.Optional;

import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapper;
import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapperContainer;
import org.eclipse.app4mc.capra.generic.artifacts.ArtifactsFactory;
import org.eclipse.app4mc.capra.generic.handlers.ArtifactHandler;
import org.eclipse.app4mc.capra.generic.helpers.ArtifactHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

public class IFileHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {
		return selection instanceof IFile;
	}

	@Override
	public EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers) {
		IFile selectionAsFile = (IFile) selection;
		ArtifactWrapper wrapper = ArtifactsFactory.eINSTANCE.createArtifactWrapper();
		wrapper.setName(selectionAsFile.getName());
		wrapper.setUri(selectionAsFile.getFullPath().toString());
		wrapper.setArtifactHandler(this.getClass().getName());
		
		return ArtifactHelper.existingWrapperWithURIorNew(wrapper, existingWrappers);
	}
}
