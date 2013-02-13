package com.bhrobotics.morcontrol.devices.registry;

import java.util.Enumeration;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;

public interface Registry {

	public Device getDevice(Address address) throws InvalidAddressException;

	public Enumeration getAllDevices();

	public void reset();
}
