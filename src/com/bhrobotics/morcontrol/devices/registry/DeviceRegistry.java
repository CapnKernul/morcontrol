package com.bhrobotics.morcontrol.devices.registry;

import com.bhrobotics.morcontrol.OIServerObserver;
import com.bhrobotics.morcontrol.devices.Address;
import com.bhrobotics.morcontrol.devices.Device;
import com.bhrobotics.morcontrol.devices.DeviceType;
import com.bhrobotics.morcontrol.devices.InvalidAddressException;
import com.bhrobotics.morcontrol.devices.input.AnalogInput;
import com.bhrobotics.morcontrol.devices.input.DigitalInput;
import com.bhrobotics.morcontrol.devices.input.Encoder;
import com.bhrobotics.morcontrol.devices.output.Motor;
import com.bhrobotics.morcontrol.devices.output.Relay;
import com.bhrobotics.morcontrol.devices.output.Solenoid;
import com.bhrobotics.morcontrol.devices.tracking.Ticker;
import com.bhrobotics.morcontrol.io.Mailbox;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class DeviceRegistry {
	private static final int PWM_COUNT = 10;
	private static final int SOLENOID_COUNT = 8;
	private static final int DIGITAL_COUNT = 14;
	private static final int ANALOG_COUNT = 7;
	private static final int RELAY_COUNT = 8;
	private Mailbox mailboxe = new Mailbox();
	private Ticker ticker = new Ticker();

	private ModuleMapping mapping = ModuleMapping.getInstance();

	private BasicDeviceIndex analogs = new BasicDeviceIndex(new DeviceFactory() {
		public Device makeDevice(Address address) {
			AnalogInput input = new AnalogInput(address);
			input.addObserver(mailboxe);
			ticker.addTickable(input);
			return input;
		}
	});
	private BasicDeviceIndex digitals = new BasicDeviceIndex(new DeviceFactory() {
		public Device makeDevice(Address address) {
			DigitalInput input = new DigitalInput(address);
			input.addObserver(mailboxe);
			ticker.addTickable(input);
			return input;
		}
	});
	private BasicDeviceIndex pwms = new BasicDeviceIndex(new DeviceFactory() {
		public Device makeDevice(Address address) {
			return new Motor(address);
		}
	});
	private BasicDeviceIndex solenoids = new BasicDeviceIndex(new DeviceFactory() {
		public Device makeDevice(Address address) {
			return new Solenoid(address);
		}
	});
	private BasicDeviceIndex relays = new BasicDeviceIndex(new DeviceFactory() {
		public Device makeDevice(Address address) {
			return new Relay(address);
		}
	});
	private EncoderRegistry encoders = new EncoderRegistry(mailboxe, ticker);

	public ModuleMapping getMapping() {
		return mapping;
	}

	private void initializeDeviceIndex(BasicDeviceIndex index, int moduleCount, int channelCount) throws InvalidAddressException {
		for (int module = 1; module <= moduleCount; module++) {
			for (int channel = 1; channel <= channelCount; channel++) {
				index.makeDevice(new Address(module, channel));
			}
		}
	}

	public DeviceRegistry() throws InvalidAddressException {
		initializeDeviceIndex(pwms, mapping.getDigitalModuleCount(), PWM_COUNT);
		initializeDeviceIndex(relays, mapping.getDigitalModuleCount(), RELAY_COUNT);
		initializeDeviceIndex(digitals, mapping.getDigitalModuleCount(), DIGITAL_COUNT);
		initializeDeviceIndex(analogs, mapping.getAnalogModuleCount(), ANALOG_COUNT);
		initializeDeviceIndex(solenoids, mapping.getSolenoidModuleCount(), SOLENOID_COUNT);
	}

	public Motor getPWM(Address address) throws InvalidAddressException {
		return (Motor) pwms.getDevice(address);
	}

	public Solenoid getSolenoid(Address address) throws InvalidAddressException {
		return (Solenoid) solenoids.getDevice(address);
	}

	public Relay getRelay(Address address) throws InvalidAddressException {
		return (Relay) relays.getDevice(address);
	}

	public DigitalInput getDigital(Address address) throws InvalidAddressException {
		return (DigitalInput) digitals.getDevice(address);
	}

	public AnalogInput getAnalog(Address address) throws InvalidAddressException {
		return (AnalogInput) analogs.getDevice(address);
	}

	public Encoder getEncoder(Address address) throws InvalidAddressException {
		return (Encoder) encoders.getDevice(address);
	}

	public void initializeEncoder(Address address, Address inputOne, Address inputTwo) throws InvalidAddressException {
		DigitalInput deviceOne = getDigital(inputOne);
		DigitalInput deviceTwo = getDigital(inputTwo);
		encoders.initializeEncoder(address, deviceOne, deviceTwo);
	}

	public Registry getRegistry(DeviceType type) {
		if (type == DeviceType.ANALOG_INPUT) {
			return analogs;
		} else if (type == DeviceType.DIGITAL_INPUT) {
			return digitals;
		} else if (type == DeviceType.ENCODER) {
			return encoders;
		} else if (type == DeviceType.PWM) {
			return pwms;
		} else if (type == DeviceType.SOLENOID) {
			return solenoids;
		} else if (type == DeviceType.RELAY) {
			return relays;
		} else {
			return null;
		}
	}

	public Mailbox getMailbox() {
		return mailboxe;
	}

	public void start() {
		ticker.start();
		Logger.defaultLogger.debug("Ticker started");
	}

	public void stop() {
		analogs.reset();
		digitals.reset();
		encoders.reset();
		pwms.reset();
		solenoids.reset();
		relays.reset();
		ticker.stop();
		mailboxe.clear();
		Logger.defaultLogger.debug("Ticker stopped");
	}
}