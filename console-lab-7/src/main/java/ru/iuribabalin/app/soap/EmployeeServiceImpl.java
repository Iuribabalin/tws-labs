
package ru.iuribabalin.app.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import ru.iuribabalin.app.EmployeeSearchResponseType;
import ru.iuribabalin.app.model.EmployeeRequest;
import ru.iuribabalin.app.model.EmployeeResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmployeeServiceImpl", targetNamespace = "http://soap.app.iuribabalin.ru/")
@XmlSeeAlso({
    ru.iuribabalin.app.ObjectFactory.class,
    ru.iuribabalin.app.model.ObjectFactory.class,
    ru.iuribabalin.app.soap.ObjectFactory.class
})
public interface EmployeeServiceImpl {


    /**
     * 
     * @param request
     * @param id
     * @return
     *     returns ru.iuribabalin.app.model.EmployeeResponse
     * @throws EmployeeServiceException_Exception
     */
    @WebMethod
    @WebResult(name = "EmployeeResponse", targetNamespace = "http://app.iuribabalin.ru/")
    @RequestWrapper(localName = "update", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.UpdateResponse")
    @Action(input = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/updateRequest", output = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/updateResponse", fault = {
        @FaultAction(className = EmployeeServiceException_Exception.class, value = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/update/Fault/EmployeeServiceException")
    })
    public EmployeeResponse update(
        @WebParam(name = "id", targetNamespace = "")
        Long id,
        @WebParam(name = "request", targetNamespace = "")
        EmployeeRequest request)
        throws EmployeeServiceException_Exception
    ;

    /**
     * 
     * @param id
     * @throws EmployeeServiceException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.DeleteResponse")
    @Action(input = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/deleteRequest", output = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/deleteResponse", fault = {
        @FaultAction(className = EmployeeServiceException_Exception.class, value = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/delete/Fault/EmployeeServiceException")
    })
    public void delete(
        @WebParam(name = "id", targetNamespace = "")
        Long id)
        throws EmployeeServiceException_Exception
    ;

    /**
     * 
     * @param request
     * @return
     *     returns ru.iuribabalin.app.model.EmployeeResponse
     * @throws EmployeeServiceException_Exception
     */
    @WebMethod
    @WebResult(name = "EmployeeResponse", targetNamespace = "http://app.iuribabalin.ru/")
    @RequestWrapper(localName = "create", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.CreateResponse")
    @Action(input = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/createRequest", output = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/createResponse", fault = {
        @FaultAction(className = EmployeeServiceException_Exception.class, value = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/create/Fault/EmployeeServiceException")
    })
    public EmployeeResponse create(
        @WebParam(name = "request", targetNamespace = "")
        EmployeeRequest request)
        throws EmployeeServiceException_Exception
    ;

    /**
     * 
     * @param firstName
     * @param lastName
     * @param data
     * @param position
     * @param department
     * @return
     *     returns ru.iuribabalin.app.EmployeeSearchResponseType
     * @throws EmployeeServiceException_Exception
     */
    @WebMethod
    @WebResult(name = "EmployeeSearchResponse", targetNamespace = "http://app.iuribabalin.ru/")
    @RequestWrapper(localName = "searchEmployees", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.SearchEmployees")
    @ResponseWrapper(localName = "searchEmployeesResponse", targetNamespace = "http://soap.app.iuribabalin.ru/", className = "ru.iuribabalin.app.soap.SearchEmployeesResponse")
    @Action(input = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/searchEmployeesRequest", output = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/searchEmployeesResponse", fault = {
        @FaultAction(className = EmployeeServiceException_Exception.class, value = "http://soap.app.iuribabalin.ru/EmployeeServiceImpl/searchEmployees/Fault/EmployeeServiceException")
    })
    public EmployeeSearchResponseType searchEmployees(
        @WebParam(name = "firstName", targetNamespace = "")
        String firstName,
        @WebParam(name = "lastName", targetNamespace = "")
        String lastName,
        @WebParam(name = "position", targetNamespace = "")
        Position position,
        @WebParam(name = "department", targetNamespace = "")
        Department department,
        @WebParam(name = "data", targetNamespace = "")
        String data)
        throws EmployeeServiceException_Exception
    ;

}
