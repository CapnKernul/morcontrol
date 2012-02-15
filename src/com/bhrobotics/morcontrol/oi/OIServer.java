package com.bhrobotics.morcontrol.oi;

public interface OIServer {
	public int getPort();
	public MessageConnection accept();
}
