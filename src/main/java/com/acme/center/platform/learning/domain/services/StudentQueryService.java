package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.queries.ExistsByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;

import java.util.Optional;

/**
 * StudentQueryService
 * This interface defines the contract for the StudentQueryService.
 */
public interface StudentQueryService {
    /**
     * handle
     * This method is used to handle the GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery.
     * @param query the GetStudentByProfileIdQuery containing the student record id and profile id.
     * @return Optional<Student> containing the student for the given profile id.
     * @see GetStudentByProfileIdQuery
     */
    Optional<Student> handle(GetStudentByProfileIdQuery query);
    /**
     * handle
     * This method is used to handle the GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery.
     * @param query the GetStudentByAcmeStudentRecordIdQuery containing the student record id.
     * @return Optional<Student> containing the student for the given record id.
     * @see GetStudentByAcmeStudentRecordIdQuery
     */
    Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query);

    /**
     * handle
     * This method is used to handle the ExistByAcmeStudentRecordIdQuery.
     * @param query the ExistByAcmeStudentRecordIdQuery containing the student record id.
     * @return boolean indicating if a student with the given record ID exists.
     * @see ExistsByAcmeStudentRecordIdQuery
     */
    boolean handle(ExistsByAcmeStudentRecordIdQuery query);
}
