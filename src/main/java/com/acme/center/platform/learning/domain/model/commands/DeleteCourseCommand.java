package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to delete a course
 * @param courseId the course id.
 *                 Cannot be null or less than 1
 */
public record DeleteCourseCommand(Long courseId) {
    /**
     * Constructor
     * @param courseId the course id.
     *                 Cannot be null or less than 1
     * @throws IllegalArgumentException if courseId is null or less than 1
     */
    public DeleteCourseCommand {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than 1");
        }
    }
}
