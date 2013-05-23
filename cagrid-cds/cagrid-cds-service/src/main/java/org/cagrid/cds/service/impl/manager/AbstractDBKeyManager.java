package org.cagrid.cds.service.impl.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.cds.service.impl.util.WrappedKey;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gaards.pki.SecurityUtil;
import org.cagrid.tools.database.Database;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDBKeyManager implements KeyManager {

	private final static String PROVIDER = "BC";
	private final static String TABLE = "key_manager";
	private final static String ALIAS = "ALIAS";
	private final static String PUBLIC_KEY = "PUBLIC_KEY";
	private final static String PRIVATE_KEY = "PRIVATE_KEY";
	private final static String IV = "IV";

	private final static String CERTIFICATES_TABLE = "key_manager_certificates";
	private final static String CERTIFICATE_NUMBER = "CERTIFICATE_NUMBER";
	private final static String CERTIFICATE = "CERTIFICATE";

	private boolean dbBuilt = false;

	private Database db;
	private Log log;

	public AbstractDBKeyManager(Database db) {
		this.log = LogFactory.getLog(this.getClass().getName());
		this.db = db;
		SecurityUtil.init();
	}

	public boolean exists(String alias) throws CDSInternalException {
		try {
			return db.exists(TABLE, ALIAS, alias);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		}
	}

	public abstract WrappedKey wrapPrivateKey(PrivateKey key)
			throws CDSInternalException;

	public abstract PrivateKey unwrapPrivateKey(WrappedKey wrappedKey)
			throws CDSInternalException;

	public KeyPair createAndStoreKeyPair(String alias, int keyLength)
			throws CDSInternalException {
		try {
			KeyPair pair = KeyUtil.generateRSAKeyPair(PROVIDER, keyLength);
			String publicKey = KeyUtil.writePublicKey(pair.getPublic());
			WrappedKey privateKey = wrapPrivateKey(pair.getPrivate());
			insertKeypair(alias, publicKey, privateKey);
			return pair;
		} catch (CDSInternalException f) {
			throw f;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		}

	}

	public X509Certificate[] getCertificates(String alias)
			throws CDSInternalException {
		this.buildDatabase();

		if (exists(alias)) {
			List<X509Certificate> list = new ArrayList<X509Certificate>();
			Connection c = null;
			try {
				c = db.getConnection();
				PreparedStatement s = c.prepareStatement("select "
						+ CERTIFICATE + " from " + CERTIFICATES_TABLE
						+ " WHERE " + ALIAS + "= ? ORDER BY "
						+ CERTIFICATE_NUMBER);

				s.setString(1, alias);
				ResultSet rs = s.executeQuery();
				while (rs.next()) {
					list.add(CertUtil
							.loadCertificate(rs.getString(CERTIFICATE)));
				}
				rs.close();
				s.close();
				if (list.size() == 0) {
					return null;
				} else {
					X509Certificate[] certs = new X509Certificate[list.size()];
					list.toArray(certs);
					return certs;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
                throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
			} finally {
				this.db.releaseConnection(c);
			}
		} else {
			return null;
		}
	}

	public PublicKey getPublicKey(String alias) throws CDSInternalException {
		this.buildDatabase();
		PublicKey key = null;
		if (exists(alias)) {

			Connection c = null;
			try {
				c = this.db.getConnection();
				PreparedStatement s = c.prepareStatement("select " + PUBLIC_KEY
						+ " from " + TABLE + " WHERE " + ALIAS + "= ?");

				s.setString(1, alias);
				ResultSet rs = s.executeQuery();
				if (rs.next()) {
					key = KeyUtil.loadPublicKey(rs.getString(PUBLIC_KEY));
				}
				rs.close();
				s.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
                throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
			} finally {
				this.db.releaseConnection(c);
			}
		}
		return key;
	}

	public PrivateKey getPrivateKey(String alias) throws CDSInternalException {
		this.buildDatabase();
		PrivateKey key = null;
		if (exists(alias)) {

			Connection c = null;
			try {
				c = this.db.getConnection();
				PreparedStatement s = c.prepareStatement("select "
						+ PRIVATE_KEY + "," + IV + " from " + TABLE + " WHERE "
						+ ALIAS + "= ?");

				s.setString(1, alias);
				ResultSet rs = s.executeQuery();
				if (rs.next()) {
					byte[] keyData = rs.getBytes(PRIVATE_KEY);
					byte[] ivData = rs.getBytes(IV);
					key = unwrapPrivateKey(new WrappedKey(keyData, ivData));
				}
				rs.close();
				s.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
                throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
			} finally {
				this.db.releaseConnection(c);
			}
		}
		return key;

	}

	public void storeCertificates(String alias, X509Certificate[] cert)
			throws CDSInternalException, DelegationException {
		this.buildDatabase();
		if (exists(alias)) {
			if ((cert != null) && (cert.length > 0)) {
				if (!cert[0].getPublicKey().equals(getPublicKey(alias))) {
                    throw Errors.makeException(DelegationException.class, "The certificate provides is not bound to the public key generated.");
				}
				Connection c = null;
				try {
					c = this.db.getConnection();
					for (int i = 0; i < cert.length; i++) {
						PreparedStatement s = c.prepareStatement("INSERT INTO "
								+ CERTIFICATES_TABLE+ " SET " + ALIAS + "= ?,"
								+ CERTIFICATE_NUMBER + "= ?," + CERTIFICATE
								+ "= ?");
						s.setString(1, alias);
						s.setInt(2, (i+1));
						s.setString(3, CertUtil.writeCertificate(cert[i]));
						s.execute();
						s.close();
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
                    throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
				} finally {
					this.db.releaseConnection(c);
				}
			}
		} else {
            throw Errors.makeException(CDSInternalException.class, "Cannot insert certificate, no key pair exists for the record ("
                    + alias + ").");

		}

	}

	private void insertKeypair(String alias, String publicKey,
			WrappedKey privateKey) throws CDSInternalException {
		this.buildDatabase();
		if (!exists(alias)) {
			Connection c = null;
			try {
				c = this.db.getConnection();
				PreparedStatement s = c.prepareStatement("INSERT INTO " + TABLE
						+ " SET " + ALIAS + "= ?, " + PUBLIC_KEY + "= ?, "
						+ PRIVATE_KEY + "= ?, " + IV + "= ?");
				s.setString(1, alias);
				s.setString(2, publicKey);
				s.setBytes(3, privateKey.getWrappedKeyData());
				s.setBytes(4, privateKey.getIV());
				s.execute();
				s.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
                throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
			} finally {
				this.db.releaseConnection(c);
			}
		} else {
            throw Errors.makeException(CDSInternalException.class, "Cannot insert key pair, a key pair already exists for the record ("
                    + alias + ").");
		}

	}

	public void delete(String alias) throws CDSInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = this.db.getConnection();
			PreparedStatement s = c.prepareStatement("DELETE FROM " + TABLE
					+ "  WHERE " + ALIAS + "= ?");
			s.setString(1, alias);
			s.execute();
			s.close();
			s = c.prepareStatement("DELETE FROM " + CERTIFICATES_TABLE
					+ "  WHERE " + ALIAS + "= ?");
			s.setString(1, alias);
			s.execute();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		} finally {
			this.db.releaseConnection(c);
		}
	}

	public void deleteAll() throws CDSInternalException {
		buildDatabase();
		try {
			this.db.update("DELETE FROM " + TABLE);
			this.db.update("DELETE FROM " + CERTIFICATES_TABLE);
			dbBuilt = false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		}
	}

	private void buildDatabase() throws CDSInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(TABLE)) {
					String table = "CREATE TABLE " + TABLE + " (" + ALIAS
							+ " VARCHAR(255) NOT NULL PRIMARY KEY,"
							+ PUBLIC_KEY + " TEXT NOT NULL," + PRIVATE_KEY
							+ " BLOB NOT NULL," + IV
							+ " BLOB, INDEX document_index (" + ALIAS + "));";
					this.db.update(table);

				}
				if (!this.db.tableExists(CERTIFICATES_TABLE)) {
					String certTable = "CREATE TABLE " + CERTIFICATES_TABLE
							+ " (" + ALIAS + " VARCHAR(255) NOT NULL,"
							+ CERTIFICATE_NUMBER + " INT," + CERTIFICATE
							+ " TEXT, INDEX document_index (" + ALIAS + "));";
					this.db.update(certTable);
				}
				dbBuilt = true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
                throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
			}

		}
	}

	protected Database getDB() {
		return db;
	}

	protected Log getLog() {
		return log;
	}

}
