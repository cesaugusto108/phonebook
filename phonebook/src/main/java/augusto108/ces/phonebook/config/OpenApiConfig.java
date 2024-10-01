package augusto108.ces.phonebook.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class OpenApiConfig
{

	@Bean public OpenAPI openAPIConfig()
	{
		return new OpenAPI().info(new Info().title("Phonebook").description("Phonebook application").version("v1"));
	}
}
