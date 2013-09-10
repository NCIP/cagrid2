
package org.cagrid.gme.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.gme.model.XMLSchemaImportInformation.Imports;


/**
 * This contains a collection of XMLSchemas, indexed by their respective
 * targetNamespaces, as well as a List of the targetNamespaces each of them
 * imports (also index by the importing schema's targetNamespace). This
 * information can be used to reconstruct a graph of schemas and their
 * relationships to each other. It could be processed by a library like JUNG
 * (http://jung.sourceforge.net/).
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSchemaBundle", propOrder = {
    "xmlSchemaCollection",
    "importInformationCollection"
})
public class XMLSchemaBundle
    implements Serializable
{

    protected XMLSchemaBundle.XmlSchemaCollection xmlSchemaCollection = new XmlSchemaCollection();
    protected XMLSchemaBundle.ImportInformationCollection importInformationCollection = new ImportInformationCollection();

    /**
     * Gets the value of the xmlSchemaCollection property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSchemaBundle.XmlSchemaCollection }
     *     
     */
    public XMLSchemaBundle.XmlSchemaCollection getXmlSchemaCollection() {
        return xmlSchemaCollection;
    }

    /**
     * Sets the new Set of XMLSchemas in the bundle. NOTE: this Set must be
     * consistent with the importInformation Set, in that every namespace
     * referenced in the ImportInformation Set must have a corresponding
     * XMLSchema, with that targetNamespace, in this Set.
     * 
     * @param xmlSchemaCollection
     *            the new Set of schemas
     */
    public void setXmlSchemaCollection(XMLSchemaBundle.XmlSchemaCollection xmlSchemaCollection) {
    	if (xmlSchemaCollection == null) {
    		this.xmlSchemaCollection = new XmlSchemaCollection();
    	}
    	else {
    		this.xmlSchemaCollection = xmlSchemaCollection;
    	}
    }

    /**
     * Gets the value of the importInformationCollection property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSchemaBundle.ImportInformationCollection }
     *     
     */
    public XMLSchemaBundle.ImportInformationCollection getImportInformationCollection() {
        return importInformationCollection;
    }

    /**
     * Sets the new Set of XMLSchemaImportInformation in the bundle. NOTE: this
     * Set must be consistent with the XMLSchema Set, in that every namespace
     * referenced in the ImportInformation Set must have a corresponding
     * XMLSchema, with that targetNamespace, in the XMLSchema Set.
     * 
     * @param importInformation
     *            the new Set of XMLSchemaImportInformation
     */
    public void setImportInformationCollection(XMLSchemaBundle.ImportInformationCollection importInformation) {
    	if (importInformation == null) {
    		this.importInformationCollection = new ImportInformationCollection();
    	}
    	else {
    		this.importInformationCollection = importInformation;
    	}
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaImportInformation" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "xmlSchemaImportInformation"
    })
    public static class ImportInformationCollection
        implements Serializable
    {

        @XmlElement(name = "XMLSchemaImportInformation", required = true)
        protected Set<XMLSchemaImportInformation> xmlSchemaImportInformation;;

        /**
         * Gets the value of the xmlSchemaImportInformation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the xmlSchemaImportInformation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getXMLSchemaImportInformation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XMLSchemaImportInformation }
         * 
         * 
         */
        public Set<XMLSchemaImportInformation> getXMLSchemaImportInformation() {
            if (xmlSchemaImportInformation == null) {
                xmlSchemaImportInformation = new HashSet<XMLSchemaImportInformation>();
            }
            return this.xmlSchemaImportInformation;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((xmlSchemaImportInformation == null) ? 0
							: xmlSchemaImportInformation.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ImportInformationCollection other = (ImportInformationCollection) obj;
			if (xmlSchemaImportInformation == null) {
				if (other.xmlSchemaImportInformation != null)
					return false;
			} else if (!xmlSchemaImportInformation
					.equals(other.xmlSchemaImportInformation))
				return false;
			return true;
		}

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchema" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "xmlSchema"
    })
    public static class XmlSchemaCollection
        implements Serializable
    {

        @XmlElement(name = "XMLSchema", required = true)
        protected Set<XMLSchema> xmlSchema;

        /**
         * Gets the value of the xmlSchema property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the xmlSchema property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getXMLSchema().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XMLSchema }
         * 
         * 
         */
        public Set<XMLSchema> getXMLSchema() {
            if (xmlSchema == null) {
                xmlSchema = new HashSet<XMLSchema>();
            }
            return this.xmlSchema;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((xmlSchema == null) ? 0 : xmlSchema.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			XmlSchemaCollection other = (XmlSchemaCollection) obj;
			if (xmlSchema == null) {
				if (other.xmlSchema != null)
					return false;
			} else if (!xmlSchema.equals(other.xmlSchema))
				return false;
			return true;
		}

    }

    /**
     * @return a Set of all of the targetNamespaces (represented as
     *         XMLSchemaNamespaces) of schemas in the Set
     */
    public Set<XMLSchemaNamespace> getXMLSchemaTargetNamespaces() {
        assert this.xmlSchemaCollection != null;

        Set<XMLSchemaNamespace> result = new HashSet<XMLSchemaNamespace>(this.xmlSchemaCollection.getXMLSchema().size());
        for (XMLSchema s : this.xmlSchemaCollection.getXMLSchema()) {
            result.add(new XMLSchemaNamespace(s.getTargetNamespace()));
        }
        return result;
    }

    /**
     * Utility accessor for retrieving an XMLSchema by its targetNamespace
     * 
     * @param targetNamespace
     *            the targetNamespace of the XMLSchema that is desired
     * @return the XMLSchema with the corresponding targetNamespace, or null, if
     *         it does not exist in the Set
     */
    public XMLSchema getXMLSchemaForTargetNamespace(XMLSchemaNamespace targetNamespace) {
        assert this.xmlSchemaCollection != null;

        for (XMLSchema s : this.xmlSchemaCollection.getXMLSchema()) {
            if (s.getTargetNamespace().equals(targetNamespace.uri)) {
                return s;
            }
        }
        return null;
    }


    /**
     * Utility accessor for retrieving an XMLSchema's imports by its
     * targetNamespace
     * 
     * @param targetNamespace
     *            the targetNamespace of the XMLSchema that is desired
     * @return the XMLSchema with the corresponding targetNamespace, or null, if
     *         it does not exist in the Set (which means there are no imports
     *         for it)
     */
    public XMLSchemaImportInformation getImportInformationForTargetNamespace(XMLSchemaNamespace targetNamespace) {
        assert this.importInformationCollection != null;

        for (XMLSchemaImportInformation ii : this.importInformationCollection.getXMLSchemaImportInformation()) {
            if (ii.getXMLSchemaNamespace().equals(targetNamespace)) {
                return ii;
            }
        }
        return null;
    }


    /**
     * Utility accessor for retrieving the set of imported XMLSchemas for an
     * XMLSchema identified by its targetNamespace
     * 
     * @param targetNamespace
     *            the targetNamespace of the XMLSchema for which the imported
     *            Schemas are desired
     * @return a Set of XMLSchema which are imported by the XMLSchema with the
     *         given targetNamespace, or null, if no such Schema exists
     */
    public Set<XMLSchema> getImportedXMLSchemasForTargetNamespace(XMLSchemaNamespace targetNamespace) {
        assert this.importInformationCollection != null;
        assert this.xmlSchemaCollection != null;

        XMLSchemaImportInformation schemaImportInformation = getImportInformationForTargetNamespace(targetNamespace);
        if (schemaImportInformation == null) {
            return null;
        }

        Set<XMLSchema> results = new HashSet<XMLSchema>();
        // walk the imported Schemas, and build up a Set containing the actual
        // XMLSchema instances
        for (XMLSchemaNamespace namespace : schemaImportInformation.getImports().getXMLSchemaNamespace()) {
            XMLSchema schema = getXMLSchemaForTargetNamespace(namespace);
            assert schema != null;
            results.add(schema);
        }

        // return the created set
        return results;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((importInformationCollection == null) ? 0
						: importInformationCollection.hashCode());
		result = prime
				* result
				+ ((xmlSchemaCollection == null) ? 0 : xmlSchemaCollection
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XMLSchemaBundle other = (XMLSchemaBundle) obj;
		if (importInformationCollection == null) {
			if (other.importInformationCollection != null)
				return false;
		} else if (!importInformationCollection
				.equals(other.importInformationCollection))
			return false;
		if (xmlSchemaCollection == null) {
			if (other.xmlSchemaCollection != null)
				return false;
		} else if (!xmlSchemaCollection.equals(other.xmlSchemaCollection))
			return false;
		return true;
	}

}
