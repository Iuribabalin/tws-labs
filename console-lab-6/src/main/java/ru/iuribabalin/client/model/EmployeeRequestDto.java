package ru.iuribabalin.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;
    private Position position;
    private Department department;
    private String hireDate;
}
