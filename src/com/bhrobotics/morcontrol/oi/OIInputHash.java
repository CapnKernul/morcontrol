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
			
			if (message instanceof BooleanMessage) {
				update((BooleanMessage) message);
			} else if (message instanceof NumberMessage) {
				update((NumberMessage) message);
			} else if (message instanceof StringMessage) {
				update((StringMessage) message);
			} else {
				throw new OIException("Unknown message type.");
			}
		}
		
		mutex.unlock();
	}
	
	private void update(BooleanMessage message) {
		String key = message.getKey();
		boolean value = message.toBoolean();
		hashtable.put(key, new Boolean(value));
	}
	
	private void update(NumberMessage message) {
		String key = message.getKey();
		double value = message.toDouble();
		hashtable.put(key, new Double(value));
	}
	
	private void update(StringMessage message) {
		String key = message.getKey();
		String value = message.toString();
		hashtable.put(key, value);
	}
}
