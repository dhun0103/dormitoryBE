package com.example.dormitorybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DormitoryBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryBeApplication.class, args);
    }

}