package ru.iuribabalin.command.impl.command.impl;

import lombok.SneakyThrows;
import ru.iuribabalin.app.EmployeeServiceImpl;
import ru.iuribabalin.command.impl.command.Command;
import ru.iuribabalin.command.impl.command.CommandHandler;
import ru.iuribabalin.command.impl.command.Key;
import ru.iuribabalin.command.impl.command.mapper.EmployeeMapper;
import ru.iuribabalin.command.impl.model.Employee;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCommandImpl implements CommandHandler {
    private final EmployeeServiceImpl employeeService;

    public SearchCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @SneakyThrows
    @Override
    public void execute(Map<Key, String> params) {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        String employees = Optional.ofNullable(
                        employeeService.searchEmployees(employee.getFirstName(), employee.getLastName(),
                                employee.getPosition(), employee.getDepartment(), employee.getHireDate()
                        ).getResponseModelList()
                ).stream()
                .flatMap(Collection::stream)
                .map(EmployeeMapper::mapToString)
                .collect(Collectors.joining("\n"));
        if (employees.isEmpty()) {
            System.out.println("Нет сотрудников, соответствующих заданным параметрам.");
        } else {
            System.out.println(employees);
        }

    }

    @Override
    public Command getName() {
        return Command.SEARCH;
    }
}
