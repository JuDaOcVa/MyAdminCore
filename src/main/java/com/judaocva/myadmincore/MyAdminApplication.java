package com.judaocva.myadmincore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@AutoConfiguration
@EntityScan(basePackages = "com.judaocva.myadmincore.models.entities")
public class MyAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAdminApplication.class, args);
    }

}
