package br.com.cbyk.contas_pagar.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
            .group("public-apis")
            .pathsToMatch("/**")
            .build();
    }

    private Info apiInfo() {
        var info = new Info();
        info.setTitle("Api Contas a Pagar");
        return info;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(apiInfo());
    }
}
