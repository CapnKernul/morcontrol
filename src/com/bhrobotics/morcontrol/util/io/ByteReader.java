package com.bhrobotics.morcontrol.util.io;

import java.io.IOException;

public interface ByteReader {
	public boolean isClosed();
	public void close() throws IOException;
	public byte read() throws IOException;
}
