package org.cagrid.dorian.service.federation;

import org.cagrid.dorian.model.federation.GridUser;
import org.cagrid.dorian.model.federation.TrustedIdP;

public class FederationDefaults {
    private GridUser defaultUser;
    private TrustedIdP defaultIdP;


    public FederationDefaults(TrustedIdP idp, GridUser user) {
        this.defaultIdP = idp;
        this.defaultUser = user;
    }


    public GridUser getDefaultUser() {
        return defaultUser;
    }


    public TrustedIdP getDefaultIdP() {
        return defaultIdP;
    }


    public void setDefaultUser(GridUser defaultUser) {
        this.defaultUser = defaultUser;
    }


    public void setDefaultIdP(TrustedIdP defaultIdP) {
        this.defaultIdP = defaultIdP;
    }
}
