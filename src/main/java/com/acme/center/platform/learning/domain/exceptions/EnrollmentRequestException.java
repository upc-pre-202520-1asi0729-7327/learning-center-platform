package com.acme.center.platform.learning.domain.exceptions;

/**
 * Exception thrown when an error occurs while creating an enrollment.
 * @summary
 * This exception is thrown when an error occurs while creating an enrollment.
 * @see RuntimeException
 */
public class EnrollmentRequestException extends RuntimeException {
    /**
     * Constructor for the exception.
     * @param exceptionMessage The message of the exception.
     */
    public EnrollmentRequestException(String exceptionMessage) {
        super("Error while creating the enrollment: %s".formatted(exceptionMessage));
    }
}
