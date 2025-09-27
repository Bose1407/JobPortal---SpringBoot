package com.bose.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
	
    public String getName() {
		return name;
	}
	public SignUpDto() {
		super();
	}
	public SignUpDto(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
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
	private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String resumeLink;
	public SignUpDto(String name, String email, String password, String phoneNumber, String resumeLink) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.resumeLink = resumeLink;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getResumeLink() {
		return resumeLink;
	}
	public void setResumeLink(String resumeLink) {
		this.resumeLink = resumeLink;
	}
}
