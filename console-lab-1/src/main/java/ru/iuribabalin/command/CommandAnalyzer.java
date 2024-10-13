package ru.iuribabalin.command;

import ru.iuribabalin.command.impl.ExitCommandImpl;
import ru.iuribabalin.command.impl.HelpCommandImpl;
import ru.iuribabalin.command.impl.SearchCommandImpl;

import java.util.*;

public class CommandAnalyzer {

    private final Map<Command, CommandHandler> commandHandlers = new HashMap<>();

    public CommandAnalyzer() {
        SearchCommandImpl searchCommand = new SearchCommandImpl();
        HelpCommandImpl helpCommand = new HelpCommandImpl();
        ExitCommandImpl exitCommand = new ExitCommandImpl();
        commandHandlers.put(searchCommand.getName(), searchCommand);
        commandHandlers.put(helpCommand.getName(), helpCommand);
        commandHandlers.put(exitCommand.getName(), exitCommand);
    }

    public Optional<CommandHandler> handler(String command) {
        Optional<Command> commandExecute = Arrays.stream(Command.values())
                .filter(it -> it.getCommandName().equals(command)).findFirst();
        return commandExecute.flatMap(this::findCommandHandler);
    }

    private Optional<CommandHandler> findCommandHandler(Command command) {
        return Optional.ofNullable(commandHandlers.get(command));
    }
}
