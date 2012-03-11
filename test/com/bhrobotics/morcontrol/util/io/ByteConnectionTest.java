package com.bhrobotics.morcontrol.util.io;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class ByteConnectionTest extends TestCase {
	private InputStream inputStream = mock(InputStream.class);
	private OutputStream outputStream = mock(OutputStream.class);
	
	@Test
	public void testReadOnly() throws IOException {
		ByteConnection connection = new ByteConnection(inputStream);
		assertThat(connection.isReadable(), is(true));
		assertThat(connection.isWritable(), is(false));
	}
	
	@Test
	public void testWriteOnly() throws IOException {
		ByteConnection connection = new ByteConnection(outputStream);
		assertThat(connection.isReadable(), is(false));
		assertThat(connection.isWritable(), is(true));
	}

	@Test
	public void testReadWrite() throws IOException {
		ByteConnection connection = new ByteConnection(inputStream, outputStream);
		assertThat(connection.isReadable(), is(true));
		assertThat(connection.isWritable(), is(true));
	}
	
	@Test
	public void testClose() throws IOException {
		ByteConnection connection = new ByteConnection(inputStream, outputStream);
		connection.close();
		
		verify(inputStream).close();
		verify(outputStream).close();
	}
}
