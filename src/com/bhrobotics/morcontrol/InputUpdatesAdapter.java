package com.bhrobotics.morcontrol;

import com.bhrobotics.morcontrol.devices.input.AnalogInputDevice;
import com.bhrobotics.morcontrol.devices.input.DigitalInputDevice;
import com.bhrobotics.morcontrol.devices.input.InputDeviceAccessor;
import com.bhrobotics.morcontrol.protobuf.AnalogInputUpdate;
import com.bhrobotics.morcontrol.protobuf.DigitalInputUpdate;
import com.bhrobotics.morcontrol.protobuf.InputUpdates;

public class InputUpdatesAdapter {
	private InputDeviceAccessor accessor;
	
	public InputUpdatesAdapter() {
		this(new InputDeviceAccessor());
	}
	
	public InputUpdatesAdapter(InputDeviceAccessor accessor) {
		this.accessor = accessor;
	}

	public InputUpdates getAllInputs() {
		InputUpdates.Builder builder = InputUpdates.newBuilder();
		DigitalInputDevice[] digitalInputs = accessor.getDigitalInputs();
		AnalogInputDevice[] analogInputs = accessor.getAnalogInputs();
		
		for (int i = 0; i < digitalInputs.length; i++) {
			DigitalInputUpdate update = newDigitalInputUpdate(digitalInputs[i]);
			builder.addElementDigital_input_updates(update);
		}

		for (int i = 0; i < analogInputs.length; i++) {
			AnalogInputUpdate update = newAnalogInputUpdate(analogInputs[i]);
			builder.addElementAnalog_input_updates(update);
		}
		
		return builder.build();
	}

	public InputUpdates getUpdatedInputs() {
		InputUpdates.Builder builder = InputUpdates.newBuilder();
		DigitalInputDevice[] digitalInputs = accessor.getDigitalInputs();
		AnalogInputDevice[] analogInputs = accessor.getAnalogInputs();
		
		for (int i = 0; i < digitalInputs.length; i++) {
			DigitalInputDevice device = digitalInputs[i];
			if (device.hasChanged()) {
				DigitalInputUpdate update = newDigitalInputUpdate(device);
				builder.addElementDigital_input_updates(update);
			}
		}

		for (int i = 0; i < analogInputs.length; i++) {
			AnalogInputDevice device = analogInputs[i];
			if (device.hasChanged()) {
				AnalogInputUpdate update = newAnalogInputUpdate(device);
				builder.addElementAnalog_input_updates(update);
			}
		}
		
		return builder.build();
	}

	private DigitalInputUpdate newDigitalInputUpdate(DigitalInputDevice device) {
		return DigitalInputUpdate.newBuilder()
				.setModule(device.getModule())
				.setChannel(device.getChannel())
				.setValue(device.getState())
				.build();
	}
	
	private AnalogInputUpdate newAnalogInputUpdate(AnalogInputDevice device) {
		return AnalogInputUpdate.newBuilder()
				.setModule(device.getModule())
				.setChannel(device.getChannel())
				.setValue(device.getState())
				.build();
	}
}
