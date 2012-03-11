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

public class InputUpdates extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final java.util.Vector digital_input_updates;
	private static final int fieldNumberDigital_input_updates = 1;

	private final java.util.Vector analog_input_updates;
	private static final int fieldNumberAnalog_input_updates = 2;


	public static Builder newBuilder() {
		return new Builder();
	}

	private InputUpdates(final Builder builder) {
		this.digital_input_updates = builder.digital_input_updates;
		this.analog_input_updates = builder.analog_input_updates;
	}

	public static class Builder {
		private java.util.Vector digital_input_updates = new java.util.Vector();
		private boolean hasDigital_input_updates = false;

		private java.util.Vector analog_input_updates = new java.util.Vector();
		private boolean hasAnalog_input_updates = false;


		private Builder() {
		}

		public Builder setDigital_input_updates(final java.util.Vector digital_input_updates) {
			if(!hasDigital_input_updates) {
				hasDigital_input_updates = true;
			}
			this.digital_input_updates = digital_input_updates;
			return this;
		}


		public Builder addElementDigital_input_updates(final DigitalInputUpdate element) {
			if(!hasDigital_input_updates) {
				hasDigital_input_updates = true;
			}
			digital_input_updates.addElement(element);
			return this;
		}

		public Builder setAnalog_input_updates(final java.util.Vector analog_input_updates) {
			if(!hasAnalog_input_updates) {
				hasAnalog_input_updates = true;
			}
			this.analog_input_updates = analog_input_updates;
			return this;
		}


		public Builder addElementAnalog_input_updates(final AnalogInputUpdate element) {
			if(!hasAnalog_input_updates) {
				hasAnalog_input_updates = true;
			}
			analog_input_updates.addElement(element);
			return this;
		}

		public InputUpdates build() {
			return new InputUpdates(this);
		}
	}

	public java.util.Vector getDigital_input_updates() {
		return digital_input_updates;
	}

	public java.util.Vector getAnalog_input_updates() {
		return analog_input_updates;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "digital_input_updates = " + this.digital_input_updates + TAB;
		retValue += "analog_input_updates = " + this.analog_input_updates + TAB;
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
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberDigital_input_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, digital_input_updates);
		messageSize += ComputeSizeUtil.computeListSize(fieldNumberAnalog_input_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, analog_input_updates);

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeList(fieldNumberDigital_input_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, digital_input_updates);
		writer.writeList(fieldNumberAnalog_input_updates, net.jarlehansen.protobuf.javame.SupportedDataTypes.DATA_TYPE_CUSTOM, analog_input_updates);
		writer.writeData();
	}

	static InputUpdates parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final InputUpdates.Builder builder = InputUpdates.newBuilder();

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
			case fieldNumberDigital_input_updates:
				Vector vcDigital_input_updates = reader.readMessages(fieldNumberDigital_input_updates);
				for(int i = 0 ; i < vcDigital_input_updates.size(); i++) {
					byte[] eachBinData = (byte[]) vcDigital_input_updates.elementAt(i);
					DigitalInputUpdate.Builder builderDigital_input_updates = DigitalInputUpdate.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolDigital_input_updates = true;
					int nestedFieldDigital_input_updates = -1;
					while(boolDigital_input_updates) {
						nestedFieldDigital_input_updates = getNextFieldNumber(innerInputReader);
						boolDigital_input_updates = DigitalInputUpdate.populateBuilderWithField(innerInputReader, builderDigital_input_updates, nestedFieldDigital_input_updates);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementDigital_input_updates(builderDigital_input_updates.build());
				}
				break;
			case fieldNumberAnalog_input_updates:
				Vector vcAnalog_input_updates = reader.readMessages(fieldNumberAnalog_input_updates);
				for(int i = 0 ; i < vcAnalog_input_updates.size(); i++) {
					byte[] eachBinData = (byte[]) vcAnalog_input_updates.elementAt(i);
					AnalogInputUpdate.Builder builderAnalog_input_updates = AnalogInputUpdate.newBuilder();
					InputReader innerInputReader = new InputReader(eachBinData, unknownTagHandler);
					boolean boolAnalog_input_updates = true;
					int nestedFieldAnalog_input_updates = -1;
					while(boolAnalog_input_updates) {
						nestedFieldAnalog_input_updates = getNextFieldNumber(innerInputReader);
						boolAnalog_input_updates = AnalogInputUpdate.populateBuilderWithField(innerInputReader, builderAnalog_input_updates, nestedFieldAnalog_input_updates);
					}
					eachBinData = null;
					innerInputReader = null;
					builder.addElementAnalog_input_updates(builderAnalog_input_updates.build());
				}
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		InputUpdates.unknownTagHandler = unknownTagHandler;
	}

	public static InputUpdates parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static InputUpdates parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static InputUpdates parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}