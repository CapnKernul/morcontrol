package com.bhrobotics.morcontrol.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConcurrentByteConnection implements ByteConnection {
	private ConcurrentByteReader reader;
	private ConcurrentByteWriter writer;
	
	public ConcurrentByteConnection(InputStream inputStream) {
		this(new ConcurrentByteReader(inputStream));
	}
	
	public ConcurrentByteConnection(OutputStream outputStream) {
		this(new ConcurrentByteWriter(outputStream));
	}
	
	public ConcurrentByteConnection(InputStream inputStream, OutputStream outputStream) {
		this(new ConcurrentByteReader(inputStream), new ConcurrentByteWriter(outputStream));
	}

	public ConcurrentByteConnection(ConcurrentByteReader reader) {
		this.reader = reader;
	}
	
	public ConcurrentByteConnection(ConcurrentByteWriter writer) {
		this.writer = writer;
	}
	
	public ConcurrentByteConnection(ConcurrentByteReader reader, ConcurrentByteWriter writer) {
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
	
	private void closeInputStream() throws IOException {
		if (isReadable()) {
			reader.close();
		}
	}

	private void closeOutputStream() throws IOException {
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

	public boolean isClosed() {
		return false;
	}
}
