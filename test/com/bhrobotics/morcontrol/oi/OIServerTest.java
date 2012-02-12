package com.bhrobotics.morcontrol.oi;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

import static org.hamcrest.CoreMatchers.is;

public class OIServerTest extends TestCase {
	private OIServer server = new OIServer(1234);
	
	@Test
	public void testPort() {
		assertThat(server.getPort(), is(1234));
	}
	
	@Test
	public void testAccept() {
		OIConnection connection = server.accept();
		assertThat(connection.isClosed(), is(false));
	}
}
