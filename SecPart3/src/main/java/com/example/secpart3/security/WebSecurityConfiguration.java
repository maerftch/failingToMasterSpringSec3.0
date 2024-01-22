package com.example.secpart3.security;

import com.example.secpart3.filters.JwtTokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//not working with WebSecurityConfigurerAdapter cuz it's deprecated in Spring 3.0
public class WebSecurityConfiguration {
        @Autowired
        public MyUserDetailsService userDetailsService;
        @Autowired
        public BCryptPasswordEncoder passwordEncoder;
        @Autowired
        public JwtTokenFilter jwtTokenFilter;

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();

        }
        @Autowired
        public void configurePasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
                // adding custom UserDetailsService and encryption bean to Authentication Manager
                builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception {
                httpSecurity
                        // disabling csrf since we won't use form login
                        .csrf().disable()
                        // giving every permission to every request for /login endpoint
                        .authorizeHttpRequests().requestMatchers("/login").permitAll()
                        // for everything else, the user has to be authenticated
                        .anyRequest().authenticated()
                        // setting stateless session, because we choose to implement Rest API
                        .and().sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }





}