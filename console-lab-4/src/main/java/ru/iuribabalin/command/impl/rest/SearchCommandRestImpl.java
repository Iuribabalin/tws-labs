package ru.iuribabalin.command.impl.rest;

import ru.iuribabalin.client.WebClientImpl;
import ru.iuribabalin.client.model.Department;
import ru.iuribabalin.client.model.Position;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCommandRestImpl implements CommandHandler {
    private final WebClientImpl client;

    public SearchCommandRestImpl(WebClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute(Map<Key, String> params) throws ParseException, URISyntaxException, IOException, InterruptedException {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        String employees = Optional.ofNullable(
                        client.search(employee.getFirstName(), employee.getLastName(),
                                employee.getPosition() != null
                                        ? Position.valueOf(employee.getPosition().name())
                                        : null,
                                employee.getDepartment() != null
                                        ? Department.valueOf(employee.getDepartment().name())
                                        : null,
                                employee.getHireDate()
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

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
