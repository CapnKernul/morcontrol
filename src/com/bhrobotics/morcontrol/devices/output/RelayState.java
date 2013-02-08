package com.bhrobotics.morcontrol.devices.output;

import edu.wpi.first.wpilibj.Relay;

public class RelayState {
    public static final RelayState OFF = new RelayState(0);
    public static final RelayState FORWARD = new RelayState(1);
    public static final RelayState REVERSE = new RelayState(2);

    private int value;

    private RelayState(int value) {
	this.value = value;
    }

    public static RelayState fromRelayValue(Relay.Value value) {
	if (value.equals(Relay.Value.kForward)) {
	    return FORWARD;
	} else if (value.equals(Relay.Value.kReverse)) {
	    return REVERSE;
	} else {
	    return OFF;
	}
    }

    public Relay.Value toRelayValue() {
	switch (value) {
	case 1:
	    return Relay.Value.kForward;
	case 2:
	    return Relay.Value.kReverse;
	default:
	    return Relay.Value.kOff;
	}
    }
}
