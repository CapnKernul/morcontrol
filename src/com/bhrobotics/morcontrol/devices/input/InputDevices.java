package com.bhrobotics.morcontrol.devices.input;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.AnalogModules;
import com.bhrobotics.morcontrol.devices.DigitalModules;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;

public class InputDevices {
    private static InputDevices instance;

    private final DigitalModules digitalModules = DigitalModules.getInstance();
    private final AnalogModules analogModules = AnalogModules.getInstance();

    public static InputDevices getInstance() {
	if (instance == null) {
	    instance = new InputDevices();
	}

	return instance;
    }

    private InputDevices() {}

    public boolean getDigitalInput(Address address) {
	return digitalModules.getDigitalInput(address).getState();
    }

    public double getAnalogInput(Address address) {
	return analogModules.getAnalogInput(address).getState();
    }

    public Map getDigitalInputs() {
	Map result = new HashMap();

	Enumeration e = digitalModules.digitalInputs();
	while (e.hasMoreElements()) {
	    Digitalnput digitalInput = (Digitalnput) e.nextElement();
	    result.put(digitalInput.getAddress(), digitalInput.getState());
	}

	return result;
    }

    public Map getAnalogInputs() {
	Map result = new HashMap();

	Enumeration e = analogModules.analogInputs();
	while (e.hasMoreElements()) {
	    AnalogInput deviceAnalogInput = (AnalogInput) e.nextElement();
	    result.put(deviceAnalogInput.getAddress(), deviceAnalogInput.getState());
	}

	return result;
    }
}
