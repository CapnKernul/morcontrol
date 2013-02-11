package com.bhrobotics.morcontrol.devices.registry;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.InvalidStateException;
import com.bhrobotics.morcontrol.util.collections.HashMap;
import com.bhrobotics.morcontrol.util.collections.UnknownKeyException;

public class BasicDeviceIndex implements Registry {
	private HashMap devices = new HashMap();
	private DeviceFactory factory;

	public BasicDeviceIndex(DeviceFactory factory) {
		this.factory = factory;
	}

	public Device getDevice(Address address) throws InvalidAddressException {
		try {
			return (Device) devices.fetch(address);
		} catch (UnknownKeyException e) {
			throw new InvalidAddressException(address);
		}
	}

	public Enumeration getAllDevices() {
		return devices.values();
	}

	public Enumeration getAllTakenAddresses() {
		return devices.keys();
	}

	public void reset() {
		Enumeration e = getAllDevices();
		while (e.hasMoreElements()) {
			((Device) e.nextElement()).reset();
		}
	}

	public void makeDevice(Address address) throws InvalidAddressException {
		if (!devices.containsKey(address)) {
			throw new InvalidAddressException(address);
		}
		devices.put(address, factory.makeDevice(address));
	}

}
