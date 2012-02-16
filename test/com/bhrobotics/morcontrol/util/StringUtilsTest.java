package com.bhrobotics.morcontrol.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.support.TestCase;

public class StringUtilsTest extends TestCase {
	@Test
	public void testSplit() {
		String original = "foo:bar:qux";
		String[] parts = StringUtils.split(original, ":");
		assertEquals(3, parts.length);
		assertEquals("foo", parts[0]);
		assertEquals("bar", parts[1]);
		assertEquals("qux", parts[2]);
		
		original = "::bar::a:";
		parts = StringUtils.split(original, ":");
		assertEquals(6, parts.length);
		assertEquals("", parts[0]);
		assertEquals("", parts[1]);
		assertEquals("bar", parts[2]);
		assertEquals("", parts[3]);
		assertEquals("a", parts[4]);
		assertEquals("", parts[5]);
	}
	
	@Test
	public void testContains() {
		String original = "foo:bar:qux";
		assertThat(StringUtils.contains(original, "foo"), is(true));
		assertThat(StringUtils.contains(original, "foo2"), is(false));
	}
}
