package ru.iuribabalin.command.impl.soap;

import ru.iuribabalin.app.model.EmployeeResponse;
import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.app.soap.EmployeeServiceImpl;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;

import java.text.ParseException;
import java.util.Map;

public class CreateCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public CreateCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException {
        EmployeeResponse employeeResponse =
                employeeService.create(EmployeeMapper.mapToRequestCreate(EmployeeMapper.mapKeysToEmployee(params)));
        System.out.println(EmployeeMapper.mapToString(employeeResponse));
    }

    @Override
    public Command getName() {
        return Command.CREATE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.SOAP;
    }
}
