package org.amalthea4public.generic.tracecreation.handlers;


import java.util.Collection;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelExtensionHelper;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class TraceCreationHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public TraceCreationHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		Collection<EClass> traceTypes = TraceMetamodelExtensionHelper.getExtension().getAvailableTraceTypes(selection);
		
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(window.getShell(), new LabelProvider(){
			
			@Override
			public String getText(Object element) {
				EClass eclass = (EClass)element;
				return eclass.getName();
			}
		});
		dialog.setTitle("Select the trace type you want to create");
		dialog.setElements(traceTypes.toArray());
		
		if (dialog.open() == Window.OK) {
			
			return dialog.getFirstResult();
		}
		
		return null;
	}
}
