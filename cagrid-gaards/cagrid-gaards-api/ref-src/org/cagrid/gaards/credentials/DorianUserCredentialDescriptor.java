/**
 * DorianUserCredentialDescriptor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package org.cagrid.gaards.credentials;

public class DorianUserCredentialDescriptor  extends org.cagrid.gaards.credentials.X509CredentialDescriptor  implements java.io.Serializable {
    private java.lang.String authenticationServiceURL;  // attribute
    private java.lang.String dorianURL;  // attribute
    private java.lang.String firstName;  // attribute
    private java.lang.String lastName;  // attribute
    private java.lang.String email;  // attribute
    private java.lang.String organization;  // attribute

    public DorianUserCredentialDescriptor() {
    }

    public DorianUserCredentialDescriptor(
           java.lang.String authenticationServiceURL,
           java.lang.String dorianURL,
           java.lang.String email,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String organization) {
           this.authenticationServiceURL = authenticationServiceURL;
           this.dorianURL = dorianURL;
           this.firstName = firstName;
           this.lastName = lastName;
           this.email = email;
           this.organization = organization;
    }


    /**
     * Gets the authenticationServiceURL value for this DorianUserCredentialDescriptor.
     * 
     * @return authenticationServiceURL
     */
    public java.lang.String getAuthenticationServiceURL() {
        return authenticationServiceURL;
    }


    /**
     * Sets the authenticationServiceURL value for this DorianUserCredentialDescriptor.
     * 
     * @param authenticationServiceURL
     */
    public void setAuthenticationServiceURL(java.lang.String authenticationServiceURL) {
        this.authenticationServiceURL = authenticationServiceURL;
    }


    /**
     * Gets the dorianURL value for this DorianUserCredentialDescriptor.
     * 
     * @return dorianURL
     */
    public java.lang.String getDorianURL() {
        return dorianURL;
    }


    /**
     * Sets the dorianURL value for this DorianUserCredentialDescriptor.
     * 
     * @param dorianURL
     */
    public void setDorianURL(java.lang.String dorianURL) {
        this.dorianURL = dorianURL;
    }


    /**
     * Gets the firstName value for this DorianUserCredentialDescriptor.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this DorianUserCredentialDescriptor.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this DorianUserCredentialDescriptor.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this DorianUserCredentialDescriptor.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the email value for this DorianUserCredentialDescriptor.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this DorianUserCredentialDescriptor.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the organization value for this DorianUserCredentialDescriptor.
     * 
     * @return organization
     */
    public java.lang.String getOrganization() {
        return organization;
    }


    /**
     * Sets the organization value for this DorianUserCredentialDescriptor.
     * 
     * @param organization
     */
    public void setOrganization(java.lang.String organization) {
        this.organization = organization;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DorianUserCredentialDescriptor)) return false;
        DorianUserCredentialDescriptor other = (DorianUserCredentialDescriptor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.authenticationServiceURL==null && other.getAuthenticationServiceURL()==null) || 
             (this.authenticationServiceURL!=null &&
              this.authenticationServiceURL.equals(other.getAuthenticationServiceURL()))) &&
            ((this.dorianURL==null && other.getDorianURL()==null) || 
             (this.dorianURL!=null &&
              this.dorianURL.equals(other.getDorianURL()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.organization==null && other.getOrganization()==null) || 
             (this.organization!=null &&
              this.organization.equals(other.getOrganization())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAuthenticationServiceURL() != null) {
            _hashCode += getAuthenticationServiceURL().hashCode();
        }
        if (getDorianURL() != null) {
            _hashCode += getDorianURL().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getOrganization() != null) {
            _hashCode += getOrganization().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DorianUserCredentialDescriptor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "DorianUserCredentialDescriptor"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("authenticationServiceURL");
        attrField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "AuthenticationServiceURL"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("dorianURL");
        attrField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "DorianURL"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("firstName");
        attrField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "FirstName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("lastName");
        attrField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "LastName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("email");
        attrField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "Email"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("organization");
        attrField.setXmlName(new javax.xml.namespace.QName("http://gaards.cagrid.org/credentials", "Organization"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
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
