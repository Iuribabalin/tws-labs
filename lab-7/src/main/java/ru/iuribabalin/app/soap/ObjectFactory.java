
package ru.iuribabalin.app.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ru.iuribabalin.app.model.EmployeeRequest;
import ru.iuribabalin.app.model.EmployeeResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.iuribabalin.app.soap package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EmployeeRequest_QNAME = new QName("http://soap.app.iuribabalin.ru/", "EmployeeRequest");
    private final static QName _EmployeeResponse_QNAME = new QName("http://soap.app.iuribabalin.ru/", "EmployeeResponse");
    private final static QName _EmployeeServiceException_QNAME = new QName("http://soap.app.iuribabalin.ru/", "EmployeeServiceException");
    private final static QName _Create_QNAME = new QName("http://soap.app.iuribabalin.ru/", "create");
    private final static QName _CreateResponse_QNAME = new QName("http://soap.app.iuribabalin.ru/", "createResponse");
    private final static QName _Delete_QNAME = new QName("http://soap.app.iuribabalin.ru/", "delete");
    private final static QName _DeleteResponse_QNAME = new QName("http://soap.app.iuribabalin.ru/", "deleteResponse");
    private final static QName _SearchEmployees_QNAME = new QName("http://soap.app.iuribabalin.ru/", "searchEmployees");
    private final static QName _SearchEmployeesResponse_QNAME = new QName("http://soap.app.iuribabalin.ru/", "searchEmployeesResponse");
    private final static QName _Update_QNAME = new QName("http://soap.app.iuribabalin.ru/", "update");
    private final static QName _UpdateResponse_QNAME = new QName("http://soap.app.iuribabalin.ru/", "updateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.iuribabalin.app.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmployeeServiceException }
     * 
     */
    public EmployeeServiceException createEmployeeServiceException() {
        return new EmployeeServiceException();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link SearchEmployees }
     * 
     */
    public SearchEmployees createSearchEmployees() {
        return new SearchEmployees();
    }

    /**
     * Create an instance of {@link SearchEmployeesResponse }
     * 
     */
    public SearchEmployeesResponse createSearchEmployeesResponse() {
        return new SearchEmployeesResponse();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link EmployeeErrorDto }
     * 
     */
    public EmployeeErrorDto createEmployeeErrorDto() {
        return new EmployeeErrorDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmployeeRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "EmployeeRequest")
    public JAXBElement<EmployeeRequest> createEmployeeRequest(EmployeeRequest value) {
        return new JAXBElement<EmployeeRequest>(_EmployeeRequest_QNAME, EmployeeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmployeeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "EmployeeResponse")
    public JAXBElement<EmployeeResponse> createEmployeeResponse(EmployeeResponse value) {
        return new JAXBElement<EmployeeResponse>(_EmployeeResponse_QNAME, EmployeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeServiceException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmployeeServiceException }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "EmployeeServiceException")
    public JAXBElement<EmployeeServiceException> createEmployeeServiceException(EmployeeServiceException value) {
        return new JAXBElement<EmployeeServiceException>(_EmployeeServiceException_QNAME, EmployeeServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Create }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEmployees }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchEmployees }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "searchEmployees")
    public JAXBElement<SearchEmployees> createSearchEmployees(SearchEmployees value) {
        return new JAXBElement<SearchEmployees>(_SearchEmployees_QNAME, SearchEmployees.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEmployeesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchEmployeesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "searchEmployeesResponse")
    public JAXBElement<SearchEmployeesResponse> createSearchEmployeesResponse(SearchEmployeesResponse value) {
        return new JAXBElement<SearchEmployeesResponse>(_SearchEmployeesResponse_QNAME, SearchEmployeesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Update }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.app.iuribabalin.ru/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

}
