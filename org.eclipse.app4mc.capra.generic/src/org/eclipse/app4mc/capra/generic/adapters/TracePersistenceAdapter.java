package org.eclipse.app4mc.capra.generic.adapters;

import java.util.List;
import java.util.Optional;

import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapperContainer;
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
	public Optional<EObject> getTraceModel(ResourceSet resourceSet);

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
	public Optional<ArtifactWrapperContainer> getArtifactWrappers(ResourceSet resourceSet);

	/**
	 * Save the trace model and new artifact wrappers if necessary.
	 * Implementations are expected to: (i) save the trace model, (ii) check
	 * selectionForTraceCreation for artifact wrappers that are not already
	 * contained in artifactWrappers, (iii) add these new artifact wrappers to
	 * artifactWrappers before saving it as well
	 * 
	 * @param traceModel
	 *            The updated trace model to be saved
	 * @param selectionForTraceCreation
	 *            The objects used to update the trace model
	 * @param artifactWrappers
	 *            Existing artifact wrappers
	 */
	void saveTracesAndArtifactWrappers(EObject traceModel, List<EObject> selectionForTraceCreation,
			Optional<ArtifactWrapperContainer> artifactWrappers);
}
