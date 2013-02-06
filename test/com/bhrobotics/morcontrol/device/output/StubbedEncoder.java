package com.bhrobotics.morcontrol.device.output;

import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.input.Encoder;

public class StubbedEncoder implements Encoder {
	private Address address;
	private int count = 0;
	private int rate = 0;
	private int distance = 0;
	
	public StubbedEncoder (Address address) {
		this.address = address;
	}

	@Override
	public Address getAddress() {
		System.out.println("Encoder address is " + address + ".");
		return address;
	}

	@Override
	public void reset() {
		System.out.println("Encoder has been reset.");
	}

	@Override
	public int getCount() {
		System.out.println("Encoder count is" + count + ".");
		return count;
	}

	@Override
	public double getRate() {
		System.out.println("Encoder rate is" + rate + ".");
		return rate;
	}

	@Override
	public double getDistance() {
		System.out.println("Encoder distance is" + distance + ".");
		return distance;
	}
}
