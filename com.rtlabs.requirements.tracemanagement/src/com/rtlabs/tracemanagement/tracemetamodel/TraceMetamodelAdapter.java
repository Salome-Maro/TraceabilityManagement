package com.rtlabs.tracemanagement.tracemetamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.rtlabs.requirements.Requirement;
import com.rtlabs.traces.ReqToArtifact;
import com.rtlabs.traces.ReqToReq;
import com.rtlabs.traces.RtLabsTraceModel;
import com.rtlabs.traces.Trace;
import com.rtlabs.traces.TracesFactory;
import com.rtlabs.traces.TracesPackage;

public class TraceMetamodelAdapter
		implements org.amalthea4public.tracemanagement.generic.adapters.TraceMetamodelAdapter {


	@Override
	public Collection<EClass> getAvailableTraceTypes (List<EObject> selection) {

		Collection<EClass> traceTypes = new ArrayList<>();

		if (selection.size() == 2) {
			if (everythingIsARequirement(selection))
				traceTypes.add(TracesPackage.eINSTANCE.getReqToReq());
			else if (selectionContainsRequirementAndArtifact(selection))
				traceTypes.add(TracesPackage.eINSTANCE.getReqToArtifact());
		}

		return traceTypes;
	}

	private boolean selectionContainsRequirementAndArtifact(List<EObject> selection) {
		return selection.stream().anyMatch(o -> o instanceof Requirement)
				&& selection.stream().anyMatch(o -> o instanceof ArtifactWrapper);
	}

	private boolean everythingIsARequirement(List<EObject> selection) {
		return selection.stream().allMatch(o -> o instanceof Requirement);
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, List<EObject> selection) {
		
		RtLabsTraceModel root = (RtLabsTraceModel) traceModel.orElse(TracesFactory.eINSTANCE.createRtLabsTraceModel());

		Trace trace = (Trace) TracesFactory.eINSTANCE.create(traceType);
		if (trace instanceof ReqToReq) {
			ReqToReq reqToReq = (ReqToReq) trace;
			reqToReq.setParent((Requirement) selection.get(0));
			reqToReq.setChild((Requirement) selection.get(1));
		} else if (trace instanceof ReqToArtifact) {
			ReqToArtifact reqToArtifact = (ReqToArtifact) trace;
			if (selection.get(0) instanceof Requirement) {
			reqToArtifact.setParent((Requirement) selection.get(0));
			reqToArtifact.setArtifact((ArtifactWrapper) selection.get(1));
			}
			else{
				reqToArtifact.setParent((Requirement) selection.get(1));
				reqToArtifact.setArtifact((ArtifactWrapper) selection.get(0));
			}
		
		}
		
		root.getTraces().add(trace);

		return root;
	}

	@Override
	public boolean isThereATraceBetween(EObject firstElement, EObject secondElement, Optional<EObject> traceModel) {
		return traceModel.map(tm -> {
			RtLabsTraceModel root = (RtLabsTraceModel) tm;
			return root.getTraces().stream().anyMatch(trace -> isRelevant(trace, firstElement, secondElement));
		}).orElse(false);
	}

	private boolean isRelevant(Trace trace, EObject firstElement, EObject secondElement) {
		return trace.computeTracedElements().contains(firstElement) && trace.computeTracedElements().contains(secondElement) && !firstElement.equals(secondElement);
	}

	@Override
	public Map<EObject, List<EObject>> getConnectedElements(EObject element, Optional<EObject> traceModel) {

		Map<EObject, List<EObject>> connectedElements = new HashMap<>();
		traceModel.ifPresent(tm -> {
			RtLabsTraceModel root = (RtLabsTraceModel)tm;
			root.getTraces().forEach(t -> {
				if (t.computeTracedElements().contains(element)) {
					EList<EObject> allelements = t.computeTracedElements();
					List<EObject> tracedElements = allelements.stream().filter(e->!e.equals(element)).collect(Collectors.toList());
					connectedElements.put(t, tracedElements);
				}
			});
		});
		return connectedElements;
	}


}
