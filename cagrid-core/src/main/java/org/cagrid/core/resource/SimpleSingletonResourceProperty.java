package org.cagrid.core.resource;

public class SimpleSingletonResourceProperty<T> extends
		SingletonResourceProperty<T> {

	private T property;

	public SimpleSingletonResourceProperty(
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
		this.property = property;
	}
}
