package org.amalthea4public.generic.tracecreation.metamodel.trace.adapter;

import java.util.List;
import java.util.Optional;

import org.amalthea4public.generic.tracecreation.ArtifactWrapperContainer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

//FIXME [Salome] document me
public interface TracePersistenceAdapter {
		
	public Optional<EObject> getTraceModel(ResourceSet resourceSet);
	
	public Optional<ArtifactWrapperContainer> getArtifactWrappers(ResourceSet resourceSet);
	
	void saveTracesAndArtifactWrappers(EObject traceModel, List<EObject> selectionForTraceCreation, Optional<ArtifactWrapperContainer> artifactWrappers); 
}
