package ru.iuribabalin.app.rest;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import ru.iuribabalin.app.domain.DatabaseExecutor;
import ru.iuribabalin.app.domain.Employee;
import ru.iuribabalin.app.rest.model.EmployeeResponseDto;
import ru.iuribabalin.app.rest.model.EmployeeSearchResponseDto;
import ru.iuribabalin.app.soap.exception.EmployeeServiceException;
import ru.iuribabalin.model.Department;
import ru.iuribabalin.model.Position;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeRestService {

    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final DatabaseExecutor databaseExecutor;

    @Inject
    public EmployeeRestService(ServletContext context) {
        this.databaseExecutor = new DatabaseExecutor((DataSource) context.getAttribute("dataSource"));
    }

    @GET
    @Path("/search")
    public Response searchEmployees(
            @QueryParam("firstName") @DefaultValue("") String firstName,
            @QueryParam("lastName") @DefaultValue("") String lastName,
            @QueryParam("position") Position position,
            @QueryParam("department") Department department,
            @QueryParam("hireDate") @DefaultValue("") String hireDate
    ) throws EmployeeServiceException {
        log.info("searchEmployees with : {} {} {} {} {}", firstName, lastName, position, department, hireDate);
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM Employees WHERE 1=1");
            List<Object> params = new ArrayList<>();
            addStringParam(firstName, "first_name", query, params);
            addStringParam(lastName, "last_name", query, params);
            if (hireDate != null && !hireDate.isEmpty()) {
                addStringParamDate(DATA_FORMAT.format(DATA_FORMAT.parse(hireDate)), "hire_date", query, params);
            }
            addEnumParam(position, "position", query, params);
            addEnumParam(department, "department", query, params);
            List<Employee> employees = databaseExecutor.executeSelect(query.toString(), params);
            return Response.ok(
                    EmployeeSearchResponseDto.builder()
                            .responseModelList(employees.stream().map(this::mapToEmployeeResponse).toList())
                            .build()
            ).build();
        } catch (ParseException e) {
            throw new EmployeeServiceException("Error parsing hireDate", 400);
        }
    }

    private void addStringParam(String value, String name, StringBuilder query, List<Object> params) {
        if (value != null && !value.isEmpty()) {
            query.append(" AND ").append(name).append(" = ?");
            params.add(value);
        }
    }

    private void addStringParamDate(String value, String name, StringBuilder query, List<Object> params) {
        if (value != null && !value.isEmpty()) {
            query.append(" AND ").append(name).append(" = CAST(? AS DATE)");
            params.add(value);
        }
    }

    private void addEnumParam(Object value, String name, StringBuilder query, List<Object> params) {
        if (value != null) {
            query.append(" AND ").append(name).append(" = ?");
            params.add(value);
        }
    }

    private EmployeeResponseDto mapToEmployeeResponse(Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .position(Position.valueOf(employee.getPosition()))
                .department(Department.valueOf(employee.getDepartment()))
                .hireDate(DATA_FORMAT.format(employee.getHireDate()))
                .build();
    }

}
