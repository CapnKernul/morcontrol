package com.bhrobotics.morcontrol;

import org.junit.Test;

import com.bhrobotics.morcontrol.oi.messages.BooleanMessage;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.NumberMessage;
import com.bhrobotics.morcontrol.oi.messages.StringMessage;
import com.bhrobotics.morcontrol.output.AnalogState;
import com.bhrobotics.morcontrol.output.DeviceUpdater;
import com.bhrobotics.morcontrol.output.DigitalState;
import com.bhrobotics.morcontrol.output.RelayState;
import com.bhrobotics.morcontrol.support.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OutputMessageAdapterTest extends TestCase {
	private DeviceUpdater updater = mock(DeviceUpdater.class);
	private OutputMessageAdapter adapter = new OutputMessageAdapter(updater);

	@Test
	public void testUpdateMotor() {
		Message motorMessage1 = (Message) new NumberMessage("motor 2:3", 0.3);
		Message motorMessage2 = (Message) new NumberMessage("motor 1:2", 0.0);

		adapter.update(motorMessage1);
		adapter.update(motorMessage2);
		verify(updater).updateMotor(2, 3, new AnalogState(0.3));
		verify(updater).updateMotor(1, 2, new AnalogState(0.0));
	}

	@Test(expected=InvalidMessageException.class)
	public void testInvalidMotorMessage() {
		Message message = (Message) new NumberMessage("motor 2:3", 5.5);
		adapter.update(message);
	}

	@Test
	public void testUpdateRelay() {
		Message relayMessage1 = (Message) new StringMessage("relay 4:1", "f");
		Message relayMessage2 = (Message) new StringMessage("relay 3:2", "r");
		Message relayMessage3 = (Message) new StringMessage("relay 2:3", "o");

		adapter.update(relayMessage1);
		adapter.update(relayMessage2);
		adapter.update(relayMessage3);
		verify(updater).updateRelay(4, 1, RelayState.FORWARD);
		verify(updater).updateRelay(3, 2, RelayState.REVERSE);
		verify(updater).updateRelay(2, 3, RelayState.OFF);
	}

	@Test(expected=InvalidMessageException.class)
	public void testInvalidRelayMessage() {
		Message message = (Message) new StringMessage("relay 2:3", "e");
		adapter.update(message);
	}

	@Test
	public void testUpdateSolenoid() {
		Message solenoidMessage1 = (Message) new BooleanMessage("solenoid 2:1", true);
		Message solenoidMessage2 = (Message) new BooleanMessage("solenoid 5:2", false);

		adapter.update(solenoidMessage1);
		adapter.update(solenoidMessage2);
		verify(updater).updateSolenoid(2, 1, DigitalState.ON);
		verify(updater).updateSolenoid(5, 2, DigitalState.OFF);
	}
}
