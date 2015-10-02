package org.amalthea4public.metamodel.trace;

import java.io.IOException;
import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class TracePersistenceAdapter
		implements org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TracePersistenceAdapter {

	private static final String DEFAULT_PROJECT_NAME = "__WorkspaceTraceModels";
	private static final String DEFAULT_TRACE_MODEL_NAME = "defaultTraceModel.xmi";

	@Override
	public Optional<EObject> getTraceModel() {
		try {
			ensureProjectExists(DEFAULT_PROJECT_NAME);
			URI uri = URI.createPlatformResourceURI(DEFAULT_PROJECT_NAME + "/" + DEFAULT_TRACE_MODEL_NAME, true);
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.getResource(uri, true);
			resource.load(null);

			return Optional.ofNullable(resource.getContents().get(0));
		} catch (Exception e) {
			return Optional.empty();
		}
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
	public void saveTraceModel(EObject root) {
		try {
			URI uri = URI.createPlatformResourceURI(DEFAULT_PROJECT_NAME + "/" + DEFAULT_TRACE_MODEL_NAME, true);
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.createResource(uri);
			resource.getContents().add(root);
			resource.save(null);
		} catch (IOException e) {
			System.err.println("Unable to save!");
			e.printStackTrace();
		} 
	}
}
