package ru.iuribabalin.command.impl.rest;

import ru.iuribabalin.client.WebClientImpl;
import ru.iuribabalin.client.model.EmployeeRequestDto;
import ru.iuribabalin.client.model.EmployeeResponseDto;
import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;
import ru.iuribabalin.command.mapper.EmployeeMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CreateCommandRestImpl implements CommandHandler {

    private final WebClientImpl client;

    public CreateCommandRestImpl(WebClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute(Map<Key, String> params) throws ParseException, URISyntaxException, IOException {
        EmployeeRequestDto requestDto = EmployeeMapper.mapToRestRequestCreate(EmployeeMapper.mapKeysToEmployee(params));
        CompletableFuture<EmployeeResponseDto> futureResponse = client.createAsync(requestDto);
        futureResponse.thenAccept(employeeResponse -> {
            System.out.println(EmployeeMapper.mapToString(employeeResponse));
        }).exceptionally(CommandHandler::exceptionHandler);
    }

    @Override
    public Command getName() {
        return Command.CREATE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
