package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.util.UnsupportedOperationException;

import com.bhrobotics.morcontrol.util.Hash;

public class OIInputHash extends Hash {
	public void put(Object key, Object value) {
		throw new UnsupportedOperationException("Input hashes are read-only.");
	}
	
	public void update(Message[] messages) {
		mutex.lock();
		
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			String key = message.getKey();
			
			if (message instanceof BooleanMessage) {
				BooleanMessage booleanMessage = (BooleanMessage) message;
				boolean value = booleanMessage.toBoolean();
				hashtable.put(key, new Boolean(value));
			} else if (message instanceof NumberMessage) {
				NumberMessage numberMessage = (NumberMessage) message;
				double value = numberMessage.toDouble();
				hashtable.put(key, new Double(value));
			} else if (message instanceof StringMessage) {
				StringMessage stringMessage = (StringMessage) message;
				String value = stringMessage.toString();
				hashtable.put(key, value);
			} else {
				throw new OIException("Unknown message type.");
			}
		}
		
		mutex.unlock();
	}
}
