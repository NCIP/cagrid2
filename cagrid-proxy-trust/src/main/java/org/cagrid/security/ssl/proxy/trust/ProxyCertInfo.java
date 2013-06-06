/*
 * Portions of this file Copyright 1999-2005 University of Chicago
 * Portions of this file Copyright 1999-2005 The University of Southern California.
 *
 * This file or a portion of this file is licensed under the
 * terms of the Globus Toolkit Public License, found at
 * http://www.globus.org/toolkit/download/license.html.
 * If you redistribute this file, with or without
 * modifications, you must include this notice in the file.
 */
package org.cagrid.security.ssl.proxy.trust;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;

/**
 * Represents ProxyCertInfo extension. <BR>
 * 
 * <PRE>
 * ProxyCertInfo ::= SEQUENCE {
 *    pCPathLenConstraint      INTEGER (0..MAX) OPTIONAL,
 *    proxyPolicy              ProxyPolicy }
 * </PRE>
 */
public class ProxyCertInfo implements DEREncodable {

	/** ProxyCertInfo extension OID */
	public static final DERObjectIdentifier OID = new DERObjectIdentifier(
			"1.3.6.1.5.5.7.1.14");
	public static final DERObjectIdentifier OLD_OID = new DERObjectIdentifier(
			"1.3.6.1.4.1.3536.1.222");

	private DERInteger pathLenConstraint;
	private ProxyPolicy proxyPolicy;

	/**
	 * Creates a new instance of the ProxyCertInfo extension from given
	 * ASN1Sequence object.
	 * 
	 * @param seq
	 *            ASN1Sequence object to create the instance from.
	 */
	public ProxyCertInfo(ASN1Sequence seq) {
		if (seq.size() < 1) {
			throw new IllegalArgumentException("Invalid sequence");
		}

		int seqPos = 0;

		if (seq.getObjectAt(seqPos) instanceof DERInteger) {
			this.pathLenConstraint = (DERInteger) seq.getObjectAt(seqPos);
			seqPos++;
		}

		ASN1Sequence policy = (ASN1Sequence) seq.getObjectAt(seqPos);

		this.proxyPolicy = new ProxyPolicy(policy);
	}

	/**
	 * Creates a new instance of the ProxyCertInfo extension.
	 * 
	 * @param pathLenConstraint
	 *            the path length constraint of the extension.
	 * @param policy
	 *            the policy of the extension.
	 */
	public ProxyCertInfo(int pathLenConstraint, ProxyPolicy policy) {
		if (policy == null) {
			throw new IllegalArgumentException();
		}
		this.pathLenConstraint = new DERInteger(pathLenConstraint);
		this.proxyPolicy = policy;
	}

	/**
	 * Creates a new instance of the ProxyCertInfo extension with no path length
	 * constraint.
	 * 
	 * @param policy
	 *            the policy of the extension.
	 */
	public ProxyCertInfo(ProxyPolicy policy) {
		if (policy == null) {
			throw new IllegalArgumentException();
		}
		this.pathLenConstraint = null;
		this.proxyPolicy = policy;
	}

	/**
	 * Returns an instance of <code>ProxyCertInfo</code> from given object.
	 * 
	 * @param obj
	 *            the object to create the instance from.
	 * @return <code>ProxyCertInfo</code> instance.
	 * @exception IllegalArgumentException
	 *                if unable to convert the object to
	 *                <code>ProxyCertInfo</code> instance.
	 */
	public static ProxyCertInfo getInstance(Object obj) {
		if (obj instanceof ProxyCertInfo)
			return (ProxyCertInfo) obj;

		if (obj instanceof byte[]) {
			try {
				obj = ASN1Object.fromByteArray((byte[]) obj);
			} catch (IOException ignored) {
			}
		}

		if (obj instanceof ASN1Sequence)
			return new ProxyCertInfo((ASN1Sequence) obj);

		throw new IllegalArgumentException("unknown object in factory");
	}

	/**
	 * Returns the DER-encoded ASN.1 representation of the extension.
	 * 
	 * @return <code>DERObject</code> the encoded representation of the
	 *         extension.
	 */
	public DERObject getDERObject() {
		ASN1EncodableVector vec = new ASN1EncodableVector();

		if (this.pathLenConstraint != null) {
			vec.add(this.pathLenConstraint);
		}

		vec.add(this.proxyPolicy.getDERObject());

		return new DERSequence(vec);
	}

	/**
	 * Returns the policy object in the proxy.
	 * 
	 * @return <code>ProxyPolicy</code> the policy object
	 */
	public ProxyPolicy getProxyPolicy() {
		return this.proxyPolicy;
	}

	/**
	 * Returns the maximum depth of the path of proxy certificates that can be
	 * signed by this proxy certificate.
	 * 
	 * @return the maximum depth of the path of proxy certificates that can be
	 *         signed by this proxy certificate. If 0 then this certificate must
	 *         not be used to sign a proxy certificate. If the path length
	 *         constraint field is not defined <code>Integer.MAX_VALUE</code> is
	 *         returned.
	 */
	public int getPathLenConstraint() {
		if (this.pathLenConstraint != null) {
			return this.pathLenConstraint.getValue().intValue();
		}
		return Integer.MAX_VALUE;
	}

}
