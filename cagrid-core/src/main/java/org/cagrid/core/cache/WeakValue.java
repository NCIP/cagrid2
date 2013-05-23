package org.cagrid.core.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakValue<V> extends WeakReference<V> {

	private final int hashCode;

	public WeakValue(V value) {
		this(value, null);
	}

	public WeakValue(V value, ReferenceQueue<? super V> rq) {
		super(value, rq);
		hashCode = value.hashCode();
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	/*
	 * Only return true if the references are to the same object!
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		V value = get();
		if (value == null)
			return false;
		if (!(o instanceof WeakValue<?>))
			return false;
		o = ((WeakValue<?>) o).get();
		return (value == o);
	}
}
