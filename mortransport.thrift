namespace java com.bhrobotics.morcontrol.io

enum RobotMode {
  DISABLED,
  AUTONOMOUS,
  OPERATOR_CONTROL
}


enum DeviceType {
  ROBOT,
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
  DISTANCE,
  RESET
}

struct Address {
  1: i32 mod;
  2: i32 channel;
}

exception InvalidStateException {
  1: string message;
}

exception InvalidAddressException {
  1:string message
  2:Address address
}

exception InvalidCommandException {
  1:string message
  2:EncoderCommand command
}

struct Event {
  1: DeviceType type;
  2: Address address;
}

service DeviceTransport {
  RobotMode getMode();
  void initializeEncoder(1:Address address, 2:Address addressOne, 3:Address addressTwo) throws (1:InvalidAddressException error);
  void updatePWM(1:Address address, 2:i32 state) throws (1:InvalidAddressException errorLocation, 2:InvalidStateException errorState);
  void updateRelay(1:Address address, 2:RelayState state) throws (1:InvalidAddressException errorLocations);
  void updateSolenoid(1:Address address, 2:bool state) throws (1:InvalidAddressException error);
  i32 getPWM(1:Address address)  throws (1:InvalidAddressException error);
  RelayState getRelay(1:Address address)  throws (1:InvalidAddressException error);
  bool getSolenid(1:Address address)  throws (1:InvalidAddressException error);
  bool getDigitalInput(1:Address address) throws (1:InvalidAddressException error);
  double getAnalogInput(1:Address address)  throws (1:InvalidAddressException error);
  double getEncoder(1:Address addressOne, 2:EncoderCommand command)  throws (1:InvalidAddressException errorLocation, 2:InvalidCommandException errorCommand);
}

service UpdateTransport {
  Event waitForUpdate(); 
}