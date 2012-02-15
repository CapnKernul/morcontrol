package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.util.OperatingSystem;

public class OIServerFactory {
	public static final int DEFAULT_PORT = 2576;
	
	public OIServer create() {
		return create(DEFAULT_PORT);
	}
	
	public OIServer create(int port) {
		if (OperatingSystem.isCRio()) {
			return new CRioOIServer(port);
		} else {
			return new NullOIServer(port);
		}
	}
}
