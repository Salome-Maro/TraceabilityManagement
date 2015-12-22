package org.amalthea4public.tracemanagement.generic.handlers.selection;

import java.util.List;

import org.amalthea4public.tracemanagement.generic.helpers.TraceCreationHelper;
import org.amalthea4public.tracemanagement.generic.views.SelectionView;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Action to remove a single object from the selection view
 * 
 * @author Anthony Anjorin, Salome Maro
 */
public class RemoveSelectionHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		List<Object> selection = TraceCreationHelper.extractSelectedElements(event);
		SelectionView.getOpenedView().removeFromSelection(selection);
		return null;
	}
}
