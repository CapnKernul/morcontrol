package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;

public interface Relay {
	public static final RelayState DEFAULT_STATE = RelayState.OFF;
	
    public abstract void update(RelayState state);

    public abstract void reset();

    public abstract Address getAddress();

    public abstract RelayState getState();

}