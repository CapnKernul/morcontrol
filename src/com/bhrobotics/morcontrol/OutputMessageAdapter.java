package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;
import com.bhrobotics.morcontrol.output.AnalogState;
import com.bhrobotics.morcontrol.output.DeviceUpdater;
import com.bhrobotics.morcontrol.output.DigitalState;
import com.bhrobotics.morcontrol.output.InvalidStateException;
import com.bhrobotics.morcontrol.output.RelayState;
import com.bhrobotics.morcontrol.util.StringUtils;

public class OutputMessageAdapter {
	private static final String MOTOR_PREFIX = "motor";
	private static final String RELAY_PREFIX = "relay";
	private static final String SOLENOID_PREFIX = "solenoid";
	private static final String RELAY_FORWARD_STRING = "f";
	private static final String RELAY_REVERSE_STRING = "r";
	private static final String RELAY_OFF_STRING = "o";
	
	private DeviceUpdater updater;
	
	public OutputMessageAdapter() {
		this(new DeviceUpdater());
	}
	
	public OutputMessageAdapter(DeviceUpdater updater) {
		this.updater = updater;
	}
	
	public void reset() {
		updater.reset();
	}
	
	public void reapply() {
		updater.reapply();
	}
	
	public void update(Message message) {
		try {
			String[] tokens = lexKey(message.getKey());
			String prefix = tokens[0];
			int slot = Integer.parseInt(tokens[1]);
			int channel = Integer.parseInt(tokens[2]);
	
			if (prefix.equals(MOTOR_PREFIX)) {
				NumberMessage numberMessage = (NumberMessage) message;
				updateMotor(slot, channel, numberMessage.toDouble());
			} else if (prefix.equals(RELAY_PREFIX)) {
				StringMessage stringMessage = (StringMessage) message;
				updateRelay(slot, channel, stringMessage.toString());
			} else if (prefix.equals(SOLENOID_PREFIX)) {
				BooleanMessage booleanMessage = (BooleanMessage) message;
				updateSolenoid(slot, channel, booleanMessage.toBoolean());
			} else {
				throw new InvalidMessageException("Unknown message prefix.");
			}
		} catch (NumberFormatException e) {
			throw new InvalidMessageException(e);
		} catch (ClassCastException e) {
			throw new InvalidMessageException(e);
		}
	}
	
	private String[] lexKey(String key) {
		String[] parts1 = StringUtils.split(key, " ");
		
		if (parts1.length == 2) {
			String[] parts2 = StringUtils.split(parts1[1], ":");
			
			if (parts2.length == 2) {
				return new String[] { parts1[0], parts2[0], parts2[1] };
			} else {
				throw new InvalidMessageException("Unable to parse message.");
			}
		} else {
			throw new InvalidMessageException("Unable to parse message.");
		}
	}
	
	private void updateMotor(int slot, int channel, double value) {
		try {
			AnalogState state = new AnalogState(value);
			updater.updateMotor(slot, channel, state);
		} catch (InvalidStateException e) {
			throw new InvalidMessageException(e);
		}
	}

	private void updateRelay(int slot, int channel, String value) {
		RelayState state;
		if (value.equals(RELAY_FORWARD_STRING)) {
			state = RelayState.FORWARD;
		} else if (value.equals(RELAY_REVERSE_STRING)) {
			state = RelayState.REVERSE;
		} else if (value.equals(RELAY_OFF_STRING)) {
			state = RelayState.OFF;
		} else {
			throw new InvalidMessageException("Unknown relay state.");
		}
		
		updater.updateRelay(slot, channel, state);
	}

	private void updateSolenoid(int slot, int channel, boolean value) {
		DigitalState state;
		if (value) {
			state = DigitalState.ON;
		} else {
			state = DigitalState.OFF;
		}
		
		updater.updateSolenoid(slot, channel, state);
	}
}
