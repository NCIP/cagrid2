package org.cagrid.core.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakKey<K> extends WeakReference<K> {

	private final int hashCode;

	public WeakKey(K key) {
		this(key, null);
	}

	public WeakKey(K key, ReferenceQueue<? super K> rq) {
		super(key, rq);
		hashCode = key.hashCode();
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		K key = get();
		if (key == null)
			return false;
		if (!(o instanceof WeakKey<?>))
			return false;
		o = ((WeakKey<?>) o).get();
		return key.equals(o);
	}
}