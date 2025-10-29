package com.acme.center.platform.learning.domain.exceptions;

/**
 * Exception thrown when a course is not found.
 * @summary
 * This exception is thrown when a course is not found in the database.
 * @see RuntimeException
 */
public class CourseNotFoundException extends RuntimeException {
    /**
     * Constructor for the exception.
     * @param courseId The ID of the course that was not found.
     */
    public CourseNotFoundException(Long courseId) {
        super(String.format("Course with ID %s not found.", courseId));
    }
}
