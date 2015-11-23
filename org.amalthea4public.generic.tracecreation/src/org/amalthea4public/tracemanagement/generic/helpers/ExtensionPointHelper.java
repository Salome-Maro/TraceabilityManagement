package org.amalthea4public.tracemanagement.generic.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.amalthea4public.tracemanagement.generic.adapters.TraceMetamodelAdapter;
import org.amalthea4public.tracemanagement.generic.adapters.TracePersistenceAdapter;
import org.amalthea4public.tracemanagement.generic.handlers.ArtifactHandler;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class ExtensionPointHelper {

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

}
