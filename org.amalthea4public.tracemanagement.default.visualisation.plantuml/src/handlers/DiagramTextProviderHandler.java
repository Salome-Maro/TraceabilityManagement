package handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.amalthea4public.tracemanagement.generic.adapters.Connection;
import org.amalthea4public.tracemanagement.generic.adapters.TraceMetamodelAdapter;
import org.amalthea4public.tracemanagement.generic.adapters.TracePersistenceAdapter;
import org.amalthea4public.tracemanagement.generic.helpers.EMFHelper;
import org.amalthea4public.tracemanagement.generic.helpers.ExtensionPointHelper;
import org.amalthea4public.tracemanagement.generic.helpers.TraceCreationHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

/**
 * Provides PlantUML with a string representation of elements connected by trace links
 * 
 * @author Anthony Anjorin, Salome Maro
 */
public class DiagramTextProviderHandler implements DiagramTextProvider {
	private TraceMetamodelAdapter metamodelAdapter;
	private List<Connection> traces = new ArrayList<>();

	@Override
	public String getDiagramText(IEditorPart editor, ISelection input) {
		TracePersistenceAdapter persistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		metamodelAdapter = ExtensionPointHelper.getTraceMetamodelAdapter().get();

		EcoreEditor eeditor = EcoreEditor.class.cast(editor);
		List<Object> selectedModels = TraceCreationHelper.extractSelectedElements(eeditor.getSelection());
		List<EObject> firstModelElements = null;
		List<EObject> secondModelElements = null;

		ResourceSet resourceSet = new ResourceSetImpl();
		if (selectedModels.size() > 0 && selectedModels.get(0) instanceof EObject)
			resourceSet = ((EObject) selectedModels.get(0)).eResource().getResourceSet();
		
		Optional<EObject> traceModel = persistenceAdapter.getTraceModel(resourceSet);

		if (selectedModels.size() == 1 && selectedModels.get(0) instanceof EObject) {
			EObject selectedEObject = (EObject) selectedModels.get(0);
			if (DisplayTracesHandler.isTraceViewTransitive()) {
				List<Object> accumulator = new ArrayList<>();
				traces = getTransitivelyConnectedElements(selectedEObject, traceModel, accumulator);
			} else {
				traces = metamodelAdapter.getConnectedElements(selectedEObject, traceModel);
			}

			return VisualizationHelper.createNeighboursView(traces, selectedEObject);
		} else if (selectedModels.size() == 2) {
			firstModelElements = EMFHelper.linearize(selectedModels.get(0));
			secondModelElements = EMFHelper.linearize(selectedModels.get(1));
		} else {
			firstModelElements = selectedModels.stream().flatMap(r -> EMFHelper.linearize(r).stream())
					.collect(Collectors.toList());

			secondModelElements = firstModelElements;
		}

		String umlString = VisualizationHelper.createMatrix(traceModel, firstModelElements, secondModelElements);

		return umlString;

	}

	private List<Connection> getTransitivelyConnectedElements(EObject element, Optional<EObject> traceModel,
			List<Object> accumulator) {
		List<Connection> directElements = metamodelAdapter.getConnectedElements(element, traceModel);
		List<Connection> allElements = new ArrayList<>();

		directElements.forEach(connection -> {
			if (!accumulator.contains(connection.getTlink())) {
				allElements.add(connection);
				accumulator.add(connection.getTlink());
				connection.getTargets().forEach(e -> {
					allElements.addAll(getTransitivelyConnectedElements(e, traceModel, accumulator));

				});
			}
		});

		return allElements;
	}

	@Override
	public boolean supportsEditor(IEditorPart editor) {
		return editor instanceof EcoreEditor;
	}

	@Override
	public boolean supportsSelection(ISelection selection) {
		return true;
	}
}
