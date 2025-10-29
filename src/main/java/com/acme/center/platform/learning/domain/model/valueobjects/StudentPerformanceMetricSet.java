package com.acme.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the student performance metric set.
 * @summary
 * This value object is used to represent the student performance metric set. It is an embeddable object that is used to represent the student performance metric set in the student record entity.
 * It throws an IllegalArgumentException if the total completed courses or total completed tutorials are null or less than 0.
 * @param totalCompletedCourses The total number of completed courses. It cannot be null or less than 0.
 * @param totalCompletedTutorials The total number of completed tutorials. It cannot be null or less than 0.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record StudentPerformanceMetricSet(Integer totalCompletedCourses, Integer totalCompletedTutorials) {
    public StudentPerformanceMetricSet() {
        this(0, 0);
    }

    public StudentPerformanceMetricSet {
        if (totalCompletedCourses == null || totalCompletedCourses < 0)
            throw new IllegalArgumentException("Total completed courses cannot be null or less than 0");
        if (totalCompletedTutorials == null || totalCompletedTutorials < 0)
            throw new IllegalArgumentException("Total completed tutorials cannot be null or less than 0");
    }

    /**
     * Increments the total number of completed courses by 1.
     * @summary
     * This method is used to increment the total number of completed courses by 1.
     * @return A new instance of the StudentPerformanceMetricSet value object with the total number of completed courses incremented by 1.
     * @since 1.0
     */
    public StudentPerformanceMetricSet incrementTotalCompletedCourses() {
        return new StudentPerformanceMetricSet(totalCompletedCourses + 1, totalCompletedTutorials);
    }

    /**
     * Increments the total number of completed tutorials by 1.
     * @summary
     * This method is used to increment the total number of completed tutorials by 1.
     * @return A new instance of the StudentPerformanceMetricSet value object with the total number of completed tutorials incremented by 1.
     * @since 1.0
     */
    public StudentPerformanceMetricSet incrementTotalCompletedTutorials() {
        return new StudentPerformanceMetricSet(totalCompletedCourses, totalCompletedTutorials + 1);
    }
}
