package org.amalthea4public.tracemanagement.persistence;

import java.util.List;
import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapperContainer;
import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactsFactory;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class TracePersistenceAdapter
		implements org.amalthea4public.tracemanagement.generic.adapters.TracePersistenceAdapter {

	private static final String DEFAULT_PROJECT_NAME = "__WorkspaceTraceModels";
	private static final String DEFAULT_TRACE_MODEL_NAME = "traceModel.xmi";
	private static final String DEFAULT_ARTIFACT_WRAPPER_MODEL_NAME = "artifactWrappers.xmi";

	@Override
	public Optional<EObject> getTraceModel(ResourceSet resourceSet) {
		if (projectExist(DEFAULT_PROJECT_NAME) && fileExists(DEFAULT_PROJECT_NAME + "/" + DEFAULT_TRACE_MODEL_NAME)) {
			try {
				URI uri = URI.createPlatformResourceURI(DEFAULT_PROJECT_NAME + "/" + DEFAULT_TRACE_MODEL_NAME, true);
				Resource resource = resourceSet.getResource(uri, true);
				resource.load(null);

				return Optional.of(resource.getContents().get(0));
			} catch (Exception e)

			{
				return Optional.empty();
			}

		}
		return Optional.empty();
	}

	private boolean fileExists(String path) {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).exists();
		
	}

	private boolean projectExist(String defaultProjectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(defaultProjectName).exists();
	}

	private IProject ensureProjectExists(String defaultProjectName) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(defaultProjectName);
		if (!project.exists()) {
			project.create(new NullProgressMonitor());
			project.open(new NullProgressMonitor());
		}

		return project;
	}

	@Override
	public void saveTracesAndArtifactWrappers(EObject traceModel, List<EObject> selectionForTraceCreation,
			Optional<ArtifactWrapperContainer> artifactWrappers) {
		try {
			URI uri = URI.createPlatformResourceURI(DEFAULT_PROJECT_NAME + "/" + DEFAULT_TRACE_MODEL_NAME, true);
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.createResource(uri);
			resource.getContents().add(traceModel);

			ArtifactWrapperContainer container = artifactWrappers
					.orElse(ArtifactsFactory.eINSTANCE.createArtifactWrapperContainer());

			selectionForTraceCreation.forEach(o -> {
				if (o instanceof ArtifactWrapper && o.eContainer() == null)
					container.getArtifacts().add((ArtifactWrapper) o);
			});

			uri = URI.createPlatformResourceURI(DEFAULT_PROJECT_NAME + "/" + DEFAULT_ARTIFACT_WRAPPER_MODEL_NAME, true);
			Resource resourceForArtifacts = resourceSet.createResource(uri);
			resourceForArtifacts.getContents().add(container);

			ensureProjectExists(DEFAULT_PROJECT_NAME);

			resourceForArtifacts.save(null);
			resource.save(null);
		} catch (Exception e) {
			System.err.println("Unable to save trace model!");
			e.printStackTrace();
		}
	}

	@Override
	public Optional<ArtifactWrapperContainer> getArtifactWrappers(ResourceSet resourceSet) {
		try {
			ensureProjectExists(DEFAULT_PROJECT_NAME);
			URI uri = URI.createPlatformResourceURI(DEFAULT_PROJECT_NAME + "/" + DEFAULT_ARTIFACT_WRAPPER_MODEL_NAME,
					true);
			Resource resource = resourceSet.getResource(uri, true);
			resource.load(null);

			return Optional.of((ArtifactWrapperContainer) resource.getContents().get(0));
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
