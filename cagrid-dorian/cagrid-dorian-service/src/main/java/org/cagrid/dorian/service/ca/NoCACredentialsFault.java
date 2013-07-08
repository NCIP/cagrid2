package org.cagrid.dorian.service.ca;

import java.util.Arrays;
import java.util.Calendar;

import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.BaseFaultType;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class NoCACredentialsFault extends BaseFaultType implements
		java.io.Serializable {

	public NoCACredentialsFault() {
	}

	public NoCACredentialsFault(Calendar timestamp,
			EndpointReferenceType originator,
			BaseFaultType.ErrorCode errorCode,
			BaseFaultType.Description[] description, BaseFaultType[] faultCause) {

		setTimestamp(timestamp);
		setOriginator(originator);
		setErrorCode(errorCode);
		if (description != null) {
			getDescription().addAll(Arrays.asList(description));
		}
		if (faultCause != null) {
			getFaultCause().addAll(Arrays.asList(faultCause));
		}
	}
}
