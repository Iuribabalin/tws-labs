package ru.iuribabalin.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {
    HELP("help", ""),
    SEARCH("search", "Команда для вызова поиска и обращения к внешнему серверу"),
    EXIT("exit", "Выход из консоли");

    private final String commandName;
    private final String description;
}
