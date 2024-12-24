package ru.iuribabalin.command;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.exception.ClientException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public interface CommandHandler {
    void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException, EmployeeServiceException_Exception, ClientException;

    Command getName();

    Protocol getProtocol();

    static Void exceptionHandler(Throwable ex) {
        if (ex.getCause() instanceof ClientException) {
            ClientException clientEx = (ClientException) ex.getCause();
            System.err.println("Ошибка клиента: " + clientEx.getMessage() + ", код: " + clientEx.getCode());
        } else {
            System.err.println("Ошибка при выполнении асинхронного запроса: " + ex.getMessage());
        }
        return null;
    }
}
