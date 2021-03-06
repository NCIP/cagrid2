package org.cagrid.gts.service.impl;

import gov.nih.nci.cagrid.common.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.gts.model.AuthorityGTS;
import org.cagrid.gts.model.AuthorityPrioritySpecification;
import org.cagrid.gts.model.AuthorityPriorityUpdate;
import org.cagrid.gts.model.TimeToLive;
import org.cagrid.gts.service.exception.GTSInternalException;
import org.cagrid.gts.service.exception.IllegalAuthorityException;
import org.cagrid.gts.service.exception.InvalidAuthorityException;
import org.cagrid.gts.service.impl.db.AuthorityTable;
import org.cagrid.gts.service.impl.db.DBManager;
import org.cagrid.gts.wsrf.stubs.AddAuthorityRequest;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella Exp $
 */

public class GTSAuthorityManager {
    private Log log;
    private boolean dbBuilt = false;
    private Database db;
    private DBManager dbManager;
    private String gtsURI;
    private AuthoritySyncTime syncTime;

    public GTSAuthorityManager(String gtsURI, AuthoritySyncTime syncTime, DBManager dbManager) {
        log = LogFactory.getLog(this.getClass().getName());
        this.dbManager = dbManager;
        this.db = dbManager.getDatabase();
        this.gtsURI = gtsURI;
        this.syncTime = syncTime;
    }

    public synchronized AuthorityGTS getAuthority(String gtsURI) throws GTSInternalException, InvalidAuthorityException {
        this.buildDatabase();
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = db.getConnection();
            s = c.prepareStatement("select * from " + AuthorityTable.TABLE_NAME + " where " + AuthorityTable.GTS_URI + "= ?");
            s.setString(1, gtsURI);
            rs = s.executeQuery();
            AuthorityGTS gts = null;
            if (rs.next()) {
                gts = new AuthorityGTS();
                gts.setServiceURI(rs.getString(AuthorityTable.GTS_URI));
                gts.setPerformAuthorization(rs.getBoolean(AuthorityTable.PERFORM_AUTH));
                gts.setPriority(rs.getInt(AuthorityTable.PRIORITY));
                gts.setServiceIdentity(Utils.clean(rs.getString(AuthorityTable.GTS_IDENTITY)));
                gts.setSyncTrustLevels(rs.getBoolean(AuthorityTable.SYNC_TRUST_LEVELS));
                TimeToLive ttl = new TimeToLive();
                ttl.setHours(rs.getInt(AuthorityTable.TTL_HOURS));
                ttl.setMinutes(rs.getInt(AuthorityTable.TTL_MINUTES));
                ttl.setSeconds(rs.getInt(AuthorityTable.TTL_SECONDS));
                gts.setTimeToLive(ttl);
            }

            if(gts != null){
                return gts;
            }


        } catch (Exception e) {
            this.log.fatal("Unexpected database error incurred in obtaining the authority, " + gtsURI + ", the following statement generated the error: \n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error obtaining the authority " + gtsURI);
            throw fault;
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }

            try {
                db.releaseConnection(c);
            } catch (Exception exception) {
                this.log.error(exception.getMessage(), exception);
            }
        }
        InvalidAuthorityException fault = FaultHelper.createFaultException(InvalidAuthorityException.class, "The authority " + gtsURI + " does not exist.");
        throw fault;
    }

    public synchronized void updateAuthorityPriorities(AuthorityPriorityUpdate update) throws GTSInternalException, IllegalAuthorityException {

        AuthorityGTS[] auths = this.getAuthorities();
        // Create HashMap
        Map<String, AuthorityGTS> map = new HashMap<String, AuthorityGTS>();
        for (int i = 0; i < auths.length; i++) {
            map.put(auths[i].getServiceURI(), auths[i]);
        }
        // Verfiy that all authorities are accounted for
        List<AuthorityPrioritySpecification> specs = update.getAuthorityPrioritySpecification();
        for (AuthorityPrioritySpecification spec : specs) {
            map.remove(spec.getServiceURI());
        }

        if (map.size() > 0) {
            StringBuffer error = new StringBuffer();
            error.append("Cannot update the authority priorities, an incomplete authority list was provided.\n The provided list was missing the following authorities:\n");
            Iterator<String> itr = map.keySet().iterator();
            while (itr.hasNext()) {
                error.append((String) itr.next() + "\n");
            }
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class, error.toString());
            throw fault;
        }
        // Validate priorities
        int count = this.getAuthorityCount();
        for (int i = 1; i <= count; i++) {
            int found = 0;
            for (int j = 0; j < specs.size(); j++) {
                if (i == specs.get(j).getPriority()) {
                    found = found + 1;
                }
            }
            if (found < 1) {
                IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class,
                        "Cannot update the authority priorities, no authority specified with the priority " + i
                                + ", each authority must be assigned a unique priority between 1 and " + count + "!!!");
                throw fault;
            } else if (found > 1) {
                IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class,
                        "Cannot update the authority priorities, multiple authorities specified with the priority " + i
                                + ", each authority must be assigned a unique priority between 1 and " + count + "!!!");
                throw fault;
            }
        }

        Connection c = null;
        try {
            c = db.getConnection();
            c.setAutoCommit(false);
            for (int i = 0; i < specs.size(); i++) {
                updateAuthorityPriority(c, specs.get(i).getServiceURI(), specs.get(i).getPriority());
            }
            c.commit();
        } catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.log.error("Unexpected database error incurred in updating the authority priorities!!!", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected error in updating the authority priorities!!!");
            throw fault;
        } finally {
            try {
                if (c != null) {
                    c.setAutoCommit(true);
                }
            } catch (Exception e) {

            }
            try {
                db.releaseConnection(c);
            } catch (Exception exception) {
                this.log.error(exception.getMessage(), exception);
            }
        }

    }

    protected synchronized void updateAuthorityPriority(Connection c, String uri, int priority) throws GTSInternalException, InvalidAuthorityException {
        this.buildDatabase();
        if (!doesAuthorityExist(uri)) {

        } else {
            PreparedStatement update = null;
            try {
                update = c.prepareStatement("UPDATE " + AuthorityTable.TABLE_NAME + " SET " + AuthorityTable.PRIORITY + " = ? WHERE "
                        + AuthorityTable.GTS_URI + " = ?");
                update.setInt(1, priority);
                update.setString(2, uri);
                update.executeUpdate();

            } catch (Exception e) {
                this.log.error("Unexpected database error incurred in updating the priority for the authority, " + uri + ".", e);
                GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                        "Unexpected error occurred in updating the priority for the authority, " + uri + ".");
                throw fault;
            }finally {

                if (update != null) {
                    try {
                        update.close();
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }

    }

    public synchronized void updateAuthority(AuthorityGTS gts) throws GTSInternalException, IllegalAuthorityException, InvalidAuthorityException {
        this.buildDatabase();
        if (Utils.clean(gts.getServiceURI()) == null) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class,
                    "The Authority cannot be updated, no service URI specified!!!");
            throw fault;
        }

        validateTimeToLive(gts.getTimeToLive());

        if ((gts.isPerformAuthorization()) && (Utils.clean(gts.getServiceIdentity()) == null)) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class, "The Authority, " + gts.getServiceURI()
                    + " cannot be updated, when authorization is required a service identity must be specified!!!");
            throw fault;
        }

        AuthorityGTS curr = getAuthority(gts.getServiceURI());
        if (curr.getPriority() != gts.getPriority()) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class, "The Authority, " + gts.getServiceURI()
                    + " cannot be updated, priorities cannot be updated using this method, use the update priorities method!!!");
            throw fault;
        }

        Connection c = null;
        PreparedStatement update = null;
        try {
            c = db.getConnection();
            if (!gts.equals(curr)) {
                update = c.prepareStatement("UPDATE " + AuthorityTable.TABLE_NAME + " SET " + AuthorityTable.PRIORITY + " = ?, "
                        + AuthorityTable.SYNC_TRUST_LEVELS + " = ?, " + AuthorityTable.TTL_HOURS + " = ?, " + AuthorityTable.TTL_MINUTES + " = ?, "
                        + AuthorityTable.TTL_SECONDS + " = ?, " + AuthorityTable.PERFORM_AUTH + " = ?, " + AuthorityTable.GTS_IDENTITY + " = ? WHERE "
                        + AuthorityTable.GTS_URI + " = ?");
                update.setInt(1, gts.getPriority());
                update.setString(2, String.valueOf(gts.isSyncTrustLevels()));
                update.setInt(3, gts.getTimeToLive().getHours());
                update.setInt(4, gts.getTimeToLive().getMinutes());
                update.setInt(5, gts.getTimeToLive().getSeconds());
                update.setString(6, String.valueOf(gts.isPerformAuthorization()));
                update.setString(7, gts.getServiceIdentity());
                update.setString(8, gts.getServiceURI());
                update.executeUpdate();
            }
        } catch (Exception e) {

            this.log.error("Unexpected database error incurred in updating the authority " + gts.getServiceURI() + "!!!", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected error in updating the authority " + gts.getServiceURI() + "!!!");
            throw fault;
        } finally {

            if (update != null) {
                try {
                    update.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            try {
                db.releaseConnection(c);
            } catch (Exception exception) {
                this.log.error(exception.getMessage(), exception);
            }
        }

    }

    private synchronized AuthorityGTS[] getAuthoritiesEqualToOrAfter(int priority) throws GTSInternalException {
        this.buildDatabase();

        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        try {
            c = db.getConnection();
            s = c.prepareStatement("select " + AuthorityTable.GTS_URI + " from " + AuthorityTable.TABLE_NAME + " WHERE "
                    + AuthorityTable.PRIORITY + ">= ? ORDER BY " + AuthorityTable.PRIORITY + "");
            s.setInt(1, priority);
            rs = s.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(AuthorityTable.GTS_URI));
            }

            AuthorityGTS[] gts = new AuthorityGTS[list.size()];
            for (int i = 0; i < gts.length; i++) {
                String uri = (String) list.get(i);
                gts[i] = this.getAuthority(uri);
            }
            return gts;

        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in getting the authorities, the following statement generated the error: \n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error occurred in getting the authorities.");
            throw fault;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            try {
                db.releaseConnection(c);
            } catch (Exception exception) {
                this.log.error(exception.getMessage(), exception);
            }
        }

    }

    public synchronized AuthorityGTS[] getAuthorities() throws GTSInternalException {
        this.buildDatabase();

        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        try {
            c = db.getConnection();
            s = c.createStatement();
            sql.append("select " + AuthorityTable.GTS_URI + " from " + AuthorityTable.TABLE_NAME + " ORDER BY " + AuthorityTable.PRIORITY + "");
            rs = s.executeQuery(sql.toString());
            while (rs.next()) {
                list.add(rs.getString(AuthorityTable.GTS_URI));
            }

            AuthorityGTS[] gts = new AuthorityGTS[list.size()];
            for (int i = 0; i < gts.length; i++) {
                String uri = (String) list.get(i);
                gts[i] = this.getAuthority(uri);
            }
            return gts;

        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in getting the authorities, the following statement generated the error: \n" + sql.toString()
                    + "\n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error occurred in getting the authorities.");
            throw fault;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            try {
                db.releaseConnection(c);
            } catch (Exception exception) {
                this.log.error(exception.getMessage(), exception);
            }
        }

    }

    public synchronized int getAuthorityCount() throws GTSInternalException {
        this.buildDatabase();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try {
            c = db.getConnection();
            s = c.createStatement();
            sql.append("select COUNT(*) from " + AuthorityTable.TABLE_NAME);
            rs = s.executeQuery(sql.toString());
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            return count;
        } catch (Exception e) {
            this.log.error(
                    "Unexpected database error incurred in getting the authority count, the following statement generated the error: \n" + sql.toString()
                            + "\n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected error occurred in getting the authority count.");
            throw fault;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            db.releaseConnection(c);
        }

    }

    public synchronized void addAuthority(AuthorityGTS gts) throws GTSInternalException, IllegalAuthorityException {
        this.buildDatabase();
        if (Utils.clean(gts.getServiceURI()) == null) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class,
                    "The Authority cannot be added, no service URI specified!!!");
            throw fault;
        }

        if (gts.getServiceURI().equals(gtsURI)) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class,
                    "The Authority cannot be added, a GTS cannot be its own authority!!!");
            throw fault;
        }

        validateTimeToLive(gts.getTimeToLive());

        if ((gts.isPerformAuthorization()) && (Utils.clean(gts.getServiceIdentity()) == null)) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class, "The Authority, " + gts.getServiceURI()
                    + " cannot be added, when authorization is required a service identity must be specified!!!");
            throw fault;
        }

        if (doesAuthorityExist(gts.getServiceURI())) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class, "The Authority, " + gts.getServiceURI()
                    + " cannot be added, it already exists!!!");
            throw fault;
        }

        // Validate the Priority (greater than 1 and not more than the count)
        int count = this.getAuthorityCount() + 1;
        if ((gts.getPriority() < 1) || (gts.getPriority() > count)) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class, "The Authority, " + gts.getServiceURI()
                    + " cannot be added, invalid priority specified the priority must be between 1 and " + count + "!!!");
            throw fault;
        }

        Connection c = null;
        PreparedStatement insert = null;
        try {
            c = db.getConnection();
            c.setAutoCommit(false);
            // Get the current list of Authorities
            AuthorityGTS[] list = this.getAuthoritiesEqualToOrAfter(gts.getPriority());
            for (int i = 0; i < list.length; i++) {
                this.updateAuthorityPriority(c, list[i].getServiceURI(), (list[i].getPriority() + 1));
            }

            insert = c.prepareStatement("INSERT INTO " + AuthorityTable.TABLE_NAME + " SET " + AuthorityTable.GTS_URI + " = ?, "
                    + AuthorityTable.PRIORITY + " = ?, " + AuthorityTable.SYNC_TRUST_LEVELS + " = ?, " + AuthorityTable.TTL_HOURS + " = ?, "
                    + AuthorityTable.TTL_MINUTES + " = ?, " + AuthorityTable.TTL_SECONDS + " = ?, " + AuthorityTable.PERFORM_AUTH + " = ?, "
                    + AuthorityTable.GTS_IDENTITY + " = ?");
            insert.setString(1, gts.getServiceURI());
            insert.setInt(2, gts.getPriority());
            insert.setString(3, String.valueOf(gts.isSyncTrustLevels()));
            insert.setInt(4, gts.getTimeToLive().getHours());
            insert.setInt(5, gts.getTimeToLive().getMinutes());
            insert.setInt(6, gts.getTimeToLive().getSeconds());
            insert.setString(7, String.valueOf(gts.isPerformAuthorization()));
            insert.setString(8, gts.getServiceIdentity());
            insert.executeUpdate();
            c.commit();
        } catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.log.error("Unexpected database error incurred in adding the authority " + gts.getServiceURI() + "!!!", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected error in adding the authority " + gts.getServiceURI() + "!!!");
            throw fault;
        } finally {
            try {
                if (c != null) {
                    c.setAutoCommit(true);
                }
            } catch (Exception e) {

            }


            if (insert != null) {
                try {
                    insert.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }

            db.releaseConnection(c);
        }

    }

    public synchronized void removeAuthority(String uri) throws GTSInternalException, InvalidAuthorityException {
        this.buildDatabase();
        AuthorityGTS gts = getAuthority(uri);
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = db.getConnection();
            c.setAutoCommit(false);
            // Get the current list of Authorities
            AuthorityGTS[] list = this.getAuthoritiesEqualToOrAfter(gts.getPriority());
            for (int i = 0; i < list.length; i++) {
                this.updateAuthorityPriority(c, list[i].getServiceURI(), (list[i].getPriority() - 1));
            }

           s = c.prepareStatement("DELETE FROM " + AuthorityTable.TABLE_NAME + " WHERE " + AuthorityTable.GTS_URI + " = ?");
           s.setString(1, uri);
           s.executeUpdate();
            c.commit();
        } catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.log.error("Unexpected database error incurred in deleting the authority " + uri + "!!!", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error in deleting the authority " + uri
                    + "!!!");
            throw fault;
        } finally {
            try {
                if (c != null) {
                    c.setAutoCommit(true);
                }
            } catch (Exception e) {

            }

            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            db.releaseConnection(c);
        }

    }

    public synchronized boolean doesAuthorityExist(String gtsURI) throws GTSInternalException {
        this.buildDatabase();
        Connection c = null;
        PreparedStatement s = null;
        boolean exists = false;
        try {
            c = db.getConnection();

            s = c.prepareStatement("select count(*) from " + AuthorityTable.TABLE_NAME + " where " + AuthorityTable.GTS_URI + "= ?");
            s.setString(1, gtsURI);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
            rs.close();
            s.close();
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in determining if the Authority GTS " + gtsURI
                    + " exists, the following statement generated the error: \n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error in determining if the Authority GTS "
                    + gtsURI + " exists.");
            throw fault;
        } finally {

            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            db.releaseConnection(c);
        }
        return exists;
    }

    public synchronized void clearDatabase() throws GTSInternalException {
        try {
            this.buildDatabase();
            db.update("delete FROM " + AuthorityTable.TABLE_NAME);
        } catch (Exception e) {
            this.log.error("Unexpected error in destroying the database.", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error in destroying the database.");
            throw fault;
        }
    }

    public synchronized void buildDatabase() throws GTSInternalException {
        if (!dbBuilt) {
            try {
                db.createDatabase();
                if (!this.db.tableExists(AuthorityTable.TABLE_NAME)) {
                    String trust = dbManager.getAuthorityTable().getCreateTableSQL();
                    db.update(trust);
                }
                dbBuilt = true;
            } catch (Exception e) {
                this.log.error("Unexpected error in creating the database.", e);
                GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error in creating the database.");
                throw fault;
            }
        }
    }

    private void validateTimeToLive(TimeToLive ttl) throws IllegalAuthorityException {
        if (ttl == null) {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class,
                    "The Authority cannot be added, no time to live specified!!!");
            throw fault;
        }
        if (syncTime != null) {
            Calendar c = new GregorianCalendar();
            Calendar c2 = new GregorianCalendar();
            c2.setTimeInMillis(c.getTimeInMillis());
            c.add(Calendar.HOUR, syncTime.getHours());
            c.add(Calendar.MINUTE, syncTime.getMinutes());
            c.add(Calendar.SECOND, syncTime.getSeconds());

            c2.add(Calendar.HOUR, ttl.getHours());
            c2.add(Calendar.MINUTE, ttl.getMinutes());
            c2.add(Calendar.SECOND, ttl.getSeconds());

            if (c2.before(c)) {
                IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class, "The time to live (" + ttl.getHours()
                        + " hour(s), " + ttl.getMinutes() + " minute(s), and " + ttl.getSeconds() + " second(s)"
                        + "), is shorter than how often the GTS syncs with its authorities.\n The gts syncs withs authorities every " + syncTime.getHours()
                        + " hour(s), " + syncTime.getMinutes() + " minute(s), and " + syncTime.getSeconds() + " second(s).");
                throw fault;
            }
        } else {
            IllegalAuthorityException fault = FaultHelper.createFaultException(IllegalAuthorityException.class,
                    "The Authority cannot be added, this GTS is not configured to sync with authorities!!!");
            throw fault;
        }

    }
}
