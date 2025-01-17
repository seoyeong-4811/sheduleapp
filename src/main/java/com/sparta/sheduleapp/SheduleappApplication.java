package com.sparta.sheduleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SheduleappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SheduleappApplication.class, args);
    }

}
