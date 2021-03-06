package org.cagrid.cds.service.impl.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.database.Metadata;
import org.cagrid.tools.database.MetadataManager;


/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class PropertyManager {

    private static String TABLE = "properties";
    private static String VERSION_PROPERTY = "version";
    private static String KEY_MANAGER = "Key Manager";
    public static String CDS_VERSION_1_2 = "1.2";
    public static String CDS_VERSION_1_3 = "1.3";
    public static String CURRENT_VERSION = CDS_VERSION_1_3;
    private MetadataManager manager;
    private Metadata version;
    private Metadata keyManager;
    private Log log;


    public PropertyManager(Database db) throws CDSInternalException {
        try {
            this.log = LogFactory.getLog(this.getClass().getName());
            this.manager = new MetadataManager(db, TABLE);
            version = manager.get(VERSION_PROPERTY);
            if (version == null) {
                version = new Metadata();
                version.setName(VERSION_PROPERTY);
                version.setDescription("The software version of this CDS.");
                version.setValue(String.valueOf(CURRENT_VERSION));
                this.manager.update(version);
            }
            keyManager = manager.get(KEY_MANAGER);
            if (keyManager == null) {
                keyManager = new Metadata();
                keyManager.setName(KEY_MANAGER);
                keyManager.setDescription("The key manager class used to manage keys.");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            CDSInternalException fault = FaultHelper.createFaultException(
                    CDSInternalException.class, "Unexpected error initializing the property manager.");
            FaultHelper.addMessage(fault, e.getMessage());
            throw fault;
        }
    }


    public void setKeyManager(String keyManagerClass) throws CDSInternalException {
        try {
            this.keyManager.setValue(keyManagerClass);
            this.manager.update(this.keyManager);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            CDSInternalException fault = FaultHelper.createFaultException(
                    CDSInternalException.class, "Unexpected database error.");
            FaultHelper.addMessage(fault, e.getMessage());
            throw fault;
        }
    }


    public String getKeyManager() {
        return this.keyManager.getValue();
    }


    public void setCurrentVersion() throws CDSInternalException {
        this.setVersion(CURRENT_VERSION);
    }


    public void setVersion(String version) throws CDSInternalException {
        try {
            this.version.setValue(version);
            this.manager.update(this.version);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            CDSInternalException fault = FaultHelper.createFaultException(
                    CDSInternalException.class, "Unexpected database error.");
            FaultHelper.addMessage(fault, e.getMessage());
            throw fault;
        }
    }


    public String getVersion() {
        return this.version.getValue();
    }


    public void clearAllProperties() throws CDSInternalException {
        try {
            this.version.setValue(null);
            this.keyManager.setValue(null);
            this.manager.clearDatabase();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            CDSInternalException fault = FaultHelper.createFaultException(
                    CDSInternalException.class, "Unexpected database error.");
            FaultHelper.addMessage(fault, e.getMessage());
            throw fault;
        }
    }

}
