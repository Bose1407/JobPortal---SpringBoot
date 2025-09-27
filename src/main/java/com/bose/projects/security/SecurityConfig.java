package com.bose.projects.security;
import com.bose.projects.repo.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bose.projects.entity.JobRecruiter;

@Configuration
public class SecurityConfig {
	
	 @Autowired
	 private JwtFilter jwtFilter;
	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http
	         .csrf(csrf -> csrf.disable())
	         .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	         .authorizeHttpRequests(auth -> auth
	             .requestMatchers("/api/auth/**").permitAll()
	             .requestMatchers("/api/recruiter/**").hasRole("RECRUITER")
	             .requestMatchers("/api/seeker/**").hasRole("SEEKER")
	             .anyRequest().authenticated()
	         )
	         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	     return http.build();
	 }

	
	 @Bean
	    public UserDetailsService userDetailService(){

	        return new CustomUserDetails();
	 }
	
	 	@Bean
	    public DaoAuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(){
	        return new ProviderManager(List.of(authenticationProvider()));
	    }

}
