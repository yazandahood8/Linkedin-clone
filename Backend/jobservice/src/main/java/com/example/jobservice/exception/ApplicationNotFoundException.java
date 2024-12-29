package com.example.jobservice.exception;

/**
 * Custom exception to handle cases where a job application is not found.
 */
public class ApplicationNotFoundException extends RuntimeException {

    /**
     * Constructor to initialize exception with a custom message.
     *
     * @param message The custom message for the exception.
     */
    public ApplicationNotFoundException(String message) {
        super(message);
    }
}
