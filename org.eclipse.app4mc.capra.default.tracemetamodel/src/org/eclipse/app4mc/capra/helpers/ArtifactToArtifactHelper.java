package org.eclipse.app4mc.capra.helpers;

import java.util.List;

import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapper;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.ArtifactToArtifact;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.TraceElement;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.TracemetamodelPackage;
import org.eclipse.app4mc.capra.generic.adapters.Connection;
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
		return TracemetamodelPackage.eINSTANCE.getArtifactToArtifact();
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
	public void addConnectedElements(EObject element, TraceElement trace, List<Connection> traces) {
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

	@Override
	public void addObjectsConnectedtoTrace(List<EObject> connectedElements, TraceElement trace) {
		if(trace instanceof ArtifactToArtifact) {
			ArtifactToArtifact t = (ArtifactToArtifact) trace;
			
			connectedElements.add(t.getSource());
			connectedElements.add(t.getTarget());
		}
		
	}
} 
