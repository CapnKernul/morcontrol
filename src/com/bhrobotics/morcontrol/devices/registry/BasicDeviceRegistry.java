package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;

public abstract class BasicDeviceRegistry implements Registry {

    public Device getDevice(Address address) throws InvalidAddressException {
	return getIndex().getDeviceAt(address);
    }

    public Device[] getAllDevices() {
	return getIndex().getAllDevices();
    }

    public abstract BasicDeviceIndex getIndex();

    public abstract Device initializeDevice(Address address);
}
