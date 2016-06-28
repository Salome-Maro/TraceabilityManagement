package org.eclipse.app4mc.capra.notification;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

/**
 * 
 * @author Michael Warne
 *
 */

public class MarkerResolutionGenerator implements IMarkerResolutionGenerator {

	@Override
	public IMarkerResolution[] getResolutions(IMarker marker) {
		try {
			String problem = (String) marker.getAttribute("IssueType");
			if(problem.equals("Rename")){
				return new IMarkerResolution[] {
						//new DummyNameOnlyQuickFix("Update the name only in the wrapper model."),
						new RenameOrMoveQuickFix("Update the EMF presentation."),
						//new DummyURIOnlyQuickFix("Update URI in the wrapper model."),
				};}
			if(problem.equals("Move")){
				return new IMarkerResolution[] {
						new RenameOrMoveQuickFix("Update the EMF presentation."),

				};}
			if(problem.equals("Delete")){
				return new IMarkerResolution[] {
						new DeleteQuickFix("Delete the affected trace link."),

				};}
			else return null;

		}
		catch (CoreException e) {
			return new IMarkerResolution[0];
		}
	}

}
