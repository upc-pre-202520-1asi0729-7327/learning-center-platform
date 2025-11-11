package com.acme.center.platform.iam.interfaces.rest.resources;

/**
 * Resource representing an authenticated user.
 *
 * @param id       the unique identifier of the user
 * @param username the username of the user
 * @param token    the authentication token
 */
public record AuthenticatedUserResource(Long id, String username, String token) {

}
