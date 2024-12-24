package ru.iuribabalin.command.impl.rest;

import ru.iuribabalin.client.WebClientImpl;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;
import ru.iuribabalin.model.Employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DeleteCommandRestImpl implements CommandHandler {

    private final WebClientImpl client;

    public DeleteCommandRestImpl(WebClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute(Map<Key, String> params) throws ParseException, URISyntaxException, IOException {
        Employee employee = EmployeeMapper.mapKeysToEmployee(params);
        if (employee.getId() == null) {
            throw new IllegalArgumentException("ID сотрудника не может быть null");
        }
        CompletableFuture<Void> futureResponse = client.deleteEmployeeAsync(employee.getId());
        futureResponse.thenRun(() -> {
            System.out.println("Сотрудник с ID " + employee.getId() + " успешно удален.");
        }).exceptionally(CommandHandler::exceptionHandler);
    }

    @Override
    public Command getName() {
        return Command.DELETE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
