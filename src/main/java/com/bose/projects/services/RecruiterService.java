package com.bose.projects.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bose.projects.dto.GetApplicantsForSpecificJobDto;
import com.bose.projects.dto.JobPostingDto;
import com.bose.projects.dto.RecruiterRegisterDto;
import com.bose.projects.entity.JobPosting;
import com.bose.projects.entity.JobRecruiter;
import com.bose.projects.entity.JobSeekerEntity;
import com.bose.projects.exceptions.JobNotFoundException;
import com.bose.projects.exceptions.RecruiterNotFoundException;
import com.bose.projects.exceptions.SeekerCreateException;
import com.bose.projects.repo.JobPostingRepository;
import com.bose.projects.repo.JobRecruiterRepo;
import com.bose.projects.repo.RoleRepo;

@Service
public class RecruiterService {
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private JobRecruiterRepo jobRecruiterRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JobPostingRepository jobPostingRepository;
	
	public Map<String, Object> registerRecruiter(RecruiterRegisterDto dto) {
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			JobRecruiter jobRecruiter = new JobRecruiter();
			
			jobRecruiter.setEmail(dto.getEmail());
			jobRecruiter.setName(dto.getName());
			jobRecruiter.setPassword(passwordEncoder.encode(dto.getPassword()));
			jobRecruiter.setCompanyName(dto.getCompanyName());
			jobRecruiter.setLocation(dto.getLocation());
			
			JobRecruiter jobRec = jobRecruiterRepo.save(jobRecruiter);
			response.put("Status", "203");
			response.put("Created Recruiter", jobRec);
			return response;
		}catch(Exception e) {
			response.put("Status", "500");
			response.put("Error", new SeekerCreateException(e.getMessage()));
			return response;
		}
	}

	public Map<String, Object> postJob(Long recruiterId,JobPostingDto dto) {
		Map<String,Object> response = new HashMap<String,Object>();
		 JobRecruiter recruiter = jobRecruiterRepo.findById(recruiterId).orElseThrow(()->new RecruiterNotFoundException("Recruiter Not Found with id - "+recruiterId));
		 JobPosting job = new JobPosting();
		 job.setJobName(dto.getJobName());
	     job.setDescription(dto.getDescription());
	     job.setJobPackage(dto.getJobPackage());
	     job.setSkills(dto.getSkills());
	     job.setDescription(dto.getLocation());
	     job.setRecruiter(recruiter);
	        JobPosting jobPosting = jobPostingRepository.save(job);
	        response.put("Status", "203");
			response.put("Created Recruiter", jobPosting);
			return response; 
	        
	}
	
	public List<JobPosting> getRecruiterJobs(Long recruiterId) {
        return jobPostingRepository.findByRecruiterId(recruiterId);
    }
	
	public Map<String, Object> createJob(Long recId,JobPostingDto job) {
		Map<String,Object> response = new HashMap<String,Object>();
		JobPosting jobPosting = new JobPosting();
		jobPosting.setJobName(job.getJobName());
		jobPosting.setDescription(job.getDescription());
		jobPosting.setJobPackage(job.getJobPackage());
		jobPosting.setLocation(job.getLocation());
		jobPosting.setSkills(new ArrayList(List.of("java","js","python")));
		
		JobRecruiter jobRecruiter = jobRecruiterRepo.findById(recId).orElseThrow(()->new RecruiterNotFoundException("Recruiter not found"));
		jobPosting.setRecruiter(jobRecruiter);
		
		JobPosting newjob = jobPostingRepository.save(jobPosting);
		response.put("Status","203");
		response.put("Created Job", newjob);
		return response;
		
	}

	public List<GetApplicantsForSpecificJobDto> getApplicantsOfParticularJob(Long jobId) {
		JobPosting jobPosting = jobPostingRepository.findById(jobId).orElseThrow(()->new JobNotFoundException("No Job found with Id :"+jobId));
		List<GetApplicantsForSpecificJobDto> applicants = new ArrayList<>();
		for(JobSeekerEntity entity : jobPosting.getApplicants()) {
			GetApplicantsForSpecificJobDto dto = new GetApplicantsForSpecificJobDto();
			dto.setName(entity.getName());
			dto.setEmail(entity.getEmail());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setResumeLink(entity.getResumeLink());
			dto.setId(entity.getId());
			applicants.add(dto);
		}
		return applicants;
	}

}
