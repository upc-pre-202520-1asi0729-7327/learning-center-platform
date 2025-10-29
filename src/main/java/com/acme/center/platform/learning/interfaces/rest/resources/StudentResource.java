package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Student resource.
 */
public record StudentResource(String acmeStudentRecordId, Long profileId, Integer totalCompletedCourses, Integer totalCompletedTutorials) {
}
