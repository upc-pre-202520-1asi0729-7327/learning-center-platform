package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Update course resource.
 */
public record UpdateCourseResource(String title, String description) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the title or description is null or blank.
     */
    public UpdateCourseResource {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
    }
}
