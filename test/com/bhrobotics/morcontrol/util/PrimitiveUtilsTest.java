package com.bhrobotics.morcontrol.util;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class PrimitiveUtilsTest extends TestCase {
	private Character objChar = new Character((char) 1);
	private Byte objByte = new Byte((byte) 2);
	private Short objShort = new Short((short) 3);
	private Integer objInt = new Integer(4);
	private Long objLong = new Long(5L);
	private Float objFloat = new Float(6f);
	private Double objDouble = new Double(7.0);
	private Boolean objBoolean = new Boolean(true);

	@Test
	public void testIsPrimitive() {
		assertThat(PrimitiveUtils.isPrimitive("foo"), is(false));
		assertThat(PrimitiveUtils.isPrimitive(objChar), is(true));
		assertThat(PrimitiveUtils.isPrimitive(objByte), is(true));
		assertThat(PrimitiveUtils.isPrimitive(objShort), is(true));
		assertThat(PrimitiveUtils.isPrimitive(objInt), is(true));
		assertThat(PrimitiveUtils.isPrimitive(objLong), is(true));
		assertThat(PrimitiveUtils.isPrimitive(objFloat), is(true));
		assertThat(PrimitiveUtils.isPrimitive(objDouble), is(true));
		assertThat(PrimitiveUtils.isPrimitive(objBoolean), is(true));
	}

	@Test
	public void testToPrimitive() {
		assertThat(PrimitiveUtils.toPrimitive(objChar), is((char) 1));
		assertThat(PrimitiveUtils.toPrimitive(objByte), is((byte) 2));
		assertThat(PrimitiveUtils.toPrimitive(objShort), is((short) 3));
		assertThat(PrimitiveUtils.toPrimitive(objInt), is(4));
		assertThat(PrimitiveUtils.toPrimitive(objLong), is(5L));
		assertThat(PrimitiveUtils.toPrimitive(objFloat), is(6f));
		assertThat(PrimitiveUtils.toPrimitive(objDouble), is(7.0));
		assertThat(PrimitiveUtils.toPrimitive(objBoolean), is(true));
	}

	@Test
	public void testToObject() {
		assertThat(PrimitiveUtils.toObject((char) 1), is(objChar));
		assertThat(PrimitiveUtils.toObject((byte) 2), is(objByte));
		assertThat(PrimitiveUtils.toObject((short) 3), is(objShort));
		assertThat(PrimitiveUtils.toObject(4), is(objInt));
		assertThat(PrimitiveUtils.toObject(5L), is(objLong));
		assertThat(PrimitiveUtils.toObject(6f), is(objFloat));
		assertThat(PrimitiveUtils.toObject(7.0), is(objDouble));
		assertThat(PrimitiveUtils.toObject(true), is(objBoolean));
	}
}
