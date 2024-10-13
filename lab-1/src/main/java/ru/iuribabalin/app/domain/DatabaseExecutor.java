package ru.iuribabalin.app.domain;

import lombok.extern.slf4j.Slf4j;
import ru.iuribabalin.model.Department;
import ru.iuribabalin.model.Position;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DatabaseExecutor {

    private final DataSource dataSource;

    public DatabaseExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Employee> executeSelect(String sql, List<Object> params) {
        List<Employee> employees = new ArrayList<>();
        try (
                var connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            log.info("query: {}", sql);
            log.info("params: {}", params);
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Position) {
                    statement.setString(i + 1, ((Position) param).name());
                } else if (param instanceof Department) {
                    statement.setString(i + 1, ((Department) param).name());
                } else {
                    statement.setObject(i + 1, param);
                }
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(mapToEmployee(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private Employee mapToEmployee(ResultSet resultSet) throws SQLException {
        return Employee.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .position(resultSet.getString("position"))
                .department(resultSet.getString("department"))
                .hireDate(resultSet.getDate("hire_date"))
                .build();
    }
}
