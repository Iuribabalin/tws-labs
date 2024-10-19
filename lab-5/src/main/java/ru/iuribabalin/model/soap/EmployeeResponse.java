package ru.iuribabalin.model.soap;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
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
@XmlRootElement(name = "EmployeeResponse")
@XmlType(name = "EmployeeResponse", namespace = "http://app.iuribabalin.ru/model")
public class EmployeeResponse {
    private int id;
    private String firstName;
    private String lastName;
    private Position position;
    private Department department;
    private String hireDate;
}
