package com.bhrobotics.morcontrol.oi.hashes;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.util.collections.Map;
import com.bhrobotics.morcontrol.util.concurrent.ConcurrentHashMap;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public abstract class AbstractOIHashMap {
	protected ReentrantMutex mutex = new ReentrantMutex();
	protected ConcurrentHashMap hash = new ConcurrentHashMap();
	
	public int size() {
		mutex.lock();
		int result = hash.size();
		mutex.unlock();
		return result;
	}
	
	public boolean isEmpty() {
		mutex.lock();
		boolean result = hash.isEmpty();
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

	public boolean containsKey(String key) {
		mutex.lock();
		boolean result = hash.containsKey(key);
		mutex.unlock();
		return result;
	}

	public boolean containsValue(boolean value) {
		mutex.lock();
		boolean result = hash.containsValue(value);
		mutex.unlock();
		return result;
	}

	public boolean containsValue(double value) {
		mutex.lock();
		boolean result = hash.containsValue(value);
		mutex.unlock();
		return result;
	}
	
	public boolean containsValue(String value) {
		mutex.lock();
		boolean result = hash.containsValue(value);
		mutex.unlock();
		return result;
	}

	public boolean getAsBoolean(String key) {
		mutex.lock();
		boolean result = hash.getAsBoolean(key);
		mutex.unlock();
		return result;
	}
	
	public double getAsDouble(String key) {
		mutex.lock();
		double result = hash.getAsDouble(key);
		mutex.unlock();
		return result;
	}
		
	public String getAsString(String key) {
		mutex.lock();
		String result = hash.getAsString(key);
		mutex.unlock();
		return result;
	}
	
	public boolean fetchAsBoolean(String key) {
		mutex.lock();
		
		try {
			return hash.fetchAsBoolean(key);
		} finally {
			mutex.unlock();
		}
	}

	public boolean fetchAsBoolean(String key, boolean defaultValue) {
		mutex.lock();
		boolean result = hash.fetchAsBoolean(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public double fetchAsDouble(String key) {
		mutex.lock();
		
		try {
			return hash.fetchAsDouble(key);
		} finally {
			mutex.unlock();
		}
	}

	public double fetchAsDouble(String key, double defaultValue) {
		mutex.lock();
		double result = hash.fetchAsDouble(key, defaultValue);
		mutex.unlock();
		return result;
	}

	public String fetchAsString(String key) {
		mutex.lock();
		
		try {
			return hash.fetchAsString(key);
		} finally {
			mutex.unlock();
		}
	}

	public String fetchAsString(String key, String defaultValue) {
		mutex.lock();
		String result = hash.fetchAsString(key, defaultValue);
		mutex.unlock();
		return result;
	}
	
	public boolean equals(Object other) {
		if (other instanceof AbstractOIHashMap) {
			AbstractOIHashMap otherOIHash = (AbstractOIHashMap) other;
			return hash.equals(otherOIHash.toMap());
		} else {
			return false;
		}
	}
	
	public Map toMap() {
		return hash;
	}
}
