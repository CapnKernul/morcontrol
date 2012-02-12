package com.bhrobotics.morcontrol.oi;

public class StringMessage implements Message {
	private String key;
	private String value;
	
	public StringMessage(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String toString() {
		return value;
	}
}
