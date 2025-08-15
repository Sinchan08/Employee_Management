package com.demo.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // This part tells Spring what security rules to use for our website.
    // We are setting up who can see what pages.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // This is a simple way to configure who can access different parts of the website.
        // We're disabling CSRF for now, which is common for simple APIs.
        http.csrf().disable();
        
        // This is where we decide which pages are open to which user roles.
        // It's a bit like a set of rules for a bouncer at a club.
        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
            .requestMatchers("/api/reports/**").hasRole("ADMIN")
            .requestMatchers("/api/employees/search/**").hasAnyRole("ADMIN", "MANAGER")
            .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("ADMIN", "MANAGER")
            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
            .anyRequest().authenticated(); // This line says all other pages need you to be logged in.
            
        // We are using basic authentication, which means a simple username and password popup.
        http.httpBasic();
        
        return http.build();
    }

    // This part is like a list of users and their passwords and roles.
    // In a real app, these would be saved in a database, but we are keeping them simple here.
    @Bean
    public UserDetailsService userDetailsService() {
        
        // We create a user for the Admin role.
        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder().encode("adminpass"))
            .roles("ADMIN")
            .build();
            
        // We create a user for the Manager role.
        UserDetails manager = User.withUsername("manager")
            .password(passwordEncoder().encode("managerpass"))
            .roles("MANAGER")
            .build();
            
        // We create a user for the Employee role.
        UserDetails employee = User.withUsername("employee")
            .password(passwordEncoder().encode("employeepass"))
            .roles("EMPLOYEE")
            .build();
            
        // We put all our users in a simple in-memory list.
        return new InMemoryUserDetailsManager(admin, manager, employee);
    }

    // This is a simple tool to make passwords safe. It scrambles them up so no one can read them.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}