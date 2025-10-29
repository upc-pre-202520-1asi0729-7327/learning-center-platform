package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;

/**
 * Query to get a learning path item by course id and tutorial id.
 */
public record GetLearningPathItemByCourseIdAndTutorialIdQuery(Long courseId, TutorialId tutorialId) {
    /**
     * Constructor to validate the query.
     * @param courseId The course id.
     *                 It cannot be null or less than or equal to 0.
     * @param tutorialId The tutorial id.
     *                   It cannot be null or less than or equal to 0.
     * @throws IllegalArgumentException If the courseId or tutorialId is null or less than or equal to 0.
     */
    public GetLearningPathItemByCourseIdAndTutorialIdQuery {
        if (courseId == null || courseId <= 0)
            throw new IllegalArgumentException("courseId cannot be null or less than or equal to 0");
        if (tutorialId == null || tutorialId.tutorialId() == null || tutorialId.tutorialId() <= 0)
            throw new IllegalArgumentException("tutorialId cannot be null or less than or equal to 0");
    }
}
