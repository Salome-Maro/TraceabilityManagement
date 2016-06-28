package org.eclipse.app4mc.capra.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * Toggles between showing transitive and direct links
 * 
 * @author Anthony Anjorin, Salome Maro
 */
public class DisplayTracesHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (isTraceViewTransitive())
			setTraceViewTransitive(false);
		else
			setTraceViewTransitive(true);

		return null;
	}

	public static boolean isTraceViewTransitive() {
		Preferences transitivity = getPreference();

		return transitivity.get("option", "direct").equals("transitive");
	}

	private static Preferences getPreference() {
		Preferences preferences = InstanceScope.INSTANCE.getNode("org.amalthea4public.tracemanagement.generic.preferences");
		Preferences transitivity = preferences.node("transitivity");
		return transitivity;
	}

	public static void setTraceViewTransitive(boolean value) {
		Preferences transitivity = getPreference();

		transitivity.put("option", value ? "transitive" : "direct");

		try {
			// forces the application to save the preferences
			transitivity.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
}