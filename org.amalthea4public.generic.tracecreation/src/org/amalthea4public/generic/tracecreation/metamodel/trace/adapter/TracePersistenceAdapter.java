package org.amalthea4public.generic.tracecreation.metamodel.trace.adapter;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

public interface TracePersistenceAdapter {

	/**
	 * Used to retrieve the root of a trace model.
	 * @return Root containing all traces.
	 */
	public Optional<EObject> getTraceModel();

	/**
	 * Used to save the trace model.
	 * @param root
	 */
	public void saveTraceModel(EObject root);
}
