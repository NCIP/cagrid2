package org.cagrid.gts.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gts.model.*;
import org.cagrid.gts.service.exception.GTSInternalException;
import org.cagrid.gts.service.exception.IllegalTrustedAuthorityException;
import org.cagrid.gts.service.exception.InvalidTrustedAuthorityException;
import org.cagrid.gts.service.impl.db.DBManager;
import org.cagrid.gts.service.impl.db.TrustedAuthorityTable;
import org.cagrid.gts.service.impl.db.TrustedAuthorityTrustLevelsTable;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author <A HREF="MAILTO:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A HREF="MAILTO:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A HREF="MAILTO:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: TrustedAuthorityManager.java,v 1.1 2006/03/08 19:48:46 langella Exp $
 */
public class TrustedAuthorityManager {

    private Log log;

    private boolean dbBuilt = false;

    private Database db;

    private String gtsURI;

    private TrustLevelLookup lookup;

    private DBManager dbManager;

    public TrustedAuthorityManager(String gtsURI, TrustLevelLookup lookup, DBManager dbManager) {
        log = LogFactory.getLog(this.getClass().getName());
        this.gtsURI = gtsURI;
        this.dbManager = dbManager;
        this.db = dbManager.getDatabase();
        this.lookup = lookup;
    }

    public synchronized TrustedAuthority[] findTrustAuthorities(TrustedAuthorityFilter filter) throws GTSInternalException {

        this.buildDatabase();
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        List<TrustedAuthority> authorities = new ArrayList<TrustedAuthority>();
        TrustedAuthoritySelectStatement select = new TrustedAuthoritySelectStatement();
        select.addSelectField("*");
        try {
            if (filter != null) {

                if (filter.getName() != null) {
                    select.addWhereField(TrustedAuthorityTable.NAME, "=", filter.getName());
                }

                if (filter.getCertificateDN() != null) {
                    select.addWhereField(TrustedAuthorityTable.CERTIFICATE_DN, "=", filter.getCertificateDN());
                }

                if (filter.getStatus() != null) {
                    select.addWhereField(TrustedAuthorityTable.STATUS, "=", filter.getStatus().value());
                }

                if (filter.isIsAuthority() != null) {
                    select.addWhereField(TrustedAuthorityTable.IS_AUTHORITY, "=", String.valueOf(filter.isIsAuthority()));
                }

                if (filter.getAuthorityGTS() != null) {
                    select.addWhereField(TrustedAuthorityTable.AUTHORITY_GTS, "=", filter.getAuthorityGTS());
                }

                if (filter.getSourceGTS() != null) {
                    select.addWhereField(TrustedAuthorityTable.SOURCE_GTS, "=", filter.getSourceGTS());
                }

                if (filter.getLifetime() != null) {
                    if (filter.getLifetime().equals(Lifetime.VALID)) {
                        Calendar cal = new GregorianCalendar();
                        long time = cal.getTimeInMillis();
                        select.addClause("(" + TrustedAuthorityTable.EXPIRES + "=0 OR " + TrustedAuthorityTable.EXPIRES + ">" + time + ")");
                    } else if (filter.getLifetime().equals(Lifetime.EXPIRED)) {
                        Calendar cal = new GregorianCalendar();
                        long time = cal.getTimeInMillis();
                        select.addClause("(" + TrustedAuthorityTable.EXPIRES + "<>0 AND " + TrustedAuthorityTable.EXPIRES + "<" + time + ")");
                    }
                }

            }

            c = db.getConnection();
            s = select.prepareStatement(c);
            rs = s.executeQuery();

            while (rs.next()) {
                String name = rs.getString(TrustedAuthorityTable.NAME);
                TrustLevels levels = filter.getTrustLevels();
                boolean okToAdd = true;
                if (levels != null) {
                    List<String> tl = levels.getTrustLevel();
                    if (tl != null) {
                        for (String level : tl) {
                            if (!this.hasTrustLevels(name, level)) {
                                okToAdd = false;
                                break;
                            }
                        }
                    }
                }
                if (okToAdd) {
                    TrustedAuthority ta = new TrustedAuthority();
                    ta.setName(name);
                    ta.setTrustLevels(getTrustLevels(name));
                    ta.setStatus(Status.fromValue(rs.getString(TrustedAuthorityTable.STATUS)));
                    ta.setIsAuthority(Boolean.valueOf(rs.getBoolean(TrustedAuthorityTable.IS_AUTHORITY)));
                    ta.setAuthorityGTS(rs.getString(TrustedAuthorityTable.AUTHORITY_GTS));
                    ta.setSourceGTS(rs.getString(TrustedAuthorityTable.SOURCE_GTS));
                    ta.setExpires(rs.getLong(TrustedAuthorityTable.EXPIRES));
                    ta.setLastUpdated(rs.getLong(TrustedAuthorityTable.LAST_UPDATED));
                    org.cagrid.gts.model.X509Certificate x509Certificate = new org.cagrid.gts.model.X509Certificate();
                    x509Certificate.setCertificateEncodedString(rs.getString(TrustedAuthorityTable.CERTIFICATE));
                    ta.setCertificate(x509Certificate);
                    String crl = rs.getString(TrustedAuthorityTable.CRL);
                    if ((crl != null) && (crl.trim().length() > 0)) {
                        org.cagrid.gts.model.X509CRL x509crl = new org.cagrid.gts.model.X509CRL();
                        x509crl.setCrlEncodedString(crl);
                        ta.setCRL(x509crl);
                    }
                    authorities.add(ta);
                }

            }
            rs.close();
            s.close();

            TrustedAuthority[] list = new TrustedAuthority[authorities.size()];
            for (int i = 0; i < authorities.size(); i++) {
                list[i] = (TrustedAuthority) authorities.get(i);
            }
            return list;

        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in finding trusted authorities: " + e.getMessage(), e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected error occurred in finding Trusted Authorities");
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

    public synchronized void updateTrustedAuthority(TrustedAuthority ta) throws GTSInternalException, IllegalTrustedAuthorityException,
            InvalidTrustedAuthorityException {
        updateTrustedAuthority(ta, true);
    }

    public synchronized void updateTrustedAuthority(TrustedAuthority ta, boolean internal) throws GTSInternalException, IllegalTrustedAuthorityException,
            InvalidTrustedAuthorityException {

        TrustedAuthority curr = this.getTrustedAuthority(ta.getName());
        StringBuffer sql = new StringBuffer();
        boolean needsUpdate = false;
        UpdateStatement update = new UpdateStatement(TrustedAuthorityTable.TABLE_NAME);
        if (internal) {
            if (!curr.getAuthorityGTS().equals(gtsURI)) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The Trusted Authority cannot be updated, the GTS (" + gtsURI + ") is not its authority!!!");
                throw fault;
            }

            if ((clean(ta.getAuthorityGTS()) != null) && (!ta.getAuthorityGTS().equals(curr.getAuthorityGTS()))) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The authority trust service for a Trusted Authority cannot be changed");
                throw fault;
            }

            if (ta.getCertificate() != null) {
                if ((clean(ta.getCertificate().getCertificateEncodedString()) != null) && (!ta.getCertificate().equals(curr.getCertificate()))) {
                    IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                            "The certificate for a Trusted Authority cannot be changed");
                    throw fault;
                }
            }

            if ((clean(ta.getSourceGTS()) != null) && (!ta.getSourceGTS().equals(curr.getSourceGTS()))) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The source trust service for a Trusted Authority cannot be changed");
                throw fault;
            }

        } else {

            if ((curr.isIsAuthority().booleanValue()) && (!ta.getAuthorityGTS().equals(gtsURI))) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The Trusted Authority " + ta.getName() + " cannot be updated, a conflict was detected, this gts (" + gtsURI
                                + ") was specified as its authority, however the URI of another GTS ( " + ta.getAuthorityGTS() + ") was specified.");
                throw fault;
            }

            if (!ta.getAuthorityGTS().equals(curr.getAuthorityGTS())) {
                update.addField(TrustedAuthorityTable.AUTHORITY_GTS, ta.getAuthorityGTS());
                needsUpdate = true;
            }

            if (ta.getCertificate() != null) {
                if ((clean(ta.getCertificate().getCertificateEncodedString()) != null) && (!ta.getCertificate().equals(curr.getCertificate()))) {
                    X509Certificate cert = checkAndExtractCertificate(ta);
                    if ((!ta.getName().equals(cert.getSubjectDN().toString()))) {
                        IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                                "The Trusted Authority Name must match the subject of the Trusted Authority's certificate");
                        throw fault;
                    }

                    update.addField(TrustedAuthorityTable.CERTIFICATE, ta.getCertificate().getCertificateEncodedString());
                    needsUpdate = true;
                }
            }

            if (!ta.getSourceGTS().equals(curr.getSourceGTS())) {
                update.addField(TrustedAuthorityTable.SOURCE_GTS, ta.getSourceGTS());
                needsUpdate = true;
            }

            if (ta.getExpires() != curr.getExpires()) {
                update.addField(TrustedAuthorityTable.EXPIRES, Long.valueOf(ta.getExpires()));
                needsUpdate = true;
            }

        }

        if ((ta.isIsAuthority() != null) && (!ta.isIsAuthority().equals(curr.isIsAuthority()))) {
            IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                    "The authority trust service for a Trusted Authority cannot be changed");
            throw fault;
        }

        if (ta.getCRL() != null) {
            if ((clean(ta.getCRL().getCrlEncodedString()) != null) && (!ta.getCRL().equals(curr.getCRL()))) {
                TrustedAuthority temp = curr;
                if (ta.getCertificate() != null) {
                    temp = ta;
                }
                X509Certificate cert = checkAndExtractCertificate(temp);
                checkAndExtractCRL(ta, cert);
                update.addField(TrustedAuthorityTable.CRL, ta.getCRL().getCrlEncodedString());
                needsUpdate = true;
            }
        } else {
            if (!internal) {
                if (curr.getCRL() != null) {
                    update.addField(TrustedAuthorityTable.CRL, "");
                    needsUpdate = true;
                }
            }
        }

        if ((ta.getStatus() != null) && (!ta.getStatus().equals(curr.getStatus()))) {
            update.addField(TrustedAuthorityTable.STATUS, ta.getStatus().value());
            needsUpdate = true;
        }
        boolean updateTrustLevels = false;

        if ((ta.getTrustLevels() != null)
                && (!this.areTrustLevelEquals(ta.getTrustLevels().getTrustLevel().toArray(new String[ta.getTrustLevels().getTrustLevel().size()]), curr
                .getTrustLevels().getTrustLevel().toArray(new String[curr.getTrustLevels().getTrustLevel().size()])))) {
            needsUpdate = true;
            updateTrustLevels = true;
        }

        if (!ta.equals(curr)) {
            if (needsUpdate) {
                Connection c = null;
                PreparedStatement s = null;
                try {
                    Calendar cal = new GregorianCalendar();
                    ta.setLastUpdated(cal.getTimeInMillis());
                    update.addField(TrustedAuthorityTable.LAST_UPDATED, Long.valueOf(ta.getLastUpdated()));
                    update.addWhereField(TrustedAuthorityTable.NAME, "=", ta.getName());
                    c = db.getConnection();
                    s = update.prepareUpdateStatement(c);
                    s.execute();
                    s.close();
                } catch (Exception e) {
                    this.log.error(
                            "Unexpected database error incurred in updating " + ta.getName() + ", the following statement generated the error: \n"
                                    + sql.toString() + "\n", e);
                    GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                            "Unexpected error occurred in updating " + ta.getName() + ".");
                    throw fault;
                } finally {
                    if (s != null) {
                        try {
                            s.close();
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                    if (c != null) {
                        db.releaseConnection(c);
                    }
                }
                if (updateTrustLevels) {
                    this.addTrustLevels(ta.getName(), ta.getTrustLevels());
                }
            }
        }

    }

    private String clean(String s) {
        if ((s == null) || (s.trim().length() == 0)) {
            return null;
        } else {
            return s;
        }
    }

    public synchronized TrustedAuthority getTrustedAuthority(String name) throws GTSInternalException, InvalidTrustedAuthorityException {
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = db.getConnection();
            s = c.prepareStatement("select * from " + TrustedAuthorityTable.TABLE_NAME + " where " + TrustedAuthorityTable.NAME + "= ?");
            s.setString(1, name);
            TrustedAuthority ta = null;
            rs = s.executeQuery();
            if (rs.next()) {
                ta = new TrustedAuthority();
                ta.setName(rs.getString(TrustedAuthorityTable.NAME));
                ta.setTrustLevels(getTrustLevels(name));
                ta.setStatus(Status.fromValue(rs.getString(TrustedAuthorityTable.STATUS)));
                ta.setIsAuthority(Boolean.valueOf(rs.getBoolean(TrustedAuthorityTable.IS_AUTHORITY)));
                ta.setAuthorityGTS(rs.getString(TrustedAuthorityTable.AUTHORITY_GTS));
                ta.setSourceGTS(rs.getString(TrustedAuthorityTable.SOURCE_GTS));
                ta.setExpires(rs.getLong(TrustedAuthorityTable.EXPIRES));
                ta.setLastUpdated(rs.getLong(TrustedAuthorityTable.LAST_UPDATED));
                org.cagrid.gts.model.X509Certificate x509Certificate = new org.cagrid.gts.model.X509Certificate();
                x509Certificate.setCertificateEncodedString(rs.getString(TrustedAuthorityTable.CERTIFICATE));
                ta.setCertificate(x509Certificate);
                String crl = rs.getString(TrustedAuthorityTable.CRL);
                if ((crl != null) && (crl.trim().length() > 0)) {
                    org.cagrid.gts.model.X509CRL x509crl = new org.cagrid.gts.model.X509CRL();
                    x509crl.setCrlEncodedString(crl);
                    ta.setCRL(x509crl);
                }

            }
            if (ta != null) {
                return ta;
            }
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in obtaining the Trusted Authority, " + name + ":\n", e);
            GTSInternalException fault = FaultHelper
                    .createFaultException(GTSInternalException.class, "Unexpected error obtaining the TrustedAuthority " + name);
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
        InvalidTrustedAuthorityException fault = FaultHelper.createFaultException(InvalidTrustedAuthorityException.class, "The TrustedAuthority " + name
                + " does not exist.");
        throw fault;
    }

    public synchronized boolean doesTrustedAuthorityExist(String name) throws GTSInternalException {
        this.buildDatabase();
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        boolean exists = false;
        try {
            c = db.getConnection();
            s = c.prepareStatement("select count(*) from " + TrustedAuthorityTable.TABLE_NAME + " where " + TrustedAuthorityTable.NAME
                    + "= ?");
            s.setString(1, name);
           rs = s.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
            rs.close();
            s.close();
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in odetermining if the TrustedAuthority name: \n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected error in determining if the TrustedAuthority " + name + " exists.");
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
        return exists;
    }

    public synchronized void removeTrustedAuthority(String name) throws GTSInternalException, InvalidTrustedAuthorityException {
        if (doesTrustedAuthorityExist(name)) {
            Connection c = null;
            PreparedStatement s = null;
            try {
                this.removeTrustedAuthoritysTrustLevels(name);
                c = db.getConnection();
                s = c.prepareStatement("delete FROM " + TrustedAuthorityTable.TABLE_NAME + " where " + TrustedAuthorityTable.NAME + "= ?");
                s.setString(1, name);
                s.execute();
                s.close();

            } catch (Exception e) {
                this.log.error("Unexpected database error incurred in removing the Trusted Authority, " + name
                        + ", the following statement generated the error: \n", e);
                GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error removing the TrustedAuthority "
                        + name);
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
        } else {
            InvalidTrustedAuthorityException fault = FaultHelper.createFaultException(InvalidTrustedAuthorityException.class, "The TrustedAuthority " + name
                    + " does not exist.");
            throw fault;
        }
    }

    public synchronized void removeLevelFromTrustedAuthorities(String level) throws GTSInternalException {
        buildDatabase();
        Connection c = null;
        PreparedStatement s = null;

        try {
            c = db.getConnection();
            s = c.prepareStatement("delete FROM " + TrustedAuthorityTrustLevelsTable.TABLE_NAME + " where "
                    + TrustedAuthorityTrustLevelsTable.TRUST_LEVEL + "= ?");
            s.setString(1, level);
            s.execute();
            s.close();
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in removing the trust level " + level
                    + " from the Trusted Authorities, the following statement generated the error: \n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error the trust level, " + level
                    + " from the trusted authorites!!!");
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
    }

    private synchronized void insertTrustedAuthority(TrustedAuthority ta, X509Certificate cert, X509CRL crl) throws GTSInternalException {
        StringBuffer insert = new StringBuffer();
        buildDatabase();
        Connection c = null;
        PreparedStatement s = null;
        try {

            Calendar cal = new GregorianCalendar();
            ta.setLastUpdated(cal.getTimeInMillis());
            insert.append("INSERT INTO " + TrustedAuthorityTable.TABLE_NAME + " SET " + TrustedAuthorityTable.NAME + "= ?" + ","
                    + TrustedAuthorityTable.CERTIFICATE_DN + "= ?," + TrustedAuthorityTable.STATUS + "= ?," + TrustedAuthorityTable.IS_AUTHORITY + "= ?,"
                    + TrustedAuthorityTable.AUTHORITY_GTS + "= ?," + TrustedAuthorityTable.SOURCE_GTS + "= ?," + TrustedAuthorityTable.EXPIRES + "= ?,"
                    + TrustedAuthorityTable.LAST_UPDATED + "= ?," + TrustedAuthorityTable.CERTIFICATE + "= ?");

            if (crl != null) {
                insert.append("," + TrustedAuthorityTable.CRL + "= ?");
            }
            c = db.getConnection();
            s = c.prepareStatement(insert.toString());
            s.setString(1, ta.getName());
            s.setString(2, cert.getSubjectDN().toString());
            s.setString(3, ta.getStatus().value());
            s.setString(4, String.valueOf(ta.isIsAuthority().booleanValue()));
            s.setString(5, ta.getAuthorityGTS());
            s.setString(6, ta.getSourceGTS());
            s.setLong(7, ta.getExpires());
            s.setLong(8, ta.getLastUpdated());
            s.setString(9, ta.getCertificate().getCertificateEncodedString());
            if (crl != null) {
                s.setString(10, ta.getCRL().getCrlEncodedString());
            }
            s.execute();
            s.close();
            this.addTrustLevels(ta.getName(), ta.getTrustLevels());
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in adding the Trusted Authority, " + ta.getName()
                    + ", the following statement generated the error: \n" + insert.toString() + "\n", e);
            try {
                this.removeTrustedAuthority(ta.getName());
            } catch (Exception ex) {
                this.log.error(e.getMessage(), e);
            }
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected error adding the Trusted Authority, " + ta.getName() + "!!!");
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
    }

    public synchronized TrustedAuthority addTrustedAuthority(TrustedAuthority ta) throws GTSInternalException, IllegalTrustedAuthorityException {
        return this.addTrustedAuthority(ta, true);
    }

    public synchronized TrustedAuthority addTrustedAuthority(TrustedAuthority ta, boolean internal) throws GTSInternalException,
            IllegalTrustedAuthorityException {
        this.buildDatabase();
        X509Certificate cert = checkAndExtractCertificate(ta);
        if ((ta.getName() != null) && (!ta.getName().equals(cert.getSubjectDN().toString()))) {
            IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                    "The Trusted Authority Name must match the subject of the Trusted Authority's certificate");
            throw fault;
        } else {
            ta.setName(cert.getSubjectDN().toString());
        }

        if (this.doesTrustedAuthorityExist(ta.getName())) {
            IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                    "The Trusted Authority " + ta.getName() + " already exists.");
            throw fault;
        }

        X509CRL crl = checkAndExtractCRL(ta, cert);

        if (ta.getTrustLevels() != null) {
            if (ta.getTrustLevels().getTrustLevel() != null) {
                for (String level : ta.getTrustLevels().getTrustLevel()) {
                    if (!lookup.doesTrustLevelExist(level)) {
                        IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                                "The Trusted Authority " + ta.getName() + " could not be added, the trust level " + level + " does not exist.");
                        throw fault;
                    }
                }
            }
        }
        if (ta.getStatus() == null) {
            IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                    "No status specified for the Trusted Authority!!!");
            throw fault;
        }
        if (internal) {
            ta.setIsAuthority(Boolean.TRUE);
            ta.setAuthorityGTS(gtsURI);
            ta.setSourceGTS(gtsURI);
            ta.setExpires(0);
        } else {
            if ((ta.isIsAuthority() == null)) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The Trusted Authority " + ta.getName()
                                + " cannot be added because it does not specify whether or not this GTS is the authority of it.");
                throw fault;
            }

            if (ta.getAuthorityGTS() == null) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The Trusted Authority " + ta.getName() + " cannot be added because it does not specify an authority trust service.");
                throw fault;

            }

            if (ta.getSourceGTS() == null) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The Trusted Authority " + ta.getName() + " cannot be added because it does not specify an source trust service.");
                throw fault;
            }

            if ((!ta.isIsAuthority().booleanValue()) && (ta.getExpires() <= 0)) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The Trusted Authority " + ta.getName() + " cannot be added because it does not specify an expiration.");
                throw fault;
            }

            if ((ta.isIsAuthority().booleanValue()) && (!ta.getAuthorityGTS().equals(gtsURI))) {
                IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                        "The Trusted Authority " + ta.getName() + " cannot be added, a conflict was detected, this gts (" + gtsURI
                                + ") was specified as its authority, however the URI of another GTS ( " + ta.getAuthorityGTS() + ") was specified.");
                throw fault;
            }

        }
        insertTrustedAuthority(ta, cert, crl);
        return ta;
    }

    private boolean areTrustLevelEquals(String[] levels1, String[] levels2) {
        if ((levels1 == null) && (levels2 == null)) {
            return true;
        } else if ((levels1 != null) && (levels2 == null)) {
            return false;
        } else if ((levels1 == null) && (levels2 != null)) {
            return false;
        } else if (levels1.length != levels2.length) {
            return false;
        } else {
            Set<String> s = new HashSet<String>();
            for (int i = 0; i < levels1.length; i++) {
                s.add(levels1[i]);
            }
            for (int i = 0; i < levels2.length; i++) {
                s.remove(levels2[i]);
            }
            if (s.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean hasTrustLevels(String name, String level) throws GTSInternalException, InvalidTrustedAuthorityException {
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            boolean hasLevel = false;
            c = db.getConnection();
            s = c.prepareStatement("select count(*) from " + TrustedAuthorityTrustLevelsTable.TABLE_NAME + " where "
                    + TrustedAuthorityTrustLevelsTable.NAME + "= ? AND " + TrustedAuthorityTrustLevelsTable.TRUST_LEVEL + "= ?");
            s.setString(1, name);
            s.setString(2, level);
            rs = s.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    hasLevel = true;
                }
            }

            return hasLevel;
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in determining if the Trusted Authority, " + name + " has the trust level " + level + ":\n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected database error incurred in determining if the Trusted Authority, " + name + " has the trust level " + level + "!!!");
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

    public synchronized TrustLevels getTrustLevels(String name) throws GTSInternalException, InvalidTrustedAuthorityException {
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            List<String> list = new ArrayList<String>();
            c = db.getConnection();
            s = c.prepareStatement("select * from " + TrustedAuthorityTrustLevelsTable.TABLE_NAME + " where "
                    + TrustedAuthorityTrustLevelsTable.NAME + "= ?");
            s.setString(1, name);
           rs = s.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(TrustedAuthorityTrustLevelsTable.TRUST_LEVEL));
            }
            rs.close();
            s.close();
            TrustLevels tl = new TrustLevels();
            for (String l : list) {
                tl.getTrustLevel().add(l);
            }

            return tl;
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in getting the trust levels for the Trusted Authority, " + name + ":\n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "Unexpected database error incurred in getting the trust levels for the Trusted Authority, " + name + "!!!");
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

    public synchronized void addTrustLevels(String name, TrustLevels tl) throws GTSInternalException, InvalidTrustedAuthorityException,
            IllegalTrustedAuthorityException {
        if (tl != null) {
            List<String> levels = tl.getTrustLevel();
            if ((levels != null) && (levels.size() > 0)) {
                for (String level : levels) {
                    if (!lookup.doesTrustLevelExist(level)) {
                        IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                                "The trust levels for the Trusted Authority " + name + " could not be updated, the trust level " + level + " does not exist.");
                        throw fault;
                    }
                }
            }
            removeTrustedAuthoritysTrustLevels(name);
            if ((levels != null) && (levels.size() > 0)) {

                Connection c = null;
                try {
                    c = db.getConnection();
                    for (String level : levels) {
                        PreparedStatement s = null;
                        try{
                        s = c.prepareStatement("INSERT INTO " + TrustedAuthorityTrustLevelsTable.TABLE_NAME + " SET "
                                + TrustedAuthorityTrustLevelsTable.NAME + "= ?, " + TrustedAuthorityTrustLevelsTable.TRUST_LEVEL + "= ?");
                        s.setString(1, name);
                        s.setString(2, level);
                        s.execute();
                        s.close();
                        }finally {

                            if (s != null) {
                                try {
                                    s.close();
                                } catch (Exception e) {
                                    log.error(e.getMessage(), e);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    this.log.error("Unexpected database error incurred in adding the trust levels for the Trusted Authority, " + name + ": " + e.getMessage(),
                            e);
                    try {
                        this.removeTrustedAuthoritysTrustLevels(name);
                    } catch (Exception ex) {
                        this.log.error(ex.getMessage(), ex);
                    }
                    GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error removing the TrustedAuthority "
                            + name);
                    throw fault;
                } finally {

                    db.releaseConnection(c);
                }
            }
        }
    }

    public synchronized void removeTrustedAuthoritysTrustLevels(String name) throws GTSInternalException, InvalidTrustedAuthorityException {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = db.getConnection();
            s = c.prepareStatement("delete FROM " + TrustedAuthorityTrustLevelsTable.TABLE_NAME + " where "
                    + TrustedAuthorityTrustLevelsTable.NAME + "= ?");
            s.setString(1, name);
            s.execute();
            s.close();
        } catch (Exception e) {
            this.log.error("Unexpected database error incurred in removing the trust levels for the Trusted Authority, " + name
                    + ", the following statement generated the error: \n", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error removing the TrustedAuthority " + name);
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
    }

    private X509Certificate checkAndExtractCertificate(TrustedAuthority ta) throws IllegalTrustedAuthorityException {
        if (ta.getCertificate() == null) {
            IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class, "No certificate specified!!!");
            throw fault;
        }

        if (ta.getCertificate().getCertificateEncodedString() == null) {
            IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class, "No certificate specified!!!");
            throw fault;
        }

        try {
            return CertUtil.loadCertificate(ta.getCertificate().getCertificateEncodedString());
        } catch (Exception ex) {
            IllegalTrustedAuthorityException fault = FaultHelper
                    .createFaultException(IllegalTrustedAuthorityException.class, "Invalid certificate Provided!!!");
            throw fault;
        }
    }

    private X509CRL checkAndExtractCRL(TrustedAuthority ta, X509Certificate signer) throws IllegalTrustedAuthorityException {
        X509CRL crl = null;
        if (ta.getCRL() != null) {

            if (ta.getCRL().getCrlEncodedString() != null) {
                try {
                    crl = CertUtil.loadCRL(ta.getCRL().getCrlEncodedString());
                } catch (Exception ex) {
                    IllegalTrustedAuthorityException fault = FaultHelper
                            .createFaultException(IllegalTrustedAuthorityException.class, "Invalid CRL provided!!!");
                    throw fault;
                }
                try {
                    crl.verify(signer.getPublicKey());
                } catch (Exception e) {
                    IllegalTrustedAuthorityException fault = FaultHelper.createFaultException(IllegalTrustedAuthorityException.class,
                            "The CRL provided is not signed by the Trusted Authority!!!");
                    throw fault;
                }

            }
        }

        return crl;
    }

    public synchronized void buildDatabase() throws GTSInternalException {
        if (!dbBuilt) {
            try {
                db.createDatabase();
                if (!this.db.tableExists(TrustedAuthorityTable.TABLE_NAME)) {
                    String sql = dbManager.getTrustedAuthorityTable().getCreateTableSQL();
                    db.update(sql);
                }

                if (!this.db.tableExists(TrustedAuthorityTrustLevelsTable.TABLE_NAME)) {
                    String sql = dbManager.getTrustedAuthorityTrustLevelsTable().getCreateTableSQL();
                    db.update(sql);
                }
                dbBuilt = true;
            } catch (Exception e) {
                this.log.error("Unexpected error in creating the database.", e);
                GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error in creating the database.");
                throw fault;
            }
        }
    }

    public synchronized void clearDatabase() throws GTSInternalException {
        try {
            buildDatabase();
            db.update("delete FROM " + TrustedAuthorityTable.TABLE_NAME);
            db.update("delete FROM " + TrustedAuthorityTrustLevelsTable.TABLE_NAME);
        } catch (Exception e) {
            this.log.error("Unexpected error in removing the database.", e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Unexpected error in removing the database.");
            throw fault;
        }
    }
}
