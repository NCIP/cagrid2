package org.cagrid.mms.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.cagrid.mms.model.SourceDescriptor;
import org.cagrid.mms.service.impl.MMS;
import org.cagrid.mms.service.impl.MMSGeneralException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations={
                SpringTestApplicationContextConstants.MMS_BASE_LOCATION
        })
public abstract class MMSAbstractBaseTestCaseBase {

	@Autowired
    public MMS mms;

    @Before
    public void onSetup() {
        assertNotNull(this.mms);
    }


    public MMSAbstractBaseTestCaseBase() {
    }


    @Test
    public void testMetdata() {
        try {
            assertNotNull(this.mms.getModelSourceMetadata());
            String defaultSourceIdentifier = this.mms.getModelSourceMetadata().getDefaultSourceIdentifier();
            assertNotNull(defaultSourceIdentifier);

            assertNotNull("Cannot have a null list of supported sources!", this.mms.getModelSourceMetadata()
                .getSupportedModelSources().getSource());

            assertTrue("Cannot have an empty list of supported sources!", this.mms.getModelSourceMetadata()
                .getSupportedModelSources().getSource().size() > 0);

            boolean found = false;
            for (SourceDescriptor desc : this.mms.getModelSourceMetadata().getSupportedModelSources().getSource()) {
                assertNotNull(desc.getIdentifier());
                if (desc.getIdentifier().equals(defaultSourceIdentifier)) {
                    found = true;
                }
            }
            assertTrue("The default source identifer (" + defaultSourceIdentifier
                + ") was not found in the supported sources list!", found);

        } catch (MMSGeneralException e) {
            e.printStackTrace();
            fail("Problem accessing metadata:" + e.getMessage());
        }
    }

}