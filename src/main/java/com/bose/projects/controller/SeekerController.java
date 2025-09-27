package com.bose.projects.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bose.projects.dto.SignUpDto;
import com.bose.projects.services.SeekerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seeker")
public class SeekerController {
	
	@Autowired
	private SeekerService seekerService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerSeeker(@RequestBody @Valid SignUpDto req){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(seekerService.registerSeeker(req));
	}
	
	@PostMapping("/{seekerId}/apply/{jobId}")
	public ResponseEntity<Map<String, Object>> applyForJob(@PathVariable Long seekerId,@PathVariable Long jobId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(seekerService.applyForJob(seekerId, jobId));
	}
	
	
}
