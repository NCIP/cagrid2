package org.cagrid.core.resource;

import javax.xml.namespace.QName;

import org.cagrid.wsrf.properties.InvalidResourceKeyException;
import org.cagrid.wsrf.properties.NoSuchResourceException;
import org.cagrid.wsrf.properties.RemoveNotSupportedException;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceKey;

public class SingletonResourceHomeImpl implements ResourceHome {

	private final Class<?> keyTypeClass;
	private final QName keyTypeName;
	private final Resource resource;

	public SingletonResourceHomeImpl(Resource resource) {
		this(null, null, resource);
	}

	public SingletonResourceHomeImpl(Class<?> keyTypeClass, QName keyTypeName,
			Resource resource) {
		this.keyTypeClass = keyTypeClass;
		this.keyTypeName = keyTypeName;
		this.resource = resource;
	}

	@Override
	public Class<?> getKeyTypeClass() {
		return keyTypeClass;
	}

	@Override
	public QName getKeyTypeName() {
		return keyTypeName;
	}

	@Override
	public Resource find(ResourceKey key) throws ResourceException,
			NoSuchResourceException, InvalidResourceKeyException {
		return resource;
	}

	@Override
	public void remove(ResourceKey key) throws ResourceException,
			NoSuchResourceException, InvalidResourceKeyException,
			RemoveNotSupportedException {
		throw new RemoveNotSupportedException();
	}
}
