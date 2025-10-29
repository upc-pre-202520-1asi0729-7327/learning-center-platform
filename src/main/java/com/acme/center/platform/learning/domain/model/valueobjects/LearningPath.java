package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * LearningPath value object
 *
 * @summary This value object represents a learning path.
 * A learning path is a list of learning path items.
 * @see LearningPathItem
 * @since 1.0
 */
@Embeddable
public class LearningPath {
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

    /**
     * Default constructor
     * @summary
     * Initialize the learning path with an empty list of learning path items
     */
    public LearningPath() {
        this.learningPathItems = List.of();
    }

    /**
     * Get the learning path item with the given id
     *
     * @param itemId The id of the learning path item
     * @return The learning path item with the given id
     */
    private LearningPathItem getLearningPathItemWithId(Long itemId) {
        return this.getFirstLearningPathItemWhere(item -> item.getId().equals(itemId));
    }

    /**
     * Get the learning path item with the given tutorial id
     *
     * @param tutorialId The tutorial id of the learning path item
     * @return The learning path item with the given tutorial id
     */
    public LearningPathItem getLearningPathItemWithTutorialId(TutorialId tutorialId) {
        return this.getFirstLearningPathItemWhere(item -> item.getTutorialId().equals(tutorialId));
    }

    /**
     * Get the next tutorial in the learning path
     *
     * @param currentTutorialId The id of the current tutorial
     * @return The id of the next tutorial in the learning path
     */
    public TutorialId getNextTutorialInLearningPath(TutorialId currentTutorialId) {
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(currentTutorialId).getNextItem();
        return !Objects.isNull(nextItem) ? nextItem.getTutorialId() : null;
    }

    /**
     * Check if the given tutorial is the last tutorial in the learning path
     *
     * @param currentTutorialId The id of the current tutorial
     * @return True if the given tutorial is the last tutorial in the learning path, false otherwise
     */
    public boolean isLastTutorialInLearningPath(TutorialId currentTutorialId) {
        return Objects.isNull(getNextTutorialInLearningPath(currentTutorialId));
    }

    /**
     * Get the first tutorial in the learning path
     *
     * @return The id of the first tutorial in the learning path
     */
    public TutorialId getFirstTutorialInLearningPath() {
        return learningPathItems.getFirst().getTutorialId();
    }

    /**
     * Get the last tutorial in the learning path
     *
     * @return The id of the last tutorial in the learning path
     */
    public LearningPathItem getLastItemInLearningPath() {
        return this.getFirstLearningPathItemWhere(item -> Objects.isNull(item.getNextItem()));
    }

    /**
     * Get the first learning path item that satisfies the given predicate
     *
     * @param predicate The predicate to satisfy
     * @return The first learning path item that satisfies the given predicate
     * @see Predicate
     */
    private LearningPathItem getFirstLearningPathItemWhere(Predicate<LearningPathItem> predicate) {
        return learningPathItems.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    /**
     * Check if the learning path is empty
     *
     * @return True if the learning path is empty, false otherwise
     */
    public boolean isEmpty() {
        return learningPathItems.isEmpty();
    }

    /**
     * Add a new item to the learning path
     *
     * @param course     The course of the new item
     * @param tutorialId The tutorial id of the new item
     * @param nextItem   The next item in the learning path
     */
    public void addItem(Course course, TutorialId tutorialId, LearningPathItem nextItem) {
        // Add the new item to the learning path before the next item
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, nextItem);
        learningPathItems.add(learningPathItem);
    }

    /**
     * Add a new item to the learning path
     *
     * @param course     The course of the new item
     * @param tutorialId The tutorial id of the new item
     */
    public void addItem(Course course, TutorialId tutorialId) {
        // Add the new item to the end of the learning path
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, null);
        LearningPathItem originalLastItem = null;
        if (!isEmpty()) originalLastItem = getLastItemInLearningPath();
        learningPathItems.add(learningPathItem);
        if (!Objects.isNull(originalLastItem)) originalLastItem.updateNextItem(learningPathItem);
    }

    /**
     * Add a new item to the learning path before the next item
     *
     * @param course         The course of the new item
     * @param tutorialId     The tutorial id of the new item
     * @param nextTutorialId The tutorial id of the next item
     */
    public void addItem(Course course, TutorialId tutorialId, TutorialId nextTutorialId) {
        // Add the new item to the learning path before the next item
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(nextTutorialId);
        addItem(course, tutorialId, nextItem);
    }

}
