package com.shn.apigateway.config;



public class SwaggerConfig {
/*
    private static final String API_URL = "/v3/api/docs";
    private final RouteDefinitionLocator locator;
    public SwaggerConfig(RouteDefinitionLocator locator) {
        this.locator = locator;
    }

    @Bean
    public GroupedOpenApi apis(SwaggerUiConfigProperties swaggerUiConfigProperties) {
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();

        locator.getRouteDefinitions().subscribe(routeDefinition -> {
            String resourceName = routeDefinition.getId();
            String location = routeDefinition.getPredicates().get(0).getArgs().get("_genkey_0").replace("/**", API_URL);
            urls.add(new AbstractSwaggerUiConfigProperties.SwaggerUrl(resourceName, location, resourceName));
        });

        swaggerUiConfigProperties.setUrls(urls);

        return GroupedOpenApi.builder()
                .group("resource")
                .pathsToMatch("/api/**")
                .build();
    }


 */
}
