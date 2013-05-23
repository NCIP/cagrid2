package org.cagrid.core.resource;

public class ExternalSingletonResourceProperty<T> extends
		ImmutableSingletonResourceProperty<T> {

	private final ExternalSingletonResourcePropertyValue<T> externalProperty;

	public ExternalSingletonResourceProperty(
			ResourcePropertyDescriptor<T> resourcePropertyDescriptor,
			ExternalSingletonResourcePropertyValue<T> externalProperty) {
		super(resourcePropertyDescriptor, null);
		this.externalProperty = externalProperty;
	}

	@Override
	protected T getPropertyInternal() {
		return externalProperty.getPropertyValue();
	}
}
