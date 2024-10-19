package ru.iuribabalin.app.exception;

import jakarta.xml.ws.WebFault;
import lombok.Getter;
import ru.iuribabalin.app.exception.model.EmployeeErrorDto;

@Getter
@WebFault(faultBean = "com.wishmaster.ifmo.ws.jaxws.errors.PersonServiceFault")
public class EmployeeServiceException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;
    private EmployeeErrorDto errorDto;

    public EmployeeServiceException(String message) {
        super(message);
        errorDto = EmployeeErrorDto.builder().message(message).status(500).build();
    }

    public EmployeeServiceException(String message, int statusCode) {
        super(message);
        errorDto = EmployeeErrorDto.builder().message(message).status(statusCode).build();
    }
}
