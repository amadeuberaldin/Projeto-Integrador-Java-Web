package com.mycompany.Minha_despensa_Web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        csrfTokenRepository.setSessionAttributeName("_csrf");

        http
                .csrf(csrf -> csrf
                .csrfTokenRepository(csrfTokenRepository)
                )
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login", "/register", "/produtos/*", "/usuario/cadastro", "/usuario/salvar", "/estilo.css", "/favicon.ico", "/Script.js").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true) // Redireciona para /home após o login
                .permitAll()
                )
                .logout((logout) -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
