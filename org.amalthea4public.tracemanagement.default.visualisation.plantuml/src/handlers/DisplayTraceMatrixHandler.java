package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class DisplayTraceMatrixHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Preferences preferences = InstanceScope.INSTANCE
				.getNode("org.amalthea4public.tracemanagement.generic.preferences");
		Preferences transitivity = preferences.node("transitivity");
		
		if(transitivity.get("option", "direct").equals("direct")) {
			transitivity.put("option", "transitive");
		} else {
			transitivity.put("option", "direct");
		}
		try {
			  // forces the application to save the preferences
			  preferences.flush();
			  } catch (BackingStoreException e) {
			    e.printStackTrace();
			  }
		
		return null;
	}
	
}