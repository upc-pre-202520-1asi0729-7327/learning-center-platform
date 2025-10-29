package com.acme.center.platform.learning.domain.model.commands;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Command to request enrollment
 * @param studentRecordId the student record id.
 *                        Cannot be null or blank
 * @param courseId the course id.
 *                 Cannot be null or less than 1
 */
public record RequestEnrollmentCommand(AcmeStudentRecordId studentRecordId, Long courseId) {
    /**
     * Constructor
     * @param studentRecordId the student record id.
     *                        Cannot be null or blank
     * @param courseId the course id.
     *                 Cannot be null or less than 1
     * @throws IllegalArgumentException if studentRecordId is null or courseId is null or less than 1
     */
    public RequestEnrollmentCommand {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank()) {
            throw new IllegalArgumentException("studentRecordId cannot be null or blank");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than 1");
        }
    }
}
