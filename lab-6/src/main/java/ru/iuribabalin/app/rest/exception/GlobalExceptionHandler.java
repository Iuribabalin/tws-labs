package ru.iuribabalin.app.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.iuribabalin.app.soap.exception.EmployeeServiceException;

;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<EmployeeServiceException> {

    @Override
    public Response toResponse(EmployeeServiceException exception) {
        return Response.status(exception.getErrorDto().getStatus()).entity(exception.getErrorDto()).build();
    }
}
