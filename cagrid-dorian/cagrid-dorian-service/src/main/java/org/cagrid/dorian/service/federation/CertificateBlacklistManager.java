package org.cagrid.dorian.service.federation;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.tools.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificateBlacklistManager {

	public static final String CERTIFICATE_RENEWED = "CERTIFICATE RENEWED";
	public static final String ACCOUNT_DELETED = "ACCOUNT DELETED";
	public static final String COMPROMISED = "COMPROMISED";
	public static final String TABLE = "certificate_blacklist";
	public static final String SERIAL = "SERIAL_NUMBER";
	public static final String SUBJECT = "SUBJECT";
	public static final String REASON = "REASON";
	public static final String CERTIFICATE = "CERTIFICATE";

	private boolean dbBuilt = false;
	private Database db;
	private Logger log = LoggerFactory.getLogger(getClass());

	public CertificateBlacklistManager(Database db) {
		this.db = db;
	}

	public synchronized void addCertificateToBlackList(org.cagrid.dorian.common.X509Certificate cert, String reason) throws DorianInternalException {
		try {
			addCertificateToBlackList(CertUtil.loadCertificate(cert.getCertificateAsString()), reason);
		} catch (GeneralSecurityException e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} catch (IOException e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	public X509Certificate getCertificate(long sn) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		X509Certificate cert = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select " + CERTIFICATE + " from " + TABLE + " WHERE " + SERIAL + "= ?");
			s.setLong(1, sn);

			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				cert = CertUtil.loadCertificate(rs.getString(CERTIFICATE));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}

		return cert;
	}

	public synchronized void addCertificateToBlackList(X509Certificate cert, String reason) throws DorianInternalException {
		buildDatabase();
		if (!memberOfBlackList(cert.getSerialNumber().longValue())) {
			Connection c = null;
			try {
				c = db.getConnection();
				PreparedStatement s = c.prepareStatement("INSERT INTO " + TABLE + " SET " + SERIAL + "= ?," + SUBJECT + "= ?," + REASON + "= ?," + CERTIFICATE + "= ?");
				s.setLong(1, cert.getSerialNumber().longValue());
				s.setString(2, cert.getSubjectDN().getName());
				s.setString(3, reason);
				s.setString(4, CertUtil.writeCertificate(cert));
				s.executeUpdate();
				s.close();
			} catch (Exception e) {
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Error");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			} finally {
				db.releaseConnection(c);
			}
		}
	}

	public void removeCertificateFromBlackList(long serialNumber) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("delete from " + TABLE + " where " + SERIAL + "= ?");
			s.setLong(1, serialNumber);
			s.executeUpdate();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}

	}

	public List<Long> getBlackList() throws DorianInternalException {
		buildDatabase();
		List<Long> list = new ArrayList<Long>();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select " + SERIAL + " from " + TABLE);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				list.add(Long.valueOf(rs.getLong(SERIAL)));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected error encountered.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return list;
	}

	public boolean memberOfBlackList(long id) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		boolean exists = false;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select count(*) from " + TABLE + " WHERE " + SERIAL + "= ?");
			s.setLong(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					exists = true;
				}
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return exists;
	}

	public void buildDatabase() throws DorianInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(TABLE)) {

					String certificates = "CREATE TABLE " + TABLE + " (" + SERIAL + " BIGINT PRIMARY KEY," + SUBJECT + " TEXT NOT NULL," + REASON + " VARCHAR(255) NOT NULL," + CERTIFICATE + " TEXT,"
							+ "INDEX document_index (" + SERIAL + "));";
					db.update(certificates);

				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected database error occurred.");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}
			this.dbBuilt = true;
		}
	}

	public void clearDatabase() throws DorianInternalException {
		buildDatabase();
		try {
			db.update("delete from " + TABLE);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

}
