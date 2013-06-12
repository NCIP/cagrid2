package org.cagrid.cds.service.impl.testutils;

import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationSigningRequest;
import org.cagrid.cds.model.DelegationSigningResponse;

public class DelegatedCredential {
	private DelegationSigningRequest signingRequest;
	private DelegationSigningResponse signingResponse;

	public DelegatedCredential(DelegationSigningRequest req,
                               DelegationSigningResponse res) {
		this.signingRequest = req;
		this.signingResponse = res;
	}

	public DelegationSigningRequest getSigningRequest() {
		return signingRequest;
	}

	public DelegationSigningResponse getSigningResponse() {
		return signingResponse;
	}
	
	public DelegationIdentifier getDelegationIdentifier(){
		return signingRequest.getDelegationIdentifier();
	}

}
