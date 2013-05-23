package org.cagrid.core.resource;

public class ImmutableSingletonResourceProperty<T> extends
		SingletonResourceProperty<T> {

	private final T property;

	public ImmutableSingletonResourceProperty(
			ResourcePropertyDescriptor<T> resourcePropertyDescriptor, T property) {
		super(resourcePropertyDescriptor);
		this.property = property;
	}

	@Override
	protected T getPropertyInternal() {
		return property;
	}

	@Override
	protected void setPropertyInternal(T property) {
		throw new UnsupportedOperationException("set");
	}
}
