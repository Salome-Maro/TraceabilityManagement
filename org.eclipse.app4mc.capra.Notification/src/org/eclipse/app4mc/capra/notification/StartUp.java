package org.eclipse.app4mc.capra.notification;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IStartup;

public class StartUp implements IStartup{

	@Override
	public void earlyStartup() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(new ResourceListener());  
	}
}

