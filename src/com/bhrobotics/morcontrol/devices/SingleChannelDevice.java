package com.bhrobotics.morcontrol.devices;

public abstract class SingleChannelDevice {
	private int module;
	private int channel;
	
	public SingleChannelDevice(int module, int channel) {
		this.module = module;
		this.channel = channel;
	}

	public int getModule() {
		return module;
	}

	public int getChannel() {
		return channel;
	}
}
