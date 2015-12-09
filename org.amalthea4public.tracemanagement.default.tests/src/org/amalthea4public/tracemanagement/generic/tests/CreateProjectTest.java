package org.amalthea4public.tracemanagement.generic.tests;

import static org.amalthea4public.tracemanagement.generic.helpers.EMFHelper.getIdentifier;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.amalthea4public.tracemanagement.generic.adapters.TracePersistenceAdapter;
import org.amalthea4public.tracemanagement.generic.helpers.ExtensionPointHelper;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.SimpleTraceModel;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.Trace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)

public class CreateProjectTest {

	
	private static SWTWorkbenchBot	bot;
 
	@BeforeClass
	public static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot();
		//bot.viewByTitle("Welcome").close();
		// slow down tests
		
	}
 
 
	@Test
	public void canCreateTrace() throws Exception {

		bot.viewByTitle("Package Explorer").show();
		bot.tree().getTreeItem("TestProject").expand();
		bot.tree().getTreeItem("TestProject").getNode("src").expand();
		bot.tree().getTreeItem("TestProject").getNode("src").getNode("Test.uml").select();
		
		bot.tree().contextMenu("Open With").menu("Sample Reflective Ecore Model Editor").click();
		SWTBotEditor editorBot = bot.editorByTitle("Test.uml");
		editorBot.show();
		editorBot.bot().tree().getTreeItem("platform:/resource/TestProject/src/Test.uml").expand();
		editorBot.bot().tree().getTreeItem("platform:/resource/TestProject/src/Test.uml").getNode("<Model> TestModel").expand();
		editorBot.bot().tree().getTreeItem("platform:/resource/TestProject/src/Test.uml").getNode("<Model> TestModel").select("<Class> BBB", "<Class> AAA");
		editorBot.bot().tree().contextMenu("Amalthea4Public Trace Managament").menu("Create Trace").click();
		bot.shell("Select the trace type you want to create").activate().bot().button("OK").click();

	
		TracePersistenceAdapter persistenceAdapter = ExtensionPointHelper.getTracePersistenceAdapter().get();
		
		ResourceSet resourceSet = new ResourceSetImpl();
		Optional<EObject> traceModel = persistenceAdapter.getTraceModel(resourceSet);
		
		assertTrue(traceModel.isPresent());
		
		SimpleTraceModel SimpleTraceModel = (SimpleTraceModel) traceModel.get();
		
		assertTrue(SimpleTraceModel.getTraces().size() == 1);
		
		Trace trace = (Trace) SimpleTraceModel.getTraces().get(0);
		assertTrue((!trace.getSource().equals(null) && !trace.getTarget().equals(null)));
		
		String sourceName = getIdentifier(trace.getSource());
		String targetName = getIdentifier(trace.getTarget());
		
		assertTrue(sourceName.equals("AAA : Class") && targetName.equals("BBB : Class"));
		
	}
 
 
	@AfterClass
	public static void sleep() {
		bot.sleep(2000);
		
		bot.editorByTitle("Test.uml").close();
		bot.viewByTitle("Package Explorer").show();
		bot.tree().getTreeItem("TestProject").collapse();
		
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject("__WorkspaceTraceModels").delete(true, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		

	}
 
}
