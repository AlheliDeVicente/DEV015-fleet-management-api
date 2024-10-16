package com.example.demo.security;

import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtAuthEntryPoint authEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz.requestMatchers("/api/login")
                .permitAll()
                .requestMatchers(HttpMethod.GET)
                .permitAll()
                .requestMatchers(HttpMethod.DELETE, "/users/**")
                .hasRole("USER")
                .requestMatchers(HttpMethod.POST)
                .permitAll()
                .requestMatchers(HttpMethod.PUT, "/users/**")
                .hasRole("USER")
                .anyRequest()
                .authenticated());

        http.exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(authEntryPoint));
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(c -> c.disable());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}