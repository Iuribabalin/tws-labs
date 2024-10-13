
package ru.iuribabalin.app;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.iuribabalin.app package. 
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

    private final static QName _EmployeeSearchResponse_QNAME = new QName("http://app.iuribabalin.ru/", "EmployeeSearchResponse");
    private final static QName _ParseException_QNAME = new QName("http://app.iuribabalin.ru/", "ParseException");
    private final static QName _SearchEmployees_QNAME = new QName("http://app.iuribabalin.ru/", "searchEmployees");
    private final static QName _SearchEmployeesResponse_QNAME = new QName("http://app.iuribabalin.ru/", "searchEmployeesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.iuribabalin.app
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmployeeSearchResponseType }
     * 
     */
    public EmployeeSearchResponseType createEmployeeSearchResponseType() {
        return new EmployeeSearchResponseType();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeSearchResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmployeeSearchResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://app.iuribabalin.ru/", name = "EmployeeSearchResponse")
    public JAXBElement<EmployeeSearchResponseType> createEmployeeSearchResponse(EmployeeSearchResponseType value) {
        return new JAXBElement<EmployeeSearchResponseType>(_EmployeeSearchResponse_QNAME, EmployeeSearchResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}
     */
    @XmlElementDecl(namespace = "http://app.iuribabalin.ru/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEmployees }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchEmployees }{@code >}
     */
    @XmlElementDecl(namespace = "http://app.iuribabalin.ru/", name = "searchEmployees")
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
    @XmlElementDecl(namespace = "http://app.iuribabalin.ru/", name = "searchEmployeesResponse")
    public JAXBElement<SearchEmployeesResponse> createSearchEmployeesResponse(SearchEmployeesResponse value) {
        return new JAXBElement<SearchEmployeesResponse>(_SearchEmployeesResponse_QNAME, SearchEmployeesResponse.class, null, value);
    }

}
