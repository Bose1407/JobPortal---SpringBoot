package com.bose.projects.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bose.projects.entity.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting,Long>{

	List<JobPosting> findByRecruiterId(Long recruiterId);
	

}
