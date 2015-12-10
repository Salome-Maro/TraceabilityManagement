package org.amalthea4public.tracemanagement.generic.handlers.selection;

import org.amalthea4public.tracemanagement.generic.views.SelectionView;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ClearSelectionHandler extends AbstractHandler  {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			SelectionView v = (SelectionView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.showView(SelectionView.ID).getViewSite().getPart();
			v.clearSelection();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
