package ru.iuribabalin.model;

import lombok.Builder;
import lombok.Data;
import ru.iuribabalin.app.soap.Department;
import ru.iuribabalin.app.soap.Position;

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
