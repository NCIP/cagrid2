package org.cagrid.core.resource;

import javax.xml.namespace.QName;

import org.cagrid.wsrf.properties.ResourcePropertyMetaData;

public abstract class DescriptorResourcePropertyMetaData<T> implements
		ResourcePropertyMetaData<T> {

	protected final ResourcePropertyDescriptor<T> descriptor;

	public DescriptorResourcePropertyMetaData(
			ResourcePropertyDescriptor<T> descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public QName getName() {
		return descriptor.getResourcePropertyQName();
	}

	@Override
	public int getMinOccurs() {
		return 1;
	}

	@Override
	public Class<T> getType() {
		return descriptor.getResourcePropertyClass();
	}

	@Override
	public boolean isReadOnly() {
		return false;
	}
}
