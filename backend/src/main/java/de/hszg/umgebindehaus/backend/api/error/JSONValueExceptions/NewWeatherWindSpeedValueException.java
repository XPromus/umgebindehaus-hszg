package de.hszg.umgebindehaus.backend.api.error.JSONValueExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NewWeatherWindSpeedValueException extends RuntimeException {
    public NewWeatherWindSpeedValueException(String message) {
        super(message);
    }
}
