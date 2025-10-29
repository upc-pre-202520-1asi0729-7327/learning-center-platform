package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * ExistByAcmeStudentRecordIdQuery
 * <p>Query used to check if a student exists by its Acme student record id.</p>
 * @param studentRecordId The {@link AcmeStudentRecordId} student record id.
 */
public record ExistsByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
}
