package com.bose.projects.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bose.projects.dto.RecruiterRegisterDto;
import com.bose.projects.entity.JobPosting;
import com.bose.projects.entity.JobSeekerEntity;
import com.bose.projects.repo.JobSeekerRepo;
import com.bose.projects.services.RecruiterService;

import jakarta.validation.Valid;

import com.bose.projects.dto.JobPostingDto;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {
	@Autowired
	private RecruiterService recruiterService;
	private JobSeekerRepo jobSeekerRepo;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registerRecruiter(@RequestBody @Valid RecruiterRegisterDto  recruiter) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(recruiterService.registerRecruiter(recruiter));
		
	}
	@GetMapping("/jobs/{recruiterId}")
	public ResponseEntity<List<JobPosting>> getJobsByRecruiterId(@PathVariable Long recruiterId) {
		return ResponseEntity.status(HttpStatus.OK).body(recruiterService.getRecruiterJobs(recruiterId));
	}
	
	@PostMapping("/{recId}/jobs")
	public ResponseEntity<Map<String, Object>> createJobs(@PathVariable Long recId,@RequestBody @Valid JobPostingDto job){
		return ResponseEntity.status(HttpStatus.OK).body(recruiterService.createJob(recId, job));
	}
	
	@GetMapping("/job/{jobId}/applicants")
	public ResponseEntity<?>getApplicantsForSpecificJob(@PathVariable Long jobId){
		return ResponseEntity.status(HttpStatus.OK).body(recruiterService.getApplicantsOfParticularJob(jobId));
	}
	@GetMapping("/find/{user_id}")
	public ResponseEntity<JobSeekerEntity> getUserById(@PathVariable Long user_id) {
	    return jobSeekerRepo.findById(user_id)
	        .map(ResponseEntity::ok)
	        .orElse(ResponseEntity.notFound().build());
	}

}
