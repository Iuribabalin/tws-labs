package ru.iuribabalin.command.impl.juddi;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

import static ru.iuribabalin.Main.findServices;

public class SelectService implements CommandHandler {
    @Override
    public void execute(Map<Key, String> params) throws ParseException, URISyntaxException, IOException, InterruptedException, EmployeeServiceException_Exception, ClientException {
        int index = Integer.parseInt(params.get(Key.SELECT_SERVICE));
        findServices.selectService(index);
    }

    @Override
    public Command getName() {
        return Command.SELECT_SERVICE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.NO;
    }
}
