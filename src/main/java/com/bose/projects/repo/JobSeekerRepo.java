package com.bose.projects.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bose.projects.entity.JobSeekerEntity;
@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeekerEntity,Long>{
	 Optional<JobSeekerEntity> findByEmail(String email);
}
