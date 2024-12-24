package ru.iuribabalin.model.soap;

import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;
import lombok.Data;
import ru.iuribabalin.model.Department;
import ru.iuribabalin.model.Position;

@Data
@Builder
@XmlType(name = "SearchEmployeesResponseModelType", namespace = "http://app.iuribabalin.ru/model")
public class SearchEmployeesResponseModel {
    private int id;
    private String firstName;
    private String lastName;
    private Position position;
    private Department department;
    private String hireDate;
}
