package org.eclipse.app4mc.capra.handlers;

import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;

public class GEFHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {
		// TODO Auto-generated method stub
		return selection instanceof EditPart;
		
	}

	@Override
	public EObject getEObjectForSelection(Object selection, EObject artifactModel) {
		// TODO Auto-generated method stub
		EditPart sel = (EditPart) selection;

		return EMFHelper.getEObject(sel);

	}

}
