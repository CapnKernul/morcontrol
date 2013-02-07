package com.bhrobotics.morcontrol.util.collections;

import java.util.Enumeration;

public interface Map {
	public int size();

	public boolean isEmpty();

	public boolean containsKey(Object key);

	public boolean containsValue(Object value);

	public boolean equals(Object other);

	public Enumeration keys();

	public Enumeration values();

	public Object get(Object key);

	public String getAsString(Object key);

	public char getAsChar(Object key);

	public byte getAsByte(Object key);

	public short getAsShort(Object key);

	public int getAsInt(Object key);

	public long getAsLong(Object key);

	public float getAsFloat(Object key);

	public double getAsDouble(Object key);

	public boolean getAsBoolean(Object key);

	public Object fetch(Object key);

	public String fetchAsString(Object key);

	public char fetchAsChar(Object key);

	public byte fetchAsByte(Object key);

	public short fetchAsShort(Object key);

	public int fetchAsInt(Object key);

	public long fetchAsLong(Object key);

	public float fetchAsFloat(Object key);

	public double fetchAsDouble(Object key);

	public boolean fetchAsBoolean(Object key);

	public Object fetch(Object key, Object defaultValue);

	public String fetchAsString(Object key, String defaultValue);

	public char fetchAsChar(Object key, char defaultValue);

	public byte fetchAsByte(Object key, byte defaultValue);

	public short fetchAsShort(Object key, short defaultValue);

	public int fetchAsInt(Object key, int defaultValue);

	public long fetchAsLong(Object key, long defaultValue);

	public float fetchAsFloat(Object key, float defaultValue);

	public double fetchAsDouble(Object key, double defaultValue);

	public boolean fetchAsBoolean(Object key, boolean defaultValue);

	public void clear();

	public void remove(Object key);

	public void putAll(Map map);

	public void put(Object key, Object value);

	public void put(Object key, char value);

	public void put(Object key, byte value);

	public void put(Object key, short value);

	public void put(Object key, int value);

	public void put(Object key, long value);

	public void put(Object key, float value);

	public void put(Object key, double value);

	public void put(Object key, boolean value);
}
