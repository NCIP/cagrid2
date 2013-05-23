package org.cagrid.dorian.ca.impl;

import java.security.PrivateKey;


public interface WrappingCertificateAuthority {
    public WrappedKey wrap(PrivateKey key) throws CertificateAuthorityException;


    public PrivateKey unwrap(WrappedKey key) throws CertificateAuthorityException;
}
