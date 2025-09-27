package com.bose.projects.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "job_posting")
public class JobPosting {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String jobName;

    @NotBlank
    private String description;

    @NotBlank
    private String jobPackage;

    @ElementCollection
    private List<String> skills;

    private String location;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    @JsonBackReference
    private JobRecruiter recruiter;

    public JobPosting(Long id, @NotBlank String jobName, @NotBlank String description, @NotBlank String jobPackage,
			List<String> skills, String location, JobRecruiter recruiter, List<JobSeekerEntity> applicants) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.description = description;
		this.jobPackage = jobPackage;
		this.skills = skills;
		this.location = location;
		this.recruiter = recruiter;
		this.applicants = applicants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobPackage() {
		return jobPackage;
	}

	public void setJobPackage(String jobPackage) {
		this.jobPackage = jobPackage;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public JobRecruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(JobRecruiter recruiter) {
		this.recruiter = recruiter;
	}

	public List<JobSeekerEntity> getApplicants() {
		return applicants;
	}

	public JobPosting() {
		super();
	}

	public void setApplicants(List<JobSeekerEntity> applicants) {
		this.applicants = applicants;
	}

	@ManyToMany
    @JoinTable(
        name = "job_applicants",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "seeker_id")
    )
	@JsonBackReference
    private List<JobSeekerEntity> applicants;
}
