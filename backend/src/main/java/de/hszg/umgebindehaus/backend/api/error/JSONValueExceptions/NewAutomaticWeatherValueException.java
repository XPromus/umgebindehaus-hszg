package de.hszg.umgebindehaus.backend.api.error.JSONValueExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NewAutomaticWeatherValueException extends RuntimeException {
    public NewAutomaticWeatherValueException(String message) {
        super(message);
    }
}
