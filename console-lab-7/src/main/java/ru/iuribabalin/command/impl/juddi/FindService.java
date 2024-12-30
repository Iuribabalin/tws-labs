package ru.iuribabalin.command.impl.juddi;

import lombok.SneakyThrows;
import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.impl.juddi.connect.JuddiConnect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

import static ru.iuribabalin.Main.findServices;

public class FindService implements CommandHandler {

    private final JuddiConnect juddiConnect;

    public FindService() {
        juddiConnect = new JuddiConnect();
    }

    @SneakyThrows
    @Override
    public void execute(Map<Key, String> params) throws ParseException, URISyntaxException, IOException, InterruptedException, EmployeeServiceException_Exception, ClientException {
        findServices.setServices(juddiConnect.getServices(params.get(Key.BUSINESS_KEY)));
        findServices.showServices();
    }

    @Override
    public Command getName() {
        return Command.FIND_SERVICE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.NO;
    }
}
