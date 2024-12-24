package ru.iuribabalin.command.impl.soap;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.app.soap.EmployeeServiceImpl;
import ru.iuribabalin.app.model.EmployeeResponse;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public CreateCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture.supplyAsync(() -> {
            try {
                Employee employee = EmployeeMapper.mapKeysToEmployee(params);
                return employeeService.create(EmployeeMapper.mapToRequestCreate(employee));
            } catch (EmployeeServiceException_Exception | ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor).thenAccept(employeeResponse -> {
            System.out.println(EmployeeMapper.mapToString(employeeResponse));
        }).exceptionally(ex -> {
            System.err.println("Ошибка при создании сотрудника: " + ex.getMessage());
            return null;
        }).whenComplete((result, ex) -> {
            executor.shutdown();
        });
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
