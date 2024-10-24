package com.sec.leetcodesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Enforce HTTPS for all requests
        http
            .requiresChannel()
            .anyRequest()
            .requiresSecure();  // Ensure all requests go through HTTPS
        
        // Configure security settings
        http
            .csrf().disable()  // Disable CSRF (optional, enable it if using forms)
            .authorizeRequests()
            .anyRequest().permitAll()  // Allow access to all endpoints without authentication for now (you can customize this)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // Sessions created only if required
            .and()
            .httpBasic();  // Basic authentication for testing; replace with JWT or session-based auth for production

        // Optional: Disable frame options (for H2 console access)
        http.headers().frameOptions().disable();

        return http.build();
    }

    // Password encoder (BCrypt is commonly used for password hashing)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
