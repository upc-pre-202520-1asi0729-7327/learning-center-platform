package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

/**
 * Course aggregate root
 * @summary
 * This aggregate root represents a course.
 * A course is a learning resource with a title, description, and a learning path.
 * @see LearningPath
 * @since 1.0
 */
@Getter
@Entity
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private String title;
    private String description;

    @Embedded
    private final LearningPath learningPath;

    /**
     * Create a new course
     */
    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        learningPath = new LearningPath();
    }

    /**
     * Create a new course with the given title and description
     * @param title The title of the course
     * @param description The description of the course
     */
    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    /**
     * Create a new course with information from the command
     * @param command The command to create the course
     * @see CreateCourseCommand
     */
    public Course(CreateCourseCommand command) {
        this.title = command.title();
        this.description = command.description();
        learningPath = new LearningPath();
    }

    /**
     * Add a tutorial to the learning path
     * @param tutorialId The id of the tutorial
     */
    public void addTutorialToLearningPath(TutorialId tutorialId) {
        this.learningPath.addItem(this, tutorialId);
    }

    /**
     * Add a tutorial to the learning path
     * @param tutorialId The id of the tutorial
     * @param nextTutorialId The id of the next tutorial
     */
    public void addTutorialToLearningPath(TutorialId tutorialId, TutorialId nextTutorialId) {
        this.learningPath.addItem(this, tutorialId, nextTutorialId);
    }
}
