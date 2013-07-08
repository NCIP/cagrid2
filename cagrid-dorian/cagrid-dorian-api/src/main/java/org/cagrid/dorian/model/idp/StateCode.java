
package org.cagrid.dorian.model.idp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stateCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="stateCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Outside_US"/>
 *     &lt;enumeration value="AL"/>
 *     &lt;enumeration value="AK"/>
 *     &lt;enumeration value="AS"/>
 *     &lt;enumeration value="AZ"/>
 *     &lt;enumeration value="AR"/>
 *     &lt;enumeration value="CA"/>
 *     &lt;enumeration value="CO"/>
 *     &lt;enumeration value="CT"/>
 *     &lt;enumeration value="DE"/>
 *     &lt;enumeration value="DC"/>
 *     &lt;enumeration value="FM"/>
 *     &lt;enumeration value="FL"/>
 *     &lt;enumeration value="GA"/>
 *     &lt;enumeration value="GU"/>
 *     &lt;enumeration value="HI"/>
 *     &lt;enumeration value="ID"/>
 *     &lt;enumeration value="IL"/>
 *     &lt;enumeration value="IN"/>
 *     &lt;enumeration value="IA"/>
 *     &lt;enumeration value="KS"/>
 *     &lt;enumeration value="KY"/>
 *     &lt;enumeration value="LA"/>
 *     &lt;enumeration value="ME"/>
 *     &lt;enumeration value="MH"/>
 *     &lt;enumeration value="MD"/>
 *     &lt;enumeration value="MA"/>
 *     &lt;enumeration value="MI"/>
 *     &lt;enumeration value="MN"/>
 *     &lt;enumeration value="MS"/>
 *     &lt;enumeration value="MO"/>
 *     &lt;enumeration value="MT"/>
 *     &lt;enumeration value="NE"/>
 *     &lt;enumeration value="NV"/>
 *     &lt;enumeration value="NH"/>
 *     &lt;enumeration value="NJ"/>
 *     &lt;enumeration value="NM"/>
 *     &lt;enumeration value="NY"/>
 *     &lt;enumeration value="NC"/>
 *     &lt;enumeration value="ND"/>
 *     &lt;enumeration value="MP"/>
 *     &lt;enumeration value="OH"/>
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="OR"/>
 *     &lt;enumeration value="PA"/>
 *     &lt;enumeration value="PR"/>
 *     &lt;enumeration value="RI"/>
 *     &lt;enumeration value="SC"/>
 *     &lt;enumeration value="SD"/>
 *     &lt;enumeration value="TN"/>
 *     &lt;enumeration value="TX"/>
 *     &lt;enumeration value="UT"/>
 *     &lt;enumeration value="VT"/>
 *     &lt;enumeration value="VA"/>
 *     &lt;enumeration value="VI"/>
 *     &lt;enumeration value="WA"/>
 *     &lt;enumeration value="WV"/>
 *     &lt;enumeration value="WI"/>
 *     &lt;enumeration value="WY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stateCode")
@XmlEnum
public enum StateCode {

    @XmlEnumValue("Outside_US")
    OUTSIDE_US("Outside_US"),
    AL("AL"),
    AK("AK"),
    AS("AS"),
    AZ("AZ"),
    AR("AR"),
    CA("CA"),
    CO("CO"),
    CT("CT"),
    DE("DE"),
    DC("DC"),
    FM("FM"),
    FL("FL"),
    GA("GA"),
    GU("GU"),
    HI("HI"),
    ID("ID"),
    IL("IL"),
    IN("IN"),
    IA("IA"),
    KS("KS"),
    KY("KY"),
    LA("LA"),
    ME("ME"),
    MH("MH"),
    MD("MD"),
    MA("MA"),
    MI("MI"),
    MN("MN"),
    MS("MS"),
    MO("MO"),
    MT("MT"),
    NE("NE"),
    NV("NV"),
    NH("NH"),
    NJ("NJ"),
    NM("NM"),
    NY("NY"),
    NC("NC"),
    ND("ND"),
    MP("MP"),
    OH("OH"),
    OK("OK"),
    OR("OR"),
    PA("PA"),
    PR("PR"),
    RI("RI"),
    SC("SC"),
    SD("SD"),
    TN("TN"),
    TX("TX"),
    UT("UT"),
    VT("VT"),
    VA("VA"),
    VI("VI"),
    WA("WA"),
    WV("WV"),
    WI("WI"),
    WY("WY");
    private final String value;

    StateCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StateCode fromValue(String v) {
        for (StateCode c: StateCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
