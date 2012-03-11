package com.bhrobotics.morcontrol;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bhrobotics.morcontrol.oi.OIConnection;
import com.bhrobotics.morcontrol.protobuf.InputUpdates;
import com.bhrobotics.morcontrol.protobuf.OutputUpdates;
import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.io.Logger;

public class RobotTest extends TestCase {
	private OIConnection connection = mock(OIConnection.class);
	private InputUpdatesAdapter inputAdapter = mock(InputUpdatesAdapter.class);
	private OutputUpdatesAdapter outputAdapter = mock(OutputUpdatesAdapter.class);
	private Logger logger = mock(Logger.class);
	private Robot robot = new Robot(connection, inputAdapter, outputAdapter, logger);
	
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
	public void testConnectionOpened() {
		InputUpdates updates = mock(InputUpdates.class);
		when(inputAdapter.getAllInputs()).thenReturn(updates);
		robot.connectionOpened();
		verify(connection).write(updates);
	}

	@Test
	public void testConnectionClosed() {
		robot.connectionClosed();
		verify(outputAdapter).reset();
	}
	
	@Test
	public void testUpdateOutputs() {
		OutputUpdates updates = mock(OutputUpdates.class);
		when(connection.read()).thenReturn(updates);
		robot.updateOutputs();
		verify(outputAdapter).update(updates);
	}

	@Test
	public void testUpdateInputs() {
		InputUpdates updates = mock(InputUpdates.class);
		when(inputAdapter.getUpdatedInputs()).thenReturn(updates);
		robot.updateInputs();
		verify(connection).write(updates);
	}
}
