package com.bhrobotics.morcontrol.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteConnection {
	private InputStream inputStream;
	private OutputStream outputStream;
	
	public ByteConnection(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public ByteConnection(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public ByteConnection(InputStream inputStream, OutputStream outputStream) {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}
	
	public boolean isReadable() {
		return inputStream != null;
	}
	
	public boolean isWritable() {
		return outputStream != null;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}
	
	public void close() throws IOException {
		closeInputStream();
		closeOutputStream();
	}
	
	private void closeInputStream() throws IOException {
		if (isReadable()) {
			inputStream.close();
		}
	}

	private void closeOutputStream() throws IOException {
		if (isWritable()) {
			outputStream.close();
		}
	}
}
