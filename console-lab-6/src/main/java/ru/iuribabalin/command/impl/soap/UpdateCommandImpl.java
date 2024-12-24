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
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public UpdateCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture.supplyAsync(() -> {
            try {
                Employee employee = EmployeeMapper.mapKeysToEmployee(params);
                return employeeService.update(employee.getId(), EmployeeMapper.mapToRequestCreate(employee));
            } catch (EmployeeServiceException_Exception | ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor).thenAccept(response -> {
            System.out.println(EmployeeMapper.mapToString(response));
        }).exceptionally(ex -> {
            System.err.println("Ошибка при обновлении сотрудника: " + ex.getMessage());
            return null;
        }).whenComplete((result, ex) -> executor.shutdown());
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
