package org.amalthea4public.configuration.metamodel.trace;

import java.util.ArrayList;
import java.util.Collection;

import org.amalthea4public.metamodel.trace.TracePackage;
import org.eclipse.emf.ecore.EClass;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {

	@Override
	public Collection<EClass> getAvailableTraceTypes(Object... selection) {
		
		Collection<EClass> traceTypes = new ArrayList<>(); 
		
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
		
		return traceTypes;
	}

}
