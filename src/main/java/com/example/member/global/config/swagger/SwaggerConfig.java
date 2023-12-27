package com.example.member.global.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Spring Boot App", description = "designed by hankyu", version = "1.0"))
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        var securityScheme = new SecurityScheme();
        securityScheme.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        var securityRequirement = new SecurityRequirement().addList("bearerAuth");
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(new ArrayList<>(List.of(securityRequirement)) );
    }
}
