package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.oi.messages.Message;

public interface MessageConnection {
	public void close();
	public boolean isClosed();
	public Message[] read();
	public void write(Message[] messages);
}
