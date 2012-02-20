package com.bhrobotics.morcontrol.output;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.Map;
import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class DeviceUpdater {
	private ReentrantMutex mutex = new ReentrantMutex();
	private Map motors = new HashMap();
	private Map solenoids = new HashMap();
	private Map relays = new HashMap();
	
	public MotorDevice getMotor(int slot, int channel) {
		mutex.lock();
		
		MotorDevice result;
		String key = toString(slot, channel);
		if (!motors.containsKey(key)) {
			result = new MotorDevice(slot, channel);
			motors.put(key, result);
		}
		
		result = (MotorDevice) motors.fetch(key);
		mutex.unlock();
		return result;
	}
	
	public void updateMotor(int slot, int channel, AnalogState state) {
		mutex.lock();
		getMotor(slot, channel).update(state);
		mutex.unlock();
	}

	public SolenoidDevice getSolenoid(int slot, int channel) {
		mutex.lock();
		
		SolenoidDevice result;
		String key = toString(slot, channel);
		if (!solenoids.containsKey(key)) {
			result = new SolenoidDevice(slot, channel);
			solenoids.put(key, result);
		}
		
		result = (SolenoidDevice) solenoids.fetch(key);
		mutex.unlock();
		return result;
	}
	
	public void updateSolenoid(int slot, int channel, DigitalState state) {
		mutex.lock();
		getSolenoid(slot, channel).update(state);
		mutex.unlock();
	}

	public RelayDevice getRelay(int slot, int channel) {
		mutex.lock();
		
		RelayDevice result;
		String key = toString(slot, channel);
		if (!relays.containsKey(key)) {
			result = new RelayDevice(slot, channel);
			relays.put(key, result);
		}
		
		result = (RelayDevice) relays.fetch(key);
		mutex.unlock();
		return result;
	}
	
	public void updateRelay(int slot, int channel, RelayState state) {
		mutex.lock();
		getRelay(slot, channel).update(state);
		mutex.unlock();
	}
	
	public void reset() {
		mutex.lock();
		
		Enumeration enumeration = motors.values();
		while (enumeration.hasMoreElements()) {
			MotorDevice device = (MotorDevice) enumeration.nextElement();
			device.reset();
		}

		enumeration = relays.values();
		while (enumeration.hasMoreElements()) {
			RelayDevice device = (RelayDevice) enumeration.nextElement();
			device.reset();
		}

		enumeration = solenoids.values();
		while (enumeration.hasMoreElements()) {
			SolenoidDevice device = (SolenoidDevice) enumeration.nextElement();
			device.reset();
		}
		
		mutex.unlock();
	}
	
	public void reapply() {
		mutex.lock();
		
		Enumeration enumeration = motors.values();
		while (enumeration.hasMoreElements()) {
			MotorDevice device = (MotorDevice) enumeration.nextElement();
			device.reapply();
		}

		enumeration = relays.values();
		while (enumeration.hasMoreElements()) {
			RelayDevice device = (RelayDevice) enumeration.nextElement();
			device.reapply();
		}

		enumeration = solenoids.values();
		while (enumeration.hasMoreElements()) {
			SolenoidDevice device = (SolenoidDevice) enumeration.nextElement();
			device.reapply();
		}
		
		mutex.unlock();
	}
	
	private String toString(int slot, int channel) {
		return slot + ":" + channel;
	}
}
