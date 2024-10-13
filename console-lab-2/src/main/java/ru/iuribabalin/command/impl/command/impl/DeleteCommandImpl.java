package ru.iuribabalin.command.impl.command.impl;

import lombok.SneakyThrows;
import ru.iuribabalin.app.EmployeeServiceImpl;
import ru.iuribabalin.command.impl.command.Command;
import ru.iuribabalin.command.impl.command.CommandHandler;
import ru.iuribabalin.command.impl.command.Key;
import ru.iuribabalin.command.impl.command.mapper.EmployeeMapper;
import ru.iuribabalin.command.impl.model.Employee;

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
