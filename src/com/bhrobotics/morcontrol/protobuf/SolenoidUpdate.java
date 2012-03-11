package com.bhrobotics.morcontrol.protobuf;

import java.io.IOException;
import java.io.InputStream;
import net.jarlehansen.protobuf.javame.UninitializedMessageException;
import net.jarlehansen.protobuf.javame.input.InputReader;
import net.jarlehansen.protobuf.javame.input.DelimitedInputStream;
import net.jarlehansen.protobuf.javame.input.DelimitedSizeUtil;
import net.jarlehansen.protobuf.javame.ComputeSizeUtil;
import net.jarlehansen.protobuf.javame.output.OutputWriter;
import net.jarlehansen.protobuf.javame.AbstractOutputWriter;
import net.jarlehansen.protobuf.javame.input.taghandler.UnknownTagHandler;
import net.jarlehansen.protobuf.javame.input.taghandler.DefaultUnknownTagHandlerImpl;

public class SolenoidUpdate extends AbstractOutputWriter {
	private static UnknownTagHandler unknownTagHandler = DefaultUnknownTagHandlerImpl.newInstance();

	private final int module;
	private static final int fieldNumberModule = 1;

	private final int channel;
	private static final int fieldNumberChannel = 2;

	private final boolean value;
	private static final int fieldNumberValue = 3;


	public static Builder newBuilder() {
		return new Builder();
	}

	private SolenoidUpdate(final Builder builder) {
		if (builder.hasModule && builder.hasChannel && builder.hasValue ) {
			this.module = builder.module;
			this.channel = builder.channel;
			this.value = builder.value;
		} else {
			throw new UninitializedMessageException("Not all required fields were included (false = not included in message), " + 
				" module:" + builder.hasModule + " channel:" + builder.hasChannel + " value:" + builder.hasValue + "");
		}
	}

	public static class Builder {
		private int module;
		private boolean hasModule = false;

		private int channel;
		private boolean hasChannel = false;

		private boolean value;
		private boolean hasValue = false;


		private Builder() {
		}

		public Builder setModule(final int module) {
			this.module = module;
			this.hasModule = true;
			return this;
		}

		public Builder setChannel(final int channel) {
			this.channel = channel;
			this.hasChannel = true;
			return this;
		}

		public Builder setValue(final boolean value) {
			this.value = value;
			this.hasValue = true;
			return this;
		}

		public SolenoidUpdate build() {
			return new SolenoidUpdate(this);
		}
	}

	public int getModule() {
		return module;
	}

	public int getChannel() {
		return channel;
	}

	public boolean getValue() {
		return value;
	}

	public String toString() {
		final String TAB = "   ";
		String retValue = "";
		retValue += this.getClass().getName() + "(";
		retValue += "module = " + this.module + TAB;
		retValue += "channel = " + this.channel + TAB;
		retValue += "value = " + this.value + TAB;
		retValue += ")";

		return retValue;
	}

	// Override
	public int computeSize() {
		int totalSize = 0;
		totalSize += ComputeSizeUtil.computeIntSize(fieldNumberModule, module);
		totalSize += ComputeSizeUtil.computeIntSize(fieldNumberChannel, channel);
		totalSize += ComputeSizeUtil.computeBooleanSize(fieldNumberValue, value);
		totalSize += computeNestedMessageSize();

		return totalSize;
	}

	private int computeNestedMessageSize() {
		int messageSize = 0;

		return messageSize;
	}

	// Override
	public void writeFields(final OutputWriter writer) throws IOException {
		writer.writeInt(fieldNumberModule, module);
		writer.writeInt(fieldNumberChannel, channel);
		writer.writeBoolean(fieldNumberValue, value);
		writer.writeData();
	}

	static SolenoidUpdate parseFields(final InputReader reader) throws IOException {
		int nextFieldNumber = getNextFieldNumber(reader);
		final SolenoidUpdate.Builder builder = SolenoidUpdate.newBuilder();

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
			case fieldNumberModule:
				builder.setModule(reader.readInt(fieldNumber));
				break;
			case fieldNumberChannel:
				builder.setChannel(reader.readInt(fieldNumber));
				break;
			case fieldNumberValue:
				builder.setValue(reader.readBoolean(fieldNumber));
				break;
		default:
			fieldFound = false;
		}
		return fieldFound;
	}

	public static void setUnknownTagHandler(final UnknownTagHandler unknownTagHandler) {
		SolenoidUpdate.unknownTagHandler = unknownTagHandler;
	}

	public static SolenoidUpdate parseFrom(final byte[] data) throws IOException {
		return parseFields(new InputReader(data, unknownTagHandler));
	}

	public static SolenoidUpdate parseFrom(final InputStream inputStream) throws IOException {
		return parseFields(new InputReader(inputStream, unknownTagHandler));
	}

	public static SolenoidUpdate parseDelimitedFrom(final InputStream inputStream) throws IOException {
		final int limit = DelimitedSizeUtil.readDelimitedSize(inputStream);
		return parseFields(new InputReader(new DelimitedInputStream(inputStream, limit), unknownTagHandler));
	}
}