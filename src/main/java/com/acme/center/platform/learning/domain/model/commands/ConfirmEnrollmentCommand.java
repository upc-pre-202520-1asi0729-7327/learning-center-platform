package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to confirm an enrollment
 * @param enrollmentId the enrollment id.
 *                     Cannot be null or less than 1
 */
public record ConfirmEnrollmentCommand(Long enrollmentId) {
    /**
     * Constructor
     * @param enrollmentId the enrollment id.
     *                     Cannot be null or less than 1
     * @throws IllegalArgumentException if enrollmentId is null or less than 1
     */
    public ConfirmEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than 1");
        }
    }
}
