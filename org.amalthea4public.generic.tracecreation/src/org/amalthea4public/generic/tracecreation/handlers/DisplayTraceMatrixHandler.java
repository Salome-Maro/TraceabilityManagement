package org.amalthea4public.generic.tracecreation.handlers;

import java.util.List;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TracePersistenceAdapter;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.core.resources.IFile;

import org.eclipse.ui.part.FileEditorInput;

public class DisplayTraceMatrixHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Object[] selection = TraceCreationHelper.extractSelectedElements(event);
		
		TracePersistenceAdapter persistenceAdapter = TraceCreationHelper.getTracePersistenceAdapter().get();
		Optional<EObject> traceModel = persistenceAdapter.getTraceModel();
		
		DiagramTextProviderHandler.setSelection(selection);
	
		try {
			IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("net.sourceforge.plantuml.eclipse.views.PlantUmlView");
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}