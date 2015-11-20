package org.amalthea4public.tracecreation.artifacthandling;

import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactsFactory;
import org.amalthea4public.tracemanagement.generic.handlers.ArtifactHandler;
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
		
		return existingWrapperWithURIorNew(wrapper, existingWrappers);
	}
}
