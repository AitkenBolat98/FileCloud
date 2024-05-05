package com.example.cloudfilestorage.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@RequiredArgsConstructor
@EnableWebSecurity
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
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(1)
                        .expiredUrl("/api/main/login?expired")
                        .sessionRegistry(sessionRegistry()));
        return http.build();
    }
    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher(){
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

}
