package org.cagrid.gts.service.impl;

import org.cagrid.gts.model.AuthorityGTS;
import org.cagrid.gts.model.AuthorityPrioritySpecification;
import org.cagrid.gts.model.AuthorityPriorityUpdate;
import org.cagrid.gts.model.Permission;
import org.cagrid.gts.model.PermissionFilter;
import org.cagrid.gts.model.TimeToLive;
import org.cagrid.gts.model.TrustLevel;
import org.cagrid.gts.model.TrustLevels;
import org.cagrid.gts.service.GTS;
import org.cagrid.gts.service.exception.PermissionDeniedException;
import org.cagrid.gts.service.impl.test.Utils;
import org.cagrid.gts.tools.service.PermissionBootstapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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

    // @Test
    // public void testGetTrustedIdentityProviders()
    // throws GTSInternalException, NoSuchResourceException,
    // InvalidResourceKeyException, ResourceException {
    // Assert.assertNotNull(gts.getTrustedIdentityProviders());
    //
    // ResourceHome resourceHome = gts.getResourceHome();
    // ResourcePropertySet resourceImpl = (ResourcePropertySet) resourceHome
    // .find(null);
    // @SuppressWarnings("unchecked")
    // ResourceProperty<TrustedIdentityProviders> trustedIdentityProvidersProperty = (ResourceProperty<TrustedIdentityProviders>) resourceImpl
    // .get(new QName("http://cagrid.nci.nih.gov/1/gts-ifs",
    // "TrustedIdentityProviders"));
    // TrustedIdentityProviders trustedIdentityProviders = trustedIdentityProvidersProperty
    // .get(0);
    // Assert.assertNotNull(trustedIdentityProviders);
    // }

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
            // try {
            // gts.clearDatabase();
            // Assert.assertEquals(0, gts.getDatabase().getUsedConnectionCount());
            // } catch (Exception e) {
            // e.printStackTrace();
            // }

        }

    }

    private Configuration getConfiguration() {
        return (Configuration) srm.getResource(Configuration.RESOURCE);
    }

    @BeforeClass
    public static void clearDatabase() {
        try {
            Database db = Utils.getDBManager().getDatabase();
            System.out.println("Destroying database........ " + db.getDatabaseName());
            db.destroyDatabase();
            System.out.println("The database " + db.getDatabaseName() + " was successfully destroyed!!!");
        } catch (Exception e) {
            Assert.fail("Could not clear database:" + e.getMessage());
        }
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

    // protected void setUp() throws Exception {
    // super.setUp();
    // cacount = 0;
    // GTS.SYNC_WITH_AUTHORITIES = false;
    // }

    public TrustLevels toTrustLevels(String s) {
        TrustLevels levels = new TrustLevels();
        levels.getTrustLevel().add(s);
        return levels;
    }

}
