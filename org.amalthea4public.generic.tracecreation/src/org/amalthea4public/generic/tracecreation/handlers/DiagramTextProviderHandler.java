package org.amalthea4public.generic.tracecreation.handlers;

import java.util.List;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TracePersistenceAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

public class DiagramTextProviderHandler implements net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider {

	private static Object[] selectedModels;
	private static EObject traceModel;

	@Override
	public String getDiagramText(IEditorPart arg0, ISelection arg1) {
		TraceMetamodelAdapter traceAdapter = TraceCreationHelper.getTraceMetamodelAdapter().get();

		List<EObject> firstModelElements = TraceCreationHelper.linearize(selectedModels[0]);
		List<EObject> secondModelElements = TraceCreationHelper.linearize(selectedModels[1]);

		ResourceSet set = ((EObject) selectedModels[0]).eResource().getResourceSet();
		if(!set.getResources().contains(traceModel.eResource())) 
			set.getResources().add(traceModel.eResource());
		
		String umlString = "@startuml\nsalt\n{#\n .";
		for (EObject secondElement : secondModelElements) {
			umlString += "|" + secondElement;
		}
		umlString += "\n";

		for (EObject firstElement : firstModelElements) {
			umlString += firstElement;
			for (EObject secondElement : secondModelElements) {
				umlString += "|" + (traceAdapter.isThereATraceBetween(firstElement, secondElement, traceModel)
						? "X" : ".");
			}
			umlString+= "\n";
		}

		umlString += "} \n @enduml";

		System.out.println(umlString);

		return umlString;
	}

	@Override
	public boolean supportsEditor(IEditorPart arg0) {
		return true;
	}

	@Override
	public boolean supportsSelection(ISelection arg0) {
		return true;
	}

	public static void setSelection(Object[] selection) {
		selectedModels = selection;
		TracePersistenceAdapter persistenceAdapter = TraceCreationHelper.getTracePersistenceAdapter().get();
		traceModel = persistenceAdapter.getTraceModel().get();
	}

}
