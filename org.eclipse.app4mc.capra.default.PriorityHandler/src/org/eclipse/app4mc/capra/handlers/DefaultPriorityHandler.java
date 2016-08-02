/*******************************************************************************
 *  Copyright (c) {2016} Chalmers|Gothenburg University, rt-labs and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Chalmers|Gothenburg University and rt-labs - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.app4mc.capra.handlers;

import java.util.Collection;

import org.eclipse.app4mc.capra.core.handlers.ArtifactHandler;
import org.eclipse.app4mc.capra.core.handlers.PriorityHandler;
import org.eclipse.mylyn.builds.internal.core.BuildElement;
import org.eclipse.mylyn.builds.internal.core.TestElement;

public class DefaultPriorityHandler implements PriorityHandler{

	@Override
	public ArtifactHandler getSelectedHandler(Collection<ArtifactHandler> handlers, Object selectedElement) {
		if (selectedElement instanceof TestElement || selectedElement instanceof BuildElement)
		{
			return handlers.stream().filter(h -> h instanceof HudsonHandler).findAny().get();
			
		}
		return null;
	}

}
