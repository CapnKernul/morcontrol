package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class OIServerFactoryTest extends TestCase {
	private OIServerFactory factory = new OIServerFactory();
	
	@Test
	public void testCreateWithoutPort() {
		OIServer server = factory.newServer();
		assertThat(server.getPort(), is(2576));
	}
	
	@Test
	public void testCreateWithPort() {
		OIServer server = factory.newServer(1234);
		assertThat(server.getPort(), is(1234));
	}
}
