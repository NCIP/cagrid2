package org.cagrid.gridgrouper.ws.client;

import org.cagrid.core.soapclient.ClientConfigurer;

/**
 * Created by langella on 3/21/14.
 */
public class GridGrouperClientFactory {

    public static GridGrouperClient getClient(String url, ClientConfigurer configurer) {
        GridGrouperClient client = new GridGrouperClient(url);
        configurer.configureClient(client);
        return client;
    }

}
