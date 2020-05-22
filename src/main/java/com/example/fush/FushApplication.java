package com.example.fush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.fush.dao")
public class FushApplication {

    public static void main(String[] args) {
        SpringApplication.run(FushApplication.class, args);
    }

}
