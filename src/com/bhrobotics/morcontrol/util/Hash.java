package com.bhrobotics.morcontrol.util;

import java.util.Enumeration;
import java.util.Hashtable;


public class Hash {
	protected ReentrantMutex mutex = new ReentrantMutex();
	protected Hashtable hashtable;
	
	public Hash() {
		hashtable = new Hashtable(); 
	}
	
	public Hash(Hashtable hashtable) {
		this.hashtable = hashtable;
	}
	
	public Hashtable toHashtable() {
		return hashtable;
	}
	
	public boolean isEmpty() {
		mutex.lock();
		boolean result = hashtable.isEmpty();
		mutex.unlock();
		return result;
	}
	
	public boolean contains(Object key) {
		mutex.lock();
		boolean result = hashtable.contains(key);
		mutex.unlock();
		return result;
	}
	
	public boolean containsKey(Object key) {
		mutex.lock();
		boolean result = hashtable.containsKey(key);
		mutex.unlock();
		return result;
	}
	
	public Enumeration keys() {
		mutex.lock();
		Enumeration result = hashtable.keys();
		mutex.unlock();
		return result;
	}
	
	public Enumeration elements() {
		mutex.lock();
		Enumeration result = hashtable.elements();
		mutex.unlock();
		return result;
	}
	
	public Object get(Object key) {
		mutex.lock();
		Object result = extract(key);
		mutex.unlock();
		return result;
	}
	
	private Object extract(Object key) {
		return hashtable.get(key);
	}
	
	public String getAsString(Object key) {
		return (String) extract(key);
	}
	
	public char getAsChar(Object key) {
		mutex.lock();
		char result = extractAsChar(key);
		mutex.unlock();
		return result;
	}

	private char extractAsChar(Object key) {
		return ((Character) extract(key)).charValue();
	}

	public byte getAsByte(Object key) {
		mutex.lock();
		byte result = extractAsByte(key);
		mutex.unlock();
		return result;
	}
	
	private byte extractAsByte(Object key) {
		return ((Byte) extract(key)).byteValue();
	}

	public short getAsShort(Object key) {
		mutex.lock();
		short result = extractAsShort(key);
		mutex.unlock();
		return result;
	}
	
	private short extractAsShort(Object key) {
		return ((Short) extract(key)).shortValue();
	}

	public int getAsInt(Object key) {
		mutex.lock();
		int result = extractAsInt(key);
		mutex.unlock();
		return result;
	}
	
	private int extractAsInt(Object key) {
		return ((Integer) extract(key)).intValue();
	}

	public long getAsLong(Object key) {
		mutex.lock();
		long result = extractAsLong(key);
		mutex.unlock();
		return result;
	}
	
	private long extractAsLong(Object key) {
		return ((Long) extract(key)).longValue();
	}

	public float getAsFloat(Object key) {
		mutex.lock();
		float result = extractAsFloat(key);
		mutex.unlock();
		return result;
	}
	
	private float extractAsFloat(Object key) {
		return ((Float) extract(key)).floatValue();
	}

	public double getAsDouble(Object key) {
		mutex.lock();
		double result = extractAsDouble(key);
		mutex.unlock();
		return result;
	}
	
	private double extractAsDouble(Object key) {
		return ((Double) extract(key)).doubleValue();
	}

	public boolean getAsBoolean(Object key) {
		mutex.lock();
		boolean result = extractAsBoolean(key);
		mutex.unlock();
		return result;
	}
	
	private boolean extractAsBoolean(Object key) {
		return ((Boolean) extract(key)).booleanValue();
	}
	
	public Object fetch(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			Object result = extract(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public Object fetch(Object key, Object defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			Object result = extract(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public String fetchAsString(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			String result = (String) extract(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public String fetchAsString(Object key, String defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			String result = (String) extract(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public char fetchAsChar(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			char result = extractAsChar(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public char fetchAsChar(Object key, char defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			char result = extractAsChar(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public byte fetchAsByte(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			byte result = extractAsByte(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public byte fetchAsByte(Object key, byte defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			byte result = extractAsByte(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public short fetchAsShort(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			short result = extractAsShort(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public short fetchAsShort(Object key, short defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			short result = extractAsShort(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}

	public int fetchAsInt(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			int result = extractAsInt(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public int fetchAsInt(Object key, int defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			int result = extractAsInt(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public long fetchAsLong(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			long result = extractAsLong(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public long fetchAsLong(Object key, long defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			long result = extractAsLong(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public float fetchAsFloat(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			float result = extractAsFloat(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public float fetchAsFloat(Object key, float defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			float result = extractAsFloat(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public double fetchAsDouble(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			double result = extractAsDouble(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public double fetchAsDouble(Object key, double defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			double result = extractAsDouble(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public boolean fetchAsBoolean(Object key) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			boolean result = extractAsBoolean(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public boolean fetchAsBoolean(Object key, boolean defaultValue) {
		mutex.lock();
		if (hashtable.containsKey(key)) {
			boolean result = extractAsBoolean(key);
			mutex.unlock();
			return result;
		} else {
			mutex.unlock();
			return defaultValue;
		}
	}
	
	public void put(Object key, Object value) {
		mutex.lock();
		hashtable.put(key, value);
		mutex.unlock();
	}
	
	public void put(Object key, char value) {
		mutex.lock();
		hashtable.put(key, new Character(value));
		mutex.unlock();
	}
	
	public void put(Object key, byte value) {
		mutex.lock();
		hashtable.put(key, new Byte(value));
		mutex.unlock();
	}
	
	public void put(Object key, short value) {
		mutex.lock();
		hashtable.put(key, new Short(value));
		mutex.unlock();
	}
	
	public void put(Object key, int value) {
		mutex.lock();
		hashtable.put(key, new Integer(value));
		mutex.unlock();
	}
	
	public void put(Object key, long value) {
		mutex.lock();
		hashtable.put(key, new Long(value));
		mutex.unlock();
	}
	
	public void put(Object key, float value) {
		mutex.lock();
		hashtable.put(key, new Float(value));
		mutex.unlock();
	}
	
	public void put(Object key, double value) {
		mutex.lock();
		hashtable.put(key, new Double(value));
		mutex.unlock();
	}
	
	public void put(Object key, boolean value) {
		mutex.lock();
		hashtable.put(key, new Boolean(value));
		mutex.unlock();
	}
	
	public void remove(Object key) {
		mutex.lock();
		hashtable.remove(key);
		mutex.unlock();
	}
	
	public int size() {
		mutex.lock();
		int result = hashtable.size();
		mutex.unlock();
		return result;
	}
	
	public void clear() {
		mutex.lock();
		hashtable.clear();
		mutex.unlock();
	}
	
	public String toString() {
		mutex.lock();
		String result = hashtable.toString();
		mutex.unlock();
		return result;
	}
	
	public boolean equals(Object other) {
		mutex.lock();
		boolean result = hashtable.equals(other);
		mutex.unlock();
		return result;
	}
}
