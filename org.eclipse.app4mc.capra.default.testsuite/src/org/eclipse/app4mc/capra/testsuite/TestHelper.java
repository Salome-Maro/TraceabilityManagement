
package org.eclipse.app4mc.capra.testsuite;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.app4mc.capra.generic.adapters.Connection;
import org.eclipse.app4mc.capra.generic.adapters.TraceMetamodelAdapter;
import org.eclipse.app4mc.capra.generic.adapters.TracePersistenceAdapter;
import org.eclipse.app4mc.capra.generic.artifacts.ArtifactWrapper;
import org.eclipse.app4mc.capra.generic.handlers.TraceCreationHandler;
import org.eclipse.app4mc.capra.generic.helpers.ExtensionPointHelper;
import org.eclipse.app4mc.capra.generic.views.SelectionView;
import org.eclipse.app4mc.capra.handlers.CDTHandler;
import org.eclipse.app4mc.capra.handlers.DisplayTracesHandler;
import org.eclipse.app4mc.capra.handlers.JavaElementHandler;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.ArtifactToArtifact;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.SimpleTraceModel;
import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class TestHelper {
	public static void createSimpleProject(String projectName) throws CoreException {
		IProject project = getProject(projectName);

		IProgressMonitor progressMonitor = new NullProgressMonitor();
		project.create(progressMonitor);
		project.open(progressMonitor);
	}

	public static IType createJavaProjectWithASingleJavaClass(String projectName) throws CoreException {
		IProject project = getProject(projectName);

		// Create project
		IProgressMonitor progressMonitor = new NullProgressMonitor();
		project.create(progressMonitor);
		project.open(progressMonitor);

		// Add Java nature
		IProjectDescription description = project.getDescription();
		description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		project.setDescription(description, null);

		// Create as Java project and set up build path etc.
		IJavaProject javaProject = JavaCore.create(project);
		IFolder binFolder = project.getFolder("bin");
		binFolder.create(false, true, null);
		javaProject.setOutputLocation(binFolder.getFullPath(), null);
		List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
		LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
		for (LibraryLocation element : locations)
			entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));

		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);

		// Create a src file
		IFolder sourceFolder = project.getFolder("src");
		sourceFolder.create(false, true, null);
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourceFolder);
		IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
		IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
		newEntries[oldEntries.length] = JavaCore.newSourceEntry(root.getPath());
		javaProject.setRawClasspath(newEntries, null);

		IPackageFragment pack = javaProject.getPackageFragmentRoot(sourceFolder)
				.createPackageFragment("org.amalthea.test", false, null);

		StringBuffer buffer = new StringBuffer();
		buffer.append("package " + pack.getElementName() + ";\n");
		buffer.append("\n");
		buffer.append("public class TestClass {}");

		ICompilationUnit icu = pack.createCompilationUnit("TestClass.java", buffer.toString(), false, null);
		return icu.getType("TestClass");
	}

	public static void clearWorkspace() throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (IProject p : root.getProjects())
			p.delete(true, new NullProgressMonitor());
	}

	public static boolean projectExists(String projectName) {
		return getProject(projectName).exists();
	}

	public static IProject getProject(String projectName) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		return root.getProject(projectName);
	}

	public static EPackage createEcoreModel(String name) {
		EPackage p = EcoreFactory.eINSTANCE.createEPackage();
		p.setName(name);
		return p;
	}

	public static void createEClassInEPackage(EPackage p, String name) {
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName(name);
		p.getEClassifiers().add(c);
	}

	public static void save(IProject project, EPackage pack) throws IOException {
		ResourceSet rs = new ResourceSetImpl();
		URI path = URI.createFileURI(project.getLocation().toString() + "/" + pack.getName() + ".ecore");
		Resource r = rs.createResource(path);
		r.getContents().add(pack);
		r.save(null);
	}

	public static EPackage load(IProject project, String p, ResourceSet rs) throws IOException {
		URI path = URI.createFileURI(project.getLocation().toString() + "/" + p);
		return (EPackage) rs.getResource(path, true).getContents().get(0);
	}

	public static void createTraceForCurrentSelectionOfType(EClass traceType) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		TraceCreationHandler handler = new TraceCreationHandler();
		handler.createTrace(window, (traceTypes, selection) -> {
			if (traceTypes.contains(traceType))
				return Optional.of(traceType);
			else
				return Optional.empty();
		});
	}

	public static boolean thereIsATraceBetween(EObject a, EObject b) {
		TracePersistenceAdapter persistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		TraceMetamodelAdapter traceAdapter = ExtensionPointHelper.getTraceMetamodelAdapter().get();
		return traceAdapter.isThereATraceBetween(a, b,
				persistenceAdapter.getTraceModel(a.eResource().getResourceSet()));
	}

	public static boolean thereIsATraceBetween(EObject a, IType b) {
		TracePersistenceAdapter persistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		TraceMetamodelAdapter traceAdapter = ExtensionPointHelper.getTraceMetamodelAdapter().get();

		List<Connection> connected = traceAdapter.getConnectedElements(a,
				persistenceAdapter.getTraceModel(a.eResource().getResourceSet()));

		return connected.stream().filter(o -> {
			ArtifactWrapper w = (ArtifactWrapper) o.getTargets().get(0);
			if (w.getArtifactHandler().equals(JavaElementHandler.class.getName())) {
				return w.getUri().equals(b.getHandleIdentifier());
			}
			return false;
		}).findAny().isPresent();

	}

	public static boolean thereIsATraceBetween(IResource r1, IResource r2) {
		TracePersistenceAdapter persistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		return persistenceAdapter
			.getTraceModel(new ResourceSetImpl())
			.map(tm -> {
				SimpleTraceModel stm = (SimpleTraceModel) tm;
				return stm.getTraces().get(0);
			})
			.map(trace -> {
				ArtifactToArtifact a2a = (ArtifactToArtifact) trace;
				return a2a.getSource().getUri().equals(r1.getFullPath().toString()) && 
					   a2a.getTarget().getUri().equals(r2.getFullPath().toString());
			})
			.orElse(false);
	}

	public static boolean thereIsATraceBetween(EObject a, ICProject b) {
		TracePersistenceAdapter persistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		TraceMetamodelAdapter traceAdapter = ExtensionPointHelper.getTraceMetamodelAdapter().get();

		List<Connection> connected = traceAdapter.getConnectedElements(a,
				persistenceAdapter.getTraceModel(a.eResource().getResourceSet()));

		return connected.stream().filter(o -> {
			ArtifactWrapper w = (ArtifactWrapper) o.getTargets().get(0);
			if (w.getArtifactHandler().equals(CDTHandler.class.getName())) {
				return w.getUri().equals(b.getHandleIdentifier());
			}
			return false;
		}).findAny().isPresent();

	}

	public static ICProject createCDTProject(String projectName) throws OperationCanceledException, CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(projectName);
		IProjectDescription description = workspace.newProjectDescription(projectName);
		project = CCorePlugin.getDefault().createCDTProject(description, project, new NullProgressMonitor());
		project.open(null);

		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_REFRESH, null);
		} catch (Exception e) {
			// Ignore
		}

		ICProject tu = CoreModel.getDefault().create(project);

		return tu;
	}

	public static IResource createEmptyFileInProject(String fileName, String projectName) throws CoreException {
		IProject project = getProject(projectName);
		IFile f = project.getFile(fileName);
		f.create(new ByteArrayInputStream("hello world!".getBytes()), true, new NullProgressMonitor());

		return f;
	}
	
	public static void resetSelectionView(){
		SelectionView.getOpenedView().clearSelection();
		DisplayTracesHandler.setTraceViewTransitive(true);
		assertTrue(SelectionView.getOpenedView().getSelection().isEmpty());
	}
}
