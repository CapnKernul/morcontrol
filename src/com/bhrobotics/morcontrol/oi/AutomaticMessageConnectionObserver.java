package com.bhrobotics.morcontrol.oi;

public interface AutomaticMessageConnectionObserver {
	public void openedConnection();
	public void closedConnection();
	public void readMessages();
	public void wroteMessages();
	public void exceptionWhileReading(OIException e);
	public void exceptionWhileWriting(OIException e);
}
