package org.eclipse.app4mc.capra.generic.helpers;

import java.util.Optional;

import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapper;
import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapperContainer;
import org.eclipse.app4mc.capra.generic.handlers.ArtifactHandler;
import org.eclipse.emf.ecore.EObject;

public class ArtifactHelper {
	/**
	 * Checks if their exists a wrapper in existingWrappers that is equal to the
	 * given wrapper, based on a comparison of their {@link ArtifactHandler} and
	 * URIs.
	 * 
	 * @param wrapper
	 *            Wrapper to check for
	 * @param existingWrappers
	 *            Container of all existing wrappers
	 * @return If there already existing an equivalent wrapper in
	 *         existingWrappers return it, else return the given wrapper itself
	 */
	public static EObject existingWrapperWithURIorNew(ArtifactWrapper wrapper,
			Optional<ArtifactWrapperContainer> existingWrappers) {
		if (existingWrappers.isPresent()) {
			for (ArtifactWrapper existing : existingWrappers.get().getArtifacts()) {
				if (wrapper.getArtifactHandler().equals(existing.getArtifactHandler())
						&& existing.getUri().equals(wrapper.getUri()))
					return existing;
			}
		}

		return wrapper;
	}
}
