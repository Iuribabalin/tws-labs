
package ru.iuribabalin.app;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ru.iuribabalin.app.model.EmployeeResponse;


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

    private final static QName _EmployeeResponse_QNAME = new QName("http://app.iuribabalin.ru/", "EmployeeResponse");
    private final static QName _EmployeeSearchResponse_QNAME = new QName("http://app.iuribabalin.ru/", "EmployeeSearchResponse");

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
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmployeeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://app.iuribabalin.ru/", name = "EmployeeResponse")
    public JAXBElement<EmployeeResponse> createEmployeeResponse(EmployeeResponse value) {
        return new JAXBElement<EmployeeResponse>(_EmployeeResponse_QNAME, EmployeeResponse.class, null, value);
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

}
