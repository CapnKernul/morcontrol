package com.bhrobotics.morcontrol.oi.hashes;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.oi.ConcurrentMessageConnection;
import com.bhrobotics.morcontrol.oi.OIException;
import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;

public class OutputHashMap extends AbstractOIHashMap {
	private boolean inTransaction = false;
	private Vector transactionBuffer = new Vector();
	private ConcurrentMessageConnection connection;
	
	public OutputHashMap(ConcurrentMessageConnection connection) {
		this.connection = connection;
	}
	
	public boolean getAsBoolean(String key) {
		if (inTransaction) {
			return hash.getAsBoolean(key);
		} else {
			return super.getAsBoolean(key);
		}
	}
	
	public double getAsDouble(String key) {
		if (inTransaction) {
			return hash.getAsDouble(key);
		} else {
			return super.getAsDouble(key);
		}
	}
		
	public String getAsString(String key) {
		if (inTransaction) {
			return hash.getAsString(key);
		} else {
			return super.getAsString(key);
		}
	}
	
	public boolean fetchAsBoolean(String key) {
		if (inTransaction) {
			return hash.fetchAsBoolean(key);
		} else {
			return super.fetchAsBoolean(key);
		}
	}

	public boolean fetchAsBoolean(String key, boolean defaultValue) {
		if (inTransaction) {
			return hash.fetchAsBoolean(key, defaultValue);
		} else {
			return super.fetchAsBoolean(key, defaultValue);
		}
	}
	
	public double fetchAsDouble(String key) {
		if (inTransaction) {
			return hash.fetchAsDouble(key);
		} else {
			return super.fetchAsDouble(key);
		}
	}

	public double fetchAsDouble(String key, double defaultValue) {
		if (inTransaction) {
			return hash.fetchAsDouble(key, defaultValue);
		} else {
			return super.fetchAsDouble(key, defaultValue);
		}
	}

	public String fetchAsString(String key) {
		if (inTransaction) {
			return hash.fetchAsString(key);
		} else {
			return super.fetchAsString(key);
		}
	}

	public String fetchAsString(String key, String defaultValue) {
		if (inTransaction) {
			return hash.fetchAsString(key, defaultValue);
		} else {
			return super.fetchAsString(key, defaultValue);
		}
	}
	
	public void put(String key, boolean value) {
		if (inTransaction) {
			hash.put(key, value);
			addToBuffer(key, value);
		} else {
			mutex.lock();
			hash.put(key, value);
			write(key, value);
			mutex.unlock();
		}
	}

	public void put(String key, double value) {
		if (inTransaction) {
			hash.put(key, value);
			addToBuffer(key, value);
		} else {
			mutex.lock();
			hash.put(key, value);
			write(key, value);
			mutex.unlock();
		}
	}

	public void put(String key, String value) {
		if (inTransaction) {
			hash.put(key, value);
			addToBuffer(key, value);
		} else {
			mutex.lock();
			hash.put(key, value);
			write(key, value);
			mutex.unlock();
		}
	}
	
	public boolean isInTransaction() {
		return inTransaction;
	}
	
	public void beginTransaction() {
		mutex.lock();
		inTransaction = true;
	}
	
	public void commitTransaction() {
		flushBuffer();
		inTransaction = false;
		mutex.unlock();
	}
	
	public void resend() {
		beginTransaction();
		
		Enumeration enumeration = hash.keys();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			Object value = hash.get(key);
			
			if (value instanceof Boolean) {
				boolean booleanValue = ((Boolean) value).booleanValue();
				addToBuffer(key, booleanValue);
			} else if (value instanceof Double) {
				double doubleValue = ((Double) value).doubleValue();
				addToBuffer(key, doubleValue);
			} else if (value instanceof String) {
				String stringValue = (String) value;
				addToBuffer(key, stringValue);
			} else {
				throw new OIException("Unknown data type.");
			}
		}
		
		commitTransaction();
	}
	
	private void addToBuffer(String key, boolean value) {
		BooleanMessage message = new BooleanMessage(key, value);
		addToBuffer(message);
	}

	private void addToBuffer(String key, double value) {
		NumberMessage message = new NumberMessage(key, value);
		addToBuffer(message);
	}

	private void addToBuffer(String key, String value) {
		StringMessage message = new StringMessage(key, value);
		addToBuffer(message);
	}
	
	private void addToBuffer(Message message) {
		transactionBuffer.addElement(message);
	}
	
	private void write(String key, boolean value) {
		BooleanMessage message = new BooleanMessage(key, value);
		write(message);
	}

	private void write(String key, double value) {
		NumberMessage message = new NumberMessage(key, value);
		write(message);
	}

	private void write(String key, String value) {
		StringMessage message = new StringMessage(key, value);
		write(message);
	}
	
	private void write(Message message) {
		write(new Message[] { message });
	}

	private void write(Message[] messages) {
		connection.write(messages);
	}
	
	private void flushBuffer() {
		Message[] messages = new Message[transactionBuffer.size()];
		transactionBuffer.copyInto(messages);
		transactionBuffer.removeAllElements();
		write(messages);
	}
}
