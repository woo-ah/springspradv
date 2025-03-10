package com.thc.sprboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringspradvApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringspradvApplication.class, args);
    }
}
