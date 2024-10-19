package ru.iuribabalin.command;

import ru.iuribabalin.app.EmployeeService;
import ru.iuribabalin.command.impl.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandAnalyzer {

    private final Map<Command, CommandHandler> commandHandlers = new HashMap<>();

    public CommandAnalyzer() {
        EmployeeService service = new EmployeeService();
        SearchCommandImpl searchCommand = new SearchCommandImpl(service.getEmployeeServicePort());
        DeleteCommandImpl deleteCommand = new DeleteCommandImpl(service.getEmployeeServicePort());
        CreateCommandImpl createCommand = new CreateCommandImpl(service.getEmployeeServicePort());
        UpdateCommandImpl updateCommand = new UpdateCommandImpl(service.getEmployeeServicePort());
        HelpCommandImpl helpCommand = new HelpCommandImpl();
        ExitCommandImpl exitCommand = new ExitCommandImpl();
        commandHandlers.put(searchCommand.getName(), searchCommand);
        commandHandlers.put(deleteCommand.getName(), deleteCommand);
        commandHandlers.put(createCommand.getName(), createCommand);
        commandHandlers.put(updateCommand.getName(), updateCommand);
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
