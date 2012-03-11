package com.bhrobotics.morcontrol;

import org.junit.Test;

import com.bhrobotics.morcontrol.devices.output.OutputDeviceAccessor;
import com.bhrobotics.morcontrol.devices.output.RelayState;
import com.bhrobotics.morcontrol.protobuf.MotorUpdate;
import com.bhrobotics.morcontrol.protobuf.OutputUpdates;
import com.bhrobotics.morcontrol.protobuf.RelayUpdate;
import com.bhrobotics.morcontrol.protobuf.SolenoidUpdate;
import com.bhrobotics.morcontrol.support.TestCase;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OutputUpdatesAdapterTest extends TestCase {
	private OutputDeviceAccessor accessor = mock(OutputDeviceAccessor.class);
	private OutputUpdatesAdapter adapter = new OutputUpdatesAdapter(accessor);

	@Test
	public void testUpdateMotors() {
		MotorUpdate motorUpdate1 = MotorUpdate.newBuilder()
				.setModule(2)
				.setChannel(3)
				.setValue(0.3)
				.build();

		MotorUpdate motorUpdate2 = MotorUpdate.newBuilder()
				.setModule(1)
				.setChannel(2)
				.setValue(0.0)
				.build();

		OutputUpdates updates = OutputUpdates.newBuilder()
				.addElementMotor_updates(motorUpdate1)
				.addElementMotor_updates(motorUpdate2)
				.build();
		
		adapter.update(updates);
		verify(accessor).updateMotor(2, 3, 0.3);
		verify(accessor).updateMotor(1, 2, 0.0);
	}

	@Test(expected=InvalidMessageException.class)
	public void testInvalidMotorMessage() {
		MotorUpdate motorUpdate = MotorUpdate.newBuilder()
				.setModule(1)
				.setChannel(2)
				.setValue(3.0)
				.build();

		OutputUpdates updates = OutputUpdates.newBuilder()
				.addElementMotor_updates(motorUpdate)
				.build();
		
		doThrow(InvalidMessageException.class).when(accessor).updateMotor(1, 2, 3.0);
		adapter.update(updates);
	}

	@Test
	public void testUpdateRelay() {
		RelayUpdate relayUpdate1 = RelayUpdate.newBuilder()
				.setModule(4)
				.setChannel(1)
				.setValue(RelayUpdate.RelayValue.FORWARD)
				.build();

		RelayUpdate relayUpdate2 = RelayUpdate.newBuilder()
				.setModule(3)
				.setChannel(2)
				.setValue(RelayUpdate.RelayValue.REVERSE)
				.build();

		RelayUpdate relayUpdate3 = RelayUpdate.newBuilder()
				.setModule(2)
				.setChannel(3)
				.setValue(RelayUpdate.RelayValue.OFF)
				.build();
		
		OutputUpdates updates = OutputUpdates.newBuilder()
				.addElementRelay_updates(relayUpdate1)
				.addElementRelay_updates(relayUpdate2)
				.addElementRelay_updates(relayUpdate3)
				.build();
		
		adapter.update(updates);
		verify(accessor).updateRelay(4, 1, RelayState.FORWARD);
		verify(accessor).updateRelay(3, 2, RelayState.REVERSE);
		verify(accessor).updateRelay(2, 3, RelayState.OFF);
	}

	@Test
	public void testUpdateSolenoid() {
		SolenoidUpdate solenoidUpdate1 = SolenoidUpdate.newBuilder()
				.setModule(2)
				.setChannel(1)
				.setValue(true)
				.build();

		SolenoidUpdate solenoidUpdate2 = SolenoidUpdate.newBuilder()
				.setModule(5)
				.setChannel(2)
				.setValue(false)
				.build();
		
		OutputUpdates updates = OutputUpdates.newBuilder()
				.addElementSolenoid_updates(solenoidUpdate1)
				.addElementSolenoid_updates(solenoidUpdate2)
				.build();

		adapter.update(updates);
		verify(accessor).updateSolenoid(2, 1, true);
		verify(accessor).updateSolenoid(5, 2, false);
	}
}
