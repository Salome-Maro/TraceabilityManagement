package org.amalthea4public.metamodel.trace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.ArtifactWrapper;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {

	@Override
	public Collection<EClass> getAvailableTraceTypes(List<EObject> selection) {

		Collection<EClass> traceTypes = new ArrayList<>();

		if (selection.size() == 2) {
			if(selection.get(0) instanceof ArtifactWrapper && selection.get(1) instanceof ArtifactWrapper){
				traceTypes.add(TracePackage.eINSTANCE.getArtifactToArtifact());
			} else if(selection.get(0) instanceof ArtifactWrapper || selection.get(1) instanceof ArtifactWrapper){
				traceTypes.add(TracePackage.eINSTANCE.getTraceToArtifact());				
			} else {
				traceTypes.add(TracePackage.eINSTANCE.getTrace());
			}
		}

		return traceTypes;
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, List<EObject> selection) {
		TraceModel root = (TraceModel) traceModel.orElse(TraceFactory.eINSTANCE.createTraceModel());

		EObject trace = TraceFactory.eINSTANCE.create(traceType);

		if(trace instanceof Trace){
			((Trace) trace).setSource(selection.get(0));
			((Trace) trace).setTarget(selection.get(1));
			
		} else if(trace instanceof ArtifactToArtifact){
			((ArtifactToArtifact) trace).setSource((ArtifactWrapper) selection.get(0));
			((ArtifactToArtifact) trace).setTarget((ArtifactWrapper) selection.get(1));
	
		} else if (trace instanceof TraceToArtifact){
			if (selection.get(0) instanceof ArtifactWrapper) {
				((TraceToArtifact) trace).setSource(selection.get(1));
				((TraceToArtifact) trace).setTarget((ArtifactWrapper) selection.get(0));
			}else {
				((TraceToArtifact) trace).setSource(selection.get(0));
				((TraceToArtifact) trace).setTarget((ArtifactWrapper) selection.get(1));
			}
		}
		root.getTraces().add((TraceElement) trace);
		return root;
	}

	@Override
	public boolean isThereATraceBetween(EObject firstElement, EObject secondElement, Optional<EObject> traceModel) {
		return traceModel.map(tm -> {
			TraceModel root = (TraceModel) tm;
			return root.getTraces().stream().anyMatch(trace -> isRelevant(trace, firstElement, secondElement));
		}).orElse(false);
	}

	private boolean isRelevant(TraceElement trace, EObject firstElement, EObject secondElement) {
		if (trace instanceof Trace) {
		return (((Trace) trace).getSource().equals(firstElement) && (((Trace) trace).getTarget().equals(secondElement))
				|| ( ((Trace) trace).getSource().equals(secondElement) && (((Trace) trace).getTarget().equals(firstElement))));
		} else if (trace instanceof ArtifactToArtifact) {
			return (((ArtifactToArtifact) trace).getSource().equals(firstElement) && (((ArtifactToArtifact) trace).getTarget().equals(secondElement))
					|| ( ((ArtifactToArtifact) trace).getSource().equals(secondElement) && (((ArtifactToArtifact) trace).getTarget().equals(firstElement))));		
			}
		else return (((TraceToArtifact) trace).getSource().equals(firstElement) && (((TraceToArtifact) trace).getTarget().equals(secondElement))
				|| ( ((TraceToArtifact) trace).getSource().equals(secondElement) && (((TraceToArtifact) trace).getTarget().equals(firstElement))));
		}

	@Override
	public Map<EObject, List<EObject>> getConnectedElements(EObject element, Optional<EObject> traceModel) {
		Map<EObject, List<EObject>> traces = new HashMap<>();
		
		traceModel.ifPresent(tm -> {
			TraceModel root = (TraceModel)tm;
			root.getTraces().forEach(trace -> {
				if (trace instanceof Trace) {
					Trace t = (Trace) trace;
					if (t.getSource().equals(element)) {
						List<EObject> tracedElements = new ArrayList<>();
						tracedElements.add(t.getTarget());
						traces.put(trace, tracedElements);
					}
					else if (t.getTarget().equals(element)) {
						List<EObject> tracedElements = new ArrayList<>();
						tracedElements.add(t.getSource());
						traces.put(trace, tracedElements);
					}
					
				}
				
				else if (trace instanceof TraceToArtifact) {
					TraceToArtifact t = (TraceToArtifact) trace;
					if (t.getSource().equals(element)) {
						List<EObject> tracedElements = new ArrayList<>();
						tracedElements.add(t.getTarget());
						traces.put(trace, tracedElements);
					}
					else if (t.getTarget().equals(element)) {
						List<EObject> tracedElements = new ArrayList<>();
						tracedElements.add(t.getSource());
						traces.put(trace, tracedElements);
					}
					
				}
				
				else if (trace instanceof ArtifactToArtifact) {
					ArtifactToArtifact t = (ArtifactToArtifact) trace;
					if (t.getSource().equals(element)) {
						List<EObject> tracedElements = new ArrayList<>();
						tracedElements.add(t.getTarget());
						traces.put(trace, tracedElements);
					}
					else if (t.getTarget().equals(element)) {
						List<EObject> tracedElements = new ArrayList<>();
						tracedElements.add(t.getSource());
						traces.put(trace, tracedElements);
					}
					
				}
				
			});
		});
		
		return traces;
	}

}
