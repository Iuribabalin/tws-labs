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

public class DeleteCommandImpl implements CommandHandler {

    private final EmployeeServiceImpl employeeService;

    public DeleteCommandImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(() -> {
            try {
                Employee employee = EmployeeMapper.mapKeysToEmployee(params);
                employeeService.delete(employee.getId());
            } catch (EmployeeServiceException_Exception | ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor).exceptionally(ex -> {
            System.err.println("Ошибка при удалении сотрудника: " + ex.getMessage());
            return null;
        }).whenComplete((result, ex) -> {
            executor.shutdown();
            if (ex == null) {
                System.out.println("Удаление сотрудника успешно завершено.");
            }
        });
    }

    @Override
    public Command getName() {
        return Command.DELETE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.SOAP;
    }
}
