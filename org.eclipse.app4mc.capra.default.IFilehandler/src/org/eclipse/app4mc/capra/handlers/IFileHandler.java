/*******************************************************************************
 *  Copyright (c) {2016} Chalmers|Gothenburg University, rt-labs and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Chalmers|Gothenburg University and rt-labs - initial API and implementation and/or initial documentation
 *******************************************************************************/
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
