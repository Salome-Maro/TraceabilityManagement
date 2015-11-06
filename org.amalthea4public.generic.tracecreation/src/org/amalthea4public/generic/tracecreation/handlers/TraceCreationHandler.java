package org.amalthea4public.generic.tracecreation.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.amalthea4public.generic.tracecreation.artifacthandling.ArtifactHandler;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TracePersistenceAdapter;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
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
		Object[] selection = TraceCreationHelper.extractSelectedElements(event);

		TraceMetamodelAdapter traceAdapter = TraceCreationHelper.getTraceMetamodelAdapter().get();
		TracePersistenceAdapter persistenceAdapter = TraceCreationHelper.getTracePersistenceAdapter().get();
		Collection<ArtifactHandler> artifactHandlers = TraceCreationHelper.getArtifactHandlers();
		
		List<EObject> selectionAsEObjects = Arrays.asList(selection).stream()
														.map(sel -> convertToEObject(sel, artifactHandlers))
														.collect(Collectors.toList());
		
		Collection<EClass> traceTypes = traceAdapter.getAvailableTraceTypes(selectionAsEObjects);
		Optional<EClass> chosenType = getTraceTypeToCreate(window, traceTypes);

		Optional<EObject> traceModel = persistenceAdapter.getTraceModel();

		if (chosenType.isPresent()){
			EObject root = traceAdapter.createTrace(chosenType.get(), traceModel, selectionAsEObjects);
			persistenceAdapter.saveTraceModel(root);
		}
		
		return null;
	}



	private EObject convertToEObject(Object sel, Collection<ArtifactHandler> artifactHandlers) {
		List<ArtifactHandler> availableHandlers = artifactHandlers.stream()
																		.filter(handler -> handler.canHandleSelection(sel))
																		.collect(Collectors.toList());
		if(availableHandlers.size() == 1){
			return availableHandlers.get(0).getEObjectForSelection(sel); 
		}else{
			// TODO:  No handler, too many handlers
			return null;
		}
		
	}

	private Optional<EClass> getTraceTypeToCreate(IWorkbenchWindow window, Collection<EClass> traceTypes) {
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(window.getShell(), new LabelProvider() {
			@Override
			public String getText(Object element) {
				EClass eclass = (EClass) element;
				return eclass.getName();
			}
		});
		dialog.setTitle("Select the trace type you want to create");
		dialog.setElements(traceTypes.toArray());

		if (dialog.open() == Window.OK) {
			return Optional.of((EClass) dialog.getFirstResult());
		}

		return Optional.empty();
	}
}
