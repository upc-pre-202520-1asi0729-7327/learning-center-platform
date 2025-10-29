package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Student repository
 * <p>This interface is used to interact with the database and perform CRUD and business command and query supporting operations on the Student entity.</p>
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    /**
     * This method is used to find a student by its Acme student record id.
     * @param studentRecordId The Acme student record id.
     * @return An optional with a student object if found, otherwise an empty optional.
     * @see AcmeStudentRecordId
     */
    Optional<Student> findByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
    /**
     * This method is used to find a student by its profile id.
     * @param profileId The profile id.
     * @return An optional with a student object if found, otherwise an empty optional.
     * @see ProfileId
     */
    Optional<Student> findByProfileId(ProfileId profileId);

    /**
     * This method is used to check if a student exists by its Acme student record id.
     * @param studentRecordId The Acme student record id.
     * @return A boolean indicating if the student exists.
     * @see AcmeStudentRecordId
     */
    boolean existsByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
}
