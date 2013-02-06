package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;

public interface Relay extends Device {
    
    public static final RelayState DEFAULT_STATE = RelayState.OFF;

    public void update(RelayState state);

    public void reset();

    public Address getAddress();

    public RelayState getState();

}