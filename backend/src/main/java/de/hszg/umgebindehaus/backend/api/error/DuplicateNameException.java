package de.hszg.umgebindehaus.backend.api.error;

public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(String message) {
        super(message);
    }
}
