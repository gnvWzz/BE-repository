package com.codegym.springboot_modul_6.Controller.configuration;

import com.codegym.springboot_modul_6.Service.FE_SF_Service.AccountService;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
//    @Autowired
//    private AccountService accountService;
    @Bean
    public ModelMapper mapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;

    }


//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
//    }

}
