package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to cancel an enrollment
 * @param enrollmentId the enrollment id.
 *                     Cannot be null or less than 1
 */
public record CancelEnrollmentCommand(Long enrollmentId) {
    /**
     * Constructor
     * @param enrollmentId the enrollment id.
     *                     Cannot be null or less than 1
     * @throws IllegalArgumentException if enrollmentId is null or less than 1
     */
    public CancelEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than 1");
        }
    }
}
