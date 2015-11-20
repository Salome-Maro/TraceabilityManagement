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

	public static List<Object> extractSelectedElements(ExecutionEvent event) {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		return extractSelectedElements(currentSelection);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> extractSelectedElements(ISelection selection){
		if(selection instanceof IStructuredSelection){
			IStructuredSelection sselection = (IStructuredSelection) selection;
			return sselection.toList();
		}else {
			return new ArrayList<Object>();
		}
	}

	public static boolean noArtifactsSelected(Collection<EObject> selection) {
		return selection.stream().noneMatch(o -> o instanceof ArtifactWrapper);
	}
	
}