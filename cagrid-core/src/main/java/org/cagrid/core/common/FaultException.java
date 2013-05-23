package org.cagrid.core.common;

import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.BaseFaultType;

public interface FaultException<F extends BaseFaultType> {

	public F getFault();
}
