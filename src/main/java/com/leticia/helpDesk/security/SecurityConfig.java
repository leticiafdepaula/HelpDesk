package com.leticia.helpDesk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        requestMatchers("/h2-console/**").permitAll()
                .and().headers().frameOptions().sameOrigin();
        http.authorizeHttpRequests().anyRequest();
        return http.build();
    }
}
