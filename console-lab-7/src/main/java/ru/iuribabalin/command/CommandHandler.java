package ru.iuribabalin.command;


import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.exception.ClientException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public interface CommandHandler {
    void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException, ru.iuribabalin.app.soap.EmployeeServiceException_Exception, ClientException;

    Command getName();

    Protocol getProtocol();
}
