
package ru.iuribabalin.app.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.iuribabalin.app.EmployeeSearchResponseType;


/**
 * &lt;p&gt;Java class for searchEmployeesResponse complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="searchEmployeesResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element ref="{http://app.iuribabalin.ru/}EmployeeSearchResponse" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchEmployeesResponse", propOrder = {
    "employeeSearchResponse"
})
public class SearchEmployeesResponse {

    @XmlElement(name = "EmployeeSearchResponse", namespace = "http://app.iuribabalin.ru/")
    protected EmployeeSearchResponseType employeeSearchResponse;

    /**
     * Gets the value of the employeeSearchResponse property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeSearchResponseType }
     *     
     */
    public EmployeeSearchResponseType getEmployeeSearchResponse() {
        return employeeSearchResponse;
    }

    /**
     * Sets the value of the employeeSearchResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeSearchResponseType }
     *     
     */
    public void setEmployeeSearchResponse(EmployeeSearchResponseType value) {
        this.employeeSearchResponse = value;
    }

}
