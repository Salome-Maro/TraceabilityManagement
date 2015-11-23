package org.amalthea4public.tracemanagement.generic.handlers;

import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.eclipse.emf.ecore.EObject;

/**
 * This interface defines functionality required to map chosen Objects in the
 * Eclipse workspace to EObjects of some kind, which can then be traced and
 * persisted in EMF models.
 * 
 * Implementations can use the provided concepts of an {@link ArtifactWrapper}
 * if this is suitable.
 * 
 * @author Anthony Anjorin, Salome Maro
 */
public interface ArtifactHandler {

	/**
	 * Can the handler map selection to an EObject as required?
	 * 
	 * @param selection
	 *            The object to be mapped to an EObject
	 * @return <code>true</code> if selection can be handled, <code>false</code>
	 *         otherwise.
	 */
	boolean canHandleSelection(Object selection);

	/**
	 * Map the object selection to an EObject.
	 * 
	 * @param selection
	 *            The object to be mapped
	 * @param existingWrappers
	 *            Container of all existing artifact wrappers. This can be used
	 *            to reuse mappings, effectively avoiding multiple artifact
	 *            wrappers for the same artifact if this is desired
	 * @return
	 */
	EObject getEObjectForSelection(Object selection, Optional<ArtifactWrapperContainer> existingWrappers);
}
