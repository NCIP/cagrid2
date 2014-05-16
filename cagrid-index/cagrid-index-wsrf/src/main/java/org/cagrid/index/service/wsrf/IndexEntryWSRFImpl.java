package org.cagrid.index.service.wsrf;

import javax.xml.ws.WebServiceContext;

import org.cagrid.index.service.IndexEntryService;
import org.cagrid.index.wsrf.stubs.BigIndexEntryPortTypeImpl;

public class IndexEntryWSRFImpl extends BigIndexEntryPortTypeImpl {
    private IndexEntryService entryService;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public IndexEntryWSRFImpl(IndexEntryService entryService) {
        this.entryService = entryService;

    }
}
