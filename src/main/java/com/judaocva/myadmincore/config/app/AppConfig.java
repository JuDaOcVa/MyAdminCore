package com.judaocva.myadmincore.config.app;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {

    @Value("${spring.application.version}")
    private String appVersion;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.language}")
    private String language;
}