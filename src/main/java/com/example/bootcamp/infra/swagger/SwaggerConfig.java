package com.example.bootcamp.infra.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.bootcamp.infra.util.ConstantsConfiguration.*;

@Configuration

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(API_TITLE)
                        .description(API_DESCRIPTION)
                        .version(API_VERSION));
    }

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group(API_GROUP)
                .pathsToMatch(API_PATHS)
                .build();
    }
}
