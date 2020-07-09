package com.instagram.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@EnableSwagger2
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Value("${spring.application.version}")
    private String version;

    @Autowired
    private RouteLocator routeLocator;

    @Bean
    public Docket api() {
        AuthorizationScope[] authScopes = new AuthorizationScope[0];
        SecurityReference securityReference = SecurityReference.builder()
                .reference("bearer_token")
                .scopes(authScopes)
                .build();

        List<SecurityContext> securityContexts = Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(securityReference))
                        .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(Optional.class)
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(securityContexts);
    }



    private SecurityScheme securityScheme() {
        return new ApiKey("bearer_token", "Authorization", "header");
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Gate Service")
                .description("API Gateway")
                .version(version)
                .build();
    }

    @Override
    public List<SwaggerResource> get() {
        return routeLocator.getRoutes().stream()
                .map(route -> swaggerResource(
                        route.getId(),
                        route.getFullPath().replace("/**", "/v2/api-docs"), "2.0"))
                .collect(Collectors.toList());
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
