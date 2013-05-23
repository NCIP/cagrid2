package org.cagrid.gridgrouper.service.impl.util;

import org.cagrid.core.common.FaultException;
import org.cagrid.core.common.FaultHelper;

public class Errors {

    public static <T extends FaultException<?>> T makeException(Class<T> clazz, String msg, Exception e) {
        T fault = FaultHelper.createFaultException(clazz, msg);
        FaultHelper.addMessage(fault, e.getMessage());
        return fault;
    }

    public static <T extends FaultException<?>> T makeException(Class<T> clazz, String msg) {
        return FaultHelper.createFaultException(clazz, msg);
    }
}
