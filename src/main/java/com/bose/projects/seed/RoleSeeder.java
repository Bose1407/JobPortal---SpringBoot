package com.bose.projects.seed;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bose.projects.entity.RoleEntity;
import com.bose.projects.repo.RoleRepo;

import lombok.RequiredArgsConstructor;

@Component
public class RoleSeeder implements CommandLineRunner {
	
	@Autowired
	private RoleRepo roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            RoleEntity seeker = new RoleEntity ();
            seeker.setName("ROLE_SEEKER");

            RoleEntity  recruiter = new RoleEntity ();
            recruiter.setName("ROLE_RECRUITER");

            RoleEntity admin = new RoleEntity ();
            admin.setName("ROLE_ADMIN");

            roleRepository.saveAll(List.of(seeker, recruiter, admin));
        }
    }
}
