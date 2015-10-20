package org.amalthea4public.metamodel.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {

	@Override
	public Collection<EClass> getAvailableTraceTypes(Object... selection) {

		Collection<EClass> traceTypes = new ArrayList<>();

		if (selection.length == 2 && TraceCreationHelper.isEMFSelection(Arrays.asList(selection))) {
			traceTypes.add(TracePackage.eINSTANCE.getTrace());
		}

		return traceTypes;
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, Object... selection) {
		TraceModel root = (TraceModel) traceModel.orElse(TraceFactory.eINSTANCE.createTraceModel());

		Trace trace = (Trace) TraceFactory.eINSTANCE.create(traceType);

		trace.setSource((EObject) selection[0]);
		trace.setTarget((EObject) selection[1]);

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
		return (trace.getSource().equals(firstElement) && trace.getTarget().equals(secondElement))
				|| (trace.getSource().equals(secondElement) && trace.getTarget().equals(firstElement));
	}

}
