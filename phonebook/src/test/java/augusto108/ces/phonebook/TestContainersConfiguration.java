package augusto108.ces.phonebook;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(initializers = {TestContainersConfiguration.Initializer.class})
public class TestContainersConfiguration {

    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.33");

    private static void startContainer() {
        Startables.deepStart(MY_SQL_CONTAINER).join();
    }

    private static Map<String, Object> createTestContainersConnectionConfiguration() {
        final Map<String, Object> map = new HashMap<>();
        map.put("spring.datasource.url", MY_SQL_CONTAINER.getJdbcUrl());
        map.put("spring.datasource.username", MY_SQL_CONTAINER.getUsername());
        map.put("spring.datasource.password", MY_SQL_CONTAINER.getPassword());
        return map;
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainer();

            final ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
            final MapPropertySource mapPropertySource =
                    new MapPropertySource("testContainer", createTestContainersConnectionConfiguration());
            configurableEnvironment.getPropertySources().addFirst(mapPropertySource);
        }
    }
}
