package com.bhrobotics.morcontrol.devices.output;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class OutputDeviceAccessor {
	private static final String MOTOR_PREFIX = "motor";
	private static final String RELAY_PREFIX = "relay";
	private static final String SOLENOID_PREFIX = "solenoid";
	
	private OutputDeviceFactory factory = new OutputDeviceFactory();
	private ReentrantMutex mutex = new ReentrantMutex();
	private Map devices = new HashMap();
	
	public MotorDevice getMotor(int module, int channel) {
		mutex.lock();
		
		MotorDevice result;
		String key = toString(MOTOR_PREFIX, module, channel);
		if (!devices.containsKey(key)) {
			result = factory.newMotor(module, channel);
			devices.put(key, result);
		}
		
		result = (MotorDevice) devices.fetch(key);
		mutex.unlock();
		return result;
	}
	
	public void updateMotor(int module, int channel, double state) {
		mutex.lock();
		
		try {
			getMotor(module, channel).update(state);
		} finally {
			mutex.unlock();
		}
	}

	public SolenoidDevice getSolenoid(int module, int channel) {
		mutex.lock();
		
		SolenoidDevice result;
		String key = toString(SOLENOID_PREFIX, module, channel);
		if (!devices.containsKey(key)) {
			result = factory.newSolenoid(module, channel);
			devices.put(key, result);
		}
		
		result = (SolenoidDevice) devices.fetch(key);
		mutex.unlock();
		return result;
	}
	
	public void updateSolenoid(int module, int channel, boolean state) {
		mutex.lock();
		getSolenoid(module, channel).update(state);
		mutex.unlock();
	}

	public RelayDevice getRelay(int module, int channel) {
		mutex.lock();
		
		RelayDevice result;
		String key = toString(RELAY_PREFIX, module, channel);
		if (!devices.containsKey(key)) {
			result = factory.newRelay(module, channel);
			devices.put(key, result);
		}
		
		result = (RelayDevice) devices.fetch(key);
		mutex.unlock();
		return result;
	}
	
	public void updateRelay(int module, int channel, RelayState state) {
		mutex.lock();
		getRelay(module, channel).update(state);
		mutex.unlock();
	}
	
	public void reset() {
		mutex.lock();
		
		Enumeration enumeration = devices.values();
		while (enumeration.hasMoreElements()) {
			OutputDevice device = (OutputDevice) enumeration.nextElement();
			device.reset();
		}
		
		mutex.unlock();
	}
	
	public void apply() {
		mutex.lock();
		
		Enumeration enumeration = devices.values();
		while (enumeration.hasMoreElements()) {
			OutputDevice device = (OutputDevice) enumeration.nextElement();
			device.apply();
		}
		
		mutex.unlock();
	}
	
	private String toString(String prefix, int module, int channel) {
		return prefix + module + ":" + channel;
	}
}
