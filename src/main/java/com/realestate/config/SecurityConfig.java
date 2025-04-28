package com.realestate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()
            .authorizeRequests()  // Use authorizeRequests instead of antMatchers
            .requestMatchers(
            		"/login",
            		"/login/process", 
            		"/register", 
            		"/register/process",
            		"/css/**", 
            		"/js/**").permitAll()  // Allow access to login page and static resources
            .and()
            .formLogin()
                .loginPage("/login")  // Custom login page
                .defaultSuccessUrl("/index", true)  // Redirect after successful login
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/login?logout")  // Redirect after logout
                .permitAll();

        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder)
    {
        UserDetails user = User.builder()
            .username("user")
            .password(encoder.encode("password"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

