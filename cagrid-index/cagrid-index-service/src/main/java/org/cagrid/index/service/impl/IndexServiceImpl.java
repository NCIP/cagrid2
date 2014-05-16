package org.cagrid.index.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.cagrid.index.service.IndexService;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;

public class IndexServiceImpl implements IndexService {

    // TODO: replace this with the xml db
    private Map<String, EntryType> entries = new HashMap<String, EntryType>();

    public String add(EntryType entry) {
        String entryId = UUID.randomUUID().toString();
        // TODO: need something more to store things like termination time, etc
        entries.put(entryId, entry);

        return entryId;
    }

}
