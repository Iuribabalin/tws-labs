package ru.iuribabalin.command.impl;

import ru.iuribabalin.command.Command;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.Protocol;

import java.util.Map;

public class ExitCommandImpl implements CommandHandler {
    @Override
    public void execute(Map<Key, String> params) {
        System.out.println("Exit console, bye");
        System.exit(0);
    }

    @Override
    public Command getName() {
        return Command.EXIT;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.NO;
    }
}
