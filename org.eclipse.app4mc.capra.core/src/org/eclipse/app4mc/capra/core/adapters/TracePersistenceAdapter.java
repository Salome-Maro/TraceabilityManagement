package org.eclipse.app4mc.capra.core.adapters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * This interface defines all functionality used to decide how and where the
 * trace model and all artifact wrappers are persisted.
 * 
 * @author Anthony Anjorin, Salome Maro
 *
 */
public interface TracePersistenceAdapter {

	/**
	 * Load and return the trace model in the given resource set
	 * 
	 * @param resourceSet
	 *            Resource set to load the trace model in
	 * @return Root of loaded trace model, Optional can be empty to indicate
	 *         that loading failed or was not possible (there is no trace model
	 *         to load at the moment)
	 */
	public EObject getTraceModel(ResourceSet resourceSet);

	/**
	 * Return the trace model for the given object
	 * 
	 * @param object
	 *            EObject to return the model for
	 * @return Root of trace model, Optional can be empty to indicate
	 *         that there is no trace model for the object
	 */
	public EObject getTraceModel(EObject object);
	
	/**
	 * Load and return the container for all artifact wrappers in the given
	 * resource set
	 * 
	 * @param resourceSet
	 *            Resource set to load the container for artifact wrappers in
	 * @return Container for all artifact wrappers, Optional can be empty to
	 *         indicate that loading failed or was not possible (no container
	 *         exists at the moment)
	 */
	public EObject getArtifactWrappers(ResourceSet resourceSet);

	/**
	 * Return the trace model for the given object
	 * 
	 * @param object
	 *            EObject to return the model for
	 * @return Root of trace model, Optional can be empty to indicate
	 *         that there is no trace model for the object
	 */
	public EObject getArtifactWrappers(EObject object);

	/**
	 * Save the trace and artifact models.
	 * Implementations are expected to: (i) save the trace model, (ii) check
	 * selectionForTraceCreation for artifact wrappers that are not already
	 * contained in artifactWrappers, (iii) add these new artifact wrappers to
	 * artifactWrappers before saving it as well
	 * 
	 * @param traceModel
	 *            The updated trace model to be saved
	 * @param artifactModel
	 *            The updated artifacts to be saved
	 */
	void saveTracesAndArtifacts(EObject traceModel, EObject artifactModel);
}
