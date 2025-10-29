package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.events.TutorialCompletedEvent;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {
    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Embedded
    private ProgressRecord progressRecord;

    private EnrollmentStatus status;

    public Enrollment() {
        // Required by JPA
    }

    public Enrollment(AcmeStudentRecordId acmeStudentRecordId, Course course) {
        this.acmeStudentRecordId = acmeStudentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
        this.progressRecord.initializeProgressRecord(this, course.getLearningPath());
        // this.registerEvent(new EnrollmentConfirmedEvent(this));
    }

    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
        // this.registerEvent(new EnrollmentRejectedEvent(this));
    }

    public void cancel() {
        this.status = EnrollmentStatus.CANCELLED;
        // this.registerEvent(new EnrollmentCancelledEvent(this));
    }

    public boolean isConfirmed() {
        return this.status == EnrollmentStatus.CONFIRMED;
    }

    public boolean isRejected() {
        return this.status == EnrollmentStatus.REJECTED;
    }

    public boolean isCancelled() {
        return this.status == EnrollmentStatus.CANCELLED;
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }

    public long calculateDaysElapsed() {
        return progressRecord.calculateDaysElapsedForEnrollment(this);
    }

    public void completeTutorial(TutorialId tutorialId) {
        this.progressRecord.completeTutorial(tutorialId, course.getLearningPath());
        // Publish a Tutorial Completed Event
        this.registerEvent(new TutorialCompletedEvent(this, this.getId(), tutorialId));
    }
}
