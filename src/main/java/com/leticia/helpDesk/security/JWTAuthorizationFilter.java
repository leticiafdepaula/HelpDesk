package com.leticia.helpDesk.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    UserDetailsService userDetailsService;


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println("Nenhum token JWT encontrado.");
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7).trim();
        System.out.println(" Token recebido: " + token);

        if (token.isEmpty()) {
            System.out.println("Token vazio ou inválido.");
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);

        if (authenticationToken != null) {
            System.out.println("Usuário autenticado: " + authenticationToken.getName());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            System.out.println("Token inválido. Nenhuma autenticação foi realizada.");
        }

        chain.doFilter(request, response);
    }



    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        System.out.println("Validando token...");

        if (jwtUtil.tokenValido(token)) {
            String username = jwtUtil.getUsername(token);
            System.out.println("Token válido. Usuário extraído: " + username);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println(" Usuário encontrado: " + userDetails.getUsername());

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }


        System.out.println(" Token inválido.");
        return null;
    }

}
