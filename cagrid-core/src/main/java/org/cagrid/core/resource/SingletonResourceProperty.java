package org.cagrid.core.resource;

import org.cagrid.wsrf.properties.ResourcePropertyMetaData;

public abstract class SingletonResourceProperty<T> extends
		AbstractResourceProperty<T> {

	public SingletonResourceProperty(
			ResourcePropertyDescriptor<T> resourcePropertyDescriptor) {
		super(resourcePropertyDescriptor);
	}

	protected abstract T getPropertyInternal();

	protected abstract void setPropertyInternal(T property);

	@Override
	public void add(Object property) {
		throw new UnsupportedOperationException("add");
	}

	@Override
	public boolean remove(Object property) {
		throw new UnsupportedOperationException("remove");
	}

	@Override
	public T get(int index) {
		if (index != 0) {
			throw new IndexOutOfBoundsException("get(" + index + ")");
		}
		return getPropertyInternal();
	}

	@Override
	public void set(int index, Object obj) {
		if (index != 0) {
			throw new IndexOutOfBoundsException("set(" + index + ")");
		}
		T property = checkPropertyType(obj, "set");
		setPropertyInternal(property);
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("clear");
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	protected ResourcePropertyMetaData<T> createMetaData(
			ResourcePropertyDescriptor<T> resourcePropertyDescriptor) {
		return new NonNillableSingletonDescriptorResourcePropertyMetaData<T>(
				resourcePropertyDescriptor);
	}
}
