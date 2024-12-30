
package ru.iuribabalin.app.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for department.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="department"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="IT"/&amp;gt;
 *     &amp;lt;enumeration value="MARKETING"/&amp;gt;
 *     &amp;lt;enumeration value="DATA"/&amp;gt;
 *     &amp;lt;enumeration value="HR"/&amp;gt;
 *     &amp;lt;enumeration value="DESIGN"/&amp;gt;
 *     &amp;lt;enumeration value="ADMINISTRATION"/&amp;gt;
 *     &amp;lt;enumeration value="MANAGEMENT"/&amp;gt;
 *     &amp;lt;enumeration value="SUPPORT"/&amp;gt;
 *     &amp;lt;enumeration value="SECURITY"/&amp;gt;
 *     &amp;lt;enumeration value="OPERATIONS"/&amp;gt;
 *     &amp;lt;enumeration value="PROJECTS"/&amp;gt;
 *     &amp;lt;enumeration value="DOCUMENTATION"/&amp;gt;
 *     &amp;lt;enumeration value="FINANCE"/&amp;gt;
 *     &amp;lt;enumeration value="BUSINESS"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "department")
@XmlEnum
public enum Department {

    IT,
    MARKETING,
    DATA,
    HR,
    DESIGN,
    ADMINISTRATION,
    MANAGEMENT,
    SUPPORT,
    SECURITY,
    OPERATIONS,
    PROJECTS,
    DOCUMENTATION,
    FINANCE,
    BUSINESS;

    public String value() {
        return name();
    }

    public static Department fromValue(String v) {
        return valueOf(v);
    }

}
