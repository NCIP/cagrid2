package org.cagrid.core.resource;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;

import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertyMetaData;
import org.cagrid.wsrf.properties.ResourcePropertySet;
import org.w3c.dom.Element;

public class ResourceImpl implements Resource, ResourcePropertySet {

	private final Map<QName, ResourceProperty<?>> _resourceProperties = new ConcurrentHashMap<QName, ResourceProperty<?>>();
	private final Map<QName, ResourceProperty<?>> resourceProperties = Collections
			.unmodifiableMap(_resourceProperties);

	private final QName resourceQName;

	public ResourceImpl(QName resourceQName) {
		this.resourceQName = resourceQName;
	}

	@Override
	public boolean add(ResourceProperty<?> property) {
		QName qName = property.getMetaData().getName();
		_resourceProperties.put(qName, property);
		return true;
	}

	@Override
	public boolean remove(QName qName) {
		ResourceProperty<?> oldProperty = _resourceProperties.remove(qName);
		return (oldProperty != null);
	}

	@Override
	public ResourceProperty<?> get(QName qName) {
		return _resourceProperties.get(qName);
	}

	@Override
	public ResourceProperty<?> create(ResourcePropertyMetaData<?> rpMetaData) {
		return null;
	}

	@Override
	public Iterator<ResourceProperty<?>> iterator() {
		return resourceProperties.values().iterator();
	}

	@Override
	public void clear() {
		_resourceProperties.clear();
	}

	@Override
	public int size() {
		return _resourceProperties.size();
	}

	@Override
	public boolean isEmpty() {
		return _resourceProperties.isEmpty();
	}

	@Override
	public boolean isOpenContent() {
		return false;
	}

	@Override
	public QName getName() {
		return resourceQName;
	}

	@Override
	public SOAPElement toSOAPElement() {
		return null;
	}

	@Override
	public Element toElement() {
		return null;
	}
}
