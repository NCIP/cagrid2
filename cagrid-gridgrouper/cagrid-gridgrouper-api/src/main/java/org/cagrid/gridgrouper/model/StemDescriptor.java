
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StemDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StemDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Extension" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayExtension" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreateSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreateSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreateTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ModifySource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModifySubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModifyTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StemDescriptor", propOrder = {
    "uuid",
    "name",
    "displayName",
    "extension",
    "displayExtension",
    "description",
    "createSource",
    "createSubject",
    "createTime",
    "modifySource",
    "modifySubject",
    "modifyTime"
})
public class StemDescriptor
    implements Serializable
{

    @XmlElement(name = "UUID", required = true)
    protected String uuid;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "DisplayName", required = true)
    protected String displayName;
    @XmlElement(name = "Extension", required = true)
    protected String extension;
    @XmlElement(name = "DisplayExtension", required = true)
    protected String displayExtension;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "CreateSource", required = true)
    protected String createSource;
    @XmlElement(name = "CreateSubject", required = true)
    protected String createSubject;
    @XmlElement(name = "CreateTime")
    protected long createTime;
    @XmlElement(name = "ModifySource", required = true)
    protected String modifySource;
    @XmlElement(name = "ModifySubject", required = true)
    protected String modifySubject;
    @XmlElement(name = "ModifyTime")
    protected long modifyTime;

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUUID(String value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtension(String value) {
        this.extension = value;
    }

    /**
     * Gets the value of the displayExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayExtension() {
        return displayExtension;
    }

    /**
     * Sets the value of the displayExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayExtension(String value) {
        this.displayExtension = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the createSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateSource() {
        return createSource;
    }

    /**
     * Sets the value of the createSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateSource(String value) {
        this.createSource = value;
    }

    /**
     * Gets the value of the createSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateSubject() {
        return createSubject;
    }

    /**
     * Sets the value of the createSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateSubject(String value) {
        this.createSubject = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     */
    public void setCreateTime(long value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the modifySource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifySource() {
        return modifySource;
    }

    /**
     * Sets the value of the modifySource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifySource(String value) {
        this.modifySource = value;
    }

    /**
     * Gets the value of the modifySubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifySubject() {
        return modifySubject;
    }

    /**
     * Sets the value of the modifySubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifySubject(String value) {
        this.modifySubject = value;
    }

    /**
     * Gets the value of the modifyTime property.
     * 
     */
    public long getModifyTime() {
        return modifyTime;
    }

    /**
     * Sets the value of the modifyTime property.
     * 
     */
    public void setModifyTime(long value) {
        this.modifyTime = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StemDescriptor{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append(", displayExtension='").append(displayExtension).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", createSource='").append(createSource).append('\'');
        sb.append(", createSubject='").append(createSubject).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", modifySource='").append(modifySource).append('\'');
        sb.append(", modifySubject='").append(modifySubject).append('\'');
        sb.append(", modifyTime=").append(modifyTime);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StemDescriptor that = (StemDescriptor) o;

        if (createTime != that.createTime) return false;
        if (modifyTime != that.modifyTime) return false;
        if (createSource != null ? !createSource.equals(that.createSource) : that.createSource != null) return false;
        if (createSubject != null ? !createSubject.equals(that.createSubject) : that.createSubject != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (displayExtension != null ? !displayExtension.equals(that.displayExtension) : that.displayExtension != null)
            return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (extension != null ? !extension.equals(that.extension) : that.extension != null) return false;
        if (modifySource != null ? !modifySource.equals(that.modifySource) : that.modifySource != null) return false;
        if (modifySubject != null ? !modifySubject.equals(that.modifySubject) : that.modifySubject != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (displayExtension != null ? displayExtension.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createSource != null ? createSource.hashCode() : 0);
        result = 31 * result + (createSubject != null ? createSubject.hashCode() : 0);
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (modifySource != null ? modifySource.hashCode() : 0);
        result = 31 * result + (modifySubject != null ? modifySubject.hashCode() : 0);
        result = 31 * result + (int) (modifyTime ^ (modifyTime >>> 32));
        return result;
    }
}
