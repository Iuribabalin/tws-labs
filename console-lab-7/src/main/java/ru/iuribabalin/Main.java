package ru.iuribabalin;

import ru.iuribabalin.app.soap.EmployeeServiceException_Exception;
import ru.iuribabalin.client.exception.ClientException;
import ru.iuribabalin.command.CommandAnalyzer;
import ru.iuribabalin.command.CommandHandler;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.command.impl.juddi.connect.FindServices;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private final static CommandAnalyzer analyzer = new CommandAnalyzer();
    private final static Scanner scanner = new Scanner(System.in);
    public static String connectUrl = "";
    public static FindServices findServices = new FindServices();

    public static void main(String[] args) {
        System.out.println("Start console application with protocol: " + analyzer.getProtocol());
        while (true) {
            String command = scanner.nextLine().trim();
            if (!command.isEmpty()) {
                String commandName = command.split(" ")[0];
                try {
                    Optional<CommandHandler> handler = analyzer.handler(commandName);
                    if (handler.isPresent()) {
                        handler.get().execute(getKeyCommand(command.replace(commandName, "").trim()));
                    } else {
                        throw new RuntimeException("Command " + commandName + " not found");
                    }
                } catch (EmployeeServiceException_Exception soapClientException) {
                    redPrint("status: " + soapClientException.getFaultInfo().getErrorDto().getStatus()
                            + ", message: " + soapClientException.getFaultInfo().getMessage());
                } catch (ClientException clientException) {
                    redPrint("Request status: " + clientException.getCode() + ", message: " + clientException.getMessage());
                } catch (Exception e) {
                    redPrint(e.getMessage());
                }
            }
        }
    }

    public static Map<Key, String> getKeyCommand(String keyString) {
        Map<Key, String> map = new HashMap<>();
        if (keyString.isEmpty()) {
            return map;
        }
        String[] keys = keyString.split(" ");
        for (int i = 0; i < keys.length; i++) {
            Key key = Key.findKey(keys[i]);
            if (key == null) {
                throw new IllegalArgumentException("Key " + keys[i] + " not found!");
            }
            int nextIndex = Stream.of(keyString.split(" "))
                    .collect(Collectors.toList()).indexOf(keys[i]) + 1;
            if (nextIndex < keyString.split(" ").length
                    && !keyString.split(" ")[nextIndex].startsWith("-")
            ) {
                map.put(key, keyString.split(" ")[nextIndex]);
                i += 1;
            } else {
                throw new RuntimeException("Value for key " + keys[i] + " not valid!");
            }
        }
        return map;
    }

    public static void redPrint(String message) {
        System.err.println(message);
    }
}