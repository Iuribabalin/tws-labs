package ru.iuribabalin.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Key {
    FIRST_NAME(Command.SEARCH, "-fn", "--first-name", "-fn, --first-name\t\t->\tДобавление поля поиска по имени"),
    LAST_NAME(Command.SEARCH, "-ln", "--last-name", "-ln, --last-name\t\t->\tДобавление поля поиска по фамилии"),
    POSITION(Command.SEARCH, "-pos", "--position", "-pos, --position\t\t->\tДобавление поля поиска по позиции"),
    DEPARTMENT(Command.SEARCH, "-dep", "--department", "-dep, --department\t\t->\tДобавление поля поиска по департаменту"),
    DATE(Command.SEARCH, "-d", "--date", "-d, --date        \t\t->\tДобавление поля поиска по дате");

    private final Command command;
    private final String key;
    private final String fullKey;
    private final String description;

    public static Key findKey(String key) {
        return Arrays.stream(Key.values()).filter(k -> k.key.equals(key) || k.getFullKey().equals(key))
                .findFirst().orElse(null);
    }
}
