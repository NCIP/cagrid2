package org.cagrid.core.resource;

public class NonNillableSingletonDescriptorResourcePropertyMetaData<T> extends
		SingletonDescriptorResourcePropertyMetaData<T> {

	public NonNillableSingletonDescriptorResourcePropertyMetaData(
			ResourcePropertyDescriptor<T> descriptor) {
		super(descriptor);
	}

	@Override
	public boolean isNillable() {
		return false;
	}
}
