package com.acme.center.platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Resource representing a user in the system.
 *
 * @param id       the unique identifier of the user
 * @param username the username of the user
 * @param roles    the list of roles assigned to the user
 */
public record UserResource(Long id, String username, List<String> roles) {
}
