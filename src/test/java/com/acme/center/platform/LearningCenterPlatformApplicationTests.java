package com.acme.center.platform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic smoke test that verifies the Spring application context loads successfully.
 *
 * <p>This test class contains a single sanity test used by CI to ensure that the
 * Spring Boot configuration is valid and that the application context starts
 * without errors. It is intentionally lightweight and does not assert on
 * application behavior.</p>
 */
@SpringBootTest
class LearningCenterPlatformApplicationTests {

    /**
     * Ensures the Spring context can be created. A successful execution implies
     * that basic wiring and configuration are correct for the application.
     */
    @Test
    void contextLoads() {
    }

}
