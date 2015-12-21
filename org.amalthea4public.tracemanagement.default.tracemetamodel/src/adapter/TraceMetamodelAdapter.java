package adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.adapters.Connection;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.SimpleTraceModel;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.TraceElement;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.TracemetamodelFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import helpers.ArtifactToArtifactHelper;
import helpers.TraceHelper;
import helpers.TraceToArtifactHelper;
import helpers.TraceTypeHelper;

public class TraceMetamodelAdapter
		implements org.amalthea4public.tracemanagement.generic.adapters.TraceMetamodelAdapter {

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
			if (h.fitsSelection(selection))
				traceTypes.add(h.getType());
		});

		return traceTypes;
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, List<EObject> selection) {
		SimpleTraceModel root = (SimpleTraceModel) traceModel
				.orElse(TracemetamodelFactory.eINSTANCE.createSimpleTraceModel());

		EObject trace = TracemetamodelFactory.eINSTANCE.create(traceType);

		helpers.forEach(h -> {
			h.initialise(trace, selection);
		});

		root.getTraces().add((TraceElement) trace);
		return root;
	}

	@Override
	public boolean isThereATraceBetween(EObject firstElement, EObject secondElement, Optional<EObject> traceModel) {
		return traceModel.map(tm -> {
			SimpleTraceModel root = (SimpleTraceModel) tm;
			return root.getTraces().stream().anyMatch(trace -> isRelevant(trace, firstElement, secondElement));
		}).orElse(false);
	}

	private boolean isRelevant(TraceElement trace, EObject firstElement, EObject secondElement) {
		return helpers.stream().anyMatch(h -> h.isRelevant(trace, firstElement, secondElement));
	}

	@Override
	public List<Connection> getConnectedElements(EObject element, Optional<EObject> traceModel) {
		List<Connection> traces = new ArrayList<>();

		traceModel.ifPresent(tm -> {
			if (element instanceof TraceElement) {
				TraceElement t = (TraceElement) element;
				List<EObject> tracedElements = new ArrayList<>();
				helpers.forEach(h -> {
					h.addObjectsConnectedtoTrace(tracedElements, t);
				});

				traces.add(new Connection(element, tracedElements, t));
			} else {
				SimpleTraceModel root = (SimpleTraceModel) tm;
				root.getTraces().forEach(trace -> {
					helpers.forEach(h -> h.addConnectedElements(element, trace, traces));
				});
			}
		});

		return traces;
	}

}
