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
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.globus.wsrf.Constants;
import org.globus.wsrf.WSRFConstants;
import org.globus.wsrf.ResourceProperties;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.NoSuchResourceException;
import org.globus.wsrf.query.QueryEngine;
import org.globus.wsrf.query.UnsupportedQueryDialectException;
import org.globus.wsrf.query.QueryException;
import org.globus.wsrf.query.QueryEvaluationException;
import org.globus.wsrf.query.InvalidQueryExpressionException;
import org.globus.wsrf.utils.AnyHelper;
import org.globus.wsrf.utils.FaultHelper;
import org.globus.wsrf.utils.Resources;
import org.globus.wsrf.impl.servicegroup.ServiceGroupConstants;
import org.globus.wsrf.impl.properties.QueryResourcePropertiesProvider;
import org.globus.util.I18n;

import org.oasis.wsrf.properties.InvalidQueryExpressionFaultType;
import org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType;
import org.oasis.wsrf.properties.QueryEvaluationErrorFaultType;
import org.oasis.wsrf.properties.QueryExpressionType;
import org.oasis.wsrf.properties.ResourceUnknownFaultType;
import org.oasis.wsrf.properties.UnknownQueryExpressionDialectFaultType;
import org.oasis.wsrf.properties.QueryResourceProperties_Element;
import org.oasis.wsrf.properties.QueryResourcePropertiesResponse;

import org.globus.mds.bigindex.impl.database.xml.xindice.XindiceIndexDatabase;

/**
 * QueryResourceProperties operation implementation.  Directly queries the 
 * database using XPath when the Entry resource property is queried, otherwise 
 * falls back to the existing implementation for all other resource properties.  
 * Since not all Entry resource property data is necessarily stored in-memory, 
 * what can be returned from this method may be different than what is returned 
 * from GetResourceProperty directly on the Entry resource property.
 */
public class QueryRPProvider extends QueryResourcePropertiesProvider {

    static Log logger =
        LogFactory.getLog(QueryRPProvider.class.getName());

    private static I18n i18n = I18n.getI18n(Resources.class.getName());

    public QueryResourcePropertiesResponse queryResourceProperties(QueryResourceProperties_Element request)
        throws RemoteException,
               InvalidResourcePropertyQNameFaultType,
               ResourceUnknownFaultType,
               InvalidQueryExpressionFaultType,
               QueryEvaluationErrorFaultType,
               UnknownQueryExpressionDialectFaultType {

        if (request == null) {
            throw new RemoteException(
                i18n.getMessage("nullArgument", "request"));
        }

        BigIndexResource resource = null;
        try {
            resource = 
            (BigIndexResource)
                ResourceContext.getResourceContext().getResource();
        } catch (NoSuchResourceException e) {
            ResourceUnknownFaultType fault = 
                new ResourceUnknownFaultType();
            FaultHelper faultHelper = new FaultHelper(fault);
            faultHelper.addFaultCause(e);
            throw fault;
        } catch (Exception e) {
            throw new RemoteException(
                i18n.getMessage("resourceDisoveryFailed"), e);
        }
        
        QueryExpressionType query = request.getQueryExpression();        
        String queryStr;        
        try {
            queryStr = this.getQueryString(query); 
        } catch (Exception e) {
            throw new RemoteException("Query string parsing error", e);
        }
        
        if (this.isDatabaseQuery(queryStr)) 
        {
            // get db instance
            XindiceIndexDatabase db = resource.getDatabase();
            if (db == null) {
                throw new RemoteException("Error: database instance is null");
            }
            
            List results = null;            
            try {
                results = db.query(queryStr, null);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException("Query error", e);
            }
            
            QueryResourcePropertiesResponse response =
                new QueryResourcePropertiesResponse();        
            AnyHelper.setAny(response, results);   
            
            return response;                                
        }
        else 
        {
            return super.queryResourceProperties(request);
        }                            
        

    }
    
    private String getQueryString(QueryExpressionType expression) 
        throws Exception
    {
        if (expression == null) {
            throw new QueryException(i18n.getMessage("noQuery"));
        }
        if (expression.getDialect() == null) {
            throw new QueryException(
                i18n.getMessage("nullArgument", "expression.dialect"));
        }
        String dialect = expression.getDialect().toString();
        if (!(dialect.equals(WSRFConstants.XPATH_1_DIALECT))) {
            throw new UnsupportedQueryDialectException(
                i18n.getMessage("invalidQueryExpressionDialect"));
        }

        if (expression.getValue() == null ||
            expression.getValue().toString().trim().length() == 0) {
            throw new InvalidQueryExpressionException(
                i18n.getMessage("noQueryString"));
        }

        String query = expression.getValue().toString().trim();

        if (logger.isDebugEnabled()) {
            logger.debug("Query: " + query);    
        }
        
        return query;
    }
    
    /*
     * This pattern matching is imperfect as it will fail with any potential
     * wildcard queries against child elements that may exist in the database,
     * but where the parent or ancestor element names(s) of that child are not 
     * found in the query string.
     *
     * This limitation exists for backward compatibilty reasons. Moving forward 
     * we need to implement a separate query dialect in order to automatically 
     * dereference and search Content from the database.  
     * 
     * For now, such wildcarded queries are supported via the service-specific 
     * query interface implemented in QueryProvider.
     */    
    private boolean isDatabaseQuery(String query) 
    {
        boolean isDatabaseQuery = false;
        
        if (query.length() == 0) {
            return isDatabaseQuery;
        }
        
        /*
         * TODO: find out which is more efficient: default to search root of
         * Entry or start with Content (and specific children)?
         *
         * Starting with Content will cause less database hits, but will force 
         * the complete resource document to be serialized more often for 
         * queries against Entry that don't explicitly include Content or 
         * one of the other Content child elements listed below.
         *
         */
        isDatabaseQuery = 
        ( 
            //(query.indexOf("'Entry'")>-1) || 
            //(query.indexOf(":Entry")>-1) ||
            //(query.indexOf(ServiceGroupConstants.WSSG_NS)>-1) || 
            (query.indexOf("'AggregatorData'")>-1) ||
            (query.indexOf(":AggregatorData")>-1) ||            
            (query.indexOf("'AggregatorConfig'")>-1) ||
            (query.indexOf(":AggregatorConfig")>-1) ||            
            (query.indexOf("'Content'")>-1) ||             
            (query.indexOf(":Content")>-1) 
        );            
        
        return isDatabaseQuery;
    }
}
