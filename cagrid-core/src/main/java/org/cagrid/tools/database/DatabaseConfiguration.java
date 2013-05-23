/**
 * DatabaseConfiguration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package org.cagrid.tools.database;

public class DatabaseConfiguration  implements java.io.Serializable {
    private java.lang.String host;
    private int port;
    private java.lang.String username;
    private java.lang.String password;

    public DatabaseConfiguration() {
    }

    public DatabaseConfiguration(
           java.lang.String host,
           java.lang.String password,
           int port,
           java.lang.String username) {
           this.host = host;
           this.port = port;
           this.username = username;
           this.password = password;
    }


    /**
     * Gets the host value for this DatabaseConfiguration.
     * 
     * @return host
     */
    public java.lang.String getHost() {
        return host;
    }


    /**
     * Sets the host value for this DatabaseConfiguration.
     * 
     * @param host
     */
    public void setHost(java.lang.String host) {
        this.host = host;
    }


    /**
     * Gets the port value for this DatabaseConfiguration.
     * 
     * @return port
     */
    public int getPort() {
        return port;
    }


    /**
     * Sets the port value for this DatabaseConfiguration.
     * 
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }


    /**
     * Gets the username value for this DatabaseConfiguration.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this DatabaseConfiguration.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this DatabaseConfiguration.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this DatabaseConfiguration.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }

}
