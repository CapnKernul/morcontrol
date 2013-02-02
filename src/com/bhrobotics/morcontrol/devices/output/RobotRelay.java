package com.bhrobotics.morcontrol.devices.output;

import com.bhrobotics.morcontrol.devices.Address;

public class RobotRelay implements Relay {
	
	private Address address;
	private edu.wpi.first.wpilibj.Relay relay;
	private RelayState state = DEFAULT_STATE;
	
	public RobotRelay(Address address) {
		this.address = address;
		relay = new edu.wpi.first.wpilibj.Relay(address.getModule(), address.getChannel());
	}
	
	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Relay#update(com.bhrobotics.morcontrol.devices.output.RelayState)
	 */
	public void update(RelayState state) {
		this.state = state;
		relay.set(state.toRelayValue());
	}
	
	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Relay#reset()
	 */
	public void reset() {
		update(DEFAULT_STATE);
	}
	
	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Relay#getAddress()
	 */
	public Address getAddress() {
		return address;
	}
	
	/* (non-Javadoc)
	 * @see com.bhrobotics.morcontrol.devices.output.Relay#getState()
	 */
	public RelayState getState() {
		return state;
	}
}
