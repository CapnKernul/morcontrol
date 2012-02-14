package com.bhrobotics.morcontrol.util.io;

import java.io.IOException;

public interface ByteWriter {
	public boolean isClosed();
	public void close() throws IOException;
	public void write(byte[] bytes) throws IOException;
}
