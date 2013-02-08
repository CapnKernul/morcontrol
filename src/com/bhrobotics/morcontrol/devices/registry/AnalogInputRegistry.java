package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AnalogInputRegistry extends BasicDeviceRegistry {
    private BasicDeviceIndex index;

    public AnalogInputRegistry(ModuleMapping mapping) {
	index = new BasicDeviceIndex(this, mapping.getAnalogModuleCount());
	Logger.defaultLogger.debug("initialized " + index.getAllDevices().length + " analog inputs.");
    }

    public int getChannelsPerModule() {
	return 7;
    }

    public BasicDeviceIndex getIndex() {
	return index;
    }

    public Device initializeDevice(Address address) {
	return new AnalogInput(address);
    }

    public Type getType() {
	return Registry.Type.ANALOG_INPUT;
    }

    public Device[] getAllDevices() {
	return index.getAllDevices();
    }

    public double getAnalogInput(Address address) throws InvalidAddressException {
	return ((AnalogInput) index.getDeviceAt(address)).getState();
    }

    public double[] getAllAnalogInputs() {
	double[] states = new double[getAllDevices().length];
	for (int i = 0; i < getAllDevices().length; i++) {
	    states[i] = ((AnalogInput) getAllDevices()[i]).getState();
	}
	return states;
    }

    public void reset() {
	index.reset();
    }
}