/**
 * Metadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package org.cagrid.tools.database;

public class Metadata implements java.io.Serializable {
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String value;

	public Metadata() {
	}

	public Metadata(java.lang.String description, java.lang.String name,
			java.lang.String value) {
		this.name = name;
		this.description = description;
		this.value = value;
	}

	/**
	 * Gets the name value for this Metadata.
	 * 
	 * @return name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Sets the name value for this Metadata.
	 * 
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets the description value for this Metadata.
	 * 
	 * @return description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Sets the description value for this Metadata.
	 * 
	 * @param description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets the value value for this Metadata.
	 * 
	 * @return value
	 */
	public java.lang.String getValue() {
		return value;
	}

	/**
	 * Sets the value value for this Metadata.
	 * 
	 * @param value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}

	@Override
	public boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Metadata))
			return false;
		Metadata other = (Metadata) obj;
		if (this == obj)
			return true;
		return ((this.name == null && other.getName() == null) || (this.name != null && this.name
				.equals(other.getName())))
				&& ((this.description == null && other.getDescription() == null) || (this.description != null && this.description
						.equals(other.getDescription())))
				&& ((this.value == null && other.getValue() == null) || (this.value != null && this.value
						.equals(other.getValue())));
	}

	@Override
	public int hashCode() {
		int _hashCode = 1;
		if (getName() != null) {
			_hashCode += getName().hashCode();
		}
		if (getDescription() != null) {
			_hashCode += getDescription().hashCode();
		}
		if (getValue() != null) {
			_hashCode += getValue().hashCode();
		}
		return _hashCode;
	}
}
