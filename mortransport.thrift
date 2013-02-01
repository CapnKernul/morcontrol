namespace java com.bhrobotics.morcontrol.io

enum RobotMode {
  DISABLED,
  AUTONOMOUS,
  OPERATOR_CONTROL
}


enum DeviceType {
  PWM,
  SOLENOID,
  RELAY,
  DIGITAL_INPUT,
  ANALOG_INPUT,
  ENCODER
}

enum RelayState {
  FORWARD,
  REVERSE,
  OFF
}

enum EncoderCommand {
  COUNT,
  RATE,
  DISTANCE
}

struct Address {
  1: i32 mod;
  2: i32 channel;
}

exception InvalidStateException {
  1: string message;
}

struct Event {
  1: DeviceType type;
  2: Address address;
}

service DeviceTransport {
  RobotMode getMode();
  void initializeEncoder(1:Address addressOne, 2:Address addressTwo);
  void updatePWM(1:Address address, 2:i32 state) throws (1:InvalidStateException error);
  void updateRelay(1:Address address, 2:RelayState state);
  void updateSolenoid(1:Address address, 2:bool state);
  i32 getPWM(1:Address address)
  RelayState getRelay(1:Address address)
  bool getSolenid(1:Address address)
  bool getDigitalInput(1:Address address)
  double getAnalogInput(1:Address address)
  double getEncoder(1:Address addressOne, 2:EncoderCommand command)
}

service UpdateTransport {
  Event waitForUpdate(); 
}