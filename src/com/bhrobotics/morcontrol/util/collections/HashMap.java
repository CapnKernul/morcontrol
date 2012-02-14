package com.bhrobotics.morcontrol.util.collections;

import java.util.Enumeration;
import java.util.Hashtable;

import com.bhrobotics.morcontrol.util.PrimitiveUtils;

public class HashMap implements Map {
	private Hashtable hashtable;
	
	public HashMap() {
		hashtable = new Hashtable(); 
	}
	
	public HashMap(Hashtable hashtable) {
		this.hashtable = hashtable;
	}
	
	public boolean isEmpty() {
		return hashtable.isEmpty();
	}
	
	public boolean containsValue(Object key) {
		return hashtable.contains(key);
	}
	
	public boolean containsKey(Object key) {
		return hashtable.containsKey(key);
	}
	
	public Enumeration keys() {
		return hashtable.keys();
	}
	
	public Enumeration values() {
		return hashtable.elements();
	}
	
	public Object get(Object key) {
		return hashtable.get(key);
	}
	
	public String getAsString(Object key) {
		return (String) get(key);
	}
	
	public char getAsChar(Object key) {
		return PrimitiveUtils.toPrimitive((Character) get(key));
	}

	public byte getAsByte(Object key) {
		return PrimitiveUtils.toPrimitive((Byte) get(key));
	}

	public short getAsShort(Object key) {
		return PrimitiveUtils.toPrimitive((Short) get(key));
	}
	
	public int getAsInt(Object key) {
		return PrimitiveUtils.toPrimitive((Integer) get(key));
	}

	public long getAsLong(Object key) {
		return PrimitiveUtils.toPrimitive((Long) get(key));
	}
	
	public float getAsFloat(Object key) {
		return PrimitiveUtils.toPrimitive((Float) get(key));
	}

	public double getAsDouble(Object key) {
		return PrimitiveUtils.toPrimitive((Double) get(key));
	}
	
	public boolean getAsBoolean(Object key) {
		return PrimitiveUtils.toPrimitive((Boolean) get(key));
	}
	
	public Object fetch(Object key) {
		if (containsKey(key)) {
			return get(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public Object fetch(Object key, Object defaultValue) {
		if (containsKey(key)) {
			return get(key);
		} else {
			return defaultValue;
		}
	}
	
	public String fetchAsString(Object key) {
		if (containsKey(key)) {
			return getAsString(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public String fetchAsString(Object key, String defaultValue) {
		if (containsKey(key)) {
			return getAsString(key);
		} else {
			return defaultValue;
		}
	}
	
	public char fetchAsChar(Object key) {
		if (containsKey(key)) {
			return getAsChar(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public char fetchAsChar(Object key, char defaultValue) {
		if (containsKey(key)) {
			return getAsChar(key);
		} else {
			return defaultValue;
		}
	}
	
	public byte fetchAsByte(Object key) {
		if (containsKey(key)) {
			return getAsByte(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public byte fetchAsByte(Object key, byte defaultValue) {
		if (containsKey(key)) {
			return getAsByte(key);
		} else {
			return defaultValue;
		}
	}
	
	public short fetchAsShort(Object key) {
		if (containsKey(key)) {
			return getAsShort(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public short fetchAsShort(Object key, short defaultValue) {
		if (containsKey(key)) {
			return getAsShort(key);
		} else {
			return defaultValue;
		}
	}

	public int fetchAsInt(Object key) {
		if (containsKey(key)) {
			return getAsInt(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public int fetchAsInt(Object key, int defaultValue) {
		if (containsKey(key)) {
			return getAsInt(key);
		} else {
			return defaultValue;
		}
	}
	
	public long fetchAsLong(Object key) {
		if (containsKey(key)) {
			return getAsLong(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public long fetchAsLong(Object key, long defaultValue) {
		if (containsKey(key)) {
			return getAsLong(key);
		} else {
			return defaultValue;
		}
	}
	
	public float fetchAsFloat(Object key) {
		if (containsKey(key)) {
			return getAsFloat(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public float fetchAsFloat(Object key, float defaultValue) {
		if (containsKey(key)) {
			return getAsFloat(key);
		} else {
			return defaultValue;
		}
	}
	
	public double fetchAsDouble(Object key) {
		if (containsKey(key)) {
			return getAsDouble(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public double fetchAsDouble(Object key, double defaultValue) {
		if (containsKey(key)) {
			return getAsDouble(key);
		} else {
			return defaultValue;
		}
	}
	
	public boolean fetchAsBoolean(Object key) {
		if (containsKey(key)) {
			return getAsBoolean(key);
		} else {
			throw new UnknownKeyException("Unknown key " + key + ".");
		}
	}
	
	public boolean fetchAsBoolean(Object key, boolean defaultValue) {
		if (containsKey(key)) {
			return getAsBoolean(key);
		} else {
			return defaultValue;
		}
	}
	
	public void put(Object key, Object value) {
		hashtable.put(key, value);
	}
	
	public void put(Object key, char value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void put(Object key, byte value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void put(Object key, short value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void put(Object key, int value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void put(Object key, long value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void put(Object key, float value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void put(Object key, double value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void put(Object key, boolean value) {
		put(key, PrimitiveUtils.toObject(value));
	}
	
	public void remove(Object key) {
		hashtable.remove(key);
	}
	
	public int size() {
		return hashtable.size();
	}
	
	public void clear() {
		hashtable.clear();
	}
	
	public void putAll(Map map) {
		Enumeration enumeration = map.keys();
		while (enumeration.hasMoreElements()) {
			Object key = enumeration.nextElement();
			Object value = map.fetch(key);
			put(key, value);
		}
	}
	
	public boolean equals(Object other) {
		if (other instanceof Map) {
			Map otherMap = (Map) other;
			if (size() != otherMap.size()) {
				return false;
			}
			
			Enumeration enumeration = otherMap.keys();
			while (enumeration.hasMoreElements()) {
				Object key = enumeration.nextElement();
				Object value = otherMap.fetch(key);
				
				if (!containsKey(key) || !fetch(key).equals(value)) {
					return false;
				}
			}
			
			return true;
		} else {
			return false;
		}
	}
}
