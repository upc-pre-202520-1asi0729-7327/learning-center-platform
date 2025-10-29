package com.acme.center.platform.learning.domain.model.queries;

/**
 * Query to get all enrollments by course id.
 * @param courseId Course id.
 */
public record GetAllEnrollmentsByCourseIdQuery(Long courseId) {
    /**
     * Constructor.
     * @param courseId Course id.
     *                 Must be greater than 0.
     *                 Must not be null.
     * @throws IllegalArgumentException If the course ID is invalid.
     */
    public GetAllEnrollmentsByCourseIdQuery {
        if (courseId == null || courseId <= 0) throw new IllegalArgumentException("Course id is required.");
    }
}
