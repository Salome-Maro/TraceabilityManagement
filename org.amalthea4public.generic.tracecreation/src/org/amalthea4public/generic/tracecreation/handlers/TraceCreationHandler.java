package org.amalthea4public.generic.tracecreation.handlers;

import java.util.ArrayList;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
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
	public static Collection<Object> preSelection = new ArrayList<>();
	private IWorkbenchWindow window;
	
	public TraceCreationHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		List<Object> selection = TraceCreationHelper.extractSelectedElements(event);

		preSelection.addAll(selection);
		
		TraceMetamodelAdapter traceAdapter = TraceCreationHelper.getTraceMetamodelAdapter().get();
		TracePersistenceAdapter persistenceAdapter = TraceCreationHelper.getTracePersistenceAdapter().get();
		Collection<ArtifactHandler> artifactHandlers = TraceCreationHelper.getArtifactHandlers();
		
		List<EObject> selectionAsEObjects = preSelection.stream()
														.map(sel -> convertToEObject(sel, artifactHandlers))
														.filter(Optional::isPresent)
														.map(Optional::get)
														.collect(Collectors.toList());
		
		Collection<EClass> traceTypes = traceAdapter.getAvailableTraceTypes(selectionAsEObjects);
		Optional<EClass> chosenType = getTraceTypeToCreate(window, traceTypes, selectionAsEObjects);

		Optional<EObject> traceModel = persistenceAdapter.getTraceModel();

		if (chosenType.isPresent()){
			EObject root = traceAdapter.createTrace(chosenType.get(), traceModel, selectionAsEObjects);
			persistenceAdapter.saveTraceModel(root);
			preSelection.clear();
		}
		
		return null;
	}



	private Optional<EObject> convertToEObject(Object sel, Collection<ArtifactHandler> artifactHandlers) {
		List<ArtifactHandler> availableHandlers = artifactHandlers.stream()
																		.filter(handler -> handler.canHandleSelection(sel))
																		.collect(Collectors.toList());
		if(availableHandlers.size() == 1){
			return Optional.of(availableHandlers.get(0).getEObjectForSelection(sel)); 
		}else{
			MessageDialog.openWarning(window.getShell(), "No handler for selected item", "There is no handler for " + sel + " so it will be ignored.");
			return Optional.empty();
		}
		
	}

	private Optional<EClass> getTraceTypeToCreate(IWorkbenchWindow window, Collection<EClass> traceTypes, List<EObject> selectionAsEObjects) {
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(window.getShell(), new LabelProvider() {
			@Override
			public String getText(Object element) {
				EClass eclass = (EClass) element;
				return eclass.getName();
			}
		});
		dialog.setTitle("Select the trace type you want to create");
		dialog.setElements(traceTypes.toArray());
		dialog.setMessage("Selection: " + selectionAsEObjects.stream()
			     									         .map(TraceCreationHelper::getIdentifier)
			     									         .collect(Collectors.toList()));

		if (dialog.open() == Window.OK) {
			return Optional.of((EClass) dialog.getFirstResult());
		}

		return Optional.empty();
	}
}
