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

/*
 * XindiceDriver.java
 *
 * Created on September 18, 2002, 2:29 AM
 *
 */

package org.globus.mds.bigindex.impl.database.xml.xindice;

import org.globus.wsrf.utils.PerformanceLog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XUpdateQueryService;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.DatabaseManager;

import org.apache.xindice.xml.dom.DocumentImpl;
import org.apache.xindice.xml.TextWriter;
import org.apache.xindice.client.xmldb.services.CollectionManager;
import org.apache.xindice.client.xmldb.services.DatabaseInstanceManager;
import org.apache.xindice.client.xmldb.services.XUpdateQueryServiceImpl;
import org.apache.xindice.tools.command.StringSerializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * Class wrapper for common Xindice operations using XML:DB API.
 * Essentially just a refactoring of the Xindice command-line tool
 * functions into a single class for simplified runtime access.
 */
public class XindiceDriver {
    // Collection manager path to instantiate
    public static final String XINDICEURI = "xindice://";
    public static final String XINDICELOCALURI = "xindice-embed://";
    public static final String XMLDBURI = "xmldb:";
    private static final String NS = "xmlns:";
    
    // Version of the XML:DB API that we are using
    public static final String XMLDBAPIVERSION = "1.0";

    // Class name of the Standard Xindice Indexer
    public static final String XINDICE_VAL_INDEXER =
        "org.apache.xindice.core.indexer.ValueIndexer";
    public static final String XINDICE_NAME_INDEXER =
        "org.apache.xindice.core.indexer.NameIndexer";

    public static final int XINDICE_DOCTYPE_STRING = 0;
    public static final int XINDICE_DOCTYPE_DOM = 1;
    public static final int XINDICE_DOCTYPE_SAX = 2;

    static Log logger = LogFactory.getLog(XindiceDriver.class.getName());
    static PerformanceLog performanceLogger =
        new PerformanceLog(XindiceDriver.class.getName() + ".performance");

    private boolean isLocal = true;
    private boolean isDebug = logger.isDebugEnabled();
    private boolean isVerbose = false;
    private boolean isProfiling = false;
    private String pageSize = "";
    private String maxkeySize = "";
    private Database database = null;
    private static Object dbLock = new Object();

    public static void main(String[] args) 
    {
        boolean local = false;
        boolean purgeDB = false;
        boolean verbose = false;
        boolean profile = false;
        boolean scaletest = false;

        for (int i = 0; i < args.length; i++) {
            String argstr = (String) args[i];
            if ((argstr != null)
                && ("local".compareToIgnoreCase(argstr) == 0)) {
                local = true;
            } else if (
                (argstr != null)
                    && ("purge".compareToIgnoreCase(argstr) == 0)) {
                purgeDB = true;
            } else if (
                (argstr != null)
                    && ("verbose".compareToIgnoreCase(argstr) == 0)) {
                verbose = true;
            } else if (
                (argstr != null)
                    && ("scaletest".compareToIgnoreCase(argstr) == 0)) {
                scaletest = true;
            }            
        }

        try 
        {
            if (purgeDB) {
                XindiceDriver db = new XindiceDriver(local);
                db.setVerbose(verbose);
                db.setProfiling(profile);                
                db.dropAllCollections("/db");
                if (logger.isDebugEnabled()) {
                    logger.debug("Purge complete.");
                }
            }
            if (scaletest ){
                for (int i=0;i<10;i++) {
                    XindiceDriver db = new XindiceDriver(local);
                    db.setVerbose(verbose);
                    db.setProfiling(profile);                     
                    db.scalabilityTest();
                }
            } else {
                XindiceDriver db = new XindiceDriver(local);
                db.setVerbose(verbose);
                db.setProfiling(profile);                 
                db.selfTest();
            }            
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Success!");
        }        
    }
    
    /** Creates a new instance of XindiceDriver */
    public XindiceDriver() throws Exception 
    {
        this.init();
    }
    
    /** Creates a new instance of XindiceDriver */
    public XindiceDriver(boolean isLocal) throws Exception 
    {
        this.isLocal = isLocal;
        this.init();
    }

    /** 
     This method is invoked by the default constructor of a new instance of 
     this class or may be used to explicity reactivate an instance of this 
     class that has had the #shutdown method invoked on it. Note that any 
     method calls to this class will fail if the Xindice database is not 
     registered.
     */    
    public void init()
    {
        if (this.database == null) 
        {
            try
            {
                String driver = "org.apache.xindice.client.xmldb.DatabaseImpl";
                Class c = Class.forName(driver);

                this.database = (Database) c.newInstance();
                synchronized (XindiceDriver.dbLock) {
                    DatabaseManager.registerDatabase(database);
                }
            }
            catch (Exception e) 
            {
                logger.error("XINDICE Exception: " + e.getMessage(), e);
            }             
        }
    }
    
    /** 
     * Use this method to explicitly checkpoint the database and free open 
     * memory and file resources held by Xindice.
     * Note that any other method calls to this class (with the exception of #init) 
     * after this method is invoked will likely throw an Exception.
     * @param collection - name of the parent collection.
     */
    public void shutdown(String collection) throws Exception
    {
        Collection col = null;
        try {
            String colURI = normalizeCollectionURI(collection, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }            

            DatabaseInstanceManager man = (DatabaseInstanceManager) 
                col.getService("DatabaseInstanceManager", XMLDBAPIVERSION);

            col.close();
            col = null;            
            man.shutdown();
            
            if (this.database != null) {
                synchronized (XindiceDriver.dbLock) {
                    DatabaseManager.deregisterDatabase(this.database);
                }
                this.database = null;
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }       
    }    
    public synchronized void checkpointCollection(String collection) 
        throws Exception
    {
        Collection col = null;
        try {
            String colURI = normalizeCollectionURI(collection, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }            

            DatabaseInstanceManager man = (DatabaseInstanceManager) 
                col.getService("DatabaseInstanceManager", XMLDBAPIVERSION);
         
            man.shutdown();
            
        } finally {
            if (col != null) {
                col.close();
            }
        }       
    }
    
    public void checkInitialized() 
        throws Exception
    {
        if (this.database == null) 
        {
            String err = "XINDICE ERROR: Database not initialized!";
            if (logger.isDebugEnabled()) { 
                logger.debug(err);
            }                
            throw new Exception(err);
        }
    }   
    public void setVerbose(boolean isVerbose) 
    {
        this.isVerbose = isVerbose;
    }

    public void setProfiling(boolean isProfiling) 
    {
        this.isProfiling = isProfiling;
    }

    public String createUniqueID(String parentCol) throws Exception 
    {
        checkInitialized();
        
        String uniqueId = null;

        String colURI = normalizeCollectionURI(parentCol, this.isLocal);
        Collection parentCollection = DatabaseManager.getCollection(colURI);
        if (parentCollection == null) {
            String err = "XINDICE ERROR: Collection not found! " + colURI;
            if (logger.isDebugEnabled()) { 
                logger.debug(err);
            }                
            throw new Exception(err);
        }

        try {
            uniqueId = parentCollection.createId();
        } finally {
            if (parentCollection != null) {
                parentCollection.close();
            }
        }        
        return uniqueId;
    }

    /**
     * Creates a new Xindice collection.
     * @param parentCol - name of the parent collection
     * @param colName - name of the new collection
     * @exception Exception Description of Exception
     */
    public void createCollection(String parentCol, String colName)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        try {
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Parent collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }
            
            Collection child = col.getChildCollection(colName);
            if (child != null) {
                if (logger.isDebugEnabled()) { 
                    logger.debug("Xindice Collection already exists: " + colName);
                }                
                return;
            }
            
            CollectionManager service =
                (CollectionManager) col.getService("CollectionManager",
                                                    XMLDBAPIVERSION);

            // Build up the Collection XML configuration.
            Document doc = new DocumentImpl();
            Element colEle = doc.createElement("collection");
            colEle.setAttribute("compressed", "true");
            colEle.setAttribute("name", colName);
            doc.appendChild(colEle);
            Element filEle = doc.createElement("filer");
            //filEle.setAttribute("gzip", "true");
            filEle.setAttribute("class",
                                "org.apache.xindice.core.filer.BTreeFiler");
            if (this.pageSize.length() != 0) {
                filEle.setAttribute("pagesize", this.pageSize);
            }
            if (this.maxkeySize.length() != 0) {
                filEle.setAttribute("maxkeysize", maxkeySize);
            }
            colEle.appendChild(filEle);

            Collection newCol = service.createCollection(colName, doc);
            if (newCol != null) {
                newCol.close();
            }
            if (logger.isDebugEnabled()) { 
                logger.debug("Xindice Collection created: " + colName);
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
    }

    /**
     * Drops a collection from Xindice.
     * @param parentCol - name of the parent collection
     * @param colName - name of the new collection
     * @exception Exception Description of Exception
     */
    public void dropCollection(String parentCol, String colName)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        try {
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            CollectionManager service =
                (CollectionManager) col.getService("CollectionManager",
                                                    XMLDBAPIVERSION);
            
            // Drop the collection
            service.dropCollection(colName);
            if (logger.isDebugEnabled()) { 
                logger.debug("Xindice Collection " + colName + " dropped.");
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
    }

    /**
     * Gets a list of the collections beneath a parent collection.
     * @param parentCol - name of the parent collection.
     * @return String[] - array of child collection names.
     * @exception Exception Description of Exception
     */
    public String[] listChildCollections(String parentCol) 
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        String[] resultArray = null;
        try {
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            resultArray = col.listChildCollections();

            if (this.isDebug) {
                String colstr =
                    Long.toString(resultArray.length)
                        + " Collections in "
                        + colURI
                        + ": [ ";
                for (int i = 0; i < resultArray.length; i++) {
                    colstr += ((i == 0) ? "" : ", ") + resultArray[i];
                }
                colstr += " ]";
                if (logger.isDebugEnabled()) { 
                    logger.debug(colstr);
                }
            }
        } finally {
            // Release the collection object
            if (col != null) {
                col.close();
            }
        }
        return resultArray;
    }

    /**
     * Gets a list of the documents in a collection.
     * @param collection - name of the collection
     * @return String[] - array of document URI's
     * @exception Exception Description of Exception
     */
    public String[] listCollectionDocuments(String collection)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        String[] resultArray = null;
        try {
            String colURI = normalizeCollectionURI(collection, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            resultArray = col.listResources();

            if (this.isDebug) {
                String colstr =
                    Long.toString(resultArray.length)
                        + " Documents in collection "
                        + colURI
                        + ": [ ";
                for (int i = 0; i < resultArray.length; i++) {
                    colstr += ((i == 0) ? "" : ", ") + resultArray[i];
                }
                colstr += " ]";
                if (logger.isDebugEnabled()) { 
                    logger.debug(colstr);
                }
            }
        } finally {
            // Release the collection object
            if (col != null) {
                col.close();
            }
        }
        return resultArray;
    }

    public boolean findCollection(String parentCol, String collectionName)
        throws Exception 
    {
        checkInitialized();
        
        boolean found = false;
        String[] collections = this.listChildCollections(parentCol);

        if (this.isProfiling) {
            this.performanceLogger.start();
        }

        for (int i = 0; i < collections.length; i++) {
            if (collectionName.compareToIgnoreCase(collections[i]) == 0) {
                found = true;
                break;
            }
        }
        if (this.isProfiling) {
            this.performanceLogger.stop("FindCollection");
        }

        return found;
    }    
    
    public boolean findDocument(String parentCol, String documentName) 
        throws Exception
    {
        checkInitialized();
        
        boolean found = false;
        
        if (this.isProfiling) {
            this.performanceLogger.start();
        }

        String[] documents = this.listCollectionDocuments(parentCol);
        
        for (int i = 0; i<documents.length;i++)
        {
            if (documentName.compareToIgnoreCase(documents[i])==0) {
                found = true;
                break;
            }
        }        
        if (this.isProfiling) {        
            this.performanceLogger.stop("FindDocument");
        }
        
        return found;
    }
    
    public void dropAllCollections(String parentCol) 
        throws Exception 
    {
        checkInitialized();
        
        String[] collections = this.listChildCollections(parentCol);
        for (int i = 0; i < collections.length; i++) {
            if ((collections[i] != null)
                && (collections[i].length() != 0)
                && (collections[i].compareToIgnoreCase("system") != 0))
                this.dropCollection(parentCol, collections[i]);
        }
    }

    /**
    * Gets a list of document indexers from a collection.
    * @param parentCol - name of the collection
    * @return String[] - array of document indexers.
    * @exception Exception - if the collection does not exist.
    */
    public String[] listIndexers(String parentCol) 
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        String[] resultArray = null;
        try {
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            // Create a collection manager instance for the parent of the collection
            CollectionManager colman =
                (CollectionManager) col.getService("CollectionManager",
                                                    XMLDBAPIVERSION);
            resultArray = colman.listIndexers();

            if (this.isDebug) {
                String colstr =
                    Long.toString(resultArray.length)
                        + " Indices on "
                        + colURI
                        + ": [ ";
                for (int i = 0; i < resultArray.length; i++) {
                    colstr += ((i == 0) ? "" : ", ") + resultArray[i];
                }
                colstr += " ]";
                if (logger.isDebugEnabled()) { 
                    logger.debug(colstr);
                }
            }
        } finally {
            // Release the collection object
            if (col != null) {
                col.close();
            }
        }
        return resultArray;
    }

    /**
     * Adds a document file to a collection.
     * @param parentCol - name of the parent collection
     * @param fileName - name of the file
     * @param docName - name of the document
     * @return String - the document ID.
     * @exception Exception - if the collection does not exist.
     */
    public String addDocumentFile(String parentCol,
                                  String fileName,
                                  String docName)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        String docID = null;
	InputStream fis = null;
	
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            // Create a collection instance
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            // Parse in XML using Xerces
            File file = new File(fileName);
            fis = new FileInputStream(file);

            SAXParserFactory spf =
                javax.xml.parsers.SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);

            XMLReader saxReader = spf.newSAXParser().getXMLReader();
            StringSerializer ser = new StringSerializer(null);

            saxReader.setContentHandler(ser);
            saxReader.setProperty("http://xml.org/sax/properties/lexical-handler",
                                  ser);       
            saxReader.parse(new InputSource(fis));

            // Create the XMLResource and store the document
            XMLResource resource =
                (XMLResource) col.createResource(docName, "XMLResource");
            resource.setContent(ser.toString());
            col.storeResource(resource);
            docID = resource.getId();

            if (logger.isDebugEnabled()) { 
                logger.debug("STORED Document: " + colURI + "/" + docID);
            }
            resource = null;
        } finally {
	    if (fis != null) {
		try {
		    fis.close();
		} catch (IOException e) {
		    logger.debug("Failed to close: " + e.getMessage(), e);
		}
	    }
            if (col != null) {
                col.close();
            }
            col = null;
            if (this.isProfiling) {
                this.performanceLogger.stop("addDocumentFile");
            }
        }
        return docID;
    }

    /**
     * Adds a document file to a collection.
     * @param parentCol - name of the parent collection
     * @param doc - the document to add
     * @param docName - name of the document
     * @return String - the document ID.
     * @exception Exception - if the collection does not exist.
     */
    public String addDocumentDOM(String parentCol,
                                 Node node,
                                 String docName)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        String docID = null;
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }
            // Create a collection instance
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            // Create the XMLResource and store the document
            XMLResource resource =
                (XMLResource) col.createResource(docName, "XMLResource");
            resource.setContentAsDOM(node);
            col.storeResource(resource);
            docID = resource.getId();

            if (logger.isDebugEnabled()) { 
                logger.debug("STORED Document: " + colURI + "/" + docID);
            }
            resource = null;
        } finally {
            if (col != null) {
                col.close();
            }
            col = null;

            if (this.isProfiling) {
                this.performanceLogger.stop("addDocumentDOM");
            }
        }

        return docID;
    }

    /**
     * Adds a Document to a collection, where the input Document is in String form.
     * @param parentCol - name of the parent collection
     * @param docstr - String representation of the Document to add
     * @param docName - name of the document
     * @return String - the document ID.
     */
    public String addDocumentString(String parentCol,
                                    String docstr,
                                    String docName)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        String docID = null;
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            // Create a collection instance
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            // Parse in XML using Xerces
            StringReader reader = new StringReader(docstr);

            SAXParserFactory spf =
                javax.xml.parsers.SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);

            XMLReader saxReader = spf.newSAXParser().getXMLReader();
            StringSerializer ser = new StringSerializer(null);

            saxReader.setContentHandler(ser);
            saxReader.setProperty("http://xml.org/sax/properties/lexical-handler",
                                   ser);
            saxReader.parse(new InputSource(reader));
            reader.close();

            // Create the XMLResource and store the document
            XMLResource resource =
                (XMLResource) col.createResource(docName, "XMLResource");
            resource.setContent(ser.toString());
            col.storeResource(resource);
            docID = resource.getId();

            if (logger.isDebugEnabled()) { 
                logger.debug("STORED Document: " + colURI + "/" + docID);
            }
            resource = null;
        } finally {
            if (col != null) {
                col.close();
            }
            col = null;

            if (this.isProfiling) {
                this.performanceLogger.stop("addDocumentString");
            }
        }
        return docID;
    }

    /**
     * Deletes a document from a collection.
     * @param parentCol - name of the parent collection
     * @param docID - id of the document to delete
     * @exception Exception -
     */
    public void deleteDocument(String parentCol, String docID)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }
            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if ( col == null ) {
                String err = "XINDICE ERROR: Parent Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }
            
            Resource colresource = col.getResource(docID);
            if (colresource == null) {
                String err = "DELETE Document FAILURE - document resource not found: " + docID;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);                
            }
            
            col.removeResource(colresource);
            if (this.isDebug) {
                if (logger.isDebugEnabled()) { 
                    logger.debug("DELETED Document: " + colURI + "/" + docID);
                }
            }
        } finally {
            // Release the collection object
            if (col != null) {
                col.close();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("deleteDocument");
            }
        }
    }

    /**
     * Gets a Document from a collection, reutrns the result in String representation.
     * @param parentCol - name of the parent collection
     * @param docID - id of the document
     * @return String - the document represented as a String.
     * @exception Exception - if docId is null, collection not found, or document not found
     */
    public String getDocumentAsString(String parentCol, String docID)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        String docstr = null;
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            XMLResource resource = (XMLResource) col.getResource(docID);
            if (resource == null) {
                String err = "XINDICE ERROR : Document not found!";
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            docstr = (String) resource.getContent();
            if (logger.isDebugEnabled()) { 
                logger.debug("LOADED Document: " + colURI + "/" + docID);
            }

            if ((this.isDebug) && (this.isVerbose)) {
                if (logger.isDebugEnabled()) { 
                    logger.debug(docstr);
                }
            }
        } finally {
            // Release the collection object
            if (col != null) {
                col.close();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("getDocumentAsString");
            }
        }

        return docstr;
    }

    /**
     * Gets a Document from a collection, reutrns the result in Node representation.
     * @param parentCol - name of the parent collection
     * @param docID - id of the document
     * @return Node - the document represented as a Node
     * @exception Exception - if docId is null, collection not found, or document not found
     */
    public Node getDocumentAsDOM(String parentCol, String docID)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        Node node = null;
        if (docID == null) {
            String err = "XINDICE ERROR : Document ID cannot be null";
            if (logger.isDebugEnabled()) { 
                logger.debug(err);
            }                
            throw new Exception(err);
        }
        
        try 
        {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            XMLResource resource = (XMLResource) col.getResource(docID);
            if (resource == null) {
                String err =
                    "XINDICE ERROR : Document "
                        + colURI
                        + "/"
                        + docID
                        + " not found! ";
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            node = (Document) resource.getContentAsDOM();
            if (logger.isDebugEnabled()) { 
                logger.debug("LOADED Document: " + colURI + "/" + docID);
            }

            if ((this.isDebug) && (this.isVerbose)) {
                if (logger.isDebugEnabled()) { 
                    logger.debug(TextWriter.toString(node));
                }
            }
        }
        finally 
        {
            // Release the collection object
            if (col != null) {
                col.close();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("getDocumentAsDom");
            }
        }
        return node;
    }

    public void addIndexer(String parentCol,
                           String name,
                           String type,
                           String pattern)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            // Create a collection manager instance for the collection
            CollectionManager colman =
                (CollectionManager) col.getService("CollectionManager",
                                                    XMLDBAPIVERSION);

            Document doc = new DocumentImpl();

            // Create the index element to hold attributes
            Element idxEle = doc.createElement("index");
            idxEle.setAttribute("class", XINDICE_VAL_INDEXER);
            idxEle.setAttribute("name", name);
            idxEle.setAttribute("pattern", pattern);

            // Setup optional index attributes
            if ((type != null) && (type.length() != 0)) {
                if (type.equalsIgnoreCase("name"))
                    idxEle.setAttribute("class", XINDICE_NAME_INDEXER);
                else
                    idxEle.setAttribute("type", type);
            }

            if (this.pageSize.length() != 0) {
                idxEle.setAttribute("pagesize", this.pageSize);
            }
            if (this.maxkeySize.length() != 0) {
                idxEle.setAttribute("maxkeysize", maxkeySize);
            }

            // Add Element to the document
            doc.appendChild(idxEle);
            if ((this.isDebug) && (this.isVerbose)) {
                String indexstr = TextWriter.toString(doc);
                if (logger.isDebugEnabled()) { 
                    logger.debug("Index node element added: ");
                }
                if (logger.isDebugEnabled()) { 
                    logger.debug(indexstr);
                }
            }

            // Create the indexer for this collection manager
            colman.createIndexer(doc);
            if (logger.isDebugEnabled()) { 
                logger.debug("Created Index : " + name);
            }
        } finally {
            if (col != null) {
                col.close();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("addIndexer");
            }
        }
    }

    public void dropIndexer(String parentCol, String indexName)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            String colURI = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colURI);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colURI;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            // Create a collection manager instance for the collection
            CollectionManager colman =
                (CollectionManager) col.getService("CollectionManager",
                                                    XMLDBAPIVERSION);
            colman.dropIndexer(indexName);
            if (logger.isDebugEnabled()) { 
                logger.debug("Dropped Index: " + indexName);
            }
        } finally {
            if (col != null) {
                col.close();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("dropIndexer");
            }
        }
    }

    public Object[] XPathQuery(String parentCol,
                               String query,
                               String[] namespaces,
                               int returnType)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        ArrayList results = new ArrayList();
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            if (parentCol.length() == 0) {
                String err = "XINDICE ERROR : Collection name required";
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }
                throw new Exception(err);                
            }
            if (query.length() == 0) {
                String err = "XINDICE ERROR : Query string required";
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }
                throw new Exception(err);
            }

            String colstring = normalizeCollectionURI(parentCol, this.isLocal);
            XPathQueryService service = null;
            ResourceIterator resultIter = null;

            col = DatabaseManager.getCollection(colstring);

            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colstring;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            service =
                (XPathQueryService) col.getService("XPathQueryService", "1.0");
            
            addNamespaces(service, namespaces);
            ResourceSet resultSet = service.query(query);
            resultIter = resultSet.getIterator();

            while (resultIter.hasMoreResources())
            {
                XMLResource resource = (XMLResource) resultIter.nextResource();
                switch (returnType)
                {
                    case XINDICE_DOCTYPE_STRING :
                        {
                            String documentstr = (String) resource.getContent();
                            results.add(documentstr);
                            if ((this.isDebug) && (this.isVerbose)) {
                                if (logger.isDebugEnabled()) { 
                                    logger.debug(documentstr);
                                }
                            }
                            break;
                        }
                    case XINDICE_DOCTYPE_DOM :
                        {
                            Node node = (Document) resource.getContentAsDOM();
                            results.add(node);
                            if ((this.isDebug) && (this.isVerbose)) {
                                if (logger.isDebugEnabled()) { 
                                    logger.debug(TextWriter.toString(node));
                                }
                            }
                            break;
                        }
                    case XINDICE_DOCTYPE_SAX :
                        {
                            throw new Exception("XINDICE: Unsupported document return type");
                        }
                    default :
                        throw new Exception("XINDICE: Unsupported document return type");
                }
            }
            switch (returnType) 
            {
                case XINDICE_DOCTYPE_STRING :
                    {
                        String[] strArray = new String[results.size()];
                        return (String[]) results.toArray(strArray);
                    }
                case XINDICE_DOCTYPE_DOM :
                    {
                        Node[] docArray = new Document[results.size()];
                        return (Node[]) results.toArray(docArray);
                    }
                case XINDICE_DOCTYPE_SAX :
                    {
                        break;
                    }
                default :
                    break;
            }
        } finally {
            if (col != null) {
                col.close();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("XPathQuery");
            }
        }
        return null;
    }

    public void XUpdate(String parentCol, String xupdate, String[] namespaces)
        throws Exception 
    {
        checkInitialized();
        
        Collection col = null;
        try {
            if (this.isProfiling) {
                this.performanceLogger.start();
            }

            String colstring = normalizeCollectionURI(parentCol, this.isLocal);
            col = DatabaseManager.getCollection(colstring);
            if (col == null) {
                String err = "XINDICE ERROR: Collection not found! " + colstring;
                if (logger.isDebugEnabled()) { 
                    logger.debug(err);
                }                
                throw new Exception(err);
            }

            XUpdateQueryService service =
                (XUpdateQueryService) col.getService(
                    "XUpdateQueryService",
                    "1.0");
            addNamespaces(service, namespaces);

            long count = service.update(xupdate);
            if (logger.isDebugEnabled()) { 
                logger.debug("XUpdate: " + count + " entries updated.");
            }
        } finally {
            if (col != null) {
                col.close();
            }
            if (this.isProfiling) {
                this.performanceLogger.stop("XUpdate");
            }
        }
    }

    public void selfTest() throws Exception 
    {
        final String dbName = "/db";
        final String collectionName = "selfTest";
        final String collectionURI = dbName + "/" + collectionName;
        final String testElementName = collectionName + "-Element";
        final String testAttribName = collectionName + "-Attribute";
        final String testDocumentName = collectionName + "-Document";
        final String testIndexName = collectionName + "-Index";

        if (logger.isDebugEnabled()) { 
            logger.debug("Running Self-Test");
        }

        if (logger.isDebugEnabled()) { 
            logger.debug("Creating new collection: " + collectionURI);
        }
        this.createCollection(dbName, collectionName);
        if (logger.isDebugEnabled()) { 
            logger.debug("Listing collections...");
        }
        this.listChildCollections(dbName);

        // Build up a simple test XML doc and add it to the DB.
        Document doc = new DocumentImpl();
        Element testElement = doc.createElement(testElementName);
        testElement.setAttribute(testAttribName, "false");
        doc.appendChild(testElement);

        // add the document
        if (logger.isDebugEnabled()) { 
            logger.debug(
                "Adding document " + testDocumentName + " to " + collectionURI);
        }
        this.addDocumentDOM(collectionURI, doc, testDocumentName);

        // list the collection documents
        if (logger.isDebugEnabled()) { 
            logger.debug("Listing collection documents for " + collectionURI);
        }
        this.listCollectionDocuments(collectionURI);

        // add a simple name indexer, giving it some time to build
        if (logger.isDebugEnabled()) { 
            logger.debug("Adding Indexer " + 
                         testIndexName + 
                         " to " + 
                         collectionURI);
        }
        
        this.addIndexer(
            collectionURI,
            testIndexName,
            XINDICE_NAME_INDEXER,
            testElementName);
        synchronized (this) {
            this.wait(3000);
        }

        // list the indexers
        if (logger.isDebugEnabled()) { 
            logger.debug("Listing Indexers for " + collectionURI);
        }
        this.listIndexers(collectionURI);

        // issue an XPATH query

        // issue an XUpdate

        // drop the indexer
        if (logger.isDebugEnabled()) { 
            logger.debug("Dropping Indexer " + 
                         testIndexName + 
                         " from " + 
                         collectionURI);
        }
        this.dropIndexer(collectionURI, testIndexName);

        // remove the document
        if (logger.isDebugEnabled()) { 
            logger.debug("Deleting Document " +
                         testDocumentName + 
                         " from " + 
                         collectionURI);
        }
        this.deleteDocument(collectionURI, testDocumentName);

        // drop the collection
        if (logger.isDebugEnabled()) { 
            logger.debug("Dropping Collection " + 
                         collectionName + 
                         " from " 
                         + dbName);
        }
        this.dropCollection(dbName, collectionName);
        
        this.shutdown(dbName);
    }

    public void scalabilityTest() throws Exception 
    {
        final int MAX_TEST_COLLECTIONS = 10;
        final String dbName = "/db";
        final String baseCollectionName = "scalabilityTest-" + 
                                          Integer.toString(this.hashCode())
                                          + "-";
        final String testElementName = baseCollectionName + "Element";
        final String testAttribName = baseCollectionName + "Attribute";
        final String testDocumentName = baseCollectionName + "Document";
        final String testIndexName = baseCollectionName + "Index";
        
        String collectionName = baseCollectionName;
        String collectionURI = "";   
        
        if (logger.isDebugEnabled()) { 
            logger.debug("Running Scalability-Test");
        }

        for (int i=0; i<MAX_TEST_COLLECTIONS;i++) 
        {
            collectionName = baseCollectionName + Integer.toString(i);
            collectionURI = dbName + "/" + collectionName;
            if (!this.findCollection(dbName, collectionName)) {
                if (logger.isDebugEnabled()) { 
                    logger.debug("Creating new collection: " + collectionURI);
                }
                
                this.createCollection(dbName, collectionName);            
            }
            // Build up a simple test XML doc and add it to the DB.
            Document doc = new DocumentImpl();
            Element testElement = doc.createElement(testElementName);
            testElement.setAttribute(testAttribName, "false");
            doc.appendChild(testElement);

            // add the document
            if (logger.isDebugEnabled()) {
                logger.debug("Adding document " + 
                             testDocumentName + 
                             " to " + 
                             collectionURI);
            }
            this.addDocumentDOM(collectionURI, doc, testDocumentName);

            // list the collection documents
            if (logger.isDebugEnabled()) { 
                logger.debug("Listing collection documents for " + collectionURI);
                this.listCollectionDocuments(collectionURI);                
            }        
        }
        
        if (logger.isDebugEnabled()) { 
            logger.debug("Listing collections...");
            this.listChildCollections(dbName);            
        }
        
        for (int i=0; i<MAX_TEST_COLLECTIONS;i++) 
        {
            collectionName = baseCollectionName + Integer.toString(i);
            collectionURI = dbName + "/" + collectionName;            
            // list the collection documents
            if (logger.isDebugEnabled()) {
                logger.debug("Listing collection documents for " + 
                             collectionURI);
                this.listCollectionDocuments(collectionURI);                
            }

            // remove the document
            if (logger.isDebugEnabled()) { 
                logger.debug("Deleting Document " + 
                             testDocumentName + 
                             " from " 
                             + collectionURI);
            }
            this.deleteDocument(collectionURI, testDocumentName);

            // list the collection documents
           if (logger.isDebugEnabled()) { 
               logger.debug("Listing collection documents for " + collectionURI);
               this.listCollectionDocuments(collectionURI);               
           }
            
            // drop the collection
            /*if (logger.isDebugEnabled()) {
                logger.debug("Dropping Collection " + 
                             collectionName + 
                             " from " + 
                             dbName);
            }
            this.dropCollection(dbName, collectionName);*/

        }
    
        if (logger.isDebugEnabled()) { 
            logger.debug("Listing collections...");
            this.listChildCollections(dbName);            
        }
        this.shutdown(collectionURI);  
    }

    //  return normalized CollectionURI for creation of a Collection object
    protected String normalizeCollectionURI(String uri, boolean local) {
        // Check to see if this uri starts with "xmldb:" , if so treat as absolute
        if (uri.startsWith("xmldb:")) {
            // URI is absolute, leave alone
            return uri;
        } else if (
            uri.startsWith("xindice:") || uri.startsWith("xindice-embed:")) {
            return (XMLDBURI + uri);
        } else {
            if (local) {
                return (XMLDBURI + XINDICELOCALURI + uri);
            } else {
                // URI passed in is not absoulte, build the full URI
                return (XMLDBURI + XINDICEURI + uri);
            }
        }
    }

    private String[] convertNamespaceString(String namespacesString)
        throws Exception 
    {
        ArrayList results = new ArrayList();
        if ((namespacesString != "") && (namespacesString != null)) 
        {
            StringTokenizer st = new StringTokenizer(namespacesString, ";");
            while (st.hasMoreTokens()) 
            {
                results.add(st.nextToken());               
            }
        }
        return (String[])results.toArray(new String[]{});
    }
    
    private void addNamespaces(Object service,
                               String[] namespaces)
        throws Exception 
    {
        if (namespaces == null) {
            return;
        }
        
        String prefix;
        String namespace;      
            
        for (int i=0;i<namespaces.length;i++) 
        {
            StringTokenizer st = new StringTokenizer(namespaces[i], "=");
            if (st.countTokens() % 2 != 0) {
                logger.warn(
                    "XINDICE ERROR : malformed namespace encountered: " + 
                        namespaces[i]);
                continue;
            }
                    
            while (st.hasMoreTokens()) 
            {
                prefix = st.nextToken();
                namespace = st.nextToken();
                
                if (prefix.startsWith(NS)) {
                    prefix = prefix.substring(NS.length());                
                }

                if (service instanceof XPathQueryService) {
                    ((XPathQueryService)service).setNamespace(prefix,
                                                              namespace);                    
                }
                else if (service instanceof XUpdateQueryService) {                
                    ((XUpdateQueryServiceImpl)service).setNamespace(prefix,
                                                                    namespace);
                } 
            }
        }
    }    
}
