
package ru.iuribabalin.app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.iuribabalin.app.model.EmployeeResponse;


/**
 * &lt;p&gt;Java class for createResponse complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="createResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element ref="{http://app.iuribabalin.ru/}EmployeeResponse" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createResponse", propOrder = {
    "employeeResponse"
})
public class CreateResponse {

    @XmlElement(name = "EmployeeResponse", namespace = "http://app.iuribabalin.ru/")
    protected EmployeeResponse employeeResponse;

    /**
     * Gets the value of the employeeResponse property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeResponse }
     *     
     */
    public EmployeeResponse getEmployeeResponse() {
        return employeeResponse;
    }

    /**
     * Sets the value of the employeeResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeResponse }
     *     
     */
    public void setEmployeeResponse(EmployeeResponse value) {
        this.employeeResponse = value;
    }

}
