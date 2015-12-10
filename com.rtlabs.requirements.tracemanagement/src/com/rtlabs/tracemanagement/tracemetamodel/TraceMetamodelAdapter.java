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
import com.rtlabs.traces.ArtifactToArtifact;
import com.rtlabs.traces.Children;
import com.rtlabs.traces.ReqToArtifact;
import com.rtlabs.traces.ReqToReq;
import com.rtlabs.traces.RtLabsTraceModel;
import com.rtlabs.traces.Trace;
import com.rtlabs.traces.TracesFactory;
import com.rtlabs.traces.TracesPackage;

public class TraceMetamodelAdapter
		implements org.amalthea4public.tracemanagement.generic.adapters.TraceMetamodelAdapter {

	@Override
	public Collection<EClass> getAvailableTraceTypes(List<EObject> selection) {

		Collection<EClass> traceTypes = new ArrayList<>();

		if (selection.size() == 2 && everythingIsARequirement(selection)) {
			traceTypes.add(TracesPackage.eINSTANCE.getReqToReq());
		}
		if (selection.size() == 2 && selectionContainsRequirementAndArtifact(selection)) {
			traceTypes.add(TracesPackage.eINSTANCE.getReqToArtifact());
		}
		if (selection.size() >= 2 && everythingIsARequirement(selection)) {
			traceTypes.add(TracesPackage.eINSTANCE.getChildren());
		}
		if (selection.size() == 2 && everythingIsArtifact(selection)) {
			traceTypes.add(TracesPackage.eINSTANCE.getArtifactToArtifact());
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

	private boolean everythingIsArtifact(List<EObject> selection) {
		return selection.stream().allMatch(sel->sel instanceof ArtifactWrapper);
		
	}
	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, List<EObject> selection) {

		RtLabsTraceModel root = (RtLabsTraceModel) traceModel.orElse(TracesFactory.eINSTANCE.createRtLabsTraceModel());

		Trace trace = (Trace) TracesFactory.eINSTANCE.create(traceType);
		if (trace instanceof ReqToReq) {
			ReqToReq reqToReq = (ReqToReq) trace;
			reqToReq.setSource((Requirement) selection.get(0));
			reqToReq.setTarget((Requirement) selection.get(1));
		} else if (trace instanceof Children) {
			Children reqChildren = (Children) trace;
			reqChildren.setParent((Requirement) selection.get(0));
			selection.stream().forEach(e -> {
				if (!e.equals(selection.get(0))) {
					reqChildren.getChild().add((Requirement) e);
				}
			});

		} else if (trace instanceof ReqToArtifact) {
			ReqToArtifact reqToArtifact = (ReqToArtifact) trace;
			if (selection.get(0) instanceof Requirement) {
				reqToArtifact.setSource((Requirement) selection.get(0));
				reqToArtifact.setTarget((ArtifactWrapper) selection.get(1));
			} else {
				reqToArtifact.setSource((Requirement) selection.get(1));
				reqToArtifact.setTarget((ArtifactWrapper) selection.get(0));
			}
		} else if (trace instanceof ArtifactToArtifact) {
			ArtifactToArtifact t = (ArtifactToArtifact) trace;
			t.setSource((ArtifactWrapper)selection.get(0));
			t.setTarget((ArtifactWrapper)selection.get(1));
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
		return trace.computeTracedElements().contains(firstElement)
				&& trace.computeTracedElements().contains(secondElement) && !firstElement.equals(secondElement);
	}

	@Override
	public Map<EObject, List<EObject>> getConnectedElements(EObject element, Optional<EObject> traceModel) {

		Map<EObject, List<EObject>> connectedElements = new HashMap<>();

		traceModel.ifPresent(tm -> {
			RtLabsTraceModel root = (RtLabsTraceModel) tm;
			root.getTraces().forEach(t -> {
				if (t.computeTracedElements().contains(element)) {
					if (t instanceof Children) {
						if (((Children) t).getChild().contains(element)) {
							List<EObject> tracedElements = new ArrayList<>();
							tracedElements.add(((Children) t).getParent());
							connectedElements.put(t, tracedElements);
						} else {
							List<EObject> tracedElements = new ArrayList<>();
							EList<EObject> allelements = t.computeTracedElements();
							tracedElements = allelements.stream().filter(e -> !e.equals(element))
									.collect(Collectors.toList());
							connectedElements.put(t, tracedElements);
						}

					} else {
						List<EObject> tracedElements = new ArrayList<>();
						EList<EObject> allelements = t.computeTracedElements();
						tracedElements = allelements.stream().filter(e -> !e.equals(element))
								.collect(Collectors.toList());
						connectedElements.put(t, tracedElements);
					}

				}
			});
			
			if (element instanceof ReqToReq) {
				List<EObject> tracedSource = new ArrayList<>();
				ReqToReq trace = (ReqToReq) element;
				tracedSource.add(trace.getSource());
				connectedElements.put(trace.eClass().getEStructuralFeature(TracesPackage.REQ_TO_REQ__SOURCE), tracedSource);
				
				List<EObject> tracedTarget = new ArrayList<>();
				tracedTarget.add(trace.getTarget());
				connectedElements.put(trace.eClass().getEStructuralFeature(TracesPackage.REQ_TO_REQ__TARGET), tracedTarget);
			}
			if (element instanceof Children) {
				Children c = (Children) element;
				List<EObject> tracedParent = new ArrayList<>();
				tracedParent.add(c.getParent());
				connectedElements.put(c.eClass().getEStructuralFeature(TracesPackage.CHILDREN__PARENT), tracedParent);
				
				List<EObject> tracedChildren = new ArrayList<>();
				tracedChildren.addAll(c.getChild());
				connectedElements.put(c.eClass().getEStructuralFeature(TracesPackage.CHILDREN__CHILD), tracedChildren);
			}
			
			if (element instanceof ReqToArtifact) {
				List<EObject> tracedSource = new ArrayList<>();
				ReqToArtifact trace = (ReqToArtifact) element;
				tracedSource.add(trace.getSource());
				connectedElements.put(trace.eClass().getEStructuralFeature(TracesPackage.REQ_TO_ARTIFACT__SOURCE), tracedSource);
				
				List<EObject> tracedTarget = new ArrayList<>();
				tracedTarget.add(trace.getTarget());
				connectedElements.put(trace.eClass().getEStructuralFeature(TracesPackage.REQ_TO_ARTIFACT__TARGET), tracedTarget);
			}
			
			if (element instanceof ArtifactToArtifact) {
				List<EObject> tracedSource = new ArrayList<>();
				ArtifactToArtifact trace = (ArtifactToArtifact) element;
				tracedSource.add(trace.getSource());
				connectedElements.put(trace.eClass().getEStructuralFeature(TracesPackage.ARTIFACT_TO_ARTIFACT__SOURCE), tracedSource);
				
				List<EObject> tracedTarget = new ArrayList<>();
				tracedTarget.add(trace.getTarget());
				connectedElements.put(trace.eClass().getEStructuralFeature(TracesPackage.ARTIFACT_TO_ARTIFACT__TARGET), tracedTarget);
			}
		});
		return connectedElements;
	}

}
