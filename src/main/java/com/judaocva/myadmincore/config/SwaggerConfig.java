package com.judaocva.myadmincore.config;

import com.judaocva.myadmincore.config.app.AppConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final AppConfig appConfig;

    public SwaggerConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(appConfig.getAppName())
                        .version(appConfig.getAppVersion())
                        .description("API documentation for MyAdmin application")
                        .summary("MyAdmin API Documentation")
                        .termsOfService("https://swagger.io/terms/")
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("https://springdoc.org"))
                        .contact(new io.swagger.v3.oas.models.info.Contact().email("juandavidocampo80@gmail.com").name("Juan David Ocampo Valencia").url("https://github.com/JuDaOcVa"))
                );
    }
}