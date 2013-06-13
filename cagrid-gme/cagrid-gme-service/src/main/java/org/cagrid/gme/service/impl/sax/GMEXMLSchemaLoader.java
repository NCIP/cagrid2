package org.cagrid.gme.service.impl.sax;

import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.impl.dao.XMLSchemaInformationDao;

import java.util.List;


/**
 * @author oster
 */
public class GMEXMLSchemaLoader extends XMLSchemaLoader {

    public GMEXMLSchemaLoader(List<XMLSchema> submissionSchemas, XMLSchemaInformationDao dao) {
        setEntityResolver(new GMEEntityResolver(submissionSchemas, dao));
        setErrorHandler(new GMEErrorHandler());
    }


    @Override
    public GMEErrorHandler getErrorHandler() {
        return (GMEErrorHandler) super.getErrorHandler();
    }
}
