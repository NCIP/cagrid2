package org.cagrid.core.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WeakKeyConcurrentCacheTest {

	private final String key1 = "key1";
	private final String key2 = "key2";
	private final String value1 = "value1";
	private final String value2 = "value2";
	private final String value3 = "value3";

	private WeakKeyConcurrentCache<String, String> cache;

	@Before
	public void createCache() {
		cache = new WeakKeyConcurrentCache<String, String>();
	}

	@Test
	public void testGetPut() {
		cache.put(key1, value1);
		Assert.assertEquals(value1, cache.get(key1));
		cache.put(key1, value2);
		Assert.assertEquals(value2, cache.get(key1));
		Assert.assertNull(cache.get(key2));
	}

	@Test
	public void testRemove() {
		cache.put(key1, key1);
		Assert.assertEquals(key1, cache.remove(key1));
		Assert.assertNull(cache.get(key1));
	}

	@Test
	public void testClear() {
		cache.put(key1, key1);
		cache.clear();
		Assert.assertNull(cache.get(key1));
	}

	@Test
	public void testPutIfAbsent() {
		Assert.assertNull(cache.putIfAbsent(key1, value1));
		Assert.assertEquals(value1, cache.putIfAbsent(key1, value2));
		Assert.assertEquals(value1, cache.get(key1));
	}

	@Test
	public void testConditionalRemove() {
		cache.put(key1, value1);
		Assert.assertFalse(cache.remove(key1, value2));
		Assert.assertTrue(cache.remove(key1, value1));
	}

	@Test
	public void testReplace() {
		Assert.assertNull(cache.replace(key1, value1));
		Assert.assertNull(cache.get(key1));
		cache.put(key1, value1);
		Assert.assertEquals(value1, cache.replace(key1, value2));
		Assert.assertEquals(value2, cache.get(key1));
	}

	@Test
	public void testConditionalReplace() {
		cache.put(key1, value1);
		Assert.assertFalse(cache.replace(key1, value2, value3));
		Assert.assertTrue(cache.replace(key1, value1, value3));
		Assert.assertEquals(value3, cache.get(key1));
	}

	/*
	 * There's no guarantee that this test will pass, System.gc may not collect
	 * the key referent.
	 */
	@Test
	public void testGarbageCollection() {
		WeakKeyConcurrentCache<Object, Object> objectCache = new WeakKeyConcurrentCache<Object, Object>();
		Assert.assertTrue(objectCache.isEmpty());

		Object key = new Object();
		Object value = new Object();
		objectCache.put(key, value);
		Assert.assertFalse(objectCache.isEmpty());

		for (int i = 0; i < 10; i++) {
			System.gc();
			try {
				Thread.sleep(10L);
			} catch (Exception ignored) {
			}
			if (objectCache.isEmpty()) {
				Assert.fail("Object cache was emptied while still holding a key");
			}
		}

		key = null;

		for (int i = 0; i < 10; i++) {
			System.gc();
			try {
				Thread.sleep(10L);
			} catch (Exception ignored) {
			}
			if (objectCache.isEmpty()) {
				break;
			}
		}
		Assert.assertTrue("Object cache was not emptied", objectCache.isEmpty());
	}
}
