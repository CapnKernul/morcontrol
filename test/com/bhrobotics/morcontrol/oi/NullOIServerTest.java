package com.bhrobotics.morcontrol.oi;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class NullOIServerTest extends TestCase {
	private NullOIServer server = new NullOIServer(1234);
	
	@Test
	public void testPort() {
		assertThat(server.getPort(), is(1234));
	}
	
	@Test
	public void testAccept() {
		MessageConnection connection = server.accept();
		assertThat(connection.isClosed(), is(false));
	}
}
