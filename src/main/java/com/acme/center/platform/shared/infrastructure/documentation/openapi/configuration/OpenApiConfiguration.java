package com.acme.center.platform.shared.infrastructure.documentation.openapi.configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration that exposes an OpenAPI description for the application.
 *
 * <p>This configuration builds an {@link OpenAPI} bean consumed by Springdoc/OpenAPI
 * tooling so that API documentation (Swagger UI, OpenAPI JSON/YAML) can be
 * generated at runtime.</p>
 *
 * <p>Values for the title, description and version are injected from
 * application properties and used to populate the {@link Info} metadata.</p>
 */
@Configuration
public class OpenApiConfiguration {
    // Properties
    @Value("${spring.application.name}")
    String applicationName;

    @Value("${documentation.application.description}")
    String applicationDescription;

    @Value("${documentation.application.version}")
    String applicationVersion;

    // Methods

    /**
     * Creates the primary {@link OpenAPI} bean describing the API.
     *
     * <p>The produced {@code OpenAPI} instance contains basic metadata such as
     * title, description, version and a reference to external documentation.
     * Springdoc will pick up this bean and expose OpenAPI endpoints like
     * {@code /v3/api-docs} and the Swagger UI if configured.</p>
     *
     * @return a configured {@link OpenAPI} instance containing application metadata
     */
    @Bean
    public OpenAPI learningPlatformOpenApi() {


        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title(this.applicationName)
                        .description(this.applicationDescription)
                        .version(this.applicationVersion)
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ACME Learning Platform wiki Documentation")
                        .url("https://acme-learning-platform.wiki.github.io/docs"));


        return openApi;
    }
}
