package com.bhrobotics.morcontrol.devices.input;

import java.util.Vector;

import com.bhrobotics.morcontrol.util.OperatingSystem;
import com.bhrobotics.morcontrol.util.PrimitiveUtils;

import edu.wpi.first.wpilibj.communication.ModulePresence;

public class InputDeviceAccessor {
	private static final int MAX_MODULES = 2;
	private static final int DIGITAL_CHANNELS_PER_MODULE = 14;
	private static final int ANALOG_CHANNELS_FOR_MODULE_1 = 7;
	private static final int ANALOG_CHANNELS_FOR_OTHER_MODULES = 8;
	private static final int[] DEFAULT_DIGITAL_MODULES = new int[] { 2, 4 };
	private static final int[] DEFAULT_ANALOG_MODULES = new int[] { 1 };
	
	private InputDeviceFactory factory = new InputDeviceFactory();
	private DigitalInputDevice[] digitalInputs;
	private AnalogInputDevice[] analogInputs;
	
	public int[] getDigitalModules() {
		if (OperatingSystem.isCRio()) {
			Vector vector = new Vector();
			for (int i = 0; i < MAX_MODULES; i++) {
				if (ModulePresence.getModulePresence(ModulePresence.ModuleType.kDigital, i)) {
					vector.addElement(new Integer(i + 1));
				}
			}
			
			return toIntArray(vector);
		} else {
			return DEFAULT_DIGITAL_MODULES;
		}
	}	
	
	public int[] getAnalogModules() {
		if (OperatingSystem.isCRio()) {
			Vector vector = new Vector();
			for (int i = 0; i < MAX_MODULES; i++) {
				if (ModulePresence.getModulePresence(ModulePresence.ModuleType.kAnalog, i)) {
					vector.addElement(new Integer(i + 1));
				}
			}
			
			return toIntArray(vector);
		} else {
			return DEFAULT_ANALOG_MODULES;
		}
	}
	
	public DigitalInputDevice[] getDigitalInputs() {
		if (digitalInputs == null) {
			Vector devices = new Vector();
			int[] digitalModules = getDigitalModules();
			
			for (int i = 0; i < digitalModules.length; i++) {
				int module = digitalModules[i];
				for (int channel = 1; channel <= DIGITAL_CHANNELS_PER_MODULE; channel++) {
					DigitalInputDevice device = factory.newDigitalInput(module, channel);
					devices.addElement(device);
				}
			}
			
			digitalInputs = new DigitalInputDevice[devices.size()];
			devices.copyInto(digitalInputs);
		}
		
		return digitalInputs;
	}

	public AnalogInputDevice[] getAnalogInputs() {
		if (analogInputs == null) {
			Vector devices = new Vector();
			int[] analogModules = getAnalogModules();
			
			for (int i = 0; i < analogModules.length; i++) {
				int module = analogModules[i];
				int channels = module == 1 ? ANALOG_CHANNELS_FOR_MODULE_1 : ANALOG_CHANNELS_FOR_OTHER_MODULES;
				
				for (int channel = 1; channel <= channels; channel++) {
					AnalogInputDevice device = factory.newAnalogInput(module, channel);
					devices.addElement(device);
				}
			}
			
			analogInputs = new AnalogInputDevice[devices.size()];
			devices.copyInto(analogInputs);
		}
		
		return analogInputs;
	}

	private int[] toIntArray(Vector vector) {
		Integer[] objectArray = new Integer[vector.size()];
		int[] intArray = new int[vector.size()];
		
		vector.copyInto(objectArray);
		for (int i = 0; i < objectArray.length; i++) {
			intArray[i] = PrimitiveUtils.toPrimitive(objectArray[i]);
		}
		
		return intArray;
	}
}
