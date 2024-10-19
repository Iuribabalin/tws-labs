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
@XmlRootElement(name = "EmployeeRequest")
@XmlType(name = "EmployeeRequest", namespace = "http://app.iuribabalin.ru/model")
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private Position position;
    private Department department;
    private String hireDate;

    public String toQueryStringUpdate() {
        StringBuilder stringBuilder = new StringBuilder();

        if (setField(firstName)) {
            stringBuilder.append("first_name = '").append(firstName).append("', ");
        }
        if (setField(lastName)) {
            stringBuilder.append("last_name = '").append(lastName).append("', ");
        }
        if (setField(position)) {
            stringBuilder.append("position = '").append(position).append("', ");
        }
        if (setField(department)) {
            stringBuilder.append("department = '").append(department).append("', ");
        }
        if (setField(hireDate)) {
            stringBuilder.append("hire_date = '").append(hireDate).append("', ");
        }

        if (!stringBuilder.isEmpty()) {
            stringBuilder.setLength(stringBuilder.length() - 2);  // убираем последние ", "
        }

        return stringBuilder.toString();
    }


    private boolean setField(Object field) {
        if (field != null) {
            if (field instanceof String) {
                return !((String) field).isEmpty();
            } else {
                return true;
            }
        }
        return false;
    }

}
