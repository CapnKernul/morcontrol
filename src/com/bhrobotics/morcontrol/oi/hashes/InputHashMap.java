package com.bhrobotics.morcontrol.oi.hashes;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.oi.OIException;
import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;

public class InputHashMap extends AbstractOIHashMap {
	private Vector observers = new Vector();
	
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
	
	public void clear() {
		mutex.lock();
		hash.clear();
		hashCleared();
		mutex.unlock();
	}
	
	public void registerObserver(InputHashMapObserver observer) {
		mutex.lock();
		observers.addElement(observer);
		mutex.unlock();
	}

	public void unregisterObserver(InputHashMapObserver observer) {
		mutex.lock();
		observers.removeElement(observer);
		mutex.unlock();
	}
	
	private void update(BooleanMessage message) {
		String key = message.getKey();
		boolean value = message.toBoolean();
		hash.put(key, value);
		keyUpdated(key, value);
	}
	
	private void update(NumberMessage message) {
		String key = message.getKey();
		double value = message.toDouble();
		hash.put(key, value);
		keyUpdated(key, value);
	}
	
	private void update(StringMessage message) {
		String key = message.getKey();
		String value = message.toString();
		hash.put(key, value);
		keyUpdated(key, value);
	}
	
	private void eachObserver(EachObserver each) {
		Enumeration enumeration = observers.elements();
		while (enumeration.hasMoreElements()) {
			InputHashMapObserver observer = (InputHashMapObserver) enumeration.nextElement();
			each.execute(observer);
		}
	}
	
	private interface EachObserver {
		public void execute(InputHashMapObserver observer);
	}
	
	private void keyUpdated(final String key, final boolean value) {
		eachObserver(new EachObserver() {
			public void execute(InputHashMapObserver observer) {
				observer.keyUpdated(key, value);
			}
		});
	}

	private void keyUpdated(final String key, final double value) {
		eachObserver(new EachObserver() {
			public void execute(InputHashMapObserver observer) {
				observer.keyUpdated(key, value);
			}
		});
	}

	private void keyUpdated(final String key, final String value) {
		eachObserver(new EachObserver() {
			public void execute(InputHashMapObserver observer) {
				observer.keyUpdated(key, value);
			}
		});
	}
	
	private void hashCleared() {
		eachObserver(new EachObserver() {
			public void execute(InputHashMapObserver observer) {
				observer.hashCleared();
			}
		});
	}
}
