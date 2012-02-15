package com.bhrobotics.morcontrol.util.io;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class ConcurrentByteConnectionTest extends TestCase {
	private InputStream inputStream = mock(InputStream.class);
	private OutputStream outputStream = mock(OutputStream.class);
	private ConcurrentByteReader reader = mock(ConcurrentByteReader.class);
	private ConcurrentByteWriter writer = mock(ConcurrentByteWriter.class);
	
	@Test
	public void testReadOnly() throws IOException {
		ConcurrentByteConnection connection = new ConcurrentByteConnection(inputStream);
		assertThat(connection.isReadable(), is(true));
		assertThat(connection.isWritable(), is(false));
		
		connection.close();
		assertThat(connection.isReadable(), is(false));
	}
	
	@Test
	public void testWriteOnly() throws IOException {
		ConcurrentByteConnection connection = new ConcurrentByteConnection(outputStream);
		assertThat(connection.isReadable(), is(false));
		assertThat(connection.isWritable(), is(true));

		connection.close();
		assertThat(connection.isWritable(), is(false));
	}

	@Test
	public void testReadWrite() throws IOException {
		ConcurrentByteConnection connection = new ConcurrentByteConnection(inputStream, outputStream);
		assertThat(connection.isReadable(), is(true));
		assertThat(connection.isWritable(), is(true));

		connection.close();
		connection.close();
		assertThat(connection.isReadable(), is(false));
		assertThat(connection.isWritable(), is(false));
	}

	@Test
	public void testRead() throws IOException {
		when(reader.read()).thenReturn((byte) 1, (byte) 2, (byte) 3);
		ConcurrentByteConnection connection = new ConcurrentByteConnection(reader);
		assertThat(connection.read(), is((byte) 1));
		assertThat(connection.read(), is((byte) 2));
		assertThat(connection.read(), is((byte) 3));
	}

	@Test(expected=IOException.class)
	public void testReadWhenInputStreamIsClosed() throws IOException {
		when(reader.isClosed()).thenReturn(true);
		ConcurrentByteConnection connection = new ConcurrentByteConnection(writer);
		connection.read();
	}

	@Test(expected=IOException.class)
	public void testReadWhenWriteOnly() throws IOException {
		ConcurrentByteConnection connection = new ConcurrentByteConnection(writer);
		connection.read();
	}
	
	@Test
	public void testWrite() throws IOException {
		ConcurrentByteConnection connection = new ConcurrentByteConnection(writer);
		connection.write(new byte[] { 0, 1, 2 });
		verify(writer).write(new byte[] { 0, 1, 2 });
	}

	@Test(expected=IOException.class)
	public void testWriteWhenOutputStreamIsClosed() throws IOException {
		when(writer.isClosed()).thenReturn(true);
		ConcurrentByteConnection connection = new ConcurrentByteConnection(writer);
		connection.write(new byte[] { 0, 1, 2 });
	}

	@Test(expected=IOException.class)
	public void testWriteWhenReadOnly() throws IOException {
		ConcurrentByteConnection connection = new ConcurrentByteConnection(reader);
		connection.write(new byte[] { 0, 1, 2 });
	}
}
