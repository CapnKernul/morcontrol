package com.bhrobotics.morcontrol.devices;

import com.bhrobotics.morcontrol.util.CausableException;

public class InvalidAddressException extends CausableException {

    private static final long serialVersionUID = 5923413528342878113L;
    private Address address;

    public InvalidAddressException(Address address) {
	this("Address error with " + address.toString(), address);
    }

    public InvalidAddressException(String s, Address address) {
	super(s);
	this.address = address;
    }

    public InvalidAddressException(Throwable cause, Address address) {
	this("Address error with " + address.toString(), cause, address);
    }

    public InvalidAddressException(String s, Throwable cause, Address address) {
	super(s, cause);
	this.address = address;
    }

    public Address getAddress() {
	return address;
    }
}
