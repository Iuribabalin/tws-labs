package ru.iuribabalin.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Key {
    //PROTOCOL(List.of(Command.PROTOCOL), "-p", "--protocol", "-p --protocol\t\t->\tключь в котором нужно указать REST или SOAP"),
    BUSINESS(List.of(Command.REGISTRATION), "-bn", "--business-name", "-bn, --business-name\t\tКлюч для передачи имени бизнеса"),
    SERVICE_NAME(List.of(Command.REGISTRATION), "-sn", "--service-name", "-sn, --service-name\t\tКлюч для передачи имени сервиса"),
    HOST(List.of(Command.REGISTRATION), "-h", "--host", "-h, --host\t\tКлюч для передачи хоста сервиса"),
    PORT(List.of(Command.REGISTRATION), "-p", "--port", "-p, --port\t\tКлюч для передачи порта сервиса"),
    SECURITY(List.of(Command.REGISTRATION), "-s", "--security", "-s, --security\t\tКлюч для передачи типа взаисодействия с сервисом, по дефолу http"),
    BUSINESS_KEY(List.of(Command.FIND_SERVICE), "-bk", "--business-key", "-bk, --business-key\t\tКлюч для передачи ключа бизнеса"),
    SELECT_SERVICE(List.of(Command.SELECT_SERVICE), "-ss", "--select-service", "-s, --select-service\t\tКлюч для выбора сервиса из реестра Juddi"),
    ID(List.of(Command.DELETE, Command.UPDATE), "-id", "--id","-id, --id        \t\t->\tКлючь указывает id работника"),
    FIRST_NAME(List.of(Command.SEARCH, Command.CREATE, Command.UPDATE), "-fn", "--first-name", "-fn, --first-name\t\t->\tКлюч для передачи имени"),
    LAST_NAME(List.of(Command.SEARCH, Command.CREATE, Command.UPDATE), "-ln", "--last-name", "-ln, --last-name\t\t->\tКлюч для передачи фамилии"),
    POSITION(List.of(Command.SEARCH, Command.CREATE, Command.UPDATE), "-pos", "--position", "-pos, --position\t\t->\tКлюч для передачи позиции"),
    DEPARTMENT(List.of(Command.SEARCH, Command.CREATE, Command.UPDATE), "-dep", "--department", "-dep, --department\t\t->\tКлюч для передачи департамента"),
    DATE(List.of(Command.SEARCH, Command.CREATE, Command.UPDATE), "-d", "--date", "-d, --date        \t\t->\tКлюч для передачи даты"),;

    private final List<Command> commands;
    private final String key;
    private final String fullKey;
    private final String description;

    public static Key findKey(String key) {
        return Arrays.stream(Key.values()).filter(k -> k.key.equals(key) || k.getFullKey().equals(key))
                .findFirst().orElse(null);
    }
}
