package ru.iuribabalin.command.impl;

import lombok.SneakyThrows;
import ru.iuribabalin.app.EmployeeServiceImpl;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.util.Map;

public class DeleteCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public DeleteCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @SneakyThrows
    @Override
    public void execute(Map<Key, String> params) {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        if(employee.getId() != null){
            employeeService.delete(employee.getId());
        } else {
            throw new RuntimeException("Id is null");
        }
    }

    @Override
    public Command getName() {
        return Command.DELETE;
    }
}
