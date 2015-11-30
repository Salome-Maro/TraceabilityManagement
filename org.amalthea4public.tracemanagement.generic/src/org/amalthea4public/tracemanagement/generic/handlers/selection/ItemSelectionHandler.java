package org.amalthea4public.tracemanagement.generic.handlers.selection;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.amalthea4public.tracemanagement.generic.handlers.ArtifactHandler;
import org.amalthea4public.tracemanagement.generic.handlers.TraceCreationHandler;
import org.amalthea4public.tracemanagement.generic.helpers.ExtensionPointHelper;
import org.amalthea4public.tracemanagement.generic.helpers.TraceCreationHelper;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class ItemSelectionHandler extends AbstractHandler {
	private IWorkbenchWindow window;
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		List<Object> selection = TraceCreationHelper.extractSelectedElements(event);
		
		Collection<ArtifactHandler> artifactHandlers = ExtensionPointHelper.getArtifactHandlers();
		window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		List<ArtifactHandler> availableHandlers = artifactHandlers.stream()
				.filter(handler -> handler.canHandleSelection(selection)).collect(Collectors.toList());
		if (availableHandlers.size() == 0) {
			MessageDialog.openWarning(window.getShell(), "No handler for selected item",
					"There is no handler for " + selection + " so it will be ignored.");
		} else if (availableHandlers.size() > 1) {
			MessageDialog.openWarning(window.getShell(), "Multiple handlers for selected item",
					"There are multiple handlers for " + selection + " so it will be ignored.");
		} else {
			TraceCreationHandler.preSelection.addAll(selection);
		}
		return null;
	}

}
