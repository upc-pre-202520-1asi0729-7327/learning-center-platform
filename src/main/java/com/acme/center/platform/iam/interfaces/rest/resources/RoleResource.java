package com.acme.center.platform.iam.interfaces.rest.resources;

/**
 * Resource representing a role.
 * @param id the unique identifier of the role
 * @param name the name of the role
 */
public record RoleResource(Long id, String name) {
}
