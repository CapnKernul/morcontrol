package com.bhrobotics.morcontrol.oi;

public class StringMessageSerializer extends AbstractMessageSerializer {
	public String getType() {
		return "S";
	}

	public byte[] serialize(StringMessage message) {
		return createMessage(message.getKey(), message.toString());
	}
	
	public StringMessage deserialize(byte[] bytes) {
		String[] parts = parse(bytes);
		return new StringMessage(parts[1], parts[2]);
	}
}
