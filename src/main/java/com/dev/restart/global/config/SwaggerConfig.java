package com.dev.restart.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        SecurityRequirement requirement = new SecurityRequirement().addList("BearerAuth");

        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .addSecurityItem(requirement)
                .schemaRequirement("BearerAuth", securityScheme());
    }

    @Bean
    public GroupedOpenApi api(){
        String[] paths = {"/api/v1/**"};
        String[] packagesToScan = {"com.dev.restart"};
        return GroupedOpenApi.builder().group("springdoc-openapi")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }

    private Info apiInfo(){
        return new Info()
                .title("Re:Start API")
                .description("Restart 서비스를 위한 Swagger UI 입니다.")
                .version("1.0.0");
    }

    private SecurityScheme securityScheme(){
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");
    }
}
