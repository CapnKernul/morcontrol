package com.bhrobotics.morcontrol.devices.modules;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;

public class EncoderRegistry implements Registry {

    public Type getType() {
	return Registry.Type.ENCODER;
    }

    public int getChannelsPerModule() {
	return 7;
    }

    public Device initializeDevice(Address address) {
	// TODO Auto-generated method stub
	return null;
    }

}
