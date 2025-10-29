package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.commands.*;

/**
 * EnrollmentCommandService
 * Service that handles enrollment commands
 */
public interface EnrollmentCommandService {
    /**
     * Handle a request enrollment command
     * @param command The request enrollment command containing the enrollment data
     * @return The requested enrollment id
     * @see RequestEnrollmentCommand
     */
    Long handle(RequestEnrollmentCommand command);
    /**
     * Handle a confirm enrollment command
     * @param command The confirm enrollment command containing the enrollment id
     * @return The confirmed enrollment id
     * @see ConfirmEnrollmentCommand
     */
    Long handle(ConfirmEnrollmentCommand command);
    /**
     * Handle a reject enrollment command
     * @param command The reject enrollment command containing the enrollment id
     * @return The rejected enrollment id
     * @see RejectEnrollmentCommand
     */
    Long handle(RejectEnrollmentCommand command);
    /**
     * Handle a cancel enrollment command
     * @param command The cancel enrollment command containing the enrollment id
     * @return The canceled enrollment id
     * @see CancelEnrollmentCommand
     */
    Long handle(CancelEnrollmentCommand command);
    /**
     * Handle a complete tutorial for enrollment command
     * @param command The complete tutorial for enrollment command containing the enrollment id and tutorial id
     * @return The enrollment id where the tutorial was completed
     * @see CompleteTutorialForEnrollmentCommand
     */
    Long handle(CompleteTutorialForEnrollmentCommand command);
}
