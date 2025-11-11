package com.acme.center.platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Resource for user sign-up.
 *
 * @param username the username for the new user
 * @param password the password for the new user
 * @param roles    the list of roles to assign to the user
 */
public record SignUpResource(String username, String password, List<String> roles) {
}
