package org.eclipse.app4mc.capra.generic.handlers.selection;

import org.eclipse.app4mc.capra.generic.views.SelectionView;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Action to remove all objects from the selection view
 * 
 * @author Anthony Anjorin, Salome Maro
 */
public class ClearSelectionHandler extends AbstractHandler  {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SelectionView.getOpenedView().clearSelection();
		return null;
	}
}
