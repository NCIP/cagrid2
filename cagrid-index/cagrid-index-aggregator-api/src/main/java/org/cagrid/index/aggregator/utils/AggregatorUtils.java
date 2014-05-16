package org.cagrid.index.aggregator.utils;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

public class AggregatorUtils {

    private static final Logger LOG = Logger.getLogger(AggregatorUtils.class.getName());

    // try to detect an EPR loopback condition
    // not perfect, but OK for now
    public static boolean detectLoopback(EndpointReferenceType remoteEPR, EndpointReferenceType localEPR) {
        boolean loopbackDetected = false;
        try {
            // first try the basic string-based comparison to fail fast
            if (remoteEPR.getAddress().equals(localEPR.getAddress())) {
                loopbackDetected = true;
            } else {
                URI remoteURI = new URI(remoteEPR.getAddress().getValue());
                URI localURI = new URI(localEPR.getAddress().getValue());

                InetAddress remoteAddr = null;
                InetAddress localAddr = null;
                // check to make sure we dont have two 'local' (or equivalent)
                // host addresses - note this will force DNS name resolution
                // to be used
                try {
                    remoteAddr = InetAddress.getByName(remoteURI.getHost());
                    localAddr = InetAddress.getByName(localURI.getHost());

                } catch (UnknownHostException e) {
                    LOG.warning("UnknownHostException: " + e.getMessage());
                    return false;
                }

                // check to see if the addresses resolved to the same IP address
                // or have special loopback characteristics
                // note - depends on JDK 1.4 or greater
                if (remoteAddr.isLoopbackAddress()
                        || (remoteAddr.getHostAddress().equalsIgnoreCase(localAddr.getHostAddress()))) {
                    // if the entries are now suspect as local addresses,
                    // then if the following matches, its enough to generate a warning
                    if ((remoteURI.getScheme().equalsIgnoreCase(localURI.getScheme()))
                            && (remoteURI.getPort() == localURI.getPort())
                            && (remoteURI.getPath().equalsIgnoreCase(localURI.getPath()))) {
                        loopbackDetected = true;
                    }
                }
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Unexpected Exception", e);
        }
        return loopbackDetected;
    }

}