/**
 * EncodedCertificates.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package org.cagrid.gaards.credentials;

public class EncodedCertificates  implements java.io.Serializable {
    private java.lang.String[] encodedCertificate;

    public EncodedCertificates() {
    }

    public EncodedCertificates(
           java.lang.String[] encodedCertificate) {
           this.encodedCertificate = encodedCertificate;
    }


    /**
     * Gets the encodedCertificate value for this EncodedCertificates.
     * 
     * @return encodedCertificate
     */
    public java.lang.String[] getEncodedCertificate() {
        return encodedCertificate;
    }


    /**
     * Sets the encodedCertificate value for this EncodedCertificates.
     * 
     * @param encodedCertificate
     */
    public void setEncodedCertificate(java.lang.String[] encodedCertificate) {
        this.encodedCertificate = encodedCertificate;
    }

    public java.lang.String getEncodedCertificate(int i) {
        return this.encodedCertificate[i];
    }

    public void setEncodedCertificate(int i, java.lang.String _value) {
        this.encodedCertificate[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EncodedCertificates)) return false;
        EncodedCertificates other = (EncodedCertificates) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.encodedCertificate==null && other.getEncodedCertificate()==null) || 
             (this.encodedCertificate!=null &&
              java.util.Arrays.equals(this.encodedCertificate, other.getEncodedCertificate())));
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
        if (getEncodedCertificate() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEncodedCertificate());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEncodedCertificate(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EncodedCertificates.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "EncodedCertificates"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encodedCertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "EncodedCertificate"));
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
