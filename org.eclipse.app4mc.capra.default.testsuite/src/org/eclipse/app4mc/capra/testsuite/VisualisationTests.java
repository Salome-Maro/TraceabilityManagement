package org.eclipse.app4mc.capra.testsuite;

import static org.eclipse.app4mc.capra.testsuite.TestHelper.clearWorkspace;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.createEClassInEPackage;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.createEcoreModel;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.createSimpleProject;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.createTraceForCurrentSelectionOfType;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.getProject;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.load;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.projectExists;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.resetSelectionView;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.save;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.thereIsATraceBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.app4mc.capra.generic.adapters.TracePersistenceAdapter;
import org.eclipse.app4mc.capra.generic.helpers.ExtensionPointHelper;
import org.eclipse.app4mc.capra.generic.views.SelectionView;
import org.eclipse.app4mc.capra.handlers.DiagramTextProviderHandler;
import org.eclipse.app4mc.capra.handlers.DisplayTracesHandler;
import org.eclipse.app4mc.capra.simpletrace.tracemetamodel.TracemetamodelPackage;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;

public class VisualisationTests {
	@Before
	public void init() throws CoreException {
		clearWorkspace();
		resetSelectionView();
	}

	@Test
	public void testProducedPlantUMLStringMatrix1() throws CoreException, IOException {
		List<Object> selectedModels = createSimpleTestProjectForVisWithTwoTraces();

		DiagramTextProviderHandler handler = new DiagramTextProviderHandler();
		String created = handler.getDiagramText(selectedModels);
		String expected = VisualisationResultHelper.expectedMatrix1;

		assertEquals(expected, created);
	}

	@Test
	public void testProducedPlantUMLStringMatrix2() throws CoreException, IOException {
		List<Object> selectedModels = createSimpleTestProjectForVisWithTwoTraces();

		DiagramTextProviderHandler handler = new DiagramTextProviderHandler();
		String created = handler.getDiagramText(selectedModels.subList(0, 2));
		String expected = VisualisationResultHelper.expectedMatrix2;

		assertEquals(expected, created);
	}
	
	@Test
	public void testProducedPlantUMLStringTransitiveDiagram() throws CoreException, IOException {
		List<Object> selectedModels = createSimpleTestProjectForVisWithTwoTraces();

		DiagramTextProviderHandler handler = new DiagramTextProviderHandler();
		String created = handler.getDiagramText(selectedModels.subList(0, 1));
		String expected = VisualisationResultHelper.transitiveDiagram;

		assertEquals(expected, created);
	}
		
	@Test
	public void testProducedPlantUMLStringDirectDiagram() throws CoreException, IOException {
		List<Object> selectedModels = createSimpleTestProjectForVisWithTwoTraces();

		DisplayTracesHandler.setTraceViewTransitive(false);
		
		DiagramTextProviderHandler handler = new DiagramTextProviderHandler();
		String created = handler.getDiagramText(selectedModels.subList(0, 1));
		String expected = VisualisationResultHelper.directDiagram;

		assertEquals(expected, created);
	}
	
	private List<Object> createSimpleTestProjectForVisWithTwoTraces() throws CoreException, IOException {
		// Create a project
		createSimpleProject("TestProject");
		assertTrue(projectExists("TestProject"));

		// Create two models and persist them
		IProject testProject = getProject("TestProject");
		{
			EPackage a = TestHelper.createEcoreModel("modelA");
			createEClassInEPackage(a, "A1");
			createEClassInEPackage(a, "A2");
			save(testProject, a);

			EPackage b = createEcoreModel("modelB");
			createEClassInEPackage(b, "B");
			save(testProject, b);
		}

		// Load them, choose elements
		ResourceSet rs = new ResourceSetImpl();

		EPackage _a = load(testProject, "modelA.ecore", rs);
		assertEquals(_a.getName(), "modelA");
		EClass _A1 = (EClass) _a.getEClassifier("A1");
		EClass _A2 = (EClass) _a.getEClassifier("A2");

		EPackage _b = load(testProject, "modelB.ecore", rs);
		assertEquals(_b.getName(), "modelB");
		EClass _B = (EClass) _b.getEClassifier("B");

		// Add to the selection view
		assertTrue(SelectionView.getOpenedView().getSelection().isEmpty());
		SelectionView.getOpenedView().dropToSelection(_A1);
		SelectionView.getOpenedView().dropToSelection(_A2);
		assertFalse(SelectionView.getOpenedView().getSelection().isEmpty());

		// Create a trace via the selection view
		assertFalse(thereIsATraceBetween(_A1, _A2));
		createTraceForCurrentSelectionOfType(TracemetamodelPackage.eINSTANCE.getEObjectToEObject());
		SelectionView.getOpenedView().clearSelection();

		// Check if trace has been created
		removeTraceModel(rs);
		assertTrue(thereIsATraceBetween(_A1, _A2));

		// Add next elements to the selection view
		assertTrue(SelectionView.getOpenedView().getSelection().isEmpty());
		SelectionView.getOpenedView().dropToSelection(_A2);
		SelectionView.getOpenedView().dropToSelection(_B);
		assertFalse(SelectionView.getOpenedView().getSelection().isEmpty());

		assertFalse(thereIsATraceBetween(_A2, _B));

		// Create a trace via the selection view
		createTraceForCurrentSelectionOfType(TracemetamodelPackage.eINSTANCE.getEObjectToEObject());

		// Check if second trace was created
		removeTraceModel(rs);
		assertTrue(thereIsATraceBetween(_A2, _B));

		return Arrays.asList(new Object[] { _A1, _A2, _B });
	}

	private void removeTraceModel(ResourceSet rs) {
		TracePersistenceAdapter persistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		Optional<EObject> tm = persistenceAdapter.getTraceModel(rs);
		tm.ifPresent(traces -> rs.getResources().remove(traces.eResource()));
	}
}
