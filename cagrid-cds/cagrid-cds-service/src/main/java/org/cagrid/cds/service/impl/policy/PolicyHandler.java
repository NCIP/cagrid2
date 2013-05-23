package org.cagrid.cds.service.impl.policy;

import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationPolicy;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.InvalidPolicyException;

public interface PolicyHandler {
	public void removeAllStoredPolicies() throws CDSInternalException;
	
	public boolean isSupported(String policyClassName);

	public void storePolicy(DelegationIdentifier id, DelegationPolicy policy)
			throws CDSInternalException, InvalidPolicyException;

	public void removePolicy(DelegationIdentifier id) throws CDSInternalException;

	public DelegationPolicy getPolicy(DelegationIdentifier id)
			throws CDSInternalException, InvalidPolicyException;

	public boolean isAuthorized(DelegationIdentifier id, String gridIdentity)
			throws CDSInternalException;
}
