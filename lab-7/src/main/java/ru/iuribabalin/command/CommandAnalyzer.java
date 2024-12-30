package ru.iuribabalin.command;

import ru.iuribabalin.app.soap.EmployeeService;
import ru.iuribabalin.client.WebClientImpl;
import ru.iuribabalin.command.impl.ExitCommandImpl;
import ru.iuribabalin.command.impl.HelpCommandImpl;
import ru.iuribabalin.command.impl.SetProtocol;
import ru.iuribabalin.command.impl.juddi.FindService;
import ru.iuribabalin.command.impl.juddi.Registration;
import ru.iuribabalin.command.impl.juddi.SelectService;
import ru.iuribabalin.command.impl.rest.CreateCommandRestImpl;
import ru.iuribabalin.command.impl.rest.DeleteCommandRestImpl;
import ru.iuribabalin.command.impl.rest.SearchCommandRestImpl;
import ru.iuribabalin.command.impl.rest.UpdateCommandRestImpl;
import ru.iuribabalin.command.impl.soap.CreateCommandImpl;
import ru.iuribabalin.command.impl.soap.DeleteCommandImpl;
import ru.iuribabalin.command.impl.soap.SearchCommandImpl;
import ru.iuribabalin.command.impl.soap.UpdateCommandImpl;

import java.util.*;

public class CommandAnalyzer {

    private static Protocol protocol = Protocol.REST;

    private static WebClientImpl client = new WebClientImpl();

    private final Map<Command, List<CommandHandler>> commandHandlers = new HashMap<>();

    public CommandAnalyzer() {
        EmployeeService service = new EmployeeService();
        SearchCommandImpl searchCommand = new SearchCommandImpl(service.getEmployeeServicePort());
        DeleteCommandImpl deleteCommand = new DeleteCommandImpl(service.getEmployeeServicePort());
        CreateCommandImpl createCommand = new CreateCommandImpl(service.getEmployeeServicePort());
        UpdateCommandImpl updateCommand = new UpdateCommandImpl(service.getEmployeeServicePort());
        SearchCommandRestImpl searchRestCommand = new SearchCommandRestImpl(client);
        DeleteCommandRestImpl deleteRestCommand = new DeleteCommandRestImpl(client);
        CreateCommandRestImpl createRestCommand = new CreateCommandRestImpl(client);
        UpdateCommandRestImpl updateRestCommand = new UpdateCommandRestImpl(client);
        HelpCommandImpl helpCommand = new HelpCommandImpl();
        ExitCommandImpl exitCommand = new ExitCommandImpl();
        FindService findService = new FindService();
        Registration registration = new Registration();
        SelectService selectService = new SelectService();
        //SetProtocol setProtocol = new SetProtocol(this);
        commandHandlers.put(searchCommand.getName(), List.of(searchCommand, searchRestCommand));
        commandHandlers.put(deleteCommand.getName(), List.of(deleteCommand, deleteRestCommand));
        commandHandlers.put(createCommand.getName(), List.of(createCommand, createRestCommand));
        commandHandlers.put(updateCommand.getName(), List.of(updateCommand, updateRestCommand));
        commandHandlers.put(helpCommand.getName(), List.of(helpCommand));
        commandHandlers.put(exitCommand.getName(), List.of(exitCommand));
        commandHandlers.put(findService.getName(), List.of(findService));
        commandHandlers.put(registration.getName(), List.of(registration));
        commandHandlers.put(selectService.getName(), List.of(selectService));
       // commandHandlers.put(setProtocol.getName(), List.of(setProtocol));
    }

    public Optional<CommandHandler> handler(String command) {
        Optional<Command> commandExecute = Arrays.stream(Command.values())
                .filter(it -> it.getCommandName().equals(command)).findFirst();
        return commandExecute.flatMap(this::findCommandHandler);
    }

    public void setProtocol(Protocol protocol) {
        CommandAnalyzer.protocol = protocol;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    private Optional<CommandHandler> findCommandHandler(Command command) {
        return commandHandlers.get(command).stream()
                .filter(it -> it.getProtocol().equals(protocol) || it.getProtocol().equals(Protocol.NO))
                .findFirst();
    }
}
