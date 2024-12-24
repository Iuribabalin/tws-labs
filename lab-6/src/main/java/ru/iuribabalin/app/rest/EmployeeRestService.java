package ru.iuribabalin.app.rest;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iuribabalin.app.domain.DatabaseExecutor;
import ru.iuribabalin.app.domain.Employee;
import ru.iuribabalin.app.rest.model.EmployeeRequestDto;
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

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeRestService {

    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(EmployeeRestService.class);
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

    @POST
    public Response createEmployee(EmployeeRequestDto request) throws EmployeeServiceException {
        validateRequest(request);
        StringBuilder query = new StringBuilder("INSERT INTO Employees (first_name, last_name, position, department, hire_date) values (");
        query.append("'").append(request.getFirstName()).append("'");
        query.append(", '").append(request.getLastName()).append("'");
        query.append(", '").append(request.getPosition()).append("'");
        query.append(", '").append(request.getDepartment()).append("'");
        query.append(", '").append(request.getHireDate()).append("')");
        Employee employee = databaseExecutor.executeQuery(query.toString());
        return Response.ok(mapToEmployeeResponse(employee)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") Long id, EmployeeRequestDto request) throws EmployeeServiceException {
        if (id == null) {
            throw new EmployeeServiceException("Id cannot be null", 400);
        }
        String queryUpdateParams = request.toQueryStringUpdate();
        if (queryUpdateParams.isEmpty()) {
            throw new EmployeeServiceException("Empty update", 400);
        }
        StringBuilder query = new StringBuilder("UPDATE Employees SET ").append(queryUpdateParams)
                .append(" WHERE ID = ").append(id);
        Employee employee = databaseExecutor.executeQuery(query.toString());
        return Response.ok(mapToEmployeeResponse(employee)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Long id) throws EmployeeServiceException {
        if (id == null) {
            throw new EmployeeServiceException("Id cannot be null", 400);
        }
        if (databaseExecutor.getEmployeeById(Math.toIntExact(id)) == null) {
            throw new EmployeeServiceException("Employee not found!", 404);
        }
        databaseExecutor.executeQueryEmpty("DELETE FROM Employees WHERE ID = " + id);
        return Response.noContent().build();
    }

    private void validateRequest(EmployeeRequestDto request) throws EmployeeServiceException {
        if (request == null) {
            throw new EmployeeServiceException("Request cannot be null", 400);
        }
        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            throw new EmployeeServiceException("FirstName is null or empty", 400);
        }
        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new EmployeeServiceException("LastName is null or empty", 400);
        }
        if (request.getPosition() == null) {
            throw new EmployeeServiceException("Position is null or empty", 400);
        }
        if (request.getDepartment() == null) {
            throw new EmployeeServiceException("Department is null or empty", 400);
        }
        if (request.getHireDate() == null || request.getHireDate().isEmpty()) {
            throw new EmployeeServiceException("HireDate is null or empty", 400);
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
