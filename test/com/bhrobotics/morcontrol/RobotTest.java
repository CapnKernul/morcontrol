package com.bhrobotics.morcontrol;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bhrobotics.morcontrol.oi.OIConnection;
import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.support.TestCase;

public class RobotTest extends TestCase {
	private OIConnection connection = mock(OIConnection.class);
	private OutputMessageAdapter adapter = mock(OutputMessageAdapter.class);
	private Robot robot = new Robot(connection, adapter);
	
	@Test
	public void testConnectionObserver() {
		verify(connection).registerObserver(robot);
	}
	
	@Test
	public void testSwitchMode() {
		assertEquals(RobotMode.DISABLED, robot.getMode());
		robot.switchMode(RobotMode.AUTONOMOUS);
		assertEquals(RobotMode.AUTONOMOUS, robot.getMode());
		robot.switchMode(RobotMode.OPERATOR_CONTROL);
		assertEquals(RobotMode.OPERATOR_CONTROL, robot.getMode());
		robot.switchMode(RobotMode.OPERATOR_CONTROL);
		assertEquals(RobotMode.OPERATOR_CONTROL, robot.getMode());
		robot.switchMode(RobotMode.DISABLED);
		assertEquals(RobotMode.DISABLED, robot.getMode());
	}
	
	@Test
	public void testReset() {
		robot.connectionClosed();
		verify(adapter).reset();
	}

	@Test
	public void testUpdateOutputs() {
		Message message1 = mock(Message.class);
		Message message2 = mock(Message.class);
		
		robot.switchMode(RobotMode.OPERATOR_CONTROL);
		when(connection.read()).thenReturn(new Message[] { message1, message2 });
		robot.updateOutputs();
		verify(adapter).update(message1);
		verify(adapter).update(message2);
	}
}
