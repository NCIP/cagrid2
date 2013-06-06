package org.cagrid.cds.service.impl;

import org.cagrid.cds.service.CredentialDelegationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-beans.xml", "classpath:META-INF/spring/cds-beans-configuration.xml" })
public class CDSImplTest {

    @javax.annotation.Resource
    private CredentialDelegationService cds;

    @Test
    public void testCredentialDelegationService() {
        Assert.assertNotNull(cds);
    }

    @Test
    public void testGetServiceMetadata() {
        Assert.assertNotNull(cds.getServiceMetadata());
    }

    @Test
    public void testGetServiceSecurityMetadata() {
        Assert.assertNotNull(cds.getServiceSecurityMetadata());
    }
}
