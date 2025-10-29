package com.acme.center.platform.learning.domain.model.queries;

/**
 * Query to get an enrollment by its id.
 */
public record GetEnrollmentByIdQuery(Long enrollmentId) {
    /**
     * Constructor to validate the query.
     * @param enrollmentId The enrollment id.
     *                     It cannot be null or less than or equal to 0.
     * @throws IllegalArgumentException If the enrollmentId is null or less than or equal to 0.
     */
    public GetEnrollmentByIdQuery {
        if (enrollmentId == null || enrollmentId <= 0)
            throw new IllegalArgumentException("enrollmentId cannot be null or less than or equal to 0");
    }
}
