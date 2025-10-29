package com.acme.center.platform.learning.domain.model.entities;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * LearningPathItem entity
 * @summary
 * This entity represents a learning path item.
 * A learning path item is a course with a tutorialId and a reference to the next item in the learning path.
 * @see Course
 * @see TutorialId
 * @since 1.0
 */
@Getter
@Entity
public class LearningPathItem extends AuditableModel {
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    @NotNull
    @Embedded
    @Column(name = "tutorial_id")
    private TutorialId tutorialId;

    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LearningPathItem nextItem;

    public LearningPathItem(Course course, TutorialId tutorialId, LearningPathItem nextItem) {
        this.course = course;
        this.tutorialId = tutorialId;
        this.nextItem = nextItem;
    }

    public LearningPathItem() {
        this.tutorialId = new TutorialId(0L);
        this.nextItem = null;
    }

    /**
     * Update the next item in the learning path
     * @param nextItem The next item in the learning path
     */
    public void updateNextItem(LearningPathItem nextItem) {
        this.nextItem = nextItem;
    }
}
