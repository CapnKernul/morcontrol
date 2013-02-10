package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class DigitalInputRegistry extends BasicDeviceRegistry {
	private BasicDeviceIndex index;

	public DigitalInputRegistry(ModuleMapping mapping) {
		index = new BasicDeviceIndex(this, mapping.getDigitalModuleCount());
		Logger.defaultLogger.debug("initialized " + index.getAllDevices().length + " digital inputs.");
	}

	public int getChannelsPerModule() {
		return 14;
	}

	public BasicDeviceIndex getIndex() {
		return index;
	}

	public Device initializeDevice(Address address) {
		return new DigitalInput(address);
	}

	public Type getType() {
		return Registry.Type.DIGITAL_INPUT;
	}

	public Device[] getAllDevices() {
		return index.getAllDevices();
	}

	public boolean getDigitalInput(Address address) throws InvalidAddressException {
		return ((DigitalInput) index.getDeviceAt(address)).getState();
	}

	public boolean[] getAllDigitalInputs() {
		boolean[] states = new boolean[getAllDevices().length];
		for (int i = 0; i < getAllDevices().length; i++) {
			states[i] = ((DigitalInput) getAllDevices()[i]).getState();
		}
		return states;
	}

	public void reset() {
		index.reset();
	}
}