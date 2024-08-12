package com.tantan.SaleCourse.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
    private static final List<String> WHITE_LISTED_PATHS = Arrays.asList(
            "/api/v1/auth/**",
            "/api/v1/program/**",
            "/api/v1/subject/**",
            "/api/v1/topic/**"
    );
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LISTED_PATHS.toArray(new String[0]))
                                .permitAll()
                                .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/teacher/**").hasAuthority("TEACHER")
                                .requestMatchers("/api/v1/user/**").hasAuthority("USER")
//                                .requestMatchers("/api/v1/admin/**").hasAnyRole("ADMIN")
//                                .requestMatchers(GET, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MEMBER_READ.name())
//                                .requestMatchers(POST, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MEMBER_CREATE.name())
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}