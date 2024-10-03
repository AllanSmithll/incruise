package br.edu.pweb2.incruise.model.exception;

/**
 * Exception reference to when a Item was not found in the database
 */
public class ItemNotFoundException extends RuntimeException {
    
    public ItemNotFoundException(String message) {
        super(message);
    }
}

