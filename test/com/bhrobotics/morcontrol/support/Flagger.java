package com.bhrobotics.morcontrol.support;

import java.util.HashSet;
import java.util.Set;

public class Flagger {
	private Set<String> flags = new HashSet<String>(); 
	
	public boolean isFlagged(String flag) {
		return flags.contains(flag);
	}

	public void flag(String flag) {
		flags.add(flag);
	}
}