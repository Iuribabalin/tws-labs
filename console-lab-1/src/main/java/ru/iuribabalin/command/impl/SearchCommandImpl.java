package ru.iuribabalin.command.impl;

import lombok.SneakyThrows;
import ru.iuribabalin.app.Department;
import ru.iuribabalin.app.EmployeeService;
import ru.iuribabalin.app.EmployeeServiceImpl;
import ru.iuribabalin.app.Position;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.mapper.EmployeeMapper;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCommandImpl implements CommandHandler {
    private final EmployeeServiceImpl employeeService;

    public SearchCommandImpl() {
        EmployeeService service = new EmployeeService();
        employeeService = service.getEmployeeServicePort();
    }

    @SneakyThrows
    @Override
    public void execute(Map<Key, String> params) {
        String employees = Optional.ofNullable(
                        employeeService.searchEmployees(
                                params.get(Key.FIRST_NAME),
                                params.get(Key.LAST_NAME),
                                params.get(Key.POSITION) != null
                                        ? Position.valueOf(params.get(Key.POSITION).toUpperCase())
                                        : null,
                                params.get(Key.DEPARTMENT) != null
                                        ? Department.valueOf(params.get(Key.DEPARTMENT).toUpperCase())
                                        : null,
                                params.get(Key.DATE) != null
                                        ? EmployeeMapper.DATA_FORMAT_SERVER
                                        .format(EmployeeMapper.DATA_FORMAT.parse(params.get(Key.DATE)))
                                        : null
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
