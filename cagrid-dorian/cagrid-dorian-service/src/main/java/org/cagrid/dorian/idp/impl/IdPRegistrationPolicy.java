package org.cagrid.dorian.idp.impl;

import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.exceptions.InvalidUserPropertyException;
import org.cagrid.dorian.model.idp.Application;
import org.cagrid.dorian.model.idp.ApplicationReview;

public interface IdPRegistrationPolicy {
    public ApplicationReview register(Application application) throws DorianInternalException, InvalidUserPropertyException;


    public String getDescription();


    public String getName();
}
