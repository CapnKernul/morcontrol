package com.bhrobotics.morcontrol.oi.messages;

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

	public boolean equals(Object other) {
		if (other instanceof BooleanMessage) {
			BooleanMessage otherMessage = (BooleanMessage) other;
			return key.equals(otherMessage.getKey()) && value == otherMessage.toBoolean();
		} else {
			return false;
		}
	}
}
