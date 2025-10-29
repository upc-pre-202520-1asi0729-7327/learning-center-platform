package com.acme.center.platform.learning.domain.model.commands;

import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;

/**
 * Command to add a tutorial to a course learning path
 * @param tutorialId the tutorial id.
 *                   Cannot be null or less than 1
 * @param courseId the course id.
 *                 Cannot be null or less than 1
 * @see TutorialId
 */
public record AddTutorialToCourseLearningPathCommand(TutorialId tutorialId, Long courseId) {
    /**
     * Constructor
     * @param tutorialId the tutorial id.
     *                   Cannot be null or less than 1
     * @param courseId the course id.
     *                 Cannot be null or less than 1
     * @throws IllegalArgumentException if tutorialId is null or less than 1
     * @throws IllegalArgumentException if courseId is null or less than 1
     */
    public AddTutorialToCourseLearningPathCommand {
        if (tutorialId == null || tutorialId.tutorialId() <= 0) {
            throw new IllegalArgumentException("tutorialId cannot be null or less than 1");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than 1");
        }
    }
}
