package org.eclipse.app4mc.capra.helpers;

import java.util.List;

import org.eclipse.app4mc.capra.generic.adapters.Connection;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.EObjectToEObject;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.TraceElement;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.TracemetamodelPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class EObjectToEObjectHelper extends TraceTypeHelper {

	@Override
	public boolean fitsSelection(List<EObject> selection) {
		return selection.size() == 2;
	}

	@Override
	public EClass getType() {
		return TracemetamodelPackage.eINSTANCE.getEObjectToEObject();
	}

	@Override
	public void initialise(EObject trace, List<EObject> selection) {
		if(trace instanceof EObjectToEObject){
			EObjectToEObject t = (EObjectToEObject) trace;
			t.setSource(selection.get(0));
			t.setTarget(selection.get(1));
		}
	}

	@Override
	public void addConnectedElements(EObject element, TraceElement trace, List<Connection> traces) {
		if (trace instanceof EObjectToEObject) {
			EObjectToEObject t = (EObjectToEObject) trace;
			addConnectedElementsToSourceAndTarget(element, trace, t.getSource(), t.getTarget(), traces);
		}
	}

	@Override
	public boolean isRelevant(TraceElement trace, EObject firstElement, EObject secondElement) {
		if (trace instanceof EObjectToEObject) {
			EObjectToEObject t = (EObjectToEObject)trace;
			return isRelevantForSourceAndTarget(t.getSource(), t.getTarget(), firstElement, secondElement);
		}
		
		return false;
	}

	@Override
	public void addObjectsConnectedtoTrace(List<EObject> connectedElements, TraceElement trace) {
		if (trace instanceof EObjectToEObject) {
			EObjectToEObject t = (EObjectToEObject) trace;
			connectedElements.add(t.getSource());
			connectedElements.add(t.getTarget());
		}
	}

}
