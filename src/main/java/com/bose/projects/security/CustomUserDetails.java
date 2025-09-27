package com.bose.projects.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.bose.projects.entity.JobRecruiter;
import com.bose.projects.entity.JobSeekerEntity;
import com.bose.projects.repo.JobRecruiterRepo;
import com.bose.projects.repo.JobSeekerRepo;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private JobRecruiterRepo jobRecruiterRepo;

    @Autowired
    private JobSeekerRepo jobSeekerRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<JobRecruiter> recruiterOpt = jobRecruiterRepo.findByEmail(email);
        if (recruiterOpt.isPresent()) {
            JobRecruiter recruiter = recruiterOpt.get();
            return User.builder()
                    .username(recruiter.getEmail())
                    .password(recruiter.getPassword())
                    .roles("RECRUITER")
                    .build();
        }

        Optional<JobSeekerEntity> seekerOpt = jobSeekerRepo.findByEmail(email);
        if (seekerOpt.isPresent()) {
            JobSeekerEntity seeker = seekerOpt.get();
            return User.builder()
                    .username(seeker.getEmail())
                    .password(seeker.getPassword())
                    .roles("SEEKER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }


}
