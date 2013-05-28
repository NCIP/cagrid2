/**
   Pickledx509.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed Dec 31 16:00:00 1969


   This package is a SSLv3/TLS implementation written by Eric Rescorla
   <ekr\@rtfm.com> and licensed by Claymore Systems, Inc.

   Redistribution and use in source and binary forms, with or without
   modification, are permitted provided that the following conditions
   are met:
   1. Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
   2. Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
   3. All advertising materials mentioning features or use of this software
      must display the following acknowledgement:
      This product includes software developed by Claymore Systems, Inc.
   4. Neither the name of Claymore Systems, Inc. nor the name of Eric
      Rescorla may be used to endorse or promote products derived from this
      software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
   ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
   FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
   DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
   OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
   HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
   OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
   SUCH DAMAGE.


   $Id: Pickledx509.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;

/** This is pickled version of x509.asn
    <P>
    This is a machine generated file. Do not edit.
*/
class Pickledx509 extends java.io.StringReader {
  public Pickledx509() {
    super(
"-- x509.asn --\n"
+ "-- modified by EKR <ekr@rtfm.com> from the version in the\n"
+ "-- Cryptix ASN.1 kit\n"
+ "-- new items are tagged by an [EKR] comment\n"
+ "-- sun.asn: contains the asn.1 X509v3 definition\n"
+ "-- From: Søren Hilmer <hilmer@teliamail.dk>\n"
+ "-- Object identifiers\n"
+ "-------------------------------------------------------------------\n"
+ "\n"
+ "pkcs-1 OBJECT IDENTIFIER ::= {\n"
+ "    iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 1\n"
+ "}\n"
+ "\n"
+ "-- This object identifier identifies RSA public and private\n"
+ "-- keys and the RSA encryption and decryption processes.\n"
+ "\n"
+ "rsaEncryption OBJECT IDENTIFIER ::= { pkcs-1 1 }\n"
+ "\n"
+ "\n"
+ "-- These object identifiers identify respectively, the\n"
+ "-- \"MD2 with RSA,\" \"MD4 with RSA,\" and \"MD5 with RSA\" signature\n"
+ "-- and verification processes.\n"
+ "\n"
+ "md2WithRSAEncryption OBJECT IDENTIFIER ::= { pkcs-1 2 }\n"
+ "md4WithRSAEncryption OBJECT IDENTIFIER ::= { pkcs-1 3 }\n"
+ "md5WithRSAEncryption OBJECT IDENTIFIER ::= { pkcs-1 4 }\n"
+ "\n"
+ "\n"
+ "md2 OBJECT IDENTIFIER ::= {\n"
+ "    iso(1) member-body(2) us(840) rsadsi(113549) digestAlgorithmId(2) 2\n"
+ "}\n"
+ "\n"
+ "md4 OBJECT IDENTIFIER ::= {\n"
+ "    iso(1) member-body(2) us(840) rsadsi(113549) digestAlgorithmId(2) 4\n"
+ "}\n"
+ "\n"
+ "md5 OBJECT IDENTIFIER ::= {\n"
+ "    iso(1) member-body(2) us(840) rsadsi(113549) digestAlgorithmId(2) 5\n"
+ "}\n"
+ "\n"
+ "ds OBJECT IDENTIFIER ::= { joint-iso-ccitt(2) 5 }\n"
+ "\n"
+ "id-at                         OBJECT IDENTIFIER ::= { ds 4 }\n"
+ "id-at-commonName              OBJECT IDENTIFIER ::= { id-at  3 }\n"
+ "id-at-countryName             OBJECT IDENTIFIER ::= { id-at  6 }\n"
+ "id-at-organizationName        OBJECT IDENTIFIER ::= { id-at 10 }\n"
+ "id-at-organizationalUnitName  OBJECT IDENTIFIER ::= { id-at 11 }\n"
+ "\n"
+ "\n"
+ "-- RSA Public and Private keys\n"
+ "-------------------------------------------------------------------\n"
+ "\n"
+ "RSAPublicKey ::= SEQUENCE {\n"
+ "    modulus         INTEGER, -- the modulus n\n"
+ "    publicExponent  INTEGER  -- the public exponent e\n"
+ "}\n"
+ "\n"
+ "RSAPrivateKey ::= SEQUENCE {\n"
+ "    version         Version, -- the version number, for compatibility\n"
+ "                             -- with future revisions of this standard.\n"
+ "                             -- It shall be 0 for this version of the\n"
+ "                             -- standard.\n"
+ "    modulus         INTEGER, -- the modulus n\n"
+ "    publicExponent  INTEGER, -- the public exponent e\n"
+ "    privateExponent INTEGER, -- the private exponent d\n"
+ "    prime1          INTEGER, -- the prime factor p of n\n"
+ "    prime2          INTEGER, -- the prime factor q of n\n"
+ "    exponent1       INTEGER, -- d mod (p-1)\n"
+ "    exponent2       INTEGER, -- d mod (q-1)\n"
+ "    coefficient     INTEGER  -- (inverse of q) mod p\n"
+ "}\n"
+ "\n"
+ "-- Version ::= INTEGER\n"
+ "Version ::= INTEGER { v1(0), v2(1), v3(2) } -- used also in Certificate\n"
+ "\n"
+ "\n"
+ "DSAParameters ::= SEQUENCE {\n"
+ "     p INTEGER,\n"
+ "     q INTEGER,\n"
+ "     g INTEGER\n"
+ "}\n"
+ "\n"
+ "DSSSignature ::= SEQUENCE {\n"
+ "     r INTEGER,\n"
+ "     s INTEGER\n"
+ "}\n"
+ "\n"
+ "DSAKey ::= INTEGER\n"
+ "\n"
+ "EAYDSAPrivateKey ::= SEQUENCE {\n"
+ "	version INTEGER,\n"
+ "	p INTEGER,\n"
+ "	q INTEGER,\n"
+ "	g INTEGER,\n"
+ "	y INTEGER,\n"
+ "	x INTEGER\n"
+ "}	\n"
+ "	      \n"
+ "-- Signature algorithms\n"
+ "-------------------------------------------------------------------\n"
+ "\n"
+ "DigestInfo ::= SEQUENCE {\n"
+ "    -- Identifies the message-digest algorithm (and any associated\n"
+ "    -- parameters). For this application, it should identify the\n"
+ "    -- selected message-digest algorithm, MD2, MD4 or MD5. For\n"
+ "    -- reference, the relevant object identifiers are given earlier\n"
+ "    -- in this module.\n"
+ "    digestAlgorithm       DigestAlgorithmIdentifier,\n"
+ "\n"
+ "    -- The result of the message-digesting process, i.e., the message\n"
+ "    -- digest MD.\n"
+ "    digest                Digest\n"
+ "}\n"
+ "\n"
+ "DigestAlgorithmIdentifier ::= AlgorithmIdentifier\n"
+ "\n"
+ "Digest ::= OCTET STRING\n"
+ "\n"
+ "\n"
+ "-- PKCS-6 Appendix A (X.509 certificate)\n"
+ "-------------------------------------------------------------------\n"
+ "\n"
+ "Certificate  ::=  SEQUENCE  {\n"
+ "     tbsCertificate       TBSCertificate,\n"
+ "     signatureAlgorithm   AlgorithmIdentifier,\n"
+ "     signature            BIT STRING\n"
+ "}\n"
+ "\n"
+ "TBSCertificate  ::=  SEQUENCE  {\n"
+ "     version         [0]  EXPLICIT Version OPTIONAL, -- This should be DEFAULT v1, but the kit\n"
+ "						     -- doesn't know how\n"
+ "     serialNumber         CertificateSerialNumber,\n"
+ "     signature            AlgorithmIdentifier,\n"
+ "     issuer               Name,\n"
+ "     validity             Validity,\n"
+ "     subject              Name,\n"
+ "     subjectPublicKeyInfo SubjectPublicKeyInfo,\n"
+ "     issuerUniqueID  [1]  IMPLICIT UniqueIdentifier OPTIONAL,\n"
+ "                          -- If present, version must be v2 or v3\n"
+ "     subjectUniqueID [2]  IMPLICIT UniqueIdentifier OPTIONAL,\n"
+ "                          -- If present, version must be v2 or v3\n"
+ "     extensions      [3]  EXPLICIT Extensions OPTIONAL\n"
+ "                          -- If present, version must be v3\n"
+ "     }\n"
+ "\n"
+ "\n"
+ "UsefulCertificate ::= SEQUENCE {\n"
+ "     tbsCertificate [UNIVERSAL 16] IMPLICIT ANY DEFINED BY ekr,\n"
+ "     signatureAlgorithm AlgorithmIdentifier,\n"
+ "     signature BIT STRING\n"
+ "}\n"
+ "\n"
+ "RawName ::= [UNIVERSAL 16] IMPLICIT ANY DEFINED BY ekr\n"
+ "RawName2 ::= [UNIVERSAL 16] IMPLICIT ANY DEFINED BY ekr\n"
+ "RawSubjectPublicKeyInfo ::= [UNIVERSAL 16] IMPLICIT ANY DEFINED BY ekr\n"
+ "\n"
+ "UsefulTBSCertificate  ::=  SEQUENCE  {\n"
+ "     version         [0]  EXPLICIT Version OPTIONAL, -- This should be DEFAULT v1, but the kit\n"
+ "						     -- doesn't know how\n"
+ "--     version         [0]  EXPLICIT Version DEFAULT v1,--\n"
+ "     serialNumber         CertificateSerialNumber,\n"
+ "     signature            AlgorithmIdentifier,\n"
+ "     issuer               RawName,\n"
+ "     validity             Validity,\n"
+ "     subject              RawName2, -- HACK --\n"
+ "     subjectPublicKeyInfo RawSubjectPublicKeyInfo,\n"
+ "     issuerUniqueID  [1]  IMPLICIT UniqueIdentifier OPTIONAL,\n"
+ "                          -- If present, version must be v2 or v3\n"
+ "     subjectUniqueID [2]  IMPLICIT UniqueIdentifier OPTIONAL,\n"
+ "                          -- If present, version must be v2 or v3\n"
+ "     extensions      [3]  EXPLICIT Extensions OPTIONAL\n"
+ "                          -- If present, version must be v3\n"
+ "     }\n"
+ "\n"
+ "UniqueIdentifier  ::=  BIT STRING\n"
+ "\n"
+ "Extensions  ::=  SEQUENCE OF ANY\n"
+ "-- Extensions  ::=  SEQUENCE OF Extension\n"
+ "\n"
+ "Extension  ::=  SEQUENCE  {\n"
+ "   extnID      OBJECT IDENTIFIER,\n"
+ "   critical    BOOLEAN OPTIONAL,\n"
+ "   extnValue   OCTET STRING\n"
+ "}\n"
+ "\n"
+ "CertificateSerialNumber ::= INTEGER\n"
+ "\n"
+ "Validity ::= SEQUENCE {\n"
+ "    notBefore  UTCTime,\n"
+ "    notAfter   UTCTime\n"
+ "}\n"
+ "\n"
+ "SubjectPublicKeyInfo ::= SEQUENCE {\n"
+ "    algorithm         AlgorithmIdentifier,\n"
+ "    subjectPublicKey  BIT STRING\n"
+ "}\n"
+ "\n"
+ "AlgorithmIdentifier ::= SEQUENCE {\n"
+ "    algorithm   OBJECT IDENTIFIER,\n"
+ "    parameters  ANY DEFINED BY algorithm OPTIONAL\n"
+ "}\n"
+ "\n"
+ "Name ::= RDNSequence\n"
+ "\n"
+ "RDNSequence ::= SEQUENCE OF RelativeDistinguishedName\n"
+ "\n"
+ "RelativeDistinguishedName ::= SET OF AttributeValueAssertion\n"
+ "\n"
+ "AttributeValueAssertion ::= SEQUENCE {\n"
+ "    attributeType     AttributeType,\n"
+ "    attributeValue    AttributeValue\n"
+ "}\n"
+ "\n"
+ "AttributeType ::= OBJECT IDENTIFIER\n"
+ "\n"
+ "AttributeValue ::= PrintableString\n"
    );
  }
}
