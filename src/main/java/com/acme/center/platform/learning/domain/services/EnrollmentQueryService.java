package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * EnrollmentQueryService
 * Service that handles enrollment queries
 */
public interface EnrollmentQueryService {
    /**
     * Handle a get all enrollments by acme student record id query
     *
     * @param query The get all enrollments by acme student record id query containing the acme student record id
     * @return The list of enrollments for the acme student record id
     * @see GetAllEnrollmentsByAcmeStudentRecordIdQuery
     */
    List<Enrollment> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query);
    /**
     * Handle a get enrollment by id query
     * @param query The get enrollment by id query containing the enrollment id
     * @return The enrollment for the id
     * @see GetEnrollmentByIdQuery
     */
    Optional<Enrollment> handle(GetEnrollmentByIdQuery query);
    /**
     * Handle a get all enrollments query
     * @param query The get all enrollments query
     * @return The list of enrollments
     * @see GetAllEnrollmentsQuery
     */
    List<Enrollment> handle(GetAllEnrollmentsQuery query);
    /**
     * Handle a get all enrollments by course id query
     * @param query The get all enrollments by course id query containing the course id
     * @return The list of enrollments for the course id
     * @see GetAllEnrollmentsByCourseIdQuery
     */
    List<Enrollment> handle(GetAllEnrollmentsByCourseIdQuery query);
    /**
     * Handle a get enrollment by acme student record id and course id query
     * @param query The get enrollment by acme student record id and course id query containing the acme student record id and course id
     * @return The enrollment for the acme student record id and course id
     * @see GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery
     */
    Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query);
}
