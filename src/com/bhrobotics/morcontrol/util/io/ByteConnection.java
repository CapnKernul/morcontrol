package com.bhrobotics.morcontrol.util.io;

public interface ByteConnection extends ByteReader, ByteWriter {
	public boolean isReadable();
	public boolean isWritable();
}
