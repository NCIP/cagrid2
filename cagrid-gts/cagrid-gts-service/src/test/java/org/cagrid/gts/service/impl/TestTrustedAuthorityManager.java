package org.cagrid.gts.service.impl;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.bouncycastle.asn1.x509.CRLReason;
import org.cagrid.gaards.pki.CRLEntry;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gts.model.Lifetime;
import org.cagrid.gts.model.Status;
import org.cagrid.gts.model.TrustLevels;
import org.cagrid.gts.model.TrustedAuthority;
import org.cagrid.gts.model.TrustedAuthorityFilter;
import org.cagrid.gts.model.X509CRL;
import org.cagrid.gts.model.X509Certificate;
import org.cagrid.gts.service.exception.GTSInternalException;
import org.cagrid.gts.service.exception.IllegalTrustedAuthorityException;
import org.cagrid.gts.service.exception.InvalidTrustedAuthorityException;
import org.cagrid.gts.service.impl.db.DBManager;
import org.cagrid.gts.service.impl.db.TrustedAuthorityTable;
import org.cagrid.gts.service.impl.test.CA;
import org.cagrid.gts.service.impl.test.Utils;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella Exp $
 */
public class TestTrustedAuthorityManager extends TestCase implements TrustLevelLookup {

    private final static String LEVEL_ONE = "ONE";

    private final static String LEVEL_TWO = "TWO";

    private final static String LEVEL_THREE = "THREE";

    private DBManager db;

    public void testCreateAndDestroy() {

        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            assertTrue(db.getDatabase().tableExists(TrustedAuthorityTable.TABLE_NAME));
            trust.clearDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testExpiringExternalTrustedAuthorities() {

        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CA ca = new CA();
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());

            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
            ta.setIsAuthority(Boolean.FALSE);
            ta.setSourceGTS("Some Source");
            ta.setAuthorityGTS("Some Authority");
            Calendar c = new GregorianCalendar();
            c.add(Calendar.SECOND, 4);
            ta.setExpires(c.getTimeInMillis());
            trust.addTrustedAuthority(ta, false);
            TrustedAuthorityFilter f = new TrustedAuthorityFilter();
            f.setName(ta.getName());
            f.setLifetime(Lifetime.VALID);
            assertEquals(1, trust.findTrustAuthorities(f).length);
            assertEquals(ta, trust.findTrustAuthorities(f)[0]);

            Thread.sleep(4100);
            assertEquals(0, trust.findTrustAuthorities(f).length);
            f.setLifetime(Lifetime.EXPIRED);
            assertEquals(1, trust.findTrustAuthorities(f).length);
            assertEquals(ta, trust.findTrustAuthorities(f)[0]);

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void testAddUpdateAndRemoveExternalTrustedAuthorities() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            int count = 12;
            String dnPrefix = "O=Organization ABC,OU=Unit XYZ,CN=Certificate Authority";
            String[] authorityGTS = new String[4];
            authorityGTS[0] = "Authority 1";
            authorityGTS[1] = "Authority 2";
            authorityGTS[2] = "Authority 3";
            authorityGTS[3] = "Authority 4";
            Calendar c = new GregorianCalendar();
            c.add(Calendar.HOUR, 1);

            long[] expires = new long[2];
            expires[0] = 3;
            expires[1] = c.getTimeInMillis();

            TrustedAuthority[] auths = new TrustedAuthority[count];
            for (int i = 0; i < count; i++) {
                String dn = dnPrefix + i;
                int authIndex = i % 2;
                int sourceIndex = i % 4;
                int authCount = (i / 2) + 1;
                int sourceCount = (i / 4) + 1;
                int expiresIndex = i % 2;
                int validCount = (i / 2) + 1;

                CA ca = new CA(dn);
                String name = ca.getCertificate().getSubjectDN().toString();
                BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
                CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
                ca.updateCRL(entry);
                auths[i] = new TrustedAuthority();
                auths[i].setName(name);
                X509Certificate x509 = new X509Certificate();
                x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
                auths[i].setCertificate(x509);
                X509CRL crl = new X509CRL();
                crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
                auths[i].setCRL(crl);
                auths[i].setStatus(Status.TRUSTED);
                auths[i].setTrustLevels(toTrustLevels(LEVEL_ONE));
                auths[i].setIsAuthority(Boolean.FALSE);
                auths[i].setAuthorityGTS(authorityGTS[authIndex]);
                auths[i].setSourceGTS(authorityGTS[sourceIndex]);
                auths[i].setExpires(expires[expiresIndex]);
                trust.addTrustedAuthority(auths[i], false);
                assertEquals(auths[i], trust.getTrustedAuthority(auths[i].getName()));
                TrustedAuthority[] tas = trust.findTrustAuthorities(new TrustedAuthorityFilter());
                assertEquals(tas.length, (i + 1));
                TrustedAuthorityFilter f = new TrustedAuthorityFilter();
                f.setName(auths[i].getName());
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(auths[i], trust.findTrustAuthorities(f)[0]);

                TrustedAuthorityFilter f2 = new TrustedAuthorityFilter();
                f2.setAuthorityGTS(authorityGTS[authIndex]);
                assertEquals(authCount, trust.findTrustAuthorities(f2).length);

                TrustedAuthorityFilter f3 = new TrustedAuthorityFilter();
                f3.setSourceGTS(authorityGTS[sourceIndex]);
                assertEquals(sourceCount, trust.findTrustAuthorities(f3).length);

                TrustedAuthorityFilter f4 = new TrustedAuthorityFilter();
                if (authIndex == 0) {
                    f4.setLifetime(Lifetime.EXPIRED);
                } else {
                    f4.setLifetime(Lifetime.VALID);
                }
                assertEquals(validCount, trust.findTrustAuthorities(f4).length);

                TrustedAuthorityFilter f5 = new TrustedAuthorityFilter();
                f5.setTrustLevels(toTrustLevels(LEVEL_ONE));
                assertEquals((i + 1), trust.findTrustAuthorities(f5).length);

                TrustedAuthorityFilter f6 = new TrustedAuthorityFilter();
                f6.setStatus(Status.TRUSTED);
                assertEquals((i + 1), trust.findTrustAuthorities(f6).length);

            }

            // Test Update

            authorityGTS[0] = "Updated Authority 1";
            authorityGTS[1] = "Updated Authority 2";
            authorityGTS[2] = "Updated Authority 3";
            authorityGTS[3] = "Updated Authority 4";
            int validCount = count / 2;
            int expiresCount = count / 2;
            for (int i = 0; i < count; i++) {
                String dn = dnPrefix + i;
                int authIndex = i % 2;
                int sourceIndex = i % 4;
                int authCount = (i / 2) + 1;
                int sourceCount = (i / 4) + 1;
                int expiresIndex = i % 2;
                if (expiresIndex == 1) {
                    validCount = validCount - 1;
                    expiresCount = expiresCount + 1;
                }

                CA ca = new CA(dn);
                BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
                CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
                ca.updateCRL(entry);
                X509Certificate x509 = new X509Certificate();
                x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
                auths[i].setCertificate(x509);
                X509CRL crl = new X509CRL();
                crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
                auths[i].setCRL(crl);
                auths[i].setStatus(Status.SUSPENDED);
                auths[i].setTrustLevels(toTrustLevels(LEVEL_TWO));
                auths[i].setIsAuthority(Boolean.FALSE);
                auths[i].setAuthorityGTS(authorityGTS[authIndex]);
                auths[i].setSourceGTS(authorityGTS[sourceIndex]);
                auths[i].setExpires(10);
                trust.updateTrustedAuthority(auths[i], false);
                assertEquals(auths[i], trust.getTrustedAuthority(auths[i].getName()));
                TrustedAuthority[] tas = trust.findTrustAuthorities(new TrustedAuthorityFilter());
                assertEquals(tas.length, count);
                TrustedAuthorityFilter f = new TrustedAuthorityFilter();
                f.setName(auths[i].getName());
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(auths[i], trust.findTrustAuthorities(f)[0]);

                TrustedAuthorityFilter f2 = new TrustedAuthorityFilter();
                f2.setAuthorityGTS(authorityGTS[authIndex]);
                assertEquals(authCount, trust.findTrustAuthorities(f2).length);

                TrustedAuthorityFilter f3 = new TrustedAuthorityFilter();
                f3.setSourceGTS(authorityGTS[sourceIndex]);
                assertEquals(sourceCount, trust.findTrustAuthorities(f3).length);

                TrustedAuthorityFilter f4 = new TrustedAuthorityFilter();
                f4.setLifetime(Lifetime.EXPIRED);
                assertEquals(expiresCount, trust.findTrustAuthorities(f4).length);

                TrustedAuthorityFilter f5 = new TrustedAuthorityFilter();
                f5.setLifetime(Lifetime.VALID);
                assertEquals(validCount, trust.findTrustAuthorities(f5).length);

                TrustedAuthorityFilter f6 = new TrustedAuthorityFilter();
                f6.setTrustLevels(toTrustLevels(LEVEL_TWO));
                assertEquals((i + 1), trust.findTrustAuthorities(f6).length);

                TrustedAuthorityFilter f7 = new TrustedAuthorityFilter();
                f7.setTrustLevels(toTrustLevels(LEVEL_ONE));
                assertEquals((count - (i + 1)), trust.findTrustAuthorities(f7).length);

                TrustedAuthorityFilter f8 = new TrustedAuthorityFilter();
                f8.setStatus(Status.SUSPENDED);
                assertEquals((i + 1), trust.findTrustAuthorities(f8).length);

                TrustedAuthorityFilter f9 = new TrustedAuthorityFilter();
                f9.setStatus(Status.TRUSTED);
                assertEquals((count - (i + 1)), trust.findTrustAuthorities(f9).length);
            }

            // Test Remove
            int remaining = count;
            for (int i = 0; i < count; i++) {
                trust.removeTrustedAuthority(auths[i].getName());
                remaining = remaining - 1;
                assertEquals(remaining, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
            }
            assertEquals(0, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testAddTrustedAuthorityWithCRL() {

        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CA ca = new CA();
            BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
            CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(entry);
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            X509CRL crl = new X509CRL();
            crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
            ta.setCRL(crl);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
            trust.addTrustedAuthority(ta);
            assertEquals(ta, trust.getTrustedAuthority(ta.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testAddTrustedAuthorityWithLargeCRL() {
        int numOfCRLS = 10000;
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CRLEntry[] entries = new CRLEntry[numOfCRLS];
            CA ca = new CA();
            for (int i = 0; i < numOfCRLS; i++) {
                BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
                entries[i] = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
            }
            ca.updateCRL(entries);
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            X509CRL crl = new X509CRL();
            crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
            ta.setCRL(crl);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
            trust.addTrustedAuthority(ta);
            assertEquals(ta, trust.getTrustedAuthority(ta.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testAddTrustedAuthorityWithInvalidCRL() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CA ca = new CA();
            CA ca2 = new CA();
            BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
            CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
            ca2.updateCRL(entry);
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                X509Certificate x509 = new X509Certificate();
                x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
                ta.setCertificate(x509);
                X509CRL crl = new X509CRL();
                crl.setCrlEncodedString(CertUtil.writeCRL(ca2.getCRL()));
                ta.setCRL(crl);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                trust.addTrustedAuthority(ta);
                fail("Did not generate error when an invalidly signed CRL was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testAddInvalidTrustedAuthority() {

        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CA ca = new CA();

            // INTERNAL ADD: Invalid Trust Level
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels("INVALID_LEVEL"));
                trust.addTrustedAuthority(ta);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // INTERNAL ADD: No Certificate
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                trust.addTrustedAuthority(ta);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }
            // INTERNAL ADD: No Status
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                trust.addTrustedAuthority(ta);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: Invalid Trust Level
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels("INVALID_LEVEL"));
                ta.setIsAuthority(Boolean.FALSE);
                ta.setSourceGTS("Some Source");
                ta.setAuthorityGTS("Some Authority");
                ta.setExpires(1);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: No Certificate
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels("LEVEL_ONE"));
                ta.setIsAuthority(Boolean.FALSE);
                ta.setSourceGTS("Some Source");
                ta.setAuthorityGTS("Some Authority");
                ta.setExpires(1);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: No Status
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                ta.setIsAuthority(Boolean.FALSE);
                ta.setSourceGTS("Some Source");
                ta.setAuthorityGTS("Some Authority");
                ta.setExpires(1);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: No Authority
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                ta.setSourceGTS("Some Source");
                ta.setAuthorityGTS("Some Authority");
                ta.setExpires(1);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: Conflicting Authority
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                ta.setIsAuthority(Boolean.TRUE);
                ta.setSourceGTS("Some Source");
                ta.setAuthorityGTS("Some Authority");
                ta.setExpires(1);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: No Authority GTS
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                ta.setIsAuthority(Boolean.FALSE);
                ta.setSourceGTS("Some Source");
                ta.setExpires(1);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: No Source GTS
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                ta.setIsAuthority(Boolean.FALSE);
                ta.setAuthorityGTS("Some Authority");
                ta.setExpires(1);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: Invalid Expiration
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                ta.setIsAuthority(Boolean.FALSE);
                ta.setSourceGTS("Some Source");
                ta.setAuthorityGTS("Some Authority");
                ta.setExpires(0);
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // EXTERNAL ADD: No experation
            try {
                TrustedAuthority ta = new TrustedAuthority();
                ta.setName(ca.getCertificate().getSubjectDN().toString());
                ta.setCertificate(x509);
                ta.setStatus(Status.TRUSTED);
                ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
                ta.setIsAuthority(Boolean.FALSE);
                ta.setSourceGTS("Some Source");
                ta.setAuthorityGTS("Some Authority");
                trust.addTrustedAuthority(ta, false);
                fail("Did not generate error when an invalid Trusted Authority was provided.");
            } catch (IllegalTrustedAuthorityException f) {

            }

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testUpdateInvalidTrustedAuthority() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CA ca = new CA();
            BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
            CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(entry);
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            X509CRL crl = new X509CRL();
            crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
            ta.setCRL(crl);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
            trust.addTrustedAuthority(ta);
            assertEquals(ta, trust.getTrustedAuthority(ta.getName()));

            CA ca2 = new CA("O=Organization ABC,OU=Unit ABC,CN=Certificate Authority");
            BigInteger sn2 = new BigInteger(String.valueOf(System.currentTimeMillis()));
            CRLEntry entry2 = new CRLEntry(sn2, CRLReason.PRIVILEGE_WITHDRAWN);
            ca2.updateCRL(entry2);
            TrustedAuthority ta2 = new TrustedAuthority();
            ta2.setName(ca2.getCertificate().getSubjectDN().toString());
            X509Certificate x5092 = new X509Certificate();
            x5092.setCertificateEncodedString(CertUtil.writeCertificate(ca2.getCertificate()));
            ta2.setCertificate(x5092);
            X509CRL crl2 = new X509CRL();
            crl2.setCrlEncodedString(CertUtil.writeCRL(ca2.getCRL()));
            ta2.setCRL(crl2);
            ta2.setStatus(Status.TRUSTED);
            ta2.setTrustLevels(toTrustLevels(LEVEL_ONE));
            ta2.setIsAuthority(Boolean.FALSE);
            ta2.setAuthorityGTS("some other service");
            ta2.setSourceGTS("some other service");
            ta2.setExpires(20);
            trust.addTrustedAuthority(ta2, false);
            assertEquals(ta2, trust.getTrustedAuthority(ta2.getName()));

            // TEST INTERNAL UPDATE

            // Test BAD or no Name

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setName(null);
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority without specifying a valid name!!!");
            } catch (InvalidTrustedAuthorityException f) {

            }

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setName("");
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority without specifying a valid name!!!");
            } catch (InvalidTrustedAuthorityException f) {

            }

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setName("DOES NOT EXIST");
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority without specifying a valid name!!!");
            } catch (InvalidTrustedAuthorityException f) {

            }

            // Test Invalid Authority

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setAuthorityGTS("Other");
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setCertificate(x5092);
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // Test Invalid Authority

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setIsAuthority(Boolean.FALSE);
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // Test Invalid Source

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setSourceGTS("Invalid Source");
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // Test Invalid Trust Level

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setTrustLevels(toTrustLevels("INVALID"));
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta2.getName());
                trust.updateTrustedAuthority(u);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // TEST EXTERNAL UPDATE

            // Test BAD or no Name

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setName(null);
                trust.updateTrustedAuthority(u, false);
                fail("Should not be able to update a trusted authority without specifying a valid name!!!");
            } catch (InvalidTrustedAuthorityException f) {

            }

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setName("");
                trust.updateTrustedAuthority(u, false);
                fail("Should not be able to update a trusted authority without specifying a valid name!!!");
            } catch (InvalidTrustedAuthorityException f) {

            }

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setName("DOES NOT EXIST");
                trust.updateTrustedAuthority(u, false);
                fail("Should not be able to update a trusted authority without specifying a valid name!!!");
            } catch (InvalidTrustedAuthorityException f) {

            }

            // Test Invalid Authority

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setIsAuthority(Boolean.FALSE);
                trust.updateTrustedAuthority(u, false);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }
            // Authority Conflict
            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setAuthorityGTS("Other");
                trust.updateTrustedAuthority(u, false);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // Test Invalid Trust Level

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setTrustLevels(toTrustLevels("INVALID"));
                trust.updateTrustedAuthority(u, false);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

            // Test Invalid Certificate

            try {
                TrustedAuthority u = trust.getTrustedAuthority(ta.getName());
                u.setCertificate(ta2.getCertificate());
                trust.updateTrustedAuthority(u, false);
                fail("Should not be able to update a trusted authority!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testAddTrustedAuthorityNoCRL() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CA ca = new CA();
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
            trust.addTrustedAuthority(ta);
            assertEquals(ta, trust.getTrustedAuthority(ta.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testRemoveTrustedAuthority() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            CA ca = new CA();
            BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
            CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(entry);
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            X509CRL crl = new X509CRL();
            crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
            ta.setCRL(crl);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
            trust.addTrustedAuthority(ta);
            assertEquals(ta, trust.getTrustedAuthority(ta.getName()));
            trust.removeTrustedAuthority(ta.getName());
            try {
                trust.getTrustedAuthority(ta.getName());
                fail("Trusted Authority still exists when it should have been removed");
            } catch (InvalidTrustedAuthorityException f) {

            }

            try {
                trust.removeTrustedAuthority(ta.getName());
                fail("Trusted Authority still exists when it should have been removed");
            } catch (InvalidTrustedAuthorityException f) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testFindTrustedAuthorities() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            int count = 5;
            String dnPrefix = "O=Organization ABC,OU=Unit XYZ,CN=Certificate Authority";
            TrustedAuthority[] auths = new TrustedAuthority[count];
            for (int i = 0; i < count; i++) {
                String dn = dnPrefix + i;
                CA ca = new CA(dn);
                String name = ca.getCertificate().getSubjectDN().toString();

                BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
                CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
                ca.updateCRL(entry);
                auths[i] = new TrustedAuthority();
                auths[i].setName(name);
                X509Certificate x509 = new X509Certificate();
                x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
                auths[i].setCertificate(x509);
                X509CRL crl = new X509CRL();
                crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
                auths[i].setCRL(crl);
                auths[i].setStatus(Status.TRUSTED);
                auths[i].setTrustLevels(toTrustLevels(LEVEL_ONE));
                trust.addTrustedAuthority(auths[i]);
                assertEquals(auths[i], trust.getTrustedAuthority(auths[i].getName()));
                TrustedAuthority[] tas = trust.findTrustAuthorities(new TrustedAuthorityFilter());
                assertEquals(tas.length, (i + 1));

                // Filter by name
                TrustedAuthorityFilter tf2 = new TrustedAuthorityFilter();
                tf2.setName(name);
                TrustedAuthority[] tas2 = trust.findTrustAuthorities(tf2);
                assertEquals(1, tas2.length);
                assertEquals(auths[i], tas2[0]);
                tf2.setName("yada yada");
                tas2 = trust.findTrustAuthorities(tf2);
                assertEquals(0, tas2.length);

                // Filter by DN
                TrustedAuthorityFilter tf3 = new TrustedAuthorityFilter();
                tf3.setCertificateDN(dn);
                TrustedAuthority[] tas3 = trust.findTrustAuthorities(tf3);
                assertEquals(1, tas3.length);
                assertEquals(auths[i], tas3[0]);
                tf3.setCertificateDN("yada yada");
                tas3 = trust.findTrustAuthorities(tf3);
                assertEquals(0, tas3.length);

                // Filter by Trust Level
                TrustedAuthorityFilter tf4 = new TrustedAuthorityFilter();
                tf4.setTrustLevels(toTrustLevels(LEVEL_ONE));
                TrustedAuthority[] tas4 = trust.findTrustAuthorities(tf4);
                assertEquals((i + 1), tas4.length);
                tf4.setTrustLevels(toTrustLevels(LEVEL_TWO));
                tas4 = trust.findTrustAuthorities(tf4);
                assertEquals(0, tas4.length);

                // Filter by Status
                TrustedAuthorityFilter tf5 = new TrustedAuthorityFilter();
                tf5.setStatus(Status.TRUSTED);
                TrustedAuthority[] tas5 = trust.findTrustAuthorities(tf5);
                assertEquals((i + 1), tas5.length);
                tf5.setStatus(Status.SUSPENDED);
                tas5 = trust.findTrustAuthorities(tf5);
                assertEquals(0, tas5.length);

                // Filter by IsAuthority and Authority
                TrustedAuthorityFilter tf6 = new TrustedAuthorityFilter();
                tf6.setIsAuthority(Boolean.TRUE);
                tf6.setAuthorityGTS("localhost");
                TrustedAuthority[] tas6 = trust.findTrustAuthorities(tf6);
                assertEquals((i + 1), tas6.length);
                tf6.setIsAuthority(Boolean.FALSE);
                tas6 = trust.findTrustAuthorities(tf6);
                assertEquals(0, tas6.length);
                tf6.setIsAuthority(Boolean.TRUE);
                tf6.setAuthorityGTS("yada yada");
                tas6 = trust.findTrustAuthorities(tf6);
                assertEquals(0, tas6.length);

                // Filter by ALL
                TrustedAuthorityFilter tf7 = new TrustedAuthorityFilter();
                tf7.setName(name);
                TrustedAuthority[] tas7 = trust.findTrustAuthorities(tf7);
                assertEquals(1, tas7.length);
                assertEquals(auths[i], tas7[0]);
                tf7.setCertificateDN(dn);
                tas7 = trust.findTrustAuthorities(tf7);
                assertEquals(1, tas7.length);
                assertEquals(auths[i], tas7[0]);
                tf7.setTrustLevels(toTrustLevels(LEVEL_ONE));
                tas7 = trust.findTrustAuthorities(tf7);
                assertEquals(1, tas7.length);
                assertEquals(auths[i], tas7[0]);
                tf7.setStatus(Status.TRUSTED);
                tas7 = trust.findTrustAuthorities(tf7);
                assertEquals(1, tas7.length);
                assertEquals(auths[i], tas7[0]);
                tf7.setIsAuthority(Boolean.TRUE);
                tas7 = trust.findTrustAuthorities(tf7);
                assertEquals(1, tas7.length);
                assertEquals(auths[i], tas7[0]);
                tf7.setAuthorityGTS("localhost");
                tas7 = trust.findTrustAuthorities(tf7);
                assertEquals(1, tas7.length);
                assertEquals(auths[i], tas7[0]);
            }
            // Test Remove
            int remaining = count;
            for (int i = 0; i < count; i++) {
                trust.removeTrustedAuthority(auths[i].getName());
                remaining = remaining - 1;
                assertEquals(remaining, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
            }
            assertEquals(0, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testMultipleTrustLevels() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            int count = 4;
            String dnPrefix = "O=Organization ABC,OU=Unit XYZ,CN=Certificate Authority";

            String[] levels = new String[3];
            levels[0] = LEVEL_ONE;
            levels[1] = LEVEL_TWO;
            levels[2] = LEVEL_THREE;

            TrustLevels trustLevels = new TrustLevels();
            trustLevels.getTrustLevel().add(LEVEL_ONE);
            trustLevels.getTrustLevel().add(LEVEL_TWO);
            trustLevels.getTrustLevel().add(LEVEL_THREE);

            TrustedAuthority[] auths = new TrustedAuthority[count];
            for (int i = 0; i < count; i++) {
                String dn = dnPrefix + i;
                CA ca = new CA(dn);
                String name = ca.getCertificate().getSubjectDN().toString();

                BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
                CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
                ca.updateCRL(entry);
                auths[i] = new TrustedAuthority();
                auths[i].setName(name);
                X509Certificate x509 = new X509Certificate();
                x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
                auths[i].setCertificate(x509);
                X509CRL crl = new X509CRL();
                crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
                auths[i].setCRL(crl);
                auths[i].setStatus(Status.TRUSTED);

                TrustLevels tl = new TrustLevels();
                String[] localLevels = new String[i];
                for (int j = 0; j < i; j++) {
                    localLevels[j] = levels[j];
                    tl.getTrustLevel().add(localLevels[j]);
                }

                auths[i].setTrustLevels(tl);

                trust.addTrustedAuthority(auths[i]);

                TrustedAuthority temp = trust.getTrustedAuthority(auths[i].getName());
                assertEquals(auths[i], temp);
                TrustedAuthority[] tas = trust.findTrustAuthorities(new TrustedAuthorityFilter());
                assertEquals(tas.length, (i + 1));

                // Filter by Name
                TrustedAuthorityFilter tf3 = new TrustedAuthorityFilter();
                tf3.setName(name);
                TrustedAuthority[] tas3 = trust.findTrustAuthorities(tf3);
                assertEquals(1, tas3.length);
                assertEquals(auths[i], tas3[0]);
                assertEquals(auths[i].getTrustLevels().getTrustLevel().size(), tas3[0].getTrustLevels().getTrustLevel().size());
                tf3.setName("yada yada");
                tas3 = trust.findTrustAuthorities(tf3);
                assertEquals(0, tas3.length);

                // Filter by name and trust level
                for (int j = 0; j < levels.length; j++) {
                    TrustedAuthorityFilter tf2 = new TrustedAuthorityFilter();
                    tf2.setName(name);
                    tf2.setTrustLevels(toTrustLevels(levels[j]));
                    TrustedAuthority[] tas2 = trust.findTrustAuthorities(tf2);
                    int expected = 0;
                    if (i > j) {
                        expected = 1;
                    }
                    assertEquals(expected, tas2.length);
                    if (expected == 1) {
                        assertEquals(auths[i], tas2[0]);
                        assertEquals(auths[i].getTrustLevels().getTrustLevel().size(), tas2[0].getTrustLevels().getTrustLevel().size());
                    }
                }

                // Filter by Trust Level
                for (int j = 0; j < levels.length; j++) {
                    TrustedAuthorityFilter tf4 = new TrustedAuthorityFilter();
                    tf4.setTrustLevels(toTrustLevels(levels[j]));
                    TrustedAuthority[] tas4 = trust.findTrustAuthorities(tf4);
                    int num = i - j;

                    if (num < 0) {
                        num = 0;
                    }
                    assertEquals(num, tas4.length);
                }

                // Test Multiple Trust Levels
                for (int j = 0; j < levels.length; j++) {
                    TrustLevels tls = getTrustLevels(levels, j);
                    TrustedAuthorityFilter tf4 = new TrustedAuthorityFilter();
                    tf4.setTrustLevels(tls);
                    TrustedAuthority[] tas4 = trust.findTrustAuthorities(tf4);
                    int expected = (i + 1) - j;
                    if (expected < 0) {
                        expected = 0;
                    }
                    assertEquals(expected, tas4.length);
                }
            }
            // Test Remove
            int remaining = count;
            for (int i = 0; i < count; i++) {
                trust.removeTrustedAuthority(auths[i].getName());
                remaining = remaining - 1;
                assertEquals(remaining, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
            }
            assertEquals(0, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private TrustLevels getTrustLevels(String[] list, int count) {
        TrustLevels levels = new TrustLevels();
        for (int i = 0; i < count; i++) {
            levels.getTrustLevel().add(list[i]);
        }
        return levels;
    }

    public void testUpdateTrustedAuthorities() {
        TrustedAuthorityManager trust = new TrustedAuthorityManager("localhost", this, db);
        try {
            trust.clearDatabase();
            int count = 5;
            String dnPrefix = "O=Organization ABC,OU=Unit XYZ,CN=Certificate Authority";
            TrustedAuthority[] auths = new TrustedAuthority[count];
            for (int i = 0; i < count; i++) {
                String dn = dnPrefix + i;
                CA ca = new CA(dn);
                String name = ca.getCertificate().getSubjectDN().toString();

                BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
                CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
                ca.updateCRL(entry);
                auths[i] = new TrustedAuthority();
                auths[i].setName(name);
                X509Certificate x509 = new X509Certificate();
                x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
                auths[i].setCertificate(x509);
                X509CRL crl = new X509CRL();
                crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
                auths[i].setCRL(crl);
                auths[i].setStatus(Status.TRUSTED);
                auths[i].setTrustLevels(toTrustLevels(LEVEL_ONE));
                trust.addTrustedAuthority(auths[i]);
                assertEquals(auths[i], trust.getTrustedAuthority(auths[i].getName()));
                TrustedAuthority[] tas = trust.findTrustAuthorities(new TrustedAuthorityFilter());
                assertEquals(tas.length, (i + 1));
                TrustedAuthorityFilter f = new TrustedAuthorityFilter();
                f.setName(auths[i].getName());
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(auths[i], trust.findTrustAuthorities(f)[0]);

                // Test Changing the Authority Trust Service
                TrustedAuthority u1 = trust.findTrustAuthorities(f)[0];
                try {
                    u1.setAuthorityGTS("localhost2");
                    trust.updateTrustedAuthority(u1);
                    fail("Should not be able to change the Authority Trust Service.");
                } catch (IllegalTrustedAuthorityException fault) {

                }
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(auths[i], trust.findTrustAuthorities(f)[0]);

                // Test Changing the Certificate
                TrustedAuthority u2 = trust.findTrustAuthorities(f)[0];
                CA ca2 = new CA();
                X509Certificate x5092 = new X509Certificate();
                x5092.setCertificateEncodedString(CertUtil.writeCertificate(ca2.getCertificate()));
                u2.setCertificate(x5092);
                try {
                    trust.updateTrustedAuthority(u2);
                    fail("Should not be able to change the certificate for a Trust Service");
                } catch (IllegalTrustedAuthorityException fault) {

                }
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(auths[i], trust.findTrustAuthorities(f)[0]);

                // Test Updating the CRL

                TrustedAuthority u3 = trust.findTrustAuthorities(f)[0];
                // First Let test a bad CRL

                CRLEntry bad = new CRLEntry(new BigInteger(String.valueOf(System.currentTimeMillis())), CRLReason.PRIVILEGE_WITHDRAWN);
                ca2.updateCRL(bad);
                X509CRL crl2 = new X509CRL();
                crl2.setCrlEncodedString(CertUtil.writeCRL(ca2.getCRL()));
                u3.setCRL(crl2);
                try {
                    trust.updateTrustedAuthority(u3);
                    fail("Should not be able to change the certificate for a Trust Service");
                } catch (IllegalTrustedAuthorityException fault) {

                }

                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(auths[i], trust.findTrustAuthorities(f)[0]);

                CRLEntry good = new CRLEntry(new BigInteger(String.valueOf(System.currentTimeMillis())), CRLReason.PRIVILEGE_WITHDRAWN);
                ca.updateCRL(good);
                u3.setCRL(crl);
                trust.updateTrustedAuthority(u3);
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(u3, trust.findTrustAuthorities(f)[0]);

                TrustedAuthority u4 = trust.findTrustAuthorities(f)[0];
                u4.setIsAuthority(Boolean.FALSE);
                try {
                    trust.updateTrustedAuthority(u4);
                    fail("Should not be able to change the Authority Trust Service.");
                } catch (IllegalTrustedAuthorityException fault) {

                }

                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(u3, trust.findTrustAuthorities(f)[0]);

                // Test update status
                TrustedAuthority u5 = trust.findTrustAuthorities(f)[0];
                u5.setStatus(Status.SUSPENDED);
                trust.updateTrustedAuthority(u5);
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(u5, trust.findTrustAuthorities(f)[0]);

                // Test update trust level
                TrustedAuthority u6 = trust.findTrustAuthorities(f)[0];
                u6.setTrustLevels(toTrustLevels(LEVEL_THREE));
                trust.updateTrustedAuthority(u6);
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(u6, trust.findTrustAuthorities(f)[0]);
                u6.setTrustLevels(toTrustLevels("INVALID_LEVEL"));
                try {
                    trust.updateTrustedAuthority(u6);
                    fail("Should not be able to update a Trusted Authority with an invalid trust level!!!");
                } catch (IllegalTrustedAuthorityException itaf) {

                }
                u6.setTrustLevels(toTrustLevels(LEVEL_THREE));
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(u6, trust.findTrustAuthorities(f)[0]);

                // Test updating the Trust Authority Name

                TrustedAuthority u7 = trust.findTrustAuthorities(f)[0];
                u7.setName("localhost");
                try {
                    trust.updateTrustedAuthority(u7);
                    fail("Should not be able to change the name of a Trust Authority");
                } catch (InvalidTrustedAuthorityException fault) {

                }

                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(u6, trust.findTrustAuthorities(f)[0]);

                // Test Updating all
                TrustedAuthority u8 = trust.findTrustAuthorities(f)[0];
                CRLEntry crlE = new CRLEntry(new BigInteger(String.valueOf(System.currentTimeMillis())), CRLReason.PRIVILEGE_WITHDRAWN);
                ca.updateCRL(crlE);
                u8.setCRL(crl);
                u8.setStatus(Status.TRUSTED);
                u8.setTrustLevels(toTrustLevels(LEVEL_THREE));
                trust.updateTrustedAuthority(u8);
                assertEquals(1, trust.findTrustAuthorities(f).length);
                assertEquals(u8, trust.findTrustAuthorities(f)[0]);

            }

            // Test Remove
            int remaining = count;
            for (int i = 0; i < count; i++) {
                trust.removeTrustedAuthority(auths[i].getName());
                remaining = remaining - 1;
                assertEquals(remaining, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
            }
            assertEquals(0, trust.findTrustAuthorities(new TrustedAuthorityFilter()).length);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                trust.clearDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
        try {
            db = Utils.getDBManager();
            assertEquals(0, db.getDatabase().getUsedConnectionCount());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        try {
            assertEquals(0, db.getDatabase().getUsedConnectionCount());
            // db.getDatabase().destroyDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public boolean doesTrustLevelExist(String name) throws GTSInternalException {
        if (name.equals(LEVEL_ONE)) {
            return true;
        } else if (name.equals(LEVEL_TWO)) {
            return true;
        } else if (name.equals(LEVEL_THREE)) {
            return true;
        } else {
            return false;
        }
    }

    public TrustLevels toTrustLevels(String s) {
        TrustLevels levels = new TrustLevels();
        levels.getTrustLevel().add(s);
        return levels;
    }

}
