package com.bhrobotics.morcontrol.oi.messages;

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
	
	public boolean equals(Object other) {
		if (other instanceof StringMessage) {
			StringMessage otherMessage = (StringMessage) other;
			return key.equals(otherMessage.getKey()) && value.equals(otherMessage.toString());
		} else {
			return false;
		}
	}
}
