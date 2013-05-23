package org.cagrid.core.common;

import java.lang.reflect.Constructor;
import java.util.List;

import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.BaseFaultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FaultHelper {

	private final static Logger logger = LoggerFactory
			.getLogger(FaultHelper.class);

	@SuppressWarnings("unchecked")
	public static <T extends FaultException<?>> T createFaultException(
			Class<T> exceptionClass, String message) {

		/*
		 * Must have a constructor that takes something that extends BaseFault and a String.
		 */
		Constructor<T> exceptionConstructor = null;
		Class<? extends BaseFaultType> faultClass = null;
		for (Constructor<?> c : exceptionClass.getConstructors()) {
			Class<?>[] cParams = c.getParameterTypes();
			if (cParams.length != 2)
				continue;
			Class<?> cParam = cParams[0];
			if (BaseFaultType.class.isAssignableFrom(cParam) && (cParams[1] == String.class)) {
				exceptionConstructor = (Constructor<T>) c;
				faultClass = (Class<? extends BaseFaultType>) cParam;
			}
		}
		if (exceptionConstructor == null) {
			String msg = "No constructor taking a subclass of "
					+ BaseFaultType.class.getName() + " in "
					+ exceptionClass.getName();
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		Constructor<? extends BaseFaultType> faultConstructor = null;
		try {
			faultConstructor = faultClass.getConstructor();
		} catch (Exception e) {
			String msg = "No default constructor for " + faultClass.getName();
			logger.error(msg, e);
			throw new IllegalArgumentException(msg, e);
		}

		BaseFaultType fault = null;
		try {
			fault = faultConstructor.newInstance();
		} catch (Exception e) {
			String msg = "Could not construct fault " + faultClass.getName();
			logger.error(msg, e);
			throw new IllegalArgumentException(msg, e);
		}

		BaseFaultType.Description description = new BaseFaultType.Description();
		description.setValue(message);
		fault.getDescription().add(description);

		T exception = null;
		try {
			exception = exceptionConstructor.newInstance(fault, message);
		} catch (Exception e) {
			String msg = "Could not construct exception "
					+ exceptionClass.getName();
			logger.error(msg, e);
			throw new IllegalArgumentException(msg, e);
		}

		return exception;
	}

	public static String getMessage(FaultException<?> exception) {
		String message = null;
		BaseFaultType fault = exception.getFault();
		List<BaseFaultType.Description> descriptions = fault.getDescription();
		if (!descriptions.isEmpty()) {
			message = descriptions.get(0).getValue();
		}
		return message;
	}

	public static void addMessage(FaultException<?> exception, String message) {
		BaseFaultType.Description description = new BaseFaultType.Description();
		description.setValue(message);
		BaseFaultType fault = exception.getFault();
		fault.getDescription().add(description);
	}

	public static void addCause(FaultException<?> exception, BaseFaultType cause) {
		BaseFaultType fault = exception.getFault();
		fault.getFaultCause().add(cause);
	}
}
