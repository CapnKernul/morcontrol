package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.util.OperatingSystem;

public class OIServerFactory {
	public static final int DEFAULT_PORT = 2576;
	
	public OIServer newServer() {
		return newServer(DEFAULT_PORT);
	}
	
	public OIServer newServer(int port) {
		if (OperatingSystem.isCRio()) {
			return new CRioOIServer(port);
		} else {
			return new QueuedOIServer(port);
		}
	}
}
