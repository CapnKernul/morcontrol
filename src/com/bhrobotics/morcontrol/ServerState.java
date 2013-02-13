package com.bhrobotics.morcontrol;

public class ServerState {

	public static final ServerState UNCONNECTED = new ServerState();
	public static final ServerState CONNECTED = new ServerState();
	public static final ServerState PROCESSING = new ServerState();

	private ServerState() {
	};
}
