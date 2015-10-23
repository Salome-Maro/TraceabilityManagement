package org.amalthea4public.generic.tracecreation.handlers;

import java.util.List;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
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

		EcoreEditor eeditor = EcoreEditor.class.cast(editor);
		Object[] selectedModels = TraceCreationHelper.extractSelectedElements(eeditor.getSelection());
		List<EObject> firstModelElements = null;
		List<EObject> secondModelElements = null;

		if(selectedModels.length == 1){
			firstModelElements = TraceCreationHelper.linearize(selectedModels[0]);
			secondModelElements = TraceCreationHelper.linearize(selectedModels[0]);
		}
		else if (selectedModels.length == 2) {
			firstModelElements = TraceCreationHelper.linearize(selectedModels[0]);
			secondModelElements = TraceCreationHelper.linearize(selectedModels[1]);
		}
		
		addTraceModelToResourceSet(selectedModels, traceModel);
		String umlString = MatrixHelper.createMatrix(traceModel, firstModelElements, secondModelElements);
		removeTraceModelFromResourceSet(selectedModels, traceModel);
		
		return umlString;
	}

	private void removeTraceModelFromResourceSet(Object[] selectedModels, Optional<EObject> traceModel) {
		traceModel.ifPresent(tm -> {
			if (selectedModels.length > 0 && selectedModels[0] instanceof EObject) {
				ResourceSet set = ((EObject) selectedModels[0]).eResource().getResourceSet();
				set.getResources().remove(tm.eResource());
			}
		});
	}

	private void addTraceModelToResourceSet(Object[] selectedModels, Optional<EObject> traceModel) {
		traceModel.ifPresent(tm -> {
			if (selectedModels.length > 0 && selectedModels[0] instanceof EObject) {
				ResourceSet set = ((EObject) selectedModels[0]).eResource().getResourceSet();
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
