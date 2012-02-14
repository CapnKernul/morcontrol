package com.bhrobotics.morcontrol.util.concurrent;

import java.util.Enumeration;
import java.util.Hashtable;

import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;

public class ConcurrentHashMap implements Map {
	private ReentrantMutex mutex = new ReentrantMutex();
	private HashMap hash;
	
	public ConcurrentHashMap() {
		this(new HashMap()); 
	}
	
	public ConcurrentHashMap(HashMap hash) {
		this.hash = hash;
	}
	
	public ConcurrentHashMap(Hashtable hashtable) {
		this(new HashMap(hashtable));
	}
	
	public boolean isEmpty() {
		mutex.lock();
		boolean result = hash.isEmpty();
		mutex.unlock();
		return result;
	}
	
	public boolean containsValue(Object key) {
		mutex.lock();
		boolean result = hash.containsValue(key);
		mutex.unlock();
		return result;
	}
	
	public boolean containsKey(Object key) {
		mutex.lock();
		boolean result = hash.containsKey(key);
		mutex.unlock();
		return result;
	}
	
	public Enumeration keys() {
		mutex.lock();
		Enumeration result = hash.keys();
		mutex.unlock();
		return result;
	}
	
	public Enumeration values() {
		mutex.lock();
		Enumeration result = hash.values();
		mutex.unlock();
		return result;
	}
	
	public Object get(Object key) {
		mutex.lock();
		Object result = hash.get(key);
		mutex.unlock();
		return result;
	}
	
	public String getAsString(Object key) {
		mutex.lock();
		String result = hash.getAsString(key);
		mutex.unlock();
		return result;
	}
	
	public char getAsChar(Object key) {
		mutex.lock();
		char result = hash.getAsChar(key);
		mutex.unlock();
		return result;
	}

	public byte getAsByte(Object key) {
		mutex.lock();
		byte result = hash.getAsByte(key);
		mutex.unlock();
		return result;
	}

	public short getAsShort(Object key) {
		mutex.lock();
		short result = hash.getAsShort(key);
		mutex.unlock();
		return result;
	}
	
	public int getAsInt(Object key) {
		mutex.lock();
		int result = hash.getAsInt(key);
		mutex.unlock();
		return result;
	}

	public long getAsLong(Object key) {
		mutex.lock();
		long result = hash.getAsLong(key);
		mutex.unlock();
		return result;
	}
	
	public float getAsFloat(Object key) {
		mutex.lock();
		float result = hash.getAsFloat(key);
		mutex.unlock();
		return result;
	}
	
	public double getAsDouble(Object key) {
		mutex.lock();
		double result = hash.getAsDouble(key);
		mutex.unlock();
		return result;
	}

	public boolean getAsBoolean(Object key) {
		mutex.lock();
		boolean result = hash.getAsBoolean(key);
		mutex.unlock();
		return result;
	}
	
	public Object fetch(Object key) {
		mutex.lock();
		
		try {
			return hash.fetch(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public Object fetch(Object key, Object defaultValue) {
		mutex.lock();
		Object result = hash.fetch(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public String fetchAsString(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsString(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public String fetchAsString(Object key, String defaultValue) {
		mutex.lock();
		String result = hash.fetchAsString(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public char fetchAsChar(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsChar(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public char fetchAsChar(Object key, char defaultValue) {
		mutex.lock();
		char result = hash.fetchAsChar(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public byte fetchAsByte(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsByte(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public byte fetchAsByte(Object key, byte defaultValue) {
		mutex.lock();
		byte result = hash.fetchAsByte(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public short fetchAsShort(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsShort(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public short fetchAsShort(Object key, short defaultValue) {
		mutex.lock();
		short result = hash.fetchAsShort(key, defaultValue);
		mutex.unlock();
		return result;
	}

	public int fetchAsInt(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsInt(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public int fetchAsInt(Object key, int defaultValue) {
		mutex.lock();
		int result = hash.fetchAsInt(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public long fetchAsLong(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsLong(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public long fetchAsLong(Object key, long defaultValue) {
		mutex.lock();
		Long result = hash.fetchAsLong(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public float fetchAsFloat(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsFloat(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public float fetchAsFloat(Object key, float defaultValue) {
		mutex.lock();
		float result = hash.fetchAsFloat(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public double fetchAsDouble(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsDouble(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public double fetchAsDouble(Object key, double defaultValue) {
		mutex.lock();
		double result = hash.fetchAsDouble(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public boolean fetchAsBoolean(Object key) {
		mutex.lock();
		
		try {
			return hash.fetchAsBoolean(key);
		} finally {
			mutex.unlock();
		}
	}
	
	public boolean fetchAsBoolean(Object key, boolean defaultValue) {
		mutex.lock();
		boolean result = hash.fetchAsBoolean(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public void put(Object key, Object value) {
		mutex.lock();
		hash.put(key, value);
		mutex.unlock();
	}
	
	public void put(Object key, char value) {
		mutex.lock();
		hash.put(key, new Character(value));
		mutex.unlock();
	}
	
	public void put(Object key, byte value) {
		mutex.lock();
		hash.put(key, new Byte(value));
		mutex.unlock();
	}
	
	public void put(Object key, short value) {
		mutex.lock();
		hash.put(key, new Short(value));
		mutex.unlock();
	}
	
	public void put(Object key, int value) {
		mutex.lock();
		hash.put(key, new Integer(value));
		mutex.unlock();
	}
	
	public void put(Object key, long value) {
		mutex.lock();
		hash.put(key, new Long(value));
		mutex.unlock();
	}
	
	public void put(Object key, float value) {
		mutex.lock();
		hash.put(key, new Float(value));
		mutex.unlock();
	}
	
	public void put(Object key, double value) {
		mutex.lock();
		hash.put(key, new Double(value));
		mutex.unlock();
	}
	
	public void put(Object key, boolean value) {
		mutex.lock();
		hash.put(key, new Boolean(value));
		mutex.unlock();
	}
	
	public void remove(Object key) {
		mutex.lock();
		hash.remove(key);
		mutex.unlock();
	}
	
	public int size() {
		mutex.lock();
		int result = hash.size();
		mutex.unlock();
		return result;
	}
	
	public void clear() {
		mutex.lock();
		hash.clear();
		mutex.unlock();
	}
	
	public boolean equals(Object other) {
		mutex.lock();
		boolean result = hash.equals(other);
		mutex.unlock();
		return result;
	}

	public void putAll(Map map) {
		mutex.lock();
		hash.putAll(map);
		mutex.unlock();
	}
}
