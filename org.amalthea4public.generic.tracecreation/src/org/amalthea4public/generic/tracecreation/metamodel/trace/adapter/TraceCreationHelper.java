package org.amalthea4public.generic.tracecreation.metamodel.trace.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.amalthea4public.generic.tracecreation.ArtifactWrapper;
import org.amalthea4public.generic.tracecreation.artifacthandling.ArtifactHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class TraceCreationHelper {

	private static final String TRACE_ID = "org.amalthea4public.configuration.metamodel.trace";
	private static final String TRACE_CONFIG = "adapter";

	private static final String PERSISTENCE_ID = "org.amalthea4public.configuration.persistence.trace";
	private static final String PERSISTENCE_CONFIG = "adapter";
	
	private static final String ARTIFACT_HANDLER_ID = "org.amalthea4public.configuration.artifacthandler";
	private static final String ARTIFACT_HANDLER_CONFIG = "handler";
	
	public static List<Object> getExtensions(final String ID, final String CONFIG) {
		try {
			IConfigurationElement[] configs = Platform.getExtensionRegistry().getConfigurationElementsFor(ID);
			
			List<Object> extensions = new ArrayList<>();
			for (IConfigurationElement config : configs)
				extensions.add(config.createExecutableExtension(CONFIG));
			
			return extensions;
		} catch (Exception ex) {
			return Collections.emptyList();
		}
	}

	public static Optional<TraceMetamodelAdapter> getTraceMetamodelAdapter() {
		try {
			Object extension = getExtensions(TRACE_ID, TRACE_CONFIG).get(0);
			return Optional.of((TraceMetamodelAdapter) extension);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public static Optional<TracePersistenceAdapter> getTracePersistenceAdapter() {
		try {
			Object extension = getExtensions(PERSISTENCE_ID, PERSISTENCE_CONFIG).get(0);
			return Optional.of((TracePersistenceAdapter) extension);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	

	public static Collection<ArtifactHandler> getArtifactHandlers() {
		try {
			return getExtensions(ARTIFACT_HANDLER_ID, ARTIFACT_HANDLER_CONFIG).stream()
																			  .map(ArtifactHandler.class::cast)
																			  .collect(Collectors.toList());
		} catch (Exception e) {
			return Collections.<ArtifactHandler>emptyList();
		}
	}
	
	public static Object[] extractSelectedElements(ExecutionEvent event) {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		return extractSelectedElements(currentSelection);
	}
	
	public static Object[] extractSelectedElements(ISelection selection){
		if(selection instanceof IStructuredSelection){
			IStructuredSelection sselection = (IStructuredSelection) selection;
			return sselection.toArray();
		}else {
			return new Object[]{};
		}
	}

	public static List<EObject> linearize(Object object) {		
		ArrayList<EObject> elementList = new ArrayList<EObject>();
		if (object instanceof EObject) {
			EObject root = (EObject) object;
			root.eAllContents().forEachRemaining(element -> elementList.add(element));
			elementList.add(root);
		}
		return elementList;
	}

	/**
	 * Builds an identifier String for the given EObject. This identifier starts
	 * with
	 * <ul>
	 * <li>the attribute of the EObject as a String, if the EObject does only
	 * have one attribute.</li>
	 * <li>the attribute called 'name' of the EObject, if it has such an
	 * attribute</li>
	 * <li>any attribute of the EObject, but String attributes are preferred
	 * </li>
	 * </ul>
	 * The identifier ends with " : " followed by the type of the EObject. <br>
	 * Example: A Node with the name "foo" will result in "foo : Node"
	 * <br>
	 * If the EObject does not have any attributes or all attributes have the
	 * value null, this function will only return the type of the EObject.
	 */
	public static String getIdentifier(final EObject eObject) {
		boolean success = false;
		List<EAttribute> attributes = eObject.eClass().getEAllAttributes();
		StringBuilder identifier = new StringBuilder();
	
		success = TraceCreationHelper.tryGetSingleAttribute(eObject, attributes, identifier);
	
		if (!success)
			success = tryGetNameAttribute(eObject, attributes, identifier);
	
		if (!success)
			success = tryGetAnyAttribute(eObject, attributes, identifier);
	
		if (success)
			identifier.append(" : ");
	
		identifier.append(eObject.eClass().getName());
	
		return identifier.toString();
	}

	/**
	 * @param name
	 *            Use an empty StringBuilder as input. If this function returns
	 *            true, this parameter has been filled, if it returns false,
	 *            nothing happened.
	 * @return Indicates the success of this function and if the last parameter
	 *         contains output.
	 */
	public static boolean tryGetSingleAttribute(final EObject eObject, final List<EAttribute> attributes,
			final StringBuilder name) {
		boolean success = false;
		if (attributes.size() == 1) {
			Object obj = eObject.eGet(attributes.get(0));
			if (obj != null) {
				name.append(obj.toString());
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * @param name
	 *            Use an empty StringBuilder as input. If this function returns
	 *            true, this parameter has been filled, if it returns false,
	 *            nothing happened.
	 * @return Indicates the success of this function and if the last parameter
	 *         contains output.
	 */
	public static boolean tryGetNameAttribute(final EObject eObject, final List<EAttribute> attributes,
			final StringBuilder name) {
		boolean success = false;
		for (EAttribute feature : attributes) {
			if (feature.getName().equals("name")) {
				Object obj = eObject.eGet(feature);
				if (obj != null) {
					name.append(obj.toString());
					success = true;
					break;
				}
			}
		}
		return success;
	}

	/**
	 * @param name
	 *            Use an empty StringBuilder as input. If this function returns
	 *            true, this parameter has been filled, if it returns false,
	 *            nothing happened.
	 * @return Indicates the success of this function and if the last parameter
	 *         contains output.
	 */
	public static boolean tryGetAnyAttribute(final EObject eObject, final List<EAttribute> attributes,
			final StringBuilder name) {
		boolean success = false;
		String nonStringName = null;
		String stringName = null;
		for (EAttribute feature : attributes) {
			Object obj = eObject.eGet(feature);
			if (obj == null)
				continue;
			if (obj instanceof String) {
				stringName = (String) obj;
				break;
			} else {
				nonStringName = obj.toString();
			}
		}
		if (stringName != null && !stringName.equals("null")) {
			name.append(stringName);
			success = true;
		} else if (nonStringName != null && !nonStringName.equals("null")) {
			name.append(nonStringName);
			success = true;
		}
		return success;
	} 
	
	public static boolean noArtifactsSelected(Collection<EObject> selection) {
		return selection.stream().noneMatch(o -> o instanceof ArtifactWrapper);
	}
}
