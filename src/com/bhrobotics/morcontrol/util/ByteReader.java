package com.bhrobotics.morcontrol.util;

import java.io.IOException;
import java.io.InputStream;

public class ByteReader {
	private ReentrantMutex mutex = new ReentrantMutex();
	private boolean closed = false;
	private InputStream stream;
	
	public ByteReader(InputStream stream) {
		this.stream = stream;
	}
	
	public byte read() throws IOException {
		if (closed) {
			throw new IOException("Cannot read from a closed stream.");
		}
		
		mutex.lock();
		try {
			return (byte) stream.read();
		} catch (IOException e) {
			closed = true;
			throw e;
		} finally {
			mutex.unlock();
		}
	}

	public boolean isClosed() {
		return closed;
	}
	
	public void close() throws IOException {
		closed = true;
		stream.close();
	}
}
