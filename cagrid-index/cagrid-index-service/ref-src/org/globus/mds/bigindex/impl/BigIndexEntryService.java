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

import javax.xml.namespace.QName;

/** Service to provide access to index entries. All functions handled via
    operation providers.
 */

public class BigIndexEntryService {
    
    public static final QName ENTRY_KEY = 
            new QName(BigIndexService.BIGINDEX_NS, "ServiceGroupEntryKey");
   
    public static final QName RP_SET = 
            new QName(BigIndexService.BIGINDEX_NS, "BigIndexEntryResourceProperties");    
    
}

