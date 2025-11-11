package com.acme.center.platform.iam.interfaces.rest.transform;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.acme.center.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

/**
 * Assembler to convert a User entity and token to an AuthenticatedUserResource.
 */
public class AuthenticatedUserResourceFromEntityAssembler {
    /**
     * Converts a User entity and token to an AuthenticatedUserResource.
     * @param user the User entity
     * @param token the authentication token
     * @return the AuthenticatedUserResource
     */
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
