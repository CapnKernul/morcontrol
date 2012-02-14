package com.bhrobotics.morcontrol.util.io;

import java.io.IOException;
import java.io.OutputStream;

import com.bhrobotics.morcontrol.util.concurrent.ReentrantMutex;

public class ConcurrentByteWriter implements ByteWriter {
	private ReentrantMutex mutex = new ReentrantMutex();
	private boolean closed = false;
	private OutputStream stream;
	
	public ConcurrentByteWriter(OutputStream stream) {
		this.stream = stream;
	}
	
	public void write(byte[] bytes) throws IOException {
		if (closed) {
			throw new IOException("Cannot write to a closed stream.");
		}
		
		mutex.lock();
		try {
			stream.write(bytes);
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
