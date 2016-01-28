package org.eclipse.app4mc.capra.generic.adapters;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * An minimal abstraction of a traceability link used in
 * {@link TraceMetamodelAdapter}, to retain independence of a concrete trace
 * metamodel.
 * 
 * @author Anthony Anjorin, Salome Maro
 */
public class Connection {
	private EObject origin;
	private List<EObject> targets;
	private EObject tlink;

	public Connection(EObject origin, List<EObject> targets, EObject tlink) {
		this.origin = origin;
		this.targets = targets;
		this.tlink = tlink;
	}

	public EObject getOrigin() {
		return origin;
	}

	public List<EObject> getTargets() {
		return targets;
	}

	public EObject getTlink() {
		return tlink;
	}
}
