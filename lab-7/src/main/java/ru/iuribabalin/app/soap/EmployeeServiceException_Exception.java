
package ru.iuribabalin.app.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EmployeeServiceException", targetNamespace = "http://soap.app.iuribabalin.ru/")
public class EmployeeServiceException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EmployeeServiceException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public EmployeeServiceException_Exception(String message, EmployeeServiceException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public EmployeeServiceException_Exception(String message, EmployeeServiceException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ru.iuribabalin.app.soap.EmployeeServiceException
     */
    public EmployeeServiceException getFaultInfo() {
        return faultInfo;
    }

}
