package com.spring.blogapp_Springboot.Security;

import com.spring.blogapp_Springboot.users.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfiguration {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final JWTService jwtService;
    private final UsersService usersService;
    public AppSecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter, JWTService jwtService, UsersService usersService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Bean
    JWTAuthenticationFilter getJwtAuthenticationFilter() throws Exception{
        return new JWTAuthenticationFilter(
                new JWTAuthenticationManager(jwtService, usersService));
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/articles", "/articles/**")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
    }
}
