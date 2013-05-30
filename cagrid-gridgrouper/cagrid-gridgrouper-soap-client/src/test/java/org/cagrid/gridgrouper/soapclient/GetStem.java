package org.cagrid.gridgrouper.soapclient;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.model.StemIdentifier;
import org.cagrid.gridgrouper.wsrf.stubs.GetStemRequest;
import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperRuntimeFaultFaultMessage;
import org.cagrid.gridgrouper.wsrf.stubs.StemNotFoundFaultFaultMessage;

public class GetStem extends GrouperClientBase {

	private GetStem(String url) throws Exception {
		super(url);
	}

    public StemDescriptor getStem(String name) throws StemNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage {
        StemIdentifier id = new StemIdentifier();
        id.setStemName(name);

        GetStemRequest.Stem value = new GetStemRequest.Stem();
        value.setStemIdentifier(id);

        GetStemRequest request = new GetStemRequest();
        request.setStem(value);
        return gridGrouper.getStem(request).getStemDescriptor();
    }

	public static void main(String[] args) {

        try {
		    GetStem me = new GetStem(LOCAL_URL);
            StemDescriptor stem = me.getStem("grouperadministration");
            System.out.println(stem.toString());
        } catch(Exception e) {
            System.out.println(ExceptionUtils.getFullStackTrace(e));
        }

	}
}
