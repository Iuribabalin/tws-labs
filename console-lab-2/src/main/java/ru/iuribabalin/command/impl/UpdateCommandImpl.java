package ru.iuribabalin.command.impl;

import lombok.SneakyThrows;
import ru.iuribabalin.app.EmployeeServiceImpl;
import ru.iuribabalin.app.model.EmployeeResponse;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.util.Map;

public class UpdateCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public UpdateCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @SneakyThrows
    @Override
    public void execute(Map<Key, String> params) {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        EmployeeResponse response =
                employeeService.update(employee.getId(), EmployeeMapper.mapToRequestCreate(employee));
        System.out.println(EmployeeMapper.mapToString(response));
    }

    @Override
    public Command getName() {
        return Command.UPDATE;
    }
}
