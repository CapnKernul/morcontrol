package com.bhrobotics.morcontrol;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.devices.output.OutputDeviceAccessor;
import com.bhrobotics.morcontrol.devices.output.RelayState;
import com.bhrobotics.morcontrol.protobuf.MotorUpdate;
import com.bhrobotics.morcontrol.protobuf.OutputUpdates;
import com.bhrobotics.morcontrol.protobuf.RelayUpdate;
import com.bhrobotics.morcontrol.protobuf.SolenoidUpdate;

public class OutputUpdatesAdapter {
	private OutputDeviceAccessor accessor;
	
	public OutputUpdatesAdapter() {
		this(new OutputDeviceAccessor());
	}
	
	public OutputUpdatesAdapter(OutputDeviceAccessor accessor) {
		this.accessor = accessor;
	}
	
	public void reset() {
		accessor.reset();
	}
	
	public void apply() {
		accessor.apply();
	}
	
	public void update(OutputUpdates updates) {
		updateMotors(updates.getMotor_updates());
		updateRelays(updates.getRelay_updates());
		updateSolenoids(updates.getSolenoid_updates());
	}
	
	private void updateMotors(Vector motorUpdates) {
		Enumeration enumeration = motorUpdates.elements();
		while (enumeration.hasMoreElements()) {
			MotorUpdate motorUpdate = (MotorUpdate) enumeration.nextElement();
			int module = motorUpdate.getModule();
			int channel = motorUpdate.getChannel();
			double value = motorUpdate.getValue();
			updateMotor(module, channel, value);
		}
	}

	private void updateRelays(Vector relayUpdates) {
		Enumeration enumeration = relayUpdates.elements();
		while (enumeration.hasMoreElements()) {
			RelayUpdate relayUpdate = (RelayUpdate) enumeration.nextElement();
			int module = relayUpdate.getModule();
			int channel = relayUpdate.getChannel();
			int value = relayUpdate.getValue();
			updateRelay(module, channel, value);
		}
	}

	private void updateSolenoids(Vector solenoidUpdates) {
		Enumeration enumeration = solenoidUpdates.elements();
		while (enumeration.hasMoreElements()) {
			SolenoidUpdate solenoidUpdate = (SolenoidUpdate) enumeration.nextElement();
			int module = solenoidUpdate.getModule();
			int channel = solenoidUpdate.getChannel();
			boolean value = solenoidUpdate.getValue();
			updateSolenoid(module, channel, value);
		}
	}
	
	private void updateMotor(int module, int channel, double value) {
		try {
			accessor.updateMotor(module, channel, value);
		} catch (InvalidStateException e) {
			throw new InvalidMessageException(e);
		}
	}

	private void updateRelay(int module, int channel, int value) {
		RelayState state;
		switch (value) {
		case RelayUpdate.RelayValue.OFF:
			state = RelayState.OFF;
			break;
		case RelayUpdate.RelayValue.FORWARD:
			state = RelayState.FORWARD;
			break;
		case RelayUpdate.RelayValue.REVERSE:
			state = RelayState.REVERSE;
			break;
		default:
			throw new InvalidMessageException("Unknown relay state.");
		}
		
		accessor.updateRelay(module, channel, state);
	}

	private void updateSolenoid(int module, int channel, boolean value) {
		accessor.updateSolenoid(module, channel, value);
	}
}
