package com.bose.projects.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bose.projects.entity.RoleEntity;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity,Long>{

	RoleEntity findByName(String string);

}
