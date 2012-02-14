package com.bhrobotics.morcontrol.oi.messages;

import com.bhrobotics.morcontrol.oi.ConcurrentMessageConnection;
import com.bhrobotics.morcontrol.oi.OIException;
import com.bhrobotics.morcontrol.util.StringUtils;

public abstract class AbstractMessageSerializer {
	public static final String GROUP_SEPARATOR = new String(ConcurrentMessageConnection.GROUP_SEPARATOR_BYTES);
	public static final String RECORD_SEPARATOR = new String(ConcurrentMessageConnection.RECORD_SEPARATOR_BYTES);
	public static final String UNIT_SEPARATOR = new String(ConcurrentMessageConnection.UNIT_SEPARATOR_BYTES);
	
	public abstract String getType();
	
	public boolean canDeserialize(byte[] bytes) {
		String string = new String(bytes);
		return string.startsWith(getType() + UNIT_SEPARATOR);
	}
	
	protected byte[] createMessage(String key, String value) {
		if (key.equals("")) {
			throw new OIException("Keys and values cannot be empty.");
		}

		verifyString(key);
		verifyString(value);
		
		String message = getType() + UNIT_SEPARATOR + key + UNIT_SEPARATOR + value;
		return message.getBytes();
	}
	
	protected String[] parse(byte[] bytes) {
		String[] result = StringUtils.split(new String(bytes), UNIT_SEPARATOR);
		
		if (result.length != 3 || !result[0].equals(getType())) {
			throw new OIException("Invalid message.");
		}
		
		if (result[1].equals("")) {
			throw new OIException("Invalid message key.");
		}
		
		if (result[2].equals("")) {
			throw new OIException("Invalid message value.");
		}
		
		return result;
	}
	
	private void verifyString(String string) {
		if (StringUtils.contains(string, UNIT_SEPARATOR) || StringUtils.contains(string, RECORD_SEPARATOR) || StringUtils.contains(string, GROUP_SEPARATOR)) {
			throw new OIException("Keys and values cannot contain the record or unit separator.");
		}
	}
}
