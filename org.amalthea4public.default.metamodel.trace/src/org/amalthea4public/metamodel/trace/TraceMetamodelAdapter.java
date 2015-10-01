package org.amalthea4public.metamodel.trace;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;

public class TraceMetamodelAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter {


	@Override
	public Collection<EClass> getAvailableTraceTypes(Object... selection) {
		
		Collection<EClass> traceTypes = new ArrayList<>(); 
		
		if (selection.length == 2) {
			traceTypes.add(TracePackage.eINSTANCE.getTrace());
		}
		
		return traceTypes;
	}

}
 