package com.bhrobotics.morcontrol.oi;

import com.bhrobotics.morcontrol.oi.messages.Message;

public class NullMessageConnection implements MessageConnection {
	public void close() {
	}

	public boolean isClosed() {
		return true;
	}

	public Message[] read() {
		return null;
	}

	public void write(Message[] messages) {
	}
}
