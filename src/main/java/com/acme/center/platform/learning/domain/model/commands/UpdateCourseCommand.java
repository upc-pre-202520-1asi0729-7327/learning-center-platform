package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to update a course
 * @param courseId the course id.
 *                 Cannot be null or less than 1
 * @param title the course title.
 *              Cannot be null or blank
 * @param description the course description.
 *                    Cannot be null or blank
 */
public record UpdateCourseCommand(Long courseId, String title, String description) {
    /**
     * Constructor
     * @param courseId the course id.
     *                 Cannot be null or less than 1
     * @param title the course title.
     *              Cannot be null or blank
     * @param description the course description.
     *                    Cannot be null or blank
     * @throws IllegalArgumentException if courseId is null or less than 1
     * @throws IllegalArgumentException if title is null or blank
     * @throws IllegalArgumentException if description is null or blank
     */
    public UpdateCourseCommand {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than 1");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
    }
}
