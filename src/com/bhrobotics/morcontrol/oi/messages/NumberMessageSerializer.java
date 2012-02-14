package com.bhrobotics.morcontrol.oi.messages;

public class NumberMessageSerializer extends AbstractMessageSerializer {
	public String getType() {
		return "N";
	}
	
	public byte[] serialize(NumberMessage message) {
		String key = message.getKey();
		String value = String.valueOf(message.toDouble());
		return createMessage(key, value);
	}
	
	public NumberMessage deserialize(byte[] bytes) {
		String[] parts = parse(bytes);
		return new NumberMessage(parts[1], Double.parseDouble(parts[2]));
	}
}
