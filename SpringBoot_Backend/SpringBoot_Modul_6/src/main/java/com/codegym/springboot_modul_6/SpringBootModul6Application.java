package com.codegym.springboot_modul_6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.codegym.springboot_modul_6.repository")
public class SpringBootModul6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootModul6Application.class, args);
    }

}
