package com.bhrobotics.morcontrol.util;

import java.util.Hashtable;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import com.bhrobotics.morcontrol.support.TestCase;

public class HashTest extends TestCase {
	@Test
	public void testCreateWithoutHashtable() {
		Hash hash = new Hash();
		assertThat(hash.toHashtable(), is(new Hashtable()));
	}
	
	@Test
	public void testCreateWithHashtable() {
		Hashtable hashtable = new Hashtable();
		Hash hash = new Hash(hashtable);
		assertThat(hash.toHashtable(), is(hashtable));
	}
	
	@Test
	public void testEmpty() {
		Hash hash = new Hash();
		assertThat(hash.isEmpty(), is(true));
		
		hash.put("foo", "bar");
		assertThat(hash.isEmpty(), is(false));
	}
	
	@Test
	public void testContainsKey() {
		Hash hash = new Hash();
		
		hash.put("foo", "bar");
		assertThat(hash.containsKey("foo2"), is(false));
		
		hash.put("foo2", "bar2");
		assertThat(hash.containsKey("foo2"), is(true));
	}
	
	@Test
	public void testContains() {
		Hash hash = new Hash();
		
		hash.put("foo", "bar");
		assertThat(hash.contains("bar2"), is(false));
		
		hash.put("foo2", "bar2");
		assertThat(hash.contains("bar2"), is(true));
	}
	
	@Test
	public void testGetAndPut() {
		Hash hash = new Hash();
		
		hash.put("object", "bar");
		assertThat(hash.get("object"), is((Object) "bar"));
		
		hash.put("string", "bar");
		assertThat(hash.getAsString("string"), is("bar"));

		hash.put("char", 'g');
		assertThat(hash.getAsChar("char"), is('g'));		
		
		hash.put("byte", (byte) 0xFF);
		assertThat(hash.getAsByte("byte"), is((byte) 0xFF));		
		
		hash.put("short", (short) 4);
		assertThat(hash.getAsShort("short"), is((short) 4));		
		
		hash.put("int", 2);
		assertThat(hash.getAsInt("int"), is(2));		
		
		hash.put("long", 2L);
		assertThat(hash.getAsLong("long"), is(2L));		
		
		hash.put("float", 2.0f);
		assertThat(hash.getAsFloat("float"), is(2.0f));		
		
		hash.put("double", 2.0);
		assertThat(hash.getAsDouble("double"), is(2.0));		
		
		hash.put("boolean", true);
		assertThat(hash.getAsBoolean("boolean"), is(true));
	}
	
	@Test
	public void testFetchWithoutADefaultValue() {
		Hash hash = new Hash();
		
		hash.put("object", "bar");
		assertEquals("bar", hash.fetch("object"));
		
		hash.put("string", "bar");
		assertEquals("bar", hash.fetchAsString("string"));

		hash.put("char", 'g');
		assertEquals('g', hash.fetchAsChar("char"));
		
		hash.put("byte", (byte) 0xFF);
		assertEquals((byte) 0xFF, hash.fetchAsByte("byte"));

		hash.put("short", (short) 4);
		assertEquals((short) 4, hash.fetchAsShort("short"));

		hash.put("int", 2);
		assertEquals(2, hash.fetchAsInt("int"));

		hash.put("long", 2L);
		assertEquals(2L, hash.fetchAsLong("long"));		

		hash.put("float", 2.0f);
		assertEquals(2.0f, hash.fetchAsFloat("float"), 0.01);	
		
		hash.put("double", 2.0);
		assertEquals(2.0, hash.fetchAsDouble("double"), 0.01);	

		hash.put("boolean", true);
		assertEquals(true, hash.fetchAsBoolean("boolean"));
	}
	
	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownObjectWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetch("no object");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownStringWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsString("no string");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownCharWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsChar("no char");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownByteWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsByte("no byte");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownShortWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsShort("no short");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownIntWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsInt("no int");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownLongWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsLong("no long");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownFloatWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsFloat("no float");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownDoubleWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsDouble("no double");
	}

	@Test(expected=UnknownKeyException.class)
	public void testFetchUnknownBooleanWithoutDefaultValue() {
		Hash hash = new Hash();
		hash.fetchAsBoolean("no boolean");
	}
	
	@Test
	public void testFetchWithADefaultValue() {
		Hash hash = new Hash();
		
		hash.put("object", "bar");
		assertThat(hash.fetch("object", "baz"), is((Object) "bar"));
		assertThat(hash.fetch("no object", "baz"), is((Object) "baz"));
		
		hash.put("string", "bar");
		assertThat(hash.fetchAsString("string", "baz"), is("bar"));
		assertThat(hash.fetchAsString("no string", "baz"), is("baz"));

		hash.put("char", 'g');
		assertThat(hash.fetchAsChar("char", 'a'), is('g'));		
		assertThat(hash.fetchAsChar("no char", 'a'), is('a'));		
		
		hash.put("byte", (byte) 0xFF);
		assertThat(hash.fetchAsByte("byte", (byte) 0x00), is((byte) 0xFF));
		assertThat(hash.fetchAsByte("no byte", (byte) 0x00), is((byte) 0x00));
		
		hash.put("short", (short) 4);
		assertThat(hash.fetchAsShort("short", (short) 2), is((short) 4));
		assertThat(hash.fetchAsShort("no short", (short) 2), is((short) 2));
		
		hash.put("int", 2);
		assertThat(hash.fetchAsInt("int", 3), is(2));
		assertThat(hash.fetchAsInt("no int", 3), is(3));
		
		hash.put("long", 2L);
		assertThat(hash.fetchAsLong("long", 5L), is(2L));		
		assertThat(hash.fetchAsLong("no long", 5L), is(5L));		
		
		hash.put("float", 2.0f);
		assertThat(hash.fetchAsFloat("float", 5.0f), is(2.0f));		
		assertThat(hash.fetchAsFloat("no float", 5.0f), is(5.0f));		
		
		hash.put("double", 2.0);
		assertThat(hash.fetchAsDouble("double", 9.0), is(2.0));	
		assertThat(hash.fetchAsDouble("no double", 9.0), is(9.0));	
		
		hash.put("boolean", true);
		assertThat(hash.fetchAsBoolean("boolean", false), is(true));
		assertThat(hash.fetchAsBoolean("no boolean", false), is(false));
	}
	
	@Test
	public void testRemove() {
		Hash hash = new Hash();
		hash.put("foo", "bar");
		hash.remove("foo");
		assertThat(hash.containsKey("foo"), is(false));
	}
	
	@Test
	public void testSize() {
		Hash hash = new Hash();
		assertThat(hash.size(), is(0));
		
		hash.put("foo", "bar");
		assertThat(hash.size(), is(1));
		
		hash.put("baz", "quz");
		assertThat(hash.size(), is(2));
	}
	
	@Test
	public void testClear() {
		Hash hash = new Hash();
		hash.put("foo", "bar");
		hash.put("baz", "quz");
		hash.clear();
		
		assertThat(hash.isEmpty(), is(true));
	}
	
	@Test
	public void testToString() {
		Hashtable hashtable = new Hashtable();
		Hash hash = new Hash(hashtable);
		hash.put("foo", "bar");
		
		assertThat(hash.toString(), is(hashtable.toString()));
	}
}
