package ru.iuribabalin.command.impl;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.command.*;

import java.text.ParseException;
import java.util.Map;

public class SetProtocol implements CommandHandler {

    private final CommandAnalyzer analyzer;

    public SetProtocol(CommandAnalyzer analyzer) {
        this.analyzer = analyzer;
    }


    @Override
    public void execute(Map<Key, String> params) throws EmployeeServiceException_Exception, ParseException {
        analyzer.setProtocol(Protocol.valueOf(params.get(Key.PROTOCOL).toUpperCase()));
        System.out.println("Protocol update: " + analyzer.getProtocol());
    }

    @Override
    public Command getName() {
        return Command.PROTOCOL;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.NO;
    }
}
