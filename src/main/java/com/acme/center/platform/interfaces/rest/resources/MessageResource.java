package com.acme.center.platform.interfaces.rest.resources;

/**
 * Simple immutable resource used to return textual messages from REST endpoints.
 *
 * <p>Implemented as a Java {@code record} so the type is immutable and concise.
 * This resource is commonly returned by controllers for health checks,
 * status responses, or other simple message-bearing endpoints.</p>
 *
 * @param message the textual payload returned to API clients; should be short
 *                and human-readable (must not be {@code null} in typical use).
 */
public record MessageResource(String message) {
}
