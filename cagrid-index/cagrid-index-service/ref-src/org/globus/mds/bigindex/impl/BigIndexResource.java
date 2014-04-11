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

import java.rmi.RemoteException;
import java.util.Calendar;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;

import org.globus.wsrf.NotifyCallback;
import org.globus.wsrf.ResourceException;
import org.globus.wsrf.ResourceKey;
import org.globus.wsrf.ResourceProperty;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.TopicList;
import org.globus.wsrf.TopicListAccessor;
import org.globus.wsrf.Constants;
import org.globus.wsrf.NotificationConsumerManager;
import org.globus.wsrf.container.ServiceHost;
import org.globus.wsrf.utils.ContextUtils;

import org.globus.wsrf.impl.ReflectionResourceProperty;
import org.globus.wsrf.impl.SimpleTopicList;
import org.globus.wsrf.impl.SimpleResourcePropertyMetaData;

import org.apache.axis.MessageContext;
import org.apache.axis.message.addressing.EndpointReferenceType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.globus.wsrf.impl.servicegroup.EntryResourcePropertyTopic;
import org.globus.wsrf.impl.servicegroup.ServiceGroupConstants;

import org.globus.mds.aggregator.impl.AggregatorServiceGroupResource;
import org.globus.mds.aggregator.impl.ServiceGroupEntryAggregatorSink;

import org.globus.mds.bigindex.impl.database.xml.xindice.XindiceIndexDatabase;

/**
 * BigIndexResource maintains an aggregation of resource properties
 * from other resources that have been registered to it.  The data is stored
 * in a Xindice database.
 */
public class BigIndexResource extends AggregatorServiceGroupResource
                              implements TopicListAccessor
{
    private static Log logger =
            LogFactory.getLog(BigIndexResource.class.getName());

    private ResourceKey resourceKey = null;

    static NotificationConsumerManager consumerManager = 
        NotificationConsumerManager.getInstance();

    public static final QName RP_SET = 
        new QName(BigIndexService.BIGINDEX_NS, "BigIndexResourceProperties");

    protected Calendar terminationTime;
    
    private ResourcePropertySet propSet;
    private TopicList topicList;
    private String [] strings = null;

    private String docID = null;
    private XindiceIndexDatabase db;
    private DBCheckpointThread checkpointThread = new DBCheckpointThread();
    private int dbCheckpointInterval = 60000 * 10;
    
    public BigIndexResource() 
    {
        super.init(BigIndexResource.RP_SET);
        this.propSet = this.getResourcePropertySet();
        this.topicList = new SimpleTopicList(this);
        ResourceProperty prop = null;
        
        EntryResourcePropertyTopic rpTopic = 
            new EntryResourcePropertyTopic(
                this.propSet.get(ServiceGroupConstants.ENTRY));
        
        this.propSet.add(rpTopic);
        this.topicList.addTopic(rpTopic);
        
        try {
            prop = new ReflectionResourceProperty(
                SimpleResourcePropertyMetaData.TERMINATION_TIME, this);
            this.propSet.add(prop);
            
            prop = new ReflectionResourceProperty(
                SimpleResourcePropertyMetaData.CURRENT_TIME, this);            
            this.propSet.add(prop);                        
            
            // initialize aggregator
            this.initializeAggregator();
            
            // initialize database
            this.initializeDatabase();            
            
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    public XindiceIndexDatabase getDatabase() {
        return this.db;
    }
    
    public ResourceKey getResourceKey() {
        return resourceKey;
    }

    public void setTerminationTime(Calendar time) {
        logger.debug("Set Termination time called: " + time.getTime());
        this.terminationTime = time;
    }
    
    public Calendar getTerminationTime() {
        return this.terminationTime;
    }

    public Calendar getCurrentTime() {
        return Calendar.getInstance();
    }

    public TopicList getTopicList() {
        return this.topicList;
    }

    /** returns an EPR to a newly created consumer, such that notifications
    sent to this consumer will be delivered to the callback. */
    public EndpointReferenceType getConsumer(NotifyCallback callback)
    {
        EndpointReferenceType consumerEPR;

        try {
            consumerEPR = consumerManager.createNotificationConsumer(callback);
        } catch(ResourceException re) {
            logger.error("Exception getting a consumer");
            return null;
        }
        return consumerEPR;
    }
    
    private void initializeAggregator() throws Exception
    {
        // initialize aggregator
        ServiceGroupEntryAggregatorSink sink = 
            new ServiceGroupEntryAggregatorSink();

        this.loadCompatibleSources(sink);        
    }
    

    private void initializeDatabase() throws Exception
    {
        // initialize database
        MessageContext context = MessageContext.getCurrentContext();
        String serviceName = ContextUtils.getTargetServicePath(context);
        String serviceAddr = 
            ServiceHost.getHost() + "." +  
                Integer.toString(ServiceHost.getPort());

        String rootCollectionName = serviceAddr + "." + serviceName;
        this.db = new XindiceIndexDatabase(rootCollectionName);

        if (logger.isDebugEnabled()) {
            logger.debug("Initialized database rootCollection: " +
                         rootCollectionName);
        }
        
        this.checkpointThread.setDaemon(true);
        this.checkpointThread.start();        
    }
    
    class DBCheckpointThread extends Thread 
    {
        public void run() 
        {
            while(true) 
            {                
                try {
                    Thread.sleep(dbCheckpointInterval);
                } catch(InterruptedException e) {
                    logger.warn("DBCheckpointThread interrupted. Terminating.");
                    return;
                }
                
                if (BigIndexResource.this.db != null) {
                    try {
                        BigIndexResource.this.db.checkpointCollection(
                            BigIndexResource.this.db.getDefaultCollectionURI());
                        if (logger.isDebugEnabled()) {
                            logger.debug("Checkpointed database (flushed to disk)");                              
                        }                      
                    } catch (Exception e) {
                        logger.warn("Exception while checkpointing database", e);
                    }
                    System.gc();                    
                }
            }
        }
    }
}

