package br.edu.pweb2.incruise.model.exception;

public class DuplicateCnpjException extends RuntimeException {
    public DuplicateCnpjException(String message) {
        super(message);
    }

    public DuplicateCnpjException(String message, Throwable cause) {
        super(message, cause);
    }
}