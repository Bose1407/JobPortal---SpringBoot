package com.bose.projects.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name = "job_recruiter")
public class JobRecruiter{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    public JobRecruiter() {
		super();
	}

	public JobRecruiter(Long id, @NotBlank String name, String email, @NotBlank String password, String companyName,
			String location, Set<RoleEntity> roles, List<JobPosting> jobPostings) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.companyName = companyName;
		this.location = location;
		this.roles = roles;
		this.jobPostings = jobPostings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public List<JobPosting> getJobPostings() {
		return jobPostings;
	}

	public void setJobPostings(List<JobPosting> jobPostings) {
		this.jobPostings = jobPostings;
	}

	@NotBlank
    private String password;

    private String companyName;
    

	private String location;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "recruiter_roles",
        joinColumns = @JoinColumn(name = "recruiter_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<JobPosting> jobPostings;
}

