package org.amalthea4public.generic.tracecreation.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TracePersistenceAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

public class DiagramTextProviderHandler implements DiagramTextProvider {

	@Override
	public String getDiagramText(IEditorPart editor, ISelection input) {
		TracePersistenceAdapter persistenceAdapter = TraceCreationHelper.getTracePersistenceAdapter().get();
		Optional<EObject> traceModel = persistenceAdapter.getTraceModel();
		TraceMetamodelAdapter metamodelAdapter = TraceCreationHelper.getTraceMetamodelAdapter().get();

		EcoreEditor eeditor = EcoreEditor.class.cast(editor);
		List<Object> selectedModels = TraceCreationHelper.extractSelectedElements(eeditor.getSelection());
		List<EObject> firstModelElements = null;
		List<EObject> secondModelElements = null;

		if(selectedModels.size() == 1 && selectedModels.get(0) instanceof EObject){
			
			EObject selectedEObject = (EObject) selectedModels.get(0);
			
			addTraceModelToResourceSet(selectedModels, traceModel);
			Map<EObject, List<EObject>> traces = metamodelAdapter.getConnectedElements(selectedEObject, traceModel);
			removeTraceModelFromResourceSet(selectedModels, traceModel);
			
			List<String> traceLabels = new ArrayList<>();
			List<EObject> connectedElements = new ArrayList<>();
			traces.keySet().forEach(k -> {
				traces.get(k).forEach(e -> {
					traceLabels.add(TraceCreationHelper.getIdentifier(k));
					connectedElements.add(e);
				});
			});
			
			return VisualizationHelper.createNeighboursView(connectedElements, traceLabels, selectedEObject);
		}
		else if (selectedModels.size() == 2) {
			firstModelElements = TraceCreationHelper.linearize(selectedModels.get(0));
			secondModelElements = TraceCreationHelper.linearize(selectedModels.get(1));
		}else {
			firstModelElements = selectedModels.stream()
					      					   .flatMap(r -> TraceCreationHelper.linearize(r).stream())
					      					   .collect(Collectors.toList());
			
			secondModelElements = firstModelElements;
		}
		
		addTraceModelToResourceSet(selectedModels, traceModel);
		String umlString = VisualizationHelper.createMatrix(traceModel, firstModelElements, secondModelElements);
		removeTraceModelFromResourceSet(selectedModels, traceModel);
		
		return umlString;
	}


	private void removeTraceModelFromResourceSet(List<Object> selectedModels, Optional<EObject> traceModel) {
		traceModel.ifPresent(tm -> {
			if (selectedModels.size() > 0 && selectedModels.get(0) instanceof EObject) {
				ResourceSet set = ((EObject) selectedModels.get(0)).eResource().getResourceSet();
				set.getResources().remove(tm.eResource());
			}
		});
	}

	private void addTraceModelToResourceSet(List<Object> selectedModels, Optional<EObject> traceModel) {
		traceModel.ifPresent(tm -> {
			if (selectedModels.size() > 0 && selectedModels.get(0) instanceof EObject) {
				ResourceSet set = ((EObject) selectedModels.get(0)).eResource().getResourceSet();
				if (!set.getResources().contains(tm.eResource()))
					set.getResources().add(tm.eResource());
			}
		});
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
