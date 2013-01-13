package com.bhrobotics.morcontrol.devices;

public class Address {
	private int module;
	private int channel;
	
	public Address(int module, int channel) {
		this.module = module;
		this.channel = channel;
	}
	
	public int getModule() {
		return module;
	}
	
	public int getChannel() {
		return channel;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channel;
		result = prime * result + module;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (channel != other.channel)
			return false;
		if (module != other.module)
			return false;
		return true;
	}
}
