package org.cagrid.cds.service.impl.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.ClientDelegationFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditRecord;
import org.cagrid.cds.model.DelegatedCredentialEvent;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationPolicy;
import org.cagrid.cds.model.DelegationRecord;
import org.cagrid.cds.model.DelegationRecordFilter;
import org.cagrid.cds.model.DelegationRequest;
import org.cagrid.cds.model.DelegationSigningRequest;
import org.cagrid.cds.model.DelegationSigningResponse;
import org.cagrid.cds.model.DelegationStatus;
import org.cagrid.cds.model.ExpirationStatus;
import org.cagrid.cds.model.ProxyLifetime;
import org.cagrid.cds.model.PublicKey;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.cds.service.impl.util.ProxyPolicy;
import org.cagrid.cds.service.impl.util.Utils;
import org.cagrid.cds.service.impl.policy.PolicyHandler;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.InvalidPolicyException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.gaards.pki.CertificateExtensionsUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gaards.pki.ProxyCreator;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.Event;
import org.cagrid.tools.events.EventAuditor;
import org.cagrid.tools.events.EventManager;
import org.cagrid.tools.events.InvalidHandlerException;
import org.globus.gsi.CertUtil;
import org.globus.gsi.CertificateRevocationLists;
import org.globus.gsi.TrustedCertificates;
import org.globus.gsi.bc.BouncyCastleUtil;
import org.globus.gsi.proxy.ProxyPathValidator;

import java.security.KeyPair;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DelegatedCredentialManager {

	public static int DELEGATION_BUFFER_SECONDS = 120;
	private final static String TABLE = "delegated_credentials";
	private final static String DELEGATION_ID = "DELEGATION_ID";
	private final static String GRID_IDENTITY = "GRID_IDENTITY";
	private final static String STATUS = "STATUS";
	private final static String POLICY_TYPE = "POLICY_TYPE";
	private final static String EXPIRATION = "EXPIRATION";
	private final static String DELEGATION_PATH_LENGTH = "DELEGATION_PATH_LENGTH";
	private final static String DATE_INITIATED = "DATE_INITIATED";
	private final static String DATE_APPROVED = "DATE_APPROVED";
	private final static String PROXY_LIFETIME_HOURS = "PROXY_LIFETIME_HOURS";
	private final static String PROXY_LIFETIME_MINUTES = "PROXY_LIFETIME_MINUTES";
	private final static String PROXY_LIFETIME_SECONDS = "PROXY_LIFETIME_SECONDS";
	private final static String DELEGATED_CREDENTIAL_AUDITOR = "delegatedCredentialAuditor";

	public static int PROXY_EXPIRATION_BUFFER_SECONDS = 5;

	private Database db;
	private boolean dbBuilt = false;
	private List<PolicyHandler> handlers;
	private Log log;
	private KeyManager keyManager;
	private ProxyPolicy proxyPolicy;
	private EventManager events;
	private EventAuditor delegationAuditor;

	public DelegatedCredentialManager(Database db, PropertyManager properties,
			KeyManager keyManager, List<PolicyHandler> policyHandlers,
			ProxyPolicy proxyPolicy, EventManager events)
			throws CDSInternalException {
		this.db = db;
		this.log = LogFactory.getLog(this.getClass().getName());
		this.handlers = policyHandlers;
		this.proxyPolicy = proxyPolicy;
		this.events = events;
		try {
			this.delegationAuditor = (EventAuditor) events
					.getEventHandler(DELEGATED_CREDENTIAL_AUDITOR);
		} catch (InvalidHandlerException e) {
			throw Errors.makeException(CDSInternalException.class, "Could not initialize the delegation credential manager, could not obtain the delegated credential auditor.", e);
		}
		String currentKeyManager = properties.getKeyManager();
		if ((currentKeyManager != null)
				&& (!currentKeyManager.equals(keyManager.getClass().getName()))) {
			throw Errors.makeException(CDSInternalException.class, Errors.KEY_MANAGER_CHANGED);
		}
		this.keyManager = keyManager;
		if (currentKeyManager == null) {
			properties.setKeyManager(this.keyManager.getClass().getName());
		}
	}

	public PolicyHandler findHandler(String policyClassName)
			throws InvalidPolicyException {
		PolicyHandler handler = null;
		boolean handlerFound = false;
		for (int i = 0; i < handlers.size(); i++) {
			if (handlers.get(i).isSupported(policyClassName)) {
				if (!handlerFound) {
					handler = handlers.get(i);
					handlerFound = true;
				} else {
					throw Errors.makeException(InvalidPolicyException.class, Errors.MULTIPLE_HANDLERS_FOUND_FOR_POLICY
                            + policyClassName
                            + ", cannot decide which handler to employ.");
				}
			}
		}
		if (!handlerFound) {
			throw Errors.makeException(InvalidPolicyException.class, Errors.DELEGATION_POLICY_NOT_SUPPORTED);
		}
		return handler;
	}

	public List<DelegatedCredentialAuditRecord> searchAuditLog(
			DelegatedCredentialAuditFilter f) throws CDSInternalException {
		try {
			String targetId = null;
			if (f.getDelegationIdentifier() != null) {
				targetId = String.valueOf(f.getDelegationIdentifier()
						.getDelegationId());
			}

			String eventType = null;
			if (f.getEvent() != null) {
				eventType = f.getEvent().value();
			}

			Date start = null;
			if (f.getStartDate() != null) {
				start = new Date(f.getStartDate().longValue());
			}
			Date end = null;

			if (f.getEndDate() != null) {
				end = new Date(f.getEndDate().longValue());
			}
			List<Event> events = this.delegationAuditor.findEvents(targetId, f
					.getSourceGridIdentity(), eventType, start, end, null);
			List<DelegatedCredentialAuditRecord> records = new ArrayList<DelegatedCredentialAuditRecord>();
			for (int i = 0; i < events.size(); i++) {
                DelegatedCredentialAuditRecord record = new DelegatedCredentialAuditRecord();
				DelegationIdentifier id = new DelegationIdentifier();
				id.setDelegationId(Long.valueOf(events.get(i).getTargetId()).longValue());
				record.setDelegationIdentifier(id);
				record.setEvent(DelegatedCredentialEvent.fromValue(events
						.get(i).getEventType()));
				record.setMessage(events.get(i).getMessage());
				record.setOccurredAt(events.get(i).getOccurredAt());
				record.setSourceGridIdentity(events.get(i)
						.getReportingPartyId());
                records.add(record);
			}

			return records;
		} catch (Exception e) {
			throw Errors.makeException(CDSInternalException.class, "An unexpected error occurred in searching the audit logs.", e);
		}
	}

	public List<DelegationRecord> findCredentialsDelegatedToClient(
			String callerIdentity, ClientDelegationFilter filter)
			throws CDSInternalException {
		DelegationRecordFilter f = new DelegationRecordFilter();
		f.setDelegationStatus(DelegationStatus.APPROVED);
		f.setExpirationStatus(ExpirationStatus.VALID);

		if (filter != null) {
			f.setGridIdentity(filter.getGridIdentity());
		}

		List<DelegationRecord> records = findDelegatedCredentials(f);
		if (records != null) {
			List<DelegationRecord> list = new ArrayList<DelegationRecord>();
            for (DelegationRecord record : records) {
                try {
                    PolicyHandler handler = this.findHandler(record.getDelegationPolicy().getClass().getName());
                    if (handler.isAuthorized(record.getDelegationIdentifier(), callerIdentity)) {
                        list.add(record);
                    }
                } catch (Exception e) {
                    log.error(e);
                }
            }
			return list;
		} else {
			return Collections.emptyList();
		}
	}

	public synchronized DelegationSigningRequest initiateDelegation(
			String callerGridIdentity, DelegationRequest request)
			throws CDSInternalException, DelegationException, InvalidPolicyException {
		this.buildDatabase();
		DelegationPolicy policy = request.getDelegationPolicy();
		PolicyHandler handler = this.findHandler(policy.getClass().getName());
		if (!this.proxyPolicy.isKeySizeSupported(request.getKeyLength())) {
			throw Errors.makeException(DelegationException.class, Errors.INVALID_KEY_LENGTH_SPECIFIED);
		}

		if (request.getIssuedCredentialLifetime() == null) {
            throw Errors.makeException(DelegationException.class, Errors.PROXY_LIFETIME_NOT_SPECIFIED);
		}

		if ((request.getIssuedCredentialPathLength() < 0)
				|| (this.proxyPolicy.getMaxDelegationPathLength() < request
						.getIssuedCredentialPathLength())) {
            throw Errors.makeException(DelegationException.class, Errors.INVALID_DELEGATION_PATH_LENGTH_SPECIFIED);
		}

		Connection c = null;
		long delegationId = -1;
		try {
			c = this.db.getConnection();
			PreparedStatement s = c.prepareStatement("INSERT INTO " + TABLE
					+ " SET " + GRID_IDENTITY + "= ?, " + POLICY_TYPE + "= ?, "
					+ STATUS + "= ?," + DATE_INITIATED + "=?," + DATE_APPROVED
					+ "=?," + EXPIRATION + "=?," + DELEGATION_PATH_LENGTH
					+ "=?," + PROXY_LIFETIME_HOURS + "=?,"
					+ PROXY_LIFETIME_MINUTES + "=?," + PROXY_LIFETIME_SECONDS
					+ "=?");
			s.setString(1, callerGridIdentity);
			s.setString(2, policy.getClass().getName());
			s.setString(3, DelegationStatus.PENDING.value());
			s.setLong(4, new Date().getTime());
			s.setLong(5, 0);
			s.setLong(6, 0);
			s.setInt(7, request.getIssuedCredentialPathLength());
			s.setInt(8, request.getIssuedCredentialLifetime().getHours());
			s.setInt(9, request.getIssuedCredentialLifetime().getMinutes());
			s.setInt(10, request.getIssuedCredentialLifetime().getSeconds());
			s.execute();
			s.close();
			delegationId = db.getLastAutoId(c);
			DelegationIdentifier id = new DelegationIdentifier();
			id.setDelegationId(delegationId);
			// Create and Store Key Pair.
			KeyPair keys = this.keyManager.createAndStoreKeyPair(String
					.valueOf(delegationId), request.getKeyLength());
			handler.storePolicy(id, policy);
			DelegationSigningRequest req = new DelegationSigningRequest();
			req.setDelegationIdentifier(id);
			PublicKey publicKey = new PublicKey();
			publicKey.setKeyAsString(KeyUtil.writePublicKey(keys.getPublic()));
			req.setPublicKey(publicKey);
			logEvent(delegationId, callerGridIdentity,
					DelegatedCredentialEvent.DELEGATION_INITIATED,
					"Delegation initiated for " + callerGridIdentity + ".");
			return req;
		} catch (CDSInternalException e) {
			try {
				this.delete(delegationId);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
			throw e;
		} catch (InvalidPolicyException e) {
			try {
				this.delete(delegationId);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				this.delete(delegationId);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
			throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
		} finally {
			db.releaseConnection(c);
		}
	}

	private void logEvent(long delegationId, String callerGridIdentity,
			DelegatedCredentialEvent event, String message)
			throws CDSInternalException {
		try {
			events.logEvent(String.valueOf(delegationId), callerGridIdentity,
					event.value(), message);
		} catch (Exception e) {
			throw Errors.makeException(CDSInternalException.class,
							"Unexpected error encountered in establishing the audit trail.",
							e);
		}
	}

	public boolean delegationExists(DelegationIdentifier id)
			throws CDSInternalException {
		try {
			if (id == null) {
				return false;
			} else {
				return db.exists(TABLE, DELEGATION_ID, id.getDelegationId());
			}
		} catch (Exception e) {
			throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
		}
	}

	public DelegationRecord getDelegationRecord(DelegationIdentifier id)
			throws CDSInternalException, DelegationException {

		if (delegationExists(id)) {
			DelegationRecord r = new DelegationRecord();
			r.setDelegationIdentifier(id);
			Connection c = null;
			try {
				c = this.db.getConnection();
				PreparedStatement s = c.prepareStatement("select * from "
						+ TABLE + " WHERE " + DELEGATION_ID + "= ?");
				s.setLong(1, id.getDelegationId());
				ResultSet rs = s.executeQuery();
				if (rs.next()) {
					r.setDateApproved(rs.getLong(DATE_APPROVED));
					r.setDateInitiated(rs.getLong(DATE_INITIATED));
					r.setDelegationStatus(DelegationStatus.fromValue(rs
							.getString(STATUS)));
					r.setExpiration(rs.getLong(EXPIRATION));
					r.setGridIdentity(rs.getString(GRID_IDENTITY));
					r.setIssuedCredentialPathLength(rs
							.getInt(DELEGATION_PATH_LENGTH));
					ProxyLifetime lifetime = new ProxyLifetime();
					lifetime.setHours(rs.getInt(PROXY_LIFETIME_HOURS));
					lifetime.setMinutes(rs.getInt(PROXY_LIFETIME_MINUTES));
					lifetime.setSeconds(rs.getInt(PROXY_LIFETIME_SECONDS));
					r.setIssuedCredentialLifetime(lifetime);
				}
				rs.close();
				s.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
			} finally {
				this.db.releaseConnection(c);
			}

			try {
				X509Certificate[] certs = this.keyManager
						.getCertificates(String.valueOf(id.getDelegationId()));
				r.setCertificateChain(Utils
                        .toCertificateChain(certs));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors.makeException(CDSInternalException.class,
                        Errors.UNEXPECTED_ERROR_LOADING_CERTIFICATE_CHAIN, e);
			}
			try {
				PolicyHandler handler = this.findHandler(getPolicyType(id
						.getDelegationId()));
				r.setDelegationPolicy(handler.getPolicy(id));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors.makeException(CDSInternalException.class,
                        Errors.UNEXPECTED_ERROR_LOADING_DELEGATION_POLICY, e);
			}

			return r;
		} else {
			throw Errors.makeException(DelegationException.class, Errors.DELEGATION_RECORD_DOES_NOT_EXIST);
		}
	}

	public synchronized DelegationIdentifier approveDelegation(
			String callerGridIdentity, DelegationSigningResponse res)
			throws CDSInternalException, DelegationException, PermissionDeniedException {
		DelegationIdentifier id = res.getDelegationIdentifier();
		if (this.delegationExists(id)) {
			DelegationRecord r = getDelegationRecord(id);

			if (!r.getDelegationStatus().equals(DelegationStatus.PENDING)) {
				throw Errors.makeException(DelegationException.class, Errors.CANNOT_APPROVE_INVALID_STATUS);
			}

			Calendar c = new GregorianCalendar();
			c.setTimeInMillis(r.getDateInitiated());
			c.add(Calendar.SECOND, DELEGATION_BUFFER_SECONDS);
			Date d = new Date();
			if (d.after(c.getTime())) {
				throw Errors.makeException(DelegationException.class, Errors.DELEGATION_APPROVAL_BUFFER_EXPIRED);
			}

			// Check to make sure that the entity that initiated the delegation
			// is the same entity that is approving it.
			if (!r.getGridIdentity().equals(callerGridIdentity)) {
				throw Errors.makeException(DelegationException.class, Errors.INITIATOR_DOES_NOT_MATCH_APPROVER);
			}
			CertificateChain chain = res.getCertificateChain();
			if (chain == null) {
				throw Errors.makeException(DelegationException.class, Errors.CERTIFICATE_CHAIN_NOT_SPECIFIED);
			}
			X509Certificate[] certs = null;
			try {
				certs = Utils.toCertificateArray(chain);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors.makeException(CDSInternalException.class,
                        Errors.UNEXPECTED_ERROR_LOADING_CERTIFICATE_CHAIN, e);
			}

			if (certs.length < 2) {
				throw Errors.makeException(DelegationException.class, Errors.INSUFFICIENT_CERTIFICATE_CHAIN_SPECIFIED);
			}

			// Check that the public keys match.
			java.security.PublicKey publicKey = this.keyManager
					.getPublicKey(String.valueOf(id.getDelegationId()));
			if (!certs[0].getPublicKey().equals(publicKey)) {
				throw Errors.makeException(DelegationException.class, Errors.PUBLIC_KEY_DOES_NOT_MATCH);
			}

			// Check delegation path length
			try {
				if (CertUtil.isProxy(BouncyCastleUtil
                        .getCertificateType(certs[0]))) {
					int currLength = r.getIssuedCredentialPathLength();
					for (int i = 0; i < certs.length; i++) {
						if (CertUtil.isProxy(BouncyCastleUtil
                                .getCertificateType(certs[i]))) {
							int delegationPathLength = CertificateExtensionsUtil
									.getDelegationPathLength(certs[i]);
							int maxLength = delegationPathLength - 1;
							if (maxLength < currLength) {
								throw Errors.makeException(DelegationException.class, Errors.INSUFFICIENT_DELEGATION_PATH_LENGTH);
							}
							currLength = delegationPathLength;
						}
					}
				} else {
					throw Errors.makeException(DelegationException.class, Errors.CERTIFICATE_CHAIN_DOES_NOT_CONTAIN_PROXY);
				}

				try {
					ProxyPathValidator validator = new ProxyPathValidator();
					validator.validate(certs, TrustedCertificates
							.getDefaultTrustedCertificates().getCertificates(),
							CertificateRevocationLists
									.getDefaultCertificateRevocationLists());

				} catch (Exception e) {
					throw Errors.makeException(DelegationException.class,
                            Errors.INVALID_CERTIFICATE_CHAIN, e);
				}

				// Check to make sure the Identity of the proxy cert matches the
				// Identity of the initiator.

				try {
					if (!BouncyCastleUtil.getIdentity(certs).equals(
							r.getGridIdentity())) {
						throw Errors.makeException(DelegationException.class, Errors.IDENTITY_DOES_NOT_MATCH_INITIATOR);
					}
				} catch (CertificateException e) {
					log.error(e.getMessage(), e);
					throw Errors
							.makeException(CDSInternalException.class,
                                    Errors.UNEXPECTED_ERROR_EXTRACTING_IDENTITY_FROM_CERTIFICATE_CHAIN,
                                    e);
				}

			} catch (CDSInternalException e) {
				throw e;
			} catch (DelegationException e) {
				throw e;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors
						.makeException(CDSInternalException.class,
                                Errors.UNEXPECTED_ERROR_DETERMINING_DELEGATION_PATH_LENGTH,
                                e);
			}

			this.keyManager.storeCertificates(String.valueOf(id
					.getDelegationId()), certs);
			Connection conn = null;
			try {
				Date now = new Date();
				conn = this.db.getConnection();
				PreparedStatement s = conn.prepareStatement("update " + TABLE
						+ " SET " + STATUS + "=?," + EXPIRATION + "=?,"
						+ DATE_APPROVED + "=?" + " WHERE " + DELEGATION_ID
						+ "= ?");
				s.setString(1, DelegationStatus.APPROVED.value());
				s.setLong(2, Utils.getEarliestExpiration(certs).getTime());
				s.setLong(3, now.getTime());
				s.setLong(4, id.getDelegationId());
				s.executeUpdate();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
			} finally {
				this.db.releaseConnection(conn);
			}
			logEvent(id.getDelegationId(), callerGridIdentity,
					DelegatedCredentialEvent.DELEGATION_APPROVED,
					"The delegated credential for " + callerGridIdentity
							+ " has been approved.");
			return id;
		} else {
			throw Errors.makeException(DelegationException.class, Errors.DELEGATION_RECORD_DOES_NOT_EXIST);
		}

	}

	public void updateDelegatedCredentialStatus(String callerGridIdentity,
			DelegationIdentifier id, DelegationStatus status)
			throws CDSInternalException, DelegationException {
		if (this.delegationExists(id)) {
			if (status.equals(DelegationStatus.PENDING)) {
				throw Errors.makeException(DelegationException.class, Errors.CANNOT_CHANGE_STATUS_TO_PENDING);
			}
			DelegationRecord r = getDelegationRecord(id);
			Connection conn = null;
			try {
				conn = this.db.getConnection();
				PreparedStatement s = conn.prepareStatement("update " + TABLE
						+ " SET " + STATUS + "=?" + " WHERE " + DELEGATION_ID
						+ "= ?");
				s.setString(1, status.value());
				s.setLong(2, id.getDelegationId());
				s.executeUpdate();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
			} finally {
				this.db.releaseConnection(conn);
			}

			logEvent(id.getDelegationId(), callerGridIdentity,
					DelegatedCredentialEvent.DELEGATION_STATUS_UPDATED,
					"Delegation Status changed from "
							+ r.getDelegationStatus().value() + " to "
							+ status.value());
		} else {
			throw Errors.makeException(DelegationException.class, Errors.DELEGATION_RECORD_DOES_NOT_EXIST);
		}

	}

	public CertificateChain getDelegatedCredential(String gridIdentity,
			DelegationIdentifier id, PublicKey publicKey)
			throws CDSInternalException, DelegationException, PermissionDeniedException {
		if (delegationExists(id)) {
			DelegationRecord r = this.getDelegationRecord(id);

			if (!r.getDelegationStatus().equals(DelegationStatus.APPROVED)) {
				logEvent(
						id.getDelegationId(),
						gridIdentity,
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
						Errors.CANNOT_GET_INVALID_STATUS);
				throw Errors.makeException(DelegationException.class, Errors.CANNOT_GET_INVALID_STATUS);
			}

			PolicyHandler handler = null;
			try {
				handler = this.findHandler(r.getDelegationPolicy().getClass()
						.getName());
			} catch (Exception e) {
				logEvent(
						id.getDelegationId(),
						gridIdentity,
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
						Errors.POLICY_HANDLER_NOT_FOUND);
				throw Errors.makeException(CDSInternalException.class, Errors.POLICY_HANDLER_NOT_FOUND,
                        e);
			}
			if (!handler.isAuthorized(id, gridIdentity)) {
				logEvent(
						id.getDelegationId(),
						gridIdentity,
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
						Errors.PERMISSION_DENIED_TO_DELEGATED_CREDENTIAL);
				throw Errors
						.makeException(PermissionDeniedException.class, Errors.PERMISSION_DENIED_TO_DELEGATED_CREDENTIAL);
			}
			Date now = new Date();
			Date expiration = new Date(r.getExpiration());
			if (now.after(expiration)) {
				logEvent(
						id.getDelegationId(),
						gridIdentity,
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
						Errors.SIGNING_CREDENTIAL_EXPIRED);
				throw Errors.makeException(DelegationException.class, Errors.SIGNING_CREDENTIAL_EXPIRED);
			}

			X509Certificate[] certs = null;
			try {
				certs = Utils.toCertificateArray(r.getCertificateChain());

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				logEvent(
						id.getDelegationId(),
						gridIdentity,
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
						Errors.UNEXPECTED_ERROR_LOADING_CERTIFICATE_CHAIN);
				throw Errors.makeException(CDSInternalException.class,
                        Errors.UNEXPECTED_ERROR_LOADING_CERTIFICATE_CHAIN, e);
			}

			try {

				java.security.PublicKey pkey = KeyUtil.loadPublicKey(publicKey
                        .getKeyAsString());
				int length = ((RSAPublicKey) pkey).getModulus().bitLength();
				if (!this.proxyPolicy.isKeySizeSupported(length)) {
					logEvent(
							id.getDelegationId(),
							gridIdentity,
							DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
							Errors.INVALID_KEY_LENGTH_SPECIFIED);
					throw Errors.makeException(DelegationException.class, Errors.INVALID_KEY_LENGTH_SPECIFIED);
				}

				int hours = 0;
				int minutes = 0;
				int seconds = 0;
				Calendar c = new GregorianCalendar();
				c.add(Calendar.HOUR_OF_DAY, r.getIssuedCredentialLifetime()
						.getHours());
				c.add(Calendar.MINUTE, r.getIssuedCredentialLifetime()
						.getMinutes());
				c.add(Calendar.SECOND, r.getIssuedCredentialLifetime()
						.getSeconds()
						+ PROXY_EXPIRATION_BUFFER_SECONDS);

				if (c.getTime().after(certs[0].getNotAfter())) {
					Calendar expires = new GregorianCalendar();
					expires.setTimeInMillis(certs[0].getNotAfter().getTime());
					long diff = (certs[0].getNotAfter().getTime() - System
							.currentTimeMillis()) / 1000;
					if (diff > PROXY_EXPIRATION_BUFFER_SECONDS) {
						seconds = (int) diff - PROXY_EXPIRATION_BUFFER_SECONDS;
					} else {
						logEvent(
								id.getDelegationId(),
								gridIdentity,
								DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
								Errors.SIGNING_CREDENTIAL_ABOUT_EXPIRE);
						throw Errors.makeException(DelegationException.class, Errors.SIGNING_CREDENTIAL_ABOUT_EXPIRE);
					}
				} else {
					hours = r.getIssuedCredentialLifetime().getHours();
					minutes = r.getIssuedCredentialLifetime().getMinutes();
					seconds = r.getIssuedCredentialLifetime().getSeconds();
				}

				X509Certificate[] proxy = ProxyCreator
						.createImpersonationProxyCertificate(certs,
                                this.keyManager.getPrivateKey(String.valueOf(id
                                        .getDelegationId())), pkey, hours,
                                minutes, seconds, r
                                .getIssuedCredentialPathLength());
				logEvent(id.getDelegationId(), gridIdentity,
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ISSUED,
						"A credential was issued to " + gridIdentity
								+ ".  The credential will expire on "
								+ proxy[0].getNotAfter().toString() + ".");
				return Utils.toCertificateChain(proxy);
			} catch (DelegationException f) {
				throw f;
			} catch (Exception e) {
				throw Errors.makeException(CDSInternalException.class,
                        Errors.UNEXPECTED_ERROR_CREATING_PROXY, e);
			}

		} else {
			throw Errors.makeException(DelegationException.class, Errors.DELEGATION_RECORD_DOES_NOT_EXIST);
		}
	}

	public List<DelegationRecord> findDelegatedCredentials(DelegationRecordFilter f)
			throws CDSInternalException {
		this.buildDatabase();
		Connection c = null;
		try {
			c = this.db.getConnection();
			List<DelegationRecord> records = new ArrayList<DelegationRecord>();
			boolean filterAdded = false;
			StringBuffer sql = new StringBuffer();
			sql.append("select " + DELEGATION_ID + " from " + TABLE);

			if (f.getDelegationIdentifier() != null) {
				if (!filterAdded) {
					sql.append(" WHERE ");
					filterAdded = true;
				} else {
					sql.append(" AND ");
				}
				sql.append(DELEGATION_ID + " = ?");
			}

			if (f.getGridIdentity() != null) {
				if (!filterAdded) {
					sql.append(" WHERE ");
					filterAdded = true;
				} else {
					sql.append(" AND ");
				}
				sql.append(GRID_IDENTITY + " = ?");
			}

			if (f.getDelegationStatus() != null) {
				if (!filterAdded) {
					sql.append(" WHERE ");
					filterAdded = true;
				} else {
					sql.append(" AND ");
				}
				sql.append(STATUS + " = ?");
			}

			if (f.getExpirationStatus() != null) {
				if (!filterAdded) {
					sql.append(" WHERE ");
					filterAdded = true;
				} else {
					sql.append(" AND ");
				}
				if (f.getExpirationStatus().equals(ExpirationStatus.VALID)) {
					sql.append(EXPIRATION + " > ? AND EXPIRATION>0");
				} else {
					sql.append(EXPIRATION + " < ? AND EXPIRATION>0");
				}
			}

			PreparedStatement s = c.prepareStatement(sql.toString());
			int count = 0;

			if (f.getDelegationIdentifier() != null) {
				count = count + 1;
				s.setLong(count, f.getDelegationIdentifier().getDelegationId());
			}

			if (f.getGridIdentity() != null) {
				count = count + 1;
				s.setString(count, f.getGridIdentity());
			}

			if (f.getDelegationStatus() != null) {
				count = count + 1;
				s.setString(count, f.getDelegationStatus().value());
			}

			if (f.getExpirationStatus() != null) {
				count = count + 1;
				Date now = new Date();
				s.setLong(count, now.getTime());
			}

			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				DelegationIdentifier id = new DelegationIdentifier();
				id.setDelegationId(rs.getLong(DELEGATION_ID));
				records.add(getDelegationRecord(id));
			}
			rs.close();
			s.close();
			return records;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
		} finally {
			this.db.releaseConnection(c);
		}
	}

	private String getPolicyType(long delegationId) throws CDSInternalException {
		Connection c = null;
		String policyType = null;
		try {
			c = this.db.getConnection();
			PreparedStatement s = c.prepareStatement("select " + POLICY_TYPE
					+ " from " + TABLE + " WHERE " + DELEGATION_ID + "= ?");
			s.setLong(1, delegationId);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				policyType = rs.getString(POLICY_TYPE);
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
		} finally {
			this.db.releaseConnection(c);
		}
		return policyType;
	}

	public synchronized void delete(DelegationIdentifier id)
			throws CDSInternalException {
		this.delete(id.getDelegationId());
	}

	public synchronized void delete(long delegationId) throws CDSInternalException {
		buildDatabase();
		Connection c = null;
		try {
			String policyType = getPolicyType(delegationId);
			PolicyHandler handler = findHandler(policyType);
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("DELETE FROM " + TABLE
					+ "  WHERE " + DELEGATION_ID + "= ?");
			s.setLong(1, delegationId);
			s.execute();
			s.close();
			this.keyManager.delete(String.valueOf(delegationId));
			DelegationIdentifier id = new DelegationIdentifier();
			id.setDelegationId(delegationId);
			handler.removePolicy(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
		} finally {
			db.releaseConnection(c);
		}
		try {
			List<Event> list = this.delegationAuditor.findEvents(String
					.valueOf(delegationId), null, null, null, null, null);
			for (int i = 0; i < list.size(); i++) {
				this.delegationAuditor.deleteEvent(list.get(i).getEventId());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class,
					"An inexpected error occurred in deleting the audit log.",
					e);
		}
	}

	public void clearDatabase() throws CDSInternalException {
		buildDatabase();
		try {
			for (int i = 0; i < handlers.size(); i++) {
				this.handlers.get(i).removeAllStoredPolicies();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		try {
			this.keyManager.deleteAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		try {
			events.clearHandlers();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		try {
			db.update("DELETE FROM " + TABLE);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
		}
		dbBuilt = false;

	}

	private void buildDatabase() throws CDSInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(TABLE)) {
					String trust = "CREATE TABLE " + TABLE + " ("
							+ DELEGATION_ID
							+ " BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
							+ GRID_IDENTITY + " VARCHAR(255) NOT NULL,"
							+ POLICY_TYPE + " VARCHAR(255) NOT NULL," + STATUS
							+ " VARCHAR(50) NOT NULL," + DATE_INITIATED
							+ " BIGINT," + DATE_APPROVED + " BIGINT,"
							+ DELEGATION_PATH_LENGTH + " INT,"
							+ PROXY_LIFETIME_HOURS + " INT,"
							+ PROXY_LIFETIME_MINUTES + " INT,"
							+ PROXY_LIFETIME_SECONDS + " INT," + EXPIRATION
							+ " BIGINT, INDEX document_index (" + DELEGATION_ID
							+ "));";
					db.update(trust);
				}

				dbBuilt = true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw Errors.makeException(CDSInternalException.class, Errors.UNEXPECTED_DATABASE_ERROR, e);
			}
		}
	}

}
