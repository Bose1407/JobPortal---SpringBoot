package com.bose.projects.dto;

import java.util.List;

public class JobPostingDto {
	private String jobName;
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
	private String description;
    private String jobPackage;
    private List<String> skills;
    private String location;

}
