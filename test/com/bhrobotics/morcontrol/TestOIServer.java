package com.bhrobotics.morcontrol;

import java.io.IOException;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class TestOIServer extends TestCase {
    
    @Test
    public void testInit() throws IOException {
	fail("Junit works!");
	OIServer oIServer = new OIServer();
	assertNotNull(oIServer);
    }
}
