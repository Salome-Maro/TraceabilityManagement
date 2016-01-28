package org.eclipse.app4mc.capra.testsuite;

import static org.eclipse.app4mc.capra.testsuite.TestHelper.createEClassInEPackage;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.createEcoreModel;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.createSimpleProject;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.createTraceForCurrentSelectionOfType;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.getProject;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.load;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.projectExists;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.save;
import static org.eclipse.app4mc.capra.testsuite.TestHelper.thereIsATraceBetween;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.TracemetamodelPackage;
import org.eclipse.app4mc.capra.generic.views.SelectionView;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

public class CreateTLinkBetweenModelElements {

	@Test
	public void testSimpleLinkCreation() throws CoreException, IOException {
		// Create a project
		createSimpleProject("TestProject");
		assertTrue(projectExists("TestProject"));

		// Create two models and persist them
		IProject testProject = getProject("TestProject");
		EPackage a = TestHelper.createEcoreModel("modelA");
		createEClassInEPackage(a, "A");
		save(testProject, a);

		EPackage b = createEcoreModel("modelB");
		createEClassInEPackage(b, "B");
		save(testProject, b);

		// Load them, choose two elements
		ResourceSet rs = new ResourceSetImpl();
		
		EPackage _a = load(testProject, "modelA.ecore", rs);
		assertEquals(_a.getName(), "modelA");
		EClass _A = (EClass) _a.getEClassifier("A");

		EPackage _b = load(testProject, "modelB.ecore", rs);
		assertEquals(_b.getName(), "modelB");
		EClass _B = (EClass) _b.getEClassifier("B");

		// Add them to the selection view
		assertTrue(SelectionView.getOpenedView().getSelection().isEmpty());
		SelectionView.getOpenedView().dropToSelection(_A);
		SelectionView.getOpenedView().dropToSelection(_B);
		assertFalse(SelectionView.getOpenedView().getSelection().isEmpty());

		// Create a trace via the selection view
		assertFalse(thereIsATraceBetween(_A, _B));
		createTraceForCurrentSelectionOfType(TracemetamodelPackage.eINSTANCE.getEObjectToEObject());

		// Check if trace has been created
		assertTrue(thereIsATraceBetween(_A, _B));
	}

}
