package com.acme.center.platform.iam.interfaces.rest.resources;

/**
 * Resource for user sign-in.
 * @param username the username
 * @param password the password
 */
public record SignInResource(String username, String password) {
}
