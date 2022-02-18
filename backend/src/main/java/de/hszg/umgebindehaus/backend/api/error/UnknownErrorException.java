package de.hszg.umgebindehaus.backend.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownErrorException extends RuntimeException {
    public UnknownErrorException(String message) {
        super(message);
    }
}
