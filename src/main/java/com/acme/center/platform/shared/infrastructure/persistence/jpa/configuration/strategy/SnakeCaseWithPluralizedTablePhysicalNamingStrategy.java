package com.acme.center.platform.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/**
 * Hibernate {@link PhysicalNamingStrategy} that converts entity and attribute
 * identifiers into snake_case and pluralizes table names.
 *
 * <p>Converted identifiers follow these rules:
 * <ul>
 *   <li>Catalog, schema, sequence and column names are converted from camelCase
 *       (or PascalCase) to snake_case (for example {@code someField -> some_field}).</li>
 *   <li>Table identifiers are first pluralized (for example {@code User -> Users})
 *       then converted to snake_case (for example {@code UserProfile -> user_profiles}).</li>
 * </ul>
 *</p>
 *
 * <p>This strategy preserves {@code null} identifiers by returning {@code null}
 * when an input identifier is {@code null}. The goal is to produce SQL-friendly
 * names that match common conventions in relational schemas.</p>
 *
 * <p>Note: Pluralization uses the external <code>pluralize</code> utility; if
 * your domain model contains irregular plurals you may need to adjust the
 * pluralization rules or replace the utility.</p>
 */
public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(this.toPlural(identifier));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Convert the given {@link Identifier} text to snake_case.
     *
     * <p>Examples:
     * <ul>
     *   <li>{@code someField -> some_field}</li>
     *   <li>{@code HTTPServer -> http_server}</li>
     * </ul>
     * If {@code identifier} is {@code null} this method returns {@code null}.
     *</p>
     *
     * @param identifier identifier to convert; may be {@code null}
     * @return new {@link Identifier} with snake_cased text, or {@code null}
     */
    private Identifier toSnakeCase(final Identifier identifier) {
        if (identifier == null) return null;
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText()
                .replaceAll(regex, replacement)
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }

    /**
     * Pluralize the identifier's text using the configured pluralize utility.
     *
     * <p>This method expects a non-null {@code identifier}. If a caller may pass
     * {@code null}, it should guard accordingly before calling.</p>
     *
     * @param identifier identifier to pluralize; expected to be non-null
     * @return new {@link Identifier} with pluralized text
     * @throws NullPointerException if {@code identifier} is {@code null}
     */
    private Identifier toPlural(final Identifier identifier) {
        final String newName = pluralize(identifier.getText());
        return Identifier.toIdentifier(newName);
    }
}
