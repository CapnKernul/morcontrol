package com.bhrobotics.morcontrol;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.bhrobotics.morcontrol.output.DeviceUpdater;
import com.bhrobotics.morcontrol.support.TestCase;

public class RobotTest extends TestCase {
	private DeviceUpdater updater = mock(DeviceUpdater.class);
	private Robot robot = new Robot(updater);
	
	@Test
	public void testSwitchMode() {
		assertEquals(Robot.Mode.DISABLED, robot.getMode());
		robot.switchMode(Robot.Mode.AUTONOMOUS);
		assertEquals(Robot.Mode.AUTONOMOUS, robot.getMode());
		robot.switchMode(Robot.Mode.OPERATOR_CONTROL);
		assertEquals(Robot.Mode.OPERATOR_CONTROL, robot.getMode());
		robot.switchMode(Robot.Mode.OPERATOR_CONTROL);
		assertEquals(Robot.Mode.OPERATOR_CONTROL, robot.getMode());
		robot.switchMode(Robot.Mode.DISABLED);
		assertEquals(Robot.Mode.DISABLED, robot.getMode());
	}
	
	@Test
	public void testUpdate() {
		robot.switchMode(Robot.Mode.OPERATOR_CONTROL);
		verify(updater).reset();
	}
}
