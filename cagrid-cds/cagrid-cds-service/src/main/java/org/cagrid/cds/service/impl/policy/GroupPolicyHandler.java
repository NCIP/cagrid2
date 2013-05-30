package org.cagrid.cds.service.impl.policy;

import gov.nih.nci.cagrid.common.Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationPolicy;
import org.cagrid.cds.model.GroupDelegationPolicy;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.InvalidPolicyException;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.gridgrouper.client.GridGrouperClient;
import org.cagrid.tools.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GroupPolicyHandler implements PolicyHandler {
	private final static String TABLE = "group_policies";
	private final static String DELEGATION_ID = "delegation_id";
	private final static String GRID_GROUPER_URL = "grid_grouper_url";
	private final static String GROUP_SYSTEM_NAME = "group_name";

	private boolean dbBuilt = false;

	private Database db;

	private Log log;
    private GridGrouperClient grouper;

	public GroupPolicyHandler(Database db, GridGrouperClient grouper) {
		this.log = LogFactory.getLog(this.getClass().getName());
		this.db = db;
        this.grouper = grouper;
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

	public DelegationPolicy getPolicy(DelegationIdentifier id)
			throws CDSInternalException, InvalidPolicyException {
		if (policyExists(id)) {
			Connection c = null;
			try {
				GroupDelegationPolicy policy = new GroupDelegationPolicy();
				c = this.db.getConnection();
				PreparedStatement s = c.prepareStatement("select *  from "
						+ TABLE + " WHERE " + DELEGATION_ID + "= ? ");
				s.setLong(1, id.getDelegationId());
				ResultSet rs = s.executeQuery();
				if (rs.next()) {
					policy.setGridGrouperServiceURL(rs
							.getString(GRID_GROUPER_URL));
					policy.setGroupName(rs.getString(GROUP_SYSTEM_NAME));
				}
				rs.close();
				s.close();
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
		try {
			GroupDelegationPolicy policy = (GroupDelegationPolicy) getPolicy(id);
			return grouper.isMemberOf(gridIdentity, policy.getGroupName());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw Errors.makeException(CDSInternalException.class, "Unexpected Error.", e);
		}
	}

	public boolean isSupported(String policyClassName) {
		if (policyClassName.equals(GroupDelegationPolicy.class.getName())) {
			return true;
		} else {
			return false;
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

		GroupDelegationPolicy policy = (GroupDelegationPolicy) pol;
		if (Utils.clean(policy.getGridGrouperServiceURL()) == null) {
            throw Errors.makeException(InvalidPolicyException.class, "Invalid Grid Grouper Service URL specified.");
        }

		if (Utils.clean(policy.getGroupName()) == null) {
            throw Errors.makeException(InvalidPolicyException.class, "Invalid Group Name specified.");
        }

		try {
			grouper.getGroup(policy.getGroupName());
		} catch (Exception e) {
            throw Errors.makeException(InvalidPolicyException.class, "Could not resolve the group "
                    + policy.getGroupName() + " on the Grid Grouper "
                    + policy.getGridGrouperServiceURL() + ".", e);
		}

		Connection c = null;

		try {
			c = this.db.getConnection();
			PreparedStatement s = c.prepareStatement("INSERT INTO " + TABLE
					+ " SET " + DELEGATION_ID + "= ?, " + GRID_GROUPER_URL
					+ "= ?," + GROUP_SYSTEM_NAME + "= ?");
			s.setLong(1, id.getDelegationId());
			s.setString(2, policy.getGridGrouperServiceURL());
			s.setString(3, policy.getGroupName());
			s.execute();
			s.close();

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
	}

	private void buildDatabase() throws CDSInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(TABLE)) {
					String table = "CREATE TABLE " + TABLE + " ("
							+ DELEGATION_ID + " BIGINT NOT NULL,"
							+ GRID_GROUPER_URL + " VARCHAR(255) NOT NULL,"
							+ GROUP_SYSTEM_NAME
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
