package org.cagrid.cds.service.impl.manager;

import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.cds.service.impl.util.WrappedKey;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.tools.database.Database;

import java.io.ByteArrayInputStream;
import java.security.PrivateKey;

public class DBKeyManager extends AbstractDBKeyManager {

	private String keyEncryptionPassword;

	public DBKeyManager(Database db) throws CDSInternalException {
		super(db);
	}

	public void setKeyEncryptionPassword(String keyEncryptionPassword) {
		this.keyEncryptionPassword = keyEncryptionPassword;
	}

	public PrivateKey unwrapPrivateKey(WrappedKey wrappedKey)
			throws CDSInternalException {
		try {
			return KeyUtil.loadPrivateKey(new ByteArrayInputStream(wrappedKey
                    .getWrappedKeyData()), keyEncryptionPassword);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "Unexpected error unwrapping key.", e);
		}
	}

	public WrappedKey wrapPrivateKey(PrivateKey key) throws CDSInternalException {
		try {
			WrappedKey wk = new WrappedKey(KeyUtil.writePrivateKey(key,
                    keyEncryptionPassword).getBytes(), null);
			return wk;
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class, "Unexpected error wrapping key.",e);
		}
	}
	
	public String getName() {
	    //for backwards compatibility
	    return "org.cagrid.gaards.cds.service.DBKeyManager";
	}

}
