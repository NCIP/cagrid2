package org.cagrid.dorian.idp.impl;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.common.Lifetime;
import org.cagrid.dorian.types.DorianInternalException;

public class PasswordSecurityPolicy {

	public final static int MIN_CONSECUTIVE_INVALID_LOGINS = 1;
	public final static int MIN_TOTAL_INVALID_LOGINS = 1;
	public final static int MIN_PASSWORD_LENGTH = 6;
	public final static int MAX_PASSWORD_LENGTH = 255;
	private Lifetime lockout;
	private int consecutiveInvalidLogins;
	private int totalInvalidLogins;
	private int minPasswordLength;
	private int maxPasswordLength;

	public PasswordSecurityPolicy() {
		this.lockout = new Lifetime();
		lockout.setHours(2);
		this.consecutiveInvalidLogins = 5;
		this.totalInvalidLogins = 100;
		this.minPasswordLength = MIN_PASSWORD_LENGTH;
		this.maxPasswordLength = 20;
	}

	public Lifetime getLockout() {
		return lockout;
	}

	public int getConsecutiveInvalidLogins() {
		return consecutiveInvalidLogins;
	}

	public int getTotalInvalidLogins() {
		return totalInvalidLogins;
	}

	public int getMinPasswordLength() {
		return minPasswordLength;
	}

	public int getMaxPasswordLength() {
		return maxPasswordLength;
	}

	public void setLockout(Lifetime lockout) {
		this.lockout = lockout;
	}

	public void setConsecutiveInvalidLogins(int consecutiveInvalidLogins)
			throws DorianInternalException {
		if (consecutiveInvalidLogins < MIN_CONSECUTIVE_INVALID_LOGINS) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The number of invalid consecutive logins must be at least "
							+ MIN_CONSECUTIVE_INVALID_LOGINS + ".");
			throw f;
		}
		this.consecutiveInvalidLogins = consecutiveInvalidLogins;
	}

	public void setTotalInvalidLogins(int totalInvalidLogins)
			throws DorianInternalException {
		if (totalInvalidLogins < MIN_TOTAL_INVALID_LOGINS) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The number of total invalid logins must be at least "
							+ MIN_TOTAL_INVALID_LOGINS + ".");
			throw f;
		}
		this.totalInvalidLogins = totalInvalidLogins;
	}

	public void setMinPasswordLength(int minPasswordLength)
			throws DorianInternalException {
		if (minPasswordLength < MIN_PASSWORD_LENGTH) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The mininum password length must be at least "
							+ MIN_PASSWORD_LENGTH + ".");
			throw f;
		}

		this.minPasswordLength = minPasswordLength;
	}

	public void setMaxPasswordLength(int maxPasswordLength)
			throws DorianInternalException {
		if (maxPasswordLength > MAX_PASSWORD_LENGTH) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"The maximum password length must be no more than "
							+ MAX_PASSWORD_LENGTH + ".");
			throw f;
		}
		this.maxPasswordLength = maxPasswordLength;
	}

}
