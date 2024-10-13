package ru.iuribabalin.app.domain;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private Date hireDate;
}
