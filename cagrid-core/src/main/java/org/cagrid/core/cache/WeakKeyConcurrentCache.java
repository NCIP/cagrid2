package org.cagrid.core.cache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentHashMap;

public class WeakKeyConcurrentCache<K, V> {

	private final ReferenceQueue<K> rq = new ReferenceQueue<K>();

	private final ConcurrentHashMap<WeakKey<K>, V> map = new ConcurrentHashMap<WeakKey<K>, V>();

	public V get(K key) {
		clearQueue();
		WeakKey<K> weakKey = new WeakKey<K>(key, rq);
		return map.get(weakKey);
	}

	public V put(K key, V value) {
		clearQueue();
		WeakKey<K> weakKey = new WeakKey<K>(key, rq);
		return map.put(weakKey, value);
	}

	public V remove(K key) {
		clearQueue();
		WeakKey<K> weakKey = new WeakKey<K>(key, rq);
		return map.remove(weakKey);
	}

	public void clear() {
		map.clear();
	}

	public boolean isEmpty() {
		clearQueue();
		return map.isEmpty();
	}
	
	public V putIfAbsent(K key, V value) {
		clearQueue();
		WeakKey<K> weakKey = new WeakKey<K>(key, rq);
		return map.putIfAbsent(weakKey, value);
	}

	public boolean remove(K key, V value) {
		clearQueue();
		WeakKey<K> weakKey = new WeakKey<K>(key, rq);
		return map.remove(weakKey, value);
	}

	public V replace(K key, V value) {
		clearQueue();
		WeakKey<K> weakKey = new WeakKey<K>(key, rq);
		return map.replace(weakKey, value);
	}

	public boolean replace(K key, V oldValue, V newValue) {
		clearQueue();
		WeakKey<K> weakKey = new WeakKey<K>(key, rq);
		return map.replace(weakKey, oldValue, newValue);
	}

	private void clearQueue() {
		for (Reference<? extends K> ref = rq.poll(); ref != null; ref = rq
				.poll()) {
			map.remove(ref);
		}
	}
}
