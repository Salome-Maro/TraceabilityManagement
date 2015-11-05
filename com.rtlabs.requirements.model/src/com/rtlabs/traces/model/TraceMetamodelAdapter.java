package com.rtlabs.traces.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.rtlabs.artifacts.Artifact;
import com.rtlabs.requirements.Requirement;
import com.rtlabs.traces.ReqToArtifact;
import com.rtlabs.traces.ReqToReq;
import com.rtlabs.traces.Trace;
import com.rtlabs.traces.TraceModel;
import com.rtlabs.traces.TracesFactory;
import com.rtlabs.traces.TracesPackage;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {

	@Override
	public Collection<EClass> getAvailableTraceTypes(Object... selection) {

		Collection<EClass> traceTypes = new ArrayList<>();

		if (TraceCreationHelper.isEMFSelection(Arrays.asList(selection)) && selection.length == 2) {
			if (everythingIsARequirement(selection))
				traceTypes.add(TracesPackage.eINSTANCE.getReqToReq());
			else if (selectionContainsRequirementAndArtifact(selection))
				traceTypes.add(TracesPackage.eINSTANCE.getReqToArtifact());
		}

		return traceTypes;
	}

	private boolean selectionContainsRequirementAndArtifact(Object... selection) {
		return Arrays.asList(selection).stream().anyMatch(o -> o instanceof Requirement)
				&& Arrays.asList(selection).stream().anyMatch(o -> o instanceof Artifact);
	}

	private boolean everythingIsARequirement(Object... selection) {
		return Arrays.asList(selection).stream().allMatch(o -> o instanceof Requirement);
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, Object... selection) {
		TraceModel root = (TraceModel) traceModel.orElse(TracesFactory.eINSTANCE.createTraceModel());

		Trace trace = (Trace) TracesFactory.eINSTANCE.create(traceType);
		if (trace instanceof ReqToReq) {
			ReqToReq reqToReq = (ReqToReq) trace;
			reqToReq.setParent((Requirement) selection[0]);
			reqToReq.setChild((Requirement) selection[1]);
		} else if (trace instanceof ReqToArtifact) {
			ReqToArtifact reqToArtifact = (ReqToArtifact) trace;
			reqToArtifact.setParent((Requirement) selection[0]);
			reqToArtifact.setArtifact((Artifact) selection[1]);
		
		}
		
		root.getTraces().add(trace);

		return root;
	}

	@Override
	public boolean isThereATraceBetween(EObject firstElement, EObject secondElement, Optional<EObject> traceModel) {
		return traceModel.map(tm -> {
			TraceModel root = (TraceModel) tm;
			return root.getTraces().stream().anyMatch(trace -> isRelevant(trace, firstElement, secondElement));
		}).orElse(false);
	}

	private boolean isRelevant(Trace trace, EObject firstElement, EObject secondElement) {
		return trace.computeTracedElements().contains(firstElement) && trace.computeTracedElements().contains(secondElement) && !firstElement.equals(secondElement);
	}

}
