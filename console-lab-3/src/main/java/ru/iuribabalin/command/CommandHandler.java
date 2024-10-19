package ru.iuribabalin.command;

import ru.iuribabalin.app.EmployeeServiceException_Exception;

import java.util.Map;

public interface CommandHandler {
    void execute(Map<Key, String> params) throws EmployeeServiceException_Exception;

    Command getName();
}
