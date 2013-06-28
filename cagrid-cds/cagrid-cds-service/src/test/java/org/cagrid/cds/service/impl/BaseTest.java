package org.cagrid.cds.service.impl;

import org.cagrid.cds.service.CredentialDelegationService;
import org.cagrid.cds.service.impl.manager.DelegatedCredentialManager;
import org.cagrid.cds.service.impl.manager.DelegationManager;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.groups.GroupManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@ContextConfiguration(locations = { "classpath*:test-beans.xml", "classpath:META-INF/spring/cds-beans-configuration.xml" })
public class BaseTest {

    @Resource
    protected CredentialDelegationService cds;

    @Resource
    protected DelegationManager delegationManager;

    @Resource
    protected GroupManager groupManager;

    @Resource
    protected DelegatedCredentialManager delegatedCredentialManager;

    @Resource
    protected Database database;

    @Test
    public void testResources() {
        assertNotNull(cds);
        assertNotNull(delegatedCredentialManager);
        assertNotNull(groupManager);
        assertNotNull(delegationManager);
    }
}
