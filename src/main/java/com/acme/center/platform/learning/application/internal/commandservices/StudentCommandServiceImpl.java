package com.acme.center.platform.learning.application.internal.commandservices;

import com.acme.center.platform.learning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.center.platform.learning.domain.exceptions.StudentNotFoundException;
import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.services.StudentCommandService;
import com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the StudentCommandService interface.
 * <p>This class is responsible for handling the commands related to the Student aggregate. It requires a StudentRepository.</p>
 * @see StudentCommandService
 * @see StudentRepository
 */
@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;
    private final ExternalProfileService externalProfileService;

    // inherit javadoc
    public StudentCommandServiceImpl(StudentRepository studentRepository, ExternalProfileService externalProfileService) {
        this.studentRepository = studentRepository;
        this.externalProfileService = externalProfileService;
    }

    // inherit javadoc
    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {
        // Fetch profile from an external service by email
        var profileId = externalProfileService.fetchProfileByEmail(command.email());
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(
                    command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.street(),
                    command.number(),
                    command.city(),
                    command.postalCode(),
                    command.country());
        } else {
            studentRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                var message = String.format("Student with ID %s already exists with same profile.", student.getAcmeStudentRecordId().studentRecordId());
                throw new IllegalArgumentException(message);
            });
        }

        if (profileId.isEmpty()) {
            throw new IllegalArgumentException("Unable to create student profile.");
        }

        // Create a new student with the profile data.
        var student = new Student(profileId.get());
        studentRepository.save(student);
        return student.getAcmeStudentRecordId();
    }

    // inherit javadoc
    @Override
    public AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command) {
        studentRepository.findByAcmeStudentRecordId(command.studentRecordId()).map(student -> {
            // Update the student metrics
            student.updateMetricsOnTutorialCompleted();
            studentRepository.save(student);
            return student.getAcmeStudentRecordId();
        }).orElseThrow(() -> new StudentNotFoundException(command.studentRecordId()));
        return null;
    }
}
