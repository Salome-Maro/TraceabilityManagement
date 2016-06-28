package org.eclipse.app4mc.capra.generic.perspective;

import org.eclipse.app4mc.capra.generic.views.SelectionView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class CapraPerspective implements IPerspectiveFactory {

	private IPageLayout factory;

	public void createInitialLayout(IPageLayout factory) {
		this.factory = factory;
		addViews();
	}

	private void addViews() {
		IFolderLayout bottom = factory.createFolder("bottomRight", IPageLayout.BOTTOM, 0.6f, factory.getEditorArea());
		bottom.addView(SelectionView.ID);

		IFolderLayout topLeft = factory.createFolder("topLeft", IPageLayout.LEFT, 0.25f, factory.getEditorArea());
		topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
		
		IFolderLayout topRight = factory.createFolder("topRight", IPageLayout.RIGHT, 0.75f, factory.getEditorArea());
		topRight.addView(IPageLayout.ID_OUTLINE);
	}
}
