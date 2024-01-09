// com.example.gamerzone.config.SecurityConfig
package com.example.gamerzone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory user details (replace with your user store)
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build(),
                User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER", "ADMIN").build()
        );
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/games/list").permitAll()
                .antMatchers("/api/games/**").authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
    }
}
