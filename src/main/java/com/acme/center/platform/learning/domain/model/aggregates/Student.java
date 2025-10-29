package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.center.platform.learning.domain.model.valueobjects.StudentPerformanceMetricSet;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * Student aggregate root entity
 * @summary
 * This entity represents the student aggregate root entity.
 * It contains the student record id, profile id, and performance metrics.
 * The performance metrics are updated when a course or tutorial is completed.
 * @see AcmeStudentRecordId
 * @see ProfileId
 * @see StudentPerformanceMetricSet
 * @see AuditableAbstractAggregateRoot
 * @since 1.0
 */
@Entity
public class Student extends AuditableAbstractAggregateRoot<Student> {
    @Getter
    @Embedded
    @Column(name = "acme_student_id")
    private AcmeStudentRecordId acmeStudentRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private StudentPerformanceMetricSet performanceMetricSet;

    /**
     * Default constructor
     */
    public Student() {
        super();
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetricSet = new StudentPerformanceMetricSet();
    }

    /**
     * Constructor with profile id
     * @param profileId the profile id
     */
    public Student(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    /**
     * Constructor with profile id
     * @param profileId the profile id
     *                  @see ProfileId
     */
    public Student(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    /**
     * Update metrics on course completed.
     * @summary
     * This method increments the total completed courses in the performance metrics.
     */
    public void updateMetricsOnCourseCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalCompletedCourses();
    }

    /**
     * Update metrics on tutorial completed.
     * @summary
     * This method increments the total completed tutorials in the performance metrics.
     */
    public void updateMetricsOnTutorialCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalCompletedTutorials();
    }

    /**
     * Get student record id
     * @return the student record id
     */
    public String getStudentRecordId() {
        return this.acmeStudentRecordId.studentRecordId();
    }

    /**
     * Get profile id
     * @return the profile id
     */
    public Long getProfileId() {
        return this.profileId.profileId();
    }

    /**
     * Get total completed courses
     * @return the total completed courses by the student
     */
    public int getTotalCompletedCourses() {
        return this.performanceMetricSet.totalCompletedCourses();
    }

    /**
     * Get total completed tutorials
     * @return the total completed tutorials by the student
     */
    public int getTotalCompletedTutorials() {
        return this.performanceMetricSet.totalCompletedTutorials();
    }
}
