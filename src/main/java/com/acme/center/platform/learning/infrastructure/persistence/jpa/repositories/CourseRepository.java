package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CourseRepository
 * <p>This interface is used to interact with the database and perform CRUD and business command and query supporting operations on the Course entity.</p>
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * This method is used to find a course by its title.
     * @param title The title of the course.
     * @return An optional of the course.
     */
    Optional<Course> findByTitle(String title);
    /**
     * This method is used to check if a course exists by its title.
     * @param title The title of the course.
     * @return A boolean indicating if the course exists.
     */
    boolean existsByTitle(String title);
    /**
     * This method is used to check if a course exists by its title and a different id.
     * @param title The title of the course.
     * @param id The id of the course.
     * @return A boolean indicating if the course exists with the same title but a different id.
     */
    boolean existsByTitleAndIdIsNot(String title, Long id);

}
