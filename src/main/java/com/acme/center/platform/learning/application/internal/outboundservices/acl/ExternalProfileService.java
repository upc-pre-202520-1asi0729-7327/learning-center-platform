package com.acme.center.platform.learning.application.internal.outboundservices.acl;

import com.acme.center.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.center.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * External Profile Service
 */
@Service
public class ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    /**
     * Constructor
     *
     * @param profilesContextFacade Profiles Context Facade
     */
    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    /**
     * Fetch Profile By Email
     * @param email The email
     * @return An {@link Optional} of {@link ProfileId}
     */
    public Optional<ProfileId> fetchProfileByEmail(String email) {
        var profileId = profilesContextFacade.fetchProfileIdByEmail(email);
        return profileId == 0L ? Optional.empty() : Optional.of(new ProfileId(profileId));
    }

    /**
     * Create Profile
     * @param firstName The first name
     * @param lastName The last name
     * @param email The email
     * @param street The street
     * @param number The number
     * @param city The city
     * @param postalCode The postal code
     * @param country The country
     * @return An {@link Optional} of {@link ProfileId}
     */
    public Optional<ProfileId> createProfile(
            String firstName,
            String lastName,
            String email,
            String street,
            String number,
            String city,
            String postalCode,
            String country) {
        var profileId = profilesContextFacade.createProfile(firstName, lastName, email, street, number, city, postalCode, country);
        return profileId == 0L ? Optional.empty() : Optional.of(new ProfileId(profileId));
    }
}
