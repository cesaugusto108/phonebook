package augusto108.ces.phonebook;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(initializers = {TestContainersConfiguration.Initializer.class})
public class TestContainersConfiguration {

	private static final MariaDBContainer<?> MARIA_DB_CONTAINER = new MariaDBContainer<>("mariadb:10.11.4");

	private static void startContainer() {
		Startables.deepStart(MARIA_DB_CONTAINER).join();
	}

	private static Map<String, Object> createTestContainersConnectionConfiguration() {
		final Map<String, Object> map = new HashMap<>();
		map.put("spring.datasource.url", MARIA_DB_CONTAINER.getJdbcUrl());
		map.put("spring.datasource.username", MARIA_DB_CONTAINER.getUsername());
		map.put("spring.datasource.password", MARIA_DB_CONTAINER.getPassword());
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
