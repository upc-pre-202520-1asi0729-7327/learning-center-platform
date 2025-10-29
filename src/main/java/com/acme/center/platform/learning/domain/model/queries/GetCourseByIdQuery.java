package com.acme.center.platform.learning.domain.model.queries;

/**
 * Query to get course by id.
 * @param courseId Course id.
 */
public record GetCourseByIdQuery(Long courseId) {
    /**
     * Constructor.
     * @param courseId Course id.
     *                 Must be greater than 0.
     *                 Must not be null.
     * @throws IllegalArgumentException If the course ID is invalid.
     */
    public GetCourseByIdQuery {
        if (courseId == null || courseId <= 0) throw new IllegalArgumentException("Course id is required.");
    }
}
