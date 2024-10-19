package ru.iuribabalin.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {
    HELP("help", ""),
    SEARCH("search", "Команда для вызова поиска и обращения к внешнему серверу"),
    CREATE("create", "Команда для создания работника"),
    UPDATE("update", "Команда для обновление данных о работнике"),
    DELETE("delete", "Команда для удаления работника"),
    EXIT("exit", "Выход из консоли");

    private final String commandName;
    private final String description;
}
