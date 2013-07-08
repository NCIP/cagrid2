package org.cagrid.dorian.federation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.common.Lifetime;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.policy.HostCertificateRenewalPolicy;
import org.cagrid.dorian.policy.SearchPolicyType;
import org.cagrid.dorian.service.impl.CredentialManager;

public class IdentityFederationProperties {

	public static int MIN_IDP_NAME_LENGTH = 3;
	public static int MAX_IDP_NAME_LENGTH = 60;
	public static int DEFAULT_MIN_IDP_DISPLAY_NAME_LENGTH = 3;
	public static int DEFAULT_MAX_IDP_DISPLAY_NAME_LENGTH = 60;
	public static int DEFAULT_MIN_IDP_NAME_LENGTH = 3;
	public static int DEFAULT_MAX_IDP_NAME_LENGTH = 25;

	private String identityAssignmentPolicy;
	private int minIdPNameLength;
	private int maxIdPNameLength;
	private int minIdPDisplayNameLength;
	private int maxIdPDisplayNameLength;
	private Lifetime issuedCertificateLifetime;
	private boolean autoHostCertificateApproval;
	private Lifetime userCertificateLifetime;
	private List<AccountPolicy> accountPolicies;
	private List<String> gtsPublishCRLList;
	private SearchPolicyType hostSearchPolicy;
	private SearchPolicyType userSearchPolicy;
	private HostCertificateRenewalPolicy hostCertificateRenewalPolicy;
	private CredentialManager credentialManager;

	public IdentityFederationProperties() {
		this.identityAssignmentPolicy = IdentityAssignmentPolicy.NAME;
		this.minIdPNameLength = DEFAULT_MIN_IDP_NAME_LENGTH;
		this.maxIdPNameLength = DEFAULT_MAX_IDP_NAME_LENGTH;
		this.minIdPDisplayNameLength = DEFAULT_MIN_IDP_DISPLAY_NAME_LENGTH;
		this.maxIdPDisplayNameLength = DEFAULT_MAX_IDP_DISPLAY_NAME_LENGTH;
		this.issuedCertificateLifetime = new Lifetime();
		this.issuedCertificateLifetime.setYears(1);
		this.autoHostCertificateApproval = false;
		this.userCertificateLifetime = new Lifetime();
		this.userCertificateLifetime.setHours(12);
		this.accountPolicies = new ArrayList<AccountPolicy>();
		this.accountPolicies.add(new ManualApprovalPolicy());
		this.gtsPublishCRLList = new ArrayList<String>();
		this.userSearchPolicy = SearchPolicyType.ADMIN;
		this.hostSearchPolicy = SearchPolicyType.ADMIN;
	}

	public int getMinIdPDisplayNameLength() {
		return minIdPDisplayNameLength;
	}

	public void setMinIdPDisplayNameLength(int minIdPDisplayNameLength) {
		this.minIdPDisplayNameLength = minIdPDisplayNameLength;
	}

	public int getMaxIdPDisplayNameLength() {
		return maxIdPDisplayNameLength;
	}

	public void setMaxIdPDisplayNameLength(int maxIdPDisplayNameLength) {
		this.maxIdPDisplayNameLength = maxIdPDisplayNameLength;
	}

	public String getIdentityAssignmentPolicy() {
		return identityAssignmentPolicy;
	}

	public void setIdentityAssignmentPolicy(String identityAssignmentPolicy) throws DorianInternalException {
		if (IdentityAssignmentPolicy.isValidPolicy(identityAssignmentPolicy)) {
			this.identityAssignmentPolicy = identityAssignmentPolicy;
		} else {
			DorianInternalException f = FaultHelper.createFaultException(DorianInternalException.class, "Invalid identity assigment policy specified.");
			throw f;
		}
	}

	public int getMinIdPNameLength() {
		return minIdPNameLength;
	}

	public void setMinIdPNameLength(int minIdPNameLength) throws DorianInternalException {
		if (this.minIdPNameLength < MIN_IDP_NAME_LENGTH) {
			DorianInternalException f = FaultHelper.createFaultException(DorianInternalException.class, "The minumum IdP name length must be at least " + MIN_IDP_NAME_LENGTH + " characters.");
			throw f;
		}
		this.minIdPNameLength = minIdPNameLength;
	}

	public int getMaxIdPNameLength() {
		return maxIdPNameLength;
	}

	public void setMaxIdPNameLength(int maxIdPNameLength) throws DorianInternalException {
		if (this.maxIdPNameLength > MAX_IDP_NAME_LENGTH) {
			DorianInternalException f = FaultHelper.createFaultException(DorianInternalException.class, "The maximum IdP name length must be nore more than " + MAX_IDP_NAME_LENGTH + " characters.");
			throw f;
		}
		this.maxIdPNameLength = maxIdPNameLength;
	}

	public Lifetime getIssuedCertificateLifetime() {
		return issuedCertificateLifetime;
	}

	public void setIssuedCertificateLifetime(Lifetime issuedCertificateLifetime) {
		this.issuedCertificateLifetime = issuedCertificateLifetime;
	}

	public boolean autoHostCertificateApproval() {
		return autoHostCertificateApproval;
	}

	public void setAutoHostCertificateApproval(boolean autoCertificateApproval) {
		this.autoHostCertificateApproval = autoCertificateApproval;
	}

	public Lifetime getUserCertificateLifetime() {
		return userCertificateLifetime;
	}

	public void setUserCertificateLifetime(Lifetime maxProxyLifetime) throws DorianInternalException {
		if ((this.userCertificateLifetime.getYears() != 0) || (this.userCertificateLifetime.getMonths() != 0) || (this.userCertificateLifetime.getDays() != 0)) {
			DorianInternalException f = FaultHelper.createFaultException(DorianInternalException.class, "The max proxy lifetime configuration cannot specify years, months, or days.");
			throw f;
		}
		this.userCertificateLifetime = maxProxyLifetime;
	}

	public List<AccountPolicy> getAccountPolicies() {
		return accountPolicies;
	}

	public void setAccountPolicies(List<AccountPolicy> accountPolicies) {
		this.accountPolicies = accountPolicies;
	}

	public List<String> getCRLPublishingList() {
		return gtsPublishCRLList;
	}

	public void setCRLPublishList(String list) {
		StringTokenizer st = new StringTokenizer(list, ",");
		while (st.hasMoreTokens()) {
			this.gtsPublishCRLList.add(st.nextToken());
		}
	}

	public String getHostSearchPolicy() {
		if (hostSearchPolicy == null) {
			this.hostSearchPolicy = SearchPolicyType.ADMIN;
		}
		return hostSearchPolicy.value();
	}

	public void setHostSearchPolicy(String searchPolicy) throws DorianInternalException {
		if (searchPolicy.equals(SearchPolicyType.PUBLIC.value()) || searchPolicy.equals(SearchPolicyType.AUTHENTICATED.value()) || searchPolicy.equals(SearchPolicyType.ADMIN.value())) {
			this.hostSearchPolicy = SearchPolicyType.fromValue(searchPolicy);
		} else {
			DorianInternalException f = FaultHelper.createFaultException(DorianInternalException.class, "The user search policy " + searchPolicy
					+ ", is invalid.  Please specify a valid search policy (" + SearchPolicyType.PUBLIC.value() + ", " + SearchPolicyType.AUTHENTICATED.value() + ", " + SearchPolicyType.ADMIN.value()
					+ ").");
			throw f;
		}
	}

	public String getUserSearchPolicy() {
		if (userSearchPolicy == null) {
			this.userSearchPolicy = SearchPolicyType.ADMIN;
		}
		return userSearchPolicy.value();
	}

	public void setUserSearchPolicy(String searchPolicy) throws DorianInternalException {
		if (searchPolicy.equals(SearchPolicyType.PUBLIC.value()) || searchPolicy.equals(SearchPolicyType.AUTHENTICATED.value()) || searchPolicy.equals(SearchPolicyType.ADMIN.value())) {
			this.userSearchPolicy = SearchPolicyType.fromValue(searchPolicy);
		} else {
			DorianInternalException f = FaultHelper.createFaultException(DorianInternalException.class, "The user search policy " + searchPolicy
					+ ", is invalid.  Please specify a valid search policy (" + SearchPolicyType.PUBLIC.value() + ", " + SearchPolicyType.AUTHENTICATED.value() + ", " + SearchPolicyType.ADMIN.value()
					+ ").");
			throw f;
		}
	}

	public String getHostCertificateRenewalPolicy() {
		if (hostCertificateRenewalPolicy == null) {
			this.hostCertificateRenewalPolicy = HostCertificateRenewalPolicy.ADMIN;
		}
		return hostCertificateRenewalPolicy.value();
	}

	public void setHostCertificateRenewalPolicy(String renewalPolicy) throws DorianInternalException {
		if (renewalPolicy.equals(HostCertificateRenewalPolicy.OWNER.value()) || renewalPolicy.equals(HostCertificateRenewalPolicy.ADMIN.value())) {
			this.hostCertificateRenewalPolicy = HostCertificateRenewalPolicy.fromValue(renewalPolicy);
		} else {
			DorianInternalException f = FaultHelper.createFaultException(DorianInternalException.class, "The host certificate renewal policy " + renewalPolicy
					+ ", is invalid.  Please specify a valid renewal policy (" + HostCertificateRenewalPolicy.OWNER.value() + ", " + HostCertificateRenewalPolicy.ADMIN.value() + ").");
			throw f;
		}
	}

	public CredentialManager getCredentialManager() {
		return credentialManager;
	}

	public void setCredentialManager(CredentialManager credentialManager) {
		this.credentialManager = credentialManager;
	}

}
