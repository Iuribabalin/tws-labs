package ru.iuribabalin.app.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import lombok.extern.slf4j.Slf4j;
import ru.iuribabalin.app.domain.DatabaseExecutor;
import ru.iuribabalin.app.domain.Employee;
import ru.iuribabalin.app.soap.exception.EmployeeServiceException;
import ru.iuribabalin.model.Department;
import ru.iuribabalin.model.Position;
import ru.iuribabalin.model.soap.EmployeeRequest;
import ru.iuribabalin.model.soap.EmployeeResponse;
import ru.iuribabalin.model.soap.EmployeeSearchResponse;
import ru.iuribabalin.model.soap.SearchEmployeesResponseModel;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebService(serviceName = "EmployeeService", portName = "EmployeeServicePort")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class EmployeeServiceImpl {

    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final DatabaseExecutor databaseExecutor;

    public EmployeeServiceImpl(DataSource dataSource) {
        this.databaseExecutor = new DatabaseExecutor(dataSource);
    }

    @WebMethod
    @WebResult(name = "EmployeeSearchResponse", targetNamespace = "http://app.iuribabalin.ru/")
    public EmployeeSearchResponse searchEmployees(
            @WebParam(name = "firstName")
            String firstName,
            @WebParam(name = "lastName")
            String lastName,
            @WebParam(name = "position")
            Position position,
            @WebParam(name = "department")
            Department department,
            @WebParam(name = "data")
            String data
    ) throws EmployeeServiceException {
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM Employees WHERE 1=1");
            List<Object> params = new ArrayList<>();
            addStringParam(firstName, "first_name", query, params);
            addStringParam(lastName, "last_name", query, params);
            if (data != null && !data.isEmpty()) {
                addStringParamDate(DATA_FORMAT.format(DATA_FORMAT.parse(data)), "hire_date", query, params);
            }
            addEnumParam(position, "position", query, params);
            addEnumParam(department, "department", query, params);
            return EmployeeSearchResponse.builder()
                    .responseModelList(databaseExecutor.executeSelect(query.toString(), params).stream()
                            .map(EmployeeServiceImpl::mapToSearchResponse).toList())
                    .build();
        } catch (ParseException e) {
            throw new EmployeeServiceException("Error parse field date", 400);
        }
    }

    @WebMethod
    @WebResult(name = "EmployeeResponse", targetNamespace = "http://app.iuribabalin.ru/")
    public EmployeeResponse create(@WebParam(name = "request") EmployeeRequest request) throws EmployeeServiceException {
        if (request == null) {
            throw new EmployeeServiceException("request is null");
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
        StringBuilder query = new StringBuilder(
                "INSERT INTO Employees (first_name, last_name, position, department, hire_date) values ("
        );
        query.append("'").append(request.getFirstName()).append("'");
        query.append(", '").append(request.getLastName()).append("'");
        query.append(", '").append(request.getPosition()).append("'");
        query.append(", '").append(request.getDepartment()).append("'");
        query.append(", '").append(request.getHireDate()).append("'");
        query.append(")");
        Employee employee = databaseExecutor.executeQuery(query.toString());
        log.info("Employee created: {}", employee.getId());
        return employee != null ? mapToEmployeeResponse(employee) : null;
    }

    @WebMethod
    @WebResult(name = "EmployeeResponse", targetNamespace = "http://app.iuribabalin.ru/")
    public EmployeeResponse update(
            @WebParam(name = "id") Long id,
            @WebParam(name = "request") EmployeeRequest request
    ) throws EmployeeServiceException {
        if (id == null) {
            throw new EmployeeServiceException("Id was not be null", 400);
        }
        String queryUpdateParams = request.toQueryStringUpdate();
        if(queryUpdateParams.isEmpty()){
            throw new EmployeeServiceException("Empty update", 400);
        }
        StringBuilder query = new StringBuilder("UPDATE Employees SET ").append(queryUpdateParams)
                .append(" WHERE ID = ").append(id);
        Employee employee = databaseExecutor.executeQuery(query.toString());
        return employee != null ? mapToEmployeeResponse(employee) : null;
    }

    @WebMethod
    public void delete(
            @WebParam(name = "id") Long id
    ) throws EmployeeServiceException {
        if (id == null) {
            throw new EmployeeServiceException("Id was not be null", 400);
        }
        databaseExecutor.executeQueryEmpty("DELETE FROM Employees WHERE ID = " + id);
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

    private static SearchEmployeesResponseModel mapToSearchResponse(Employee employee) {
        return SearchEmployeesResponseModel.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .position(Position.valueOf(employee.getPosition()))
                .department(Department.valueOf(employee.getDepartment()))
                .hireDate(DATA_FORMAT.format(employee.getHireDate()))
                .build();
    }

    private static EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .position(Position.valueOf(employee.getPosition()))
                .department(Department.valueOf(employee.getDepartment()))
                .hireDate(DATA_FORMAT.format(employee.getHireDate()))
                .build();
    }
}


