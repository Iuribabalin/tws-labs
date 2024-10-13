package ru.iuribabalin.command.impl.command.impl;

import lombok.SneakyThrows;
import ru.iuribabalin.app.EmployeeServiceImpl;
import ru.iuribabalin.app.model.EmployeeResponse;
import ru.iuribabalin.command.impl.command.Command;
import ru.iuribabalin.command.impl.command.CommandHandler;
import ru.iuribabalin.command.impl.command.Key;
import ru.iuribabalin.command.impl.command.mapper.EmployeeMapper;

import java.util.Map;

public class CreateCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public CreateCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @SneakyThrows
    @Override
    public void execute(Map<Key, String> params) {
        EmployeeResponse employeeResponse =
                employeeService.create(EmployeeMapper.mapToRequestCreate(EmployeeMapper.mapKeysToEmployee(params)));
        System.out.println(EmployeeMapper.mapToString(employeeResponse));
    }

    @Override
    public Command getName() {
        return Command.CREATE;
    }
}
