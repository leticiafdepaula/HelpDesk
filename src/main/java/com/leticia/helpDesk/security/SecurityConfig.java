package com.leticia.helpDesk.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig  {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        log.info("Configuração de segurança carregada!");

        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").disable())  // CSRF desativado para a H2 Console
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // API sem estado
                .authorizeRequests(req -> req
                        .requestMatchers("/auth/**", "/h2-console/**").permitAll()  // Permite acessos públicos
                        .anyRequest().authenticated())  // Exige autenticação para outras rotas
                .headers(headers -> headers.frameOptions().disable())  // Permite o console do H2
                .addFilter(new JWTAuthenticationFilter(authenticationManager(http), jwtUtil))  // Filtro JWT
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        // Configuração do UserDetailsService e PasswordEncoder
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bcryptPasswordEncoder()); // Corrigido para usar o método @Bean

        return authenticationManagerBuilder.build();  // Retorna o AuthenticationManager
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();  // Criação do bean do encoder
    }
}




