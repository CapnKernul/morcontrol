package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;

import com.bhrobotics.morcontrol.io.DeviceService;
import com.bhrobotics.morcontrol.io.DeviceTransport;
import com.bhrobotics.morcontrol.io.UpdateService;
import com.bhrobotics.morcontrol.io.UpdateTransport;
import com.bhrobotics.morcontrol.util.logger.Logger;

public class AsyncOIServer implements OIServer {

    private Vector observers = new Vector();

    private CompetitionRobot robot;
    private ServerSocketConnection socketDevice;
    private ServerSocketConnection socketUpdate;
    private AsyncOIConnector deviceConnector;
    private AsyncOIConnector updateConnector;

    private AsyncProcessor deviceProcess = null;
    private AsyncProcessor updateProcess = null;

    public AsyncOIServer(CompetitionRobot robot, int port) {
	this.robot = robot;
	try {
	    socketDevice = (ServerSocketConnection) Connector.open("socket://:" + port);
	    int portTwo = 2 * port;
	    socketUpdate = (ServerSocketConnection) Connector.open("socket://:" + (portTwo));
	    Logger.defaultLogger.info("Sucessfully openned sockets");
	    deviceConnector = new AsyncOIConnector(socketDevice);
	    updateConnector = new AsyncOIConnector(socketUpdate);
	} catch (IOException e) {
	    Logger.defaultLogger.error("could not open sockets");
	}
    }

    public void start() {

    }

    public void stop() {

    }

    public boolean isConnected() {
	return deviceConnector.isConnected() && updateConnector.isConnected();
    }

    public void openListeners() {
	if(!deviceConnector.isConnected()) {
	    deviceConnector.establishConnection();
	}

	if(!updateConnector.isConnected()) {
	    updateConnector.establishConnection();
	}
    }

    public void closeListeners() {
	deviceConnector.resetConnection();
	updateConnector.resetConnection();
    }


    public void startProcess() {
	if(isConnected()) {
	   endProcess();
	    
	   DeviceService deviceService = new DeviceService(robot);
	   TProcessor processor = new DeviceTransport.Processor(deviceService);
	   TBinaryProtocol protocol = new TBinaryProtocol(deviceConnector.getConnection());
	   deviceProcess  = new AsyncProcessor(processor, protocol);
	   
	   UpdateService updateService = new UpdateService(robot.getRegistry().getMailbox());
	   processor = new UpdateTransport.Processor(updateService);
	   protocol = new TBinaryProtocol(updateConnector.getConnection());
	   updateProcess = new AsyncProcessor(processor, protocol);
	   
	   deviceProcess.start();
	   updateProcess.start();
	} else {
	    Logger.defaultLogger.info("Robot is not connected, did not start processes");
	}
    }

    public void endProcess() {
	if(deviceProcess != null) {
	    deviceProcess.kill();
	    deviceProcess = null;
	}
	
	if(updateProcess != null) {
	    updateProcess.kill();
	    updateProcess = null;
	}
    }
    
    public void removeObserver(OIServerObserver observer) {
	observers.removeElement(observer);
    }

    public Enumeration getObservers() {
	return observers.elements();
    }

    public void addObserver(OIServerObserver observer) {
	observers.addElement(observer);
    }
}