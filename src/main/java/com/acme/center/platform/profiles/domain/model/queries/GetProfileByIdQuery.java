package com.acme.center.platform.profiles.domain.model.queries;

/**
 * Query to get a profile by its ID.
 *
 * @param profileId the ID of the profile to retrieve
 */
public record GetProfileByIdQuery(Long profileId) {
}
