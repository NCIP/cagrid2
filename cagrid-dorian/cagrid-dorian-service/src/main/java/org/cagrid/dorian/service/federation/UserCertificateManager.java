package org.cagrid.dorian.service.federation;

import gov.nih.nci.cagrid.common.Utils;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.exceptions.InvalidUserCertificateException;
import org.cagrid.dorian.model.federation.DateRange;
import org.cagrid.dorian.model.federation.UserCertificateFilter;
import org.cagrid.dorian.model.federation.UserCertificateRecord;
import org.cagrid.dorian.model.federation.UserCertificateStatus;
import org.cagrid.dorian.model.federation.UserCertificateUpdate;
import org.cagrid.dorian.service.util.PreparedStatementBuilder;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.tools.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCertificateManager {

	public static final String TABLE = "user_certificates";
	private static final String GID = "GID";
	private static final String SERIAL = "SERIAL_NUMBER";
	private static final String STATUS = "STATUS";
	private static final String CERTIFICATE = "CERTIFICATE";
	private static final String NOT_BEFORE = "NOT_BEFORE";
	private static final String NOT_AFTER = "NOT_AFTER";
	private static final String NOTES = "NOTES";

	public static final String CANNOT_UPDATE_CERT_DOES_NOT_EXIST_ERROR = "Could not update the user certificate record, no such user certificate exists.";
	public static final String CANNOT_UPDATE_STATUS_IF_COMPROMISED_ERROR = "You cannot update the status of a user certificate that has been compromised.";
	public static final String USER_CERTIFICATE_DOES_NOT_EXIST_ERROR = "The requested user certificate does not exist.";
	public static final String USER_CERTIFICATE_ALREADY_EXISTS_ERROR = "Cannot add the requested user certificate, a user certificate with the same serial number already exists.";
	public static final String FIND_INVALID_RANGE_ERROR = "Invalid search criteria specified, the start date of the search must before the end date of the search.";
	public static final String FIND_INVALID_RANGE_NO_START_ERROR = "Invalid search criteria specified, no start date specified";
	public static final String FIND_INVALID_RANGE_NO_END_ERROR = "Invalid search criteria specified, no end date specified";

	private boolean dbBuilt = false;
	private Database db;
	private Publisher publisher;
	private CertificateBlacklistManager blacklist;
	private Logger log;

	public UserCertificateManager(Database db, Publisher publisher,
			CertificateBlacklistManager blacklist) {
		this.db = db;
		this.log = LoggerFactory.getLogger(this.getClass());
		this.publisher = publisher;
		this.blacklist = blacklist;
	}

	public synchronized void addUserCertifcate(String gridIdentity,
			X509Certificate cert) throws DorianInternalException,
			InvalidUserCertificateException {
		if (determineIfRecordExistBySerialNumber(cert.getSerialNumber()
				.longValue())) {
			InvalidUserCertificateException fault = FaultHelper
					.createFaultException(
							InvalidUserCertificateException.class,
							USER_CERTIFICATE_ALREADY_EXISTS_ERROR);
			throw fault;
		}
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("INSERT INTO " + TABLE
					+ " SET " + SERIAL + "= ?," + GID + "= ?," + STATUS
					+ "= ?," + NOT_BEFORE + "= ?," + NOT_AFTER + "= ?," + NOTES
					+ "= ?," + CERTIFICATE + "= ?");
			s.setLong(1, cert.getSerialNumber().longValue());
			s.setString(2, gridIdentity);
			s.setString(3, UserCertificateStatus.OK.value());
			s.setLong(4, cert.getNotBefore().getTime());
			s.setLong(5, cert.getNotAfter().getTime());
			s.setString(6, "");
			s.setString(7, CertUtil.writeCertificate(cert));
			s.execute();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"An unexpected error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public boolean determineIfRecordExistBySerialNumber(long serialNumber)
			throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		boolean exists = false;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select count(*) from "
					+ TABLE + " WHERE " + SERIAL + "= ?");
			s.setLong(1, serialNumber);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					exists = true;
				}
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return exists;
	}

	public void updateUserCertificateRecord(UserCertificateUpdate update)
			throws DorianInternalException, InvalidUserCertificateException {
		if (!determineIfRecordExistBySerialNumber(update.getSerialNumber())) {
			InvalidUserCertificateException fault = FaultHelper
					.createFaultException(
							InvalidUserCertificateException.class,
							CANNOT_UPDATE_CERT_DOES_NOT_EXIST_ERROR);
			throw fault;
		}
		UserCertificateRecord record = getUserCertificateRecord(update
				.getSerialNumber());

		boolean updateStatus = false;
		if ((update.getStatus() != null)
				&& (!update.getStatus().equals(record.getStatus()))) {
			if (record.getStatus().equals(UserCertificateStatus.COMPROMISED)
					&& (update.getStatus().equals(UserCertificateStatus.OK))) {
				InvalidUserCertificateException fault = FaultHelper
						.createFaultException(
								InvalidUserCertificateException.class,
								CANNOT_UPDATE_STATUS_IF_COMPROMISED_ERROR);
				throw fault;
			}
			updateStatus = true;
		}
		boolean updateNotes = false;
		if ((update.getNotes() != null)
				&& (!update.getNotes().equals(record.getNotes()))) {
			updateNotes = true;
		}

		if (updateNotes || updateStatus) {
			Connection c = null;
			try {
				c = db.getConnection();
				StringBuffer sb = new StringBuffer();
				sb.append("update " + TABLE + " SET ");

				if (updateStatus) {
					sb.append(STATUS + "= ?");
				}
				if (updateNotes) {
					if (updateStatus) {
						sb.append(",");
					}
					sb.append(NOTES + "= ? ");
				}

				sb.append(" WHERE " + SERIAL + "= ?");
				PreparedStatement s = c.prepareStatement(sb.toString());
				int count = 1;
				if (updateStatus) {
					s.setString(count, update.getStatus().value());
					count++;
				}
				if (updateNotes) {
					s.setString(count, update.getNotes());
					count++;
				}
				s.setLong(count, update.getSerialNumber());
				s.execute();
				if (updateStatus) {
					publishCRLIfNeeded(record.getStatus(), update.getStatus());
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper
						.createFaultException(DorianInternalException.class,
								"An unexpected error occurred.");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			} finally {
				db.releaseConnection(c);
			}
		}

	}

	public UserCertificateRecord getUserCertificateRecord(long serialNumber)
			throws DorianInternalException, InvalidUserCertificateException {
		buildDatabase();
		UserCertificateFilter f = new UserCertificateFilter();
		f.setSerialNumber(new Long(serialNumber));
		List<UserCertificateRecord> records = findUserCertificateRecords(f);
		if (records.size() == 1) {
			return records.get(0);
		} else if (records.size() == 0) {
			InvalidUserCertificateException fault = FaultHelper
					.createFaultException(
							InvalidUserCertificateException.class,
							USER_CERTIFICATE_DOES_NOT_EXIST_ERROR);
			throw fault;
		} else {
			String msg = "Multiple user certificates found with the "
					+ serialNumber + " please contact you administrator.";
			log.error(msg);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, msg);
			throw fault;
		}
	}

	public List<BigInteger> getActiveCertificates(String gridIdentity)
			throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		List<BigInteger> certs = new ArrayList<BigInteger>();
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select " + SERIAL
					+ " from " + TABLE + " WHERE " + STATUS + "= ? AND "
					+ NOT_BEFORE + "<= ? AND " + NOT_AFTER + " >= ? AND " + GID
					+ "= ?");
			s.setString(1, UserCertificateStatus.OK.value());
			Date time = new Date();
			s.setLong(2, time.getTime());
			s.setLong(3, time.getTime());
			s.setString(4, gridIdentity);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				certs.add(BigInteger.valueOf(rs.getLong(SERIAL)));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return certs;
	}

	public List<BigInteger> getCompromisedCertificates()
			throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		List<BigInteger> certs = new ArrayList<BigInteger>();
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select " + SERIAL
					+ " from " + TABLE + " WHERE " + STATUS + "= ?");
			s.setString(1, UserCertificateStatus.COMPROMISED.value());
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				certs.add(BigInteger.valueOf(rs.getLong(SERIAL)));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return certs;
	}

	private void publishCRLIfNeeded(UserCertificateStatus s1,
			UserCertificateStatus s2) {
		if ((s1.equals(UserCertificateStatus.OK))
				&& (s2.equals(UserCertificateStatus.COMPROMISED))) {
			publisher.publishCRL();
		}
	}

	public List<UserCertificateRecord> findUserCertificateRecords(
			UserCertificateFilter f) throws DorianInternalException,
			InvalidUserCertificateException {
		this.buildDatabase();
		Connection c = null;
		List<UserCertificateRecord> certs = new ArrayList<UserCertificateRecord>();

		try {
			c = db.getConnection();
			PreparedStatementBuilder select = new PreparedStatementBuilder(
					TABLE);
			select.addSelectField(SERIAL);
			select.addSelectField(GID);
			select.addSelectField(STATUS);
			select.addSelectField(NOTES);
			select.addSelectField(CERTIFICATE);

			if (f != null) {
				if (f.getSerialNumber() != null) {
					select.addWhereField(SERIAL, "=", f.getSerialNumber());
				}

				if (f.getGridIdentity() != null) {
					select.addWhereField(GID, "=", f.getGridIdentity());
				}

				if (f.getStatus() != null) {
					select.addWhereField(STATUS, "=", f.getStatus().value());
				}

				if (f.getNotes() != null) {
					select.addWhereField(NOTES, "LIKE", "%" + f.getNotes()
							+ "%");
				}

				// We want to get check the target range
				DateRange range = f.getDateRange();
				if (range != null) {
					if (range.getStartDate() == null) {
						InvalidUserCertificateException fault = FaultHelper
								.createFaultException(
										InvalidUserCertificateException.class,
										FIND_INVALID_RANGE_NO_START_ERROR);
						throw fault;
					} else if (range.getEndDate() == null) {
						InvalidUserCertificateException fault = FaultHelper
								.createFaultException(
										InvalidUserCertificateException.class,
										FIND_INVALID_RANGE_NO_END_ERROR);
						throw fault;
					} else if (range.getStartDate().after(range.getEndDate())) {
						InvalidUserCertificateException fault = FaultHelper
								.createFaultException(
										InvalidUserCertificateException.class,
										FIND_INVALID_RANGE_ERROR);
						throw fault;
					} else {
						select.addClause("((" + NOT_BEFORE + ">="
								+ range.getStartDate().getTimeInMillis()
								+ " AND " + NOT_AFTER + "<="
								+ range.getEndDate().getTimeInMillis() + ")"
								+ " OR (" + NOT_BEFORE + "<="
								+ range.getStartDate().getTimeInMillis()
								+ " AND " + NOT_AFTER + ">="
								+ range.getEndDate().getTimeInMillis() + ")"
								+ " OR (" + NOT_BEFORE + ">="
								+ range.getStartDate().getTimeInMillis()
								+ " AND " + NOT_BEFORE + "<="
								+ range.getEndDate().getTimeInMillis()
								+ " AND " + NOT_AFTER + ">="
								+ range.getStartDate().getTimeInMillis()
								+ " AND " + NOT_AFTER + ">="
								+ range.getEndDate().getTimeInMillis() + ")"
								+ " OR (" + NOT_BEFORE + "<="
								+ range.getStartDate().getTimeInMillis()
								+ " AND " + NOT_BEFORE + "<="
								+ range.getEndDate().getTimeInMillis()
								+ " AND " + NOT_AFTER + ">="
								+ range.getStartDate().getTimeInMillis()
								+ " AND " + NOT_AFTER + "<="
								+ range.getEndDate().getTimeInMillis() + "))");
					}
				}
			}
			PreparedStatement s = select.prepareStatement(c);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				UserCertificateRecord record = new UserCertificateRecord();
				record.setSerialNumber(rs.getLong(SERIAL));
				record.setGridIdentity(rs.getString(GID));
				record.setStatus(UserCertificateStatus.fromValue(rs
						.getString(STATUS)));
				record.setNotes(rs.getString(NOTES));
				String certStr = Utils.clean(rs.getString(CERTIFICATE));
				if (certStr != null) {
					org.cagrid.dorian.common.X509Certificate cert = new org.cagrid.dorian.common.X509Certificate();
					cert.setCertificateAsString(certStr);
					record.setCertificate(cert);
				}
				certs.add(record);
			}
			rs.close();
			s.close();

		} catch (InvalidUserCertificateException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"An unexpected error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return certs;

	}

	public void removeCertificates(String gridIdentity)
			throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select " + SERIAL
					+ " from " + TABLE + " WHERE " + GID + "= ?");
			s.setString(1, gridIdentity);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				removeCertificate(rs.getLong(SERIAL));
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public void removeCertificate(long recordId)
			throws DorianInternalException, InvalidUserCertificateException {
		UserCertificateRecord record = getUserCertificateRecord(recordId);
		buildDatabase();
		Connection c = null;
		try {
			if (record.getStatus().equals(UserCertificateStatus.COMPROMISED)) {
				X509Certificate cert = CertUtil.loadCertificate(record
						.getCertificate().getCertificateAsString());
				blacklist.addCertificateToBlackList(cert,
						CertificateBlacklistManager.COMPROMISED);
			}
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("DELETE FROM " + TABLE
					+ " WHERE " + SERIAL + "= ?");
			s.setLong(1, recordId);
			s.executeUpdate();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public void buildDatabase() throws DorianInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(TABLE)) {
					String certificates = "CREATE TABLE " + TABLE + " ("
							+ SERIAL + " BIGINT PRIMARY KEY," + GID + " TEXT,"
							+ STATUS + " VARCHAR(15) NOT NULL," + NOT_BEFORE
							+ " BIGINT," + NOT_AFTER + " BIGINT," + NOTES
							+ " TEXT," + CERTIFICATE + " TEXT,"
							+ "INDEX document_index (" + SERIAL + "));";
					db.update(certificates);
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper
						.createFaultException(DorianInternalException.class,
								"An unexpected database error occurred.");
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
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"An unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}
}
