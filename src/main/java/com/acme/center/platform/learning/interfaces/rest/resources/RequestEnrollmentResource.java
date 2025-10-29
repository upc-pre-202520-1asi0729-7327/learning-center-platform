package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Request enrollment resource.
 */
public record RequestEnrollmentResource(String studentRecordId, Long courseId) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the student record id or course id is null or blank.
     */
    public RequestEnrollmentResource {
        if (studentRecordId == null || studentRecordId.isBlank()) {
            throw new IllegalArgumentException("Student record id is required");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Course id is required");
        }
    }
}
