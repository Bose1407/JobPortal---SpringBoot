package com.bose.projects.services;
import com.bose.projects.repo.*;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bose.projects.dto.SignUpDto;
import com.bose.projects.entity.JobPosting;
import com.bose.projects.entity.JobSeekerEntity;
import com.bose.projects.entity.RoleEntity;
import com.bose.projects.exceptions.FailedToApplyJobException;
import com.bose.projects.exceptions.JobNotFoundException;
import com.bose.projects.exceptions.SeekerCreateException;
import com.bose.projects.exceptions.SeekerNotFoundException;
import com.bose.projects.repo.JobSeekerRepo;
import com.bose.projects.repo.RoleRepo;

import jakarta.transaction.Transactional;

@Service
public class SeekerService {
	
	@Autowired
	private JobSeekerRepo jobSeekerRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JobPostingRepository jobPostingRepo;
	
	public Map<String, Object> registerSeeker(SignUpDto reg) {
		Map<String,Object> response = new HashMap<>();
		try {
			RoleEntity role = roleRepo.findByName("ROLE_SEEKER");
			if(role==null) {
				throw new SeekerCreateException("Role not found exception,failed to create user");
			}
			JobSeekerEntity jobSeekerEntity = new JobSeekerEntity();
			jobSeekerEntity.setName(reg.getName());
			jobSeekerEntity.setEmail(reg.getEmail());
			jobSeekerEntity.setPassword(passwordEncoder.encode(reg.getPassword()));
			jobSeekerEntity.setPhoneNumber(reg.getPhoneNumber());
			jobSeekerEntity.setResumeLink(reg.getResumeLink());
			
			JobSeekerEntity savedUser = jobSeekerRepo.save(jobSeekerEntity);
			response.put("Status", "203");
			response.put("Created User", savedUser);
			return response;
		}
		catch(Exception e){
			response.put("Status", "500");
			response.put("Error", new SeekerCreateException(e.getMessage()));
			return response;	
		}
	}
	@Transactional
	public Map<String, Object> applyForJob(Long seekerId,Long jobId) {
		Map<String,Object> response = new HashMap<>();
		JobPosting job = jobPostingRepo.findById(jobId).orElseThrow(()->new JobNotFoundException("No Job Found with Id :"+jobId));
		JobSeekerEntity seeker = jobSeekerRepo.findById(seekerId).orElseThrow(()->new SeekerNotFoundException("No Seeker found with id :"+seekerId));
		
		if(!seeker.getAppliedJobs().contains(job)) {
			seeker.getAppliedJobs().add(job);
			job.getApplicants().add(seeker);
			JobPosting jobApplied = jobPostingRepo.save(job);
			jobSeekerRepo.save(seeker);
			response.put("Status", "203");
			response.put("Successfully Applied", jobApplied);
			return response;
			
		}
		return (Map<String, Object>) response.put("Already Registered", new FailedToApplyJobException("Failed to apply the Job"));
	}

}
