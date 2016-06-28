package org.eclipse.app4mc.capra.notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.app4mc.capra.GenericArtifactMetamodel.ArtifactWrapper;
import org.eclipse.app4mc.capra.GenericArtifactMetamodel.ArtifactWrapperContainer;
import org.eclipse.app4mc.capra.GenericTraceMetamodel.GenericTraceMetamodelFactory;
import org.eclipse.app4mc.capra.GenericTraceMetamodel.GenericTraceModel;
import org.eclipse.app4mc.capra.GenericTraceMetamodel.RelatedTo;
import org.eclipse.app4mc.capra.core.adapters.Connection;
import org.eclipse.app4mc.capra.core.adapters.TraceMetamodelAdapter;
import org.eclipse.app4mc.capra.core.adapters.TracePersistenceAdapter;
import org.eclipse.app4mc.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IMarkerResolution;

/**
 * 
 * @author Michael Warne
 *
 */

public class DeleteQuickFix implements IMarkerResolution {

	private URI artifactModelURI;
	private URI traceModelURI;
	private TracePersistenceAdapter tracePersistenceAdapter;
	private ResourceSet resourceSet;
	private EObject awc;
	private String label;
	private Resource resourceForArtifacts;
	private Resource resourceForTraces;
	private ArtifactWrapperContainer container;
	private String fullPath;
	private EObject traceModel;
	private TraceMetamodelAdapter traceMetamodelAdapter;
	private List<RelatedTo> toDelete = new ArrayList<>();
	
	
	DeleteQuickFix(String label) {
		this.label = label;
	}
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void run(IMarker marker) {
		try {
			fullPath = (String) marker.getAttribute("DeltaFullPath");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resourceSet = new ResourceSetImpl();
		tracePersistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		traceMetamodelAdapter = ExtensionPointHelper.getTraceMetamodelAdapter().get();
		traceModel = tracePersistenceAdapter.getTraceModel(resourceSet);
		traceModelURI = EcoreUtil.getURI(traceModel);
		GenericTraceModel newTraceModel = GenericTraceMetamodelFactory.eINSTANCE.createGenericTraceModel();
		resourceForTraces = resourceSet.createResource(traceModelURI);
		
		GenericTraceModel simpleTM = (GenericTraceModel) traceModel;
		List<RelatedTo> traces = simpleTM.getTraces();
		
		awc = tracePersistenceAdapter.getArtifactWrappers(resourceSet);
		artifactModelURI = EcoreUtil.getURI(awc);
		
		resourceForArtifacts = resourceSet.createResource(artifactModelURI);
		EList<ArtifactWrapper> list = ((ArtifactWrapperContainer) awc).getArtifacts();
		container = (ArtifactWrapperContainer) awc;
		int counter = -1;
		for (ArtifactWrapper aw : list) {
			counter ++;
			String s = aw.getUri().replace("<{", "/");
			s = s.substring(1);
			s = s.replace("<", "/");
			s = s.replace("{", "/");
			s = "/" + s;

			if(s.equals(fullPath)){	
			List<Connection> connections = traceMetamodelAdapter.getConnectedElements(aw, traceModel);
				connections.forEach(c -> {
					for(RelatedTo t: traces) {
						if(c.getTlink().equals(t)) {
							toDelete.add(t);
						}
					}
				});
				traces.removeAll(toDelete);
				newTraceModel.getTraces().addAll(traces);
				resourceForTraces.getContents().add(newTraceModel);
				
				ArtifactWrapper toRemove = container.getArtifacts().get(counter);
				EcoreUtil.delete(toRemove);
				resourceForArtifacts.getContents().add(container);
				try {
					resourceForTraces.save(null);
					resourceForArtifacts.save(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				break;
			}												
		}					

		try {
			marker.delete();
		} catch (CoreException e) {
			
			e.printStackTrace();
		}
	}
}

