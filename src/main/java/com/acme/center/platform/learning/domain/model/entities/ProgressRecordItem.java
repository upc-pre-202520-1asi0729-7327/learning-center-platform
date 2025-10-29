package com.acme.center.platform.learning.domain.model.entities;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressStatus;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a record of a student's progress on a tutorial of the learning path for a course.
 * @summary
 * This entity represents a record of a student's progress in a tutorial.
 * It contains information about the student's enrollment, the tutorial being tracked, the status of the progress,
 * and the dates when the progress was started and completed.
 * @see AuditableModel
 * @since 1.0
 */
@Getter
@Entity
public class ProgressRecordItem extends AuditableModel {
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    private TutorialId tutorialId;

    private ProgressStatus status;

    private Date statedAt;

    private Date completedAt;

    /**
     * Creates a new instance of the ProgressRecordItem class.
     * @param enrollment The enrollment of the student.
     * @param tutorialId The ID of the tutorial being tracked.
     * @see Enrollment
     * @see TutorialId
     */
    public ProgressRecordItem(Enrollment enrollment, TutorialId tutorialId) {
        this.enrollment = enrollment;
        this.tutorialId = tutorialId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    /**
     * Default constructor
     */
    public ProgressRecordItem() {
        // Required by JPA
    }

    /**
     * Updates the status of the progress to "Started" and sets the start date.
     * @see ProgressStatus
     */
    public void start() {
        this.status = ProgressStatus.STARTED;
        this.statedAt = new Date();
    }

    /**
     * Updates the status of the progress to "Completed" and sets the completion date.
     * @see ProgressStatus
     */
    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    /**
     * Verifies if the progress is completed.
     * @see ProgressStatus
     */
    public boolean isCompleted() {
        return ProgressStatus.COMPLETED.equals(status);
    }

    /**
     * Verifies if the progress is in progress (started).
     * @see ProgressStatus
     */
    public boolean isInProgress() {
        return ProgressStatus.STARTED.equals(status);
    }

    /**
     * Verifies if the progress has not started.
     * @see ProgressStatus
     */
    public boolean isNotStarted() {
        return ProgressStatus.NOT_STARTED.equals(status);
    }

    /**
     * Calculates the number of days elapsed since the progress on the learning path item was started.
     * @summary
     * This method calculates the number of days elapsed since the learning path item was started by the enrolled student.
     * If there is no progress yet, the method returns 0.
     * If the item is completed, the method returns the number of days between the start and completion dates.
     * @return The number of days elapsed since the progress was started, or 0 if there is no progress on the item.
     */
    public long calculateDaysElapsed() {
        if(ProgressStatus.NOT_STARTED.equals(status)) return 0;
        var defaultTimeZone = ZoneId.systemDefault();
        var fromDate = this.statedAt.toInstant().atZone(defaultTimeZone);
        var toDate = Objects.isNull(this.completedAt)
                ? LocalDate.now().atStartOfDay(defaultTimeZone).toInstant()
                : this.completedAt.toInstant();
        return Duration.between(fromDate, toDate).toDays();
    }

}
