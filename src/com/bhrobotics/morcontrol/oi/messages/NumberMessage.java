package com.bhrobotics.morcontrol.oi.messages;

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

	public boolean equals(Object other) {
		if (other instanceof NumberMessage) {
			NumberMessage otherMessage = (NumberMessage) other;
			return key.equals(otherMessage.getKey()) && value == otherMessage.toDouble();
		} else {
			return false;
		}
	}
}
