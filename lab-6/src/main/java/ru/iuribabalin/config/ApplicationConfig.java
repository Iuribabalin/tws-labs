package ru.iuribabalin.config;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import ru.iuribabalin.app.rest.EmployeeRestService;
import ru.iuribabalin.app.rest.exception.GlobalExceptionHandler;
import ru.iuribabalin.app.rest.filter.BasicAuthFilter;
import ru.iuribabalin.app.rest.providers.CustomJsonProvider;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(EmployeeRestService.class);
        resources.add(GlobalExceptionHandler.class);
        resources.add(BasicAuthFilter.class);
        resources.add(CustomJsonProvider.class);
        return resources;
    }
}
