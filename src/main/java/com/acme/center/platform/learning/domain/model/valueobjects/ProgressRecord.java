package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


/**
 * ProgressRecord is a value object that represents the progress of a student in a learning path.
 * It is embedded in the Enrollment aggregate.
 * It contains a list of ProgressRecordItems, each representing the progress of a student in a tutorial.
 * ProgressRecord provides methods to start a tutorial, calculate days elapsed for an enrollment, etc.
 */
@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;

    /**
     * Default constructor
     */
    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }

    /**
     * Initializes the progress record with the first tutorial in the learning path.
     * @param enrollment Enrollment
     * @param learningPath LearningPath
     * @see LearningPath
     * @see Enrollment
     */
    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath) {
        if (learningPath.isEmpty()) return;
        TutorialId tutorialId = learningPath.getFirstTutorialInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorialId);
        progressRecordItems.add(progressRecordItem);
    }

    /**
     * Get the ProgressRecordItem with the given TutorialId
     * @param tutorialId TutorialId
     * @return ProgressRecordItem object or null if not found
     * @see ProgressRecordItem
     * @see TutorialId
     */
    private ProgressRecordItem getProgressRecordItemWithTutorialId(TutorialId tutorialId) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Check if there is a tutorial in progress
     * @return boolean with true if there is a tutorial in progress, false otherwise
     */
    private boolean hasAnItemInProgress() {
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    /**
     * Start a tutorial with the given TutorialId
     * @param tutorialId TutorialId
     * @throws IllegalStateException if a tutorial is already in progress
     * @throws IllegalStateException if the tutorial with the given ID is already started or completed
     * @throws IllegalArgumentException if the tutorial with the given ID is not found in the progress record
     * @see TutorialId
     * @see ProgressRecordItem
     */
    public void startTutorial(TutorialId tutorialId) {

        if (hasAnItemInProgress()) throw new IllegalStateException("A tutorial is already in progress");

        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) {
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalStateException("Tutorial with given Id is already started or completed");
        }
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");
    }

    /**
     * Complete a tutorial with the given TutorialId
     * @param tutorialId the TutorialId of the tutorial to be completed
     * @param learningPath the LearningPath object representing the learning path
     * @throws IllegalArgumentException if the tutorial with the given ID is not found in the progress record
     * @see TutorialId
     * @see LearningPath
     */
    public void completeTutorial(TutorialId tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.complete();
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");
        if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;
        TutorialId nextTutorialId = learningPath.getNextTutorialInLearningPath(tutorialId);
        if (nextTutorialId != null) {
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
            progressRecordItems.add(nextProgressRecordItem);
        }
    }

    /**
     * Calculate the days elapsed for a given enrollment
     * @param enrollment Enrollment
     * @return long with the days elapsed for the given enrollment
     * @see Enrollment
     */
    public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed)
                .sum();
    }
}
