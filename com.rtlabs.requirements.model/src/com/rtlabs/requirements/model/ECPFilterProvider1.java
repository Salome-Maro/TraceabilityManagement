package com.rtlabs.requirements.model;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecp.core.util.ECPFilterProvider;

public class ECPFilterProvider1 implements ECPFilterProvider {

	public ECPFilterProvider1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<String> getHiddenPackages() {
		Set<String> set = new HashSet<String>();
		set.add("xcore.lang");
		return set;
	}

}
