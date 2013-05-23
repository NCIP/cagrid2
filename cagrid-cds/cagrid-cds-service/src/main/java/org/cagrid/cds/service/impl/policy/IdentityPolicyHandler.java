package org.cagrid.cds.service.impl.policy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.cds.model.AllowedParties;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationPolicy;
import org.cagrid.cds.model.IdentityDelegationPolicy;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.InvalidPolicyException;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.tools.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IdentityPolicyHandler implements PolicyHandler {

	private final static String TABLE = "identity_policies";
	private final static String DELEGATION_ID = "delegation_id";
	private final static String GRID_IDENTITY = "grid_identity";

	private boolean dbBuilt = false;

	private Database db;
	private Log log;

	public IdentityPolicyHandler(Database db) {
		this.log = LogFactory.getLog(this.getClass().getName());
		this.db = db;
	}

	public void removePolicy(DelegationIdentifier id) throws CDSInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = this.db.getConnection();
			PreparedStatement s = c.prepareStatement("DELETE FROM " + TABLE
					+ "  WHERE " + DELEGATION_ID + "= ?");
			s.setLong(1, id.getDelegationId());
			s.execute();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		} finally {
			this.db.releaseConnection(c);
		}
	}

	public DelegationPolicy getPolicy(DelegationIdentifier id)
			throws CDSInternalException, InvalidPolicyException {
		if (policyExists(id)) {
			List<String> parties = new ArrayList<String>();
			Connection c = null;
			try {
				c = this.db.getConnection();
				PreparedStatement s = c.prepareStatement("select "
						+ GRID_IDENTITY + " from " + TABLE + " WHERE "
						+ DELEGATION_ID + "= ? ");
				s.setLong(1, id.getDelegationId());
				ResultSet rs = s.executeQuery();
				while (rs.next()) {
					parties.add(rs.getString(1));
				}
				rs.close();
				s.close();
				IdentityDelegationPolicy policy = new IdentityDelegationPolicy();
				AllowedParties ap = new AllowedParties();
                ap.getGridIdentity().addAll(parties);
				policy.setAllowedParties(ap);
				return policy;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
                throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
			} finally {
				this.db.releaseConnection(c);
			}
		} else {
            throw Errors.makeException(InvalidPolicyException.class, "The requested policy does not exist.");
		}
	}

	public boolean isAuthorized(DelegationIdentifier id, String gridIdentity)
			throws CDSInternalException {
		boolean isAuthorized = false;
		Connection c = null;
		try {
			c = this.db.getConnection();
			PreparedStatement s = c.prepareStatement("select count(*) "
					+ " from " + TABLE + " WHERE " + DELEGATION_ID + "= ? AND "
					+ GRID_IDENTITY + "= ?");
			s.setLong(1, id.getDelegationId());
			s.setString(2, gridIdentity);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					isAuthorized = true;
				}
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		} finally {
			this.db.releaseConnection(c);
		}
		return isAuthorized;
	}

	public void storePolicy(DelegationIdentifier id, DelegationPolicy pol)
			throws CDSInternalException, InvalidPolicyException {
		this.buildDatabase();
		if (!isSupported(pol.getClass().getName())) {
            throw Errors.makeException(InvalidPolicyException.class, "The policy handler " + getClass().getName()
                    + " does not support the policy "
                    + pol.getClass().getName() + ".");
		}
		if (this.policyExists(id)) {
            throw Errors.makeException(InvalidPolicyException.class, "A policy already exists for the delegation "
                    + id.getDelegationId());
		}

		IdentityDelegationPolicy policy = (IdentityDelegationPolicy) pol;

		Connection c = null;
		boolean policyStored = false;
		try {
			c = this.db.getConnection();

			AllowedParties ap = policy.getAllowedParties();

			if (ap != null) {
                for(String party : ap.getGridIdentity()) {
                    PreparedStatement s = c.prepareStatement("INSERT INTO "
                            + TABLE + " SET " + DELEGATION_ID + "= ?, "
                            + GRID_IDENTITY + "= ?");
                    s.setLong(1, id.getDelegationId());
                    s.setString(2, party);
                    s.execute();
                    s.close();
                    policyStored = true;
                }
			}

		} catch (Exception e) {
			try {
				this.removePolicy(id);
			} catch (Exception ex) {

			}
			log.error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		} finally {
			this.db.releaseConnection(c);
		}

		if (!policyStored) {
            throw Errors.makeException(InvalidPolicyException.class, "No allowed parties provided.");
		}
	}

	public void removeAllStoredPolicies() throws CDSInternalException {
		buildDatabase();
		try {
			this.db.update("DELETE FROM " + TABLE);
			dbBuilt = false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		}

	}

	public boolean isSupported(String policyClassName) {
		if (policyClassName.equals(IdentityDelegationPolicy.class.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean policyExists(DelegationIdentifier id)
			throws CDSInternalException {
		buildDatabase();
		try {
			return db.exists(TABLE, DELEGATION_ID, id.getDelegationId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
		}
	}

	private void buildDatabase() throws CDSInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(TABLE)) {
					String table = "CREATE TABLE " + TABLE + " ("
							+ DELEGATION_ID + " BIGINT NOT NULL,"
							+ GRID_IDENTITY
							+ " VARCHAR(255) NOT NULL, INDEX document_index ("
							+ DELEGATION_ID + "));";
					this.db.update(table);
				}
				dbBuilt = true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
                throw Errors.makeException(CDSInternalException.class, "Unexpected Database Error.", e);
			}

		}
	}

}
