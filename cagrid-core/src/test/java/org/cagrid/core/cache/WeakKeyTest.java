package org.cagrid.core.cache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

import org.junit.Assert;
import org.junit.Test;

public class WeakKeyTest {

	/*
	 * There's no guarantee that this test will pass, System.gc may not collect
	 * the key referent.
	 */
	@Test
	public void testGarbageCollectedReference() {
		ReferenceQueue<Object> rq = new ReferenceQueue<Object>();

		WeakKey<Object> key = new WeakKey<Object>(new Object(), rq);

		boolean collected = false;
		for (int i = 0; i < 10; i++) {
			System.gc();
			try {
				Thread.sleep(10L);
			} catch (Exception ignored) {
			}
			Reference<?> ref = rq.poll();
			if (ref != null) {
				System.out.println(i);
				Assert.assertEquals(
						"Garbage-collected reference should equal WeakKey",
						key, ref);
				collected = true;
				break;
			}
		}
		Assert.assertTrue("WeakKey reference was never garbage-collected",
				collected);
	}
}
