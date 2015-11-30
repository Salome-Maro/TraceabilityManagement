package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.Trace;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.TraceElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

abstract public class TraceTypeHelper {
	public abstract boolean fitsSelection(List<EObject> selection);

	public abstract EClass getType();

	public abstract void initialise(EObject trace, List<EObject> selection);

	public abstract void addConnectedElements(EObject element, TraceElement trace, Map<EObject, List<EObject>> traces);
	
	public abstract  void addObjectsConnectedtoTrace(List<EObject> connectedElements, TraceElement trace);

	protected void addConnectedElementsToSourceAndTarget(EObject element, TraceElement trace, EObject source, EObject target, Map<EObject, List<EObject>> traces) {
		if (source.equals(element)) {
			List<EObject> tracedElements = new ArrayList<>();
			tracedElements.add(target);
			traces.put(trace, tracedElements);
		}
		else if (target.equals(element)) {
			List<EObject> tracedElements = new ArrayList<>();
			tracedElements.add(source);
			traces.put(trace, tracedElements);
		}
	}
	
	public abstract boolean isRelevant(TraceElement trace, EObject firstElement, EObject secondElement);
	
	protected boolean isRelevantForSourceAndTarget(EObject source, EObject target, EObject firstElement, EObject secondElement) {
		return    (source.equals(firstElement)  && target.equals(secondElement))
			   || (source.equals(secondElement) && target.equals(firstElement));
	}
}
