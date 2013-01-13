namespace java com.bhrobotics.morcontrol.io

enum RobotMode {
  DISABLED,
  AUTONOMOUS,
  OPERATOR_CONTROL
}

enum RelayState {
  FORWARD,
  REVERSE,
  OFF
}

struct Address {
  1: i32 mod;
  2: i32 channel;
}

exception InvalidStateException {
  1: string message;
}

service MorTransport {
  RobotMode getMode();
  void updateMotor(1:Address address, 2:double state) throws (1:InvalidStateException error);
  void updateRelay(1:Address address, 2:RelayState state);
  void updateSolenoid(1:Address address, 2:bool state);
}
