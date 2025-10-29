package com.acme.center.platform.learning.domain.services;

import com.acme.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

/**
 * StudentCommandService
 * This interface defines the contract for the StudentCommandService.
 */
public interface StudentCommandService {
    /**
     * handle
     * This method is used to handle the CreateStudentCommand.
     * @param command the CreateStudentCommand containing the student data.
     * @return AcmeStudentRecordId generated for the student.
     */
    AcmeStudentRecordId handle(CreateStudentCommand command);
    /**
     * handle
     * This method is used to handle the UpdateStudentMetricsOnTutorialCompletedCommand.
     * @param command the UpdateStudentMetricsOnTutorialCompletedCommand containing the student record id.
     * @return AcmeStudentRecordId for the student whose metrics are updated.
     */
    AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command);
}
