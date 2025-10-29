package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to get enrollment by Acme student record id and course id.
 * @param studentRecordId Acme student record id.
 * @param courseId Course id.
 */
public record GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(AcmeStudentRecordId studentRecordId, Long courseId) {
    /**
     * Constructor.
     * @param studentRecordId Acme student record id.
     *                        Must not be null.
     *                        Must not be blank.
     * @param courseId Course id.
     *                 Must be greater than 0.
     *                 Must not be null.
     * @throws IllegalArgumentException If the student record ID or course ID is invalid.
     */
    public GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank())
            throw new IllegalArgumentException("Student record id is required.");
        if (courseId == null || courseId <= 0) throw new IllegalArgumentException("Course id is required.");
    }
}
