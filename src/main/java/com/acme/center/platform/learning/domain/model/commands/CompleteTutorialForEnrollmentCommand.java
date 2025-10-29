package com.acme.center.platform.learning.domain.model.commands;

import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;

/**
 * Command to complete a tutorial for an enrollment
 * @param enrollmentId the enrollment id.
 *                     Cannot be null or less than 1
 * @param tutorialId the tutorial id.
 *                   Cannot be null or less than 1
 * @see TutorialId
 */
public record CompleteTutorialForEnrollmentCommand(Long enrollmentId, TutorialId tutorialId) {
    /**
     * Constructor
     * @param enrollmentId the enrollment id.
     *                     Cannot be null or less than 1
     * @param tutorialId the tutorial id.
     *                   Cannot be null or less than 1
     * @throws IllegalArgumentException if enrollmentId is null or less than 1
     * @throws IllegalArgumentException if tutorialId is null or less than 1
     */
    public CompleteTutorialForEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than 1");
        }
        if (tutorialId == null || tutorialId.tutorialId() <= 0) {
            throw new IllegalArgumentException("tutorialId cannot be null or less than 1");
        }
    }
}
