package org.amalthea4public.tracemanagement.generic.handlers.selection;

import org.amalthea4public.tracemanagement.generic.handlers.TraceCreationHandler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class ClearSelectionHandler extends AbstractHandler  {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TraceCreationHandler.preSelection.clear();
		return null;
	}


}
