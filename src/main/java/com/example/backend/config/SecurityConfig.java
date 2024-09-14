package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.backend.service.UserDetailsServiceImpl;
import com.example.backend.config.JwtRequestFilter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // Inject your UserDetailsService
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter; // Inject your JWT filter
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors() // Enable CORS
            .and()
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers("/api/auth/register", "/api/auth/login", "/error", "/admin/**", "/api/feedback/**","/api/hire","/api/local","/api/book-now").permitAll() // Allow registration, login, and error page
            .anyRequest().authenticated() // All other endpoints require authentication
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // No session creation (JWT)
        
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // Use your UserDetailsService
        return authenticationManagerBuilder.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of(
                "https://work4-you-frontend.vercel.app",
                "https://supreme-space-pancake-j6gjjw579r635qqw-3000.app.github.dev"
            ));
        configuration.addAllowedHeader("*"); // Allow all headers
        configuration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
