package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * Query to get student by Acme student record id
 */
public record GetStudentByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
    /**
     * Constructor
     *
     * @param studentRecordId Acme student record id
     *                        Must not be null or blank
     * @throws IllegalArgumentException If student record id is null or blank
     */
    public GetStudentByAcmeStudentRecordIdQuery {
        if (studentRecordId == null || studentRecordId.studentRecordId() == null || studentRecordId.studentRecordId().isBlank())
            throw new IllegalArgumentException("Student record id cannot be null or blank");
    }
}
