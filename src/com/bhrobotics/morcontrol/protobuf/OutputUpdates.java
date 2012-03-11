package com.bhrobotics.morcontrol.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;

public class OutputUpdates extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final java.util.Vector motor_updates;
	private static final int fieldNumberMotor_updates = 1;

	private final java.util.Vector relay_updates;
	private static final int fieldNumberRelay_updates = 2;

	private final java.util.Vector solenoid_updates;
	private static final int fieldNumberSolenoid_updates = 3;


	public static Builder newBuilder() {
		return new Builder();
	}

	private OutputUpdates(final Builder builder) {
		this.motor_updates = builder.motor_updates;
		this.relay_updates = builder.relay_updates;
		this.solenoid_updates = builder.solenoid_updates;
	}

	public static class RelayValue {
		public static int OFF = 0;
		public static int FORWARD = 1;
		public static int REVERSE = 2;

		public static String getStringValue(int value) {
			String retValue;

			switch(value) {
				case 0:
					retValue = "OFF";
					break;
				case 1:
					retValue = "FORWARD";
					break;
				case 2:
					retValue = "REVERSE";
					break;
				default:
					retValue = "";
					break;
			}

			return retValue;
		}
	}

	public static class Builder {
		private java.util.Vector motor_updates = new java.util.Vector();
		private boolean hasMotor_updates = false;

		private java.util.Vector relay_updates = new java.util.Vector();
		private boolean hasRelay_updates = false;

		private java.util.Vector solenoid_updates = new java.util.Vector();
		private boolean hasSolenoid_updates = false;


		private Builder() {
		}

		public Builder setMotor_updates(final java.util.Vector motor_updates) {
			if(!hasMotor_updates) {
				hasMotor_updates = true;
			}
			this.motor_updates = motor_updates;
			return this;
		}


		public Builder addElementMotor_updates(final MotorUpdate element) {
			if(!hasMotor_updates) {
				hasMotor_updates = true;
			}
			motor_updates.addElement(element);
			return this;
		}

		public Builder setRelay_updates(final java.util.Vector relay_updates) {
			if(!hasRelay_updates) {
				hasRelay_updates = true;
			}
			this.relay_updates = relay_updates;
			return this;
		}


		public Builder addElementRelay_updates(final RelayUpdate element) {
			if(!hasRelay_updates) {
				hasRelay_updates = true;
			}
			relay_updates.addElement(element);
			return this;
		}

		public Builder setSolenoid_updates(final java.util.Vector solenoid_updates) {
			if(!hasSolenoid_updates) {
				hasSolenoid_updates = true;
			}
			this.solenoid_updates = solenoid_updates;
			return this;
		}


		public Builder addElementSolenoid_updates(final SolenoidUpdate element) {
			if(!hasSolenoid_updates) {
				hasSolenoid_updates = true;
			}
			solenoid_updates.addElement(element);
			return this;
		}

		public OutputUpdates build() {
			return new OutputUpdates(this);
		}
	}

	public java.util.Vector getMotor_updates() {
		return motor_updates;
	}

	public java.util.Vector getRelay_updates() {
		return relay_updates;
	}

	public java.util.Vector getSolenoid_updates() {
		return solenoid_updates;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "motor_updates = " + this.motor_updates + TAB;
		retValue += "relay_updates = " + this.relay_updates + TAB;
		retValue += "solenoid_updates = " + this.solenoid_updates + TAB;
		retValue += ")";

		return retValue;
	}

	// Override
	public int computeSize() {
		int totalSize = 0;
		totalSize += computeNestedMessageSize();

		return totalSize;
	}

	private int computeNestedMessageSize() {
		int messageSize = 0;
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberMotor_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, motor_updates);
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberRelay_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, relay_updates);
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberSolenoid_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, solenoid_updates);

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeList(fieldNumberMotor_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, motor_updates);
		writer.writeList(fieldNumberRelay_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, relay_updates);
		writer.writeList(fieldNumberSolenoid_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, solenoid_updates);
		writer.writeData();
	}

	static OutputUpdates parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final OutputUpdates.Builder builder = OutputUpdates.newBuilder();

		while (nextFieldNumber > 0) {
			if(!populateBuilderWithField(reader, builder, nextFieldNumber)) {
				reader.getPreviousTagDataTypeAndReadContent();
			}
			nextFieldNumber = getNextFieldNumber(reader);
		}

		return builder.build();
	}

	static int getNextFieldNumber(final InputReader reader) throws IOException {
		return reader.getNextFieldNumber();
	}

	static boolean populateBuilderWithField(final InputReader reader, final Builder builder, final int fieldNumber) throws IOException {
		boolean fieldFound = true;
		switch (fieldNumber) {
			case fieldNumberMotor_updates:
				Vector vcMotor_updates = reader.readMessages(fieldNumberMotor_updates);
				for(int i = 0 ; i < vcMotor_updates.size(); i++) {
					byte[] eachBinData = (byte[]) vcMotor_updates.elementAt(i);
					MotorUpdate.Builder builderMotor_updates = MotorUpdate.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolMotor_updates = true;
					int nestedFieldMotor_updates = -1;
					while(boolMotor_updates) {
						nestedFieldMotor_updates = getNextFieldNumber(innerInputReader);
						boolMotor_updates = MotorUpdate.populateBuilderWithField(innerInputReader, builderMotor_updates, nestedFieldMotor_updates);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementMotor_updates(builderMotor_updates.build());
				}
				break;
			case fieldNumberRelay_updates:
				Vector vcRelay_updates = reader.readMessages(fieldNumberRelay_updates);
				for(int i = 0 ; i < vcRelay_updates.size(); i++) {
					byte[] eachBinData = (byte[]) vcRelay_updates.elementAt(i);
					RelayUpdate.Builder builderRelay_updates = RelayUpdate.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolRelay_updates = true;
					int nestedFieldRelay_updates = -1;
					while(boolRelay_updates) {
						nestedFieldRelay_updates = getNextFieldNumber(innerInputReader);
						boolRelay_updates = RelayUpdate.populateBuilderWithField(innerInputReader, builderRelay_updates, nestedFieldRelay_updates);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementRelay_updates(builderRelay_updates.build());
				}
				break;
			case fieldNumberSolenoid_updates:
				Vector vcSolenoid_updates = reader.readMessages(fieldNumberSolenoid_updates);
				for(int i = 0 ; i < vcSolenoid_updates.size(); i++) {
					byte[] eachBinData = (byte[]) vcSolenoid_updates.elementAt(i);
					SolenoidUpdate.Builder builderSolenoid_updates = SolenoidUpdate.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolSolenoid_updates = true;
					int nestedFieldSolenoid_updates = -1;
					while(boolSolenoid_updates) {
						nestedFieldSolenoid_updates = getNextFieldNumber(innerInputReader);
						boolSolenoid_updates = SolenoidUpdate.populateBuilderWithField(innerInputReader, builderSolenoid_updates, nestedFieldSolenoid_updates);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementSolenoid_updates(builderSolenoid_updates.build());
				}
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		OutputUpdates.unknownTagHandler = unknownTagHandler;
	}

	public static OutputUpdates parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static OutputUpdates parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static OutputUpdates parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}