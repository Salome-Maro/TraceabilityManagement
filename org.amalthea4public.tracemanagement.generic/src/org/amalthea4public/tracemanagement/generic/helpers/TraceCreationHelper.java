package org.amalthea4public.tracemanagement.generic.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class TraceCreationHelper {
	/**
	 * 
	 * @param event
	 * This is the click event to create a trace
	 * @return
	 * A list of all the selected elements
	 */
	public static List<Object> extractSelectedElements(ExecutionEvent event) {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		return extractSelectedElements(currentSelection);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param selection
	 * @return
	 * A list of all the selected elements
	 */
	public static List<Object> extractSelectedElements(ISelection selection){
		if(selection instanceof IStructuredSelection){
			IStructuredSelection sselection = (IStructuredSelection) selection;
			return sselection.toList();
		}else {
			return new ArrayList<Object>();
		}
	}
	
}