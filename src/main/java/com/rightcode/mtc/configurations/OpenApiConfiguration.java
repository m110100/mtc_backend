package com.rightcode.mtc.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI mtcOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API")
                        .description("")
                        .version("1.0"));
    }
}
