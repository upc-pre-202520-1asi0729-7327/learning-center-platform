package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Learning path item resource.
 */
public record LearningPathItemResource(Long learningPathItemId, Long courseId, Long tutorialId) {
}
