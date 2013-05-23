package org.cagrid.dorian.idp.impl;

import org.cagrid.dorian.idp.Application;
import org.cagrid.dorian.idp.ApplicationReview;
import org.cagrid.dorian.types.DorianInternalException;
import org.cagrid.dorian.types.InvalidUserPropertyException;

public interface IdPRegistrationPolicy {
    public ApplicationReview register(Application application) throws DorianInternalException, InvalidUserPropertyException;


    public String getDescription();


    public String getName();
}
