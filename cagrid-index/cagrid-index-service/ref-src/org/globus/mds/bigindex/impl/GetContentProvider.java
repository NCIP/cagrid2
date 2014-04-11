/*
 * Copyright 1999-2006 University of Chicago
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.globus.mds.bigindex.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.StringReader;
import java.rmi.RemoteException;
import org.xml.sax.InputSource;

import org.apache.axis.MessageContext;
import org.apache.axis.message.MessageElement;

import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.utils.AnyHelper;
import org.globus.wsrf.utils.XmlUtils;
import org.globus.wsrf.encoding.ObjectDeserializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.globus.wsrf.utils.ContextUtils;

import org.oasis.wsrf.servicegroup.EntryType;
import org.globus.mds.bigindex.GetContentResponse;
import org.globus.mds.bigindex.BigIndexContent;
import org.globus.mds.bigindex.BigIndexContentIDList;
import org.globus.mds.bigindex.BigIndexEntry;
import org.globus.mds.bigindex.impl.database.xml.xindice.XindiceIndexDatabase;

/** 
 * This OperationProvider provides GetContent functionality for BigIndex.
*/
public class GetContentProvider
{
    private static Log logger =
            LogFactory.getLog(GetContentProvider.class.getName());

    private static final String servicePath = 
        ContextUtils.getTargetServicePath(MessageContext.getCurrentContext());

    public GetContentResponse getContent(BigIndexContentIDList request) 
        throws RemoteException, org.oasis.wsrf.faults.BaseFaultType
    {
        if (logger.isDebugEnabled()) {
            logger.debug("GetContentProvider GetContent called");
        }

        if ((request == null) ||
            ((request != null) &&
             (request.getContentID() == null)))
        {
             throw new RemoteException("Invalid parameter");
        }

        BigIndexResource resource = null;
        try {
            // get the resource            
            resource = 
                (BigIndexResource)
                    ResourceContext.getResourceContext().getResource();
        } 
        catch (Exception e) {
            throw new RemoteException("Error: could not get resource" + e);    
        }
        
        // get db instance
        XindiceIndexDatabase db = resource.getDatabase();
        if (db == null) {
            throw new RemoteException("Error: database instance is null");
        }
        
        String colURI = db.getDefaultCollectionURI();        
        String[] contentList = request.getContentID();
        ArrayList results = new ArrayList(contentList.length);

        for (int i=0;i<contentList.length;i++) 
        {
            try 
            {
                String doc = 
                    (String)db.getDocument(colURI, contentList[i], true);              
                BigIndexContent content = new BigIndexContent();
                
                BigIndexEntry bigEntry = 
                    (BigIndexEntry)
                        ObjectDeserializer.deserialize(
                            new InputSource(
                                new StringReader(doc)), 
                                    BigIndexEntry.class);
                
                content.setContentID(contentList[i]);
                EntryType entry = bigEntry.getEntry();
                if (entry != null) {
                    content.setContent(entry.getContent());
                }
                results.add(content);
            }
            catch (Exception e)
            {
                if (logger.isDebugEnabled()) {
                    logger.debug(
                        "Exception getting a document from the database: " + e);
                }
                continue;
            }
        }
        
        GetContentResponse response = new GetContentResponse();        
        response.setBigIndexContent(
            (BigIndexContent[])results.toArray(new BigIndexContent[]{}));
            
        return response;
    }
}
