/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.bhrobotics.morcontrol.io;

public class RelayState implements org.apache.thrift.TEnum {

	public static final RelayState FORWARD = new RelayState(0);
	public static final RelayState REVERSE = new RelayState(1);
	public static final RelayState OFF = new RelayState(2);

	private final int value;

	private RelayState(int value) {
		this.value = value;
	}

	/**
	 * Get the integer value of this enum value, as defined in the Thrift IDL.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Find a the enum type by its integer value, as defined in the Thrift IDL.
	 * 
	 * @return null if the value is not found.
	 */
	public static RelayState findByValue(int value) {
		switch (value) {
		case 0:
			return FORWARD;
		case 1:
			return REVERSE;
		case 2:
			return OFF;
		default:
			return null;
		}
	}
}
