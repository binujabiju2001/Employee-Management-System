package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory user store for simplicity
        return new InMemoryUserDetailsManager(
            User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Disable CSRF for simplicity (not recommended in production without proper CSRF setup)
            .authorizeRequests()
                .requestMatchers("/employees/**").authenticated()  // Protect employee APIs
                .anyRequest().permitAll()  // Allow all other requests
            .and()
            .formLogin()  // Use default Spring Security login page
                .defaultSuccessUrl("/dashboard.html", true)  // Redirect to dashboard.html after successful login
                .permitAll()  // Allow everyone to access the login page
            .and()
            .httpBasic();  // Use HTTP Basic authentication for API access

        return http.build();
    }
}
