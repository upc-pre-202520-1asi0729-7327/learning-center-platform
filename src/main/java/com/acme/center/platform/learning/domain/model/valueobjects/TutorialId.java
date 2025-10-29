package com.acme.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


/**
 * TutorialId value object
 * @summary
 * This value object represents the unique identifier of a tutorial.
 * The tutorialId must be greater than 0. It throws an IllegalArgumentException if the tutorialId is null or less than or equal to 0.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record TutorialId(Long tutorialId) {

    public TutorialId {
        if (tutorialId == null || tutorialId < 0) {
            throw new IllegalArgumentException("TutorialId cannot be null or less than or equal to 0");
        }
    }
}
