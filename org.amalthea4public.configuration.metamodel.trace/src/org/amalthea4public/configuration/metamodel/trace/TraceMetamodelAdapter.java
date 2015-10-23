package org.amalthea4public.configuration.metamodel.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.amalthea4public.metamodel.trace.AdvancedTraceModel;
import org.amalthea4public.metamodel.trace.Trace;
import org.amalthea4public.metamodel.trace.TraceFactory;
import org.amalthea4public.metamodel.trace.TracePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {

	@Override
	public Collection<EClass> getAvailableTraceTypes(Object... selection) {

		Collection<EClass> traceTypes = new ArrayList<>();

		if (TraceCreationHelper.isEMFSelection(Arrays.asList(selection))) {
			switch (selection.length) {
			case 0:
				break;
			case 1:
				traceTypes.add(TracePackage.eINSTANCE.getUnaryTrace());
				traceTypes.add(TracePackage.eINSTANCE.getNAryTrace());
				break;
			case 2:
				traceTypes.add(TracePackage.eINSTANCE.getBinaryTrace());
				traceTypes.add(TracePackage.eINSTANCE.getNAryTrace());
				break;

			default:
				traceTypes.add(TracePackage.eINSTANCE.getNAryTrace());
				break;
			}
		}

		return traceTypes;
	}

	@Override
	public EObject createTrace(EClass traceType, Optional<EObject> traceModel, Object... selection) {
		AdvancedTraceModel root = (AdvancedTraceModel) traceModel.orElse(TraceFactory.eINSTANCE.createAdvancedTraceModel());
		
		Trace trace = (Trace) TraceFactory.eINSTANCE.create(traceType);
		Arrays.asList(selection).forEach(o -> trace.getElements().add((EObject)o));
		
		root.getTraces().add(trace);
		
		return root;
	}


	@Override
	public boolean isThereATraceBetween(EObject firstElement, EObject secondElement, Optional<EObject> traceModel) {
		return traceModel.map(tm -> {
			AdvancedTraceModel root = (AdvancedTraceModel) tm;
			return root.getTraces().stream().anyMatch(trace -> isRelevant(trace, firstElement, secondElement));
		}).orElse(false);
	}

	private boolean isRelevant(Trace trace, EObject firstElement, EObject secondElement) {
		return trace.getElements().contains(firstElement) && trace.getElements().contains(secondElement);
	}
}
