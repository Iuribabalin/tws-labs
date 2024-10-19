package ru.iuribabalin.command.mapper;

import ru.iuribabalin.app.Department;
import ru.iuribabalin.app.Position;
import ru.iuribabalin.app.model.EmployeeRequest;
import ru.iuribabalin.app.model.EmployeeResponse;
import ru.iuribabalin.app.model.SearchEmployeesResponseModelType;
import ru.iuribabalin.command.Key;
import ru.iuribabalin.model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class EmployeeMapper {

    public static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat DATA_FORMAT_SERVER = new SimpleDateFormat("yyyy-MM-dd");

    public static String mapToString(SearchEmployeesResponseModelType employee) {
        try {
            return "Employee -> " + "\n" +
                    "\t Id: " + employee.getId() + "\n" +
                    "\t Name: " + employee.getFirstName() + "\n" +
                    "\t Last name: " + employee.getLastName() + "\n" +
                    "\t Position: " + employee.getPosition() + "\n" +
                    "\t Department: " + employee.getDepartment() + "\n" +
                    "\t Hire date: " + DATA_FORMAT.format(DATA_FORMAT_SERVER.parse(employee.getHireDate())) + "\n";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String mapToString(EmployeeResponse response) {
        try {
            return "Employee -> " + "\n" +
                    "\t Id: " + response.getId() + "\n" +
                    "\t Name: " + response.getFirstName() + "\n" +
                    "\t Last name: " + response.getLastName() + "\n" +
                    "\t Position: " + response.getPosition() + "\n" +
                    "\t Department: " + response.getDepartment() + "\n" +
                    "\t Hire date: " + DATA_FORMAT.format(DATA_FORMAT_SERVER.parse(response.getHireDate())) + "\n";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Employee mapKeysToEmployee(Map<Key, String> params) throws ParseException {
        return Employee.builder()
                .id(params.get(Key.ID) != null ? Long.parseLong(params.get(Key.ID)) : null)
                .firstName(params.get(Key.FIRST_NAME))
                .lastName(params.get(Key.LAST_NAME))
                .position(params.get(Key.POSITION) != null
                        ? Position.valueOf(params.get(Key.POSITION).toUpperCase())
                        : null
                )
                .department(params.get(Key.DEPARTMENT) != null
                        ? Department.valueOf(params.get(Key.DEPARTMENT).toUpperCase())
                        : null
                )
                .hireDate(params.get(Key.DATE) != null
                        ? EmployeeMapper.DATA_FORMAT_SERVER
                        .format(EmployeeMapper.DATA_FORMAT.parse(params.get(Key.DATE)))
                        : null
                )
                .build();
    }

    public static EmployeeRequest mapToRequestCreate(Employee employee) {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName(employee.getFirstName());
        request.setLastName(employee.getLastName());
        request.setPosition(employee.getPosition());
        request.setDepartment(employee.getDepartment());
        request.setHireDate(employee.getHireDate());
        return request;
    }
}
