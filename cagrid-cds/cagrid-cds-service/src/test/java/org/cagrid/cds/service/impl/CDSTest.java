package org.cagrid.cds.service.impl;

import org.junit.Assert;
import org.junit.Test;

public class CDSTest extends BaseTest {

    @Test
    public void testGetServiceMetadata() {
        Assert.assertNotNull(cds.getServiceMetadata());
    }

    @Test
    public void testGetServiceSecurityMetadata() {
        Assert.assertNotNull(cds.getServiceSecurityMetadata());
    }
}
