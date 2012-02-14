package com.bhrobotics.morcontrol.util.io;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;
import com.bhrobotics.morcontrol.util.io.ConcurrentByteReader;

public class ConcurrentByteReaderTest extends TestCase {
	private InputStream stream = mock(InputStream.class);
	private ConcurrentByteReader reader = new ConcurrentByteReader(stream);
	
	@Test
	public void testRead() throws IOException {
		when(stream.read()).thenReturn(1, 2, 3);
		assertThat(reader.read(), is((byte) 1));
		assertThat(reader.read(), is((byte) 2));
		assertThat(reader.read(), is((byte) 3));
	}
	
	@Test
	public void testClose() throws IOException {
		assertThat(reader.isClosed(), is(false));
		reader.close();
		assertThat(reader.isClosed(), is(true));
	}
	
	@Test(expected=IOException.class)
	public void testReadWhileClosed() throws IOException {
		reader.close();
		reader.read();
	}
}
