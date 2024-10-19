package ru.iuribabalin.command.impl.rest;


import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.WebClientImpl;
import ru.iuribabalin.client.model.EmployeeResponseDto;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public class UpdateCommandRestImpl implements CommandHandler {

    private final WebClientImpl client;

    public UpdateCommandRestImpl(WebClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        EmployeeResponseDto response =
                client.updateEmployee(employee.getId(), EmployeeMapper.mapToRestRequestCreate(employee));
        System.out.println(EmployeeMapper.mapToString(response));
    }

    @Override
    public Command getName() {
        return Command.UPDATE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
