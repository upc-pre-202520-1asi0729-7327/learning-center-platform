package com.acme.center.platform.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Abstract base class for auditable aggregate roots.
 * Provides common auditing fields like ID, createdAt, and updatedAt.
 * Extends AbstractAggregateRoot to support domain events.
 *
 * @param <T> the type of the aggregate root
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public void addDomainEvent(Object event) {
        this.registerEvent(event);
    }
}
