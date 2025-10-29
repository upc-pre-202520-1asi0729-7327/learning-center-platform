package com.acme.center.platform.learning.domain.model.queries;

import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;

/**
 * Query to get student by profile id
 */
public record GetStudentByProfileIdQuery(ProfileId profileId) {
    /**
     * Constructor
     *
     * @param profileId Profile id
     *                  Must not be null or blank
     * @throws IllegalArgumentException If profile id is null or blank
     */
    public GetStudentByProfileIdQuery {
        if (profileId == null || profileId.profileId() == null || profileId.profileId() <= 0)
            throw new IllegalArgumentException("Profile id cannot be null or less than or equal to 0");
    }
}
