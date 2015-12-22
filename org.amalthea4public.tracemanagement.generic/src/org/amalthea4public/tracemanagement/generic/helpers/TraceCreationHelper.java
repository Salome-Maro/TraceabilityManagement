package org.amalthea4public.tracemanagement.generic.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class TraceCreationHelper {
	/**
	 * Extract selected elements from a selection event
	 * 
	 * @param event
	 *            This is the click event to create a trace
	 * @return A list of all the selected elements
	 */
	public static List<Object> extractSelectedElements(ExecutionEvent event) {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		return extractSelectedElements(currentSelection);
	}

	/**
	 * Extract selected elements from an {@link ISelection}
	 * 
	 * @param selection
	 * @return A list of all the selected elements
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> extractSelectedElements(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sselection = (IStructuredSelection) selection;
			return sselection.toList();
		} else {
			return new ArrayList<Object>();
		}
	}

}