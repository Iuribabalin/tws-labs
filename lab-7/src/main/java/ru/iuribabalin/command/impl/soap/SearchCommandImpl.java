package ru.iuribabalin.command.impl.soap;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.app.soap.EmployeeServiceImpl;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCommandImpl implements CommandHandler {
    private final EmployeeServiceImpl employeeService;

    public SearchCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException {
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

    @Override
    public Protocol getProtocol() {
        return Protocol.SOAP;
    }
}
