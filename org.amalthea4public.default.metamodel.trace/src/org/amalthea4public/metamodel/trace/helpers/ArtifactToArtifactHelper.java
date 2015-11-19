package org.amalthea4public.metamodel.trace.helpers;

import java.util.List;
import java.util.Map;

import org.amalthea4public.generic.tracecreation.ArtifactWrapper;
import org.amalthea4public.metamodel.trace.ArtifactToArtifact;
import org.amalthea4public.metamodel.trace.TraceElement;
import org.amalthea4public.metamodel.trace.TracePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class ArtifactToArtifactHelper extends TraceTypeHelper {

	@Override
	public boolean fitsSelection(List<EObject> selection) {
		return selection.size() == 2 
			&& selection.get(0) instanceof ArtifactWrapper 
			&& selection.get(1) instanceof ArtifactWrapper;
	}

	@Override
	public EClass getType() {
		return TracePackage.eINSTANCE.getArtifactToArtifact();
	}

	@Override
	public void initialise(EObject trace, List<EObject> selection) {
		if(trace instanceof ArtifactToArtifact){
			ArtifactToArtifact a2a = (ArtifactToArtifact) trace;
			a2a.setSource((ArtifactWrapper) selection.get(0));
			a2a.setTarget((ArtifactWrapper) selection.get(1));
		}
	}

	@Override
	public void addConnectedElements(EObject element, TraceElement trace, Map<EObject, List<EObject>> traces) {
		if (trace instanceof ArtifactToArtifact) {
			ArtifactToArtifact t = (ArtifactToArtifact) trace;
			addConnectedElementsToSourceAndTarget(element, trace, t.getSource(), t.getTarget(), traces);
		}
	}

	@Override
	public boolean isRelevant(TraceElement trace, EObject firstElement, EObject secondElement) {
		if (trace instanceof ArtifactToArtifact) {
			ArtifactToArtifact t = (ArtifactToArtifact) trace;
			return isRelevantForSourceAndTarget(t.getSource(), t.getTarget(), firstElement, secondElement);
		}
		
		return false;
	}
} 
