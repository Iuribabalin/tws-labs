package ru.iuribabalin.app.soap.publisher;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.xml.ws.Endpoint;
import lombok.extern.slf4j.Slf4j;
import ru.iuribabalin.app.soap.EmployeeServiceImpl;
import ru.iuribabalin.flyway.FlywayMigrationRunner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


@Slf4j
@WebListener
public class ContextInitializer implements ServletContextListener {
    private Endpoint endpoint;

    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("\n\tStart initializing context");
        initConnection(sce);
        publishService();
        initDataBase();
    }

    private void initConnection(ServletContextEvent sce) {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/employBase");
            sce.getServletContext().setAttribute("dataSource", dataSource);
        } catch (NamingException e) {
            log.error("Failed to initialize DataSource", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (endpoint != null) {
            endpoint.stop();
            log.info("\n\tEmployeeService is stopped!");
        }
    }

    private void publishService() {
        try {
            EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl(dataSource);
            endpoint = Endpoint.publish("http://localhost:9090/EmployeeService", employeeServiceImpl);
            log.info("\n\tEmployeeService is published!");
        } catch (Exception e) {
            log.error("\n\tFailed to publish EmployeeService", e);
        }
    }

    private void initDataBase() {
        log.info("\n\tInitializing database");
        FlywayMigrationRunner migrationRunner = new FlywayMigrationRunner();
        migrationRunner.migrate();
    }

}
