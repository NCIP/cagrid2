package org.cagrid.cds.service.impl;

import org.cagrid.cds.service.impl.manager.DelegationManager;import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.EventManager;

public class CDSProperties {
    private Database database;
    private DelegationManager delegationManager;
    private EventManager eventManager;

    public CDSProperties(Database db, DelegationManager delegationManager, EventManager eventManager) {
        this.database = db;
        this.delegationManager = delegationManager;
        this.eventManager = eventManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public Database getDatabase() {
        return database;
    }

    public DelegationManager getDelegationManager() {
        return delegationManager;
    }
}
