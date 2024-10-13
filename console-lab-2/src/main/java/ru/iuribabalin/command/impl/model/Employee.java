package ru.iuribabalin.command.impl.model;

import lombok.Builder;
import lombok.Data;
import ru.iuribabalin.app.Department;
import ru.iuribabalin.app.Position;

@Data
@Builder
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
    private Department department;
    private String hireDate;
}
