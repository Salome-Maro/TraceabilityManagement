package org.eclipse.app4mc.capra.core.handlers;

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
	 * @param artifactModel 
	 * @return
	 */
	EObject getEObjectForSelection(Object selection, EObject artifactModel);
}
