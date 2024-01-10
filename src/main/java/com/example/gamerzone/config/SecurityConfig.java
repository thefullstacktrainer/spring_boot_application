// com.example.gamerzone.config.SecurityConfig
package com.example.gamerzone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//com.example.gamerzone.config.SecurityConfig
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired
 private UserDetailsService userDetailsService;

 @Override
 protected void configure(HttpSecurity http) throws Exception {
     http.authorizeRequests()
         .antMatchers("/api/games/list").permitAll()
         .antMatchers("/api/games/**").authenticated()
         .antMatchers("/user/signup", "/user/login").permitAll()
         .and().formLogin().loginPage("/user/login").permitAll()
         .and().logout().permitAll();

     // Enable basic authentication
     http.httpBasic();

     // Enable Cross-Origin Resource Sharing (CORS)
     http.cors();

     // Disable CSRF protection (for simplicity, enable it in production)
     http.csrf().disable();
 }

 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     // In-memory user store for simplicity (replace with your own user database)
     auth.inMemoryAuthentication()
         .withUser("user1").password("{noop}password1").roles("USER")
         .and()
         .withUser("user2").password("{noop}password2").roles("USER");
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
     // NoOpPasswordEncoder is used for simplicity (in-memory authentication).
     // In production, use a stronger password encoder.
     return NoOpPasswordEncoder.getInstance();
 }

 @Override
 @Bean
 public AuthenticationManager authenticationManagerBean() throws Exception {
     return super.authenticationManagerBean();
 }

 @Override
 @Bean
 public UserDetailsService userDetailsServiceBean() throws Exception {
     return super.userDetailsServiceBean();
 }
}
