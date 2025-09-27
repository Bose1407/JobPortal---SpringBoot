package com.bose.projects.controller;

import java.util.Map;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;

import com.bose.projects.dto.AuthRequestDto;
import com.bose.projects.security.JwtUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDto req){
		org.springframework.security.core.Authentication auth = authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
	        );
		
		UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtUtils.generateToken(user);

        return ResponseEntity.ok(Map.of("token", token));

		
	}
	

}
