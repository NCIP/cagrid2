package org.cagrid.core.cache;

import java.util.concurrent.ConcurrentHashMap;

public class WeakValueConcurrentCache<K, V> {

	private final ConcurrentHashMap<K, WeakValue<V>> map = new ConcurrentHashMap<K, WeakValue<V>>();

	public V get(K key) {
		WeakValue<V> weakValue = map.get(key);
		return cleanUp(key, weakValue);
	}

	public V put(K key, V value) {
		WeakValue<V> weakValue = new WeakValue<V>(value);
		WeakValue<V> oldWeakValue = map.put(key, weakValue);
		return cleanUp(key, oldWeakValue);
	}

	public V remove(K key) {
		WeakValue<V> oldWeakValue = map.remove(key);
		return cleanUp(key, oldWeakValue);
	}

	public void clear() {
		map.clear();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public V putIfAbsent(K key, V value) {
		WeakValue<V> weakValue = new WeakValue<V>(value);
		WeakValue<V> oldWeakValue = map.putIfAbsent(key, weakValue);
		return cleanUp(key, oldWeakValue);
	}

	public boolean remove(K key, V value) {
		WeakValue<V> weakValue = new WeakValue<V>(value);
		return map.remove(key, weakValue);
	}

	public V replace(K key, V value) {
		WeakValue<V> weakValue = new WeakValue<V>(value);
		WeakValue<V> oldWeakValue = map.replace(key, weakValue);
		return cleanUp(key, oldWeakValue);
	}

	public boolean replace(K key, V oldValue, V newValue) {
		WeakValue<V> oldWeakValue = new WeakValue<V>(oldValue);
		WeakValue<V> newWeakValue = new WeakValue<V>(newValue);
		return map.replace(key, oldWeakValue, newWeakValue);
	}

	private V cleanUp(K key, WeakValue<V> vRef) {
		V v = null;
		if (vRef != null) {
			v = vRef.get();
			if (v == null) {
				map.remove(key, vRef);
			}
		}
		return v;
	}
}
