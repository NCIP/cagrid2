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
package org.globus.mds.bigindex.impl.database.xml.xindice;

import org.globus.wsrf.utils.PerformanceLog;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;
import org.apache.axis.message.MessageElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import EDU.oswego.cs.dl.util.concurrent.ReentrantWriterPreferenceReadWriteLock;

/**
 * 
 */
public class XindiceIndexDatabase 
{
    static Log logger = 
        LogFactory.getLog(XindiceIndexDatabase.class.getName());
    
    static PerformanceLog performanceLogger = 
        new PerformanceLog(
            XindiceIndexDatabase.class.getName() + ".performance");
        
    private static final String BASE_COLLECTION_NAME = "bigindex-service";
    private static final String METADATA_TAG = "document-metadata";
    private static final String DEFAULT_ELEMENT_INDEX_NAME = "defaultElementIndex";
    private static final String DEFAULT_ATTRIB_INDEX_NAME = "defaultAttributeIndex";    
    
    private String rootURI = "xindice-embed:///db";
    private String baseURI = rootURI + "/" + this.BASE_COLLECTION_NAME;    
    private String rootCollectionName;
    private String defaultElementIndexPattern = null;
    private String defaultAttributeIndexPattern = null;
    
    private XindiceDriver db = null;
    
    private boolean isDebug = logger.isDebugEnabled();
    private boolean isLocal = true;
    private boolean addAsString = true;
    private boolean isProfiling = true;
    private boolean indexAdded = false;
    
    private ReentrantWriterPreferenceReadWriteLock rwLock = 
        new ReentrantWriterPreferenceReadWriteLock();
        
    public XindiceIndexDatabase(String collectionName) throws Exception
    {                               
        this.init(collectionName,null);
    }
        
    public XindiceIndexDatabase(String collectionName, String dbURI) 
        throws Exception
    {
        this.init(collectionName, dbURI);
    }
    
    protected void init(String collectionName, String dbURI) 
    {
        try 
        {
            if ((dbURI != null) && (dbURI.length()>0)) {  
                this.setDatabaseURI(dbURI);
            }

            this.db = new XindiceDriver(this.isLocal);
            this.db.setProfiling(true);
            this.db.setVerbose(false);         
            this.initializeCollection(collectionName, true, true);  
        }
        catch (Exception e)
        {
            logger.warn("Exception while initializing Xindice database: " + e);
        }
    }
    
    private void setDatabaseURI(String dbURI) throws Exception
    {
        if ((dbURI != null) || (dbURI.length()!=0)) {
            this.rootURI = dbURI;           
        } 
        
        if (this.rootURI.startsWith("xindice-embed:")) {
            this.isLocal = true;
        } else {
            this.isLocal = false;
        }
        this.baseURI = this.rootURI + "/" + this.BASE_COLLECTION_NAME;  
    }
    
    private void initializeCollection(String collectionName, 
                                      boolean createDefaultIndex,
                                      boolean replaceExisting) 
        throws Exception
    {
        try 
        {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }
            
            String colURI = this.baseURI + "/" + collectionName;

            if (!this.db.findCollection(this.rootURI, 
                                        this.BASE_COLLECTION_NAME)) {
                synchronized(this.db) {
                    this.db.createCollection(this.rootURI, 
                                             this.BASE_COLLECTION_NAME);
                }
                this.db.checkpointCollection(
                    this.rootURI + "/" + this.BASE_COLLECTION_NAME);                
            }

            synchronized(this.db) 
            {
                boolean create = true;
                if (this.db.findCollection(this.baseURI, collectionName)) {
                    if (replaceExisting) {            
                        this.db.dropCollection(this.baseURI, collectionName);
                    } else {
                        create = false;
                    }
                }
                if (create) {
                    this.db.createCollection(this.baseURI, collectionName);
                     
                }
            }
            this.db.checkpointCollection(colURI);   
            this.rootCollectionName = collectionName;
        }
        finally
        {
            if (this.isProfiling) {
                this.performanceLogger.stop("initializeCollection");
            }            
        }
    }
    
    public void checkpointCollection(String colURI) throws Exception
    {
        try 
        {
            this.rwLock.writeLock().acquire();        
            this.db.checkpointCollection(colURI);       
        } 
        finally
        {
            this.rwLock.writeLock().release();            
        }
    }
    
    public String createID(String parentCol) throws Exception 
    {
        return this.db.createUniqueID(parentCol);
    }
    
    public String getDefaultCollectionURI() 
    {
        return this.getCollectionURI(null);
    }
    
    public String getCollectionURI(String colName) 
    {
        String URI = this.baseURI + "/" + this.rootCollectionName;
            
        if ((colName != null) && 
            (colName.length()>0)) 
        {
            try 
            {        
                if (this.isProfiling) {
                    this.performanceLogger.start();
                }                        
                if (!this.db.findCollection(URI, colName)) {
                    synchronized(this.db) {
                        this.db.createCollection(URI, colName);
                    }
                    this.db.checkpointCollection(URI + "/" + colName);                
                }
                URI += "/" + colName;
            }
            catch (Exception e)
            {
                logger.error("Exception getting current collection uri: " + e);
            }
            finally
            {
                if (this.isProfiling) {
                    this.performanceLogger.stop("getCurrentCollectionURI");
                }            
            }
        }
        
        return URI;
    }
    
    public String addDocument(String collectionURI,
                              String docName,
                              Object doc,
                              boolean asString) 
        throws Exception
    {
        String docID = collectionURI + "/";
        
        try {
            rwLock.writeLock().acquire();

            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            if (asString) 
            {
                docID = docID + 
                    this.db.addDocumentString(collectionURI, (String)doc, docName); 
                if (this.isProfiling) {
                    this.performanceLogger.stop("addData(string) " + docName);
                }                              
            } 
            else 
            {
                docID = docID + 
                    this.db.addDocumentDOM(collectionURI, (Node)doc, docName);
                if (this.isProfiling) {
                    this.performanceLogger.stop("addData(node) " + docName);
                }                    
            }

            this.db.checkpointCollection(collectionURI);  
            this.addDefaultIndexers(collectionURI);
        }
        finally 
        {
            rwLock.writeLock().release();        
        }        
        return docID;
    }    
    
    public Object getDocument(String colURI,
                              String docName,
                              boolean asString)
        throws Exception
    {
        Object result;

        if (this.isProfiling) {
            this.performanceLogger.start();
        }
        
        
        if (asString) 
        {
            result = this.db.getDocumentAsString(colURI, docName);
            if (this.isProfiling) {
                this.performanceLogger.stop("getData(string)");
            }                   
        }
        else 
        {
            result = this.db.getDocumentAsDOM(colURI, docName);
            if (result instanceof Document) {
                result = ((Document)result).getDocumentElement();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("getData(node)");
            }                           
        }
        
        return result;
    }
    
    
    public void removeDocument(String collectionURI,
                               String docName) 
        throws Exception
    {
        try {
            rwLock.writeLock().acquire();

            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            this.db.deleteDocument(collectionURI, docName); 

            if (this.isProfiling) {
                this.performanceLogger.stop("removeDocument " + docName);
            }
        } finally {       
            rwLock.writeLock().release();        
        }
    }   
    
    public List query(String xpath, String[] namespaces) 
        throws Exception
    {
        if (this.isProfiling) {
            this.performanceLogger.start();
        }
        
        String URI = this.baseURI + "/" + this.rootCollectionName;
      
        ArrayList results = new ArrayList();        

        if (!this.db.findCollection(this.baseURI, this.rootCollectionName)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Could not find collection: " + this.rootCollectionName);
            }
            return results;
        }

        try {
            rwLock.readLock().acquire();

            Object[] queryResults = 
                this.db.XPathQuery(URI, 
                                   xpath,
                                   namespaces,
                                   XindiceDriver.XINDICE_DOCTYPE_DOM);


            if (queryResults != null) {
                for (int i=0;i<queryResults.length;i++) {
                    results.add(
                        new MessageElement(
                            ((Document)queryResults[i]).getDocumentElement()));
                }
            }

            if (this.isProfiling) {
                this.performanceLogger.stop("query");
            }
        } finally {
            rwLock.readLock().release();        
        }
        
        return results;
    }
    
    public void setDefaultElementIndexPattern(String pattern)  {
        this.defaultElementIndexPattern = pattern;
    }
    
    public String getDefaultElementIndexPattern() {
        return this.defaultElementIndexPattern;
    }
    
    public void setDefaultAttributeIndexPattern(String pattern)  {
        this.defaultAttributeIndexPattern = pattern;
    }
    
    public String getDefaultAttributeIndexPattern() {
        return this.defaultAttributeIndexPattern;
    }        
    
    private synchronized void addDefaultIndexers(String colURI) throws Exception 
    {
        if (this.indexAdded) {
            return;
        }
        
        if (this.defaultElementIndexPattern != null) {
            this.db.addIndexer(colURI,
                               DEFAULT_ELEMENT_INDEX_NAME,
                               null,
                               this.defaultElementIndexPattern);
            this.indexAdded = true;
        }
        if (this.defaultAttributeIndexPattern != null) {        
            this.db.addIndexer(colURI,
                               DEFAULT_ATTRIB_INDEX_NAME,
                               null,
                               this.defaultAttributeIndexPattern); 
            this.indexAdded = true;
        }
    }    
    
    public void finalize() {
        try {
            this.db.shutdown(baseURI);
        } catch (Exception e) {
            logger.warn("Exception while performing database shutdown: " + e);
        }
    }
    
    public void destroy() throws Exception
    {
        try 
        {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }        
            this.db.dropCollection(this.baseURI, this.rootCollectionName);
        }
        finally
        {
            if (this.isProfiling) {
                this.performanceLogger.stop("destroy");
            }            
        }         
    }
    
}
