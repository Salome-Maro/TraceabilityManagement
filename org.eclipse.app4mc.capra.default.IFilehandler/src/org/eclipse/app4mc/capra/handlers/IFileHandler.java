package org.eclipse.app4mc.capra.handlers;


import org.eclipse.app4mc.capra.core.adapters.ArtifactMetaModelAdapter;
import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.app4mc.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

public class IFileHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {
		return selection instanceof IFile;
	}

	@Override
	public EObject getEObjectForSelection(Object selection, EObject  artifactModel) {
		IFile selectionAsFile = (IFile) selection;
		ArtifactMetaModelAdapter adapter = ExtensionPointHelper.getArtifactWrapperMetaModelAdapter().get();
		EObject wrapper = adapter.createArtifact(
				artifactModel,
				this.getClass().getName(), 
				selectionAsFile.getFullPath().toString(), 
				selectionAsFile.getName());
		return wrapper;
	}
}
