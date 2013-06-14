package org.cagrid.gme.service.impl;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.gme.service.impl.testutils.GMETestCaseBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class HibernateMappingTestCase extends GMETestCaseBase {

    @Resource
    protected SessionFactory sessionFactory;

    @Test
    public void testEverything() throws Exception {
        Map metadata = sessionFactory.getAllClassMetadata();
        String className = "";
        for (Iterator i = metadata.values().iterator(); i.hasNext();) {
            Session session = sessionFactory.openSession();
            try {
                EntityPersister persister = (EntityPersister) i.next();
                className = persister.getEntityName();
                // log.debug("select: " + className);
                List result = session.createQuery("from " + className + " c").list();
                // log.debug("returned " + result.size() + " records for " +
                // className);

                assertTrue(true);

            } catch (Exception ex) {
                fail("Hibernate mapping error. Following class has incorrect mapping: " + className + ExceptionUtils.getFullStackTrace(ex));
            } finally {
                session.close();
            }
        }
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
