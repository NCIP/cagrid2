package org.cagrid.gts.service.impl;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bouncycastle.asn1.x509.CRLReason;
import org.cagrid.gaards.pki.CRLEntry;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gts.model.AuthorityGTS;
import org.cagrid.gts.model.AuthorityPrioritySpecification;
import org.cagrid.gts.model.AuthorityPriorityUpdate;
import org.cagrid.gts.model.Lifetime;
import org.cagrid.gts.model.Permission;
import org.cagrid.gts.model.PermissionFilter;
import org.cagrid.gts.model.Role;
import org.cagrid.gts.model.Status;
import org.cagrid.gts.model.TimeToLive;
import org.cagrid.gts.model.TrustLevel;
import org.cagrid.gts.model.TrustLevels;
import org.cagrid.gts.model.TrustedAuthority;
import org.cagrid.gts.model.TrustedAuthorityFilter;
import org.cagrid.gts.model.X509CRL;
import org.cagrid.gts.model.X509Certificate;
import org.cagrid.gts.service.GTS;
import org.cagrid.gts.service.exception.IllegalPermissionException;
import org.cagrid.gts.service.exception.IllegalTrustedAuthorityException;
import org.cagrid.gts.service.exception.PermissionDeniedException;
import org.cagrid.gts.service.impl.test.CA;
import org.cagrid.gts.service.impl.test.Utils;
import org.cagrid.gts.tools.service.PermissionBootstapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/gts-configuration.xml" })
public class GTSImplTest {

    private final static String ADMIN_USER = "O=Test Organization,OU=Test Unit,CN=GTS Admin";
    private final static String LEVEL_ONE = "ONE";
    private final static String LEVEL_TWO = "TWO";

    private int cacount = 0;
    private final String dnPrefix = "O=Organization ABC,OU=Unit XYZ,CN=Certificate Authority";
    private final String GTS_URI = "localhost";

    @javax.annotation.Resource
    private GTS gts;

    @javax.annotation.Resource
    private SimpleResourceManager srm;

    @Test
    public void testGTS() {
        Assert.assertNotNull(gts);
    }

    @Test
    public void testGetServiceMetadata() {
        Assert.assertNotNull(gts.getServiceMetadata());
    }

    @Test
    public void testGetServiceSecurityMetadata() {
        Assert.assertNotNull(gts.getServiceSecurityMetadata());
    }

    @Test
    public void testConfiguration() {
        Assert.assertNotNull(getConfiguration());
    }

    @Test
    public void testTrustLevels() throws Exception {
        TrustLevel[] trustLevels = gts.getTrustLevels("");
        Assert.assertTrue(trustLevels.length == 0);
        try {
            gts.addTrustLevel("NOT_ADMIN", new TrustLevel());
        } catch (PermissionDeniedException e) {
            // expected
        }
    }

    @Test
    public void testAddUpdateRemoveAuthorities() {
        try {
            // Make sure we start fresh
            clearDatabase();
            try {
                gts.findPermissions(ADMIN_USER, new PermissionFilter());
                Assert.fail("Should not be able to find permissions, no admin permission are configured.");
            } catch (PermissionDeniedException f) {

            }

            PermissionBootstapper pb = new PermissionBootstapper(getConfiguration());
            pb.addAdminUser(ADMIN_USER);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, new PermissionFilter()).length);
            int count = 5;
            AuthorityGTS[] a = new AuthorityGTS[count];

            for (int i = 0; i < count; i++) {
                a[i] = getAuthority("GTS " + i, 1);
                gts.addAuthority(ADMIN_USER, a[i]);
                Assert.assertEquals((i + 1), gts.getAuthorities(ADMIN_USER).length);
                for (int j = 0; j < i; j++) {
                    a[j].setPriority(a[j].getPriority() + 1);
                }
            }

            for (int i = 0; i < count; i++) {
                updateAuthority(a[i]);
                gts.updateAuthority(ADMIN_USER, a[i]);
                Assert.assertEquals(count, gts.getAuthorities(ADMIN_USER).length);
            }
            int priority = 1;
            AuthorityPriorityUpdate update = new AuthorityPriorityUpdate();
            AuthorityPrioritySpecification[] specs = new AuthorityPrioritySpecification[count];
            for (int i = 0; i < count; i++) {
                a[i].setPriority(priority);
                specs[i] = new AuthorityPrioritySpecification();
                specs[i].setServiceURI(a[i].getServiceURI());
                specs[i].setPriority(a[i].getPriority());
                priority = priority + 1;
                update.getAuthorityPrioritySpecification().add(specs[i]);
            }

            gts.updateAuthorityPriorities(ADMIN_USER, update);
            Assert.assertEquals(count, gts.getAuthorities(ADMIN_USER).length);

            AuthorityGTS[] auths = gts.getAuthorities(ADMIN_USER);
            for (int i = 0; i < count; i++) {
                Assert.assertEquals(a[i], auths[i]);
            }
            int num = count;
            for (int i = 0; i < count; i++) {
                gts.removeAuthority(ADMIN_USER, a[i].getServiceURI());
                num = num - 1;
                Assert.assertEquals(num, gts.getAuthorities(ADMIN_USER).length);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } finally {
            clearDatabase();
        }

    }

    @Test
    public void testAddFindRevokePermission() {
        try {
            Configuration conf = Utils.getGTSConfiguration();
            String user = "O=Test Organization,OU=Test Unit,CN=User";
            String user2 = "O=Test Organization,OU=Test Unit,CN=User2";

            // Make sure we start fresh
            clearDatabase();

            try {
                gts.findPermissions(ADMIN_USER, new PermissionFilter());
                Assert.fail("Should not be able to fine permissions, no admin permission are configured.");
            } catch (PermissionDeniedException f) {

            }

            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, new PermissionFilter()).length);
            addTrustLevels(gts, ADMIN_USER);
            CA ca = new CA();
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));

            Permission userPerm = new Permission();
            userPerm.setGridIdentity(user);
            userPerm.setRole(Role.TRUST_AUTHORITY_MANAGER);
            userPerm.setTrustedAuthorityName(ta.getName());

            Permission p = new Permission();
            p.setGridIdentity(user2);
            p.setRole(Role.TRUST_AUTHORITY_MANAGER);
            p.setTrustedAuthorityName(ta.getName());

            // Test null
            try {
                gts.addPermission(null, p);
                Assert.fail("Non trust service administrators should not be able to add a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.findPermissions(null, new PermissionFilter());
                Assert.fail("Non trust service administrators should not be able to find permissions!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.revokePermission(null, p);
                Assert.fail("Non trust service administrators should not be able to revoke a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            // Test Empty String
            try {
                gts.addPermission("", p);
                Assert.fail("Non trust service administrators should not be able to add a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.findPermissions("", new PermissionFilter());
                Assert.fail("Non trust service administrators should not be able to find permissions!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.revokePermission("", p);
                Assert.fail("Non trust service administrators should not be able to revoke a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            // Test user with no permissions
            try {
                gts.addPermission(user, p);
                Assert.fail("Non trust service administrators should not be able to add a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.findPermissions(user, new PermissionFilter());
                Assert.fail("Non trust service administrators should not be able to find permissions!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.revokePermission(user, p);
                Assert.fail("Non trust service administrators should not be able to revoke a permission!!!");
            } catch (PermissionDeniedException f) {

            }
            try {
                gts.addPermission(ADMIN_USER, userPerm);
                Assert.fail("Should not be able to add a permission that applies to a Trusted Authority that does not exist.!!!");
            } catch (IllegalPermissionException f) {

            }
            gts.addTrustedAuthority(ADMIN_USER, ta);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(ta, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter())[0]);
            gts.addPermission(ADMIN_USER, userPerm);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(userPerm)).length);
            Assert.assertEquals(userPerm, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(userPerm))[0]);
            // Test user with Invalid Permission
            try {
                gts.addPermission(user, p);
                Assert.fail("Non trust service administrators should not be able to add a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.findPermissions(user, new PermissionFilter());
                Assert.fail("Non trust service administrators should not be able to find permissions!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.revokePermission(user, p);
                Assert.fail("Non trust service administrators should not be able to revoke a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            // Now give use Admin rights
            Permission admin = new Permission();
            admin.setGridIdentity(user);
            admin.setRole(Role.TRUST_SERVICE_ADMIN);

            gts.addPermission(ADMIN_USER, admin);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin)).length);
            Assert.assertEquals(admin, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin))[0]);

            // Now that the user is admin try again
            gts.addPermission(user, p);
            Assert.assertEquals(1, gts.findPermissions(user, permissionToPermissionFilter(p)).length);
            Assert.assertEquals(p, gts.findPermissions(user, permissionToPermissionFilter(p))[0]);
            gts.revokePermission(user, p);
            Assert.assertEquals(0, gts.findPermissions(user, permissionToPermissionFilter(p)).length);

            // Now Revoke the user's admin rights and try again
            gts.revokePermission(ADMIN_USER, admin);
            Assert.assertEquals(0, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin)).length);
            try {
                gts.addPermission(user, p);
                Assert.fail("Non trust service administrators should not be able to add a permission!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.findPermissions(user, new PermissionFilter());
                Assert.fail("Non trust service administrators should not be able to find permissions!!!");
            } catch (PermissionDeniedException f) {

            }

            try {
                gts.revokePermission(user, p);
                Assert.fail("Non trust service administrators should not be able to revoke a permission!!!");
            } catch (PermissionDeniedException f) {

            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unexpected error:" + e.getMessage());
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testRevokePermissionsWhenTrustedAuthorityIsRemoved() {

        try {
            Configuration conf = Utils.getGTSConfiguration();
            String user = "O=Test Organization,OU=Test Unit,CN=User";
            String user2 = "O=Test Organization,OU=Test Unit,CN=User2";
            // Make sure we start fresh
            clearDatabase();

            try {
                gts.findPermissions(ADMIN_USER, new PermissionFilter());
                Assert.fail("Should not be able to fine permissions, no admin permission are configured.");
            } catch (PermissionDeniedException f) {
            }

            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, new PermissionFilter()).length);
            addTrustLevels(gts, ADMIN_USER);
            CA ca = new CA();
            TrustedAuthority ta = new TrustedAuthority();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(LEVEL_ONE));

            Permission userPerm = new Permission();
            userPerm.setGridIdentity(user2);
            userPerm.setRole(Role.TRUST_AUTHORITY_MANAGER);
            userPerm.setTrustedAuthorityName(ta.getName());

            gts.addTrustedAuthority(ADMIN_USER, ta);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(ta, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter())[0]);

            // Now give use Admin rights
            Permission admin = new Permission();
            admin.setGridIdentity(user);
            admin.setRole(Role.TRUST_SERVICE_ADMIN);

            gts.addPermission(ADMIN_USER, admin);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin)).length);
            Assert.assertEquals(admin, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin))[0]);

            // Now that the user is admin try again
            gts.addPermission(user, userPerm);
            Assert.assertEquals(1, gts.findPermissions(user, permissionToPermissionFilter(userPerm)).length);
            Assert.assertEquals(userPerm, gts.findPermissions(user, permissionToPermissionFilter(userPerm))[0]);

            gts.removeTrustedAuthority(ADMIN_USER, ta.getName());

            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin)).length);
            Assert.assertEquals(admin, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin))[0]);

            Assert.assertEquals(0, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(userPerm)).length);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testAddTrustedAuthorityInvalidLevel() {

        try {
            Configuration conf = Utils.getGTSConfiguration();

            // Make sure we start fresh
            clearDatabase();
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            addTrustLevels(gts, ADMIN_USER);
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
            ta.setTrustLevels(toTrustLevels("INVALID_LEVEL"));

            try {
                gts.addTrustedAuthority(ADMIN_USER, ta);
                Assert.fail("Should not to be able to add a trusted authority with and invalid trust level!!!");
            } catch (IllegalTrustedAuthorityException f) {

            }
            Assert.assertEquals(0, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            clearDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testAddTrustedAuthority() {

        try {
            Configuration conf = Utils.getGTSConfiguration();

            // Make sure we start fresh
            clearDatabase();
            String user = "O=Test Organization,OU=Test Unit,CN=User";
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            addTrustLevels(gts, ADMIN_USER);
            CA ca = new CA();
            X509Certificate userCert = new X509Certificate();
            userCert.setCertificateEncodedString(CertUtil.writeCertificate(ca.createIdentityCertificate("User Y").getCertificate()));
            Thread.sleep(100);
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

            // Test null
            try {
                gts.addTrustedAuthority(null, ta);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }
            Assert.assertEquals(0, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            // TODO: MIGRATE:implement
            // try {
            // assertFalse(gts.validate(userCert, new TrustedAuthorityFilter()));
            // Assert.fail("Should not be able to validate the user ceritifcate!!!");
            // } catch (CertificateValidationException f) {
            //
            // }

            // Test Empty String
            try {
                gts.addTrustedAuthority("", ta);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }
            Assert.assertEquals(0, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            // TODO: MIGRATE:implement
            // try {
            // assertFalse(gts.validate(userCert, new TrustedAuthorityFilter()));
            // Assert.fail("Should not be able to validate the user ceritifcate!!!");
            // } catch (CertificateValidationException f) {
            //
            // }

            // Test User without any permissions
            try {
                gts.addTrustedAuthority(user, ta);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }
            Assert.assertEquals(0, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            // Finally Add a trust authority so we can create trust manager
            // users
            gts.addTrustedAuthority(ADMIN_USER, ta);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(ta, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter())[0]);
            // TODO: MIGRATE:implement
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));

            // Now create a permission for a user on the previous added trust
            // authority.
            Permission p = new Permission();
            p.setGridIdentity(user);
            p.setRole(Role.TRUST_AUTHORITY_MANAGER);
            p.setTrustedAuthorityName(ta.getName());
            gts.addPermission(ADMIN_USER, p);

            // Check to make sure the permission was properly added
            PermissionFilter pf = permissionToPermissionFilter(p);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, pf).length);
            Assert.assertEquals(p, gts.findPermissions(ADMIN_USER, pf)[0]);

            // Now Create a new Trust Authority
            CA ca2 = new CA();
            TrustedAuthority ta2 = new TrustedAuthority();
            ta2.setName(ca2.getCertificate().getSubjectDN().toString());
            X509Certificate x5092 = new X509Certificate();
            x5092.setCertificateEncodedString(CertUtil.writeCertificate(ca2.getCertificate()));
            ta2.setCertificate(x5092);
            ta2.setStatus(Status.TRUSTED);
            ta2.setTrustLevels(toTrustLevels(LEVEL_ONE));

            try {
                gts.addTrustedAuthority(user, ta);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            clearDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testRemoveReferencedTrustLevels() {

        try {
            Configuration conf = Utils.getGTSConfiguration();

            // Make sure we start fresh
            clearDatabase();
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            TrustLevel l1 = new TrustLevel();
            l1.setName(LEVEL_ONE);
            gts.addTrustLevel(ADMIN_USER, l1);
            Assert.assertTrue(gts.doesTrustLevelExist(null, l1.getName()));
            Assert.assertEquals(1, gts.getTrustLevels(null).length);

            CA ca = new CA();
            BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
            CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(entry);
            Thread.sleep(100);
            X509Certificate userCert = new X509Certificate();
            userCert.setCertificateEncodedString(CertUtil.writeCertificate(ca.createIdentityCertificate("User Y").getCertificate()));
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

            TrustedAuthorityFilter f = new TrustedAuthorityFilter();
            f.setTrustLevels(toTrustLevels(LEVEL_ONE));

            gts.addTrustedAuthority(ADMIN_USER, ta);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, f).length);
            Assert.assertEquals(ta, gts.findTrustedAuthorities(null, f)[0]);
            // TODO: MIGRATE: impl
            // Assert.assertTrue(gts.validate(userCert, f));

            gts.removeTrustLevel(ADMIN_USER, l1.getName());

            Assert.assertEquals(0, gts.findTrustedAuthorities(null, f).length);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(0, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter())[0].getTrustLevels().getTrustLevel().size());

            // TODO: MIGRATE: impl
            // try {
            // assertFalse(gts.validate(userCert, f));
            // Assert.fail("Should not be able to validate the user ceritifcate!!!");
            // } catch (CertificateValidationException fault) {
            //
            // }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testAddGetUpdateRemoveTrustLevels() {

        try {
            Configuration conf = Utils.getGTSConfiguration();
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            String user = "O=Test Organization,OU=Test Unit,CN=User";
            int size = 5;
            TrustLevel[] level = new TrustLevel[size];
            for (int i = 0; i < size; i++) {
                level[i] = new TrustLevel();
                level[i].setName("My Level " + i);
                level[i].setDescription("Trust Level " + i);
                try {
                    gts.addTrustLevel(null, level[i]);
                    Assert.fail("Non trust service administrators should not be able to add a trust level!!!");
                } catch (PermissionDeniedException f) {

                }

                try {
                    gts.addTrustLevel("", level[i]);
                    Assert.fail("Non trust service administrators should not be able to add a trust level!!!");
                } catch (PermissionDeniedException f) {

                }

                try {
                    gts.addTrustLevel(user, level[i]);
                    Assert.fail("Non trust service administrators should not be able to add a trust level!!!");
                } catch (PermissionDeniedException f) {

                }

                gts.addTrustLevel(ADMIN_USER, level[i]);
                Assert.assertEquals((i + 1), gts.getTrustLevels(null).length);
                Assert.assertEquals(true, gts.doesTrustLevelExist(null, level[i].getName()));
                level[i].setDescription("Updated Trust Level " + i);

                try {
                    gts.updateTrustLevel(null, level[i]);
                    Assert.fail("Non trust service administrators should not be able to update a trust level!!!");
                } catch (PermissionDeniedException f) {

                }
                try {
                    gts.updateTrustLevel("", level[i]);
                    Assert.fail("Non trust service administrators should not be able to update a trust level!!!");
                } catch (PermissionDeniedException f) {

                }

                try {
                    gts.updateTrustLevel(user, level[i]);
                    Assert.fail("Non trust service administrators should not be able to update a trust level!!!");
                } catch (PermissionDeniedException f) {

                }

                gts.updateTrustLevel(ADMIN_USER, level[i]);
                Assert.assertEquals((i + 1), gts.getTrustLevels(null).length);
                Assert.assertEquals(true, gts.doesTrustLevelExist(null, level[i].getName()));
            }
            int count = size;
            for (int i = 0; i < size; i++) {
                try {
                    gts.removeTrustLevel(null, level[i].getName());
                    Assert.fail("Non trust service administrators should not be able to remove a trust level!!!");
                } catch (PermissionDeniedException f) {

                }

                try {
                    gts.removeTrustLevel("", level[i].getName());
                    Assert.fail("Non trust service administrators should not be able to remove a trust level!!!");
                } catch (PermissionDeniedException f) {

                }

                try {
                    gts.removeTrustLevel(user, level[i].getName());
                    Assert.fail("Non trust service administrators should not be able to remove a trust level!!!");
                } catch (PermissionDeniedException f) {

                }
                gts.removeTrustLevel(ADMIN_USER, level[i].getName());
                count = count - 1;
                Assert.assertEquals(count, gts.getTrustLevels(null).length);
                Assert.assertEquals(false, gts.doesTrustLevelExist(null, level[i].getName()));
            }
            Assert.assertEquals(0, gts.getTrustLevels(null).length);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testUpdateTrustedAuthority() {

        try {
            Configuration conf = Utils.getGTSConfiguration();

            // Make sure we start fresh
            clearDatabase();
            String user = "O=Test Organization,OU=Test Unit,CN=User";
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            addTrustLevels(gts, ADMIN_USER);
            CA ca = new CA();
            BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
            CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(entry);
            Thread.sleep(100);
            X509Certificate userCert = new X509Certificate();
            userCert.setCertificateEncodedString(CertUtil.writeCertificate(ca.createIdentityCertificate("User Y").getCertificate()));
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
            ta = gts.addTrustedAuthority(ADMIN_USER, ta);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // TODO: MIGRATE: implement
            // (gts.validate(userCert, new TrustedAuthorityFilter()));
            TrustedAuthority updated = gts.findTrustedAuthorities(null, new TrustedAuthorityFilter())[0];
            Assert.assertEquals(ta, updated);

            CRLEntry crlE = new CRLEntry(new BigInteger(String.valueOf(System.currentTimeMillis())), CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(crlE);
            updated.setCRL(crl);
            updated.setStatus(Status.SUSPENDED);
            updated.setTrustLevels(toTrustLevels(LEVEL_TWO));

            // Test null
            try {
                gts.updateTrustedAuthority(null, updated);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            // Test Empty String
            try {
                gts.updateTrustedAuthority("", updated);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            // Test User without any permissions
            try {
                gts.updateTrustedAuthority(user, updated);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            CRLEntry crlE3 = new CRLEntry(CertUtil.loadCertificate(userCert.getCertificateEncodedString()).getSerialNumber(), CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(crlE3);
            try {

                gts.updateCRL(user, updated.getName(), crl);
                Assert.fail("Should not be able to update CRL!!!");
            } catch (PermissionDeniedException f) {

            }
            // Check to make sure we can update the CRL as an administrator
            gts.updateCRL(ADMIN_USER, updated.getName(), crl);

            // TODO: MIGRATE: implement
            // try {
            // assertFalse(gts.validate(userCert, new TrustedAuthorityFilter()));
            // Assert.fail("Should not be able to validate the user ceritifcate!!!");
            // } catch (CertificateValidationException f) {
            //
            // }

            // Now create a permission for a user on the previous added trust
            // authority.
            Permission p = new Permission();
            p.setGridIdentity(user);
            p.setRole(Role.TRUST_AUTHORITY_MANAGER);
            p.setTrustedAuthorityName(ta.getName());
            gts.addPermission(ADMIN_USER, p);

            // Check to make sure the permission was properly added
            PermissionFilter pf = permissionToPermissionFilter(p);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, pf).length);
            Assert.assertEquals(p, gts.findPermissions(ADMIN_USER, pf)[0]);

            try {
                gts.updateTrustedAuthority(user, updated);
                Assert.fail("Non trust service administrators should not be able to update a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }

            // Check to make sure we can update the CRL

            CRLEntry crlE2 = new CRLEntry(new BigInteger(String.valueOf(System.currentTimeMillis())), CRLReason.PRIVILEGE_WITHDRAWN);
            ca.updateCRL(crlE2);
            gts.updateCRL(user, updated.getName(), crl);

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // TODO: MIGRATE: implement
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));

            // Now give use Admin rights
            Permission admin = new Permission();
            admin.setGridIdentity(user);
            admin.setRole(Role.TRUST_SERVICE_ADMIN);

            gts.addPermission(ADMIN_USER, admin);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin)).length);
            Assert.assertEquals(admin, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin))[0]);

            // Now that the user is admin try again
            gts.updateTrustedAuthority(user, updated);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(updated, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter())[0]);
            // TODO: MIGRATE: impl
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testRemoveTrustedAuthority() {

        try {
            Configuration conf = Utils.getGTSConfiguration();

            // Make sure we start fresh
            clearDatabase();
            String user = "O=Test Organization,OU=Test Unit,CN=User";
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            addTrustLevels(gts, ADMIN_USER);
            CA ca = new CA();
            X509Certificate userCert = new X509Certificate();
            userCert.setCertificateEncodedString(CertUtil.writeCertificate(ca.createIdentityCertificate("User Y").getCertificate()));
            Thread.sleep(100);
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

            ta = gts.addTrustedAuthority(ADMIN_USER, ta);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // TODO: MIGRATE: impl
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));

            // Test null
            try {
                gts.removeTrustedAuthority(null, ta.getName());
                Assert.fail("Non trust service administrators should not be able to remove a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // TODO: MIGRATE: impl
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));

            // Test Empty String
            try {
                gts.removeTrustedAuthority("", ta.getName());
                Assert.fail("Non trust service administrators should not be able to remove a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // TODO: MIGRATE: impl
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));

            // Test User without any permissions
            try {
                gts.removeTrustedAuthority(user, ta.getName());
                Assert.fail("Non trust service administrators should not be able to remove a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // TODO: MIGRATE: impl
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));

            // Now create a permission for a user on the previous added trust
            // authority.
            Permission p = new Permission();
            p.setGridIdentity(user);
            p.setRole(Role.TRUST_AUTHORITY_MANAGER);
            p.setTrustedAuthorityName(ta.getName());
            gts.addPermission(ADMIN_USER, p);

            // Check to make sure the permission was properly added
            PermissionFilter pf = permissionToPermissionFilter(p);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, pf).length);
            Assert.assertEquals(p, gts.findPermissions(ADMIN_USER, pf)[0]);

            try {
                gts.removeTrustedAuthority(user, ta.getName());
                Assert.fail("Non trust service administrators should not be able to remove a trust authority!!!");
            } catch (PermissionDeniedException f) {

            }

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // Assert.assertTrue(gts.validate(userCert, new TrustedAuthorityFilter()));

            // Now give use Admin rights
            Permission admin = new Permission();
            admin.setGridIdentity(user);
            admin.setRole(Role.TRUST_SERVICE_ADMIN);

            gts.addPermission(ADMIN_USER, admin);
            Assert.assertEquals(1, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin)).length);
            Assert.assertEquals(admin, gts.findPermissions(ADMIN_USER, permissionToPermissionFilter(admin))[0]);

            // Now that the user is admin try again
            gts.removeTrustedAuthority(user, ta.getName());
            Assert.assertEquals(0, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            // TODO: MIGRATE: impl
            // try {
            // assertFalse(gts.validate(userCert, new TrustedAuthorityFilter()));
            // Assert.fail("Should not be able to validate the user ceritifcate!!!");
            // } catch (CertificateValidationException f) {
            //
            // }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testSyncTrustedAuthoritiesWithSingleAuthorityGTS() {

        try {
            Configuration conf = Utils.getGTSConfiguration();
            // Make sure we start fresh
            clearDatabase();

            // Add the admin user
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);

            // Add
            addTrustLevels(gts, ADMIN_USER);

            // Now we add an authority
            String authName = GTS_URI + " Authority";
            TimeToLive ttl = new TimeToLive();
            ttl.setHours(0);
            ttl.setMinutes(0);
            ttl.setSeconds(4);
            AuthorityGTS auth = getAuthority(authName, 1, ttl);
            gts.addAuthority(ADMIN_USER, auth);
            Assert.assertEquals(1, gts.getAuthorities(ADMIN_USER).length);
            Assert.assertEquals(auth, gts.getAuthorities(null)[0]);

            int taCount = 2;
            TrustedAuthority[] ta = new TrustedAuthority[taCount];
            for (int j = 0; j < taCount; j++) {
                ta[j] = getTrustedAuthority();
                gts.addTrustedAuthority(ADMIN_USER, ta[j]);
                TrustedAuthorityFilter f = new TrustedAuthorityFilter();
                Assert.assertEquals((j + 1), gts.findTrustedAuthorities(null, f).length);
                f.setName(ta[j].getName());
                Assert.assertEquals(1, gts.findTrustedAuthorities(null, f).length);
                Assert.assertEquals(ta[j], gts.findTrustedAuthorities(null, f)[0]);
            }

            int remoteTaCount = 4;
            TrustedAuthority[] remoteta = new TrustedAuthority[remoteTaCount];
            for (int j = 0; j < remoteTaCount; j++) {
                remoteta[j] = getTrustedAuthority();
                remoteta[j].setIsAuthority(Boolean.FALSE);
                remoteta[j].setAuthorityGTS(authName);
            }

            getInternalGTS().synchronizeTrustedAuthorities(authName, remoteta);

            Thread.sleep(4100);

            // Test After Expiration
            TrustedAuthorityFilter f1 = new TrustedAuthorityFilter();
            Assert.assertEquals(taCount + remoteTaCount, gts.findTrustedAuthorities(null, f1).length);
            f1.setLifetime(Lifetime.VALID);
            f1.setStatus(Status.TRUSTED);
            Assert.assertEquals(taCount, gts.findTrustedAuthorities(null, f1).length);
            f1.setAuthorityGTS(GTS_URI);
            Assert.assertEquals(taCount, gts.findTrustedAuthorities(null, f1).length);

            ttl.setHours(5);
            auth.setTimeToLive(ttl);
            gts.updateAuthority(ADMIN_USER, auth);

            getInternalGTS().synchronizeTrustedAuthorities(authName, remoteta);

            // Test After Resync and after Longer Expiration
            TrustedAuthorityFilter f2 = new TrustedAuthorityFilter();
            f2.setLifetime(Lifetime.VALID);
            f2.setStatus(Status.TRUSTED);
            Assert.assertEquals(taCount + remoteTaCount, gts.findTrustedAuthorities(null, f2).length);
            f2.setAuthorityGTS(GTS_URI);
            Assert.assertEquals(taCount, gts.findTrustedAuthorities(null, f2).length);
            f2.setAuthorityGTS(authName);
            Assert.assertEquals(remoteTaCount, gts.findTrustedAuthorities(null, f2).length);
            f2.setSourceGTS(authName);
            Assert.assertEquals(remoteTaCount, gts.findTrustedAuthorities(null, f2).length);

            // Test after resync after delete
            int remoteTaCount2 = 2;
            TrustedAuthority[] remoteta2 = new TrustedAuthority[remoteTaCount2];
            for (int j = 0; j < remoteTaCount2; j++) {
                remoteta2[j] = remoteta[j];
            }

            getInternalGTS().synchronizeTrustedAuthorities(authName, remoteta2);

            // Test After Resync and after Longer Expiration
            TrustedAuthorityFilter f3 = new TrustedAuthorityFilter();
            Assert.assertEquals(taCount + remoteTaCount2, gts.findTrustedAuthorities(null, f3).length);
            f3.setLifetime(Lifetime.VALID);
            f3.setStatus(Status.TRUSTED);
            Assert.assertEquals(taCount + remoteTaCount2, gts.findTrustedAuthorities(null, f3).length);
            f3.setAuthorityGTS(GTS_URI);
            Assert.assertEquals(taCount, gts.findTrustedAuthorities(null, f3).length);
            f3.setAuthorityGTS(authName);
            Assert.assertEquals(remoteTaCount2, gts.findTrustedAuthorities(null, f3).length);
            f3.setSourceGTS(authName);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testSyncTrustedAuthoritiesOverlappingGTSAuthorities() {

        try {
            Configuration conf = Utils.getGTSConfiguration();
            // Make sure we start fresh
            clearDatabase();

            // Add the admin user
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);
            addTrustLevels(gts, ADMIN_USER);

            // Create Authorities
            AuthorityGTS auth1 = getAuthority(GTS_URI + " Authority 1", 1);
            gts.addAuthority(ADMIN_USER, auth1);
            AuthorityGTS auth2 = getAuthority(GTS_URI + " Authority 2", 2);
            gts.addAuthority(ADMIN_USER, auth2);
            AuthorityGTS[] list = gts.getAuthorities(null);
            Assert.assertEquals(2, list.length);
            Assert.assertEquals(auth1, list[0]);
            Assert.assertEquals(auth2, list[1]);

            // Now Add Trusted Authorities for local GTS
            TrustedAuthority[] local = new TrustedAuthority[3];
            TrustedAuthorityFilter f = new TrustedAuthorityFilter();

            local[0] = getTrustedAuthority();
            gts.addTrustedAuthority(ADMIN_USER, local[0]);
            f.setName(local[0].getName());
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, f).length);
            Assert.assertEquals(local[0], gts.findTrustedAuthorities(null, f)[0]);

            local[1] = getTrustedAuthority();
            gts.addTrustedAuthority(ADMIN_USER, local[1]);
            f.setName(local[1].getName());
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, f).length);
            Assert.assertEquals(local[1], gts.findTrustedAuthorities(null, f)[0]);

            local[2] = getTrustedAuthority();
            gts.addTrustedAuthority(ADMIN_USER, local[2]);
            f.setName(local[2].getName());
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, f).length);
            Assert.assertEquals(local[2], gts.findTrustedAuthorities(null, f)[0]);

            Assert.assertEquals(3, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            TrustedAuthority[] remote1 = new TrustedAuthority[3];
            remote1[0] = getTrustedAuthority(local[2].getName());
            remote1[0].setAuthorityGTS(auth1.getServiceURI());
            remote1[1] = getTrustedAuthority();
            remote1[1].setAuthorityGTS(auth1.getServiceURI());
            remote1[2] = getTrustedAuthority();
            remote1[2].setAuthorityGTS(auth1.getServiceURI());

            TrustedAuthority[] remote2 = new TrustedAuthority[3];
            remote2[0] = getTrustedAuthority(remote1[2].getName());
            remote2[0].setAuthorityGTS(auth2.getServiceURI());
            remote2[1] = getTrustedAuthority();
            remote2[1].setAuthorityGTS(auth2.getServiceURI());
            remote2[2] = getTrustedAuthority();
            remote2[2].setAuthorityGTS(auth2.getServiceURI());

            getInternalGTS().synchronizeTrustedAuthorities(auth2.getServiceURI(), remote2);

            Assert.assertEquals(6, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[0])).length);
            Assert.assertEquals(local[0], gts.findTrustedAuthorities(null, getFilterForTA(local[0]))[0]);
            Assert.assertEquals(local[0].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[0]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[1])).length);
            Assert.assertEquals(local[1], gts.findTrustedAuthorities(null, getFilterForTA(local[1]))[0]);
            Assert.assertEquals(local[1].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[1]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[2])).length);
            Assert.assertEquals(local[2], gts.findTrustedAuthorities(null, getFilterForTA(local[2]))[0]);
            Assert.assertEquals(local[2].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[2]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[0])).length);
            Assert.assertEquals(remote2[0], gts.findTrustedAuthorities(null, getFilterForTA(remote2[0]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[0]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[1])).length);
            Assert.assertEquals(remote2[1], gts.findTrustedAuthorities(null, getFilterForTA(remote2[1]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[1]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[2])).length);
            Assert.assertEquals(remote2[2], gts.findTrustedAuthorities(null, getFilterForTA(remote2[2]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[2]))[0].getSourceGTS());

            getInternalGTS().synchronizeTrustedAuthorities(auth1.getServiceURI(), remote1);

            Assert.assertEquals(7, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[0])).length);
            Assert.assertEquals(local[0], gts.findTrustedAuthorities(null, getFilterForTA(local[0]))[0]);
            Assert.assertEquals(local[0].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[0]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[1])).length);
            Assert.assertEquals(local[1], gts.findTrustedAuthorities(null, getFilterForTA(local[1]))[0]);
            Assert.assertEquals(local[1].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[1]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[2])).length);
            Assert.assertEquals(local[2], gts.findTrustedAuthorities(null, getFilterForTA(local[2]))[0]);
            Assert.assertEquals(local[2].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[2]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote1[1])).length);
            Assert.assertEquals(remote1[1], gts.findTrustedAuthorities(null, getFilterForTA(remote1[1]))[0]);
            Assert.assertEquals(auth1.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote1[1]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote1[2])).length);
            Assert.assertEquals(remote1[2], gts.findTrustedAuthorities(null, getFilterForTA(remote1[2]))[0]);
            Assert.assertEquals(auth1.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote1[2]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[1])).length);
            Assert.assertEquals(remote2[1], gts.findTrustedAuthorities(null, getFilterForTA(remote2[1]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[1]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[2])).length);
            Assert.assertEquals(remote2[2], gts.findTrustedAuthorities(null, getFilterForTA(remote2[2]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[2]))[0].getSourceGTS());

            gts.removeAuthority(ADMIN_USER, auth1.getServiceURI());
            Assert.assertEquals(5, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);

            getInternalGTS().synchronizeTrustedAuthorities(auth2.getServiceURI(), remote2);

            Assert.assertEquals(6, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[0])).length);
            Assert.assertEquals(local[0], gts.findTrustedAuthorities(null, getFilterForTA(local[0]))[0]);
            Assert.assertEquals(local[0].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[0]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[1])).length);
            Assert.assertEquals(local[1], gts.findTrustedAuthorities(null, getFilterForTA(local[1]))[0]);
            Assert.assertEquals(local[1].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[1]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(local[2])).length);
            Assert.assertEquals(local[2], gts.findTrustedAuthorities(null, getFilterForTA(local[2]))[0]);
            Assert.assertEquals(local[2].getSourceGTS(), gts.findTrustedAuthorities(null, getFilterForTA(local[2]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[0])).length);
            Assert.assertEquals(remote2[0], gts.findTrustedAuthorities(null, getFilterForTA(remote2[0]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[0]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[1])).length);
            Assert.assertEquals(remote2[1], gts.findTrustedAuthorities(null, getFilterForTA(remote2[1]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[1]))[0].getSourceGTS());

            Assert.assertEquals(1, gts.findTrustedAuthorities(null, getFilterForTA(remote2[2])).length);
            Assert.assertEquals(remote2[2], gts.findTrustedAuthorities(null, getFilterForTA(remote2[2]))[0]);
            Assert.assertEquals(auth2.getServiceURI(), gts.findTrustedAuthorities(null, getFilterForTA(remote2[2]))[0].getSourceGTS());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testSyncTrusLevelsOverlappingGTSAuthorities() {

        try {
            Configuration conf = Utils.getGTSConfiguration();
            // Make sure we start fresh
            clearDatabase();

            // Add the admin user
            PermissionBootstapper pb = new PermissionBootstapper(conf);
            pb.addAdminUser(ADMIN_USER);

            // Create Authorities
            AuthorityGTS auth1 = getAuthority(GTS_URI + " Authority 1", 1);
            gts.addAuthority(ADMIN_USER, auth1);
            AuthorityGTS auth2 = getAuthority(GTS_URI + " Authority 2", 2);
            gts.addAuthority(ADMIN_USER, auth2);
            AuthorityGTS[] list = gts.getAuthorities(null);
            Assert.assertEquals(2, list.length);
            Assert.assertEquals(auth1, list[0]);
            Assert.assertEquals(auth2, list[1]);

            // Now Add Trusted Authorities for local GTS
            TrustLevel[] local = new TrustLevel[3];

            for (int i = 0; i < 3; i++) {
                local[i] = getTrustLevel();
                gts.addTrustLevel(ADMIN_USER, local[i]);
                Assert.assertEquals((i + 1), gts.getTrustLevels(null).length);
                Assert.assertTrue(gts.doesTrustLevelExist(null, local[i].getName()));
            }

            TrustLevel[] remote1 = new TrustLevel[3];
            remote1[0] = getTrustLevel(local[2].getName());
            remote1[0].setAuthorityGTS(auth1.getServiceURI());
            remote1[1] = getTrustLevel();
            remote1[1].setAuthorityGTS(auth1.getServiceURI());
            remote1[2] = getTrustLevel();
            remote1[2].setAuthorityGTS(auth1.getServiceURI());

            TrustLevel[] remote2 = new TrustLevel[3];
            remote2[0] = getTrustLevel(remote1[2].getName());
            remote2[0].setAuthorityGTS(auth2.getServiceURI());
            remote2[1] = getTrustLevel();
            remote2[1].setAuthorityGTS(auth2.getServiceURI());
            remote2[2] = getTrustLevel();
            remote2[2].setAuthorityGTS(auth2.getServiceURI());

            getInternalGTS().synchronizeTrustLevels(auth2.getServiceURI(), Arrays.asList(remote2));

            Assert.assertEquals(6, gts.getTrustLevels(null).length);
            Assert.assertEquals(3, gts.getTrustLevels(null, GTS_URI).length);
            Assert.assertEquals(0, gts.getTrustLevels(null, auth1.getServiceURI()).length);
            Assert.assertEquals(3, gts.getTrustLevels(null, auth2.getServiceURI()).length);

            for (int i = 0; i < local.length; i++) {
                Assert.assertTrue(gts.doesTrustLevelExist(null, local[i].getName()));
                Assert.assertEquals(local[i], gts.getTrustLevel(null, local[i].getName()));
                Assert.assertEquals(local[i].getSourceGTS(), gts.getTrustLevel(null, local[i].getName()).getSourceGTS());
            }

            for (int i = 0; i < remote2.length; i++) {
                Assert.assertTrue(gts.doesTrustLevelExist(null, remote2[i].getName()));
                Assert.assertEquals(remote2[i], gts.getTrustLevel(null, remote2[i].getName()));
                Assert.assertEquals(remote2[i].getSourceGTS(), gts.getTrustLevel(null, remote2[i].getName()).getSourceGTS());
            }

            getInternalGTS().synchronizeTrustLevels(auth1.getServiceURI(), Arrays.asList(remote1));

            Assert.assertEquals(7, gts.getTrustLevels(null).length);
            Assert.assertEquals(3, gts.getTrustLevels(null,GTS_URI).length);
            Assert.assertEquals(2, gts.getTrustLevels(null,auth1.getServiceURI()).length);
            Assert.assertEquals(2, gts.getTrustLevels(null,auth2.getServiceURI()).length);

            for (int i = 0; i < local.length; i++) {
                Assert.assertTrue(gts.doesTrustLevelExist(null, local[i].getName()));
                Assert.assertEquals(local[i], gts.getTrustLevel(null, local[i].getName()));
                Assert.assertEquals(local[i].getSourceGTS(), gts.getTrustLevel(null, local[i].getName()).getSourceGTS());
            }

            for (int i = 1; i < remote1.length; i++) {
                Assert.assertTrue(gts.doesTrustLevelExist(null, remote1[i].getName()));
                TrustLevel l = gts.getTrustLevel(null, remote1[i].getName());
                Assert.assertEquals(remote1[i], l);
                Assert.assertEquals(remote1[i].getSourceGTS(), gts.getTrustLevel(null, remote1[i].getName()).getSourceGTS());
            }

            for (int i = 1; i < remote2.length; i++) {
                Assert.assertTrue(gts.doesTrustLevelExist(null, remote2[i].getName()));
                Assert.assertEquals(remote2[i], gts.getTrustLevel(null, remote2[i].getName()));
                Assert.assertEquals(remote2[i].getSourceGTS(), gts.getTrustLevel(null, remote2[i].getName()).getSourceGTS());
            }

            gts.removeAuthority(ADMIN_USER, auth1.getServiceURI());
            Assert.assertEquals(7, gts.getTrustLevels(null).length);
            Assert.assertEquals(3, gts.getTrustLevels(null,GTS_URI).length);
            Assert.assertEquals(2, gts.getTrustLevels(null,auth1.getServiceURI()).length);
            Assert.assertEquals(2, gts.getTrustLevels(null,auth2.getServiceURI()).length);

            // Lets add a Trusted Authority and make sure that it is deleted
            // after we sync to nothing
            TrustedAuthority ta = new TrustedAuthority();
            CA ca = new CA();
            ta.setName(ca.getCertificate().getSubjectDN().toString());
            X509Certificate x509 = new X509Certificate();
            x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
            ta.setCertificate(x509);
            ta.setStatus(Status.TRUSTED);
            ta.setTrustLevels(toTrustLevels(remote1[1].getName()));
            gts.addTrustedAuthority(ADMIN_USER, ta);
            TrustedAuthorityFilter filter = new TrustedAuthorityFilter();
            filter.setTrustLevels(toTrustLevels(remote1[1].getName()));
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, filter).length);

            getInternalGTS().synchronizeTrustLevels(auth1.getServiceURI(), null);
            Assert.assertEquals(1, gts.findTrustedAuthorities(null, new TrustedAuthorityFilter()).length);
            Assert.assertEquals(0, gts.findTrustedAuthorities(null, filter).length);
            Assert.assertEquals(3, gts.getTrustLevels(null,GTS_URI).length);
            Assert.assertEquals(0, gts.getTrustLevels(null,auth1.getServiceURI()).length);
            Assert.assertEquals(2, gts.getTrustLevels(null,auth2.getServiceURI()).length);
            getInternalGTS().synchronizeTrustLevels(auth2.getServiceURI(), Arrays.asList(remote2));
            Assert.assertEquals(3, gts.getTrustLevels(null,GTS_URI).length);
            Assert.assertEquals(0, gts.getTrustLevels(null,auth1.getServiceURI()).length);
            Assert.assertEquals(3, gts.getTrustLevels(null,auth2.getServiceURI()).length);

            for (int i = 0; i < local.length; i++) {
                Assert.assertTrue(gts.doesTrustLevelExist(null, local[i].getName()));
                Assert.assertEquals(local[i], gts.getTrustLevel(null, local[i].getName()));
                Assert.assertEquals(local[i].getSourceGTS(), gts.getTrustLevel(null, local[i].getName()).getSourceGTS());
            }

            for (int i = 0; i < remote2.length; i++) {
                Assert.assertTrue(gts.doesTrustLevelExist(null, remote2[i].getName()));
                Assert.assertEquals(remote2[i], gts.getTrustLevel(null, remote2[i].getName()));
                Assert.assertEquals(remote2[i].getSourceGTS(), gts.getTrustLevel(null, remote2[i].getName()).getSourceGTS());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (gts != null) {
                try {
                    clearDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Configuration getConfiguration() {
        return (Configuration) srm.getResource(Configuration.RESOURCE);
    }

    @Before
    public void clearDatabase() {
        try {
            org.cagrid.gts.service.impl.GTS gtsInternal = getInternalGTS();
            gtsInternal.clearDatabase();
            Assert.assertEquals(0, gtsInternal.getDatabase().getUsedConnectionCount());
        } catch (Exception e) {
            Assert.fail("Could not clear database:" + e.getMessage());
        }
    }

    private org.cagrid.gts.service.impl.GTS getInternalGTS() {
        org.cagrid.gts.service.impl.GTSImpl impl = (org.cagrid.gts.service.impl.GTSImpl) gts;
        org.cagrid.gts.service.impl.GTS gtsInternal = impl.getGTS();
        return gtsInternal;
    }

    private TrustedAuthorityFilter getFilterForTA(TrustedAuthority ta) {
        TrustedAuthorityFilter f = new TrustedAuthorityFilter();
        f.setStatus(Status.TRUSTED);
        f.setLifetime(Lifetime.VALID);
        f.setName(ta.getName());
        return f;
    }

    private void addTrustLevels(GTS gts, String gridId) throws Exception {
        TrustLevel l1 = new TrustLevel();
        l1.setName(LEVEL_ONE);
        TrustLevel l2 = new TrustLevel();
        l2.setName(LEVEL_TWO);
        gts.addTrustLevel(gridId, l1);
        gts.addTrustLevel(gridId, l2);
    }

    private TrustLevel getTrustLevel() {
        cacount = cacount + 1;
        String name = "Trust Level " + cacount;
        return getTrustLevel(name);
    }

    private TrustLevel getTrustLevel(String name) {
        TrustLevel l1 = new TrustLevel();
        l1.setName(name);
        l1.setDescription(name);
        return l1;
    }

    private PermissionFilter permissionToPermissionFilter(Permission p) {
        PermissionFilter pf = new PermissionFilter();
        pf.setGridIdentity(p.getGridIdentity());
        pf.setRole(p.getRole());
        pf.setTrustedAuthorityName(p.getTrustedAuthorityName());
        return pf;
    }

    private AuthorityGTS getAuthority(String uri, int priority) {
        TimeToLive ttl = new TimeToLive();
        ttl.setHours(1);
        ttl.setMinutes(1);
        ttl.setSeconds(1);
        return getAuthority(uri, priority, ttl);
    }

    private AuthorityGTS getAuthority(String uri, int priority, TimeToLive ttl) {
        AuthorityGTS a1 = new AuthorityGTS();
        a1.setServiceURI(uri);
        a1.setPriority(priority);
        a1.setPerformAuthorization(true);
        a1.setServiceIdentity(uri);
        a1.setSyncTrustLevels(true);
        a1.setTimeToLive(ttl);
        return a1;
    }

    private void updateAuthority(AuthorityGTS gts) {
        TimeToLive ttl = new TimeToLive();
        ttl.setHours(2);
        ttl.setMinutes(2);
        ttl.setSeconds(2);
        gts.setPerformAuthorization(false);
        gts.setServiceIdentity(null);
        gts.setSyncTrustLevels(false);
        gts.setTimeToLive(ttl);
    }

    private TrustedAuthority getTrustedAuthority() throws Exception {
        cacount = cacount + 1;
        String dn = dnPrefix + cacount;
        return getTrustedAuthority(dn);
    }

    private TrustedAuthority getTrustedAuthority(String dn) throws Exception {
        CA ca = new CA(dn);
        Calendar c = new GregorianCalendar();
        c.add(Calendar.HOUR, 1);
        String name = ca.getCertificate().getSubjectDN().toString();
        BigInteger sn = new BigInteger(String.valueOf(System.currentTimeMillis()));
        CRLEntry entry = new CRLEntry(sn, CRLReason.PRIVILEGE_WITHDRAWN);
        ca.updateCRL(entry);
        TrustedAuthority ta = new TrustedAuthority();
        ta.setName(name);
        X509Certificate x509 = new X509Certificate();
        x509.setCertificateEncodedString(CertUtil.writeCertificate(ca.getCertificate()));
        ta.setCertificate(x509);
        X509CRL crl = new X509CRL();
        crl.setCrlEncodedString(CertUtil.writeCRL(ca.getCRL()));
        ta.setCRL(crl);
        ta.setStatus(Status.TRUSTED);
        ta.setTrustLevels(toTrustLevels(LEVEL_ONE));
        ta.setIsAuthority(Boolean.TRUE);
        return ta;
    }

    public TrustLevels toTrustLevels(String s) {
        TrustLevels levels = new TrustLevels();
        levels.getTrustLevel().add(s);
        return levels;
    }

}
