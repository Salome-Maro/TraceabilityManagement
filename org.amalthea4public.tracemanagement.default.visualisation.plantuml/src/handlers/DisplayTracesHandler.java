package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class DisplayTracesHandler extends AbstractHandler {

	public static final String PLANT_UML_VIEW_ID = "net.sourceforge.plantuml.eclipse.views.PlantUmlView";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PLANT_UML_VIEW_ID);		
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}