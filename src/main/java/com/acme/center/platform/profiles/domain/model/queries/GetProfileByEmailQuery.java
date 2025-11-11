package com.acme.center.platform.profiles.domain.model.queries;

import com.acme.center.platform.profiles.domain.model.valueobjects.EmailAddress;

/**
 * Query to get a profile by email address.
 *
 * @param emailAddress the email address of the profile
 */
public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
