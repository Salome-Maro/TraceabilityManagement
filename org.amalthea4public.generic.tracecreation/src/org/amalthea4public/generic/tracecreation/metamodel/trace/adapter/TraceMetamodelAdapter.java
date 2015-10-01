package org.amalthea4public.generic.tracecreation.metamodel.trace.adapter;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;

public interface TraceMetamodelAdapter {

	Collection<EClass> getAvailableTraceTypes (Object ...selection);
}
