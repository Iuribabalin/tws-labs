package ru.iuribabalin.command.impl.rest;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.WebClientImpl;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.client.model.EmployeeResponseDto;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public class CreateCommandRestImpl implements CommandHandler {

    private final WebClientImpl client;

    public CreateCommandRestImpl(WebClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException, ClientException {
        EmployeeResponseDto employeeResponse =
                client.create(EmployeeMapper.mapToRestRequestCreate(EmployeeMapper.mapKeysToEmployee(params)));
        System.out.println(EmployeeMapper.mapToString(employeeResponse));
    }

    @Override
    public Command getName() {
        return Command.CREATE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
