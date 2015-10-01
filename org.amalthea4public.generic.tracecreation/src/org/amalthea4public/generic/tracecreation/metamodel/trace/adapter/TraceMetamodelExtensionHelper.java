package org.amalthea4public.generic.tracecreation.metamodel.trace.adapter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class TraceMetamodelExtensionHelper {

	private static final String ID = "org.amalthea4public.configuration.metamodel.trace";

	private static final String CONFIG = "adapter";

	public static TraceMetamodelAdapter getExtension() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(ID);
		try {
			for (IConfigurationElement e : config) {
				final Object o = e.createExecutableExtension(CONFIG);
				if (o instanceof TraceMetamodelAdapter) {
					return (TraceMetamodelAdapter) o;
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
