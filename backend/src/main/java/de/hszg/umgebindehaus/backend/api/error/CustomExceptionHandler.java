package de.hszg.umgebindehaus.backend.api.error;

import de.hszg.umgebindehaus.backend.api.error.JSONValueExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFound() {}

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String exceptionMessage = e.getMessage();

        if (exceptionMessage.contains("newName")) {
            throw new NewNameValueException("There is an error with the value of 'newName'");
        } else if (exceptionMessage.contains("newTime")) {
            throw new NewTimeValueException("There is an error with the value of 'newTime'");
        } else if (exceptionMessage.contains("newTimeScale")) {
            throw new NewTimeScaleValueException("There is an error with the value of 'newTimeScale'");
        } else if (exceptionMessage.contains("newAutomaticWeather")) {
            throw new NewAutomaticWeatherValueException("There is an error with the value of 'newAutomaticWeather'");
        } else if (exceptionMessage.contains("newAutomaticTime")) {
            throw new NewAutomaticTimeValueException("There is an error with the value of 'newAutomaticTime'");
        } else if (exceptionMessage.contains("newWeatherWindDirection")) {
            throw new NewWeatherWindDirectionValueException("There is an error with the value of 'newWeatherWindDirection'");
        } else if (exceptionMessage.contains("newWeatherWindSpeed")) {
            throw new NewWeatherWindSpeedValueException("There is an error with the value of 'newWeatherWindSpeed'");
        } else if (exceptionMessage.contains("newWeatherCloudiness")) {
            throw new NewWeatherCloudinessValueException("There is an error with the value of 'newWeatherCloudiness'");
        } else {
            throw new FaultyJSONException("The JSON-String could not be converted to an Object");
        }
    }
}
