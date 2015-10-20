package org.amalthea4public.generic.tracecreation.handlers;

import java.util.List;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TracePersistenceAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class DiagramTextProviderHandler extends net.sourceforge.plantuml.eclipse.utils.AbstractDiagramTextProvider {

	@Override
	public String getDiagramText(IEditorPart editor, IEditorInput input) {
		TracePersistenceAdapter persistenceAdapter = TraceCreationHelper.getTracePersistenceAdapter().get();
		Optional<EObject> traceModel = persistenceAdapter.getTraceModel();

		EcoreEditor eeditor = EcoreEditor.class.cast(editor);
		Object[] selectedModels = TraceCreationHelper.extractSelectedElements(eeditor.getSelection());

		String umlString = "@startuml\nsalt\n{#\n";

		if (selectedModels.length == 2) {
			addTraceModelToResourceSet(selectedModels, traceModel);

			List<EObject> firstModelElements = TraceCreationHelper.linearize(selectedModels[0]);
			List<EObject> secondModelElements = TraceCreationHelper.linearize(selectedModels[1]);

			umlString += " .";
			for (EObject secondElement : secondModelElements) {
				umlString += "|" + TraceCreationHelper.getIdentifier(secondElement);
			}
			umlString += "\n";

			TraceMetamodelAdapter traceAdapter = TraceCreationHelper.getTraceMetamodelAdapter().get();
			for (EObject firstElement : firstModelElements) {
				umlString += TraceCreationHelper.getIdentifier(firstElement);
				for (EObject secondElement : secondModelElements) {
					umlString += "|"
							+ (traceAdapter.isThereATraceBetween(firstElement, secondElement, traceModel) ? "X" : ".");
				}
				umlString += "\n";
			}
			
			removeTraceModelFromResourceSet(selectedModels, traceModel);
		} else {
			umlString += "Choose two containers to show a traceability matrix of their contents";
		}

		umlString += "} \n @enduml";

		return umlString;
	}

	private void removeTraceModelFromResourceSet(Object[] selectedModels, Optional<EObject> traceModel) {
		traceModel.ifPresent(tm -> {
			ResourceSet set = ((EObject) selectedModels[0]).eResource().getResourceSet();
			set.getResources().remove(tm.eResource());
		});
	}

	private void addTraceModelToResourceSet(Object[] selectedModels, Optional<EObject> traceModel) {
		traceModel.ifPresent(tm -> {
			ResourceSet set = ((EObject) selectedModels[0]).eResource().getResourceSet();
			if (!set.getResources().contains(tm.eResource()))
				set.getResources().add(tm.eResource());
		});
	}
}
