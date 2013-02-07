package com.bhrobotics.morcontrol.util.collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Test;

import com.bhrobotics.morcontrol.support.TestCase;

public class HashMapTest extends TestCase {
	@Test
	public void testEmpty() {
		HashMap hash = new HashMap();
		assertThat(hash.isEmpty(), is(true));

		hash.put("foo", "bar");
		assertThat(hash.isEmpty(), is(false));
	}

	@Test
	public void testContainsKey() {
		HashMap hash = new HashMap();

		hash.put("foo", "bar");
		assertThat(hash.containsKey("foo2"), is(false));

		hash.put("foo2", "bar2");
		assertThat(hash.containsKey("foo2"), is(true));
	}

	@Test
	public void testContains() {
		HashMap hash = new HashMap();

		hash.put("foo", "bar");
		assertThat(hash.containsValue("bar2"), is(false));

		hash.put("foo2", "bar2");
		assertThat(hash.containsValue("bar2"), is(true));
	}

	@Test
	public void testGetAndPut() {
		HashMap hash = new HashMap();

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
		HashMap hash = new HashMap();

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

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownObjectWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetch("no object");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownStringWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsString("no string");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownCharWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsChar("no char");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownByteWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsByte("no byte");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownShortWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsShort("no short");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownIntWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsInt("no int");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownLongWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsLong("no long");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownFloatWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsFloat("no float");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownDoubleWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsDouble("no double");
	}

	@Test(expected = UnknownKeyException.class)
	public void testFetchUnknownBooleanWithoutDefaultValue() {
		HashMap hash = new HashMap();
		hash.fetchAsBoolean("no boolean");
	}

	@Test
	public void testFetchWithADefaultValue() {
		HashMap hash = new HashMap();

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
		HashMap hash = new HashMap();
		hash.put("foo", "bar");
		hash.remove("foo");
		assertThat(hash.containsKey("foo"), is(false));
	}

	@Test
	public void testSize() {
		HashMap hash = new HashMap();
		assertThat(hash.size(), is(0));

		hash.put("foo", "bar");
		assertThat(hash.size(), is(1));

		hash.put("baz", "qux");
		assertThat(hash.size(), is(2));
	}

	@Test
	public void testClear() {
		HashMap hash = new HashMap();
		hash.put("foo", "bar");
		hash.put("baz", "qux");
		hash.clear();

		assertThat(hash.isEmpty(), is(true));
	}

	@Test
	public void testPutAll() {
		HashMap hash1 = new HashMap();
		hash1.put("foo", "bar");
		hash1.put("baz", "qux");

		HashMap hash2 = new HashMap();
		hash2.put("foo", "quux");
		hash2.put("test", "woop");
		hash2.putAll(hash1);

		assertThat(hash2.getAsString("foo"), is("bar"));
		assertThat(hash2.getAsString("baz"), is("qux"));
		assertThat(hash2.getAsString("test"), is("woop"));
	}

	@Test
	public void testEquals() {
		HashMap hash1 = new HashMap();
		hash1.put("foo", "bar");

		HashMap hash2 = new HashMap();
		hash2.put("foo", "bar");

		HashMap hash3 = new HashMap();
		hash3.put("baz", "qux");

		assertThat(hash1, is(hash2));
		assertThat(hash2, is(not(hash3)));
		assertThat(hash2, is(not(hash3)));
	}
}
