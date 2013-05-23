package org.cagrid.dorian.idp.impl;

import gov.nih.nci.cagrid.common.Utils;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.policy.AccountInformationModificationPolicy;
import org.cagrid.dorian.types.DorianInternalException;

public class IdentityProviderProperties {
	private static final String DEFAULT_NAME = "Dorian";
	private static final int MIN_UID_LENGTH = 4;
	private static final int MAX_UID_LENGTH = 255;
	private static final int MAX_NAME_LENGTH = 255;

	private String name;
	private boolean autoRenewAssertingCredentials = false;
	private String assertingCredentialsEncryptionPassword;
	private int minUserIdLength = 4;
	private int maxUserIdLength = 255;
	private IdPRegistrationPolicy registrationPolicy;
	private PasswordSecurityPolicy passwordSecurityPolicy;
	private AccountInformationModificationPolicy accountInformationModificationPolicy;

	public String getName() {
		if (name == null) {
			name = DEFAULT_NAME;
		}
		return name;
	}

	public void setName(String name) throws DorianInternalException {
		if (name.length() > MAX_NAME_LENGTH) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The name of the Dorian IdP cannot exceed "
							+ MAX_NAME_LENGTH + " characters.");
			throw f;
		}
		this.name = name;
	}

	public boolean autoRenewAssertingCredentials() {
		return autoRenewAssertingCredentials;
	}

	public void setAutoRenewAssertingCredentials(
			boolean autoRenewAssertingCredentials) {
		this.autoRenewAssertingCredentials = autoRenewAssertingCredentials;
	}

	public String getAssertingCredentialsEncryptionPassword() {
		return assertingCredentialsEncryptionPassword;
	}

	public void setAssertingCredentialsEncryptionPassword(
			String assertingCredentialsEncryptionPassword)
			throws DorianInternalException {
		if (Utils.clean(assertingCredentialsEncryptionPassword) == null) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Invalid asserting credentials password specified.");
			throw f;
		}
		this.assertingCredentialsEncryptionPassword = assertingCredentialsEncryptionPassword;
	}

	public int getMinUserIdLength() {
		return minUserIdLength;
	}

	public void setMinUserIdLength(int minUserIdLength)
			throws DorianInternalException {
		if (minUserIdLength < MIN_UID_LENGTH) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The minimum user id must be at least " + MIN_UID_LENGTH
							+ " characters.");
			throw f;
		}
		this.minUserIdLength = minUserIdLength;
	}

	public int getMaxUserIdLength() {
		return maxUserIdLength;
	}

	public void setMaxUserIdLength(int maxUserIdLength)
			throws DorianInternalException {
		if (maxUserIdLength > MAX_UID_LENGTH) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The maximum user id must be no more than "
							+ MAX_UID_LENGTH + " characters.");
			throw f;
		}
		this.maxUserIdLength = maxUserIdLength;
	}

	public IdPRegistrationPolicy getRegistrationPolicy() {
		return registrationPolicy;
	}

	public PasswordSecurityPolicy getPasswordSecurityPolicy() {
		return passwordSecurityPolicy;
	}

	public void setRegistrationPolicy(IdPRegistrationPolicy registrationPolicy) {
		this.registrationPolicy = registrationPolicy;
	}

	public void setPasswordSecurityPolicy(
			PasswordSecurityPolicy passwordSecurityPolicy) {
		this.passwordSecurityPolicy = passwordSecurityPolicy;
	}

	public String getAccountInformationModificationPolicy() {
		if (this.accountInformationModificationPolicy == null) {
			this.accountInformationModificationPolicy = AccountInformationModificationPolicy.ADMIN;
		}
		return accountInformationModificationPolicy.value();
	}

	public void setAccountInformationModificationPolicy(String policy)
			throws DorianInternalException {
		if (policy.equals(AccountInformationModificationPolicy.USER.value())
				|| policy.equals(AccountInformationModificationPolicy.ADMIN
						.value())) {
			this.accountInformationModificationPolicy = AccountInformationModificationPolicy
					.fromValue(policy);
		} else {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The account information modification policy "
							+ policy
							+ ", is invalid.  Please specify a valid policy ("
							+ AccountInformationModificationPolicy.USER.value()
							+ ", "
							+ AccountInformationModificationPolicy.ADMIN
									.value() + ").");
			throw f;
		}

	}

}
