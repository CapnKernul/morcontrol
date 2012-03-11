package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.util.io.ByteConnection;

public interface OIServer {
	public int getPort();
	public ByteConnection accept();
}
