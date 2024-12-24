package ru.iuribabalin.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Key {
    PROTOCOL(List.of(Command.PROTOCOL), "-p", "--protocol", "-p --protocol\t\t->\tключь в котором нужно указать REST или SOAP"),
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
