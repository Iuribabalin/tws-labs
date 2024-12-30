package ru.iuribabalin.command.impl.juddi.connect;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

import java.util.ArrayList;
import java.util.List;
//reg -bn TVS -sn TVS_SERVICE -h localhost -p 9091 -s false
@Getter
public class JuddiConnect {

    @SneakyThrows
    public String createBusiness(String name) {
        UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
        Transport transport = uddiClient.getTransport("default");
        GetAuthToken getAuthToken = new GetAuthToken();
        getAuthToken.setUserID("uddiadmin");
        getAuthToken.setCred("da_password1");
        UDDISecurityPortType security = transport.getUDDISecurityService();
        AuthToken authToken = security.getAuthToken(getAuthToken);
        String authTokenValue = authToken.getAuthInfo();
        UDDIPublicationPortType publish = transport.getUDDIPublishService();
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.getName().add(new Name(name, null));
        SaveBusiness saveBusiness = new SaveBusiness();
        saveBusiness.getBusinessEntity().add(businessEntity);
        saveBusiness.setAuthInfo(authTokenValue);
        BusinessDetail businessDetail = publish.saveBusiness(saveBusiness);
        return businessDetail.getBusinessEntity().get(0).getBusinessKey();
    }

    @SneakyThrows
    public String createService(String businessKey, String name, Connection connection) {
        UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
        Transport transport = uddiClient.getTransport("default");
        GetAuthToken getAuthToken = new GetAuthToken();
        getAuthToken.setUserID("uddiadmin");
        getAuthToken.setCred("da_password1");
        UDDISecurityPortType security = transport.getUDDISecurityService();
        AuthToken authToken = security.getAuthToken(getAuthToken);
        String authTokenValue = authToken.getAuthInfo();
        UDDIPublicationPortType publish = transport.getUDDIPublishService();
        BusinessService businessService = new BusinessService();
        businessService.setBusinessKey(businessKey);
        businessService.getName().add(new Name(name, null));
        BindingTemplate bindingTemplate = new BindingTemplate();
        AccessPoint accessPoint = new AccessPoint();
        accessPoint.setUseType(AccessPointType.END_POINT.toString());
        accessPoint.setValue(connection.getConnectUrl());
        bindingTemplate.setAccessPoint(accessPoint);
        BindingTemplates bindingTemplates = new BindingTemplates();
        bindingTemplates.getBindingTemplate().add(bindingTemplate);
        businessService.setBindingTemplates(bindingTemplates);
        SaveService saveService = new SaveService();
        saveService.getBusinessService().add(businessService);
        saveService.setAuthInfo(authTokenValue);
        ServiceDetail serviceDetail = publish.saveService(saveService);
        return serviceDetail.getBusinessService().get(0).getServiceKey();
    }

    public List<Pair<String, String>> getServices(String businessKey) throws Exception {
        UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
        Transport transport = uddiClient.getTransport("default");
        GetAuthToken getAuthToken = new GetAuthToken();
        getAuthToken.setUserID("uddiadmin");
        getAuthToken.setCred("da_password1");
        UDDIInquiryPortType inquiry = transport.getUDDIInquiryService();
        List<Pair<String, String>> services = new ArrayList<>();
        FindService findService = new FindService();
        findService.setBusinessKey(businessKey);
        ServiceList serviceList = inquiry.findService(findService);
        for (ServiceInfo serviceInfo : serviceList.getServiceInfos().getServiceInfo()) {
            GetServiceDetail getServiceDetail = new GetServiceDetail();
            getServiceDetail.getServiceKey().add(serviceInfo.getServiceKey());
            ServiceDetail serviceDetail = inquiry.getServiceDetail(getServiceDetail);
            for (BusinessService service : serviceDetail.getBusinessService()) {
                String serviceName = service.getName().get(0).getValue();

                if (service.getBindingTemplates() != null) {
                    for (BindingTemplate binding : service.getBindingTemplates().getBindingTemplate()) {
                        String url = binding.getAccessPoint().getValue();
                        services.add(org.apache.commons.lang3.tuple.Pair.of(serviceName, url));
                    }
                }
            }
        }
        return services;
    }
}
