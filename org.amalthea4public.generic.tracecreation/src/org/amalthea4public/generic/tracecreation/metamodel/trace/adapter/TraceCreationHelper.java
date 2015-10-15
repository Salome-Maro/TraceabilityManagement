package org.amalthea4public.generic.tracecreation.metamodel.trace.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class TraceCreationHelper {

	private static final String TRACE_ID = "org.amalthea4public.configuration.metamodel.trace";
	private static final String TRACE_CONFIG = "adapter";

	private static final String PERSISTENCE_ID = "org.amalthea4public.configuration.persistence.trace";
	private static final String PERSISTENCE_CONFIG = "adapter";
	
	public static Optional<Object> getExtension(final String ID, final String CONFIG) {
		try {
			IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(ID);
			return Optional.of(config[0].createExecutableExtension(CONFIG));
		} catch (Exception ex) {
			return Optional.empty();
		}
	}

	public static boolean isEMFSelection(Collection<Object> selection) {
		return selection.stream().allMatch(o -> o instanceof EObject);
	}

	public static Optional<TraceMetamodelAdapter> getTraceMetamodelAdapter() {
		try {
			Object extension = getExtension(TRACE_ID, TRACE_CONFIG).get();
			return Optional.of((TraceMetamodelAdapter) extension);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public static Optional<TracePersistenceAdapter> getTracePersistenceAdapter() {
		try {
			Object extension = getExtension(PERSISTENCE_ID, PERSISTENCE_CONFIG).get();
			return Optional.of((TracePersistenceAdapter) extension);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	public static Object[] extractSelectedElements(ExecutionEvent event) {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if(currentSelection instanceof IStructuredSelection){
			IStructuredSelection selection = (IStructuredSelection) currentSelection;
			return selection.toArray();
		}else {
			return new Object[]{};
		}
	}

	public static List<EObject> linearize(Object object) {
		EObject root = (EObject) object;
		ArrayList<EObject> elementList = new ArrayList<EObject>();
		root.eAllContents().forEachRemaining(element -> elementList.add(element));	
		return elementList;
	}
}
