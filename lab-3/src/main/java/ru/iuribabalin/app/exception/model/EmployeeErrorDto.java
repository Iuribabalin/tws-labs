package ru.iuribabalin.app.exception.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeErrorDto {
    private String message;
    private int status;
}
