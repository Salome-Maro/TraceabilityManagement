package org.amalthea4public.metamodel.trace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.amalthea4public.metamodel.trace.helpers.ArtifactToArtifactHelper;
import org.amalthea4public.metamodel.trace.helpers.TraceTypeHelper;
import org.amalthea4public.metamodel.trace.helpers.TraceHelper;
import org.amalthea4public.metamodel.trace.helpers.TraceToArtifactHelper;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {
	
	private Collection<TraceTypeHelper> helpers;
	
	public TraceMetamodelAdapter() {
		helpers = new ArrayList<>();
		helpers.add(new TraceHelper());
		helpers.add(new ArtifactToArtifactHelper());
		helpers.add(new TraceToArtifactHelper());
	}
	
	@Override
	public Collection<EClass> getAvailableTraceTypes(List<EObject> selection) {
		Collection<EClass> traceTypes = new ArrayList<>();
		
		helpers.forEach(h -> {
			if(h.fitsSelection(selection))
				traceTypes.add(h.getType());
		});
		
		return traceTypes;
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, List<EObject> selection) {
		TraceModel root = (TraceModel) traceModel.orElse(TraceFactory.eINSTANCE.createTraceModel());

		EObject trace = TraceFactory.eINSTANCE.create(traceType);

		helpers.forEach(h -> {
			h.initialise(trace, selection);
		});
		
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
		return helpers.stream().anyMatch(h -> h.isRelevant(trace, firstElement, secondElement));
	}

	@Override
	public Map<EObject, List<EObject>> getConnectedElements(EObject element, Optional<EObject> traceModel) {
		Map<EObject, List<EObject>> traces = new HashMap<>();
		
		traceModel.ifPresent(tm -> {
			TraceModel root = (TraceModel)tm;
			root.getTraces().forEach(trace -> {
				helpers.forEach(h -> h.addConnectedElements(element, trace, traces));	
			});
		});
		
		return traces;
	}

}
