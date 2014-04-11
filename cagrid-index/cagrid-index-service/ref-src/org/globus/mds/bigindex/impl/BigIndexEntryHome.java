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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.globus.mds.aggregator.impl.AggregatorServiceGroupEntryHome;

/** 
 * Home for BigIndex service entries. Most behavior implemented in 
 * AggregatorServiceGroupEntryHome base class. 
 */

public class BigIndexEntryHome extends AggregatorServiceGroupEntryHome
{
    private static Log logger =
            LogFactory.getLog(BigIndexEntryHome.class.getName());

    public BigIndexEntryHome()
    {
        super();            
    }  
    
    public synchronized void initialize() 
        throws Exception 
    {
        this.setResourceKeyName(BigIndexEntryService.ENTRY_KEY.toString());         
        super.initialize();     
    }    
}

