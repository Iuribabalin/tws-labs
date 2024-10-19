package ru.iuribabalin.command.impl.rest;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.WebClientImpl;
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

public class DeleteCommandRestImpl implements CommandHandler {

    private final WebClientImpl client;

    public DeleteCommandRestImpl(WebClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        client.deleteEmployee(employee.getId());
    }

    @Override
    public Command getName() {
        return Command.DELETE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
