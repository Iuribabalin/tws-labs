package ru.iuribabalin.app.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.iuribabalin.model.Department;
import ru.iuribabalin.model.Position;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private int id;
    private String firstName;
    private String lastName;
    private Position position;
    private Department department;
    private String hireDate;
}
