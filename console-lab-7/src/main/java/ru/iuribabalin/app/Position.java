
package ru.iuribabalin.app;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for position.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="position"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="SOFTWARE_ENGINEER"/&amp;gt;
 *     &amp;lt;enumeration value="SOFTWARE_TESTER"/&amp;gt;
 *     &amp;lt;enumeration value="PRODUCT_MANAGER"/&amp;gt;
 *     &amp;lt;enumeration value="QA_ENGINEER"/&amp;gt;
 *     &amp;lt;enumeration value="DATA_SCIENTIST"/&amp;gt;
 *     &amp;lt;enumeration value="DEVOPS_ENGINEER"/&amp;gt;
 *     &amp;lt;enumeration value="HR_MANAGER"/&amp;gt;
 *     &amp;lt;enumeration value="UI_UX_DESIGNER"/&amp;gt;
 *     &amp;lt;enumeration value="CONTENT_WRITER"/&amp;gt;
 *     &amp;lt;enumeration value="NETWORK_ENGINEER"/&amp;gt;
 *     &amp;lt;enumeration value="SCRUM_MASTER"/&amp;gt;
 *     &amp;lt;enumeration value="BUSINESS_ANALYST"/&amp;gt;
 *     &amp;lt;enumeration value="DATA_ANALYST"/&amp;gt;
 *     &amp;lt;enumeration value="SOFTWARE_ARCHITECT"/&amp;gt;
 *     &amp;lt;enumeration value="OFFICE_MANAGER"/&amp;gt;
 *     &amp;lt;enumeration value="DATABASE_ADMINISTRATOR"/&amp;gt;
 *     &amp;lt;enumeration value="MARKETING_COORDINATOR"/&amp;gt;
 *     &amp;lt;enumeration value="CHIEF_TECHNOLOGY_OFFICER"/&amp;gt;
 *     &amp;lt;enumeration value="CUSTOMER_SUPPORT"/&amp;gt;
 *     &amp;lt;enumeration value="FINANCIAL_ANALYST"/&amp;gt;
 *     &amp;lt;enumeration value="OPERATIONS_MANAGER"/&amp;gt;
 *     &amp;lt;enumeration value="LEAD_DEVELOPER"/&amp;gt;
 *     &amp;lt;enumeration value="PROJECT_MANAGER"/&amp;gt;
 *     &amp;lt;enumeration value="SECURITY_SPECIALIST"/&amp;gt;
 *     &amp;lt;enumeration value="CONTENT_MANAGER"/&amp;gt;
 *     &amp;lt;enumeration value="NETWORK_ADMINISTRATOR"/&amp;gt;
 *     &amp;lt;enumeration value="SYSTEM_ADMINISTRATOR"/&amp;gt;
 *     &amp;lt;enumeration value="TECHNICAL_WRITER"/&amp;gt;
 *     &amp;lt;enumeration value="UX_RESEARCHER"/&amp;gt;
 *     &amp;lt;enumeration value="IT_SUPPORT_SPECIALIST"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "position")
@XmlEnum
public enum Position {

    SOFTWARE_ENGINEER,
    SOFTWARE_TESTER,
    PRODUCT_MANAGER,
    QA_ENGINEER,
    DATA_SCIENTIST,
    DEVOPS_ENGINEER,
    HR_MANAGER,
    UI_UX_DESIGNER,
    CONTENT_WRITER,
    NETWORK_ENGINEER,
    SCRUM_MASTER,
    BUSINESS_ANALYST,
    DATA_ANALYST,
    SOFTWARE_ARCHITECT,
    OFFICE_MANAGER,
    DATABASE_ADMINISTRATOR,
    MARKETING_COORDINATOR,
    CHIEF_TECHNOLOGY_OFFICER,
    CUSTOMER_SUPPORT,
    FINANCIAL_ANALYST,
    OPERATIONS_MANAGER,
    LEAD_DEVELOPER,
    PROJECT_MANAGER,
    SECURITY_SPECIALIST,
    CONTENT_MANAGER,
    NETWORK_ADMINISTRATOR,
    SYSTEM_ADMINISTRATOR,
    TECHNICAL_WRITER,
    UX_RESEARCHER,
    IT_SUPPORT_SPECIALIST;

    public String value() {
        return name();
    }

    public static Position fromValue(String v) {
        return valueOf(v);
    }

}
