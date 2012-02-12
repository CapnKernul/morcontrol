package com.bhrobotics.morcontrol.oi;

public class BooleanMessageSerializer extends AbstractMessageSerializer {
	public String getType() {
		return "B";
	}
	
	public byte[] serialize(BooleanMessage message) {
		String key = message.getKey();
		String value = toString(message.toBoolean());
		return createMessage(key, value);
	}
	
	public BooleanMessage deserialize(byte[] bytes) {
		String[] parts = parse(bytes);
		return new BooleanMessage(parts[1], toBoolean(parts[2]));
	}
	
	private String toString(boolean value) {
		if (value) {
			return "T";
		} else {
			return "F";
		}
	}
	
	private boolean toBoolean(String value) {
		if (value.equals("F")) {
			return false;
		} else {
			return true;
		}
	}
}
