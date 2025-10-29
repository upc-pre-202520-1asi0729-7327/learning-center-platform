package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * EnrollmentRepository
 * <p>This interface is used to interact with the database and perform CRUD and business command and query supporting operations on the Enrollment entity.</p>
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    /**
     * This method is used to find all enrollments by a student record id.
     * @param studentRecordId The student record id.
     * @return A list of enrollments related to the student record id.
     * @see AcmeStudentRecordId
     */
    List<Enrollment> findAllByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
    /**
     * This method is used to find all enrollments by a course id.
     * @param courseId The course id.
     * @return A list of enrollments related to the course id.
     */
    List<Enrollment> findAllByCourseId(Long courseId);
    /**
     * This method is used to find an enrollment by a student record id and a course id.
     * @param studentRecordId The student record id.
     * @param courseId The course id.
     * @return An optional of the enrollment.
     * @see AcmeStudentRecordId
     */
    Optional<Enrollment> findByAcmeStudentRecordIdAndCourseId(AcmeStudentRecordId studentRecordId, Long courseId);
}
