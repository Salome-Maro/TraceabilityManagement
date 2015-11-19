package org.amalthea4public.metamodel.trace.helpers;

import java.util.List;
import java.util.Map;

import org.amalthea4public.generic.tracecreation.ArtifactWrapper;
import org.amalthea4public.metamodel.trace.TraceElement;
import org.amalthea4public.metamodel.trace.TracePackage;
import org.amalthea4public.metamodel.trace.TraceToArtifact;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class TraceToArtifactHelper extends TraceTypeHelper {

	@Override
	public boolean fitsSelection(List<EObject> selection) {
		return selection.size() == 2
				
				&& (selection.get(0) instanceof ArtifactWrapper 
				    && !(selection.get(1) instanceof ArtifactWrapper)
				    
				|| (selection.get(1) instanceof ArtifactWrapper 
				    && !(selection.get(0) instanceof ArtifactWrapper)));
	}

	@Override
	public EClass getType() {
		return TracePackage.eINSTANCE.getTraceToArtifact();
	}

	@Override
	public void initialise(EObject trace, List<EObject> selection) {
		if(trace instanceof TraceToArtifact){
			TraceToArtifact t2a = (TraceToArtifact) trace;
			
			int artifactWrapper = 0;
			int other = 1;
			if(selection.get(1) instanceof ArtifactWrapper){
				artifactWrapper = 1;
				other = 0;
			}
				
			t2a.setTarget((ArtifactWrapper) selection.get(artifactWrapper));
			t2a.setSource(selection.get(other));
		}
	}

	@Override
	public void addConnectedElements(EObject element, TraceElement trace, Map<EObject, List<EObject>> traces) {
		if (trace instanceof TraceToArtifact) {
			TraceToArtifact t = (TraceToArtifact) trace;
			addConnectedElementsToSourceAndTarget(element, trace, t.getSource(), t.getTarget(), traces);
		}
	}

	@Override
	public boolean isRelevant(TraceElement trace, EObject firstElement, EObject secondElement) {
		if(trace instanceof TraceToArtifact){
			TraceToArtifact t = (TraceToArtifact) trace;
			return isRelevantForSourceAndTarget(t.getSource(), t.getTarget(), firstElement, secondElement);
		}
		
		return false;
	}

}
