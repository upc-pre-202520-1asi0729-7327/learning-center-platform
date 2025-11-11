package com.acme.center.platform.iam.interfaces.acl;

import java.util.List;

/**
 * Facade interface for IAM (Identity and Access Management) context operations.
 * This interface provides methods to manage users within the IAM system,
 * including creating users and retrieving user information by username or ID.
 */
public interface IamContextFacade {
    /**
     * Creates a new user with the specified username and password.
     *
     * @param username the username for the new user
     * @param password the password for the new user
     * @return the ID of the newly created user
     */
    Long createUser(String username, String password);

    /**
     * Creates a new user with the specified username, password, and roles.
     *
     * @param username the username for the new user
     * @param password the password for the new user
     * @param roleNames the list of role names to assign to the user
     * @return the ID of the newly created user
     */
    Long createUser(String username, String password, List<String> roleNames);

    /**
     * Fetches the user ID associated with the given username.
     *
     * @param username the username to search for
     * @return the user ID if found, or null if the user does not exist
     */
    Long fetchUserIdByUsername(String username);

    /**
     * Fetches the username associated with the given user ID.
     *
     * @param userId the user ID to search for
     * @return the username if found, or null if the user does not exist
     */
    String fetchUsernameByUserId(Long userId);
}
