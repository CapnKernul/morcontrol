package com.bhrobotics.morcontrol.oi;

public class BooleanMessage implements Message {
	private String key;
	private boolean value;
	
	public BooleanMessage(String key, boolean value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public boolean toBoolean() {
		return value;
	}
}
