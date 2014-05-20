package org.cagrid.index.service.wsrf;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;

import org.cagrid.index.service.IndexEntryService;
import org.cagrid.index.wsrf.stubs.BigIndexEntryPortTypeImpl;

public class IndexEntryWSRFImpl extends BigIndexEntryPortTypeImpl {

    public static final String BIGINDEX_NS = "http://mds.globus.org/bigindex/2008/11/24";
    public static final QName ENTRY_KEY = new QName(BIGINDEX_NS, "ServiceGroupEntryKey");

    private IndexEntryService entryService;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public IndexEntryWSRFImpl(IndexEntryService entryService) {
        this.entryService = entryService;
    }
}
