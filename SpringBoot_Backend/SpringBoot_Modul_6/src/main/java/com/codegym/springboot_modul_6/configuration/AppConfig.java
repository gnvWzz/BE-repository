package com.codegym.springboot_modul_6.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.codegym.springboot_modul_6")
public class AppConfig implements WebMvcConfigurer {
}
