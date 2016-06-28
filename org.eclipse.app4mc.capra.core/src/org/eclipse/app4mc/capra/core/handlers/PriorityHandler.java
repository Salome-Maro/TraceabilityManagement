package org.eclipse.app4mc.capra.core.handlers;

import java.util.Collection;


public interface PriorityHandler {
	/**
	 * This method gets a list of available handers 
	 * for a selection and returns the best (prioritized) 
	 * handler that can handle the selection
	 * 
	 * @param handlers 
	 * 		  List of available handlers for the selection
	 * 
	 * @param selectedElement 
	 * 		  The selected Object
	 * s
	 * @return one handler
	 * 
	 */
	ArtifactHandler getSelectedHandler (Collection<ArtifactHandler> handlers, Object selectedElement);

}
