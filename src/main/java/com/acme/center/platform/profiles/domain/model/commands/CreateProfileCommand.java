package com.acme.center.platform.profiles.domain.model.commands;

/**
 * Command to create a new profile.
 *
 * @param firstName the first name of the person
 * @param lastName  the last name of the person
 * @param email     the email address
 * @param street    the street address
 * @param number    the street number
 * @param city      the city
 * @param postalCode the postal code
 * @param country   the country
 */
public record CreateProfileCommand(String firstName,
                                   String lastName,
                                   String email,
                                   String street,
                                   String number,
                                   String city,
                                   String postalCode,
                                   String country) {
}
