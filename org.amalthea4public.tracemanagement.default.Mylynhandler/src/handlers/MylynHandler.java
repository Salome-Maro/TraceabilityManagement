package handlers;

import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactsFactory;
import org.amalthea4public.tracemanagement.generic.handlers.ArtifactHandler;
import org.amalthea4public.tracemanagement.generic.helpers.ArtifactHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.tasks.core.ITask;


public class MylynHandler implements ArtifactHandler {

	@Override
	public boolean canHandleSelection(Object selection) {
		return selection instanceof ITask;
	}

	@Override
	public EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers) {
		ITask task = (ITask) selection;
		ArtifactWrapper wrapper = ArtifactsFactory.eINSTANCE.createArtifactWrapper();
		wrapper.setName(task.getSummary());
		wrapper.setUri(task.getUrl());
		wrapper.setArtifactHandler(this.getClass().getName());
		
		return ArtifactHelper.existingWrapperWithURIorNew(wrapper, existingWrappers);
		
	}

	
}
