package ru.iuribabalin.command.impl.soap;

import ru.iuribabalin.app.model.EmployeeResponse;
import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.app.soap.EmployeeServiceImpl;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.text.ParseException;
import java.util.Map;


public class UpdateCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public UpdateCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        EmployeeResponse response =
                employeeService.update(employee.getId(), EmployeeMapper.mapToRequestCreate(employee));
        System.out.println(EmployeeMapper.mapToString(response));
    }

    @Override
    public Command getName() {
        return Command.UPDATE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.SOAP;
    }
}
