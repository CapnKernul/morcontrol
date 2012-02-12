package com.bhrobotics.morcontrol.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Connection {
	private ByteReader reader;
	private ByteWriter writer;
	
	public Connection(InputStream inputStream) {
		this(new ByteReader(inputStream));
	}
	
	public Connection(OutputStream outputStream) {
		this(new ByteWriter(outputStream));
	}
	
	public Connection(InputStream inputStream, OutputStream outputStream) {
		this(new ByteReader(inputStream), new ByteWriter(outputStream));
	}

	public Connection(ByteReader reader) {
		this.reader = reader;
	}
	
	public Connection(ByteWriter writer) {
		this.writer = writer;
	}
	
	public Connection(ByteReader reader, ByteWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}
	
	public boolean isReadable() {
		return reader != null && !reader.isClosed();
	}
	
	public boolean isWritable() {
		return writer != null && !writer.isClosed();
	}
	
	public void close() throws IOException {
		closeInputStream();
		closeOutputStream();
	}
	
	public void closeInputStream() throws IOException {
		if (isReadable()) {
			reader.close();
		}
	}

	public void closeOutputStream() throws IOException {
		if (isWritable()) {
			writer.close();
		}
	}
	
	public byte read() throws IOException {
		if (!isReadable()) {
			throw new IOException("Could not read from a unreadable connection.");
		}
		
		return reader.read();
	}
	
	public void write(byte[] bytes) throws IOException {
		if (!isWritable()) {
			throw new IOException("Could not write to unwritable connection.");
		}
		
		writer.write(bytes);
	}
}
