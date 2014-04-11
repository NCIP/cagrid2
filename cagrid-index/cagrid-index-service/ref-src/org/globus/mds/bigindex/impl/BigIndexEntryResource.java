/*
* Portions of this file Copyright 1999-2005 University of Chicago
* Portions of this file Copyright 1999-2005 The University of Southern California.
*
* This file or a portion of this file is licensed under the
* terms of the Globus Toolkit Public License, found at
* http://www.globus.org/toolkit/download/license.html.
* If you redistribute this file, with or without
* modifications, you must include this notice in the file.
*/
package org.globus.mds.bigindex.impl;

import org.globus.mds.bigindex.BigIndexContent;
import org.globus.mds.bigindex.BigIndexEntry;
import org.globus.mds.bigindex.impl.database.xml.xindice.XindiceIndexDatabase;

import org.globus.mds.aggregator.impl.AggregatorServiceGroupResource;
import org.globus.mds.aggregator.impl.AggregatorServiceGroupEntryResource;
import org.globus.mds.aggregator.types.AggregatorContent;
import org.globus.mds.aggregator.types.PairedKeyType;

import org.oasis.wsrf.servicegroup.EntryType;
import org.globus.wsrf.impl.servicegroup.ServiceGroupConstants;

import org.globus.wsrf.ResourceException;
import org.globus.wsrf.impl.SimpleResourceKey;
import org.globus.wsrf.impl.SimpleResourcePropertySet;
import org.globus.wsrf.utils.ContextUtils;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.ObjectDeserializer;

import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.components.uuid.UUIDGen;
import org.apache.axis.components.uuid.UUIDGenFactory;
import org.apache.axis.MessageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.w3c.dom.Element;
import javax.xml.namespace.QName;

/** 
 * Implements an aggregating service group entry resource backed by an XML database.
 */

public class BigIndexEntryResource 
    extends AggregatorServiceGroupEntryResource
               
{
    private static Log logger =
            LogFactory.getLog(BigIndexEntryResource.class.getName());
    
    private static final UUIDGen uuidGen = UUIDGenFactory.getUUIDGen(); 
    
    private EntryType entry = new EntryType();
    private String groupKey;
    private String entryKey;
    private String docID = null;
    private BigIndexResource indexResource = null;
    private XindiceIndexDatabase db = null;
    
    public BigIndexEntryResource() { }
    

    public void initialize(AggregatorServiceGroupResource aggrResource,
                           EndpointReferenceType memberEPR,
                           EndpointReferenceType serviceGroupEPR,
                           AggregatorContent content) 
        throws Exception
    {
        if (!(aggrResource instanceof BigIndexResource)) {      
            throw new Exception("Incompatible parent resource type, got " + 
				aggrResource.getClass().getName() + 
                                " but expected " + 
                                BigIndexResource.class.getName());
        }
        
        this.indexResource = (BigIndexResource)aggrResource;
        this.db = indexResource.getDatabase();           
        
        super.initialize(aggrResource, memberEPR, serviceGroupEPR, content);  
        
        BigIndexContent indexContent = new BigIndexContent();
        indexContent.setContentID(this.docID);  
        this.contentRP.add(indexContent);       
        
        MessageContext msgCtx = MessageContext.getCurrentContext();
        db.setDefaultElementIndexPattern(
            (String)ContextUtils.getServiceProperty(msgCtx,
                "BigIndexServiceEntry", "defaultElementIndexPattern"));        

        db.setDefaultAttributeIndexPattern(
            (String)ContextUtils.getServiceProperty(msgCtx,
                "BigIndexServiceEntry", "defaultAttributeIndexPattern"));          
        
    }
    
    protected void createResourceKey() 
    {
        this.groupKey = (String)this.aggrResource.getKey().getValue();        
        this.entryKey = uuidGen.nextUUID();    
        this.docID = this.entryKey;
        
        PairedKeyType pk = new PairedKeyType();
        pk.setGroupKey(this.groupKey);
        pk.setEntryKey(this.entryKey);            
        this.key = new SimpleResourceKey(BigIndexEntryService.ENTRY_KEY, pk);           
    }
    
    protected void createResourcePropertySet() {
        this.propSet = 
            new SimpleResourcePropertySet(BigIndexEntryService.RP_SET);          
    }
    
    public void setContent(AggregatorContent content) 
    {              
        if (content == null) {
            return;
        }       
        
        if (logger.isDebugEnabled()) {
            logger.debug("Updating content");
        }
        synchronized (this) {
            this.content = content;
            this.updateDatabase();
            // do not cache the aggregated data in memory
            this.content.getAggregatorData().set_any(null);            
        }                        
        
        if (this.indexResource.getConfiguration().getNotifyOnEntryContentChangeValue()) {
            this.indexResource.notifyChange();        
        }
    } 

    protected void updateDatabase() 
    {
        if (this.docID == null) {
            return;
        }
        
        this.entry.setMemberServiceEPR(this.memberEPR);
        this.entry.setServiceGroupEntryEPR(this.entryEPR);
        this.entry.setContent(this.content);        
        
        try {
            String collectionURI = this.db.getDefaultCollectionURI();

            BigIndexEntry bigEntry = new BigIndexEntry();
            bigEntry.setEntry(this.entry);
            String document =
                ObjectSerializer.toString(
                    bigEntry, bigEntry.getTypeDesc().getXmlType()); 
            
            this.db.addDocument(collectionURI,
                                this.docID,
                                document,
                                true);            

            if (logger.isDebugEnabled()) {
                logger.debug("Added service group entry document with ID " + 
                             this.docID + 
                             " to database collection " + 
                             collectionURI);
            }            
        } catch (Exception e) {
            logger.error("Error processing message(s): " + e);
        }        
        
    }
    
    public void remove() throws ResourceException 
    {
        super.remove();
        
        synchronized(this) {
            // delete any existing document...
            if (this.docID != null) {
                try {
                    this.db.removeDocument(
                        this.db.getDefaultCollectionURI(), this.docID);
                    this.docID = null;
                } catch (Exception e) {
                    logger.warn("Could not remove document: " + e);
                }    
            }
        }
    }
    
}

