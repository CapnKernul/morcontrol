package com.bhrobotics.morcontrol.oi;

public class NumberMessage implements Message {
	private String key;
	private double value;
		
	public NumberMessage(String key, double value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public double toDouble() {
		return value;
	}
}
