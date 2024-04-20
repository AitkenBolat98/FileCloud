package com.example.cloudfilestorage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(httpSecurityCsrfConfigurer -> {httpSecurityCsrfConfigurer.disable();})
                .authorizeHttpRequests(authorize ->{
                    authorize.requestMatchers("/api/main","/api/main/registration","/api/main/login")
                    .permitAll().anyRequest().authenticated();})
                .formLogin(form -> form
                        .loginPage("/api/main/login")
                        .loginProcessingUrl("/api/main/login")
                        .defaultSuccessUrl("/api/main",true)
                        .permitAll());
        return http.build();
    }
    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}
