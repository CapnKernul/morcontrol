package com.bhrobotics.morcontrol;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.bhrobotics.morcontrol.devices.input.AnalogInputDevice;
import com.bhrobotics.morcontrol.devices.input.DigitalInputDevice;
import com.bhrobotics.morcontrol.devices.input.InputDeviceAccessor;
import com.bhrobotics.morcontrol.devices.input.StubAnalogInputDevice;
import com.bhrobotics.morcontrol.devices.input.StubDigitalInputDevice;
import com.bhrobotics.morcontrol.protobuf.AnalogInputUpdate;
import com.bhrobotics.morcontrol.protobuf.DigitalInputUpdate;
import com.bhrobotics.morcontrol.protobuf.InputUpdates;
import com.bhrobotics.morcontrol.support.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.is;

public class InputUpdatesAdapterTest extends TestCase {
	private InputDeviceAccessor accessor = mock(InputDeviceAccessor.class);
	private StubDigitalInputDevice digitalInput1 = new StubDigitalInputDevice(2, 1);
	private StubDigitalInputDevice digitalInput2 = new StubDigitalInputDevice(4, 2);
	private StubAnalogInputDevice analogInput1 = new StubAnalogInputDevice(1, 1);
	private StubAnalogInputDevice analogInput2 = new StubAnalogInputDevice(3, 2);
	private DigitalInputDevice[] digitalInputs = new DigitalInputDevice[] { digitalInput1, digitalInput2 };
	private AnalogInputDevice[] analogInputs = new AnalogInputDevice[] { analogInput1, analogInput2 };
	private InputUpdatesAdapter adapter = new InputUpdatesAdapter(accessor);
	
	@Before
	public void setUp() {
		when(accessor.getDigitalInputs()).thenReturn(digitalInputs);
		when(accessor.getAnalogInputs()).thenReturn(analogInputs);
	}
	
	@Test
	public void testGetAllInputs() {
		digitalInput1.setState(true);
		digitalInput2.setState(false);
		analogInput1.setState(0.2);
		analogInput2.setState(-1);
		
		digitalInput1.hasChanged();
		digitalInput2.hasChanged();
		analogInput1.hasChanged();
		analogInput2.hasChanged();
		
		InputUpdates updates = adapter.getAllInputs();
		Vector digitalInputUpdates = updates.getDigital_input_updates();
		Vector analogInputUpdates = updates.getAnalog_input_updates();
		
		assertThat(digitalInputUpdates.size(), is(2));
		assertThat(analogInputUpdates.size(), is(2));
		
		DigitalInputUpdate digitalInputUpdate1 = (DigitalInputUpdate) digitalInputUpdates.elementAt(0);
		assertThat(digitalInputUpdate1.getModule(), is(2));
		assertThat(digitalInputUpdate1.getChannel(), is(1));
		assertThat(digitalInputUpdate1.getValue(), is(true));
		
		DigitalInputUpdate digitalInputUpdate2 = (DigitalInputUpdate) digitalInputUpdates.elementAt(1);
		assertThat(digitalInputUpdate2.getModule(), is(4));
		assertThat(digitalInputUpdate2.getChannel(), is(2));
		assertThat(digitalInputUpdate2.getValue(), is(false));
		
		AnalogInputUpdate analogInputUpdate1 = (AnalogInputUpdate) analogInputUpdates.elementAt(0);
		assertThat(analogInputUpdate1.getModule(), is(1));
		assertThat(analogInputUpdate1.getChannel(), is(1));
		assertThat(analogInputUpdate1.getValue(), is(0.2));
		
		AnalogInputUpdate analogInputUpdate2 = (AnalogInputUpdate) analogInputUpdates.elementAt(1);
		assertThat(analogInputUpdate2.getModule(), is(3));
		assertThat(analogInputUpdate2.getChannel(), is(2));
		assertThat(analogInputUpdate2.getValue(), is(-1.0));
	}

	@Test
	public void testGetUpdatedInputs() {
		digitalInput1.setState(true);
		analogInput2.setState(-1);
		
		InputUpdates updates = adapter.getUpdatedInputs();
		Vector digitalInputUpdates = updates.getDigital_input_updates();
		Vector analogInputUpdates = updates.getAnalog_input_updates();
		
		assertThat(digitalInputUpdates.size(), is(1));
		assertThat(analogInputUpdates.size(), is(1));
		
		DigitalInputUpdate digitalInputUpdate1 = (DigitalInputUpdate) digitalInputUpdates.elementAt(0);
		assertThat(digitalInputUpdate1.getModule(), is(2));
		assertThat(digitalInputUpdate1.getChannel(), is(1));
		assertThat(digitalInputUpdate1.getValue(), is(true));
		
		AnalogInputUpdate analogInputUpdate2 = (AnalogInputUpdate) analogInputUpdates.elementAt(0);
		assertThat(analogInputUpdate2.getModule(), is(3));
		assertThat(analogInputUpdate2.getChannel(), is(2));
		assertThat(analogInputUpdate2.getValue(), is(-1.0));
	}
}
