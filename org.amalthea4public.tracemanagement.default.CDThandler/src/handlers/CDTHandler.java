package handlers;

import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactsFactory;
import org.amalthea4public.tracemanagement.generic.handlers.ArtifactHandler;
import org.amalthea4public.tracemanagement.generic.helpers.ArtifactHelper;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.emf.ecore.EObject;

public class CDTHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {
		if (selection instanceof ICElement) {
			return true;
		}
		return false;
	}

	@Override
	public EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers) {
		ICElement cu = (ICElement)selection;
		ArtifactWrapper wrapper = ArtifactsFactory.eINSTANCE.createArtifactWrapper();
		wrapper.setName(cu.getElementName());
		wrapper.setUri(cu.getHandleIdentifier());
		wrapper.setArtifactHandler(this.getClass().getName());
		
		return ArtifactHelper.existingWrapperWithURIorNew(wrapper, existingWrappers);
	}

}
