package ru.iuribabalin.model.soap;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import ru.iuribabalin.model.Department;
import ru.iuribabalin.model.Position;

@Data
@XmlRootElement(name = "searchEmployeesRequest")
@XmlType(name = "SearchEmployeesRequest", namespace = "http://app.iuribabalin.ru/")
public class SearchEmployeesRequest {
    private String firstName;
    private String lastName;
    private Position position;
    private Department department;

    public String toString(){
        return "First Name: " + firstName + " Last Name: " + lastName + " Position: " + position;
    }
}
