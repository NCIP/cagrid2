/**
 * X509CredentialDescriptor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package org.cagrid.gaards.credentials;

public class X509CredentialDescriptor  implements java.io.Serializable {
    private org.cagrid.gaards.credentials.EncodedCertificates encodedCertificates;
    private java.lang.String encodedKey;
    private java.lang.String identity;  // attribute

    public X509CredentialDescriptor() {
    }

    public X509CredentialDescriptor(
           org.cagrid.gaards.credentials.EncodedCertificates encodedCertificates,
           java.lang.String encodedKey,
           java.lang.String identity) {
           this.encodedCertificates = encodedCertificates;
           this.encodedKey = encodedKey;
           this.identity = identity;
    }


    /**
     * Gets the encodedCertificates value for this X509CredentialDescriptor.
     * 
     * @return encodedCertificates
     */
    public org.cagrid.gaards.credentials.EncodedCertificates getEncodedCertificates() {
        return encodedCertificates;
    }


    /**
     * Sets the encodedCertificates value for this X509CredentialDescriptor.
     * 
     * @param encodedCertificates
     */
    public void setEncodedCertificates(org.cagrid.gaards.credentials.EncodedCertificates encodedCertificates) {
        this.encodedCertificates = encodedCertificates;
    }


    /**
     * Gets the encodedKey value for this X509CredentialDescriptor.
     * 
     * @return encodedKey
     */
    public java.lang.String getEncodedKey() {
        return encodedKey;
    }


    /**
     * Sets the encodedKey value for this X509CredentialDescriptor.
     * 
     * @param encodedKey
     */
    public void setEncodedKey(java.lang.String encodedKey) {
        this.encodedKey = encodedKey;
    }


    /**
     * Gets the identity value for this X509CredentialDescriptor.
     * 
     * @return identity
     */
    public java.lang.String getIdentity() {
        return identity;
    }


    /**
     * Sets the identity value for this X509CredentialDescriptor.
     * 
     * @param identity
     */
    public void setIdentity(java.lang.String identity) {
        this.identity = identity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof X509CredentialDescriptor)) return false;
        X509CredentialDescriptor other = (X509CredentialDescriptor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.encodedCertificates==null && other.getEncodedCertificates()==null) || 
             (this.encodedCertificates!=null &&
              this.encodedCertificates.equals(other.getEncodedCertificates()))) &&
            ((this.encodedKey==null && other.getEncodedKey()==null) || 
             (this.encodedKey!=null &&
              this.encodedKey.equals(other.getEncodedKey()))) &&
            ((this.identity==null && other.getIdentity()==null) || 
             (this.identity!=null &&
              this.identity.equals(other.getIdentity())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getEncodedCertificates() != null) {
            _hashCode += getEncodedCertificates().hashCode();
        }
        if (getEncodedKey() != null) {
            _hashCode += getEncodedKey().hashCode();
        }
        if (getIdentity() != null) {
            _hashCode += getIdentity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(X509CredentialDescriptor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "X509CredentialDescriptor"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("identity");
        attrField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "Identity"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encodedCertificates");
        elemField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "EncodedCertificates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "EncodedCertificates"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encodedKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "EncodedKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
