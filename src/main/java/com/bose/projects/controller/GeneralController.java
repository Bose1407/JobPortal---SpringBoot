package com.bose.projects.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bose.projects.entity.JobPosting;
import com.bose.projects.entity.JobRecruiter;
import com.bose.projects.entity.JobSeekerEntity;
import com.bose.projects.repo.JobPostingRepository;
import com.bose.projects.repo.JobRecruiterRepo;
import com.bose.projects.repo.JobSeekerRepo;

@RestController
@RequestMapping("/api")
public class GeneralController {
	@Autowired
	private JobPostingRepository jobPostingRepo;
	
	@Autowired
	private JobSeekerRepo jobSeekerRepo;
	@Autowired
	private JobRecruiterRepo jobRecruiterRepo;

	@GetMapping("/jobs")
	public List<JobPosting> getAllJobs(){
		return jobPostingRepo.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<JobSeekerEntity> getUserById(@PathVariable Long id) {
	    return jobSeekerRepo.findById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/recruiter/{id}")
	public ResponseEntity<JobRecruiter> getRecruiterById(@PathVariable Long id) {
	    return jobRecruiterRepo.findById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}


}
