package de.hszg.umgebindehaus.backend.api.error;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFound() {}

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest request) {
        String messageBody = "The JSON-String could not be converted to an Object";
        String rootCauseMessage = e.getRootCause().getMessage();

        if (rootCauseMessage.contains("newName")) {
            messageBody = "There is an error with the value of 'newName'";
        } else if (rootCauseMessage.contains("newTime")) {
            messageBody = "There is an error with the value of 'newTime'";
        } else if (rootCauseMessage.contains("newTimeScale")) {
            messageBody = "There is an error with the value of 'newTimeScale'";
        } else if (rootCauseMessage.contains("newAutomaticWeather")) {
            messageBody = "There is an error with the value of 'newAutomaticWeather'";
        } else if (rootCauseMessage.contains("newAutomaticTime")) {
            messageBody = "There is an error with the value of 'newAutomaticTime'";
        } else if (rootCauseMessage.contains("newWeatherWindDirection")) {
            messageBody = "There is an error with the value of 'newWeatherWindDirection'";
        } else if (rootCauseMessage.contains("newWeatherWindSpeed")) {
            messageBody = "There is an error with the value of 'newWeatherWindSpeed'";
        } else if (rootCauseMessage.contains("newWeatherCloudiness")) {
            messageBody = "There is an error with the value of 'newWeatherCloudiness'";
        }

        ErrorMessage message = new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                messageBody,
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }
}
