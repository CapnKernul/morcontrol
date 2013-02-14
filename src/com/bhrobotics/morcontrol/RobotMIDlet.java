package com.bhrobotics.morcontrol;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;

import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransportException;

import com.bhrobotics.morcontrol.devices.registry.DeviceRegistry;
import com.bhrobotics.morcontrol.util.logger.Logger;

import edu.wpi.first.wpilibj.SimpleRobot;

public class RobotMIDlet extends SimpleRobot {

	private OIServer oIServer;
	private CompetitionRobot competitionRobot;
	private DeviceRegistry registry;
	private ServerSocketConnection ket;
	private ServerSocketConnection soc;

	public void robotInit() {
		Logger.defaultLogger.debug("Init was called");
		try {
			soc = (ServerSocketConnection)Connector.open("socket://:1515");
			ket = (ServerSocketConnection)Connector.open("socket://:3030");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disabled() {
		Logger.defaultLogger.debug("disabled was called");
		AsyncOIConnector connectorOne = new AsyncOIConnector(soc);
		AsyncOIConnector connectorTwo = new AsyncOIConnector(ket);
		connectorOne.establishConnection();
		connectorTwo.establishConnection();
		while(!connectorOne.isConnected() || !connectorTwo.isConnected()) {
			Thread.yield();
		}
		Logger.defaultLogger.debug("connected");
		try {
			TIOStreamTransport streamOne = connectorOne.getConnection();
			TIOStreamTransport streamTwo = connectorTwo.getConnection();
			
			while(true) {
				byte[] bytes = {1,2};
				streamOne.write(bytes);
				streamTwo.write(bytes);
			}
		} catch (TTransportException e) {
			Logger.defaultLogger.debug("disconnected");
		}
	}

	public void autonomous() {
		
	}

	public void operatorControl() {
		
	}
}