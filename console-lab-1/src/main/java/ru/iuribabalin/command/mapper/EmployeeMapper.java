package ru.iuribabalin.command.mapper;

import ru.iuribabalin.app.model.SearchEmployeesResponseModelType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
}
