package com.ib.webchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

	/**
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        		http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/msg/admin")
                .hasRole("ADMIN")
                .requestMatchers("/msg/normal")
                .hasRole("NORMAL")
                .requestMatchers("/**")
                .permitAll()
                .and()
                .formLogin();
        return http.build();
    } **/
    
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/**").permitAll();
		return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user_Normal = User.withUsername("user").password(passwordEncoder().encode("user")).roles("NORMAL").build();
        UserDetails user_Admin = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(user_Normal, user_Admin);
        return manager;
    }
}