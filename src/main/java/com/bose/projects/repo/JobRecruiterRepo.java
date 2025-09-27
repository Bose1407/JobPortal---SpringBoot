package com.bose.projects.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.bose.projects.entity.JobRecruiter;

@Repository
public interface JobRecruiterRepo extends JpaRepository<JobRecruiter,Long>{

	 Optional<JobRecruiter> findByEmail(String email);

	

}
