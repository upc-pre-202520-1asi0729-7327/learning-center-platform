package com.acme.center.platform.learning.domain.model.events;

import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * TutorialCompletedEvent
 * Event that represents the completion of a tutorial
 */
@Getter
public class TutorialCompletedEvent extends ApplicationEvent {
    private final Long enrollmentId;
    private final TutorialId tutorialId;
    /**
     * TutorialCompletedEvent Constructor
     * @param source The source of the event
     * @param enrollmentId The enrollment id
     * @param tutorialId The tutorial id
     * @see ApplicationEvent
     * @see TutorialId
     */
    public TutorialCompletedEvent(Object source, Long enrollmentId, TutorialId tutorialId) {
        super(source);
        this.enrollmentId = enrollmentId;
        this.tutorialId = tutorialId;
    }
}
