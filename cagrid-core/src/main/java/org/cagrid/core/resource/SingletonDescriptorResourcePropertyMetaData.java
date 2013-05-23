package org.cagrid.core.resource;

public abstract class SingletonDescriptorResourcePropertyMetaData<T> extends
		DescriptorResourcePropertyMetaData<T> {

	public SingletonDescriptorResourcePropertyMetaData(
			ResourcePropertyDescriptor<T> descriptor) {
		super(descriptor);
	}

	@Override
	public int getMaxOccurs() {
		return 1;
	}
}
