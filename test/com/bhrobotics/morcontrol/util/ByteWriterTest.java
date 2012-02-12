package com.bhrobotics.morcontrol.util;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.mockito.InOrder;

import com.bhrobotics.morcontrol.support.TestCase;

public class ByteWriterTest extends TestCase {
	private OutputStream stream = mock(OutputStream.class);
	private ByteWriter writer = new ByteWriter(stream);
	
	@Test
	public void testWrite() throws IOException {
		for (int i = 0; i < 50; i++) {
			writer.write(new byte[] { (byte) i });
		}
		
		InOrder inOrder = inOrder(stream);
		for (int i = 0; i < 50; i++) {
			inOrder.verify(stream).write(new byte[] { (byte) i });
		}
	}
	
	@Test
	public void testClose() throws IOException {
		assertThat(writer.isClosed(), is(false));
		writer.close();
		assertThat(writer.isClosed(), is(true));
	}
	
	@Test(expected=IOException.class)
	public void testWriteWhileClosed() throws IOException {
		writer.close();
		writer.write(new byte[] { 0 });
	}
}
