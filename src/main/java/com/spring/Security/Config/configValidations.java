package com.spring.Security.Config;

import com.spring.Security.Services.Models.validations.UserValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configValidations {

    @Bean
    public UserValidation userValidation(){
        return new UserValidation();
    }
}
