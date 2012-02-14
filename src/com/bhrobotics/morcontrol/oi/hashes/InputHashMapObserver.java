package com.bhrobotics.morcontrol.oi.hashes;

public interface InputHashMapObserver {
	public void keyUpdated(String key, boolean value);
	public void keyUpdated(String key, double value);
	public void keyUpdated(String key, String value);
	public void hashCleared();
}
