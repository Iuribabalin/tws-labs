package ru.iuribabalin.command.impl.juddi;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.impl.juddi.connect.Connection;
import ru.iuribabalin.command.impl.juddi.connect.JuddiConnect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public class Registration implements CommandHandler {

    private final JuddiConnect juddiConnect;

    public Registration() {
        juddiConnect = new JuddiConnect();
    }

    @Override
    public void execute(Map<Key, String> params) throws ParseException, URISyntaxException, IOException, InterruptedException, EmployeeServiceException_Exception, ClientException {
        String businessName = params.get(Key.BUSINESS);
        String serviceName = params.get(Key.SERVICE_NAME);
        String serviceHost = params.get(Key.HOST);
        String servicePort = params.get(Key.PORT);
        Boolean isSecurity = Boolean.parseBoolean(params.get(Key.SECURITY));
        if (businessName.isEmpty() || serviceName.isEmpty() || serviceHost.isEmpty() || servicePort.isEmpty()) {
            throw new ClientException("Missing required parameters");
        }
        int port = Integer.parseInt(servicePort);
        System.out.println(businessName + " " + serviceName + " " + serviceHost + " " + port + " " + isSecurity);
        String businessKey = juddiConnect.createBusiness(businessName);
        System.out.println("Created business: " + businessKey);
        Connection connection = Connection.builder()
                .host(serviceHost)
                .port(port)
                .secure(isSecurity)
                .build();
        String serviceKey = juddiConnect.createService(businessKey, serviceName, connection);
        System.out.println("service was register successfully. Key service: " + serviceKey);
    }

    @Override
    public Command getName() {
        return Command.REGISTRATION;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.NO;
    }
}
