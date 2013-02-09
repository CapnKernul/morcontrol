package com.bhrobotics.morcontrol.io;

import org.apache.thrift.TException;

public class UpdateService implements UpdateTransport.Iface {
    private Mailbox mailbox;
    
    public UpdateService(Mailbox mailbox) {
	this.mailbox = mailbox;
    }
    
    public Event waitForUpdate() throws TException {
	while(!Thread.interrupted()) {
	    if(mailbox.isEmpty()) {
		Thread.yield();
	    } else {
		return mailbox.shift();
	    }
	}
	mailbox.clear();
	return null;
    }
    
}
