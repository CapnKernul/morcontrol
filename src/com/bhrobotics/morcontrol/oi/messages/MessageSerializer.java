package com.bhrobotics.morcontrol.oi.messages;

import com.bhrobotics.morcontrol.oi.OIException;

public class MessageSerializer {
	private BooleanMessageSerializer booleanSerializer;
	private NumberMessageSerializer numberSerializer;
	private StringMessageSerializer stringSerializer;
	
	public MessageSerializer() {
		this(new BooleanMessageSerializer(), new NumberMessageSerializer(), new StringMessageSerializer());
	}
	
	public MessageSerializer(BooleanMessageSerializer booleanSerializer, NumberMessageSerializer numberSerializer, StringMessageSerializer stringSerializer) {
		this.booleanSerializer = booleanSerializer;
		this.numberSerializer = numberSerializer;
		this.stringSerializer = stringSerializer;
	}
	
	public byte[] serialize(Message message) {
		if (message instanceof BooleanMessage) {
			return booleanSerializer.serialize((BooleanMessage) message);
		} else if (message instanceof NumberMessage) {
			return numberSerializer.serialize((NumberMessage) message);
		} else if (message instanceof StringMessage) {
			return stringSerializer.serialize((StringMessage) message);
		} else {
			throw new OIException("Unknown message type.");
		}
	}

	public Message deserialize(byte[] bytes) {
		if (booleanSerializer.canDeserialize(bytes)) {
			return booleanSerializer.deserialize(bytes);
		} else if (numberSerializer.canDeserialize(bytes)) {
			return numberSerializer.deserialize(bytes);
		} else if (stringSerializer.canDeserialize(bytes)) {
			return stringSerializer.deserialize(bytes);
		} else {
			throw new OIException("Unknown message type.");
		}
	}
}
