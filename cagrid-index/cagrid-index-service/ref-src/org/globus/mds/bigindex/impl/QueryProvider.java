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

import java.util.List;
import java.rmi.RemoteException;

import org.apache.axis.MessageContext;
import org.apache.axis.utils.XMLUtils;

import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.utils.AnyHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.globus.wsrf.utils.ContextUtils;

import org.globus.mds.bigindex.QueryType;
import org.globus.mds.bigindex.QueryResponse;

import org.globus.mds.bigindex.impl.database.xml.xindice.XindiceIndexDatabase;

/** 
 This OperationProvider provides BigIndex query functionality.
*/
public class QueryProvider 
{
    private static Log logger =
            LogFactory.getLog(QueryProvider.class.getName());

    private static final String servicePath = 
        ContextUtils.getTargetServicePath(MessageContext.getCurrentContext());

    public Object query(QueryType request) 
        throws RemoteException
    {
        if (logger.isDebugEnabled()) {
            logger.info("QueryProvider query called");
        }

        // get the resource
        BigIndexResource resource = null;
        try {
            resource = 
                (BigIndexResource)
                    ResourceContext.getResourceContext().getResource();
        } catch (Exception e) {
            throw new RemoteException(e.toString());
        }
        
        // get db instance
        XindiceIndexDatabase db = resource.getDatabase();
        if (db == null) {
            throw new RemoteException("Error: database instance is null");
        }

        List results = null;
        try {
            results = db.query(request.getQueryString(),
                               request.getNamespaces());
        } catch (Exception e) {
            throw new RemoteException(e.toString());
        }
        
        QueryResponse response = new QueryResponse();
        AnyHelper.setAny(response, results);

        return response;
    }
}

