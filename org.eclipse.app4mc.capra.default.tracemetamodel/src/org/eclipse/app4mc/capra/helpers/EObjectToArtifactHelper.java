package org.eclipse.app4mc.capra.helpers;

import java.util.List;

import org.eclipse.app4mc.capra.generic.adapters.Connection;
import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapper;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.EObjectToArtifact;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.TraceElement;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.TracemetamodelPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class EObjectToArtifactHelper extends TraceTypeHelper {

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
		return TracemetamodelPackage.eINSTANCE.getEObjectToArtifact();
	}

	@Override
	public void initialise(EObject trace, List<EObject> selection) {
		if(trace instanceof EObjectToArtifact){
			EObjectToArtifact t2a = (EObjectToArtifact) trace;
			
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
	public void addConnectedElements(EObject element, TraceElement trace, List<Connection> traces) {
		if (trace instanceof EObjectToArtifact) {
			EObjectToArtifact t = (EObjectToArtifact) trace;
			addConnectedElementsToSourceAndTarget(element, trace, t.getSource(), t.getTarget(), traces);
		}
	}

	@Override
	public boolean isRelevant(TraceElement trace, EObject firstElement, EObject secondElement) {
		if(trace instanceof EObjectToArtifact){
			EObjectToArtifact t = (EObjectToArtifact) trace;
			return isRelevantForSourceAndTarget(t.getSource(), t.getTarget(), firstElement, secondElement);
		}
		
		return false;
	}

	@Override
	public void addObjectsConnectedtoTrace(List<EObject> connectedElements, TraceElement trace) {
		if(trace instanceof EObjectToArtifact) {
			EObjectToArtifact t = (EObjectToArtifact) trace;
			
			connectedElements.add(t.getSource());
			connectedElements.add(t.getTarget());
		}
		
	}

}
