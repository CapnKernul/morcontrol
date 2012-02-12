package com.bhrobotics.morcontrol.util;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class ConnectionTest extends TestCase {
	private InputStream inputStream = mock(InputStream.class);
	private OutputStream outputStream = mock(OutputStream.class);
	private ByteReader reader = mock(ByteReader.class);
	private ByteWriter writer = mock(ByteWriter.class);
	
	@Test
	public void testReadOnly() throws IOException {
		Connection connection = new Connection(inputStream);
		assertThat(connection.isReadable(), is(true));
		assertThat(connection.isWritable(), is(false));
		
		connection.closeInputStream();
		assertThat(connection.isReadable(), is(false));
	}
	
	@Test
	public void testWriteOnly() throws IOException {
		Connection connection = new Connection(outputStream);
		assertThat(connection.isReadable(), is(false));
		assertThat(connection.isWritable(), is(true));

		connection.closeOutputStream();
		assertThat(connection.isWritable(), is(false));
	}

	@Test
	public void testReadWrite() throws IOException {
		Connection connection = new Connection(inputStream, outputStream);
		assertThat(connection.isReadable(), is(true));
		assertThat(connection.isWritable(), is(true));

		connection.closeInputStream();
		connection.closeOutputStream();
		assertThat(connection.isReadable(), is(false));
		assertThat(connection.isWritable(), is(false));
	}

	@Test
	public void testRead() throws IOException {
		when(reader.read()).thenReturn((byte) 1, (byte) 2, (byte) 3);
		Connection connection = new Connection(reader);
		assertThat(connection.read(), is((byte) 1));
		assertThat(connection.read(), is((byte) 2));
		assertThat(connection.read(), is((byte) 3));
	}

	@Test(expected=IOException.class)
	public void testReadWhenInputStreamIsClosed() throws IOException {
		when(reader.isClosed()).thenReturn(true);
		Connection connection = new Connection(writer);
		connection.read();
	}

	@Test(expected=IOException.class)
	public void testReadWhenWriteOnly() throws IOException {
		Connection connection = new Connection(writer);
		connection.read();
	}
	
	@Test
	public void testWrite() throws IOException {
		Connection connection = new Connection(writer);
		connection.write(new byte[] { 0, 1, 2 });
		verify(writer).write(new byte[] { 0, 1, 2 });
	}

	@Test(expected=IOException.class)
	public void testWriteWhenOutputStreamIsClosed() throws IOException {
		when(writer.isClosed()).thenReturn(true);
		Connection connection = new Connection(writer);
		connection.write(new byte[] { 0, 1, 2 });
	}

	@Test(expected=IOException.class)
	public void testWriteWhenReadOnly() throws IOException {
		Connection connection = new Connection(reader);
		connection.write(new byte[] { 0, 1, 2 });
	}
	
	@Test
	public void testClose() throws IOException {
		Connection connection = new Connection(reader, writer);
		connection.close();
		verify(reader).close();
		verify(writer).close();
	}
}
