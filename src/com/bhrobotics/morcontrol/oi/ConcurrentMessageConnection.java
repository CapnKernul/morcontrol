package com.bhrobotics.morcontrol.oi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Vector;

import com.bhrobotics.morcontrol.oi.messages.Message;
import com.bhrobotics.morcontrol.oi.messages.MessageSerializer;
import com.bhrobotics.morcontrol.util.io.ConcurrentByteConnection;

public class ConcurrentMessageConnection implements MessageConnection {
	public static final byte GROUP_SEPARATOR_BYTE = (byte) 29;
	public static final byte RECORD_SEPARATOR_BYTE = (byte) 30;
	public static final byte UNIT_SEPARATOR_BYTE = (byte) 31;
	
	public static final byte[] GROUP_SEPARATOR_BYTES = new byte[] { GROUP_SEPARATOR_BYTE };
	public static final byte[] RECORD_SEPARATOR_BYTES = new byte[] { RECORD_SEPARATOR_BYTE };
	public static final byte[] UNIT_SEPARATOR_BYTES = new byte[] { UNIT_SEPARATOR_BYTE };
	
	private ConcurrentByteConnection connection;
	private MessageSerializer serializer;
	
	public ConcurrentMessageConnection(ConcurrentByteConnection connection) {
		this(connection, new MessageSerializer());
	}
	
	public ConcurrentMessageConnection(ConcurrentByteConnection connection, MessageSerializer serializer) {
		this.connection = connection;
		this.serializer = serializer;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (IOException e) {
			throw new OIException(e);
		}
	}
	
	public boolean isClosed() {
		return !connection.isReadable() || !connection.isWritable();
	}

	public Message[] read() {
		Reader reader = new Reader();
		return reader.read();
	}
	
	public void write(Message[] messages) {
		Writer writer = new Writer();
		writer.write(messages);
	}
	
	private class Reader {
		private Vector messages = new Vector();
		private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		private boolean done = false;
		
		public Message[] read() {
			while (!done) {
				byte b = readByte();
			
				switch (b) {
				case GROUP_SEPARATOR_BYTE:
					done = true;
					break;
				case RECORD_SEPARATOR_BYTE:
					Message message = deserializeBuffer();
					addMessage(message);
					clearBuffer();
					break;
				default:
					addToBuffer(b);
				}
			}
			
			return toArray();
		}
		
		private void clearBuffer() {
			buffer.reset();
		}
		
		private Message deserializeBuffer() {
			byte[] bytes = buffer.toByteArray();
			return serializer.deserialize(bytes);
		}
		
		private void addToBuffer(byte b) {
			buffer.write(b);
		}
		
		private void addMessage(Message message) {
			messages.addElement(message);
		}
		
		private Message[] toArray() {
			Message[] result = new Message[messages.size()];
			messages.copyInto(result);
			return result;
		}

		private byte readByte() {
			try {
				return connection.read();
			} catch (IOException e) {
				throw new OIException(e);
			}
		}
	}
	
	private class Writer {
		public void write(Message[] messages) {
			if (messages.length == 0) {
				return;
			}
			
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				byte[] bytes = serializer.serialize(message);
				
				write(bytes);
				write(RECORD_SEPARATOR_BYTES);
			}
			
			write(GROUP_SEPARATOR_BYTES);
		}

		private void write(byte[] bytes) {
			try {
				connection.write(bytes);
			} catch (IOException e) {
				throw new OIException(e);
			}
		}
	}
}
