package com.bhrobotics.morcontrol.devices.registry;

import java.util.Enumeration;
import java.util.Vector;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.tracking.DeviceObserver;
import com.bhrobotics.morcontrol.devices.tracking.Ticker;
import com.bhrobotics.morcontrol.io.Mailbox;
import com.bhrobotics.morcontrol.util.collections.HashMap;

public class EncoderRegistry implements Registry {
	private HashMap encoders = new HashMap();
	private Vector usedDigitalInputs = new Vector();
	private DeviceObserver mailbox;
	private Ticker ticker;

	public EncoderRegistry(DeviceObserver mailboxe, Ticker ticker) {
		mailbox = mailboxe;
		this.ticker = ticker;
	}

	public void initializeEncoder(Address address, DigitalInput inputOne, DigitalInput inputTwo) throws InvalidAddressException {
		if (encoders.containsKey(address))
			throw new InvalidAddressException(address);
		if (usedDigitalInputs.contains(inputOne))
			throw new InvalidAddressException(inputOne.getAddress());
		if (usedDigitalInputs.contains(inputTwo))
			throw new InvalidAddressException(inputTwo.getAddress());

		usedDigitalInputs.addElement(inputOne);
		usedDigitalInputs.addElement(inputTwo);
		Encoder encoder = new Encoder(address, inputOne, inputTwo);
		encoder.addObserver(mailbox);
		ticker.addTickable(encoder);
		encoders.put(address, encoder);
	}

	public Device getDevice(Address address) throws InvalidAddressException {
		if (encoders.containsKey(address)) {
			return (Device) encoders.get(address);
		}
		throw new InvalidAddressException(address);
	}

	public Enumeration getAllDevices() {
		return encoders.keys();
	}

	public void reset() {
		encoders.clear();
		usedDigitalInputs.removeAllElements();
	}
}