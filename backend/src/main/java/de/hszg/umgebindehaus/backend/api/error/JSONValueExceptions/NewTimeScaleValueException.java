package de.hszg.umgebindehaus.backend.api.error.JSONValueExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NewTimeScaleValueException extends RuntimeException {
    public NewTimeScaleValueException(String message) {
        super(message);
    }
}
