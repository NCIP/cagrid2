package org.cagrid.core.resource;

import java.util.Iterator;

import javax.xml.soap.SOAPElement;

import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertyMetaData;
import org.w3c.dom.Element;

public abstract class AbstractResourceProperty<T> implements
		ResourceProperty<T> {

	protected final Class<?> resourcePropertyClass;
	protected final ResourcePropertyMetaData<T> resourcePropertyMetaData;

	protected AbstractResourceProperty(
			ResourcePropertyDescriptor<T> resourcePropertyDescriptor) {
		resourcePropertyClass = resourcePropertyDescriptor
				.getResourcePropertyClass();
		resourcePropertyMetaData = createMetaData(resourcePropertyDescriptor);
	}

	@Override
	public abstract T get(int index);

	@Override
	public Iterator<T> iterator() {
		return new PropertyIterator<T>(this);
	}

	@Override
	public SOAPElement[] toSOAPElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element[] toElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcePropertyMetaData<T> getMetaData() {
		return resourcePropertyMetaData;
	}

	protected abstract ResourcePropertyMetaData<T> createMetaData(
			ResourcePropertyDescriptor<T> resourcePropertyDescriptor);

	protected final T checkPropertyType(Object property, String operation) {
		if (!resourcePropertyClass.isInstance(property)) {
			String argumentClassName = (property != null) ? property.getClass()
					.getName() : "null";
			throw new IllegalArgumentException(operation + " expected "
					+ resourcePropertyClass.getName() + ", was "
					+ argumentClassName);
		}
		@SuppressWarnings("unchecked")
		T t = (T) property;
		return t;
	}

	protected static class PropertyIterator<T> implements Iterator<T> {

		private final AbstractResourceProperty<T> resourceProperty;
		private int nextIndex = 0;
		private T currentValue = null;

		public PropertyIterator(AbstractResourceProperty<T> resourceProperty) {
			this.resourceProperty = resourceProperty;
		}

		@Override
		public boolean hasNext() {
			return (nextIndex < resourceProperty.size());
		}

		@Override
		public T next() {
			currentValue = resourceProperty.get(nextIndex++);
			return currentValue;
		}

		@Override
		public void remove() {
			resourceProperty.remove(currentValue);
		}
	}
}
