package org.amalthea4public.tracemanagement.generic.adapters;

import java.util.List;
import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

//FIXME [Salome] document me
public interface TracePersistenceAdapter {
		
	public Optional<EObject> getTraceModel(ResourceSet resourceSet);
	
	public Optional<ArtifactWrapperContainer> getArtifactWrappers(ResourceSet resourceSet);
	
	void saveTracesAndArtifactWrappers(EObject traceModel, List<EObject> selectionForTraceCreation, Optional<ArtifactWrapperContainer> artifactWrappers); 
}
