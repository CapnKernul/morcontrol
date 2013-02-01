package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;

public class RobotSolenoid implements Solenoid {
	public static final boolean DEFAULT_STATE = false;
	
	private Address address;
	private edu.wpi.first.wpilibj.Solenoid solenoid;
	
	public RobotSolenoid(Address address) {
		this.address = address;
		solenoid = new edu.wpi.first.wpilibj.Solenoid(address.getModule(), address.getChannel());
	}
	
	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Solenoid#update(boolean)
	 */
	public void update(boolean state) {
		solenoid.set(state);
	}

	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Solenoid#reset()
	 */
	public void reset() {
		update(DEFAULT_STATE);
	}
	
	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Solenoid#getAddress()
	 */
	public Address getAddress() {
		return address;
	}
	
	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Solenoid#getState()
	 */
	public boolean getState() {
		return solenoid.get();
	}
}
